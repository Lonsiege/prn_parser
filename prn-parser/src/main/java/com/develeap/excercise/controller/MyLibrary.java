package com.develeap.excercise.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.develeap.excercise.model.Author;
import com.develeap.excercise.model.Book;

public class MyLibrary implements LibraryOperations {
	private List<Book> booksList = new ArrayList<>();

	private Map<Author, Integer> authorsTop = new HashMap<>();

	@Override
	public List<Book> getBooksList() {
		return booksList;
	}

	public void addBook(Book newBook) {
		if (!isBookAlreadyHere(newBook)) {
			this.booksList.add(newBook);
		}
	}

	private boolean isBookAlreadyHere(Book newBook) {
		if (this.booksList.contains(newBook)) {
			return true;
		}
		return false;
	}

	public Map<Author, Integer> getAuthorsTop() {
		return authorsTop;
	}

	public void addNewAuthor(Author newAuthor) {
		this.authorsTop.put(newAuthor, 1);
	}

	public void incrementAuthorBooks(Author author) {
		this.authorsTop.put(author, authorsTop.get(author) + 1);
	}

	@Override
	public int getBooksCount() {
		return this.booksList.size();
	}

	@Override
	public int getAuthorsCount() {
		return this.authorsTop.size();

	}

	@Override
	public List<AbstractMap.SimpleEntry<Author, Integer>> getTopThreeAuthors() {
		List<AbstractMap.SimpleEntry<Author, Integer>> topThree = this.authorsTop.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3)
				.map(e -> new AbstractMap.SimpleEntry<Author, Integer>(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
		return topThree;
	}

	@Override
	public String printTopAuthor(List<AbstractMap.SimpleEntry<Author, Integer>> topList, int number)
			throws IllegalArgumentException {
		String res = "";
		if (topList == null) {
			throw new IllegalArgumentException("Top authors list can't be null");
		}
		number--;
		if (number >= topList.size()) {
			throw new IllegalArgumentException("Select 1-3 for position in top");
		}
		res = new String(topList.get(number).getKey().getName() + " (" + topList.get(number).getValue() + " titles)");
		return res;

	}
}
