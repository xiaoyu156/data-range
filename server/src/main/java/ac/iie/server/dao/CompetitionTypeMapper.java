package ac.iie.server.dao;

import ac.iie.server.domain.CompetitionType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CompetitionTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(CompetitionType record);

    CompetitionType selectByPrimaryKey(String id);

    List<CompetitionType> selectAll();

    int updateByPrimaryKey(CompetitionType record);


}