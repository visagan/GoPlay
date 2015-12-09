package com.goplay.api.servcies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.goplay.api.models.UserLogin;
import com.goplay.api.models.UserLoginDao;

@Service
public class RegisterUserService {

	@Autowired
	private UserLoginDao userRepo;
	
	public UserLogin registerUser(UserLogin registerUser) {
		
		System.out.println("Password : " + new BCryptPasswordEncoder().encode(registerUser.getPassword()));
		
        registerUser.setPassword(new BCryptPasswordEncoder().encode(registerUser.getPassword()));
        System.out.println("Password : " +registerUser.getPassword());
        userRepo.save(registerUser);
        return registerUser;
    }

	public Boolean matchPassword(String password1, String password2) {
		
		return BCrypt.checkpw(password1, password2);
		
		//return password1.matches(password2);
	}
}
