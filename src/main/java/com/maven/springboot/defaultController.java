package com.maven.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.maven.springboot.repositories.MyDataRepository;

@Controller
public class defaultController {
	@Autowired
	MyDataRepository repository;
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		Iterable<MyData> list = repository.findAll();
		mav.addObject("data", list);
		return mav;
	}

}
