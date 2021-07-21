package com.home.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.shop.model.UserDTO;
import com.home.shop.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public String showLogin(Model model) {
		UserDTO dto = new UserDTO();
		model.addAttribute("newUser", dto);
		return "site/users/login";
	}

	@PostMapping("/checklogin")
	public String checkLogin(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		if (userService.checkLogin(username, password)) {
			session.setAttribute("USERNAME", username);
			System.out.println("S");
			return "site/home/home";
		} else {
			System.out.println("F");
			model.addAttribute("message", "Incorrect Username or Password!!!");
		}
		return "site/users/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("USERNAME");
		return "redirect:/site/home/home";
	}

}












