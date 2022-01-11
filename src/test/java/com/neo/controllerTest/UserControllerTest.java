package com.neo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.neo.controller.UserController;
import com.neo.entity.User;
import com.neo.entity.User.UserBuilder;
import com.neo.service.UserServiceI;
import com.neo.service.UserServiceImpl;

import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;
@RunWith(SpringRunner.class)
@WebMvcTest(value=UserController.class)
public class UserControllerTest {
	
	@MockBean
	private UserServiceI userService;
	@Autowired
	private MockMvc mockMvc;
	private User user;
	private List<User> userList;
	
	@BeforeEach
	void setUp() {
		 user=User.builder()
				//.id(1)
				.fName("Ram")
				.lName("katkar")
				.gender("Male")
				.email("sominath@gmail.com")
				.address("Aurangabad")
				.pincode(431151)
				.dob(LocalDate.of(20000, 10, 10))
				.doj(LocalDate.of(2021, 10, 15))
				.activeSw(true)
				.build();
	}
	@Test
	public void testSaveUser() throws Exception {
		User user1=User.builder()
		.fName("Ram")
		.lName("katkar")
		.gender("Male")
		.email("sominath@gmail.com")
		.address("Aurangabad")
		.pincode(431151)
		.dob(LocalDate.of(2000,10,10))
		.doj(LocalDate.of(2021,10,15))
		.activeSw(true)
		.build();
		List<User> userList=new ArrayList<>();
		userList.add(user1);
		
		Mockito.when(userService.saveUser(user1)).thenReturn(user);
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/saveUser")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content("{\n" +
	                	    "  \"fname\": \"Ram\",\n" +
	                        "  \"lname\": \"katkar\",\n" +
	                        "  \"gender\": \"Male\",\n" +
	                        "  \"email\": \"sominath@gmail.com\",\n" +
	                        "\"address\": \"Aurangabad\",\n"+
	                        "  \"pincode\": 431151,\n" +
	                        "  \"dob\": \"2000-10-10\",\n" +
	                        "  \"doj\": \"2021-10-15\",\n" +
	                        "  \"activeSw\": true\n" +
	            
	                        "        }")).andExpect(MockMvcResultMatchers.status().isCreated());
	}
	@Test
	public void testUpdat2User() throws Exception {
		User user2=User.builder()
		.id(1)
		.fName("Ram")
		.lName("katkar")
		.gender("Male")
		.email("sominath@gmail.com")
		.address("Aurangabad")
		.pincode(431151)
		.dob(LocalDate.of(2000, 10, 10))
		.doj(LocalDate.of(2021, 10, 15))
		.activeSw(true)
		.build();
		
		
		Mockito.when(userService.saveUser(user2)).thenReturn(user);
		
		 mockMvc.perform(MockMvcRequestBuilders.put("/editUser")
				 .contentType(MediaType.APPLICATION_JSON)
					
				 .content("{\n" +
	                	    "  \"fname\": \"Ram\",\n" +
	                        "  \"lname\": \"katkar\",\n" +
	                        "  \"gender\": \"Male\",\n" +
	                        "  \"email\": \"sominath@gmail.com\",\n" +
	                        "\"address\": \"Aurangabad\",\n"+
	                        "  \"pincode\": 431151,\n" +
	                        "  \"dob\": \"2000-10-10\",\n" +
	                        "  \"doj\": \"2021-10-15\",\n" +
	                        "  \"activeSw\": true\n" +
	            
	                        "        }"))
					 
				 .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	
	@Test
	public void testGetAllUsers() throws Exception {
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getAllUsers")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void testGetByName() throws Exception {
		Mockito.when(userService.findUserByFName("Ram")).thenReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getUserByName/Ram")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetByLastName() throws Exception {
		Mockito.when(userService.findUserByLName("katkar")).thenReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getUserByLName/katkar")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void testGetByPincode() throws Exception {
		Mockito.when(userService.findUserByPincode(431151)).thenReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getUserByPincode/431151")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void testSoftDelete() throws Exception {
		Mockito.when(userService.deleteUserById(1)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteSoft/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void testHardDelete() throws Exception {
		Mockito.when(userService.deleteUser(1)).thenReturn(userList);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteHard/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
