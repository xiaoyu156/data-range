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
import ac.iie.server.service.VersionAnswersService;
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

    public DataController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, UserCompetitionService userCompetitionService, VersionAnswersService versionAnswersService, SystemConfig systemConfig) {
        super(competitionTypeService, competitionService, userCompetitionService, versionAnswersService, systemConfig);
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
        if (type < Contants.FILE_LOGO || type > Contants.FILE_USER_ANSWER_ENGINE) {
            return Response.paramError("type不合法，请检查！");
        }
        if (StringUtils.isBlank(comName) || StringUtils.isBlank(comName)) {
            return Response.paramError("comName不合法，请检查！");
        }
        String path = this.getAbsolutePath(type, comName);
        String url = path + File.separator + file.getOriginalFilename();
        boolean flag = this.saveFile(file, url, path);
        if (flag) {
            return Response.operateSucessAndHaveData(url);
        } else {
            return Response.databaseError("文件上传失败！");
        }
    }

}
