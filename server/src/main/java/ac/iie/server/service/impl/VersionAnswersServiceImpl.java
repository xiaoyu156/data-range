package ac.iie.server.service.impl;

import ac.iie.server.config.SystemConfig;
import ac.iie.server.dao.*;
import ac.iie.server.domain.VersionAnswers;
import ac.iie.server.service.VersionAnswersService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 用户答案
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-13 15:51
 * @version: 1.0.0
 */
@Service(value = "versionAnswersService")
public class VersionAnswersServiceImpl extends BaseService implements VersionAnswersService {


    public VersionAnswersServiceImpl(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper, SystemConfig systemConfig, RedisTemplate redisTemplate) {
        super(competitionMapper, competitionTypeMapper, dataMapper, userCompetitionMapper, versionAnswersMapper, systemConfig, redisTemplate);
    }

    /**
     * @Description: 用户答案提交
     * @param:
     * @return:
     * @date: 2018-8-13 15:57
     */
    @Override
    public boolean commitAnswer(VersionAnswers versionAnswers) {
        int num;
        try {
            num = this.versionAnswersMapper.insert(versionAnswers);
        } catch (Exception e) {
            return false;
        }
        return num > 0;
    }

    @Override
    public List<VersionAnswers> getUserAnswerByUserCompId(String userCompId) {
        return versionAnswersMapper.selectAllByUserComp(userCompId);
    }

    @Override
    public int updateScore(VersionAnswers versionAnswers) {
        return versionAnswersMapper.updateScore(versionAnswers);
    }

}
