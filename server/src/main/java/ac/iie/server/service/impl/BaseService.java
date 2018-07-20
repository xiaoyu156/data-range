package ac.iie.server.service.impl;

import ac.iie.server.dao.*;
import org.apache.ibatis.annotations.AutomapConstructor;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    CompetitionMapper competitionMapper;
    CompetitionTypeMapper competitionTypeMapper;
    DataMapper dataMapper;
    UserCompetitionMapper userCompetitionMapper;
    VersionAnswersMapper versionAnswersMapper;

    @Autowired
    public BaseService(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper) {
        this.competitionMapper = competitionMapper;
        this.competitionTypeMapper = competitionTypeMapper;
        this.dataMapper = dataMapper;
        this.userCompetitionMapper = userCompetitionMapper;
        this.versionAnswersMapper = versionAnswersMapper;
    }
}
