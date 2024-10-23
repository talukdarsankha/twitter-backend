package com.xyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xyz.models.Tweets;
import com.xyz.models.User;

public interface TweetRepository extends JpaRepository<Tweets, Long> {

	
	public List<Tweets> findAllByIsTweetTrueOrderByCreatedAtDesc();
	
	public List<Tweets> findByReTweetUsersContainsOrUser_idAndIsTweetTrueOrderByCreatedAtDesc(User user,Long userId);    
	
	public List<Tweets> findByLikesContainingOrderByCreatedAtDesc(User user);
	
	@Query("SELECT t from Tweets t JOIN t.likes l WHERE l.user.id=:userId")
	public List<Tweets> findByLikesUser_id(Long userId);
	
	
	@Query("SELECT t from Tweets t JOIN t.likes l WHERE l.user.id=:userId")
	public List<Tweets> findAllByUser_idAndIsReplyTweetTrueOrderByCreatedAtDesc(Long userId);
}
