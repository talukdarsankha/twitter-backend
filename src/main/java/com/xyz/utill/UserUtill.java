package com.xyz.utill;

import com.xyz.models.User;

public class UserUtill {
	
	public static final boolean isReqUser(User reqUser, User user) {
		if (user.getId()==reqUser.getId()) {
			return true;
		}
		return  false;
		
	}
	
	public static final boolean isFollwedByReqUser(User reqUser, User user) {
		if (reqUser.getFollowings().contains(user)) {
			return true;
		}
		return  false;
		
	}

}
