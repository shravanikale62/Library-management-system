package com.library.librarymanagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class IssueBookController {

    @Autowired
    private IssueBookRepository issueBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/issue-book")
    public String showIssueBookPage() {
        return "issue-book";
    }

    @PostMapping("/save-issued-book")
    public String saveIssuedBook(@ModelAttribute IssueBook issueBook,
                                 RedirectAttributes redirectAttributes) {

        Book book = bookRepository.findByTitleIgnoreCase(issueBook.getBookTitle());

        if (book == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Book not found!"
            );

            return "redirect:/issue-book";
        }

        if (book.getQuantity() <= 0) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Book is Out Of Stock!"
            );

            return "redirect:/issue-book";
        }

        book.setQuantity(book.getQuantity() - 1);

        bookRepository.save(book);

        issueBook.setStatus("Issued");
        issueBook.setFineAmount(0);

        issueBookRepository.save(issueBook);

        redirectAttributes.addFlashAttribute(
                "success",
                "Book Issued Successfully!"
        );

        return "redirect:/issued-books";
    }

    @GetMapping("/issued-books")
    public String showIssuedBooks(Model model) {

        model.addAttribute(
                "issuedBooks",
                issueBookRepository.findAll()
        );

        return "issued-books";
    }

    @GetMapping("/return-books")
    public String showReturnBooks(Model model) {

        model.addAttribute(
                "issuedBooks",
                issueBookRepository.findAll()
        );

        return "return-books";
    }

    @GetMapping("/return-book/{id}")
    public String returnBook(@PathVariable int id,
                             RedirectAttributes redirectAttributes) {

        IssueBook issuedBook =
                issueBookRepository.findById(id).orElse(null);

        if (issuedBook != null &&
                issuedBook.getStatus().equals("Issued")) {

            LocalDate expectedReturnDate =
                    LocalDate.parse(issuedBook.getReturnDate());

            LocalDate actualReturnDate =
                    LocalDate.now();

            long lateDays =
                    ChronoUnit.DAYS.between(
                            expectedReturnDate,
                            actualReturnDate
                    );

            int fine = 0;

            if (lateDays > 0) {
                fine = (int) lateDays * 5;
            }

            issuedBook.setFineAmount(fine);

            issuedBook.setStatus("Returned");

            issueBookRepository.save(issuedBook);

            Book book =
                    bookRepository.findByTitleIgnoreCase(
                            issuedBook.getBookTitle()
                    );

            if (book != null) {

                book.setQuantity(book.getQuantity() + 1);

                bookRepository.save(book);
            }

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Book Returned Successfully!"
            );
        }

        return "redirect:/return-books";
    }

    @GetMapping("/my-issued-books")
    public String myIssuedBooks(Model model,
                                HttpSession session) {

        String studentName =
                (String) session.getAttribute("studentName");

        model.addAttribute(
                "issuedBooks",
                issueBookRepository.findByStudentName(studentName)
        );

        return "my-issued-books";
    }

    @GetMapping("/my-fines")
    public String myFines(Model model,
                          HttpSession session) {

        String studentName =
                (String) session.getAttribute("studentName");

        model.addAttribute(
                "issuedBooks",
                issueBookRepository.findByStudentName(studentName)
        );

        return "my-fines";
    }
}