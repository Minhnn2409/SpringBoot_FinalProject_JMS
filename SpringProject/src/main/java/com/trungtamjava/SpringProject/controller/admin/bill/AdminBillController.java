package com.trungtamjava.SpringProject.controller.admin.bill;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.BillDTO;
import com.trungtamjava.SpringProject.model.ProductDTO;
import com.trungtamjava.SpringProject.service.BillService;

@Controller
public class AdminBillController {
	@Autowired
	private BillService billService;

	@GetMapping(value = "/admin/bill/search")
	public String searchBill(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		keyword = keyword == null ? "" : keyword;
		page = page == null ? 1 : page;

		List<BillDTO> billDTOs = billService.search(keyword, page * 10, 0);

		request.setAttribute("bills", billDTOs);
		request.setAttribute("keyword", keyword);
		request.setAttribute("page", page);
		return "admin/bill/searchBill";
	}

	@GetMapping(value = "/admin/bill/delete")
	public String deleteBill(@RequestParam("id") int id) {
		BillDTO billDTO = billService.getById(id);

		if (billDTO != null) {
			billService.delete(billDTO);
		}
		return "redirect:/admin/bill/delete";
	}
}
