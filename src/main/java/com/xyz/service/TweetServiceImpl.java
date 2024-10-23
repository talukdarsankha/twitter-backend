package com.xyz.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.Exception.TweetException;
import com.xyz.Exception.UserException;
import com.xyz.models.Tweets;
import com.xyz.models.User;
import com.xyz.repository.TweetRepository;
import com.xyz.repository.UserRepository;
import com.xyz.request.TweetReplyRequest;

@Service
public class TweetServiceImpl implements TweetService {
	
	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private UserRepository userRepository;


	@Override
	public Tweets createTweet(Tweets req, User user) {
		// TODO Auto-generated method stub
		
		Tweets tweets = new Tweets();
		tweets.setContent(req.getContent());
		tweets.setImage(req.getImage());
		tweets.setVideo(req.getVideo());
		tweets.setCreatedAt(LocalDateTime.now());
		tweets.setUser(user);  
		
		tweets.setReplyTweet(false);
		tweets.setTweet(true);
	
		return tweetRepository.save(tweets);
	}

	@Override
	public List<Tweets> findAllTweet() {
		// TODO Auto-generated method stub
		return tweetRepository.findAllByIsTweetTrueOrderByCreatedAtDesc();
	}

	@Override
	public Tweets createReTweet(Long tweetId, User user) throws TweetException {
		// TODO Auto-generated method stub
		Tweets tweet = findById(tweetId);
		if (tweet.getReTweetUsers().contains(user)) {
			tweet.getReTweetUsers().remove(user);
		}else {
			tweet.getReTweetUsers().add(user);
		}
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweets findById(Long tweetId) throws TweetException {
		// TODO Auto-generated method stub
		Tweets tweet = tweetRepository.findById(tweetId).orElseThrow(()->new  TweetException("Tweet Not Found With this id :"+tweetId));
		return tweet;
	}

	@Override
	public void deleteTweetById(Long tweetId, Long userId) throws TweetException, UserException {
		// TODO Auto-generated method stub
		Tweets tweet = findById(tweetId);
		if (!userId.equals(tweet.getUser().getId())) {
			throw new UserException("You can't delete another user tweet...");
		}
		// Delete all replies of the tweet
	    tweetRepository.deleteAll(tweet.getReplyTweets());
	    
		tweetRepository.delete(tweet);
		
	}



	@Override
	public Tweets createReplyTweet(TweetReplyRequest tweetReplyRequest, User user) throws TweetException {
		// TODO Auto-generated method stub
		
		Tweets replyTweetFor = findById(tweetReplyRequest.getTweetId());
		
		Tweets replyTweets = new Tweets();
		replyTweets.setContent(tweetReplyRequest.getContent());
		replyTweets.setImage(tweetReplyRequest.getImage());
		replyTweets.setVideo(tweetReplyRequest.getVideo());
		replyTweets.setCreatedAt(LocalDateTime.now());
		replyTweets.setUser(user);  
		
		replyTweets.setReplyTweet(true);
		replyTweets.setTweet(false);
		
		replyTweets.setReplyFor(replyTweetFor);
		Tweets saveReply = tweetRepository.save(replyTweets);
		
		replyTweetFor.getReplyTweets().add(saveReply);
		tweetRepository.save(replyTweetFor);
		return replyTweetFor;
	}

	@Override
	public List<Tweets> getUserAllTweet(User user) {
		// TODO Auto-generated method stub
		return tweetRepository.findByReTweetUsersContainsOrUser_idAndIsTweetTrueOrderByCreatedAtDesc(user, user.getId());
	}

	@Override
	public List<Tweets> findUserLikeTweets(User user) {
		// TODO Auto-generated method stub
		return tweetRepository.findByLikesUser_id(user.getId());
	}

	@Override
	public List<Tweets> findUserCreatedReplies(User user) {
		// TODO Auto-generated method stub
		return tweetRepository.findAllByUser_idAndIsReplyTweetTrueOrderByCreatedAtDesc(user.getId());
	}

}
