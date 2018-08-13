package ac.iie.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 系统配置
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-6 9:41
 * @version: 1.0.0
 */
@Component(value = "systemConfig")
@ConfigurationProperties(prefix = "yaml")
@Data
public class SystemConfig {

    private String baseUrl;
    private String logoUrl;
    private String dataUrl;
    private String programUrl;
    private String zipDataUrl;
    private Map<String, String> cloudService;
}
