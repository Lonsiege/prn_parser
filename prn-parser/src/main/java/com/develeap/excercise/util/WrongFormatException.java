package com.develeap.excercise.util;

import java.io.IOException;

public class WrongFormatException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6002087848292259690L;
	private String message;
	private String position;

	public WrongFormatException(String position) {
		this.position = position;
	}

	public WrongFormatException() {
		super();
	}

	@Override
	public String toString() {
		return ("Wrong input file format. Check string: " + position);
	}
}
