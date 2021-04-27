package com.trungtamjava.SpringProject.dao;

import java.util.List;

import com.trungtamjava.SpringProject.entity.Product;

public interface ProductDao {
	void add(Product product);

	void update(Product product);

	void delete(Product product);

	List<Product> getAllProducts();

	Product getProductById(int id);

	List<Product> getProductByName(String name);

	List<Product> getByCategory(int categoryId);

	List<Product> getLimitProduct(int numProduct);

	int count(String name);

	List<Product> search(String name, int maxPage, int offset);
}
