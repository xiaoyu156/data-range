package cn.ac.iie.permission_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PermissionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PermissionServerApplication.class, args);
	}
}
