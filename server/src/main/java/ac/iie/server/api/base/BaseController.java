package ac.iie.server.api.base;

import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.service.CompetitionService;
import ac.iie.server.service.UserCompetitionService;
import ac.iie.server.service.VersionAnswersService;
import org.springframework.beans.factory.annotation.Autowired;

import ac.iie.server.service.CompetitionTypeService;

public abstract class BaseController<T> {
    public CompetitionTypeService competitionTypeService;
    public CompetitionService competitionService;
    public CompetitionVFier competitionVFier;
    public UserCompetitionService userCompetitionService;
    public VersionAnswersService versionAnswersService;

    @Autowired
    public BaseController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, CompetitionVFier competitionVFier, UserCompetitionService userCompetitionService, VersionAnswersService versionAnswersService) {
        this.competitionTypeService = competitionTypeService;
        this.competitionService = competitionService;
        this.competitionVFier = competitionVFier;
        this.userCompetitionService = userCompetitionService;
        this.versionAnswersService = versionAnswersService;
    }
}

