package com.trungtamjava.SpringProject.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungtamjava.SpringProject.model.CategoryDTO;
import com.trungtamjava.SpringProject.model.ProductDTO;
import com.trungtamjava.SpringProject.service.CategoryService;
import com.trungtamjava.SpringProject.service.ProductService;

@Controller
public class ClientWelcomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/welcome")
	public String welcomeClient(HttpSession session, HttpServletRequest request) {
		List<ProductDTO> limitProducts = productService.getLimitProduct(10);
		List<CategoryDTO> categories = categoryService.getAll();
		session.setAttribute("categories", categories);
		request.setAttribute("limitProducts", limitProducts);
		return "client/common/CommonBase";
	}
}
