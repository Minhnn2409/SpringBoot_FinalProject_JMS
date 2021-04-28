package com.trungtamjava.SpringProject.service;

import java.util.List;

import com.trungtamjava.SpringProject.model.BillProductDTO;

public interface BillProductService {
	void create(BillProductDTO billProduct);

	void update(BillProductDTO billProduct);

	void delete(BillProductDTO billProductDTO);

	BillProductDTO getById(int id);

	List<BillProductDTO> getByBill(int billId);

	int count(String name);

	List<BillProductDTO> search(String name, int maxPage, int offset);

	double getCoupon(String name);
}
