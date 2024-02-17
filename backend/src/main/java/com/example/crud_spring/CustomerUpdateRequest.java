package com.example.crud_spring;

public record CustomerUpdateRequest(
        String name ,
          String email,
          Integer age
){
}
