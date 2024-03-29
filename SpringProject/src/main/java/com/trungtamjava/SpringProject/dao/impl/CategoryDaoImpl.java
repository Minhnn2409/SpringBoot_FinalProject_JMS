package com.trungtamjava.SpringProject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.SpringProject.dao.CategoryDao;
import com.trungtamjava.SpringProject.entity.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Category category) {
		entityManager.persist(category);
	}

	@Override
	public void delete(Category category) {
		entityManager.remove(category);
	}

	@Override
	public void update(Category updateCategory) {
		entityManager.merge(updateCategory);
	}

	@Override
	public Category getById(int Id) {
		return entityManager.find(Category.class, Id);
	}

	@Override
	public List<Category> getByName(String name) {
		String jql = "SELECT category FROM category WHERE category.name LIKE :name";
		return entityManager.createQuery(jql, Category.class).setParameter("name", "%" + name + "%").getResultList();
	}

	@Override
	public List<Category> getAll() {
		String jql = "SELECT category WHERE category";
		return entityManager.createQuery(jql, Category.class).getResultList();
	}

	@Override
	public List<Category> search(String name, int offset, int maxPerPage) {
		String jql = "SELECT category FROM category WHERE category.name LIKE :name";
		return entityManager.createQuery(jql, Category.class).setParameter("name", "%" + name + "%")
				.setFirstResult(offset).setMaxResults(maxPerPage).getResultList();
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

}
