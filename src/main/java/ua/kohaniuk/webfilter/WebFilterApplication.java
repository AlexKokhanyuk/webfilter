package ua.kohaniuk.webfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebFilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFilterApplication.class, args);
	}

}
