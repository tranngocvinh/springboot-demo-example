package com.example.crud_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    public final CustomerService customerService ;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId){
       return customerService.getCustomer(customerId) ;

    }

    @PostMapping
    public void reigsterCustomer( @RequestBody CustomerRegistrationRequest request){
        customerService.addCustomer(request) ;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        customerService.delete(id) ;

    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Integer id,@RequestBody CustomerUpdateRequest customerUpdateRequest){
        customerService.update(id,customerUpdateRequest);
    }
}
