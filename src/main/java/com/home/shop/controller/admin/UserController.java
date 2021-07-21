package com.home.shop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.shop.entity.User;
import com.home.shop.model.UserDTO;
import com.home.shop.service.StorageService;
import com.home.shop.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	StorageService storageService;

	@RequestMapping("")
	public String index(ModelMap model) {
		List<User> list = userService.findAll();
		model.addAttribute("users", list);
		return "admin/users/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		UserDTO dto = new UserDTO();
		dto.setIsEdit(false);
		model.addAttribute("user", dto);
		return "admin/users/addOrEdit";
	}

	@GetMapping("delete/{userId}")
	public ModelAndView delete(ModelMap model, @PathVariable("userId") Long userId) throws IOException {

		Optional<User> opt = userService.findById(userId);
		if (opt.isPresent()) {
			if (!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			userService.delete(opt.get());
			model.addAttribute("message", "Delete successfully!");
		} else {
			model.addAttribute("message", "User not found!");
		}
		return new ModelAndView("forward:/admin/users");
	}

	@GetMapping("edit/{userId}")
	public ModelAndView edit(ModelMap model, @PathVariable("userId") Long userId) {
		Optional<User> opt = userService.findById(userId);
		UserDTO dto = new UserDTO();
		if (opt.isPresent()) {
			User entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("user", dto);
			System.out.println(dto.getImage());
			return new ModelAndView("admin/users/addOrEdit", model);
		}
		model.addAttribute("message", "User is not exisedt");
		return new ModelAndView("forward:/admin/users/addOrEdit", model);
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
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("user") UserDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return new ModelAndView("admin/users/addOrEdit");
		}
		User entity = new User();
		BeanUtils.copyProperties(dto, entity);

		if (!dto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setImage(storageService.getStoredFileName(dto.getImageFile(), uuString));
			storageService.store(dto.getImageFile(), entity.getImage());
		}

		userService.save(entity);
		model.addAttribute("message", "Save successfully");
		return new ModelAndView("forward:/admin/users", model);
	}

//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "username", required = false) String username) {
//			List<User> list = null;
//			if (StringUtils.hasText(username)) {
//				list = userService.findByNameContaining(username);
//			} else {
//				list = userService.findAll();
//			}
//			model.addAttribute("users", list);
//			return "admin/users/searchpaginated";
//	}
//
//	@GetMapping("searchpaginated")
//	public String search(ModelMap model, @RequestParam(name = "username", required = false) String username,
//			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//
//			int currentPage = page.orElse(1);
//			int pageSize = size.orElse(5);
//
//			Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("username"));
//			Page<User> resultPage = null;
//
//			if (StringUtils.hasText(username)) {
//				resultPage = userService.findByNameContaining(username, pageable);
//				model.addAttribute("username", username);
//			} else {
//				resultPage = userService.findAll(pageable);
//			}
//			int totalPages = resultPage.getTotalPages();
//			if (totalPages > 0) {
//				int start = Math.max(1, currentPage - 2);
//				int end = Math.min(currentPage + 2, totalPages);
//
//				if (totalPages > 5) {
//					if (end == totalPages) {
//						start = end - 5;
//					} else if (start == 1) {
//						end = start + 5;
//					}
//				}
//				List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
//				model.addAttribute("pageNumbers", pageNumbers);
//			}
//
//			model.addAttribute("userPage", resultPage);
//			return "admin/users/searchpaginated";
//	}
}
