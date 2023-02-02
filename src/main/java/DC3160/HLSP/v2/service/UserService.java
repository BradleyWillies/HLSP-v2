package DC3160.HLSP.v2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DC3160.HLSP.v2.model.User;
import DC3160.HLSP.v2.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public Integer getIdForEmail(String email) {
		return userRepository.getIdForEmail(email);
	}
	
	public User add(User user) {
		return userRepository.save(user);
	}

	public Integer validateUserCredentials(String email, String password) {
		return userRepository.getIdForEmailAndPass(email, password);
	}

	public Optional<User> findById(Integer userId) {
		return userRepository.findById(userId);
	}

	public void setDashboardFilter(String filter, int id) {
		User user = userRepository.findById(id).get();
		if (user != null) {
			user.setDashboardFilter(filter);
			userRepository.save(user);
		}
	}
}
