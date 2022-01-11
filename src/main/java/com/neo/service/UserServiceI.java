package com.neo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.neo.entity.User;
import com.neo.exception.UserNotFoundException;



public interface UserServiceI {
	
	public User saveUser(User user);
	public User  updateUser(User user);
	public List<User> getAllUsers();
	public Optional<User> findById(Integer id)   ;
	public List<User> findUserByFName(String fname);
	public List<User> findUserByLName(String lName);
	public List<User> findUserByPincode(Integer pincode);
	public List<User> deleteUser(Integer id);
	public boolean deleteUserById(Integer id);
	public List<User> findData(User user, String type);

}
