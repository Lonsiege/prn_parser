package com.develeap.excercise;

import java.io.IOException;

import com.develeap.excercise.controller.LibraryOperations;
import com.develeap.excercise.controller.MyLibrary;
import com.develeap.excercise.util.LibraryParser;
import com.develeap.excercise.util.PrnParser;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new IllegalArgumentException("Use library prn file as parameter");
		}
		new Main().go(args[0]);
	}

	private void go(String fname) throws Exception {
		LibraryParser parser = new PrnParser();
		LibraryOperations myLibrary = new MyLibrary();
		try {
			myLibrary = parser.parseFile(fname);
			System.out.println("There are " + myLibrary.getBooksCount() + " books and " + myLibrary.getAuthorsCount()
					+ " authors in this file.");
			System.out.println("The most productive authors are:");
			System.out.println(myLibrary.printTopAuthor(myLibrary.getTopThreeAuthors(), 1));
			System.out.println(myLibrary.printTopAuthor(myLibrary.getTopThreeAuthors(), 2));
			System.out.println(myLibrary.printTopAuthor(myLibrary.getTopThreeAuthors(), 3));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
