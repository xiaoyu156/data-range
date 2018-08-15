package ac.iie.server.service.impl;

import ac.iie.common.utils.CompressUtil;
import ac.iie.server.api.base.Contants;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.dao.*;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.Data;
import ac.iie.server.service.ICloudService;
import ac.iie.server.service.IProcService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 业务过程异步处理
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-7 14:20
 * @version: 1.0.0
 */
@Slf4j
@Service(value = "procService")
public class ProcServiceImpl extends BaseService implements IProcService {

    @Autowired
    ICloudService cloudService;

    public ProcServiceImpl(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper, SystemConfig systemConfig, RedisTemplate redisTemplate) {
        super(competitionMapper, competitionTypeMapper, dataMapper, userCompetitionMapper, versionAnswersMapper, systemConfig, redisTemplate);
    }

    /**
     * 谷歌json解析对象
     */
    private static Gson gson = new Gson();

    /**
     * @Description: 创建比赛时数据集和评测程序的处理过程
     * @param:
     * @return:
     * @date: 2018-8-7 14:21
     */
    @Override
    public void dealCreateCompetitionProc() {
        /*
        获取需要处理的比赛数据
         */
        List<Competition> competitions;
        try {
            competitions = competitionMapper.getCompetitonByStatus(1);
        } catch (Exception e) {
            log.error("获取需要处理的比赛数据失败！");
            return;
        }
        int dealNum = competitions.size();
        log.info("*****************本次任务获取[" + dealNum + "]个比赛任务进行处理***************");
        for (Competition competition : competitions) {
            log.info("********************开始处理[" + competition.getTitle() + "]比赛");
            boolean flag = dealData(competition);
            try {
                if (flag) {
                    log.info("*****************处理[" + competition.getTitle() + "}比赛成功，请联系管理员审核");
                } else {
                    log.error("*****************处理[" + competition.getTitle() + "}比赛失败，请联系管理员审核");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("**********************");
            }

        }
    }

    @Override
    public void dealProgram() {

    }

    private boolean dealData(Competition competition) {
        log.info("*******************开始处理数据集**************");
        boolean flag;
        String dataZipUrl = competition.getDataUrl();
        String path = dataZipUrl.substring(0, dataZipUrl.indexOf("/", -2)) + File.separator + systemConfig.getDataUrl();
        String filePath = dataZipUrl.substring(0, dataZipUrl.indexOf(".", -1)) + ".txt";
        /*
        类型标志，如果是true则是多媒体类型多媒体类型读取描述文件；如果是false则直接读取文本文件
         */
        flag = compressData(dataZipUrl, path);
        if (!flag) {
            competitionMapper.updateCompetitionStatus(competition.getId(), Contants.COMPETITION_DATA_CHECK_FAILED, Contants.COMPETITION_DATA_CHECK_FAILED_MSG);
            return false;
        }

        boolean typeFlag = competition.getIsIncludeMedia();
        if (typeFlag) {
            flag = dealMediaFile(competition, path);
        } else {
            flag = dealTextFile(competition, filePath);
        }
        if (!flag) {
            competitionMapper.updateCompetitionStatus(competition.getId(), Contants.COMPETITION_DATA_CHECK_FAILED, Contants.COMPETITION_DATA_CHECK_FAILED_MSG);
            return false;
        }
        flag = createEvaluation(competition);
        if (!flag) {
            competitionMapper.updateCompetitionStatus(competition.getId(), Contants.COMPETITION_DATA_CHECK_FAILED, Contants.COMPETITION_PROGRAM_CHECK_FAILED_MSG);
            return false;
        } else {
            competitionMapper.updateCompetitionStatus(competition.getId(), Contants.COMPETITION_CHECKING, Contants.COMPETITION_CHECKING_MSG);
        }
        return true;
    }

    /**
     * @Description: 解压数据集压缩文件
     * @param:
     * @return:
     * @date: 2018-8-15 15:07
     */
    private boolean compressData(String dataZipUrl, String path) {
        try {
            log.info("******************开始解压数据集***********");
            CompressUtil.unZip(dataZipUrl, path, null);
            return true;
        } catch (ZipException e) {
            log.error("**************数据集解压失败，程序返回*****");
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @Description: 处理多媒体文件直接读取描述json
     * @param:
     * @return:
     * @date: 2018-8-15 15:03
     */
    private boolean dealMediaFile(Competition competition, String path) {
        String desc = path + File.separator + "desc.json";
        //检查是否存在多媒体描述文件如果
        File file = new File(desc);
        if (!file.exists()) {
            log.error("*******************************>文件" + desc + "不存在程序返回");
            return false;
        }
        String descJson;
        try {
            @Cleanup InputStream inputStream = new FileInputStream(file);
            byte[] metaByte = new byte[1024];
            int read = inputStream.read(metaByte);
            descJson = new String(metaByte);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        JsonObject jsonObject = gson.fromJson(descJson, JsonObject.class);
        if (!jsonObject.isJsonObject()) {
            log.error("json文件解析错误！");
            return false;
        }
        int count = jsonObject.get("count").getAsInt();
        log.info("***************************正在解析多媒体文件：[" + count + "]个***************");
        JsonArray datas = jsonObject.get("data").getAsJsonArray();
        Data data;
        for (JsonElement je : datas) {
            data = new Data();
            data.setCompId(competition.getId());
            data.setType(0);
            String md5 = je.getAsJsonObject().get("md5").getAsString();
            String ext = je.getAsJsonObject().get("ext").getAsString();
            data.setMediaUrls(md5 + "." + ext);
            dataMapper.insert(data);
        }
        return true;
    }

    /**
     * @Description: 处理文本类数据集，把行标保存到数据库，把数据保存到redis
     * @param:
     * @return:
     * @date: 2018-8-15 15:00
     */
    private boolean dealTextFile(Competition competition, String filePath) {
        List<String> textJson = new ArrayList<>();
        File file = new File(filePath);
        try {
            @Cleanup InputStream inputStream = new FileInputStream(file);
            @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                textJson.add(str);
            }
            if (saveData(textJson, competition.getId())) {
                saveRedis(textJson, competition.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Description: 数据集保存redis
     * @param:
     * @return:
     * @date: 2018-8-9 9:44
     */
    private boolean saveRedis(List<String> lines, String compId) {
        int size = lines.size();
        if (size > 0) {
            Map<Integer, Object> map = new HashMap<>(lines.size());
            for (int i = 0; i < size; i++) {
                map.put(i, lines.get(i));
            }
            try {
                redisTemplate.opsForHash().putAll(compId, map);
            } catch (Exception ex) {
                log.error("-----------------------[" + compId + "]比赛，数据集存入redis失败！--------");
                return false;
            }
        }
        return true;
    }

    /**
     * @Description: 保存到数据库
     * @param:
     * @return:
     * @date: 2018-8-9 14:51
     */
    private boolean saveData(List<String> lines, String compId) {
        int indexNum = lines.size();
        Data data;
        for (int i = 0; i < indexNum; i++) {
            data = new Data();
            data.setDataIndex(i);
            data.setCompId(compId);
            data.setType(0);
        }
        return true;
    }

    /**
     * @Description: 创建测评服务
     * @param: [lines, compId]
     * @return: boolean
     * @date: 2018-8-15 15:12
     */
    private boolean createEvaluation(Competition competition) {
        JsonObject param = new JsonObject();
        param.addProperty("projectID", competition.getId());
        param.addProperty("projectName", competition.getTitle());
        param.addProperty("imageUrl", competition.getProgramUrl());
        param.addProperty("type", competition.getType());
        param.addProperty("runCommand", competition.getRunCommand());
        param.addProperty("serviceVersion", 1);
        param.addProperty("requestAddress", "/test/evalution");
        try {
            String result = cloudService.cloudService("", Contants.CLOUD_CREATE_EVA, Contants.POST_INTERFACE);
            JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            log.info("***************创建测评服务云平台返回结果：" + result);
            return "ok".equals(jsonObject.get("status").getAsString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用云平台创建测评服务失败：" + param);
            return false;
        }
    }

}
