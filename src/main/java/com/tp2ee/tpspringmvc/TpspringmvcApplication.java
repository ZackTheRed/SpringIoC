package com.tp2ee.tpspringmvc;

import com.tp2ee.tpspringmvc.entities.Patient;
import com.tp2ee.tpspringmvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

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
