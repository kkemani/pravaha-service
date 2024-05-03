package org.pravaha.bpmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BpmnApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpmnApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BpmnApplication.class);
	}
}
