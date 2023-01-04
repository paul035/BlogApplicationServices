package com.paulsofts.blogapplicationservices.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//UserDataTranserObject
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String about;

}
