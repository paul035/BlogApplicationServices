package com.paulsofts.blogapplicationservices.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
	private T t;
	private String message;
	private String response;
}
