package ac.iie.server.api.base;

import ac.iie.common.utils.FileUtil;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description: api基类
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-14 17:23
 * @version: 1.0.0
 */
@Slf4j
public abstract class BaseController<T> {
    public CompetitionTypeService competitionTypeService;
    public CompetitionService competitionService;
    public UserCompetitionService userCompetitionService;
    public VersionAnswersService versionAnswersService;
    public SystemConfig systemConfig;
    public ICloudService cloudService;

    @Autowired
    public BaseController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, UserCompetitionService userCompetitionService, VersionAnswersService versionAnswersService, SystemConfig systemConfig, ICloudService cloudService) {
        this.competitionTypeService = competitionTypeService;
        this.competitionService = competitionService;
        this.userCompetitionService = userCompetitionService;
        this.versionAnswersService = versionAnswersService;
        this.systemConfig = systemConfig;
        this.cloudService = cloudService;
    }


    protected boolean saveFile(MultipartFile file, String url, String path) {
        boolean flag;
        try {
            byte[] getData = file.getBytes();
            FileUtil.createDir(path);
            FileUtils.writeByteArrayToFile(new File(url), getData);
            flag = true;
            log.info("=====================保存数据成功：" + url);
        } catch (Exception e) {
            log.error("====================保存数据失败：" + url);
            flag = false;
        }
        return flag;
    }

    protected String getAbsolutePath(int type, String comName, String userId, String version) {
        String filePath = systemConfig.getBaseUrl() + File.separator + comName;
        if (type == Constant.FILE_DATA) {
            filePath = filePath + File.separator + systemConfig.getZipDataUrl();
        }
        if (type == Constant.FILE_LOGO) {
            filePath = filePath + File.separator + systemConfig.getLogoUrl();
        }
        if (type == Constant.FILE_PROGRAM) {
            filePath = filePath + File.separator + systemConfig.getProgramUrl();
        }
        if (type == Constant.FILE_USER_ANSWER_RESULT) {
            filePath = filePath + File.separator + systemConfig.getUserAnswerResultUrl() + File.separator + userId + File.separator + version;
        }
        if (type == Constant.FILE_USER_ANSWER_RESULT) {
            filePath = filePath + File.separator + systemConfig.getUserAnswerEngineUrl() + File.separator + userId + File.separator + version;
        }
        return filePath;
    }
}

