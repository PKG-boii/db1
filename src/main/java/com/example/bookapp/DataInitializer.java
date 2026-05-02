package com.example.bookapp;

import com.example.bookapp.entity.Author;
import com.example.bookapp.entity.Book;
import com.example.bookapp.repository.AuthorRepository;
import com.example.bookapp.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (authorRepository.count() == 0 && bookRepository.count() == 0) {
            List<Author> authors = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                authors.add(new Author("Author " + i));
            }
            authorRepository.saveAll(authors);

            List<Book> books = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                books.add(new Book("Book Title " + i, "ISBN-100" + i, authors.get(i - 1)));
            }
            bookRepository.saveAll(books);
        }
    }
}
