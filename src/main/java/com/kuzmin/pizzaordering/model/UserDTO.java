package com.kuzmin.pizzaordering.model;

import lombok.*;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String fullname;
    private String phoneNumber;
}
