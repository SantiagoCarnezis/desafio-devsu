package com.scarnezis.challenge_user.dto;

import com.scarnezis.challenge_user.feignCliente.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class GetCustomer {

    private String id;
    private String name;
    private boolean gender;
    private int age;
    private String direction;
    private String phone;
    private boolean state;
    private List<Account> accounts;

    public GetCustomer(String id, String name, boolean gender, int age, String direction, String phone, boolean state) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.direction = direction;
        this.phone = phone;
        this.state = state;
        this.accounts = new ArrayList<>();
    }
}
