package ac.iie.permission_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PermissionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PermissionManagerApplication.class, args);
	}
}
