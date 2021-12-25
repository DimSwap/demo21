package com.example.demo21.repository;


import com.example.demo21.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBook extends JpaRepository<Book,Long> {
}
