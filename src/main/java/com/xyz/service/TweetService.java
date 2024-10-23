package com.xyz.service;

import java.util.List;

import com.xyz.Exception.TweetException;
import com.xyz.Exception.UserException;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.request.TweetReplyRequest;

public interface TweetService {
	
	public Tweets createTweet(Tweets req,User user);
	
	public List<Tweets> findAllTweet();
	
	public Tweets createReTweet(Long tweetId,User user) throws TweetException;
	
	public Tweets findById(Long tweetId) throws TweetException;
	
	public void deleteTweetById(Long tweetId,Long userId) throws TweetException, UserException;
	

	
	public Tweets createReplyTweet(TweetReplyRequest tweetReplyRequest, User user) throws TweetException;  
	
	public List<Tweets> getUserAllTweet(User user);
	
	public List<Tweets> findUserLikeTweets(User user);
	
	public List<Tweets> findUserCreatedReplies(User user);
	
	

}
