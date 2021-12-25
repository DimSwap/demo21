package com.example.demo21.repository;


import com.example.demo21.domain.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAuthor extends JpaRepository<Author,Long> {
}
