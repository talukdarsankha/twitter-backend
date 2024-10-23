package com.xyz.request;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xyz.models.TweetLike;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.models.Verfification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetReplyRequest {

	private Long tweetId;
	
	private String content;
	private String image;
	private String video;
	
	private LocalDateTime createdAt;
	
}
