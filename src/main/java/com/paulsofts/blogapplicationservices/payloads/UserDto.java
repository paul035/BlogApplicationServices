package com.paulsofts.blogapplicationservices.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//UserDataTranserObject
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4, message = "must contain atleast 4 characters")
	private String name;
	@Email(message = "Email address is not valid")
	private String email;
	@NotEmpty
	@Size(min = 8, max = 15, message = "must contain 8 characters")
	private String password;
	@NotEmpty
	private String about;

}
