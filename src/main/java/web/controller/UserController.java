package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String getAllUsers(Model model){
		List<User> allUsers = userService.readAllUsers();
		model.addAttribute("all", allUsers);
		return "all-users";
	}
	@RequestMapping("/addNewUser")
	public String newUser(Model model){
		User user = new User();
		model.addAttribute("newUser", user);
		return "user-info";
	}
	@RequestMapping("/saveUser")
	public String create(@ModelAttribute("newUser") User user){
		userService.create(user);
		return "redirect:/";
	}
	@RequestMapping("/updateInfo")
	public String updateUser(@RequestParam("userID") int id, Model model ){
		User user = userService.update(id);
		model.addAttribute("newUser", user);
		return "user-info";
	}
	//deleteUser
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("userID") int id){
		userService.delete(id);
		return "redirect:/";
	}
}