package com.example.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){
		return args -> {
			Customer a = new Customer("Vinh","vinh@gmail.com",21) ;
			Customer b = new Customer("Huong","Huong@gmail.com",21) ;
			List<Customer> list = List.of(a,b) ;
			customerRepository.saveAll(list) ;

		};
	}

}
