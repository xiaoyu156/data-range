package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.base.Constant;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.domain.Competition;
import ac.iie.server.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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


    public DataController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, UserCompetitionService userCompetitionService, VersionAnswersService versionAnswersService, SystemConfig systemConfig, ICloudService cloudService) {
        super(competitionTypeService, competitionService, userCompetitionService, versionAnswersService, systemConfig, cloudService);
    }

    /**
     * @Description: 文件上传接口
     * @param:
     * @return:
     * @date: 2018-8-6 15:08
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Response dataUpload(@RequestParam("MetaFile") MultipartFile file, int type, String comName) {
        if (type < Constant.FILE_LOGO || type > Constant.FILE_USER_ANSWER_ENGINE) {
            return Response.paramError("type不合法，请检查！");
        }
        if (StringUtils.isBlank(comName) || StringUtils.isBlank(comName)) {
            return Response.paramError("comName不合法，请检查！");
        }
        String path = this.getAbsolutePath(type, comName,null,null);
        String url = path + File.separator + file.getOriginalFilename();
        boolean flag = this.saveFile(file, url, path);
        if (flag) {
            return Response.operateSucessAndHaveData(url);
        } else {
            return Response.databaseError("文件上传失败！");
        }
    }

    @GetMapping(value = "/{fileName}/logo")
    public  void getLogo( HttpServletResponse response,@PathVariable String fileName ,String competitionTitle ) throws IOException {
        response.setContentType("image/png");
        File file = new File("J:\\work\\bisai\\logo\\programmer_1920.png");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }




}
