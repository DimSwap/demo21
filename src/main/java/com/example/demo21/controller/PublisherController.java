package com.example.demo21.controller;
import com.example.demo21.domain.Publisher;
import com.example.demo21.repository.RepositoryPublisher;
import com.example.demo21.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublisherController {

    private final PublisherService publisherService;
    public PublisherController (PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/publisher")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher savePublisher (@RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Publisher> saveAllPublishers (@RequestBody List<Publisher> publishers) {
        return publisherService.saveAllPublishers(publishers);
    }

    @PostMapping("/publisher/{publisher_id}/author/{author_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addAuthorToPublisher(@PathVariable Long publisher_id,@PathVariable Long author_id) {
        return publisherService.addAuthorToPublisher(publisher_id,author_id);
    }

    @PostMapping("/publisher/{publisher_id}/book/{book_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addBookToPublisher(@PathVariable Long publisher_id,@PathVariable Long book_id) {
        return publisherService.addBookToPublisher(publisher_id,book_id);
    }

    @PostMapping("/publisher/{publisher_id}/reward/{reward_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addRewardToPublisher(@PathVariable Long publisher_id,@PathVariable Long reward_id) {
        return publisherService.addRewardToPublisher(publisher_id,reward_id);
    }

    @GetMapping("/publisher")
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> getAllPublishers () {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getPublisherById (@PathVariable ("id") Long id) {
        return publisherService.getPublisherById(id);
    }

    @PutMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher updatePublisher (@PathVariable ("id") Long id, @RequestBody Publisher publisher) {
        return publisherService.updatePublisher(id,publisher);
    }

    @DeleteMapping("/publisher/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePublisher (@PathVariable("id") Long id) {
        return publisherService.deletePublisher(id);
    }
}