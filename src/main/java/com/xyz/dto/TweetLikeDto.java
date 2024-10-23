package com.xyz.dto;

import com.xyz.models.Tweets;
import com.xyz.models.User;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class TweetLikeDto {
	
	
    private long id;
	
	private UserDto user;
	
    private TweetDto tweet;
	

}
