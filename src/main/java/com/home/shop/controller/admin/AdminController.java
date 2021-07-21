package com.home.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.shop.entity.Admin;
import com.home.shop.entity.Product;
import com.home.shop.model.AdminDTO;
import com.home.shop.service.AdminService;
import com.home.shop.service.ProductService;

@Controller
@RequestMapping("/adminz")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/login")
	public String showLogin(Model model) {
		AdminDTO dto = new AdminDTO();
		model.addAttribute("newUser", dto);
		return "admin/home/login";
	}

	@PostMapping("/checklogin")
	public String checkLogin(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		if (adminService.checkAdmin(username, password)) {
			Optional<Admin> opt = adminService.findById(username);
			Admin admin = opt.get();
			session.setAttribute("USERNAMEADMIN", admin);
			System.out.println((Admin)session.getAttribute("USERNAMEADMIN"));
			
			List<Product> list = productService.findAll();
			model.addAttribute("products", list);
			return "admin/products/index";
			
		} else {
			System.out.println("F");
			model.addAttribute("message", "Incorrect Username or Password!!!");
		}
		return "admin/home/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println(session.toString());
		session.removeAttribute("USERNAMEADMIN");
		return "redirect:/admin/categories/login";
	}
}
