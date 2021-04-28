package com.trungtamjava.SpringProject.service;

import java.util.List;

import com.trungtamjava.SpringProject.model.BillDTO;

public interface BillService {
	void create(BillDTO bill);

	void update(BillDTO bill);

	void delete(BillDTO billDTO);

	BillDTO getById(int id);

	List<BillDTO> getByUser(int userId);

	List<BillDTO> getAll();

	int count(String name);

	List<BillDTO> search(String keyword, int maxPerPage, int offset);

	BillDTO getLastestBill();

}
