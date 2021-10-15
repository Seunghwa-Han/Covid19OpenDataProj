package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.Covid19HospitalList;
import com.google.gson.Gson;

public class Util {
	//open api uri 에서 모든 데이터를 읽어오기 
	public static Covid19HospitalList getCovid19HospitalInfo(int page, int perpage) {  
		URL url;
		try {
			url = new URL("https://api.odcloud.kr/api/15077586/v1/centers?"
					+ "page="
					+ page
					+ "&perPage="
					+ perpage
					+ "&serviceKey=1O9FiQaVzdapB8TZKdhWNwFfYk9oKZfvLHKW9xDE1GQodHbbu%2FGRbybfzDF%2FX%2BSNuvFajfAvc%2BGkExhDFF%2FT5w%3D%3D");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			BufferedReader br = new BufferedReader( new InputStreamReader(con.getInputStream(), "UTF-8"));
			
			StringBuilder sb = new StringBuilder();
			
			String input = "";
			while((input = br.readLine())!=null) {
				sb.append(input);
			}
			br.close();
			con.disconnect();
			
			Gson gson = new Gson();
			
			Covid19HospitalList hospitalInfo = gson.fromJson(sb.toString(), Covid19HospitalList.class);
			
			return hospitalInfo;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	// 전체 데이터 수 가져오기 
	public static int getTotalCount() {
		return getCovid19HospitalInfo(1,1).getTotalCount();
	}
}
