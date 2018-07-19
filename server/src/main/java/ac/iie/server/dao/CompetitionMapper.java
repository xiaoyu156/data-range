package ac.iie.server.dao;

import ac.iie.server.domain.Competition;
import java.util.List;

public interface CompetitionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Competition record);

    Competition selectByPrimaryKey(String id);

    List<Competition> selectAll();

    int updateByPrimaryKey(Competition record);
}