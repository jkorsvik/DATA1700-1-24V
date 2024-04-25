package com.example.oblig1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.repository.CrudRepository;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
@RestController
public class Oblig1Application {

	public static void main(String[] args) {
		SpringApplication.run(Oblig1Application.class, args);
	}

}
