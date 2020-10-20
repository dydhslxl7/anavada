package admin.fboard.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class FestivalAPIListServlet
 */
@WebServlet("/fapi.ad")
public class FestivalAPIListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FestivalAPIListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 페이지 api에서 제공하는 축제 정보, 정렬 수정일 순
		
		//System.out.println("AdminAPIListServlet");
				
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String eventStartDate = Integer.toString(year) + "0101";	//eventStartDate 이번년도
				
		String serviceKey = "8xPJodnpOUGNIaYtnGCbGf%2BkI4DFMWAutVxObP8eDcC1tSnLtfdnLwV2YWvXx76YpSRELCyNrZIbUnK2EOSXcQ%3D%3D";
				
		StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
//			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지 번호*/
			urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드), WIN (원도우폰),ETC*/
			urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("anavada_localfestival", "UTF-8")); /*서비스명=어플명*/
			urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode("C", "UTF-8")); /*(A=제목순, B=조회순, C=수정순, D=생성일순) 대표이미지가 반드시 있는 정렬 (O=제목순, P=조회순, Q=수정일순, R=생성일순)*/
			urlBuilder.append("&" + URLEncoder.encode("listYN","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*목록 구분(Y=목록, N=개수)*/
			urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*지역코드*/
			urlBuilder.append("&" + URLEncoder.encode("sigunguCode","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시군구코드(areaCode 필수)*/
			urlBuilder.append("&" + URLEncoder.encode("eventStartDate","UTF-8") + "=" + URLEncoder.encode(eventStartDate, "UTF-8")); /*행사 시작일(형식:YYYYMMDD)*/
			urlBuilder.append("&" + URLEncoder.encode("eventEndDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*행사 종료일(형식:YYYYMMDD)*/
	        urlBuilder.append("&" + URLEncoder.encode("modifiedtime","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*콘텐츠 수정일*/
	        urlBuilder.append("&_type=json");
	        
	        URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
			    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		 	}
			
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());

			JSONParser jsonParse = new JSONParser();

			try {
				JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());

			    // 차례대로 데이터를 파싱합니다
			    JSONObject responseJsonObj = (JSONObject) jsonObj.get("response");
			    JSONObject bodyJsonObj = (JSONObject) responseJsonObj.get("body");
			    JSONObject items = (JSONObject) bodyJsonObj.get("items");

			    // 배열로 가져오기
			    JSONArray jsonArray = (JSONArray) items.get("item");
			    int totalCount = Integer.parseInt(bodyJsonObj.get("totalCount").toString());
			    System.out.println("총 축제 개수 : " + totalCount);

			    for (int i = 0; i < jsonArray.size(); i++) {
			    	JSONObject data = (JSONObject) (JSONObject) jsonArray.get(i);
			    }

			    response.setContentType("application/json; charset=utf-8"); 	//배열을 가지고 있으면 application/json
				PrintWriter out = response.getWriter();
				out.write(items.toJSONString());
				out.flush();
				out.close();

			}catch(ParseException e) {
				e.printStackTrace();
			}
			    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
