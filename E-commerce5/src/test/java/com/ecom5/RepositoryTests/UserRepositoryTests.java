package com.ecom5.RepositoryTests;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ecom5.model.Role;
import com.ecom5.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	/* @Autowired
	private UserRepository repo;
	
	@Test
	public void testAssignRoleToUser() {
		Integer userId = 4;
		Integer roleId = 3;
		User user = repo.findById(userId).get();
		user.addRole(new Role(roleId));
		User updateUser = repo.save(user);
		assertThat(updatedUser.getRoles().hasSize(1));
	}	*/
}
