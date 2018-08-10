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
	public void testRedisTemplate(){
		Map<Integer,Object> map=new HashMap<>();
		map.put(1,"sds");
		map.put(2,"dfdfdfd");
		redisTemplate.opsForHash().putAll("cccc",map);
	    System.out.println(redisTemplate.opsForHash().get("cccc",1));

	}

}
