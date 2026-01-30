package com.example.demo.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersApiData {

	private Integer id;
    private String firstName;
    private String lastName;
    private String email;

}
