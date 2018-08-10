package ac.iie.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: java类作用描述
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-8 16:07
 * @version: 1.0.0
 */
@Configuration
@Slf4j
public class RedisConfiguration {

    @Autowired
    private RedisConfigProp redisConfigProp;

    /**
     * @Description:Jedis连接池
     * @param:
     * @return:
     * @date: 2018-8-8 16:15
     */
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置连接池相关的属性
        jedisPoolConfig.setMaxIdle(redisConfigProp.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisConfigProp.getMaxTotal());
        return jedisPoolConfig;
    }

    /**
     * @Description: Jedis连接工厂
     * @param:
     * @return:
     * @date: 2018-8-8 16:13
     */
    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
        factory.setDatabase(redisConfigProp.getDatabase());
        factory.setHostName(redisConfigProp.getHost());
        factory.setPort(redisConfigProp.getPort());
        return factory;
    }

    /**
     * @Description: 封装redisTemplate
     * @param:
     * @return:
     * @date: 2018-8-8 16:12
     */
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        //设置键的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置值得序列化
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        log.info("=======================RedisTemplate Init Success=================");
        return redisTemplate;
    }

}
