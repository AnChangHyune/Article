package com.sbs.untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndtaUsrController {
	@RequestMapping("usr/insta/main")
	@ResponseBody
	public String showMian() {
		System.out.println("hi");
		return "Hello";
	}
}
