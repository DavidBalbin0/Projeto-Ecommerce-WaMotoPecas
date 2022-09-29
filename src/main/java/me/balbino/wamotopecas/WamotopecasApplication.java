package me.balbino.wamotopecas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.web.config.EnableSpringDataWebSupport;


@SpringBootApplication
@EnableSpringDataWebSupport
@EnableFeignClients
public class WamotopecasApplication {

	public static void main(String[] args) {
		SpringApplication.run(WamotopecasApplication.class, args);
	}

}

