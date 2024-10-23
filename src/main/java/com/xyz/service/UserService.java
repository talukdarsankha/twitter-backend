package com.xyz.service;

import java.util.List;

import com.xyz.Exception.UserException;
import com.xyz.models.User;

public interface UserService {
	
	public User findUserById(Long UserId) throws UserException;
	
	public User findUserByJwt(String jwt) throws UserException;
	
    public User updateUser(Long UserId, User updateUser) throws UserException;
	
	public User followUser(Long UserId, User followUser) throws UserException;
	
	public List<User> searchUser(String query);

}
