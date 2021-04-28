package com.trungtamjava.SpringProject.controller.client.category;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.ProductDTO;
import com.trungtamjava.SpringProject.service.ProductService;

@Controller
public class ClientCategoryController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/product/get")
	public String getByCategory() {
		return "client/SearchProduct/CommonBase";
	}

	@PostMapping(value = "/product/get")
	public String getByCategory(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "categoryId", required = false) int categoryId, HttpServletRequest req) {
		if (keyword != null && categoryId != 0) {
			List<ProductDTO> products = new ArrayList<ProductDTO>();

			List<ProductDTO> productsDao = productService.getByCategory(categoryId);
			for (ProductDTO product : productsDao) {
				if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
					products.add(product);
				}
			}
			req.setAttribute("getProducts", products);

		} else if (categoryId != 0 && keyword == null) {
			List<ProductDTO> products = productService.getByCategory(categoryId);
			req.setAttribute("getProducts", products);

		} else if (keyword != null && categoryId == 0) {
			List<ProductDTO> products = productService.getProductByName(keyword);
			req.setAttribute("getProducts", products);

		}
		return "client/SearchProduct/CommonBase";
	}
}
