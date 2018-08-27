package ac.iie.server.service;

import ac.iie.server.api.base.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestCluster {
    @Autowired
    ICloudService cloudService;

    @Test
    public void testClusterSummary(){
        String service = cloudService.cloudService("", Constant.CLOUD_CLUSTER_SUMMARY, Constant.GET_INTERFACE);
        log.info(service);
    }
}
