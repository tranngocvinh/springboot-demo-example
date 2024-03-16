package com.example.crud_spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
    @DataJpaTest
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    class CustomerRepositoryTest extends AbstractTestcontainers {

        @Autowired
        private CustomerRepository undertest ;





        @BeforeEach
        void setUp() {
            undertest.deleteAll();
        }

        @Test
        void existPersonWithEmail() {
            String email = FAKER.internet().emailAddress() ;
            Customer customer = new Customer(
                    FAKER.name().fullName(),
                    email,
                    FAKER.number().numberBetween(10,99)
            );

            undertest.save(customer) ;

            boolean check = undertest.existsCustomerByEmail(email) ;

            assertThat(check).isTrue() ;


        }

        @Test
        void existPersonWithId() {
            String email = FAKER.internet().emailAddress() ;
            Customer customer = new Customer(
                    FAKER.name().fullName(),
                    email,
                    FAKER.number().numberBetween(10,99)
            );

            undertest.save(customer) ;
            int id = undertest.findAll().stream().filter(c -> c.getEmail().equals(email)).map(Customer::getId).findFirst().orElseThrow() ;

            boolean check = undertest.existsCustomerById(id) ;

            assertThat(check).isTrue() ;
        }
    }