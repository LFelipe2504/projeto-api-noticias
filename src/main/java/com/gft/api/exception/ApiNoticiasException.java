package com.gft.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiNoticiasException extends RuntimeException {

	private static final long serialVersionUID = -7461723216573927617L;
	
	private String message;

	public ApiNoticiasException(String message) {
		super(message);
		this.message = message;
	}
	
	
	

}
