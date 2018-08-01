package ac.iie.server.api;

import ac.iie.common.utils.Response;
import ac.iie.server.api.base.BaseController;
import ac.iie.server.api.verifier.CompetitionVFier;
import ac.iie.server.domain.Competition;
import ac.iie.server.service.CompetitionService;
import ac.iie.server.service.CompetitionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 数据集操作接口
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-1 8:27
 * @version: 1.0.0
 */
@RestController
@RequestMapping("data")
@Slf4j
public class DataController extends BaseController<Competition> {


    public DataController(CompetitionTypeService competitionTypeService, CompetitionService competitionService, CompetitionVFier competitionVFier) {
        super(competitionTypeService, competitionService, competitionVFier);
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Response dataUpload(@RequestParam("fileName") MultipartFile file) {

        return null;
    }


}
