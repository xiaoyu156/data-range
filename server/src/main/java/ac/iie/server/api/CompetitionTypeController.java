package ac.iie.server.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import ac.iie.common.utils.Response;
import ac.iie.server.domain.CompetitionType;
import ac.iie.server.service.CompetitionTypeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description
 *
 * @author Enter your name here...
 * @version Enter version here..., 18/07/20
 */
@RestController
@RequestMapping("competition-tyep")
@Slf4j
public class CompetitionTypeController extends BaseController {
    public CompetitionTypeController(CompetitionTypeService competitionTypeService) {
        super(competitionTypeService);
    }

    /**
     * Method description
     *
     * @param competitionType
     * @return
     */
    @RequestMapping(
            name = "/",
            method = RequestMethod.POST
    )
    @ResponseBody
    public Response addType(@RequestBody CompetitionType competitionType) {
        int addNum = 0;

        if (competitionType == null) {
            return Response.paramError("比赛类型错误！");
        } else {
            if (StringUtils.isAllBlank(competitionType.getName())) {
                return Response.paramError("比赛类型名称不能为空！");
            }

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
     * Method description
     *
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
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
            log.error("删除比赛类型错误\n" + id);

            return Response.databaseError("删除比赛类型错误:[" + id + "]");
        }

        return Response.operateSucessNoData();
    }

    /**
     * Method description
     *
     * @param competitionType
     * @return
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public Response updateCompetitionBy(@RequestBody CompetitionType competitionType) {

        /*
         * 参数校验
         */
        if ((competitionType == null)
                || StringUtils.isAllBlank(competitionType.getId())
                || StringUtils.isAllBlank(competitionType.getName())) {
            return Response.paramError("修改参数错误！");
        }

        CompetitionType oldCompetitionType = competitionTypeService.getCompetitionTypeById(competitionType.getId());

        if (oldCompetitionType == null) {
            return Response.paramError("没有获取到比赛类型数据，不能修改！");
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
     * Method description
     *
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
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
     * Method description
     *
     * @return
     */
    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET
    )
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
}

