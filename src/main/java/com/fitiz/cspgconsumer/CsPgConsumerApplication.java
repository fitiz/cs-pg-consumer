package com.fitiz.cspgconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.fitiz.cspgconsumer"})
public class CsPgConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsPgConsumerApplication.class, args);
	}

}
