package com.home.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_admins")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 30)
	private String usernameAdmin;

	@Column(length = 30, nullable = false)
	private String passwordAdmin;
}
