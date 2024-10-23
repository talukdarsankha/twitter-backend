package com.xyz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.Exception.UserException;
import com.xyz.config.JwtProvider;
import com.xyz.models.User;
import com.xyz.repository.TweetRepository;
import com.xyz.repository.UserRepository;

@Service
public class UserServiceImpl  implements UserService {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws UserException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()-> new UserException("User not found with this id :"+ userId));   
		return user;
	}

	@Override
	public User findUserByJwt(String jwt) throws UserException {
		// TODO Auto-generated method stub
		String email = jwtProvider.getEmailFromToken(jwt);
		User user = userRepository.findByEmail(email);
		
		if (user==null) {
			throw new UserException("User Not Found with this email :"+email);
		}
		return user;
	}

	@Override
	public User updateUser(Long UserId, User updateUser) throws UserException {
		// TODO Auto-generated method stub
		User existUser = findUserById(UserId);
		
		if (updateUser.getFullname()!=null) {
			existUser.setFullname(updateUser.getFullname());
		}
		if (updateUser.getImage()!=null) {
			existUser.setImage(updateUser.getImage());
		}
		if (updateUser.getBackgroundImage()!=null) {
			existUser.setBackgroundImage(updateUser.getBackgroundImage());
		}
		if (updateUser.getBio()!=null) {
			existUser.setBio(updateUser.getBio());
		}
		if (updateUser.getBirthDate()!=null) {
			existUser.setBirthDate(updateUser.getBirthDate());
		}
		if (updateUser.getWebsite()!=null) {
			existUser.setWebsite(updateUser.getWebsite());
		}
		if (updateUser.getLocation()!=null) {
			existUser.setLocation(updateUser.getLocation());
		}
		if (updateUser.getMobile()!=null) {
			existUser.setMobile(updateUser.getMobile());
		}
		return userRepository.save(existUser);
	}

	@Override
	public User followUser(Long UserId, User followUser) throws UserException {
		// TODO Auto-generated method stub
		User reqUser = findUserById(UserId);
		
		if (reqUser.getFollowings().contains(followUser) && followUser.getFollowers().contains(reqUser)) {
			reqUser.getFollowings().remove(followUser);
			followUser.getFollowers().remove(reqUser);
		}else {
			reqUser.getFollowings().add(followUser);
			followUser.getFollowers().add(reqUser);
		}
		userRepository.save(reqUser);
		userRepository.save(followUser);
		return reqUser;
	}

	@Override
	public List<User> searchUser(String query) {
		// TODO Auto-generated method stub
		return userRepository.searchUser(query);
	}

}
