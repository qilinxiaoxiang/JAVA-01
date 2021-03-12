package com.wsbo.distributedtransactionhmily2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.wsbo.distributedtransactionhmily2.dal.mapper")
public class DistributedTransactionHmily2Application {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTransactionHmily2Application.class, args);
	}

}
