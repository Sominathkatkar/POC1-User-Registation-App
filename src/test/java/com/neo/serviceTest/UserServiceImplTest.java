package com.neo.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.neo.entity.User;
import com.neo.exception.UserNotFoundException;
import com.neo.repository.UserRepository;
import com.neo.service.UserServiceI;
import com.neo.service.UserServiceImpl;

@SpringBootTest
public class UserServiceImplTest {
	@MockBean
	private UserRepository repository;

	@Autowired
	private UserServiceI userServiceI;

	@BeforeEach
	void setUp() throws UserNotFoundException {
		User user = User.builder().id(1).fName("Ram").lName("Khurana").gender("Male").email("yogesh@gmail.com")
				.address("Mumbai").pincode(123456).dob(LocalDate.of(1990, 01, 01)).doj(LocalDate.of(2021, 01, 01))
				.activeSw(true).build();

		List<User> userList = new ArrayList<>();
		userList.add(user);

		Mockito.when(userServiceI.getAllUsers()).thenReturn(userList);
		Mockito.when(userServiceI.findUserByFName("Ram")).thenReturn(userList);

	}

	@Test
	public void testSaveUser() {
		User user3 = User.builder().id(1).fName("Ram").lName("Katkar").gender("Male").email("yogesh@gmail.com")
				.address("Mumbai").pincode(123456).dob(LocalDate.of(1990, 01, 01)).doj(LocalDate.of(2021, 01, 01))
				.activeSw(true).build();
		Mockito.when(userServiceI.saveUser(user3)).thenReturn(user3);
		User save = repository.save(user3);
		assertNotNull(save);
		assertEquals(user3.getFName(), save.getFName());
	}

	@Test
	public void testGetAllUser() {
		List<User> list = repository.findAll();
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByFirstName() {
		List<User> list = repository.findByfName("Ram");
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByLastName() {
		List<User> list = repository.findBylName("Khurana");
		assertEquals(1, list.size() + 1);
	}

	@Test
	public void testFindByPincode() {
		List<User> list = repository.findByPincode(123456);
		assertEquals(1, list.size() + 1);
	}

	@Test
	public void testUserNotFoundException() throws UserNotFoundException {
		mock(UserServiceI.class);
		Mockito.when(repository.findByfName(Mockito.anyString())).thenThrow(UserNotFoundException.class);
		Mockito.when(repository.findBylName(Mockito.anyString())).thenThrow(UserNotFoundException.class);
	}

	/*
	 * @Test public void testSoftDelete() { boolean flag; Optional<User> user
	 * =repository.findById(1); user.get().setActiveSw(false);
	 * mock(UserServiceI.class);
	 * Mockito.when(userServiceI.deleteUserById(Mockito.anyInt()).thenReturn(true);
	 * 
	 * repository.deleteById(user.isPresent());
	 * 
	 * }
	 * 
	 * @Test void testHarddelete() {
	 *  Optional<User> user = repository.findById(1);
	 * Mockito.when(userServiceI.deleteUser(Mockito.anyInt()).thenReturn(repository.
	 * findAll());
	 * 
	 * 
	 * assertThat(user.getId()).isNull(); }
	 */
}
