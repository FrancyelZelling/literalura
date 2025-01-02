package com.zelling.literAlura;

import com.zelling.literAlura.main.Application;
import com.zelling.literAlura.repository.AuthorRepository;
import com.zelling.literAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired private BookRepository bookRepository;
	@Autowired private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Application application = new Application(bookRepository, authorRepository);
		application.app();
	}
}
