package com.trungtamjava.SpringProject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.SpringProject.dao.BillDao;
import com.trungtamjava.SpringProject.entity.Bill;
import com.trungtamjava.SpringProject.entity.BillProduct;
import com.trungtamjava.SpringProject.model.BillDTO;
import com.trungtamjava.SpringProject.model.BillProductDTO;
import com.trungtamjava.SpringProject.service.BillService;

@Service
@Transactional
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private BillProductServiceImpl billProductServiceImpl;

	@Override
	public void create(BillDTO bill) {
		billDao.create(null);
	}

	@Override
	public void update(BillDTO bill) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public BillDTO getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillDTO> getByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BillDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BillDTO> search(String keyword, int maxPerPage, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	public Bill convertToEntity(BillDTO billDTO) {
		Bill bill = new Bill();
		bill.setId(billDTO.getId());
		bill.setPriceTotal(billDTO.getPriceTotal());
		bill.setBuyer(userServiceImpl.convertToEntity(billDTO.getBuyer()));
		bill.setDateBuy(billDTO.getDateBuy());
		bill.setBillProducts(convertToBillProductEntityList(billDTO.getBillProductDTOs()));
		return bill;
	}

	public BillDTO convertToDTO(Bill bill) {
		BillDTO billDTO = new BillDTO();
		billDTO.setId(bill.getId());
		billDTO.setPriceTotal(bill.getPriceTotal());
		billDTO.setBuyer(userServiceImpl.convertToDTO(bill.getBuyer()));
		billDTO.setDateBuy(bill.getDateBuy());
		billDTO.setBillProductDTOs(convertToBillProductDTOList(bill.getBillProducts()));
		return billDTO;
	}

	private List<BillProductDTO> convertToBillProductDTOList(List<BillProduct> billProducts) {
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();
		for (BillProduct billProduct : billProducts) {
			billProductDTOs.add(billProductServiceImpl.convertToDTO(billProduct));
		}
		return billProductDTOs;
	}
	
	private List<BillProduct> convertToBillProductEntityList(List<BillProductDTO> billProductDTOs) {
		List<BillProduct> billProducts = new ArrayList<BillProduct>();
		for (BillProductDTO billProductDTO : billProductDTOs) {
			billProducts.add(billProductServiceImpl.convertToEntity(billProductDTO));
		}
		return billProducts;
	}

}
