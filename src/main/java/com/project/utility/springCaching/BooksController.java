package com.project.utility.springCaching;

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
@RequestMapping("/books")
public class BooksController {
	
	private final BookService bookService;
	
	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	@PostMapping
	public ResponseEntity<Boolean> addBooks() {
		return ResponseEntity.ok(bookService.addBook());
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		return ResponseEntity.ok(bookService.getBookById(id));
	}
	
	@PutMapping
	public ResponseEntity<Boolean> updateBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.updateBook(book));
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.deleteBook(book));
	}

}
