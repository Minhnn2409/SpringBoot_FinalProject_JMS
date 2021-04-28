package com.trungtamjava.SpringProject.controller.client.cart;

import java.util.HashMap;
import java.util.Map;

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
}
