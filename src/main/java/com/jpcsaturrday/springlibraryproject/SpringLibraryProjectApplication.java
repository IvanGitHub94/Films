package com.jpcsaturrday.springlibraryproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLibraryProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringLibraryProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Swagger path: http://localhost:8081/swagger-ui/index.html#/");
    }
}
