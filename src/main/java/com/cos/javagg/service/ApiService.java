package com.cos.javagg.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import com.cos.javagg.model.api.ApiEntry;
import com.cos.javagg.model.api.ApiMatch;
import com.cos.javagg.model.api.ApiMatchEntry;
import com.cos.javagg.model.api.ApiRanking;
import com.cos.javagg.model.api.ApiSummoner;

import com.cos.javagg.model.detail.Match;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ApiService {
	
	//이거 소환사 모스트 뽑는거
	//https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/"+encid+"?api_key="+api_key

	@Transactional
	public ApiSummoner getApiSummoner(String name, String apiKey) {

		try {
			// 소환사 정보
			System.out.println("소환사정보 가져오기 시작");
			System.out.println(name);

			URL url = new URL(
					"https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + name + "?api_key=" + apiKey);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			System.out.println("HttpURLConnection con = (HttpURLConnection) url.openConnection();");

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			System.out.println(
					"BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), \"UTF-8\"));");

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			System.out.println("StringBuilder sb = new StringBuilder();");

			Gson gson = new Gson();

			System.out.println("Gson gson = new Gson();");

			ApiSummoner apiSummoner = gson.fromJson(sb.toString(), ApiSummoner.class);

			System.out.println("apiSummoner 낫널");
			if (apiSummoner == null) {
				System.out.println("apiSummoner == null");
				return null;

			}
			System.out.println(apiSummoner);

			return apiSummoner;

		} catch (Exception e) {

			System.out.println("getApiSummoner 소환사정보를 가져오지 못했습니다.");

		}

		return null;
	}

	@Transactional
	public ApiMatchEntry getApiMatchEntry(ApiSummoner dto, String apiKey) {
		// 매치엔트리 가져오기
		try {
			URL url2 = new URL("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/" + dto.getAccountId()
					+ "?api_key=" + apiKey);

			HttpURLConnection con = (HttpURLConnection) url2.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			Gson gson = new Gson();

			ApiMatchEntry apiMatchEntry = gson.fromJson(sb.toString(), ApiMatchEntry.class);

			if (apiMatchEntry == null) {
				return null;
			}

			// 게임 id값
			for (Match match : apiMatchEntry.getMatches()) {
				List<Long> matchIdList = new ArrayList<>();
				matchIdList.add(match.getGameId());
			}

			return apiMatchEntry;

		} catch (Exception e) {

			System.out.println("매치엔트리를 가져오지 못했습니다.");

		}

		return null;
	}

	@Transactional
	public ApiMatch getApiMatch(long matchId, String apiKey) {

		try {
			// 매치 가져오기

			URL url3 = new URL("https://kr.api.riotgames.com/lol/match/v4/matches/" + matchId + "?api_key=" + apiKey);

			HttpURLConnection con = (HttpURLConnection) url3.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			Gson gson = new Gson();

			// 매치 가져오기

			ApiMatch apiMatch = gson.fromJson(sb.toString(), ApiMatch.class);

			if (apiMatch == null) {
				return null;
			}

			return apiMatch;

		} catch (Exception e) {
			System.out.println("경기를 가져오지 못했습니다.");
		}

		return null;

	}

	@Transactional
	public List<ApiEntry> getApiEntry(String userid, String apiKey) {

		try {
			// 엔트리 가져오기

			URL url4 = new URL(
					"https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + userid + "?api_key=" + apiKey);

			HttpURLConnection con = (HttpURLConnection) url4.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			Gson gson = new Gson();

			Type listType = new TypeToken<ArrayList<ApiEntry>>() {
			}.getType();

			List<ApiEntry> apiEntries = gson.fromJson(sb.toString(), listType);

			if (apiEntries == null) {
				return null;
			}

			return apiEntries;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;

	}

	@Transactional
	public ApiRanking getApiRanking(String key) {
		
		try {
			// 엔트리 가져오기

			URL url5 = new URL(
					"https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5?api_key="+key);

			HttpURLConnection con = (HttpURLConnection) url5.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			Gson gson = new Gson();

			ApiRanking apiRanking = gson.fromJson(sb.toString(), ApiRanking.class);

			System.out.println("apiSummoner 낫널");
			if (apiRanking == null) {
				System.out.println("apiSummoner == null");
				return null;

			}
			System.out.println(apiRanking);

			return apiRanking;

		} catch (Exception e) {

			System.out.println("getApiSummoner 소환사정보를 가져오지 못했습니다.");

		}

		return null;
	}
	
	@Transactional
	public ApiSummoner getApiRankSummoner(String name, String apiKey) {

		try {
			// 소환사 정보
			System.out.println("소환사정보 가져오기 시작");
			System.out.println(name);

			URL url = new URL(
					"https://kr.api.riotgames.com/lol/summoner/v4/summoners/"+name+"?api_key="+apiKey);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			System.out.println("HttpURLConnection con = (HttpURLConnection) url.openConnection();");

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			System.out.println(
					"BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), \"UTF-8\"));");

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			System.out.println("StringBuilder sb = new StringBuilder();");

			Gson gson = new Gson();

			System.out.println("Gson gson = new Gson();");

			ApiSummoner apiSummoner = gson.fromJson(sb.toString(), ApiSummoner.class);

			System.out.println("apiSummoner 낫널");
			if (apiSummoner == null) {
				System.out.println("apiSummoner == null");
				return null;

			}
			System.out.println(apiSummoner);

			return apiSummoner;

		} catch (Exception e) {

			System.out.println("getApiSummoner 소환사정보를 가져오지 못했습니다.");

		}

		return null;
	}

}
