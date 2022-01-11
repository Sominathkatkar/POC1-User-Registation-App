package com.neo.repositryTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.entity.User;
import com.neo.repository.UserRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {
   
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	private User user1;
	private User user2;
	private List<User> list;

	@BeforeEach
	void setUp() {
	User user1=User.builder()
			//.id(1)
			.fName("Ram")
			.lName("Katkar")
			.gender("Male")
			.email("sominath@gmail.com")
			.address("Aurangabad")
			.pincode(431151)
			.dob(LocalDate.of(2000, 10, 10))
			.doj(LocalDate.of(2021, 10, 15))
			.activeSw(true)
			.build();
	
	testEntityManager.persist(user1);
	testEntityManager.flush();

		User user2=User.builder()
				//.id(1)
				.fName("Suresh")
				.lName("Katkar")
				.gender("Male")
				.email("suresh@gmail.com")
				.address("Aurangabad")
				.pincode(431151)
				.dob(LocalDate.of(1995, 10, 10))
				.doj(LocalDate.of(2021, 10, 15))
				.activeSw(true)
				.build();
		
		testEntityManager.persist(user2);
		testEntityManager.flush();
		
		List<User> list=new ArrayList<>();
		list.add(user1);
		list.add(user2);
       }
	
		/*
		 * @Test public void testFindAll() {
		 * 
		 * List<User> list1 = repo.findAll(); assertEquals(list1.get(0), 2); }
		 */
	@Test
	public void testFindByFname() {
		
			User user1=User.builder()
					//.id(1)
					.fName("Ram")
					.lName("Katkar")
					.gender("Male")
					.email("sominath@gmail.com")
					.address("Aurangabad")
					.pincode(431151)
					.dob(LocalDate.of(2000, 10, 10))
					.doj(LocalDate.of(2021, 10, 15))
					.activeSw(true)
					.build();
			
			testEntityManager.persist(user1);
			testEntityManager.flush();
			
			List<User> list2 = repo.findByfName(user1.getFName());
			
			assertEquals(list2.get(0).getFName(), "Ram");
	}
	@Test
	public void testFindByLastName() {
		List<User> bylName = repo.findBylName("Katkar");
		assertEquals(bylName.get(0).getLName(), "Katkar");
	}
	@Test
	public void testFindByPincode() {
		List<User> pincode = repo.findByPincode(431151);
		assertEquals(pincode.get(0).getPincode(), 431151);
	}
}