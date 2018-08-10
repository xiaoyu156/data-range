package ac.iie.server.service;

import ac.iie.server.domain.Data;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-7 14:15
 * @version: 1.0.0
 */
public interface DataService {
    int batchInsert(List<Data> dataList);
}
