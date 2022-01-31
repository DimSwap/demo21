package com.example.demo21.repository;


import com.example.demo21.domain.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryAuthor extends JpaRepository<Author,Long> {
    @Query(value = "SELECT a.firstName, a.lastName, a.birthdate" +
            " FROM Author a WHERE a.id = ?1")
    String findAuthorAndDateOfBirthById (int id);
}
