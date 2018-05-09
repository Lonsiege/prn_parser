package com.develeap.excercise.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.develeap.excercise.controller.MyLibrary;
import com.develeap.excercise.model.Author;
import com.develeap.excercise.model.Book;

public class PrnParserOld implements LibraryParser {
	private final static int START_POSITION = 0;
	private final static int NAME_POSITION = 40;
	private final static int AUTHOR_POSITION = NAME_POSITION + 30;
	private final static String MORE = "...";
	private final static String SLASH_DIV = "/";
	private static final String COMMA_DIV = ",";
	private static MyLibrary resLibrary = new MyLibrary();

	@Override
	public MyLibrary parseFile(String fileName) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line = reader.readLine();

			while (line != null) {
				String title = lineParser(line, START_POSITION, NAME_POSITION);
				String isbn = lineParser(line, AUTHOR_POSITION, line.length());
				String authorString = lineParser(line, NAME_POSITION, AUTHOR_POSITION);

				if (title.contains(MORE)) {
					String title2 = lineParser(reader.readLine(), START_POSITION, NAME_POSITION);
					title = new String(title.replace(MORE, "") + title2);
				}
				if (authorString.contains(MORE)) {
					if (!title.contains(MORE)) {
						line = reader.readLine();
					}
					String author2 = lineParser(line, NAME_POSITION, AUTHOR_POSITION);
					authorString = new String(authorString.replace(MORE, "") + author2);
				}

				Set<Author> authors = getAuthors(authorString);

				Book newBook = new Book(isbn, title, authors);
				resLibrary.addBook(newBook);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Wrong library filename");
		} catch (IOException e) {
			throw new IOException();
		}

		return resLibrary;
	}

	private Set<Author> getAuthors(String authorString) {
		Set<Author> res = new HashSet<>();
		if (!authorString.contains(SLASH_DIV)) {
			addAuthorToSet(res, authorString);
		} else {

			List<String> pairs = new ArrayList<>(Arrays.asList(authorString.split(COMMA_DIV)));
			List<String> listSurnames = new ArrayList<>();
			List<String> listNames = new ArrayList<>();
			for (int i = 0; i < pairs.size(); i++) {
				parseAuthorsAndNames(pairs.get(i), i, listSurnames, listNames);
			}

			for (int i = 0; i < listSurnames.size(); i++) {
				System.out.println(listSurnames.get(i) + " -> " + listNames.get(i));
				String combinedString = listSurnames.get(i) + "," + listNames.get(i);
				addAuthorToSet(res, combinedString);
			}

		}
		return res;
	}

	private void parseAuthorsAndNames(String incomeName, int i, List<String> listSurnames, List<String> listNames) {
		if (i % 2 == 0) {
			listSurnames.add(incomeName.split(SLASH_DIV)[0]);
			listSurnames.add(incomeName.split(SLASH_DIV)[1]);
		} else {
			listNames.add(incomeName.split(SLASH_DIV)[0]);
			listNames.add(incomeName.split(SLASH_DIV)[1]);
		}
	}

	private void addAuthorToSet(Set<Author> set, String authorName) {
		Author newAuthor = new Author(authorName);
		set.add(newAuthor);
		addAuthorsToTop(newAuthor);
	}

	private void addAuthorsToTop(Author newAuthor) {
		if (!resLibrary.getAuthorsTop().containsKey(newAuthor)) {
			resLibrary.addNewAuthor(newAuthor);
		} else {
			resLibrary.incrementAuthorBooks(newAuthor);
		}
	}

	private String lineParser(String line, int start, int end) {

		return line.substring(start, end).trim();
	}

}
