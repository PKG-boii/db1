package com.example.bookapp.controller;

import com.example.bookapp.entity.Book;
import com.example.bookapp.service.AuthorService;
import com.example.bookapp.service.BookService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes, Model model) {
        try {
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
            return "redirect:/";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Integrity violation: Please check the input data.");
            model.addAttribute("authors", authorService.getAllAuthors());
            return "addBook";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while saving the book.");
            model.addAttribute("authors", authorService.getAllAuthors());
            return "addBook";
        }
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "updateBook";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book, RedirectAttributes redirectAttributes, Model model) {
        try {
            bookService.updateBook(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
            return "redirect:/";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Integrity violation: Please check the input data.");
            model.addAttribute("authors", authorService.getAllAuthors());
            return "updateBook";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while updating the book.");
            model.addAttribute("authors", authorService.getAllAuthors());
            return "updateBook";
        }
    }
}
