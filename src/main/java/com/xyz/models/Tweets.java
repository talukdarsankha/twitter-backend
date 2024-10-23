package com.xyz.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private User user;
	
	private String content;
	
	private String image;
	private String video;
	
	@OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
	private List<TweetLike> likes = new ArrayList<>();
	
	@OneToMany
	private List<Tweets> replyTweets = new ArrayList<>();
	
	@ManyToMany
	private List<User> reTweetUsers=new ArrayList<>();
	
	
	private boolean isTweet;
	
	private boolean isReplyTweet;
	
	@ManyToOne(cascade = CascadeType.REMOVE)  // Removes replie when the parent tweet is deleted
	private Tweets replyFor;
	
	private LocalDateTime createdAt;
	
	
	

	
	
	
			

}
