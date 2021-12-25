package com.example.demo21.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BOOK_TITLE")
    @Size(min = 2,max = 30)
    private String title;
    @Column(name = "BOOK_ISBN")
    private String isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
