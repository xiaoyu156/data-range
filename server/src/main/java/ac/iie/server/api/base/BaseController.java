package ac.iie.server.api.base;

import ac.iie.server.api.verifier.CompetitionVFier;
import org.springframework.beans.factory.annotation.Autowired;

import ac.iie.server.service.CompetitionTypeService;

public abstract class BaseController<T> {
    public CompetitionTypeService competitionTypeService;
    public CompetitionVFier competitionVFier;

    @Autowired
    public BaseController(CompetitionTypeService competitionTypeService, CompetitionVFier competitionVFier) {
        this.competitionTypeService = competitionTypeService;
    }

}

