package com.sjc.ssoclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLoginController {

	@GetMapping("/testLogin")
	public String Test() {
		
		return "SSO";
	}
}
