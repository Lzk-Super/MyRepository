package com.atlzk.atcrowdfunding.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
//	@Autowired
//	MemberService memberService;
	
	@RequestMapping("/reg")
	public String reg() {
		return "member/reg";
	}
}
