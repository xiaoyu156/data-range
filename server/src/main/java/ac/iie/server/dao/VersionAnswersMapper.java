package ac.iie.server.dao;

import ac.iie.server.domain.VersionAnswers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface VersionAnswersMapper {
    int deleteByPrimaryKey(String id);

    int insert(VersionAnswers record);

    VersionAnswers selectByPrimaryKey(String id);

    List<VersionAnswers> selectAll();

    int updateByPrimaryKey(VersionAnswers record);
}