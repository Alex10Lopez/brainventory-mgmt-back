package com.brainventory_mgmt.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BrainventoryConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainventoryConfigServerApplication.class, args);
	}

}
