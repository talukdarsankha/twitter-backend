package com.xyz.service;

import java.util.List;

import com.xyz.Exception.TweetException;
import com.xyz.models.TweetLike;
import com.xyz.models.User;

public interface LikeService {
 
	public TweetLike likeTweet(Long tweetId,User user) throws TweetException;
	
	public List<TweetLike> getTweetAllLikes(Long tweetId);
	
}
