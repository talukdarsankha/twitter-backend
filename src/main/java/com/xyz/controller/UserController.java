package com.xyz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.Exception.TweetException;
import com.xyz.Exception.UserException;
import com.xyz.dto.TweetDto;
import com.xyz.dto.UserDto;
import com.xyz.dto.mapper.TweetDtoMapper;
import com.xyz.dto.mapper.UserDtoMapper;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.service.TweetService;
import com.xyz.service.UserService;
import com.xyz.utill.UserUtill;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		
		UserDto userDto= UserDtoMapper.toUserDto(reqUser,reqUser);
		
		userDto.setReqUser(true);
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserProfileById(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User user = userService.findUserById(userId);
		
		UserDto userDto= UserDtoMapper.toUserDto(user,reqUser);
		
	
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserDto>> searchUser(@RequestParam("query") String query, @RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		
		List<User> users = userService.searchUser(query);
		
		List<UserDto> userDtos = UserDtoMapper.convertToUserDtoList(users);
		
		
		return new ResponseEntity<List<UserDto> >(userDtos,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDto> updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updateUser = userService.updateUser(reqUser.getId(),user);
		
		UserDto userDto = UserDtoMapper.toUserDto(updateUser,reqUser);
		
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/{userId}/follow")
	public ResponseEntity<UserDto> followUser(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User follwUser = userService.findUserById(userId);
		
		reqUser = userService.followUser(reqUser.getId(),follwUser);
		
		UserDto userDto = UserDtoMapper.toUserDto(reqUser,reqUser);
		userDto.setFollowed(UserUtill.isFollwedByReqUser(reqUser, follwUser));
		
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.ACCEPTED);
		
	}

}
