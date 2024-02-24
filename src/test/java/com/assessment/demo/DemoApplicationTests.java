package com.assessment.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestConfiguration(proxyBeanMethods = false)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests{

	public static void main(String[] args) {
		SpringApplication.from(DemoApplication::main).with(DemoApplication.class).run(args);
	}
}
