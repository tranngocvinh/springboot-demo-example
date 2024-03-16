package com.example.crud_spring;

import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.sun.jdi.request.DuplicateRequestException;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerDao customerDao ;

    public CustomerService(@Qualifier("jdbc") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomer() ;
    }

    public Customer getCustomer(Integer id){
        return customerDao.selectCustomerById(id)
               .orElseThrow(() -> new ResourceNotFoundException("customer with id [%s] not found".formatted(id))) ;
    }
    //add
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        if(customerDao.existPersonWithEmail(customerRegistrationRequest.email())){
            System.out.println("email exist");
        }
        customerDao.insertCustomer(new Customer(customerRegistrationRequest.name(), customerRegistrationRequest.email(), customerRegistrationRequest.age()));
    }


    public void delete(Integer id) {
        if(!customerDao.existPersonWithId(id)){
            System.out.println("not exist id");
        }
        customerDao.deleteCustomer(id) ;
    }


    public void update(Integer id, CustomerUpdateRequest customerUpdateRequest) {
        if(!customerDao.existPersonWithId(id)){
            System.out.println();
        }
        Customer customer = getCustomer(id) ;
        boolean changes = false ;
        if(customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(customer.getName())){
            customer.setName(customerUpdateRequest.name());
            changes = true ;
        }


        if(customerUpdateRequest.age() != null && !customerUpdateRequest.age().equals(customer.getAge())){
            customer.setAge(customerUpdateRequest.age());
            changes = true ;
        }

        if(customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(customer.getEmail())){
            if(customerDao.existPersonWithEmail(customerUpdateRequest.email())){
                throw new DuplicateRequestException("email exit");
            }
            customer.setEmail(customerUpdateRequest.email());
            changes = true ;
        }

        if(!changes){
            throw new RuntimeException() ;
        }
        customerDao.update(customer) ;

    }
}
