package com.example.crud_spring;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){
		return args -> {
			var faker = new Faker() ;
			Random random = new Random() ;
			Customer a = new Customer(faker.name().fullName(),faker.internet().emailAddress(), random.nextInt(15,99)) ;
			Customer b = new Customer(faker.name().fullName(),faker.internet().emailAddress(), random.nextInt(15,99)) ;
			List<Customer> list = List.of(a,b) ;
			//customerRepository.saveAll(list) ;

		};
	}

}
