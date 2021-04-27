package com.trungtamjava.SpringProject.model;

import java.util.Locale.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private int id;
	private String name;
	private String description;
	private int price;
	private String image;
	private CategoryDTO category;
}
