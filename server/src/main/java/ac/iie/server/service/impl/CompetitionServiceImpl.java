package ac.iie.server.service.impl;

import ac.iie.server.config.SystemConfig;
import ac.iie.server.dao.*;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.UserCompetition;
import ac.iie.server.service.CompetitionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 比赛实现
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-1 8:13
 * @version: 1.0.0
 */
@Slf4j
@Service
public class CompetitionServiceImpl extends BaseService implements CompetitionService {


    public CompetitionServiceImpl(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper, SystemConfig systemConfig, RedisTemplate redisTemplate) {
        super(competitionMapper, competitionTypeMapper, dataMapper, userCompetitionMapper, versionAnswersMapper, systemConfig, redisTemplate);
    }

    @Override
    public PageInfo<Competition> getCompetitions(Map<String, Object> condition, int pageSize, int pageNum) {
        return PageHelper.startPage(pageSize, pageNum)
                .doSelectPageInfo(() -> competitionMapper.selectAll());
    }

    @Override
    public int insert(Competition competition) {
        return competitionMapper.insert(competition);
    }

    @Override
    public Competition getCompetitionById(String id) {
        return competitionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createCompetition(Competition competition, List<String> users) {
        /*
        新增比赛
         */
        competitionMapper.insert(competition);
        /*
        创建关联关系
         */
        if (StringUtils.isBlank(competition.getId())) {
            users.forEach(user -> {
                UserCompetition userCompetition = new UserCompetition();
                userCompetition.setCompetitionId(competition.getId());
                userCompetition.setStatus(0);
                userCompetition.setType(0);
            });
        }
    }

    @Override
    public int updateCompetition(Competition competition) {
        return competitionMapper.updateByPrimaryKey(competition);
    }


}
