package com.develeap.excercise.model;

import java.util.HashSet;
import java.util.Set;

public class Book {
	private String isbn;
	private String title;
	private Set<Author> authors = new HashSet<>();

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", authors=" + authors + "]";
	}

	public Book(String isbn, String title, Set<Author> authors) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
	}

}
