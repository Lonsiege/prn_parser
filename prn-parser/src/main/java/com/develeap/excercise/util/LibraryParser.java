package com.develeap.excercise.util;

import java.io.IOException;

import com.develeap.excercise.controller.MyLibrary;

public interface LibraryParser {
	public MyLibrary parseFile(String fileName) throws IOException;
}
