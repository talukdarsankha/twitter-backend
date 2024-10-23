package com.xyz.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.xyz.models.User;

import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
public class UserDto {
	
    private Long id;
	
	private String fullname;
	
	private String website;
	
	private String location;
	
	private String bio;
	
	private String backgroundImage;
	
	private String image;
	
	private String birthDate;
	
	private String email;
	
	
	private String mobile;
	
	private boolean reqUser;
	
	private boolean loginWithGoogle;
	
	private List<UserDto> followers = new ArrayList<>();
	
	private List<UserDto> followings = new ArrayList<>();
	
	private boolean followed;
	
	private boolean isVerified;
	
	private LocalDateTime createdAt;
}
