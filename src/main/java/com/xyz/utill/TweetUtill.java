package com.xyz.utill;

import com.xyz.models.TweetLike;
import com.xyz.models.Tweets;
import com.xyz.models.User;

public class TweetUtill {

	public final static boolean isLikedByReqUser(User reqUser, Tweets tweet) {
		for(TweetLike like: tweet.getLikes()) {
			if (like.getUser().getId()==reqUser.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public final static boolean isReTweetByReqUser(User reqUser, Tweets tweet) {
		for(User user: tweet.getReTweetUsers()) {
			if (user.getId()== reqUser.getId()) {
				return true;
			}
		}
		return false;
	}
	
}
