package ac.iie.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:redis.config.properties")
@Component
@Data
public class RedisConfigProp {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.pass}")
    private String pass;
    @Value("${redis.database}")
    private int database;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.maxActive}")
    private int maxActive;
    @Value("${redis.maxWait}")
    private int maxWait;
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;
}
