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
    private boolean createDetetion(VersionAnswers versionAnswers) {
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

}
