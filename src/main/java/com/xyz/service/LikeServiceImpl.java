package com.xyz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.Exception.TweetException;
import com.xyz.models.TweetLike;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.repository.TweetLikeRepository;
import com.xyz.repository.TweetRepository;

@Service
public class LikeServiceImpl implements LikeService{
	
	@Autowired
	private TweetLikeRepository tweetLikeRepository;
	
	@Autowired
	private TweetService tweetService;
	
	@Autowired
	private TweetRepository tweetRepository;

	@Override
	public TweetLike likeTweet(Long tweetId, User user) throws TweetException {
		// TODO Auto-generated method stub
		TweetLike isLikeExist = tweetLikeRepository.isLikeExist(tweetId, user.getId());
		if (isLikeExist!=null) {
			tweetLikeRepository.deleteById(isLikeExist.getId());
			return isLikeExist;
		}
		
	     Tweets tweet = tweetService.findById(tweetId);
	     
	     TweetLike like = new TweetLike();
	     like.setTweet(tweet);
	     like.setUser(user);
	     
	     TweetLike savedLike = tweetLikeRepository.save(like);
	     
	     tweet.getLikes().add(savedLike);
	     tweetRepository.save(tweet);
	     
	     return savedLike;
	}

	@Override
	public List<TweetLike> getTweetAllLikes(Long tweetId) {
		
		List<TweetLike> likes = tweetLikeRepository.findLikesByTweetId(tweetId);
		return likes;
	}

}
