package com.xyz.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.xyz.dto.TweetDto;
import com.xyz.dto.TweetLikeDto;
import com.xyz.dto.UserDto;
import com.xyz.models.TweetLike;
import com.xyz.models.User;

public class TweetLikeDtoMapper {
	
	public static final TweetLikeDto toTweetLikeDto(TweetLike tweetLike, User reqUser) {
		UserDto userDto = UserDtoMapper.toUserDto(tweetLike.getUser(),reqUser);

		TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweetLike.getTweet(),reqUser);
		
		TweetLikeDto tweetLikeDto = new TweetLikeDto();
		tweetLikeDto.setId(tweetLike.getId());
		tweetLikeDto.setTweet(tweetDto);
		tweetLikeDto.setUser(userDto);
		
		return tweetLikeDto;
	}
	
	public static final List<TweetLikeDto> toTweetLikeDtoList(List<TweetLike> tweetLikes, User reqUser) {
		
		List<TweetLikeDto> tweetLikeDtoLists = new ArrayList<>();
		
		for(TweetLike tweetLike: tweetLikes) {
//			UserDto userDto = UserDtoMapper.toUserDto(tweetLike.getUser());
//			TweetDto tweetDto = TweetDtoMapper.toTweetDto(tweetLike.getTweet(),reqUser);
//			
//			TweetLikeDto tweetLikeDto = new TweetLikeDto();
//			tweetLikeDto.setId(tweetLike.getId());
//			tweetLikeDto.setTweet(tweetDto);
//			tweetLikeDto.setUser(userDto);
			
			TweetLikeDto tweetLikeDto  = toTweetLikeDto(tweetLike, reqUser);
			tweetLikeDtoLists.add(tweetLikeDto);
			
		}
		
		return tweetLikeDtoLists;
	}
	
	

}
