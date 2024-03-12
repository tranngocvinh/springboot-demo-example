package com.example.crud_spring;

import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class CustomerJDBCDataAccessServiceTest extends AbstractTestcontainers {
    private CustomerJDBCDataAccessService underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper() ;

    @BeforeEach
    void setUp(){
        underTest = new CustomerJDBCDataAccessService(
                getJdbcTemplate(),
                customerRowMapper
        );
    }

    @Test
    void selectAllCustomer() {

        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().emailAddress(),
                20
        );
        underTest.insertCustomer(customer);
        List<Customer> customers = underTest.selectAllCustomer() ;

        assertThat(customers).isNotEmpty() ;

    }

    @Test
    void selectCustomerById() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                 20
                );

        underTest.insertCustomer(customer);

        int id = underTest.selectAllCustomer()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        // When
        Optional<Customer> actual = underTest.selectCustomerById(id);

        // Then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void willEmptyWhenSelectCustomerById(){
        int id = -1 ;
        var actual = underTest.selectCustomerById(id) ;
        assertThat(actual).isEmpty() ;

    }
    @Test
    void insertCustomer() {

    }

    @Test
    void existPersonWithEmail(){
        String name = FAKER.name().fullName();
        String email = FAKER.internet().emailAddress() ;
        Customer customer = new Customer(
                name,
                email,
                FAKER.number().numberBetween(10,99));
        underTest.insertCustomer(customer);

        boolean flag = underTest.existPersonWithEmail(email);

        assertThat(flag).isTrue();

    }

    @Test
    void existPersonWithId() {
        String name = FAKER.name().fullName() ;
        String email = FAKER.internet().emailAddress() ;
        Customer customer = new Customer(name,email,20) ;
        underTest.insertCustomer(customer);
        int id = underTest.selectAllCustomer().stream().filter(c -> c.getEmail().equals(email))
                .map(Customer::getId).findFirst().orElseThrow();
        boolean flag = underTest.existPersonWithId(id);

        assertThat(flag).isTrue() ;

    }

    @Test
    void deleteCustomer() {
        String name = FAKER.name().fullName();
        String email = FAKER.internet().emailAddress();
        Customer customer = new Customer(name,email,20) ;
        underTest.insertCustomer(customer);

        int id = underTest.selectAllCustomer()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow() ;
         underTest.deleteCustomer(id);

         Optional<Customer> a = underTest.selectCustomerById(id) ;
         assertThat(a).isNotPresent() ;


    }

    @Test
    void updateByName() {
        String name = FAKER.name().fullName();
        String email = FAKER.internet().emailAddress();
        Customer customer = new Customer(name,email,20) ;
        underTest.insertCustomer(customer);

        int id = underTest.selectAllCustomer()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow() ;
        var newName = "truongvoky" ;
        Customer update = new Customer() ;
        update.setName(newName) ;
        update.setId(id);
        underTest.update(update);
        Optional<Customer> a = underTest.selectCustomerById(id) ;
        assertThat(a).isPresent().hasValueSatisfying(c ->{
                    assertThat(c.getId()).isEqualTo(id) ;
                    assertThat(c.getName()).isEqualTo(newName) ;
                    assertThat(c.getEmail()).isEqualTo(email);
                    assertThat(c.getAge()).isEqualTo(20) ;

                }) ;

    }
}
