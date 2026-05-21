package com.library.librarymanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/add-book")
    public String addBookPage() {
        return "add-book";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/view-books";
    }

    @GetMapping("/view-books")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "view-books";
    }

    @GetMapping("/available-books")
    public String availableBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "available-books";
    }

    @GetMapping("/search-books")
    public String searchBooks(@RequestParam String keyword, Model model) {

        List<Book> books = bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword
                );

        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);

        return "available-books";
    }

    @GetMapping("/edit-book/{id}")
    public String editBookPage(@PathVariable int id, Model model) {

        Book book = bookRepository.findById(id).orElse(null);

        model.addAttribute("book", book);

        return "edit-book";
    }

    @PostMapping("/update-book")
    public String updateBook(@ModelAttribute Book book) {

        bookRepository.save(book);

        return "redirect:/view-books";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "redirect:/view-books";
    }
}