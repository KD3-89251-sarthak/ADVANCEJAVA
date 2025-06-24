package com.sunbeam.pojos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data



public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date birth;
	private boolean status;
	private String role;

}
