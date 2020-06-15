package com.kun.dataidentification;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DataIdentificationApplication {

    public static void main(String[] args) {

        List<Object> objects = new ArrayList<>();
        SpringApplication.run(DataIdentificationApplication.class, args);
    }

}
