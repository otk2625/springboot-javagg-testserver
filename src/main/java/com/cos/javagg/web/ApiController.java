package com.cos.javagg.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.dto.LoLDto;
import com.cos.javagg.model.api.ApiEntry;
import com.cos.javagg.model.api.ApiMatch;
import com.cos.javagg.model.api.ApiMatchEntry;
import com.cos.javagg.model.api.ApiSummoner;
import com.cos.javagg.model.detail.Match;
import com.cos.javagg.service.ApiService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class ApiController{
	private final ApiService testService;
	private final String key = "RGAPI-66fa91ef-7c01-498c-9522-7ba97ac30ade";	
	private ApiSummoner summoner;
	private ApiMatchEntry apiMatchEntry;
	private List<ApiMatch> apiMatch;
	private List<Long> gameIdes;
	private List<ApiEntry> apiEntries;
	
	    //소환사 이름 
		@GetMapping("/info/{summonerName}")
		public CMRespDto<?> getByName(@PathVariable String summonerName) {
			summoner = testService.getApiSummoner(summonerName.trim(), key);
			
			if(summoner == null) {
				return new CMRespDto<>(-1, null);
			}
			
			apiMatchEntry = testService.getApiMatchEntry(summoner, key);
			apiEntries = testService.getApiEntry(summoner.getId(), key);
			
			gameIdes = new ArrayList<Long>();
			apiMatch = new ArrayList<>();
			
			//게임id	
			for(int i = 0; i< 20; i++) {
				gameIdes.add(apiMatchEntry.getMatches().get(i).getGameId());
			}
			
			for(int i = 0; i< gameIdes.size(); i++) {
				apiMatch.add(testService.getApiMatch(gameIdes.get(i), key)); 
			}
			
			//소환사 검색시 관련 데이터 전부
			LoLDto dto = new LoLDto(summoner, apiMatchEntry, apiMatch, apiEntries);
			
			

			return new CMRespDto<>(1, dto);
		}
		
		//matchs
		@GetMapping("/testenrty")
		public CMRespDto<?> getEntry() {
			List<ApiEntry> apiEntriess = testService.getApiEntry("6k1xdvcGVP140PTLZVLiJUa97A0lQK0j2b-7VeyowIjq3g", key);
			System.out.println(apiEntriess.get(0));
					
			return new CMRespDto<>(1, apiEntriess);
		}
		
		//match
		@GetMapping("/match/{matchGameId}")
		public CMRespDto<?> getMatchGameId(@PathVariable String  matchGameId) {
					
			return new CMRespDto<>(1, testService.getApiMatch(Long.parseLong(matchGameId), key));
		}
		
		@GetMapping("/perkImage")
		public CMRespDto<?> perkImage(@PathVariable String  matchGameId) {
					
			return new CMRespDto<>(1, testService.getApiMatch(Long.parseLong(matchGameId), key));
		}
		
		


}

		

