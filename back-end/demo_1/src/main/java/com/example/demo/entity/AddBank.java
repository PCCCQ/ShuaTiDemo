package com.example.demo.entity;

import lombok.Data;

import java.util.List;
@Data
public class AddBank {
    private String bankid;
    private String bankname;
    private String banktime;
    private int tinum;
    private String uuid;
    private List timudata;
}
