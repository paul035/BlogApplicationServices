package com.paulsofts.blogapplicationservices.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulsofts.blogapplicationservices.data.User;
import com.paulsofts.blogapplicationservices.exceptions.ResourceNotFoundException;
import com.paulsofts.blogapplicationservices.payloads.UserDto;
import com.paulsofts.blogapplicationservices.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//ModelMapper is used to covert one model to another model class
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		//data(Model class) are used here, apart from this everywhere DTO(data transfer object) is used
		User userSavedToDB = this.userRepository.save(this.dtoToUser(userDto));
		return this.userToDto(userSavedToDB);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser = this.userRepository.save(user);
		return this.userToDto(updateUser);

	}

	@Override
	public UserDto getUserById(int id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> userList = this.userRepository.findAll();
		List<UserDto> userDtoList = userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(int id) {
		User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		this.userRepository.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		/*
		 * User user = new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		/*
		 * UserDto userDto = new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
		 */
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
