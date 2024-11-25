package com.project.utility.ehcache;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.project.utility.entity.Book;
import com.project.utility.repository.BookRepository;

@Service
public class BookServiceImpl {
	
	@Autowired
	private BookRepository bookRepository;

	public Book addBook(Book book) {
		Book save = bookRepository.save(book);
		return save;
	}
	
	@Cacheable(cacheNames = "books")
	public List<Book> getAllBooks() {
		System.out.println("Fetching all books from list.");
		return bookRepository.findAll();
	}
	
	@Cacheable(cacheNames = "book", key = "#bookId")
	public Book getBookById(int bookId) {
		System.out.println("Fetching specific books from list.");
		return bookRepository.findById(bookId).orElse(null);
	}
	
	@CachePut(cacheNames = "book", key = "#book.bookId")
	public Book updateBook(Book book) {
		System.out.println("Updating specific books from list.");
		
		Book save = null;
		Book orElse = bookRepository.findById(book.getBookId()).orElse(null);
		if(Objects.nonNull(orElse)) {
			orElse.setBookName(book.getBookName());
			orElse.setPrice(book.getPrice());
			save = bookRepository.save(orElse);
		}
		
		return save;
	}
	
	@CacheEvict(cacheNames = "book", key = "#book.bookId")
	public boolean deleteBook(Book book) {
		System.out.println("Deleting specific books from list.");
		
		Book orElse = bookRepository.findById(book.getBookId()).orElse(null);
		if(Objects.nonNull(orElse)) {
			orElse.setBookName(book.getBookName());
			orElse.setPrice(book.getPrice());
			bookRepository.delete(orElse);
			
			return true;
		}
		
		return false;
	}
}
