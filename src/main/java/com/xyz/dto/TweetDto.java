package com.xyz.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.xyz.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class TweetDto {
	
	private Long id;
	
	private String content;
	
	private String image;
	
	private String video;
	
	private UserDto user;
	
	private LocalDateTime createdAt;
	
	private int totalLikes;
	
	private int totalReplies;
	
	private int totalRetweets;
	
	private boolean isLiked;
	
	private boolean isRetwit;
	
	private List<Long> retwitUsersId;
	
	private List<TweetDto> replyTweets;

}
