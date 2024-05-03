package org.pravaha.bpmn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testController")
public class TestController {
	
	@GetMapping(value = "/getString")
	public void test() {
		System.out.println("In BpmnController ..........");
	}
}
