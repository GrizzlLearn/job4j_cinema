package ru.job4j.cinema.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.User;

import ru.job4j.cinema.service.UserService;

import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 14:23
 */
@Controller
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/refister")
	public String getRegistrationPage(Model model) {
		return "users/register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model) {
		Optional<User> saveUser = userService.save(user);
		if (saveUser.isEmpty()) {
			model.addAttribute("message", "Пользователь с такой почтой уже существует");
			return "users/register";
		}
		return "redirect:/index";
	}

	@GetMapping("/login")
	public String getLoginPage(@ModelAttribute User user, HttpServletRequest request) {
		Optional<User> userOptional = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (userOptional.isEmpty()) {
			return "users/login";
		}
		HttpSession session = request.getSession();;
		session.setAttribute("user", userOptional.get());
		return "users/login";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
		Optional<User> userOptional = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (userOptional.isEmpty()) {
			model.addAttribute("message", "Почта или пароль введены неверно");
			return "users/login";
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", userOptional.get());
		return "redirect:/index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/login";
	}
}
