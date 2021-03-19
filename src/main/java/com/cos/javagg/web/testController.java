package com.cos.javagg.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.dto.LoLDto;
import com.cos.javagg.model.api.ApiMatch;
import com.cos.javagg.model.api.ApiMatchEntry;
import com.cos.javagg.model.api.ApiSummoner;
import com.cos.javagg.model.detail.Match;
import com.cos.javagg.service.testService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class testController{
	private final testService testService;
	private final String key = "RGAPI-eaf62d4b-d86c-4959-87cd-ca215e9822ce";	
	private ApiSummoner summoner;
	private ApiMatchEntry apiMatchEntry;
	private List<ApiMatch> apiMatch;
	private List<Long> gameIdes;
	    //소환사 이름 
		@GetMapping("/info")
		public CMRespDto<?> getByName() {
			summoner = testService.getApiSummoner("태치야치", key);
			apiMatchEntry = testService.getApiMatchEntry(summoner, key);
			 
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
			LoLDto dto = new LoLDto(summoner, apiMatchEntry, apiMatch);

			return new CMRespDto<>(1, dto);
		}
		
		//matchs
		@GetMapping("/match")
		public CMRespDto<?> getMatchEntry() {
			
			return new CMRespDto<>(1, testService.getApiMatchEntry(summoner, key));
		}
		
		//match
		@GetMapping("/match/{id}")
		public CMRespDto<?> getMatch(@PathVariable Long id) {
					
			return new CMRespDto<>(1, testService.getApiMatch(id, key));
		}


}

		

