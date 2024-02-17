package com.example.crud_spring;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{
    //db
    private static final List<Customer> customers ;

    static {
        customers = new ArrayList<>() ;
        Customer alex = new Customer(1,"Alex","Alex@gmail.com",19) ;
        customers.add(alex) ;
    }
    @Override
    public List<Customer> selectAllCustomer() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream().filter(c -> c.getId().equals(id))
                .findFirst() ;

    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer) ;
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existPersonWithId(Integer id) {
        return false;
    }

    @Override
    public void deleteCustomer(Integer id) {

    }

    @Override
    public void update(Customer customer) {

    }
}
