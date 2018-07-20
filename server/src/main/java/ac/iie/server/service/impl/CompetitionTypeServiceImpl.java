package ac.iie.server.service.impl;

import ac.iie.server.dao.*;
import ac.iie.server.domain.CompetitionType;
import ac.iie.server.service.CompetitionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CompetitionTypeServiceImpl extends BaseService implements CompetitionTypeService {
    public CompetitionTypeServiceImpl(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper) {
        super(competitionMapper, competitionTypeMapper, dataMapper, userCompetitionMapper, versionAnswersMapper);
    }


    @Override
    public int insert(CompetitionType competitionType) {
        return competitionTypeMapper.insert(competitionType);
    }

    @Override
    public List<CompetitionType> getCompetitionTypes() {
        return competitionTypeMapper.selectAll();
    }

    @Override
    public int deleteCompetitionType(String id) {
        int deleteTypeNum;
        int deleteCompetitionNum;
        /*
        删除比赛类型
         */
        deleteTypeNum = competitionTypeMapper.deleteByPrimaryKey(id);
        /*
        逻辑修改比赛关联
         */
        deleteCompetitionNum = competitionMapper.updateCompetitionTypeByTypeId(id);

        return deleteTypeNum;
    }

    @Override
    public int updateCompetitionType(CompetitionType competitionType) {
        return competitionTypeMapper.updateByPrimaryKey(competitionType);
    }

    @Override
    public CompetitionType getCompetitionTypeById(String id) {
        return competitionTypeMapper.selectByPrimaryKey(id);
    }
}
