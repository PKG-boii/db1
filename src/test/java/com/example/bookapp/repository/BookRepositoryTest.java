package com.example.bookapp.repository;

import com.example.bookapp.entity.Author;
import com.example.bookapp.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testFindAllBooksWithAuthors() {
        Author author = new Author("Jane Austen");
        author = authorRepository.save(author);

        Book book = new Book("Pride and Prejudice", "111222333", author);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAllBooksWithAuthors();
        assertFalse(books.isEmpty());
        assertEquals("Jane Austen", books.get(0).getAuthor().getName());
    }
}
