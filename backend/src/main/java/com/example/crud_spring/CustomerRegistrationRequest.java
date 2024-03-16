package com.example.crud_spring;

public record CustomerRegistrationRequest (
    String name ,
    String email,
    Integer age
    ){

}
