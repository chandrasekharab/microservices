package com.chan.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ChanEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChanEurekaApplication.class, args);
	}

}
