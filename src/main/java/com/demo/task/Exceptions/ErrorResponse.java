package com.demo.task.Exceptions;

public class ErrorResponse {
	private String message;
	
	public ErrorResponse() {
		
	}

	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}
	

}
