package ac.iie.server.service.impl;

import ac.iie.server.config.SystemConfig;
import ac.iie.server.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description:
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-14 16:54
 * @version: 1.0.0
 */
@Slf4j
public class BaseService {
    CompetitionMapper competitionMapper;
    CompetitionTypeMapper competitionTypeMapper;
    DataMapper dataMapper;
    UserCompetitionMapper userCompetitionMapper;
    VersionAnswersMapper versionAnswersMapper;
    SystemConfig systemConfig;
    RedisTemplate redisTemplate;

    @Autowired
    public BaseService(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper, SystemConfig systemConfig, RedisTemplate redisTemplate) {
        this.competitionMapper = competitionMapper;
        this.competitionTypeMapper = competitionTypeMapper;
        this.dataMapper = dataMapper;
        this.userCompetitionMapper = userCompetitionMapper;
        this.versionAnswersMapper = versionAnswersMapper;
        this.systemConfig = systemConfig;
        this.redisTemplate=redisTemplate;
    }

}
