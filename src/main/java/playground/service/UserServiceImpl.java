//package playground.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import playground.entity.User;
//import playground.repository.UserRepository;
//import playground.response.Response;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//	private final UserRepository userRepository;
//
//	public UserServiceImpl(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	public List<User> getAllUsers() {
//		return userRepository.findAll();
//	}
//
//	@Override
//	public ResponseEntity<?> getUserById(int userId) {
//		Optional<User> optionalUser = userRepository.findById(Integer.toString(userId));
//
//		if (optionalUser.isEmpty()) {
//			Response errorResponse = new Response("User has not been found", HttpStatus.NOT_FOUND.value());
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//		}
//		// User found, return the user
//		return ResponseEntity.ok(optionalUser.get());
//	}
//
//	@Override
//	public void saveUser(User user) {
//		userRepository.save(user);
//	}
//
//	@Override
//	public void deleteUserById(int userId) {
//		userRepository.deleteById(Integer.toString(userId));
//	}
//
//	@Override
//    public ResponseEntity<?> updateUserInformation(int userId, String username) {
//        ResponseEntity<?> getUserExists = getUserById(userId);
//
//        if (getUserExists.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
//	        Response errorResponse = new Response("User has not been found, so cannot be updated.", HttpStatus.NOT_FOUND.value());
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//        }
//
//        userRepository.updateUserById(userId, username);
//
//		Response response = new Response("User updated succesfully to "+ username, HttpStatus.OK.value());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    
//	}
//}
//
