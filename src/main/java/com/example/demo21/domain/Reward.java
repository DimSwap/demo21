package com.example.demo21.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "REWARDS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id",insertable = false, updatable = false)
    private Long id;

    @Column(name = "REWARD_NAME", length = 50)
    private String title;

    @Column(name = "REWARD_YEAR")
    private Long year;

    @JsonIgnore
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @JsonIgnore
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @JsonIgnore
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "NAME")
    private String name;


}


