package com.xyz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xyz.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
	@Query("SELECT DISTINCT u from User u WHERE u.fullname LIKE %:query% ")
	public List<User> searchUser(String query);

}
