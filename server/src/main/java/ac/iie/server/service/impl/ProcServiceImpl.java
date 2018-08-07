package ac.iie.server.service.impl;

import ac.iie.common.utils.CompressUtil;
import ac.iie.common.utils.FileUtil;
import ac.iie.server.api.base.SystemConfig;
import ac.iie.server.dao.*;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.Data;
import ac.iie.server.service.IProcService;
import ac.iie.server.service.impl.BaseService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

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
            @Cleanup InputStream inputStream;
            String descJson = "";
            try {
                inputStream = new FileInputStream(file);
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
            JsonArray datas = jsonObject.get("data").getAsJsonArray();
            Data data = null;
            String md5 = null;
            String ext = null;
            for (JsonElement je : datas) {
                data = new Data();
                data.setCompId(competition.getId());
                data.setType(0);
                md5 = je.getAsJsonObject().get("md5").getAsString();
                ext = je.getAsJsonObject().get("ext").getAsString();
                data.setMediaUrls(md5 + "." + ext);
            }
        } else {

        }
        return false;
    }

    private boolean dealProgram(Competition competition) {

        return false;
    }
}
