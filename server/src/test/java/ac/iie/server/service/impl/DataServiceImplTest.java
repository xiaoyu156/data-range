package ac.iie.server.service.impl;

import ac.iie.server.domain.Data;
import ac.iie.server.service.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataServiceImplTest {

    @Autowired
    DataService dataService;

    @Test
    public void batchInsert() {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            Data data = new Data();
            data.setMediaUrls("url" + i);
            data.setDataIndex(i);
            data.setType(0);
            data.setCompId("ccc");
            dataList.add(data);
        }
        dataService.batchInsert(dataList);
    }
}