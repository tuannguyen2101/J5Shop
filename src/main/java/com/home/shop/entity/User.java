package com.home.shop.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(columnDefinition = "varchar(100) not null")
	private String username;
	
	@Column(columnDefinition = "varchar(100) not null")
	private String fullname;
	
	@Column(columnDefinition = "varchar(100) not null")
	private String email;
	
	@Column(length = 20, nullable = false)
	private String password;
	
	@Column(length = 255)
	private String image;
	
	@Column(length = 20, nullable = false)
	private String phoneNumber;
	
	@Column(length = 255, nullable = false)
	private String address;	
	
	@Column(length = 255, nullable = false)
	private String registered;
	
	@Column(nullable = false)
	private short status;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // delete user -> delete order where order.userId = userId
	private Set<Order> orders;

}
