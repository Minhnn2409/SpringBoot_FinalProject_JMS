package com.trungtamjava.SpringProject.controller.client.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.SpringProject.model.ProductDTO;
import com.trungtamjava.SpringProject.service.ProductService;

@Controller
public class ClientDetailProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/product/detail")
	public String detailProduct(@RequestParam("id") int id, HttpServletRequest request) {
		ProductDTO productDTO = productService.getProductById(id);
		request.setAttribute("product", productDTO);

		List<ProductDTO> moreProducts = productService.getLimitProduct(4);
		request.setAttribute("moreProducts", moreProducts);

		int minPrice = productDTO.getPrice() - 5000000;
		int maxPrice = productDTO.getPrice() + 5000000;
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
		List<ProductDTO> allProducts = productService.getAllProducts();
		for (ProductDTO productItem : allProducts) {
			if (productItem.getPrice() >= minPrice && productItem.getPrice() <= maxPrice) {
				relatedProducts.add(productItem);
			}
		}
		request.setAttribute("relatedProducts", relatedProducts);

		return "client/DetailProduct/CommonBase";
	}
}
