package com.example.demo21.repository;

import com.example.demo21.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPublisher extends JpaRepository<Publisher,Long> {
}
