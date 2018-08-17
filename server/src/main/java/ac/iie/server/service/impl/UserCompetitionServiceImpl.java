package ac.iie.server.service.impl;

import ac.iie.server.config.SystemConfig;
import ac.iie.server.dao.*;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.UserCompetition;
import ac.iie.server.service.CompetitionService;
import ac.iie.server.service.UserCompetitionService;
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
public class UserCompetitionServiceImpl extends BaseService implements UserCompetitionService {


    public UserCompetitionServiceImpl(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper, SystemConfig systemConfig, RedisTemplate redisTemplate) {
        super(competitionMapper, competitionTypeMapper, dataMapper, userCompetitionMapper, versionAnswersMapper, systemConfig, redisTemplate);
    }

    @Override
    public boolean joinCompetition(UserCompetition userCompetition) {
        boolean flag = false;

        int num = userCompetitionMapper.insert(userCompetition);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean isJoined(UserCompetition userCompetition) {
        return userCompetitionMapper.selectByUidAndComId(userCompetition).size() != 0;
    }

    @Override
    public List<UserCompetition> selectByUid(UserCompetition userCompetition) {
        return userCompetitionMapper.selectByUid(userCompetition);
    }
}
