package com.trungtamjava.SpringProject.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private int id;
	private String name;
	private String userName;
	private String password;
	private int age;
	private String email;
	private String phoneNum;
	private String address;
	private String role;
	private String gender;
	private String imageAvatar;
}
