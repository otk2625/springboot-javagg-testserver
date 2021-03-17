package com.cos.javagg.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.cos.javagg.model.ApiSummoner;
import com.google.gson.Gson;

@Service
public class testService {

	//@Transactional
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
			
			System.out.println("BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), \"UTF-8\"));");

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
