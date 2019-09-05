package com.mentor.MentorOnDemand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentor.MentorOnDemand.model.User;


public interface UserDao extends JpaRepository<User,Integer>{
	public List<User> findByemail(String email);
	public List<User> findAll();

}
