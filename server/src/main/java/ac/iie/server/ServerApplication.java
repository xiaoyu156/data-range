package ac.iie.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: 项目启动入口
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-20 14:50
 * @version: 1.0.0
 */
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class ServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServerApplication.class, args);
    }
}
