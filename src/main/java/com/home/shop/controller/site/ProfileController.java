package com.home.shop.controller.site;

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
@RequestMapping("user")
public class ProfileController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;

	@GetMapping("register")
	public String register(Model model) {
		UserDTO dto = new UserDTO();
		model.addAttribute("user", dto);
		return "site/users/register";
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
			return new ModelAndView("admin/users/addOrEdit", model);
		}
		model.addAttribute("message", "User is not exisedt");
		return new ModelAndView("forward:/admin/users/addOrEdit", model);
	}
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> saveFile(@PathVariable String filename) {
		Resource file = storageService.LoadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("product") UserDTO dto, BindingResult result) {
		if (result.hasErrors()) {
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
}
















