package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepositoy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepositoy userRepo;

	@Autowired
	private RoleRepository roleRepo;

	/*
	 * @Test public void addUserTest() throws ParseException { User user = new
	 * User(); user.setFirstName("Vivek"); user.setLastName("B S");
	 * BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
	 * user.setPassword(pass.encode("vivek")); user.setAge(21);
	 * user.setAddress("Bangalore"); user.setGender("Male");
	 * user.setPhoneNo("8765423195"); String str = "20-10-2000"; Date date = new
	 * java.sql.Date( ((java.util.Date) new
	 * SimpleDateFormat("dd-MM-yyyy").parse(str)).getTime());
	 * user.setDataOfBirth(date); Role role = roleRepo.findByName("User");
	 * user.addRole(role); User savedUser = userRepo.save(user);
	 * 
	 * User existUser = entityManager.find(User.class, savedUser.getId());
	 * 
	 * assertThat(user.getId()).isEqualTo(existUser.getId());
	 * 
	 * }
	 */

	@Test
	public void testAddRolesToExistingUser() {
		User user = userRepo.findById((long) 1468342).get();
		Role roleUser = roleRepo.findByName("Admin");
		user.addRole(roleUser);

		User savedUser = userRepo.save(user);
		assertThat(savedUser.getRoles().size()).isEqualTo(1);
	}

}
