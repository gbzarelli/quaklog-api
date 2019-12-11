package br.com.luizalabs.quaklog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class QuaklogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuaklogApiApplication.class, args);
    }

}
