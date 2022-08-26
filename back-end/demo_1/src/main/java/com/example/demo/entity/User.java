package com.example.demo.entity;

import lombok.Data;

@Data   //自动生成getter和setter
public class User {
    public String phone;
    public String password;
    private String uuid;
}
