package com.delta.poc.util;

import java.util.Date;
import java.util.List;

public class ErrDetails {
	private Date timestamp;
	private String message;
	private List<String>errors;
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public ErrDetails(Date timestamp, String message, List<String> errors) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errors = errors;
	}
	public ErrDetails(List<String> errors) {
		super();
		this.errors = errors;
	}
	public ErrDetails(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ErrDetails [timestamp=" + timestamp + ", message=" + message + "]";
	}
	
	

}
