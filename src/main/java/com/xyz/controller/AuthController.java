package com.xyz.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.Exception.UserException;
import com.xyz.Response.AuthResponse;
import com.xyz.config.JwtProvider;
import com.xyz.models.User;
import com.xyz.models.Verfification;
import com.xyz.repository.UserRepository;
import com.xyz.service.CustomUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private JwtProvider jwtProvider; 
	
	@Autowired
	private CustomUserDetailService customUserDetailService; 
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signUpUser(@RequestBody User user) throws UserException{
		String email = user.getEmail();
		String password = user.getPassword();
		String fullname = user.getFullname();
		String birthDate = user.getBirthDate();
		
		User existUser = userRepository.findByEmail(email);
		
		if(existUser != null) {
			throw new UserException("Email alredy Registered ...");
		}
		
		User createdUser = new User();
		createdUser.setFullname(fullname);
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setBirthDate(birthDate);
		
		createdUser.setVerfification(new Verfification());
		createdUser.setCreatedAt(LocalDateTime.now());
		
	    User savedUser = userRepository.save(createdUser);
	    Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);   
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    	
	    String jwt = jwtProvider.generateToken(authentication);

	    
	    return new ResponseEntity<AuthResponse>(new AuthResponse(jwt, true),HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody User user){
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		Authentication authentication = authenticate(email,password);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
		    
		String jwt = jwtProvider.generateToken(authentication);
		return new ResponseEntity<AuthResponse>(new AuthResponse(jwt, true),HttpStatus.ACCEPTED);
	}
	
	public Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
	   
		if (userDetails==null) {
			throw new BadCredentialsException("invalid username...");
		}
		
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Incorrect password...");
		}
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	
		return authentication;
	}
	
	

}
