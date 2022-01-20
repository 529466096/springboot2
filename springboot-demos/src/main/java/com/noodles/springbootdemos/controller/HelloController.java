package com.noodles.springbootdemos.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	@RequestMapping("/sayHello")
	public String sayHello(@RequestBody String msg){
		return "Hello1, " + msg;
	}

}
