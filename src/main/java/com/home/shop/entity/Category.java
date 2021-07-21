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
@Table(name = "categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CategoryId;

	@Column(name = "category_name", length = 100, columnDefinition = "varchar(255) not null")
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // delete category -> delete product where
																	// product.categoryid = category.categoryid
	private Set<Product> products;
}
