package com.project.utility.springCaching;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.utility.entity.Book;

@Service
public class BookService {
	
	private List<Book> books = new ArrayList<>();
	
	public boolean addBook() {
		
		Book book1 = new Book(101, "Book1", 456.75f);
		Book book2 = new Book(102, "Book2", 863.36f);
		Book book3 = new Book(103, "Book3", 265.98f);
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
		
		return true;
	}
	
	@Cacheable(cacheNames = "books")
	public List<Book> getAllBooks() {
		System.out.println("Fetching all books from list.");
		return books;
	}
	
	@Cacheable(cacheNames = "book", key = "#bookId")
	public Book getBookById(int bookId) {
		System.out.println("Fetching specific books from list.");
		return books.stream().filter(e -> e.getBookId()==bookId).findFirst().get();
	}
	
	@CachePut(cacheNames = "book", key = "#book.bookId")
	public boolean updateBook(Book book) {
		boolean flag = false;
		System.out.println("Updating specific books from list.");
		for (Book e : books) {
			if(e.getBookId() == book.getBookId()) {
				e.setBookName(book.getBookName());
				e.setPrice(book.getPrice());
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	@CacheEvict(cacheNames = "book", key = "#book.bookId")
	public boolean deleteBook(Book book) {
		boolean flag = false;
		System.out.println("Deleting specific books from list.");
		for (Book e : books) {
			if(e.getBookId() == book.getBookId()) {
				flag = true;
			}
		}
		
		if(flag)
			books.remove(books.stream().filter(e -> e.getBookId()==book.getBookId()).findFirst().get());
		return flag;
	}

}
