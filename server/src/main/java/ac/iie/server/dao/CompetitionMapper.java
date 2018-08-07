package ac.iie.server.dao;

import ac.iie.server.domain.Competition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompetitionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Competition record);

    Competition selectByPrimaryKey(String id);

    List<Competition> selectAll();

    int updateByPrimaryKey(Competition record);

    int updateCompetitionTypeByTypeId(String id);

    List<Competition> getCompetitonByStatus(int status);
}