package ac.iie.server.api.base;

import ac.iie.common.utils.FileUtil;
import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.service.CompetitionService;
import ac.iie.server.service.UserCompetitionService;
import ac.iie.server.service.VersionAnswersService;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import ac.iie.server.service.CompetitionTypeService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

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

    @Autowired
    public BaseController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, UserCompetitionService userCompetitionService, VersionAnswersService versionAnswersService, SystemConfig systemConfig) {
        this.competitionTypeService = competitionTypeService;
        this.competitionService = competitionService;
        this.userCompetitionService = userCompetitionService;
        this.versionAnswersService = versionAnswersService;
        this.systemConfig = systemConfig;
    }

    protected boolean saveFile(CommonsMultipartFile file, String url, String path) {
        boolean flag;
        try {
            byte[] getData = file.getBytes();
            FileUtil.createDir(path);
            File localFile = new File(url);
            @Cleanup OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(localFile), "utf-8");
            ow.write(new String(getData));
            flag = true;
            log.info("=====================保存数据成功：" + url);
        } catch (Exception e) {
            log.error("====================保存数据失败：" + url);
            flag = false;
        }
        return flag;
    }

    protected String getAbsolutePath(int type, String comName) {
        String filePath = systemConfig.getBaseUrl() + File.separator + comName;
        if (type == Contants.FILE_DATA) {
            filePath = filePath + File.separator + systemConfig.getZipDataUrl();
        }
        if (type == Contants.FILE_LOGO) {
            filePath = filePath + File.separator + systemConfig.getLogoUrl();
        }
        if (type == Contants.FILE_PROGRAM) {
            filePath = filePath + File.separator + systemConfig.getProgramUrl();
        }
        return filePath;
    }
}

