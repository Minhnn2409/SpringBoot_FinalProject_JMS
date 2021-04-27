package com.trungtamjava.SpringProject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.SpringProject.dao.UserDao;
import com.trungtamjava.SpringProject.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(User user) {
		entityManager.persist(user);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void delete(User user) {
		entityManager.remove(user);
	}

	@Override
	public List<User> getAllUsers() {
		String jql = "SELECT * from user";
		Query query = entityManager.createQuery(jql, User.class);
		return query.getResultList();
	}

	@Override
	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User getByUserName(String userName) {
		String jql = "SELECT user FROM user WHERE user.username LIKE :username";
		return entityManager.createQuery(jql, User.class).setParameter("username", "%" + userName + "%")
				.getSingleResult();
	}

	@Override
	public List<User> getUserByName(String name) {
		String jql = "SELECT user from user WHERE user.name LIKE :name";
		return entityManager.createQuery(jql, User.class).setParameter("name", "%" + name + "%").getResultList();
	}

	@Override
	public List<User> search(String keyword, int maxPerPage, int offset) {
		String jql = "SELECT user FROM user WHERE name LIKE :keyword";

		return entityManager.createQuery(jql, User.class).setParameter("keyword", "%" + keyword + "%")
				.setFirstResult(offset).setMaxResults(maxPerPage).getResultList();
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

}
