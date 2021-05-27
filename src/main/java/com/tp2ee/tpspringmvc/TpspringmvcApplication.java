package com.tp2ee.tpspringmvc;

import com.tp2ee.tpspringmvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpspringmvcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TpspringmvcApplication.class, args);
    }

    @Autowired
    private PatientRepository pr;

    @Override
    public void run(String... args) throws Exception {
        pr.findAll().forEach(p -> {
            p.toString();
        });
    }
}
