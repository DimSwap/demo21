package com.example.demo21.controller;

import com.example.demo21.domain.Book;
import com.example.demo21.repository.RepositoryBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

            private final RepositoryBook repository;

            public BookController(RepositoryBook repository) {
                this.repository = repository;
            }


    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveEmployee(@RequestBody Book book) {
        return repository.save(book);
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllUsers() {
        return repository.findAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getEmployeeById(@PathVariable Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));

        /*if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }*/

        return book;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book refreshEmployee(@PathVariable("id") Long id, @RequestBody Book book) {

        return repository.findById(id)
                .map(entity -> {
                    entity.setIsbn(book.getIsbn());
                    entity.setTitle(book.getTitle());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    //Удаление по id
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Long id) {
        //repository.deleteById(id);
       Book book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        //employee.setIsDeleted(true);
        repository.delete(book);//save(employee);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        repository.deleteAll();
    }
}