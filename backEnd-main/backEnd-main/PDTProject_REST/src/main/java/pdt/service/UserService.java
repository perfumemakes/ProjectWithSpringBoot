package pdt.service;

import java.util.List;

import pdt.entity.User;

public interface UserService {
	
	List<User> getUserList(User user);

	void insertUser(User user);

	User getUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

}
