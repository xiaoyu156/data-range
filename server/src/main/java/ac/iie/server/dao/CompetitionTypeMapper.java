package ac.iie.server.dao;

import ac.iie.server.domain.CompetitionType;
import java.util.List;

public interface CompetitionTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(CompetitionType record);

    CompetitionType selectByPrimaryKey(String id);

    List<CompetitionType> selectAll();

    int updateByPrimaryKey(CompetitionType record);
}