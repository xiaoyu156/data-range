package ac.iie.server.service.impl;

import ac.iie.common.utils.CompressUtil;
import ac.iie.server.config.SystemConfig;
import ac.iie.server.dao.*;
import ac.iie.server.domain.Competition;
import ac.iie.server.domain.Data;
import ac.iie.server.service.DataService;
import ac.iie.server.service.ICloudService;
import ac.iie.server.service.IProcService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.exception.ZipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 数据集处理
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-7 14:20
 * @version: 1.0.0
 */
@Slf4j
@Service(value = "dataService")
public class DataServiceImpl extends BaseService implements DataService {



    public DataServiceImpl(CompetitionMapper competitionMapper, CompetitionTypeMapper competitionTypeMapper, DataMapper dataMapper, UserCompetitionMapper userCompetitionMapper, VersionAnswersMapper versionAnswersMapper, SystemConfig systemConfig, RedisTemplate redisTemplate) {
        super(competitionMapper, competitionTypeMapper, dataMapper, userCompetitionMapper, versionAnswersMapper, systemConfig, redisTemplate);
    }

    /**
     * @Description: 批量处理
     * @param:
     * @return:
     * @date: 2018-8-9 14:42
     */
    @Override
    public int batchInsert(List<Data> datas) {
        int dealCount = datas.size();
        int defauteCount = 500;
        if (dealCount <= defauteCount) {
            dataMapper.batchInsertData(datas);
        } else {
            int start;
            int endIndex = 0;
            int loop = (int) Math.ceil((dealCount * 1.0 / defauteCount));
            for (int i = 1; i <= loop; i++) {
                if (i == 1) {
                    start = 0;
                    endIndex = defauteCount;
                } else if (i == loop) {
                    start = endIndex;
                    endIndex = dealCount;
                } else {
                    start = endIndex;
                    endIndex = defauteCount * i;
                }
                List<Data> tem = datas.subList(start, endIndex);
                dataMapper.batchInsertData(tem);
            }
        }
        return dealCount;
    }
}
