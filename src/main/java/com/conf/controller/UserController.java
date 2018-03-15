package com.conf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.conf.service.UserServiceImpl;

@Controller
@RequestMapping("/hello")
public class UserController {

	@Autowired
	public UserServiceImpl userService;
	
	@RequestMapping("/getusername")
	public String hello(HttpServletRequest request, Model model){
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		model.addAttribute("user", userService.getUser(id));
		return "show";
	}
}
