package com.ssafy.ssafit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing	// 날짜 자동 생성해줌.
@SpringBootApplication
public class SsafitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsafitApplication.class, args);
	}

}
