package com.example.ec_site;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));

		return org.springframework.security.core.userdetails.User
			.withUsername(user.getEmail())
			.password(user.getPasswords())
			.roles("USER")
			.build();
	}

	public void register(User user, String rawPassword) {
		user.setPasswords(passwordEncoder.encode(rawPassword));
		userRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow();
	}
}