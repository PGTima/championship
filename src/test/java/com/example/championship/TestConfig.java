package com.example.championship;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.example.championship.jpaRepository"})
@EntityScan(basePackages = {"com.example.championship.model"})
public class TestConfig {
}
