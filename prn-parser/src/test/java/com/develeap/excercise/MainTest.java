package com.develeap.excercise;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.develeap.excercise.controller.LibraryOperations;
import com.develeap.excercise.controller.MyLibrary;
import com.develeap.excercise.util.LibraryParser;
import com.develeap.excercise.util.PrnParser;

public class MainTest {

	static String prnFile;
	static LibraryParser parser = new PrnParser();

	static LibraryOperations myLibrary = new MyLibrary();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		prnFile = "src/ISBNs_sample.prn";
		myLibrary = parser.parseFile(prnFile);

	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void showAll() {
		System.out.println(myLibrary.getBooksList());
	}

	/*
	 * @Test public void testBookCount() { Assert.assertEquals(21,
	 * myLibrary.getBooksCount()); }
	 * 
	 * @Test public void testAuthorCount() { Assert.assertEquals(11,
	 * myLibrary.getAuthorsCount()); }
	 * 
	 * @Test public void testTopAuthor1() { String result =
	 * "Dickens,Charles (4 titles)"; Assert.assertEquals(result,
	 * myLibrary.printTopAuthor(myLibrary.getTopThreeAuthors(), 1)); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * testOutOfTopThreeAuthors() {
	 * myLibrary.printTopAuthor(myLibrary.getTopThreeAuthors(), 6); }
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testNullAuthorsList() {
		myLibrary.printTopAuthor(null, 2);
	}

	@Test(expected = FileNotFoundException.class)
	public void testNoFile() throws IOException {
		prnFile = "something";
		myLibrary = parser.parseFile(prnFile);
	}

}
