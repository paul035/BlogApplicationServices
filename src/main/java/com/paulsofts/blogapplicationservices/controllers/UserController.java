package com.paulsofts.blogapplicationservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulsofts.blogapplicationservices.payloads.UserDto;
import com.paulsofts.blogapplicationservices.services.UserServiceImpl;
import com.paulsofts.blogapplicationservices.utils.GenericResponse;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to paulsofst";
	}
	
	@PostMapping("/create")
	public ResponseEntity<GenericResponse<UserDto>> createUser(@RequestBody UserDto userDto){
		UserDto createdUserDto = this.userServiceImpl.createUser(userDto);
		return new ResponseEntity<GenericResponse<UserDto>>(new GenericResponse<UserDto>(createdUserDto, "user created", "OK"), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<GenericResponse<UserDto>> updateUser(@RequestBody UserDto userDto, @PathVariable("id") int userId){
		UserDto updatedUserDto = this.userServiceImpl.updateUser(userDto, userId);
		return new ResponseEntity<GenericResponse<UserDto>>(new GenericResponse<UserDto>(updatedUserDto,"user updated", "OK"), HttpStatus.OK); 
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<GenericResponse<UserDto>> getUserById(@PathVariable("id") int userId){
		UserDto userDto = this.userServiceImpl.getUserById(userId);
		return new ResponseEntity<GenericResponse<UserDto>>(new GenericResponse<UserDto>(userDto, "user", "OK"), HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<GenericResponse<List>> getAllUser(){
		List<UserDto> userDtoList = this.userServiceImpl.getAllUser();
		return new ResponseEntity<GenericResponse<List>>(new GenericResponse<List>(userDtoList, "user list", "OK"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GenericResponse<String>> deleteUser(@PathVariable("id") int userId){
		this.userServiceImpl.deleteUser(userId);
		return new ResponseEntity<GenericResponse<String>>(new GenericResponse<String>("userId: " + userId, "user deleted", "OK"), HttpStatus.OK);

	}
}
