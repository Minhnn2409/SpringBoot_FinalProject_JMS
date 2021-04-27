package com.trungtamjava.SpringProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillProductDTO {
	private int id;
	private long unitPrice;
	private int quantity;
	private ProductDTO product;
	private BillDTO bill;
}
