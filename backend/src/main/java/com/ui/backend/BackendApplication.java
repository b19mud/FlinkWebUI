package com.ui.backend;

import com.ui.backend.generator.FlinkJobGenerator;
import com.ui.backend.generator.JobXMLReader;
import com.ui.backend.kafkademo.KafkaTestProducer;
import com.ui.backend.pojo.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackendApplication.class, args);
        Job job = new JobXMLReader().readJobFromXML();
        KafkaTestProducer.initProducer();
        FlinkJobGenerator flinkJobGenerator = new FlinkJobGenerator();
        flinkJobGenerator.generate(job);
        System.out.println(job);
    }

}
