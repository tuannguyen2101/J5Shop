package com.home.shop.model;

import java.util.Date;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	private Long orderId;

	private Date orderDate;

	private int userId;

	private String address;

	private double amount;

	private short status;
}
