package com.example.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TasktrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasktrackerApplication.class, args);
    }

}
