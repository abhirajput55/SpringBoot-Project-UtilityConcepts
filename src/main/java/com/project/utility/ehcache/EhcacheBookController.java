package com.project.utility.ehcache;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.utility.entity.Book;

@RestController
@RequestMapping("/ehCacheBooks")
public class EhcacheBookController {
	
	private final BookServiceImpl bookServiceImpl;
	
	public EhcacheBookController(BookServiceImpl bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}
	
	
	@PostMapping
	public ResponseEntity<Book> addBooks(@RequestBody Book book) {
		return ResponseEntity.ok(bookServiceImpl.addBook(book));
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookServiceImpl.getAllBooks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		return ResponseEntity.ok(bookServiceImpl.getBookById(id));
	}
	
	@PutMapping
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookServiceImpl.updateBook(book));
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookServiceImpl.deleteBook(book));
	}

}

