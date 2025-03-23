package com.brainventory_mgmt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BrainventoryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainventoryGatewayApplication.class, args);
	}

}
