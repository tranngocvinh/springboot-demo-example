package com.example.crud_spring;

import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

     private CustomerService underTest ;
     @Mock
     private CustomerDao customerDao ;
    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDao) ;
    }

    @Test
    void getAllCustomers() {
        underTest.getAllCustomers() ;
        Mockito.verify(customerDao).selectAllCustomer() ;
    }

    @Test
    void getCustomer() {
        Customer customer = new Customer(1,"vinh","vinh@fpt.edu.vn",29);
        Mockito.when(customerDao.selectCustomerById(1)).thenReturn(Optional.of(customer)) ;
        Customer test = underTest.getCustomer(1) ;
        assertThat(test).isEqualTo(customer) ;
    }

    @Test
    void WillThrowWhenGetCustomerEmpty() {
        int id = 10 ;
        Mockito.when(customerDao.selectCustomerById(id)).thenReturn(Optional.empty()) ;

        assertThatThrownBy(() -> underTest.getCustomer(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("customer with id [%s] not found (Service: null; Status Code: 0; Error Code: null; Request ID: null; Proxy: null)".formatted(id));

    }


    @Test
    void addCustomer() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}