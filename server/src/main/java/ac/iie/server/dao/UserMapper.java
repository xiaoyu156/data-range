package ac.iie.server.dao;

import ac.iie.server.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    User selectUserCompetitions(String userId);

    int updateByPrimaryKey(User record);
}