package com.trungtamjava.SpringProject.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
	private int id;
	private UserDTO buyer;
	private long priceTotal;
	private Date dateBuy;
	private List<BillProductDTO> billProductDTOs;
}
