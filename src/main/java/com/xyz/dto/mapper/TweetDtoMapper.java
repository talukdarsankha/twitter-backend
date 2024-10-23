package com.xyz.dto.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xyz.dto.TweetDto;
import com.xyz.dto.UserDto;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.utill.TweetUtill;

public class TweetDtoMapper {
	
	public static TweetDto toTweetDto(Tweets tweet, User reqUser) {
		
		 UserDto userDto = UserDtoMapper.toUserDto(tweet.getUser(),reqUser);
		 
		 boolean isLiked = TweetUtill.isLikedByReqUser(reqUser, tweet);
		 
		 boolean isReTweeted = TweetUtill.isReTweetByReqUser(reqUser, tweet);
		 
		 List<Long> retwitUsersId = new ArrayList<>();
		 
		 for(User u: tweet.getReTweetUsers()) {
			 retwitUsersId.add(u.getId());
		 }
		 
		 TweetDto tweetDto = new TweetDto();
		 tweetDto.setId(tweet.getId());
		 tweetDto.setContent(tweet.getContent());
		 tweetDto.setCreatedAt(tweet.getCreatedAt());
		 tweetDto.setImage(tweet.getImage());
		 tweetDto.setVideo(tweet.getVideo());
		 tweetDto.setTotalLikes(tweet.getLikes().size());
		 tweetDto.setTotalReplies(tweet.getReplyTweets().size());
		 tweetDto.setTotalRetweets(tweet.getReTweetUsers().size());
		 
		 tweetDto.setUser(userDto);
		 tweetDto.setLiked(isLiked);
		 tweetDto.setRetwit(isReTweeted);
		 tweetDto.setRetwitUsersId(retwitUsersId);
		 tweetDto.setReplyTweets(convertToTweetDtoList(tweet.getReplyTweets(), reqUser));
		 
		 return tweetDto;
		 
	}
	
	public static List<TweetDto> convertToTweetDtoList(List<Tweets> tweets, User reqUser ) {
		List<TweetDto> tweetDtos = new ArrayList<>();
		
		for(Tweets tweet: tweets) {
			TweetDto tweetDto = toReplyTweetDto(tweet,reqUser);
			tweetDtos.add(tweetDto);
		}
		return tweetDtos;
	}

	private static TweetDto toReplyTweetDto(Tweets tweet, User reqUser) {
		// TODO Auto-generated method stub
         
		 UserDto userDto = UserDtoMapper.toUserDto(tweet.getUser(),reqUser);
		 
		 boolean isLiked = TweetUtill.isLikedByReqUser(reqUser, tweet);
		 
		 boolean isReTweeted = TweetUtill.isReTweetByReqUser(reqUser, tweet);
		 
		 List<Long> retwitUsersId = new ArrayList<>();
		 
		 for(User u: tweet.getReTweetUsers()) {
			 retwitUsersId.add(u.getId());
		 }
		 
		 TweetDto tweetDto = new TweetDto();
		 tweetDto.setId(tweet.getId());
		 tweetDto.setContent(tweet.getContent());
		 tweetDto.setCreatedAt(tweet.getCreatedAt());
		 tweetDto.setImage(tweet.getImage());
		 tweetDto.setVideo(tweet.getVideo());
		 tweetDto.setTotalLikes(tweet.getLikes().size());
		 tweetDto.setTotalReplies(tweet.getReplyTweets().size());
		 tweetDto.setTotalRetweets(tweet.getReTweetUsers().size());
		 
		 tweetDto.setUser(userDto);
		 tweetDto.setLiked(isLiked);
		 tweetDto.setRetwit(isReTweeted);
		 tweetDto.setRetwitUsersId(retwitUsersId);
		return tweetDto;
		 
	}
	
	

}
