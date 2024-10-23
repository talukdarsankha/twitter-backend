package com.xyz.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String fullname;
	
	private String website;
	
	private String location;
	
	private String bio;
	
	private String backgroundImage;
	
	private String image;
	
	private String birthDate;
	
	private String email;
	
	private String password;
	
	private String mobile;
	
	private boolean reqUser;
	
	private boolean loginWithGoogle;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Tweets> tweets = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TweetLike> likes = new ArrayList<>();
	
	@Embedded
	private Verfification verfification;
	
	@ManyToMany
	private List<User> followers = new ArrayList<>();
	
	@ManyToMany
	private List<User> followings = new ArrayList<>();
	
	
	private LocalDateTime createdAt;
	

}
