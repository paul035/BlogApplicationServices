package com.paulsofts.blogapplicationservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paulsofts.blogapplicationservices.repositories.UserRepository;
import com.paulsofts.blogapplicationservices.services.UserServiceImpl;

@SpringBootTest
class BlogApplicationServicesApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void serviceTest() {
		System.out.println("Service Testing");
		String packageName = this.userServiceImpl.getClass().getPackageName();
		String className = this.userServiceImpl.getClass().getName();
		System.out.println("package: " + packageName);
		System.out.println("class: " + className);
	}
	
	@Test
	void repositoryTest() {
		System.out.println("Repository Testing");
		String packageName = this.userRepository.getClass().getPackageName();
		String className = this.userRepository.getClass().getName();
		System.out.println("package: " + packageName);
		System.out.println("class: " + className);
	}

}
