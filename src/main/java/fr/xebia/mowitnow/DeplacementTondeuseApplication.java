package fr.xebia.mowitnow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DeplacementTondeuseApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DeplacementTondeuseApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DeplacementTondeuseApplication.class, args);
	}

}
