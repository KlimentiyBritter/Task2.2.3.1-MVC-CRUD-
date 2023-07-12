package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String getAllUsers(Model model){
		List<User> allUsers = userService.readAllUsers();
		model.addAttribute("all", allUsers);
		return "all-users";
	}
	@GetMapping("/addNewUser")
	public String newUser(Model model){
		User user = new User();
		model.addAttribute("newUser", user);
		return "user-info";
	}
	@PostMapping("/saveUser")
	public String create(@ModelAttribute("newUser") User user){
		userService.create(user);
		return "redirect:/";
	}
	@GetMapping("/updateInfo")
	public String updateUser(@RequestParam("userID") int id, Model model ){
		User user = userService.update(id);
		model.addAttribute("newUser", user);
		return "user-info";
	}
	//deleteUser
	@GetMapping ("/deleteUser")
	public String deleteUser(@RequestParam("userID") int id){
		userService.delete(id);
		return "redirect:/";
	}
}