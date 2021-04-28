package com.trungtamjava.SpringProject.controller.client.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.BillProductDTO;
import com.trungtamjava.SpringProject.model.ProductDTO;
import com.trungtamjava.SpringProject.service.ProductService;

@Controller
public class ClientAddCartController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/client/cart/search")
	public String getCart(HttpSession httpSession) {
		List<ProductDTO> moreProducts = productService.getLimitProduct(2);
		httpSession.setAttribute("moreProducts", moreProducts);

		Object object = httpSession.getAttribute("cart");

		double sum = 0;
		if (object != null) {
			Map<Integer, BillProductDTO> billProductMap = (Map<Integer, BillProductDTO>) object;
			for (Map.Entry<Integer, BillProductDTO> entry : billProductMap.entrySet()) {
				sum += (entry.getValue().getQuantity() * entry.getValue().getProduct().getPrice());
			}
			httpSession.setAttribute("sumOfBillProduct", sum);
		}
		return "client/cart/CommonBase";
	}

	@GetMapping(value = "/client/cart/add")
	public String addCart(@RequestParam(value = "id", required = false) int id, HttpSession httpSession) {
		ProductDTO product = productService.getProductById(id);

		Object object = httpSession.getAttribute("cart");

		if (object == null) {
			BillProductDTO billProduct = new BillProductDTO();
			billProduct.setProduct(product);
			billProduct.setQuantity(1);
			billProduct.setUnitPrice(product.getPrice());

			Map<Integer, BillProductDTO> map = new HashMap<Integer, BillProductDTO>();
			map.put(id, billProduct);
			httpSession.setAttribute("cart", map);
		} else {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;
			BillProductDTO billProduct = map.get(id);

			if (billProduct == null) {
				billProduct = new BillProductDTO();
				billProduct.setProduct(product);
				billProduct.setQuantity(1);
				billProduct.setUnitPrice(product.getPrice());

				map.put(id, billProduct);
			} else {
				billProduct.setQuantity(billProduct.getQuantity() + 1);
			}
			httpSession.setAttribute("cart", map);
		}
		return "redirect:/client/cart/search";
	}

	@GetMapping(value = "/client/cart/delete")
	public String deleteCart(@RequestParam("id") int id, HttpServletRequest req, HttpSession httpSession) {
		Object object = httpSession.getAttribute("cart");
		if (object != null) {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;
			map.remove(id);
			httpSession.setAttribute("cart", map);
		}
		return "redirect:/client/cart/search";
	}
}
