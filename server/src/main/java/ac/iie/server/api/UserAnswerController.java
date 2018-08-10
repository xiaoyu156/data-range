package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.domain.Competition;
import ac.iie.server.service.CompetitionService;
import ac.iie.server.service.CompetitionTypeService;
import ac.iie.server.service.UserCompetitionService;
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

    public UserAnswerController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, CompetitionVFier competitionVFier, UserCompetitionService userCompetitionService) {
        super(competitionTypeService, competitionService, competitionVFier, userCompetitionService);
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
        return null;
    }

}
