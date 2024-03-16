package com.example.crud_spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CustomerJPADataAccessServiceTest {

    private CustomerJPADataAccessService underTest ;
    private AutoCloseable autoCloseable ;

    @Mock
    private CustomerRepository customerRepository ;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this) ;
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void selectAllCustomer() {
        underTest.selectAllCustomer() ;

        verify(customerRepository).findAll() ;
    }

    @Test
    void selectCustomerById() {
        int id = 1;

        underTest.selectCustomerById(id) ;
        verify(customerRepository).findById(id);
    }

    @Test
    void insertCustomer() {
    }

    @Test
    void existPersonWithEmail() {
    }

    @Test
    void existPersonWithId() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void update() {
    }
}