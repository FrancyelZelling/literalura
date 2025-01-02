package com.zelling.literAlura.service;

import com.zelling.literAlura.model.Author;
import com.zelling.literAlura.model.Book;
import com.zelling.literAlura.repository.AuthorRepository;
import com.zelling.literAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Book saveBook(Book book) {
        Optional<Book> findBook = bookRepository.findByTitle(book.getTitle());

        if (findBook.isEmpty()) {
            Set<Author> findAuthors = new HashSet<>();

            for (Author author : book.getAuthors()) {
                Optional<Author> existingAuthor = authorRepository.findByNameContainsIgnoreCase(author.getName());

                if (existingAuthor.isPresent()) {
                    findAuthors.add(existingAuthor.get());
                } else {
                    authorRepository.save(author);
                    findAuthors.add(author);
                }
            }
            book.setAuthors(findAuthors);
            return bookRepository.save(book);
        } else {
            return book;
        }

    }
}
