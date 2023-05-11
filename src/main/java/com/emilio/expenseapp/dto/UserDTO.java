package com.emilio.expenseapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private String full_name;
    private String email;
    private String password;
}
