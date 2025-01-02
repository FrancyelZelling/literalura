package com.zelling.literAlura.repository;

import com.zelling.literAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean findByNameContainsIgnoreCase(String name);
}
