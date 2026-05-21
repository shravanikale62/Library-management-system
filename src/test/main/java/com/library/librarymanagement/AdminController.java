package com.library.librarymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssueBookRepository issueBookRepository;

    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model) {

        int totalBooks = bookRepository.findAll()
                .stream()
                .mapToInt(book -> book.getQuantity())
                .sum();

        long totalStudents = userRepository.findByRole("Student").size();

        long issuedBooks = issueBookRepository.findAll()
                .stream()
                .filter(book -> book.getStatus().equals("Issued"))
                .count();

        long returnedBooks = issueBookRepository.findAll()
                .stream()
                .filter(book -> book.getStatus().equals("Returned"))
                .count();

        int pendingFines = issueBookRepository.findAll()
                .stream()
                .mapToInt(book -> book.getFineAmount())
                .sum();

        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("issuedBooks", issuedBooks);
        model.addAttribute("returnedBooks", returnedBooks);
        model.addAttribute("pendingFines", pendingFines);

        return "admin-dashboard";
    }

    @GetMapping("/students")
    public String studentsPage(Model model) {
        model.addAttribute("students", userRepository.findByRole("Student"));
        return "students";
    }

    @GetMapping("/reports")
    public String reportsPage(Model model) {

        int totalBooks = bookRepository.findAll()
                .stream()
                .mapToInt(book -> book.getQuantity())
                .sum();

        long totalStudents = userRepository.findByRole("Student").size();

        long issuedBooks = issueBookRepository.findAll()
                .stream()
                .filter(book -> book.getStatus().equals("Issued"))
                .count();

        long returnedBooks = issueBookRepository.findAll()
                .stream()
                .filter(book -> book.getStatus().equals("Returned"))
                .count();

        int pendingFines = issueBookRepository.findAll()
                .stream()
                .mapToInt(book -> book.getFineAmount())
                .sum();

        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("issuedBooks", issuedBooks);
        model.addAttribute("returnedBooks", returnedBooks);
        model.addAttribute("pendingFines", pendingFines);

        return "reports";
    }
}