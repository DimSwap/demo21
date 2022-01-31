package com.example.demo21.controller;

import com.example.demo21.domain.Author;
import com.example.demo21.domain.Book;
import com.example.demo21.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {


    private final AuthorService authorService;
    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;

    }

    @PostMapping("/author")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Author> saveAuthors (@RequestBody List<Author> authors) {
        return authorService.saveAuthors(authors);
    }

    @PostMapping("/author/{id}/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addBook (@PathVariable ("id") Long id, @RequestBody Book book) {
        return authorService.addBook(id,book);
    }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List <Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }
    @GetMapping("/author/birthday/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getAuthorBirthdayById(@PathVariable Integer id) {
        return authorService.getAuthorAndDateOfBirthdayById(id);
    }

    @PutMapping  ("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@PathVariable("id") Long id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);


    }

}