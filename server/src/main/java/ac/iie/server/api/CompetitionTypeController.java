package ac.iie.server.api;

import java.util.List;

import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.verifier.CompetitionVFier;
import org.apache.commons.lang3.StringUtils;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import ac.iie.common.utils.Response;
import ac.iie.server.domain.CompetitionType;
import ac.iie.server.service.CompetitionTypeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 比赛类型接口
 * @Author: wangxiaoyua
 * @CreateDate: 2018-7-30 9:32
 * @version: 1.0.0
 */
@RestController
@RequestMapping("competition-type")
@Slf4j
public class CompetitionTypeController extends BaseController {

    public CompetitionTypeController(CompetitionTypeService competitionTypeService, CompetitionVFier competitionVFier) {
        super(competitionTypeService, competitionVFier);
    }

    /**
     * @Description: 类型新增接口
     * @param:
     * @return:
     * @date: 2018-7-30 9:36
     */
    @RequestMapping(name = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response addType(@RequestBody CompetitionType competitionType) {

        if (competitionType == null) {
            return Response.paramError("比赛类型错误！");
        } else {
            if (StringUtils.isAllBlank(competitionType.getName())) {
                return Response.paramError("比赛类型名称不能为空！");
            }
            int addNum;
            try {
                addNum = competitionTypeService.insert(competitionType);
            } catch (DuplicateKeyException duplicateKeyException) {
                return Response.databaseError("比赛类型已经存在");
            } catch (Exception e) {
                return Response.databaseError("数据库错误！");
            }

            if (addNum > 0) {
                return Response.operateSucessAndHaveData(competitionType);
            } else {
                return Response.operateSucessNoData();
            }
        }
    }

    /**
     * @Description: 比赛类型删除接口
     * @param:
     * @return:
     * @date: 2018-7-30 9:37
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response deleteCompetitionById(@PathVariable String id) {
        if (StringUtils.isAllBlank(id)) {
            return Response.paramError("参数错误，修改id不能为空");
        }
        CompetitionType competitionType = competitionTypeService.getCompetitionTypeById(id);
        if (competitionType == null) {
            return Response.paramError("[" + id + "]" + "没有获取到数据请检查");
        }
        try {
            competitionTypeService.deleteCompetitionType(id);
        } catch (Exception e) {
            log.error("删除比赛类型错误,操作rollBack\n" + id);
            return Response.databaseError("删除比赛类型错误:[" + id + "]");
        }
        return Response.operateSucessNoData();
    }

    /**
     * @Description: 修改比赛类型
     * @param:
     * @return:
     * @date: 2018-7-30 9:40
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Response updateCompetitionBy(@RequestBody CompetitionType competitionType) {
        /*
         * 参数校验
         */
        if ((competitionType == null) || StringUtils.isAllBlank(competitionType.getId()) || StringUtils.isAllBlank(competitionType.getName())) {
            return Response.paramError("修改参数错误！");
        }

        CompetitionType oldCompetitionType = competitionTypeService.getCompetitionTypeById(competitionType.getId());

        if (oldCompetitionType == null) {
            return Response.paramError("没有获取到原始比赛类型数据，不能修改！");
        }

        /*
         * 调用service层更新接口
         */
        try {
            competitionTypeService.updateCompetitionType(competitionType);
        } catch (Exception e) {
            log.error("修改比赛类型错误！/n" + competitionType);

            return Response.databaseError("数据库修改比赛类型错误！");
        }

        return Response.operateSucessNoData();
    }

    /**
     * @Description: 通过id查询比赛类型
     * @param:
     * @return:
     * @date: 2018-7-30 9:43
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response getCompetitionById(@PathVariable String id) {
        if (StringUtils.isAllBlank(id)) {
            return Response.paramError("id 不能为空！");
        }

        CompetitionType competitionType = competitionTypeService.getCompetitionTypeById(id);

        if (competitionType == null) {
            return Response.operateSucessNoData();
        } else {
            return Response.operateSucessAndHaveData(competitionType);
        }
    }

    /**
     * @Description: 查询所有比赛类型
     * @param:
     * @return:
     * @date: 2018-7-30 9:44
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response getCompetitions() {
        List<CompetitionType> competitionTypes;

        try {
            competitionTypes = competitionTypeService.getCompetitionTypes();
        } catch (Exception e) {
            log.error("查询比赛类型错误！");

            return Response.databaseError("查询比赛类型错误！");
        }

        if (competitionTypes.size() == 0) {
            return Response.operateSucessNoData();
        } else {
            return Response.operateSucessAndHaveData(competitionTypes);
        }
    }

    @RequestMapping(value = "/{id}/competition", method = RequestMethod.GET)
    @ResponseBody
    public Response getCompetitionsByType(@PathVariable String id) {

        return null;
    }

}

