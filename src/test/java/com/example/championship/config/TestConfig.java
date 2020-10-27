package com.example.championship.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.example.championship.jpaRepository"})
@ComponentScan(basePackages = {"com.example.championship.service"})
@EntityScan(basePackages = {"com.example.championship.model"})
public class TestConfig {
}
