package com.develeap.excercise.util;

import java.io.IOException;

public class WrongFormatException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6002087848292259690L;
	private String message;

	public WrongFormatException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "Wrong input file format. Check string: " +message;
	}

	public WrongFormatException() {
		super();
	}

	@Override
	public String toString() {
		return ("Wrong input file format. Check string: " + message);
	}
}
