package com.home.shop.model;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long userId;
	private String username;
	private String fullname;
	private String email;
	private String password;
	private String image;
	private MultipartFile imageFile;
	private String phoneNumber;
	private String address;
	private String registered;
	private short status;
	
	private Boolean isEdit;
}
