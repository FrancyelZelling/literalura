package com.zelling.literAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer apiId;
    @Column(unique = true)
    String title;
    List<String> subjects;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Author> authors = new HashSet<>();
    List<String> languages = new ArrayList<>();

    public Book(){}

    public Book(Integer apiId,String title, List<String> subjects, HashSet<Author> authors, List<String> languages) {
        this.apiId = apiId;
        this.title = title;
        this.subjects = subjects;
        this.authors = authors;
        this.languages = languages;
    }

    public Book(BookData data){
        this.title = data.title();
        this.subjects = data.subjects();
        this.authors = new HashSet<>();
        for (Person person : data.authors()){
            this.authors.add(new Author(person.name(), person.birthYear(), person.deathYear()));
        }
        this.languages = data.languages();
    }

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

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }
}
