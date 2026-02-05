package com.example.demo.data;

import lombok.Data;

@Data
public class UsersData {
	
	private Integer id;
	private Integer storesId;
	private Integer rolesId;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String password;
	private String phone;

}