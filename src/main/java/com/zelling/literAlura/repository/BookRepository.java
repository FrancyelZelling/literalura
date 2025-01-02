package com.zelling.literAlura.repository;

import com.zelling.literAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String name);
    Optional<Book> findByApiId(Integer apiId);

}
