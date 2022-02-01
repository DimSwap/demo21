package com.example.demo21.service;

import com.example.demo21.domain.Author;
import com.example.demo21.domain.Book;
import com.example.demo21.domain.Publisher;
import com.example.demo21.domain.Reward;
import com.example.demo21.repository.ReposirtoryReward;
import com.example.demo21.repository.RepositoryAuthor;
import com.example.demo21.repository.RepositoryBook;
import com.example.demo21.repository.RepositoryPublisher;
import org.aspectj.weaver.patterns.ReferencePointcut;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookService {

    private final RepositoryBook bookRepository;
    private final RepositoryAuthor authorRepository;
    private final RepositoryPublisher publisherRepository;
    private final ReposirtoryReward rewardRepository;

    public BookService ( RepositoryBook bookRepository, RepositoryAuthor authorRepository,
                         RepositoryPublisher publisherRepository,ReposirtoryReward rewardRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.rewardRepository = rewardRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> saveBooks (List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public List<Book> getAllBooks () {
        return bookRepository.findAll();
    }

    public Book getBookById (Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with this id " + id + "not found"));

    }

    public  Book updateBook (Long id, Book book) {
        return bookRepository.findById(id)
                .map (entity -> {
                    entity.setIsbn(book.getIsbn());
                    entity.setTitle(book.getTitle());
                    return bookRepository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id = " + id));
    }

    public String deleteBook(Long id){
        bookRepository.deleteById(id);
        return "Book with id " + id + " was deleted";
    }

    public Book addAuthorToBook (Long authorId, Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Author author = authorRepository.findById(authorId).orElse(null);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book addPublisherToBook(Long publisherId, Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Publisher publisher = publisherRepository.findById(publisherId).orElse(null);
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    public Book addRewardToBook (Long rewardId, Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Reward reward = rewardRepository.findById(rewardId).orElse(null);
        book.getRewards().add(reward);
        return bookRepository.save(book);
    }
}