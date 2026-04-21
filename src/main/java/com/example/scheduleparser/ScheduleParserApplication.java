package com.example.scheduleparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching       // включает поддержку @Cacheable
@EnableScheduling    // включает поддержку @Scheduled
public class ScheduleParserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleParserApplication.class, args);
    }
}
