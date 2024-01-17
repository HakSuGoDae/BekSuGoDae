package com.parkeunyoung.haksugodae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HaksugodaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaksugodaeApplication.class, args);
    }

}
