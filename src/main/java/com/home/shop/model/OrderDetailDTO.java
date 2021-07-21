package com.home.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
	private int orderDetailId;
	private int orderId;
	private int productId;
	private int quantity;
	private double unitPrice;
}
