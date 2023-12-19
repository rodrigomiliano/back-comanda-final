package comanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"comanda"}) 
public class BackComandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackComandaApplication.class, args);
	}

}
