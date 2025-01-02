package com.zelling.literAlura.main;

import com.zelling.literAlura.model.Author;
import com.zelling.literAlura.model.Book;
import com.zelling.literAlura.model.BookDataResponse;
import com.zelling.literAlura.repository.AuthorRepository;
import com.zelling.literAlura.repository.BookRepository;
import com.zelling.literAlura.service.BookService;
import com.zelling.literAlura.utils.Api;
import com.zelling.literAlura.utils.Converter;
import com.zelling.literAlura.utils.UI;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    private BookRepository bookRepository;
    private BookService bookService;
    private AuthorRepository authorRepository;
    private final UI ui = new UI();
    private final Api api = new Api();
    private final Converter converter = new Converter();
    private final Scanner scanner = new Scanner(System.in);
    private int option = -1;

    public Application(BookRepository bookRepository, AuthorRepository authorRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    public void app() {
        while (option != 0) {
            ui.showMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    getBookByTitle();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    listAliveAuthorsGivenYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 0:
                    System.out.println("exiting...");
                    break;
            }
        }
    }

    private void getBookByTitle() {
        System.out.println("Digite o nome do livro ou autor que deseja buscar:");
        var input = scanner.nextLine();

        input = URLEncoder.encode(input, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        var json = api.getData("https://gutendex.com/books?search=" + input);
        var bookResponse = converter.convertData(json, BookDataResponse.class);

        if (bookResponse.count() > 0) {
            List<Book> bookList = bookResponse.books().stream()
                    .map(Book::new)
                    .toList();

            bookList.forEach(book -> bookService.saveBook(book));

        } else {
            System.out.println("livro ou autor não encontrado");
        }

    }

    public void listBooks() {
        List<Book> books = bookRepository.findAll();

        books.forEach(b -> System.out.println(
                "Title = " + b.getTitle() +
                        ", idiomas = " + b.getLanguages() +
                        ", autores = " + b.getAuthors()
        ));
    }

    public void listAuthors() {
        List<Author> authors = authorRepository.findAll();

        authors.forEach(System.out::println);
    }

    private void listAliveAuthorsGivenYear() {
        System.out.println("Digite o ano desejado: ");
        Integer year = scanner.nextInt();
        scanner.nextLine();

        List<Author> authors = authorRepository.findAll();

        List<Author> aliveAuthors = authors.stream()
                .filter(author -> {
                    var birthYear = author.getBirthYear();
                    var deathYear = author.getDeathYear();

                    if(birthYear != null && birthYear < year){
                        if (deathYear == null || deathYear > year){
                            return true;
                        }
                    }

                    return false;
                })
                .toList();


        aliveAuthors.forEach(System.out::println);
    }

    private void listBooksByLanguage() {
        System.out.println("""
                
                pt - português
                en - inglês
                fr - francês
                es - espanhol
                """);

        System.out.println("digite a linguagem desejada: ");
        String language = "pt";

        List<Book> books = bookRepository.findAll();
        List<Book> booksFound = books.stream().filter(book -> book.getLanguages().contains(language)).toList();

        booksFound.forEach(b -> System.out.println("Title = " + b.getTitle()));
    }
}
