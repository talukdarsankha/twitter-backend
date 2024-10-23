package com.xyz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.Exception.TweetException;
import com.xyz.Exception.UserException;
import com.xyz.dto.TweetDto;
import com.xyz.dto.TweetLikeDto;
import com.xyz.dto.mapper.TweetDtoMapper;
import com.xyz.dto.mapper.TweetLikeDtoMapper;
import com.xyz.models.TweetLike;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.service.LikeService;
import com.xyz.service.TweetService;
import com.xyz.service.UserService;

@RestController
@RequestMapping("/api")
public class TweetLikeController {
	
	@Autowired
	private  LikeService likeService; 
	
	@Autowired
	private UserService userService; 
	
	
	@PostMapping("/{tweetId}/like")
	public ResponseEntity<TweetLikeDto> likeTweet(@PathVariable("tweetId") Long tweetId,@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		TweetLike tweetLike = likeService.likeTweet(tweetId, reqUser);
		
		TweetLikeDto tweetLikeDto = TweetLikeDtoMapper.toTweetLikeDto(tweetLike, reqUser);
		return new ResponseEntity<TweetLikeDto>(tweetLikeDto,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("tweet/{tweetId}")
	public ResponseEntity<List<TweetLikeDto>> getTweetAllLikes(@PathVariable("tweetId") Long tweetId,@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		List<TweetLike> tweetLikes  = likeService.getTweetAllLikes(tweetId);
		
		List<TweetLikeDto> tweetLikeDtos = TweetLikeDtoMapper.toTweetLikeDtoList(tweetLikes, reqUser);
		return new ResponseEntity<List<TweetLikeDto>>(tweetLikeDtos,HttpStatus.OK);
		
		
	}
	

}
