package com.library.librarymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/student-dashboard")
    public String studentDashboard() {
        return "student-dashboard";
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session,
                              org.springframework.ui.Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               RedirectAttributes redirectAttributes) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("error", "Email already exists!");
            return "redirect:/register";
        }

        if (userRepository.findByMobile(user.getMobile()) != null) {
            redirectAttributes.addFlashAttribute("error", "Mobile number already exists!");
            return "redirect:/register";
        }

        if (userRepository.findByFullName(user.getFullName()) != null) {
            redirectAttributes.addFlashAttribute("error", "Name already exists!");
            return "redirect:/register";
        }

        userRepository.save(user);

        redirectAttributes.addFlashAttribute(
                "success",
                "Account created successfully! Please login."
        );

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String role,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        User user = userRepository.findByEmailAndPasswordAndRole(
                email,
                password,
                role
        );

        if (user != null && role.equals("Admin")) {

            session.setAttribute("loggedInUser", user);

            return "redirect:/admin-dashboard";
        }

        if (user != null && role.equals("Student")) {

            session.setAttribute("loggedInUser", user);
            session.setAttribute("studentName", user.getFullName());

            return "student-dashboard";
        }

        if (user != null && role.equals("Librarian")) {

            session.setAttribute("loggedInUser", user);
            session.setAttribute("studentName", user.getFullName());

            return "student-dashboard";
        }

        redirectAttributes.addFlashAttribute(
                "error",
                "Invalid email, password or role!"
        );

        return "redirect:/login";
    }
}