package com.trungtamjava.SpringProject.controller.admin.billProduct;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.BillProductDTO;
import com.trungtamjava.SpringProject.service.BillProductService;

@Controller
public class AdminBillProductController {
	@Autowired
	private BillProductService billProductService;

	@GetMapping(value = "/admin/billProduct/search")
	public String searchBillProduct(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		keyword = keyword == null ? "" : keyword;
		page = page == null ? 1 : page;

		List<BillProductDTO> billProductDTOs = billProductService.search(keyword, page*10, 0);

		request.setAttribute("billProducts", billProductDTOs);
		request.setAttribute("keyword", keyword);
		request.setAttribute("page", page);
		return "admin/billProduct/searchBill";
	}

	@GetMapping(value = "/admin/billProduct/delete")
	public String deleteBillProduct(@RequestParam("id") int id) {
		billProductService.delete(id);
		return "redirect:/admin/billProduct/delete";
	}
}
