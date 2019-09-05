package com.mentor.MentorOnDemand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mentor.MentorOnDemand.model.Mentor;


public interface MentorDao extends JpaRepository<Mentor,Integer>{
	public List<Mentor> findByemail(String email);
	@Query("select c from Mentor c where c.technology LIKE %:letter%")
	public List<Mentor> findBytechnology(@Param(value="letter")String letter);
	public List<Mentor> findAll();
}
