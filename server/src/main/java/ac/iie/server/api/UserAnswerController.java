package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.base.Contants;
import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.UserCompetition;
import ac.iie.server.domain.VersionAnswers;
import ac.iie.server.service.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description: 参赛者答案接口
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-10 17:20
 * @version: 1.0.0
 */
@RestController
@RequestMapping("answers")
@Slf4j
public class UserAnswerController extends BaseController<Competition> {

    private static Gson gson = new Gson();

    public UserAnswerController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, UserCompetitionService userCompetitionService, VersionAnswersService versionAnswersService, SystemConfig systemConfig, ICloudService cloudService) {
        super(competitionTypeService, competitionService, userCompetitionService, versionAnswersService, systemConfig, cloudService);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Response dataUpload(@RequestParam("MetaFile") MultipartFile file, int type, String comName, String userid, String version) {
        if (type < Contants.COMMON_PATTERN || type > Contants.TARGET_PATTERN) {
            return Response.paramError("type不合法，请检查！");
        }
        if (StringUtils.isBlank(comName) || StringUtils.isBlank(comName)) {
            return Response.paramError("comName不合法，请检查！");
        }
        if (StringUtils.isBlank(userid) || StringUtils.isBlank(userid)) {
            return Response.paramError("userId不合法，请检查！");
        }
        if (StringUtils.isBlank(version) || StringUtils.isBlank(version)) {
            return Response.paramError("version不合法，请检查！");
        }
        int fileType;
        if (type == 0) {
            fileType = Contants.FILE_USER_ANSWER_RESULT;
        } else {
            fileType = Contants.FILE_USER_ANSWER_ENGINE;
        }
        String path = this.getAbsolutePath(fileType, comName, userid, version);
        String url = path + File.separator + file.getOriginalFilename();
        boolean flag = this.saveFile(file, url, path);
        if (flag) {
            return Response.operateSucessAndHaveData(url);
        } else {
            return Response.databaseError("文件上传失败！");
        }
    }

    /**
     * @Description: 用户答案提交接口
     * @param:
     * @return:
     * @date: 2018-8-10 17:36
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Response answerCommit(@RequestParam String param) {
        Gson gson = new Gson();
        VersionAnswers versionAnswers = new VersionAnswers();
        /*
        数据校验与封装
         */
        if (StringUtils.isBlank(param)) {
            return Response.paramError("参数错误");
        }
        JsonObject paramsObj = gson.fromJson(param, JsonObject.class);
        if (paramsObj.isJsonNull() || paramsObj.isJsonObject()) {
            return Response.paramError("非json格式入参，请检查");
        }
        //参赛用户id
        if (paramsObj.get("user_comp_id") == null || StringUtils.isBlank(paramsObj.get("user_comp_id").getAsString())) {
            return Response.paramError("user_comp_id为必填项");
        }
        versionAnswers.setUserCompId(paramsObj.get("user_comp_id").getAsString());

        //本次答案版本
        if (paramsObj.get("version") == null || StringUtils.isBlank(paramsObj.get("version").getAsString())) {
            return Response.paramError("version为必填项");
        }
        versionAnswers.setVersion(paramsObj.get("version").getAsString());

        //数据集url，需要校验是否上传数据集
        if (paramsObj.get("result_url") == null || StringUtils.isBlank(paramsObj.get("result_url").getAsString())) {
            return Response.paramError("result_url为必填项");
        }
        versionAnswers.setResultUrl(paramsObj.get("result_url").getAsString());

        //答案的运行命令
        if (paramsObj.get("run_command") == null || StringUtils.isBlank(paramsObj.get("run_command").getAsString())) {
            return Response.paramError("run_command为必填项");
        }
        versionAnswers.setRunCommand(paramsObj.get("run_command").getAsString());

        if (versionAnswersService.commitAnswer(versionAnswers)) {
            return Response.operateSucessNoData();
        } else {
            return Response.databaseError("用户答案提交失败");
        }
    }

    /**
     * @Description:
     * @param:
     * @return:
     * @date: 2018-8-15 18:01
     */
    private boolean answerCommitCloud(VersionAnswers versionAnswers, int type) {

        try {
            String results = cloudService.cloudService("", Contants.CLOUD_CREATE_DETE, Contants.POST_INTERFACE);
            JsonObject jsonObject = gson.fromJson(results, JsonObject.class);
            log.info("***************创建评测任务云平台返回结果：" + results);
            return "ok".equals(jsonObject.get("status").getAsString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用云平台创建测评服务失败：");
            return false;
        }
    }

    private String gnerateJson(VersionAnswers versionAnswers, int type) {
        JsonObject result = new JsonObject();
        if (type == Contants.COMMON_PATTERN) {
//            projectID:’77aac21’
//            projectName:   test //项目名称（英文）
//            type: 0/1  //0：大赛模式，1：靶场模式
//            imageUrl：192.168.11.51/img/test.tar  //镜像文件下载地址
//            resource: 1/2/3/4  //1： (2核，4G)，2：（4核，8G），3：（8核，16G），4：（16核，24G）
//            requestAddress: /evaluating/test  //测评服务地址
//            runCommand：start.sh  //启动脚本
//            serviceVersion：1   //服务版本
            result.addProperty("projectID", versionAnswers.getId());
            result.addProperty("projectName", "");
            result.addProperty("imageUrl", versionAnswers.getResultUrl());
            result.addProperty("type", type);
            result.addProperty("resource", versionAnswers.getResource());
            result.addProperty("requestAddress", "");
            result.addProperty("runCommand", versionAnswers.getRunCommand());
            result.addProperty("serviceVersion", versionAnswers.getVersion());
        }
        if (type == Contants.TARGET_PATTERN) {
//            projectID:’77aac21’
//            projectName: test  //项目名称（英文）
//            teamName:   test //团队名称（英文）
//            version：1   //任务版本
            result.addProperty("projectID", versionAnswers.getId());
            result.addProperty("teamName", "");
            result.addProperty("Url", versionAnswers.getResultUrl());
            result.addProperty("version", versionAnswers.getVersion());
        }
        return result.toString();
    }

}
