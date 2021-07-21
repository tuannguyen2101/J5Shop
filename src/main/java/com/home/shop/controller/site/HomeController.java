package com.home.shop.controller.site;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

import com.home.shop.entity.Category;
import com.home.shop.entity.Product;
import com.home.shop.model.CategoryDTO;
import com.home.shop.model.ProductDTO;
import com.home.shop.model.UserDTO;
import com.home.shop.service.CategoryService;
import com.home.shop.service.ProductService;
import com.home.shop.service.StorageService;
import com.home.shop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/j5shop")
public class HomeController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	StorageService storageService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	@Autowired
	UserService userService;

	@ModelAttribute("categories")
	public List<CategoryDTO> getCategories() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDTO dto = new CategoryDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@RequestMapping("")
	public String home(ModelMap model) {
		List<Product> list = productService.findAll();
		model.addAttribute("products", list);
		return "site/home/home";
	}

	@GetMapping("product/{productId}")
	public ModelAndView detail(ModelMap model, @PathVariable("productId") Long productId) {
		Optional<Product> opt = productService.findById(productId);
		ProductDTO dto = new ProductDTO();
		if (opt.isPresent()) {
			Product entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setCategoryId(entity.getCategory().getCategoryId());
			model.addAttribute("product", dto);
			return new ModelAndView("site/home/productDetail", model);
		}
		return new ModelAndView("forward:/site/home/productDetail", model);
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> saveFile(@PathVariable String filename) {
		Resource file = storageService.LoadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Category> list = null;
		if (StringUtils.hasText(name)) {
			list = categoryService.findByNameContaining(name);
		} else {
			list = categoryService.findAll();
		}
		model.addAttribute("products", list);
		return "admin/products/search";
	}

	@GetMapping("searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Category> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = categoryService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = categoryService.findAll(pageable);
		}
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages) {
					start = end - 5;
				} else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("categoryPage", resultPage);
		return "admin/products/searchpaginated";
	}

	@GetMapping("cart/{productId}")
	public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {
		Optional<Product> opt = productService.findById(productId);
		ProductDTO dto = new ProductDTO();
		if (opt.isPresent()) {
			Product entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setCategoryId(entity.getCategory().getCategoryId());
			model.addAttribute("product", dto);
			return new ModelAndView("site/home/cart", model);
		}
		model.addAttribute("message", "Product is not exisedt");
		return new ModelAndView("forward:/site/home/cart", model);
	}

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
		return "redirect:/j5shop/login";
	}

}
