package com.trungtamjava.SpringProject.service;

import java.util.List;

import com.trungtamjava.SpringProject.model.UserDTO;

public interface UserService {
	void add(UserDTO user);

	void update(UserDTO user);

	void delete(int id);

	List<UserDTO> getAllUsers();

	UserDTO getById(int id);

	UserDTO getByUserName(String userName);

	List<UserDTO> getUserByName(String name);

	List<UserDTO> search(String keyword, int maxPerPage, int offset);

	int count(String name);
}
