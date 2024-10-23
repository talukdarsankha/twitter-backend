package com.xyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xyz.models.TweetLike;

public interface TweetLikeRepository extends JpaRepository<TweetLike, Long> {

	@Query("SELECT l from TweetLike l WHERE l.tweet.id=:tweetId AND l.user.id=:userId")
	public TweetLike isLikeExist(Long tweetId, Long userId);
	
	
	@Query("SELECT l from TweetLike l WHERE l.tweet.id=:tweetId")
	public List<TweetLike> findLikesByTweetId(Long tweetId);
	
}
