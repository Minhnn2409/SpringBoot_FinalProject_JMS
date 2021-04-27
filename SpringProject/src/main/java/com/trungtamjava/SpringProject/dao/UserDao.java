package com.trungtamjava.SpringProject.dao;

import java.util.List;

import com.trungtamjava.SpringProject.entity.User;

public interface UserDao {
	void add(User user);

	void update(User user);

	void delete(User user);

	List<User> getAllUsers();

	User getById(int id);

	User getByUserName(String userName);

	List<User> getUserByName(String name);

	List<User> search(String keyword, int maxPerPage, int offset);

	int count(String name);
}
