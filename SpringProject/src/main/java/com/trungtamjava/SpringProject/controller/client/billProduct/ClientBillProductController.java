package com.trungtamjava.SpringProject.controller.client.billProduct;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.BillProductDTO;
import com.trungtamjava.SpringProject.service.BillProductService;
import com.trungtamjava.SpringProject.service.BillService;

@Controller
public class ClientBillProductController {

	@Autowired
	private BillProductService billProductService;

	@Autowired
	private BillService billService;

	@GetMapping(value = "/client/billProduct/search")
	public String searchBillProduct(@RequestParam("id") int id, HttpServletRequest req) {
		List<BillProductDTO> billProducts = billProductService.getByBill(id);
		req.setAttribute("billProducts", billProducts);

		return "client/SearchBillProducts/CommonBase";
	}

}
