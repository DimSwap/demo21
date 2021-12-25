package com.example.demo21.controller;

import com.example.demo21.domain.Author;
import com.example.demo21.repository.RepositoryAuthor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

        private final RepositoryAuthor repository;

        public AuthorController(RepositoryAuthor repository) {
            this.repository = repository;
        }

        //Операция сохранения юзера в базу данных
        @PostMapping("/users")
        @ResponseStatus(HttpStatus.CREATED)
        public Author saveEmployee(@RequestBody Author author) {
            return repository.save(author);
        }

        //Получение списка юзеров
        @GetMapping("/users")
        @ResponseStatus(HttpStatus.OK)
        public List<Author> getAllUsers() {
            return repository.findAll();
        }

        //Получения юзера по id
        @GetMapping("/users/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Author getEmployeeById(@PathVariable Long id) {

            Author author = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
//
//        if (author.getIsDeleted()) {
//            throw new EntityNotFoundException("Employee was deleted with id = " + id);
//        }

            return author;
        }

        //Обновление юзера
        @PutMapping("/users/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Author refreshEmployee(@PathVariable("id") Long id, @RequestBody Author author) {

            return repository.findById(id)
                    .map(entity -> {
                        entity.setFirstName(author.getFirstName());
                        entity.setLastName(author.getLastName());
                        entity.setBirthdate(author.getBirthdate());
                        return repository.save(entity);
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
        }

        //Удаление по id
        @DeleteMapping("/users/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void removeEmployeeById(@PathVariable Long id) {
            //repository.deleteById(id);
            Author author= repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
            //employee.setIsDeleted(true);
            repository.delete(author);//save(employee);
        }

        //Удаление всех юзеров
        @DeleteMapping("/users")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void removeAllUsers() {
            repository.deleteAll();
        }
    }

