package com.develeap.excercise.controller;

import java.util.AbstractMap;
import java.util.List;

import com.develeap.excercise.model.Author;
import com.develeap.excercise.model.Book;

public interface LibraryOperations {
	public List<Book> getBooksList();

	public int getBooksCount();

	public int getAuthorsCount();

	public List<AbstractMap.SimpleEntry<Author, Integer>> getTopThreeAuthors();

	public String printTopAuthor(List<AbstractMap.SimpleEntry<Author, Integer>> topList, int number)
			throws IllegalArgumentException;
}
