package com.kh.spring.api.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiService {

	public String requestBeef() {
		
		final String API_KEY = "c00d5bd12bc7abb29509cded3714169e1a73725ce1c2d70c3817f124132b8a12";
		
		// 우리 Service단에서
		// API서버로 요청을 보내고 응답을 받아서 다시 앞단으로 반환
		StringBuilder sb = new StringBuilder();
		sb.append("http://211.237.50.150:7080/openapi/");
		sb.append(API_KEY);
		sb.append("/json/Grid_20200713000000000605_1/1/5");
		
		// 얘를 요청 보낼 때 필요한 또다른 객체를 만듭시다. URI 객체
		try {
			URI uri = new URI(sb.toString());
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.getForObject(uri, String.class);
			return response;
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String requestBlog(String query) {
		String clientId = "ZZmGGRKzCEctN5_w9qHW"; //애플리케이션 클라이언트 아이디
        String clientSecret = "bJLM71twRr"; //애플리케이션 클라이언트 시크릿

        String text = null;
        try {
            text = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&start=2&sort=date";    // JSON 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML 결과
        String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text;
        
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        System.out.println(responseBody);
		return responseBody;
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
	
    public String requestBusan(int pageNo) {
    	final String SERVICE_KEY = "?serviceKey=3dfe4c4b795afc4f27fc90fb7bf558d7b3bce98308d082c9ca2cf230c8ab7d72";
    	StringBuilder sb = new StringBuilder();
    	
    			sb.append("https://apis.data.go.kr/6260000/FoodService/getFoodKr");
    		    sb.append(SERVICE_KEY);
    		    sb.append("&pageNo="+pageNo);
    		    sb.append("&numOfRows=6");
    		    sb.append("&resultType=json");
    		    
    		    URI uri = null;
    		    try {
					uri = new URI(sb.toString());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
    		    String apiResponse = new RestTemplate().getForObject(uri, String.class);
    		    return apiResponse;
    }
    
    public String requestBusanDetail(int num) {
    	final String SERVICE_KEY = "?serviceKey=3dfe4c4b795afc4f27fc90fb7bf558d7b3bce98308d082c9ca2cf230c8ab7d72";
    	StringBuilder sb = new StringBuilder();
    	
    			sb.append("https://apis.data.go.kr/6260000/FoodService/getFoodKr");
    		    sb.append(SERVICE_KEY);
    		    sb.append("&pageNo=1");
    		    sb.append("&numOfRows=1");
    		    sb.append("&resultType=json");
    		    sb.append("&UC_SEQ="+num);
    		    
    		    URI uri = null;
    		    try {
					uri = new URI(sb.toString());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
    		    String apiResponse = new RestTemplate().getForObject(uri, String.class);
    		    return apiResponse;
    }
	
}
