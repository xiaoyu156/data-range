package ac.iie.server.service;

import ac.iie.server.domain.CompetitionType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompetitionTypeService {
    int insert(CompetitionType competitionType);

    List<CompetitionType> getCompetitionTypes();

    @Transactional(rollbackFor = Exception.class)
    void deleteCompetitionType(String id);

    int updateCompetitionType(CompetitionType competitionType);

    CompetitionType getCompetitionTypeById(String id);
}
