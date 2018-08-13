package ac.iie.server.api;

import ac.iie.common.utils.FileUtil;
import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.base.Contants;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.domain.Competition;
import ac.iie.server.service.CompetitionService;
import ac.iie.server.service.CompetitionTypeService;
import ac.iie.server.service.UserCompetitionService;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * @Description: 数据集操作接口
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-1 8:27
 * @version: 1.0.0
 */
@RestController
@RequestMapping("data")
@Slf4j
public class DataController extends BaseController<Competition> {

    @Autowired
    SystemConfig systemConfig;

    public DataController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, CompetitionVFier competitionVFier, UserCompetitionService userCompetitionService) {
        super(competitionTypeService, competitionService, competitionVFier, userCompetitionService);
    }

    /**
     * @Description: 文件上传接口
     * @param:
     * @return:
     * @date: 2018-8-6 15:08
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Response dataUpload(@RequestParam("fileName") CommonsMultipartFile file, int type, String comName) {
        if (type > Contants.FILE_PROGRAM || type < Contants.FILE_USER_ANSWER) {
            return Response.paramError("type不合法，请检查！");
        }
        if (StringUtils.isBlank(comName) || StringUtils.isBlank(comName)) {
            return Response.paramError("comName不合法，请检查！");
        }
        String path = getAbsolutePath(type, comName);
        String url = path + File.separator + file.getOriginalFilename();
        boolean flag = saveFile(file, url, path);
        if (flag) {
            return Response.operateSucessAndHaveData(url);
        } else {
            return Response.databaseError("文件上传失败！");
        }
    }

    private boolean saveFile(CommonsMultipartFile file, String url, String path) {
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

    private String getAbsolutePath(int type, String comName) {
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
