//package playground.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import playground.entity.User;
//import playground.service.UserService;
//
//@RestController
//@RequestMapping("users")
//public class UserController {
//
//	private final UserService userService;
//
//	public UserController(UserService userService) {
//		this.userService = userService;
//	}
//
//	@GetMapping
//	Iterable<User> allPeople() {
//		return userService.getAllUsers();
//	}
//
//	@GetMapping("/{id}")
//	ResponseEntity<?> getUserById(@PathVariable String id) {
//		return userService.getUserById(Integer.valueOf(id));
//	}
//
//	@PostMapping("/createUser")
//	User postUser(@RequestBody User User) {
//		userService.saveUser(User);
//		return User;
//	}
//
//	@PutMapping("/updateUsernameForUser/{id}")
//	ResponseEntity<?> putUser(@PathVariable String id, @RequestBody User user) {
//		ResponseEntity<?> response = userService.updateUserInformation(Integer.parseInt(id), user.getUsername());
//		return response;
//	}
//
//	@DeleteMapping("/{id}")
//	void deleteUser(@PathVariable String id) {
//		userService.deleteUserById(Integer.valueOf(id));
//	}
//}
