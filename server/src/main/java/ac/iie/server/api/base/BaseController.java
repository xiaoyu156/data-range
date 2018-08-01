package ac.iie.server.api.base;

import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;

import ac.iie.server.service.CompetitionTypeService;

public abstract class BaseController<T> {
    public CompetitionTypeService competitionTypeService;
    public CompetitionService competitionService;
    public CompetitionVFier competitionVFier;

    @Autowired
    public BaseController(CompetitionTypeService competitionTypeService, CompetitionService competitionService,
                          CompetitionVFier competitionVFier) {
        this.competitionTypeService = competitionTypeService;
        this.competitionService = competitionService;
        this.competitionVFier = competitionVFier;
    }
}

