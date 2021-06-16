package com.jvo.biglossalertsmonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableMongoRepositories
@EnableMongoAuditing
@SpringBootApplication
public class BigLossAlertsMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigLossAlertsMonitoringApplication.class, args);
    }

}
