package com.trungtamjava.SpringProject.controller.admin.product;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.ProductDTO;
import com.trungtamjava.SpringProject.service.ProductService;

@Controller
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/admin/product/search")
	public String searchProduct(HttpServletRequest request,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		keyword = keyword == null ? "" : keyword;
		page = page == null ? 1 : page;

		List<ProductDTO> productDTOs = productService.search(keyword, page * 10, 0);

		request.setAttribute("products", productDTOs);
		request.setAttribute("keyword", keyword);
		request.setAttribute("page", page);
		return "admin/product/searchProduct";
	}

	@GetMapping(value = "/admin/product/detail")
	public String detailProduct(HttpServletRequest request, @RequestParam("id") int id) {
		ProductDTO productDTO = productService.getProductById(id);
		request.setAttribute("product", productDTO);
		return "admin/product/detail";
	}

	@GetMapping(value = "/admin/product/add")
	public String addProduct() {
		return "admin/product/add";
	}

	@PostMapping(value = "/admin/product/add")
	public String addProduct(@RequestAttribute("product") ProductDTO productDTO) {
		productService.add(productDTO);
		return "redirect:/admin/product/search";
	}

	@GetMapping(value = "/admin/product/update")
	public String updateProduct() {
		return "admin/product/update";
	}

	@PostMapping(value = "/admin/product/update")
	public String addProduct(@RequestParam("id") int id) {
		ProductDTO productDTO = productService.getProductById(id);
		productService.update(productDTO);
		return "redirect:/admin/product/search";
	}

	@GetMapping(value = "/admin/product/delete")
	public String deleteProduct(@RequestParam("id") int id) {
		productService.delete(id);
		return "redirect:/admin/product/delete";
	}

	@GetMapping(value = "/product/download")
	public void downloadProduct(@RequestAttribute("image") String image, HttpServletResponse resp) throws IOException {
		final String UPLOAD_FOLDER = "D:\\Tech\\JMS\\SpringBoot_Final_Project\\SpringBoot_FinalProject_JMS\\SpringProject\\images\\products";

		File file = new File(UPLOAD_FOLDER + File.separator + image);
		if (file.exists()) {
			FileUtils.copyFile(file, resp.getOutputStream());
		}
	}
}
