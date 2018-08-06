package ac.iie.server.api.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemConfigTest {
    @Autowired
    SystemConfig systemConfig;

    @Test
    public void testConfig(){
        System.out.println(systemConfig.getBaseUrl());
    }

}