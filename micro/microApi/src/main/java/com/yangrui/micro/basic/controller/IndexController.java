package com.yangrui.micro.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index1");
	}
	/*@GetMapping("/main")
	public ModelAndView main() {
		return new ModelAndView("mainx");
	}*/
}
