package ac.iie.server.dao;

import ac.iie.server.domain.UserCompetition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserCompetitionMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserCompetition record);

    UserCompetition selectByPrimaryKey(String id);

    List<UserCompetition> selectAll();

    int updateByPrimaryKey(UserCompetition record);

    List<UserCompetition> selectByUidAndComId(UserCompetition userCompetition);
}