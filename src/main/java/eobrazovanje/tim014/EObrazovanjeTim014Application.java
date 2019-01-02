package eobrazovanje.tim014;

import eobrazovanje.tim014.propery.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class EObrazovanjeTim014Application {



	public static void main(String[] args) {
		SpringApplication.run(EObrazovanjeTim014Application.class, args);
	}
}
