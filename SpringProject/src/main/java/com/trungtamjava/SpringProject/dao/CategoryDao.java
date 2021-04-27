package com.trungtamjava.SpringProject.dao;

import java.util.List;

import com.trungtamjava.SpringProject.entity.Category;


public interface CategoryDao {
	void add(Category category);

	void delete(Category category);

	void update(Category updateCategory);

	Category getById(int Id);

	List<Category> getByName(String name);

	List<Category> getAll();

	List<Category> search(String name, int offset, int maxPerPage);

	int count(String name);
}
