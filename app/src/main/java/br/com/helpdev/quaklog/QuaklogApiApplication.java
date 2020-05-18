package br.com.helpdev.quaklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableCaching
public class QuaklogApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(QuaklogApiApplication.class, args);
    }
}