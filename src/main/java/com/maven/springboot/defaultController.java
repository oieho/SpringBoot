package com.maven.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maven.springboot.repositories.MyDataMongoRepository;
import com.maven.springboot.repositories.MyDataRepository;

@Controller
public class defaultController {

	@Autowired
	MyDataRepository repository;

	@Autowired
	MyDataDaoImpl dao;

	@Autowired
	MyDataService service;

	@Autowired
	MyDataBean myDataBean;

	@Autowired
	MyDataMongoRepository repository2;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyDataMongo의 예제입니다.");
		Iterable<MyDataMongo> list = repository2.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView form(@RequestParam("name") String name, @RequestParam("memo") String memo, ModelAndView mov) {
		MyDataMongo mydata = new MyDataMongo(name, memo);
		repository2.save(mydata);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyData의 예제입니다.");
		mav.addObject("value","");
		List<MyDataMongo> list = repository2.findAll();
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(
			@RequestParam("find") String param,
			ModelAndView mav) {
		mav.setViewName("find");
		if (param == ""){
			mav = new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title","Find result");
			mav.addObject("msg","「" + param + "」의 검색 결과");
			mav.addObject("value",param);
			List<MyDataMongo> list = repository2.findByName(param);
			mav.addObject("datalist", list);
		}
		return mav;
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
//		mav.setViewName("index");
//		mav.addObject("title", "Find Page");
//		mav.addObject("msg", "MyData의 예제입니다.");
////		Iterable<MyData> list = repository.findAllOrderByName(); //dao.getAll();
////		Iterable<MyData> list = dao.findByAge(10, 40);
////		Iterable<MyData> list = repository.findByAge(10, 40);
////		Iterable<MyData> list = dao.getAll();
//		List<MyData> list = service.getAll();
//		mav.addObject("datalist", list);
//		return mav;
//	}

//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	@Transactional(readOnly = false)
//	public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata, BindingResult result,
//			ModelAndView mav) {
//		ModelAndView res = null;
//		if (!result.hasErrors()) {
//			repository.saveAndFlush(mydata);
//			return new ModelAndView("redirect:/");
//		} else {
//			mav.setViewName("index");
//			mav.addObject("msg", "sorry, error is occured...");
//			Iterable<MyData> list = repository.findAll();
//			mav.addObject("datalist", list);
//			res = mav;
//		}
//		return res;
//	}

//	@PostConstruct
//	public void init() {
//		MyData d1 = new MyData();
//		d1.setName("kim");
//		d1.setAge(123);
//		d1.setMail("kim@gilbut.co.kr");
//		d1.setMemo("090999999");
//		repository.saveAndFlush(d1);
//		MyData d2 = new MyData();
//		d2.setName("lee");
//		d2.setAge(15);
//		d2.setMail("lee@flower");
//		d2.setMemo("080888888");
//		repository.saveAndFlush(d2);
//		MyData d3 = new MyData();
//		d3.setName("choi");
//		d3.setAge(37);
//		d3.setMail("choi@happy");
//		d3.setMemo("070777777");
//		repository.saveAndFlush(d3);
//		MyData d4 = new MyData();
//		d4.setName("cdhoi");
//		d4.setAge(37);
//		d4.setMail("chodi@happy");
//		d4.setMemo("0710777777");
//		repository.saveAndFlush(d4);
//	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit mydata.");
		MyData data = repository.findById((long) id);
		mav.addObject("formModel", data);
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute MyData mydata, ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id, ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title", "delete mydata.");
		MyData data = repository.findById((long) id);
		mav.addObject("formModel", data);
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
		repository.delete(id);
		return new ModelAndView("redirect:/");
	}

//	@RequestMapping(value = "/find", method = RequestMethod.GET)
//	public ModelAndView find(ModelAndView mav) {
//		mav.setViewName("find");
//		mav.addObject("title", "Find Page");
//		mav.addObject("msg", "MyData의 예제입니다.");
//		mav.addObject("value", "");
////		Iterable<MyData> list = dao.getAll();
//		List<MyData> list = service.getAll();
//		mav.addObject("datalist", list);
//		return mav;
//	}
//
//	@RequestMapping(value = "/find", method = RequestMethod.POST)
//	public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
//		mav.setViewName("find");
//		String param = request.getParameter("fstr");
//		if (param == "") {
//			mav = new ModelAndView("redirect:/find");
//		} else {
//			mav.addObject("title", "Find result");
//			mav.addObject("msg", "「" + param + "」의 검색 결과");
//			mav.addObject("value", param);
////			List<MyData> list = dao.find(param);
//			List<MyData> list = service.find(param);
//			mav.addObject("datalist", list);
//		}
//		return mav;
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView indexById(@PathVariable long id, ModelAndView mav) {
		mav.setViewName("pickup");
		mav.addObject("title", "Pickup Page");
		String table = "<table>" + myDataBean.getTableTagById(id) + "</table>";
		mav.addObject("msg", "pickup data id = " + id);
		mav.addObject("data", table);
		return mav;
	}

	@RequestMapping(value = "/page/{num}", method = RequestMethod.GET)
	public ModelAndView page(@PathVariable Integer num, ModelAndView mav) {
		Page<MyData> page = service.getMyDataInPage(num);
		mav.setViewName("index");
		mav.addObject("title", "Find Page");
		mav.addObject("msg", "MyData의 에제입니다.");
		mav.addObject("pagenumber", num);
		mav.addObject("datalist", page);
		return mav;
	}

}
