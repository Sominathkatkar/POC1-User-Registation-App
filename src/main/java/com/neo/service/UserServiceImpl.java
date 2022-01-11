package com.neo.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.entity.User;
import com.neo.exception.UserNotFoundException;
import com.neo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository repo;

	@Override
	public User saveUser(User user) {
		user.setActiveSw(true);
		User save = repo.save(user);
		return save;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = repo.findAll();
		List<User> sortedList1 = list.stream().sorted(Comparator.comparing(User::getDob).reversed())
		.collect(Collectors.toList());
		
		List<User> sortedList2 = sortedList1.stream().sorted(Comparator.comparing(User::getDoj).reversed())
		.collect(Collectors.toList());
		return sortedList2;
	}
	
	@Override
	public Optional<User> findById(Integer id)   {
		Optional<User> user = repo.findById(id);
		if(user.isPresent()) {
			return user;
		}
		else {
			throw   new UserNotFoundException("Not Found");
		}
		
		
	}

	@Override
	public List<User> findUserByLName(String lName) {
		List<User> findByLName = repo.findBylName(lName);
		
		
			if(findByLName.isEmpty()){
		
            throw new UserNotFoundException("Not Found");
		}
	
		
		return findByLName;
	}

	@Override
	public List<User> findUserByPincode(Integer pincode)  {
		List<User> findByPincode = repo.findByPincode(pincode);
	
		if(findByPincode.isEmpty()){
            throw new UserNotFoundException("Not Found");
		}
		
		return findByPincode;
	}
	
	@Override
	public User updateUser(User user) {
		User user1 = repo.save(user);
		return user1;

	}

	@Override
	public List<User> findUserByFName(String fname) throws UserNotFoundException {
		List<User> userList = repo.findByfName(fname);
		
//		if(userList.isEmpty()){
//            throw new UserNotFoundException("Not Found");
//		}
		 
		return userList;
	}

	@Override
	public List<User> deleteUser(Integer id) {
		User user = repo.findById(id).get();
		repo.delete(user);
		List<User> list = repo.findAll();
		return list;
	}

		@Override
	public boolean deleteUserById(Integer id) {
		try {
			User user = repo.findById(id).get();
			user.setActiveSw(false);
			repo.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

		@Override
		public List<User> findData(User user, String type) {

			System.out.println("Inside FindData");
			if(type.equals("pincode")) {
				List<User> findByPincode = repo.findByPincode(user.getPincode());
				if(findByPincode.isEmpty()) {
					throw new UserNotFoundException("Not Found");
				}
				return findByPincode;
			}
			else if(type.equals("fname")) {
				List<User> findByfname= repo.findByfName(user.getFName());
				if(findByfname.isEmpty()) {
					throw new UserNotFoundException("Not Found");
				}
				return findByfname;
			}
			else if(type.equals("lname")) {
				List<User> findBylname = repo.findBylName(user.getLName());
				if(findBylname.isEmpty()) {
					throw new UserNotFoundException("Not Found");
				}

				return findBylname;
			}
			else {
				throw new UserNotFoundException("type Not Found");
				
			}
			
			//return null;
		}

	

}
