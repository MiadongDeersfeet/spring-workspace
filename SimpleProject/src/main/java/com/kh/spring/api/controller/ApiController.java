package com.kh.spring.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.api.model.service.ApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="api", produces="application/json; charset=UTF-8")
public class ApiController {

	private final ApiService apiService;
	
	@GetMapping("beef")
	public String getBeef() {
		
		return apiService.requestBeef();
		//AjaxResponse ar = new AjaxResponse();
		//ar.setCode("200");
		//ar.setMessage("안심식당 받아라~");
	}

	@GetMapping("blog")
	public String getBlog(@RequestParam(name="query") String query) {
		
		return apiService.requestBlog(query);
	}
	
	@GetMapping("busan")
	public String getBusan(@RequestParam(name="pageNo") int pageNo) {
		return apiService.requestBusan(pageNo);
	}
	
	@GetMapping("busan/detail/{num}")
	public String getBusanDetail(@PathVariable(name="num") int num) {
		return apiService.requestBusanDetail(num);
	}
}
