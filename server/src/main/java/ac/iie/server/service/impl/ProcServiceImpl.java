package ac.iie.server.service.impl;

import ac.iie.common.utils.CompressUtil;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.dao.*;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.Data;
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
    SystemConfig systemConfig;

    @Autowired
    RedisTemplate redisTemplate;


    public ProcServiceImpl(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper) {
        super(competitionMapper, competitionTypeMapper, dataMapper, userCompetitionMapper, versionAnswersMapper);
    }

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
        List<Competition> competitions = null;
        try {
            competitions = competitionMapper.getCompetitonByStatus(1);
        } catch (Exception e) {

        }
        int dealNum = competitions.size();
        log.info("*****************本次任务获取[" + dealNum + "]个比赛任务进行处理***************");
        for (int i = 0; i < dealNum; i++) {
            Competition competition = competitions.get(i);
            log.info("********************开始处理[" + competition.getTitle() + "]比赛");
            if (!dealData(competition)) {
                dealProgram(competition);
            } else {

            }
        }
    }

    @Override
    public void dealProgram() {

    }

    private boolean dealData(Competition competition) {
        log.info("*******************开始处理数据集**************");
        String dataZipUrl = competition.getDataUrl();
        String path = dataZipUrl.substring(0, dataZipUrl.indexOf("/", -2)) + File.separator + systemConfig.getDataUrl();
        String filePath = dataZipUrl.substring(0, dataZipUrl.indexOf(".", -1)) + ".txt";
        boolean flag = false;
        try {
            log.info("******************开始解压数据集***********");
            CompressUtil.unZip(dataZipUrl, path, null);
        } catch (ZipException e) {
            log.error("**************数据集解压失败，程序返回*****");
            e.printStackTrace();
            return flag;
        }
        /*
        类型标志，如果是true则是多媒体类型多媒体类型读取描述文件；如果是false则直接读取文本文件
         */
        boolean typeFlag = competition.getIsIncludeMedia();
        Gson gson = new Gson();
        if (typeFlag) {
            String desc = path + File.separator + "desc.json";
            //检查是否存在多媒体描述文件如果
            File file = new File(desc);
            if (!file.exists()) {
                return flag;
            }
            String descJson = "";
            try {
                @Cleanup InputStream inputStream = new FileInputStream(file);
                byte b[] = new byte[1024];
                inputStream.read(b);
                descJson = new String(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JsonObject jsonObject = gson.fromJson(descJson, JsonObject.class);
            if (!jsonObject.isJsonObject()) {
                return flag;
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
        } else {
            File file = new File(filePath);
            if (!file.exists()) {

            } else {
                List<String> textJson = new ArrayList<>();
                try {
                    @Cleanup InputStream inputStream = new FileInputStream(file);
                    @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String str = null;
                    while ((str = bufferedReader.readLine()) != null) {
                        textJson.add(str);
                    }
                    if (saveData(textJson, competition.getId())) {
                        saveRedis(textJson, competition.getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        flag = true;
        return flag;
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

    private boolean dealProgram(Competition competition) {
        return false;
    }
}
