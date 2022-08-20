package com.ui.backend;

import com.ui.backend.generator.JobXMLReader;
import com.ui.backend.pojo.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        Job job = new JobXMLReader().readJobFromXML();
        System.out.println(job);
    }

}
