package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.domain.CompetitionType;
import ac.iie.server.service.CompetitionTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


public class CompetitionController extends BaseController {
    public CompetitionController(CompetitionTypeService competitionTypeService) {
        super(competitionTypeService);
    }





}
