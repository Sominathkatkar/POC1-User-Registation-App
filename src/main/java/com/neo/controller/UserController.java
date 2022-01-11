package com.neo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neo.entity.User;
import com.neo.exception.UserNotFoundException;
import com.neo.repository.UserRepository;
import com.neo.service.UserServiceI;
import com.neo.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceI userService;

	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User saveUser = userService.saveUser(user);
		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}

	@GetMapping("/getUserByName/{fname}")
	public ResponseEntity<List<User>> getUserByFname(@PathVariable String fname) {
		List<User> findUserByFName = userService.findUserByFName(fname);
		return ResponseEntity.status(HttpStatus.OK).body(findUserByFName);
		
	}
	
	//search by first name, last name and pincode 
	@GetMapping("/searchUser")
	public ResponseEntity<List<User>> searchUser(@RequestBody User user, @RequestParam String type ) {
		
		List<User> findData = userService.findData(user,type);
		
		
	return ResponseEntity.status(HttpStatus.OK).body(findData);
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<Optional<User>> getById(@PathVariable Integer id)  {
		Optional<User> findById = userService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(findById);
	}

	@GetMapping("/getUserByLName/{lName}")
	public ResponseEntity<List<User>> getByLname(@PathVariable String lName)   {
		List<User> byLName = userService.findUserByLName(lName);
		
		return ResponseEntity.status(HttpStatus.OK).body(byLName);
	}

	@GetMapping("/getUserByPincode/{pincode}")
	public ResponseEntity<List<User>> getByPincode(@PathVariable Integer pincode) {
	
		List<User> byPincode = userService.findUserByPincode(pincode);
		
		return ResponseEntity.status(HttpStatus.OK).body(byPincode);
		
	}

	@PutMapping(value = "/editUser")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User saveUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);

	}

	// hard delete
	@DeleteMapping("/deleteHard/{id}")
	public ResponseEntity<List<User>> deleteUser(@PathVariable Integer id) {
		List<User> userList = userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	// soft delete
	@DeleteMapping("/deleteSoft/{id}")
	public ResponseEntity<Boolean> softDeleteUser(@PathVariable Integer id) {
		boolean deleteUserById = userService.deleteUserById(id);
		return ResponseEntity.ok(deleteUserById);

	}

}
