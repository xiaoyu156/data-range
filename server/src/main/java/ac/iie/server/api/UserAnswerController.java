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

@RestController
@RequestMapping("competition")
@Slf4j
public class UserAnswerController extends BaseController<Competition> {


    public UserAnswerController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, CompetitionVFier competitionVFier, UserCompetitionService userCompetitionService) {
        super(competitionTypeService, competitionService, competitionVFier, userCompetitionService);
    }
}
