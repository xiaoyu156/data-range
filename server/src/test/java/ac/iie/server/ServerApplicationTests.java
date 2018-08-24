package ac.iie.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedisTemplate() {
        System.out.println(redisTemplate.opsForHash().get("fe34183ca5ae11e88a8c00ffe63e4089", 1));
    }


    @Test
    public void testCreateEva() {

    }

    @Test
    public void testGetEvaStatus() {

    }

    @Test
    public void testCreateDetection() {

    }

    @Test
    public void testQueryDetection() {

    }

    @Test
    public void testUploadResult() {

    }

    @Test
    public void dd() {
        String ss="J:\\work\\cloudtest\\zip_data\\testPro.zip";
        System.out.println(ss.substring(ss.lastIndexOf("\\")+1,ss.indexOf(".",-1)));
    }

}
