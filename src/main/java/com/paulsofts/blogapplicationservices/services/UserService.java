package com.paulsofts.blogapplicationservices.services;

import java.util.List;

import com.paulsofts.blogapplicationservices.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	public UserDto updateUser(UserDto userDto, int id);
	public UserDto getUserById(int id);
	public List<UserDto> getAllUser();
	public void deleteUser(int id);

}
