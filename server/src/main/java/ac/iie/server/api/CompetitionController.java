package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.domain.Competition;
import ac.iie.server.service.CompetitionService;
import ac.iie.server.service.CompetitionTypeService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 比赛操作接口
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-1 8:31
 * @version: 1.0.0
 */
@RestController
@RequestMapping("competition")
@Slf4j
public class CompetitionController extends BaseController<Competition> {


    public CompetitionController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, CompetitionVFier competitionVFier) {
        super(competitionTypeService, competitionService, competitionVFier);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response createCompetition(@RequestBody String param) {
        Gson gson = new Gson();
        Competition competition = new Competition();
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
        //**************************************************必填参数校验************************************************
        //比赛主题
        if (paramsObj.get("title") == null || StringUtils.isBlank(paramsObj.get("title").getAsString())) {
            return Response.paramError("title为必填项");
        }
        competition.setTitle(paramsObj.get("title").getAsString());

        //比赛内容
        if (paramsObj.get("content") == null || StringUtils.isBlank(paramsObj.get("content").getAsString())) {
            return Response.paramError("content为必填项");
        }
        competition.setContent(paramsObj.get("content").getAsString());

        //数据集url，需要校验是否上传数据集
        if (paramsObj.get("data_url") == null || StringUtils.isBlank(paramsObj.get("data_url").getAsString())) {
            return Response.paramError("data_url为必填项");
        }
        competition.setDataUrl(paramsObj.get("data_url").getAsString());

        //评测程序，需要校验是否上传评测程序
        if (paramsObj.get("program_url") == null || StringUtils.isBlank(paramsObj.get("program_url").getAsString())) {
            return Response.paramError("program_url为必填项");
        }
        competition.setProgramUrl(paramsObj.get("program_url").getAsString());

        //评分项不能为空，最多支持5项（格式）
        if (paramsObj.get("score_items") == null || StringUtils.isBlank(paramsObj.get("score_items").getAsString())) {
            return Response.paramError("score_items为必填项");
        }
        competition.setScoreItems(paramsObj.get("score_items").getAsString());

        //数据集展示字段，
        if (paramsObj.get("key_data_map") == null || StringUtils.isBlank(paramsObj.get("key_data_map").getAsString())) {
            return Response.paramError("key_data_map为必填项");
        }
        competition.setKeyDataMap(paramsObj.get("key_data_map").getAsString());

        //比赛类型
        if (paramsObj.get("type_id") == null || StringUtils.isBlank(paramsObj.get("type_id").getAsString())) {
            return Response.paramError("type_id为必填项");
        }
        competition.setTypeId(paramsObj.get("type_id").getAsString());

        //比赛模式
        if (paramsObj.get("type") == null) {
            return Response.paramError("type为必填项");
        }
        competition.setType(paramsObj.get("type_id").getAsInt());

        //举办方列表
        JsonElement userObj = paramsObj.get("users");
        if (userObj == null || !userObj.isJsonArray()) {
            return Response.paramError("users为必填数组");
        }
        List<String> userList = gson.fromJson(userObj.getAsJsonArray(), new TypeToken<List<String>>() {
        }.getType());

        //************************************************非必填参数校验************************************************
        //赛程描述
        if (paramsObj.get("description") != null) {
            competition.setDescription(paramsObj.get("description").getAsString());
        }

        //数据集描述
        if (paramsObj.get("data_desc") != null) {
            competition.setDataDesc(paramsObj.get("data_desc").getAsString());
        }

        //是否包含多媒体默认为不包含
        if (paramsObj.get("is_include_media") != null) {
            competition.setIsIncludeMedia(paramsObj.get("is_include_media").getAsBoolean());
        }

        //logo_url
        if (paramsObj.get("logo_url") != null) {
            competition.setLogoUrl(paramsObj.get("logo_url").getAsString());
        }

        //奖金
        if (paramsObj.get("bonus") != null) {
            competition.setBonus(paramsObj.get("bonus").getAsInt());
        }
        //************************************************业务调用与控制************************************************
        try {
            competitionService.createCompetition(competition, userList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.databaseError("创建比赛错误请检查！");
        }
        log.info("创建比赛成功：" + competition + "组织方列表：" + userList);
        return Response.operateSucessNoData();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Response getCompetitions() {
        /*
        数据校验
         */
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response getCompetition(@PathVariable String id) {
        /*
        数据校验
         */
        if (StringUtils.isAllBlank(id)) {
            return Response.paramError("参数错误");
        }
        Competition competition = null;
        try {
            competition = competitionService.getCompetitionById(id);
        } catch (Exception e) {
            log.error("获取id:" + id + "比赛错误");
        }
        if (competition == null) {
            return Response.operateSucessNoData();
        } else {
            return Response.operateSucessAndHaveData(competition);
        }
    }


}
