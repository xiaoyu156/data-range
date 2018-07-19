package ac.iie.server.dao;

import ac.iie.server.domain.VersionAnswers;
import java.util.List;

public interface VersionAnswersMapper {
    int deleteByPrimaryKey(String id);

    int insert(VersionAnswers record);

    VersionAnswers selectByPrimaryKey(String id);

    List<VersionAnswers> selectAll();

    int updateByPrimaryKey(VersionAnswers record);
}