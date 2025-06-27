//package sk.tuke.kpi.kp.pexeso.controller;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import sk.tuke.kpi.kp.pexeso.service.UserService;
//
//@Controller
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/register")
//    public String showRegistrationForm(HttpSession session, Model model) {
//        if (session.getAttribute("playerName") != null) {
//            return "redirect:/";
//        }
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@RequestParam String username,
//                               @RequestParam String password,
//                               HttpSession session,
//                               Model model) {
//        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
//            model.addAttribute("error", "Username and password must not be empty");
//            return "register";
//        }
//
//        boolean success = userService.registerUser(username.trim(), password);
//        if (success) {
//            session.setAttribute("playerName", username.trim());
//            return "redirect:/";
//        } else {
//            model.addAttribute("error", "Username already exists");
//            return "register";
//        }
//    }
//}
package sk.tuke.kpi.kp.pexeso.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.User;
import sk.tuke.kpi.kp.pexeso.service.UserService;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(HttpSession session) {
        if (session.getAttribute("playerName") != null) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Username and password must not be empty");
            return "register";
        }

        boolean success = userService.registerUser(username.trim(), password.trim());
        if (success) {
            session.setAttribute("playerName", username.trim());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        if (session.getAttribute("playerName") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Username and password are required");
            return "login";
        }

        Optional<User> user = userService.loginUser(username.trim(), password.trim());
        if (user.isPresent()) {
            session.setAttribute("playerName", user.get().getUsername());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}

