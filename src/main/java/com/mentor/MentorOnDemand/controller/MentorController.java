package com.mentor.MentorOnDemand.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mentor.MentorOnDemand.dao.MentorDao;
import com.mentor.MentorOnDemand.model.Mentor;
import com.mentor.MentorOnDemand.service.MentorService;



@Controller
public class MentorController {
	@Autowired
	private MentorService mentorService;
	@Autowired
	private MentorDao mentorDao;
	
	
	@RequestMapping(value="/mentorsignup", method= RequestMethod.GET)
	public String getUserForm(ModelMap model) {
		Mentor mentor=new Mentor();
		model.addAttribute("mentor",mentor);
		return "MentorSignup";
		
	}
	
	@RequestMapping(value="/mentorsignup", method= RequestMethod.POST)
	public String formHandler(@Valid Mentor mentor,ModelMap model)throws SQLException{
		mentorService.insertMentor(mentor);
		return "redirect:/mentorlogin";
		
	}
	
	@RequestMapping(value="/mentorlogin", method= RequestMethod.GET)
	public String Login(ModelMap model) {
		Mentor mentor=new Mentor();
		model.addAttribute("mentorlogin",mentor);
		return "MentorLogin";
		
	}

	@RequestMapping(value= "/mentorlogin", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute("mentorlogin") Mentor mentor) throws Exception {
		ModelAndView model = null;
		String name = mentor.getEmail();
		List<Mentor> mentor1 = mentorService.findByemail(name);
		if(mentor1.isEmpty())
		{
			model = new ModelAndView("MentorLogin", "message", "Invalid Username or Password");
		}else{
		Mentor mentor2 = mentor1.get(0);
		if ((mentor.getEmail().equals(mentor2.getEmail())) && (mentor.getPassword().equals(mentor2.getPassword()))) {
			model = new ModelAndView("userLanding");
			} else {
				model = new ModelAndView("MentorLogin", "message", "Invalid Username or Password");
			}
		}
		return model;

    }
	
	@RequestMapping(value="/search", method= RequestMethod.GET)
	public String Search(ModelMap model) {
		Mentor mentor=new Mentor();
		model.addAttribute("search",mentor);
		return "searchTechnology";
		
	}
	
	@RequestMapping(value="/search", method= RequestMethod.POST)
	public ModelAndView searchTechnology(Mentor mentor,ModelMap modelmap)throws SQLException{
		ModelAndView model=new ModelAndView();
		model.setViewName("TechnologyList");
		model.addObject("technologyList", mentorDao.findBytechnology(mentor.getTechnology()));
		return model;
		
	}
	
	@RequestMapping(path="/mentorList")
	public ModelAndView mentorList()throws Exception{
		ModelAndView model=new ModelAndView();
		model.setViewName("MentorList");
		model.addObject("mentorList",mentorService.mentorList());
		return model;
		
	}
	
}
