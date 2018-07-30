package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.domain.Competition;
import ac.iie.server.service.CompetitionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("competition")
@Slf4j
public class CompetitionController extends BaseController<Competition> {


    public CompetitionController(CompetitionTypeService competitionTypeService, CompetitionVFier competitionVFier) {
        super(competitionTypeService, competitionVFier);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response addCompetition(@RequestBody Competition competition) {
        /*
        数据校验
         */
        return null;
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
        return null;
    }


}
