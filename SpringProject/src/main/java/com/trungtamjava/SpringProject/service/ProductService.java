package com.trungtamjava.SpringProject.service;

import java.util.List;

import com.trungtamjava.SpringProject.model.ProductDTO;

public interface ProductService {
	void add(ProductDTO product);

	void update(ProductDTO product);

	void delete(int id);

	List<ProductDTO> getAllProducts();

	ProductDTO getProductById(int id);

	List<ProductDTO> getProductByName(String name);

	List<ProductDTO> getByCategory(int categoryId);

	List<ProductDTO> getLimitProduct(int numProduct);

	int count(String name);

	List<ProductDTO> search(String name, int maxPage, int offset);
}
