package com.trungtamjava.SpringProject.dao;

import java.util.List;

import com.trungtamjava.SpringProject.entity.BillProduct;

public interface BillProductDao {
	void create(BillProduct billProduct);

	void update(BillProduct billProduct);

	void delete(BillProduct billProduct);

	BillProduct getById(int id);

	List<BillProduct> getByBill(int billId);

	int count(String name);

	List<BillProduct> search(String name, int maxPage, int offset);

	double getCoupon(String name);
}
