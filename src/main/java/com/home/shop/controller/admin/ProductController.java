package com.home.shop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.shop.entity.Category;
import com.home.shop.entity.Product;
import com.home.shop.model.CategoryDTO;
import com.home.shop.model.ProductDTO;
import com.home.shop.service.AdminService;
import com.home.shop.service.CategoryService;
import com.home.shop.service.ProductService;
import com.home.shop.service.StorageService;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	StorageService storageService;

	@Autowired
	AdminService adminService;

	@ModelAttribute("categories")
	public List<CategoryDTO> getCategories() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDTO dto = new CategoryDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@RequestMapping("")
	public String index(ModelMap model) {
		List<Product> list = productService.findAll();
		model.addAttribute("products", list);
		return "admin/products/index";
	}

	@GetMapping("add")
	public String add(Model model) {
		ProductDTO dto = new ProductDTO();
		dto.setIsEdit(false);
		model.addAttribute("product", dto);
		return "admin/products/addOrEdit";
	}

	@GetMapping("delete/{productId}")
	public ModelAndView delete(ModelMap model, @PathVariable("productId") Long productId) throws IOException {

		Optional<Product> opt = productService.findById(productId);
		if (opt.isPresent()) {
			if (!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			productService.delete(opt.get());
			model.addAttribute("message", "Delete successfully!");
		} else {
			model.addAttribute("message", "Product not found!");
		}
		return new ModelAndView("forward:/admin/products");
	}

	@GetMapping("edit/{productId}")
	public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {
		Optional<Product> opt = productService.findById(productId);
		ProductDTO dto = new ProductDTO();
		if (opt.isPresent()) {
			Product entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setCategoryId(entity.getCategory().getCategoryId());
			dto.setIsEdit(true);
			model.addAttribute("product", dto);
			return new ModelAndView("admin/products/addOrEdit", model);
		}
		model.addAttribute("message", "Product is not exisedt");
		return new ModelAndView("forward:/admin/products/addOrEdit", model);
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> saveFile(@PathVariable String filename) {
		Resource file = storageService.LoadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("product") ProductDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/products/addOrEdit");
		}
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);

		Category category = new Category();
		category.setCategoryId(dto.getCategoryId());
		entity.setCategory(category);

		if (!dto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setImage(storageService.getStoredFileName(dto.getImageFile(), uuString));
			storageService.store(dto.getImageFile(), entity.getImage());
		}

		productService.save(entity);
		model.addAttribute("message", "Save successfully");
		return new ModelAndView("forward:/admin/products", model);
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Product> list = null;
		if (StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name);
		} else {
			list = productService.findAll();
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
		Page<Product> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = productService.findAll(pageable);
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
		model.addAttribute("productPage", resultPage);
		return "admin/products/searchpaginated";
	}

}
