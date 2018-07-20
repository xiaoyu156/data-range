package ac.iie.server.api;

import ac.iie.server.service.CompetitionTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {
    CompetitionTypeService competitionTypeService;

    @Autowired
    public BaseController(CompetitionTypeService competitionTypeService) {
        this.competitionTypeService = competitionTypeService;
    }
}
