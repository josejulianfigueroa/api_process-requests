package cl.api.processrequests;

import cl.api.processrequests.config.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@RibbonClient(name = "jsonplaceholder.typicode.com")
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
@EnableMongoRepositories(basePackages = {"cl.api.processrequests.repository"})

public class ProcessRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessRequestApplication.class, args);
	}
	
}
