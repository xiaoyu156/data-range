package ac.iie.server.dao;

import ac.iie.server.domain.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataMapper {
    int deleteByPrimaryKey(String id);

    int insert(Data record);

    Data selectByPrimaryKey(String id);

    List<Data> selectAll();

    int updateByPrimaryKey(Data record);

    int batchInsertData(List<Data> dataList);
}