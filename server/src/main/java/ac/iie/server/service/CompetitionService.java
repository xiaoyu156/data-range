package ac.iie.server.service;

import ac.iie.server.domain.Competition;
import ac.iie.server.domain.CompetitionType;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface CompetitionService {
    PageInfo<Competition> getCompetitions(Map<String, Object> condition, int pageSize, int pageNum);

    int insert(Competition competition);

    Competition getCompetitionById(String id);

    @Transactional(rollbackFor = Exception.class)
    void createCompetition(Competition competition, List<String> users);

    int updateCompetition(Competition competition);
}
