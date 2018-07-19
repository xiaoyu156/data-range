package ac.iie.server.dao;

import ac.iie.server.domain.Data;
import java.util.List;

public interface DataMapper {
    int deleteByPrimaryKey(String id);

    int insert(Data record);

    Data selectByPrimaryKey(String id);

    List<Data> selectAll();

    int updateByPrimaryKey(Data record);
}