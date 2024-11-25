package com.project.utility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.utility.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
