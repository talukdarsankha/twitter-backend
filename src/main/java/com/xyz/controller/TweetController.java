package com.xyz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.Exception.TweetException;
import com.xyz.Exception.UserException;
import com.xyz.Response.ApiResponse;
import com.xyz.dto.TweetDto;
import com.xyz.dto.mapper.TweetDtoMapper;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.repository.TweetRepository;
import com.xyz.repository.UserRepository;
import com.xyz.request.TweetReplyRequest;
import com.xyz.service.TweetService;
import com.xyz.service.UserService;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {
	
	@Autowired
	private TweetService tweetService; 
	
	@Autowired
	private UserService userService; 
	
	@PostMapping("/create")
	public ResponseEntity<TweetDto> createTweet(@RequestBody Tweets tweetReq,@RequestHeader("Authorization") String jwt) throws UserException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		Tweets tweet = tweetService.createTweet(tweetReq, reqUser);
		
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, reqUser);
		return new ResponseEntity<TweetDto>(tweetDto,HttpStatus.CREATED);
		
		
	}
	
	@PostMapping("/reply")
	public ResponseEntity<TweetDto> replyTweet(@RequestBody TweetReplyRequest replyRequest,@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		Tweets replyTweet = tweetService.createReplyTweet(replyRequest, reqUser);
		
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(replyTweet, reqUser);
		return new ResponseEntity<TweetDto>(tweetDto,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/{tweetId}/reTweet")
	public ResponseEntity<TweetDto> reTweet(@PathVariable("tweetId") Long tweetId,@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		Tweets reTweet = tweetService.createReTweet(tweetId, reqUser);
		
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(reTweet, reqUser);
		return new ResponseEntity<TweetDto>(tweetDto,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{tweetId}")
	public ResponseEntity<TweetDto> findTweetById(@PathVariable("tweetId") Long tweetId,@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		Tweets tweet = tweetService.findById(tweetId);
		
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweet, reqUser);
		return new ResponseEntity<TweetDto>(tweetDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{tweetId}")
	public ResponseEntity<ApiResponse> deleteTweet(@PathVariable("tweetId") Long tweetId,@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		tweetService.deleteTweetById(tweetId, reqUser.getId());
		
		
		ApiResponse apiResponse = new ApiResponse("Tweet Deleted successfully...", true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TweetDto>> findAllTweet(@RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		List<Tweets> tweets = tweetService.findAllTweet();
		
		List<TweetDto> tweetDtos = TweetDtoMapper.convertToTweetDtoList(tweets, reqUser);
		return new ResponseEntity<List<TweetDto>>(tweetDtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<TweetDto>> findUserAllTweet(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		List<Tweets> tweets = tweetService.getUserAllTweet(reqUser);
		
		List<TweetDto> tweetDtos = TweetDtoMapper.convertToTweetDtoList(tweets, reqUser);
		return new ResponseEntity<List<TweetDto>>(tweetDtos,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/user/{userId}/likes")
	public ResponseEntity<List<TweetDto>> findUserAllLikeTweets(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		List<Tweets> tweets = tweetService.findUserLikeTweets(reqUser);
		
		List<TweetDto> tweetDtos = TweetDtoMapper.convertToTweetDtoList(tweets, reqUser);
		return new ResponseEntity<List<TweetDto>>(tweetDtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}/reply")
	public ResponseEntity<List<TweetDto>> findUserCreatedAllReplies(@PathVariable("userId") Long userId, @RequestHeader("Authorization") String jwt) throws UserException, TweetException{    
		
		User reqUser = userService.findUserByJwt(jwt);
		List<Tweets> tweets = tweetService.findUserCreatedReplies(reqUser);
		
		List<TweetDto> tweetDtos = TweetDtoMapper.convertToTweetDtoList(tweets, reqUser);
		return new ResponseEntity<List<TweetDto>>(tweetDtos,HttpStatus.OK);
		
	}

}
