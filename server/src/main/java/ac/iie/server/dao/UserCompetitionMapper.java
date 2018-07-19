package ac.iie.server.dao;

import ac.iie.server.domain.UserCompetition;
import java.util.List;

public interface UserCompetitionMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserCompetition record);

    UserCompetition selectByPrimaryKey(String id);

    List<UserCompetition> selectAll();

    int updateByPrimaryKey(UserCompetition record);
}