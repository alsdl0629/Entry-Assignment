package com.example.knowledgeboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ConfigurationPropertiesScan
@SpringBootApplication
public class KnowledgeBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeBoardApplication.class, args);
    }

}
