package ac.iie.server.service.impl;

import ac.iie.server.dao.*;
import ac.iie.server.domain.CompetitionType;
import ac.iie.server.service.CompetitionTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
//        PageInfo<CompetitionType> page = PageHelper.startPage(1, 1).doSelectPageInfo(() -> competitionTypeMapper.selectAll());
//        System.out.println(page.getPageSize());
//        System.out.println(page);
        return competitionTypeMapper.selectAll();
    }

    @Override
    public void deleteCompetitionType(String id) {
        int deleteCompetitionNum;
        /*
        删除比赛类型
         */
        competitionTypeMapper.deleteByPrimaryKey(id);
        /*
        逻辑修改比赛关联
         */
        deleteCompetitionNum = competitionMapper.updateCompetitionTypeByTypeId(id);
        log.info("删除比赛类型id：" + id + ",修改比赛:" + deleteCompetitionNum);
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
