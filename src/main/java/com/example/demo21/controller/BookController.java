package com.example.demo21.controller;

import com.example.demo21.domain.Book;
import com.example.demo21.repository.RepositoryBook;
import com.example.demo21.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {


    private final BookService bookService;
    public BookController (BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Book> saveBooks(@RequestBody List<Book> books) {
        return bookService.saveBooks(books);
    }

    @PostMapping("/book/{book_id}/author/{author_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addAuthorToBook (@PathVariable Long book_id,@PathVariable Long author_id) {
        return bookService.addAuthorToBook(author_id,book_id);
    }

    @PostMapping("/book/{book_id}/publisher/{publisher_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addPublisherToBook (@PathVariable Long book_id,@PathVariable Long publisher_id) {
        return bookService.addPublisherToBook(publisher_id, book_id);
    }

    @PostMapping("/book/{book_id}/reward/{reward_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addRewardToBook (@PathVariable Long book_id,@PathVariable Long reward_id) {
        return bookService.addRewardToBook(reward_id,book_id);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return bookService.updateBook(id,book);
    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);

    }


}