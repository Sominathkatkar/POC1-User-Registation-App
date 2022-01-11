package com.neo.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Serializable>{
	
	public List<User> findAll();
	public List<User> findByfName(String fname);
	public List<User> findBylName(String lName);
	public List<User> findByPincode(Integer pincode);
	public List<User> findByActiveSw(String activeSw);
}
