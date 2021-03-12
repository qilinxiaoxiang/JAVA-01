package com.wsbo.distributedtransactionhmily1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableEurekaClient
@MapperScan("com.wsbo.distributedtransactionhmily1.dal.mapper")
@EnableTransactionManagement
public class DistributedTransactionHmily1Application {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTransactionHmily1Application.class, args);
	}

}
