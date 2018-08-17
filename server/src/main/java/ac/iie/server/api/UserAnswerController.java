package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.base.Constant;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.VersionAnswers;
import ac.iie.server.service.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

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
        if (type < Constant.COMMON_PATTERN || type > Constant.TARGET_PATTERN) {
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
            fileType = Constant.FILE_USER_ANSWER_RESULT;
        } else {
            fileType = Constant.FILE_USER_ANSWER_ENGINE;
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

        //答案的运行命令
        if (paramsObj.get("user_id") == null || StringUtils.isBlank(paramsObj.get("user_id").getAsString())) {
            return Response.paramError("user_id为必填项");
        }
        versionAnswers.setUserId(paramsObj.get("user_id").getAsString());

        //答案的运行命令
        if (paramsObj.get("user_name") == null || StringUtils.isBlank(paramsObj.get("user_name").getAsString())) {
            return Response.paramError("user_id为必填项");
        }
        versionAnswers.setUserName(paramsObj.get("user_name").getAsString());

        //答案的运行命令
        if (paramsObj.get("image_url") == null || StringUtils.isBlank(paramsObj.get("image_url").getAsString())) {
            return Response.paramError("uimage_url为必填项");
        }
        versionAnswers.setImageUrl(paramsObj.get("image_url").getAsString());

        if (paramsObj.get("type") == null) {
            return Response.paramError("type为必填项");
        }
        versionAnswers.setType(paramsObj.get("type").getAsInt());
        boolean commitCloudFlag = answerCommitCloud(versionAnswers);
        if (commitCloudFlag) {
            versionAnswers.setStatus(Constant.ANSWER_DATECTING);
            versionAnswers.setStatusMsg(Constant.ANSWER_DETECTING_MSG);
        } else {
            versionAnswers.setStatus(Constant.ANSWER_CREATE_DETE_FAILED);
            versionAnswers.setStatusMsg(Constant.ANSWER_CREATE_DETE_FAILED_MSG);
        }
        if (versionAnswersService.commitAnswer(versionAnswers)) {
            return Response.operateSucessNoData();
        } else {
            return Response.databaseError("用户答案提交失败");
        }
    }

    /**
     * @Description: 根据用户参赛id获取版本答案
     * @param:
     * @return:
     * @date: 2018-8-17 16:48
     */
    @GetMapping(value = "/{userCompId}/competition")
    @ResponseBody
    public Response getAnswersByUserCompId(@PathVariable String userCompId) {
        List<VersionAnswers> versionAnswers;
        try {
            versionAnswers = versionAnswersService.getUserAnswerByUserCompId(userCompId);
            return Response.operateSucessAndHaveData(versionAnswers);
        } catch (Exception e) {
            log.error("根据参赛/比赛id获取用户版本答案失败");
            return Response.databaseError("根据参赛/比赛id获取用户版本答案失败");
        }
    }

    /**
     * @Description:
     * @param:
     * @return:
     * @date: 2018-8-15 18:01
     */
    private boolean answerCommitCloud(VersionAnswers versionAnswers) {
        String results;
        String param = gnerateJson(versionAnswers);
        String interfaceKey;
        if (versionAnswers.getType() == Constant.TARGET_PATTERN) {
            interfaceKey = Constant.CLOUD_QUERY_DETE;
        } else {
            interfaceKey = Constant.CLOUD_UPLOAD_RESULT;
        }
        try {
            results = cloudService.cloudService(param, interfaceKey, Constant.POST_INTERFACE);
            JsonObject jsonObject = gson.fromJson(results, JsonObject.class);
            log.info("***************创建评测任务云平台返回结果：" + results);
            return "ok".equals(jsonObject.get("status").getAsString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用云平台创建测评服务失败：");
            return false;
        }
    }

    private String gnerateJson(VersionAnswers versionAnswers) {
        JsonObject result = new JsonObject();
        if (versionAnswers.getType() == Constant.COMMON_PATTERN) {
            result.addProperty("projectID", versionAnswers.getId());
            result.addProperty("projectName", versionAnswers.getUserName());
            result.addProperty("imageUrl", versionAnswers.getResultUrl());
            result.addProperty("type", versionAnswers.getType());
            result.addProperty("resource", versionAnswers.getResource());
            result.addProperty("requestAddress", "");
            result.addProperty("runCommand", versionAnswers.getRunCommand());
            result.addProperty("serviceVersion", versionAnswers.getVersion());
        }
        if (versionAnswers.getType() == Constant.TARGET_PATTERN) {
            result.addProperty("projectID", versionAnswers.getId());
            result.addProperty("teamName", versionAnswers.getUserName());
            result.addProperty("Url", versionAnswers.getResultUrl());
            result.addProperty("version", versionAnswers.getVersion());
        }
        return result.toString();
    }

}
