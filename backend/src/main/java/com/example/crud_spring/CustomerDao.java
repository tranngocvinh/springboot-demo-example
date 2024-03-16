package com.example.crud_spring;

import java.util.List;
import java.util.Optional;


public interface CustomerDao {
    List<Customer> selectAllCustomer() ;
    Optional<Customer> selectCustomerById(Integer id) ;
    void insertCustomer(Customer customer) ;
    boolean existPersonWithEmail(String email) ;
    boolean existPersonWithId(Integer id) ;


    void deleteCustomer(Integer id);

    void update(Customer customer);
}
