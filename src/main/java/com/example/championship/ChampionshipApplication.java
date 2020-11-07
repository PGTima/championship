package com.example.championship;

import com.example.championship.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfig.class})
public class ChampionshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChampionshipApplication.class, args);
	}

}
