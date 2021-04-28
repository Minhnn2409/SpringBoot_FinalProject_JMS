package com.trungtamjava.SpringProject.controller.client.bill;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.BillDTO;
import com.trungtamjava.SpringProject.model.BillProductDTO;
import com.trungtamjava.SpringProject.model.UserDTO;
import com.trungtamjava.SpringProject.service.BillProductService;
import com.trungtamjava.SpringProject.service.BillService;

@Controller
public class ClientBillController {

	@Autowired
	private BillService billService;

	@Autowired
	private BillProductService billProductService;

	@GetMapping(value = "/client/bill/add")
	public String addBill(HttpSession httpSession, @RequestParam("address_order") String newAddress,
			HttpServletRequest req) {
		Object object = httpSession.getAttribute("cart");
		UserDTO order_user = (UserDTO) httpSession.getAttribute("loginUser");
		order_user.setAddress(newAddress);

		if (object != null) {
			BillDTO bill = new BillDTO();
			Date utilStartDate = new Date();
			java.sql.Date orderDate = new java.sql.Date(utilStartDate.getTime());
			bill.setDateBuy(orderDate);
			bill.setBuyer(order_user);

			double total = (double) httpSession.getAttribute("sumOfBillProduct");
			bill.setPriceTotal((long) total);
			billService.create(bill);
			bill = billService.getLastestBill();

			List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();

			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;
			for (Entry<Integer, BillProductDTO> entry : map.entrySet()) {
				BillProductDTO billProduct = entry.getValue();
				billProduct.setBill(bill);
				billProductDTOs.add(billProduct);
				billProductService.create(billProduct);
			}

			bill.setBillProductDTOs(billProductDTOs);
			billService.update(bill);
			httpSession.removeAttribute("cart");
			httpSession.removeAttribute("sumOfBillProduct");
			return "redirect:/client/bill/success";
		} else {
			return "redirect:/product/search";
		}
	}

	@PostMapping(value = "/client/bill/add")
	public String addBill() {
		return "redirect:/client/bill/add";
	}

	@GetMapping(value = "/client/bill/search")
	public String searchBill(HttpSession session, HttpServletRequest req,
			@RequestParam(value = "page", required = false) Integer page) {
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		page = page == null ? 1 : page;
		List<BillDTO> bills = billService.search(user.getName(), page, 0);
		session.setAttribute("bills", bills);
//		req.setAttribute("pageNo", page);
//		req.setAttribute("maxPage", page*10);
		req.setAttribute("page", page);

		return "client/SearchBill/CommonBase";
	}

	@GetMapping(value = "/client/bill/delete")
	public String deleteBill(@RequestParam("id") int id) {
		List<BillProductDTO> billProducts = billProductService.getByBill(id);
		for (BillProductDTO billProduct : billProducts) {
			billProductService.delete(billProduct);
		}
		return "redirect:/client/bill/delete";
	}

	@GetMapping(value = "/client/order/search")
	public String searchOrder(HttpSession session, HttpServletRequest req) {
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		Map<Integer, BillProductDTO> cart = (Map<Integer, BillProductDTO>) session.getAttribute("cart");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime orderDate = now.plusDays(3);
		String timeOrder = dtf.format(orderDate);

		double sum = (double) session.getAttribute("sumOfBillProduct");

		req.setAttribute("clientOrder", user);
		req.setAttribute("timeOrder", timeOrder);
		req.setAttribute("cart", cart);
		req.setAttribute("sumOfBillProducts", sum);
		return "client/AddBill/CommonBase";
	}

	@GetMapping(value = "/client/bill/success")
	public String getSuccessBill() {
		return "client/DoneBill/CommonBase";
	}

}
