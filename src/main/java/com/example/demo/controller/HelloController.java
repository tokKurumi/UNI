package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String Hello() {
		return "Hello world!";
	}

	@GetMapping("/helloClient")
	public String getHelloMessage(@RequestParam String name) {
		return "Hello, " + name;
	}
}
