package com.chan.microservice.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ChanConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChanConfigserverApplication.class, args);
	}

}
