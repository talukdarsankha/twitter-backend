package com.xyz.dto.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xyz.dto.UserDto;
import com.xyz.models.User;
import com.xyz.utill.UserUtill;

public class UserDtoMapper {
	
	public static UserDto toUserDto(User user, User reqUser) {
		UserDto userDto = new UserDto();
		
		userDto.setId(user.getId());
		userDto.setFullname(user.getFullname());
		userDto.setEmail(user.getEmail());
		userDto.setMobile(user.getMobile());
		userDto.setBackgroundImage(user.getBackgroundImage());
		userDto.setImage(user.getImage());
		userDto.setBio(user.getBio());
		userDto.setBirthDate(user.getBirthDate());
		userDto.setWebsite(user.getWebsite());
		userDto.setLocation(user.getLocation());
		userDto.setFollowers(convertToUserDtoList(user.getFollowers()));
		userDto.setFollowings(convertToUserDtoList(user.getFollowings()));
		
		userDto.setLoginWithGoogle(user.isLoginWithGoogle());
         userDto.setCreatedAt(user.getCreatedAt());
         
     	userDto.setReqUser(UserUtill.isReqUser(reqUser, user));
		userDto.setFollowed(UserUtill.isFollwedByReqUser(reqUser, user));
		
		return userDto;
		
	}
	
	public static List<UserDto> convertToUserDtoList(List<User> users){
		List<UserDto> userDtos = new ArrayList<>();
		
		for(User u: users) {
			UserDto userDto = new UserDto();
			userDto.setId(u.getId());
			userDto.setEmail(u.getEmail());
			userDto.setFullname(u.getFullname());
			userDto.setImage(u.getImage());
			
			userDtos.add(userDto);
		}
		
		return userDtos;
	}
	

}
