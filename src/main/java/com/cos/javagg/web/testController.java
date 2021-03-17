package com.cos.javagg.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.service.testService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class testController{
	private final testService testService;
	
	    //소환사 이름 
		@GetMapping("/info")
		public CMRespDto<?> getByName() {

			return new CMRespDto<>(1, testService.getApiSummoner("태치야치", "RGAPI-a3a5d133-c530-4def-8dc4-1e83d20938d3"));
		}


}

		

