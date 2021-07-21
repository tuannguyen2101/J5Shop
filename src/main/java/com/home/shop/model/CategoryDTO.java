package com.home.shop.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long CategoryId;
	@NotEmpty
	@Length(min = 1)
	private String name;
	
	private Boolean isEdit = false;
}
