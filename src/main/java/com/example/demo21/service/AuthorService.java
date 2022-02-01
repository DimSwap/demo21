package com.example.demo21.service;

import com.example.demo21.domain.Author;
import com.example.demo21.domain.Book;
import com.example.demo21.repository.RepositoryAuthor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AuthorService {

    private final RepositoryAuthor authorRepository;

    public AuthorService(RepositoryAuthor authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> saveAuthors(List<Author> authors) {
        return authorRepository.saveAll(authors);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author addBook(Long id, Book book) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + id + "not found"));
        author.getBooks().add(book);
        return authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + id + "not found"));
    }

    public String getAuthorAndDateOfBirthdayById(Integer id) {
        return authorRepository.findAuthorAndDateOfBirthById(id);
    }

    public Author updateAuthor(Long id, Author author) {
        return authorRepository.findById(id)
                .map(entity -> {
                    entity.setFirstName(author.getFirstName());
                    entity.setLastName(author.getLastName());
                    entity.setBirthdate(author.getBirthdate());
                    return authorRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));
    }

    public String deleteAuthor(Long id) {
        authorRepository.deleteById(id);
        return "Author with id + " + id + "was deleted";
    }

}

