package ac.iie.server.api;

import ac.iie.common.utils.RegularUtil;
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
    public Response dataUpload(@RequestParam("MetaFile") MultipartFile file, int type, String comName, HttpServletResponse response, HttpServletRequest request) {
        if (type < Constant.FILE_LOGO || type > Constant.FILE_USER_ANSWER_ENGINE) {
            return Response.paramError("type不合法，请检查！");
        }
        if (StringUtils.isBlank(comName) || StringUtils.isBlank(comName)) {
            return Response.paramError("comName不能为空，请检查！");
        }
        if (RegularUtil.isEnglishOrChinese(comName, RegularUtil.CHINESE_PATTERN)) {
            return Response.paramError("comName不能为中文，请检查！");
        }

        String path = this.getAbsolutePath(type, comName, null, null);
        String url = path + File.separator + file.getOriginalFilename();
        boolean flag = this.saveFile(file, url, path);
        if (flag) {
            return Response.operateSucessAndHaveData(url);
        } else {
            return Response.databaseError("文件上传失败！");
        }
    }

    /**
     * @Description: 显示logo
     * @param:
     * @return:
     * @date: 2018-8-28 14:25
     */
    @GetMapping(value = "/{compId}/logo")
    public void getLogo(HttpServletResponse response, @PathVariable String compId) throws IOException {
        Competition competition = competitionService.getCompetitionById(compId);
        String logoUrl = competition.getDataUrl();
        if (StringUtils.isBlank(logoUrl)) {
            log.info("比赛" + compId + "没有获取到logo路径");
            return;
        }
        response.setContentType("image/png");
        File file = new File(logoUrl);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * @Description: 数据集下载
     * @param:
     * @return:
     * @date: 2018-8-28 14:26
     */
    @GetMapping(value = "/{compId}/data")
    public void getData(HttpServletResponse response, @PathVariable String compId) {
        Competition competition = competitionService.getCompetitionById(compId);
        String dataUrl = competition.getDataUrl();
        if (StringUtils.isBlank(dataUrl)) {
            log.info("比赛" + compId + "没有获取到数据集路径");
            return;
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        File data = new File(dataUrl);
        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(data);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("----------------------数据集下载失败------------");
        }

    }

}
