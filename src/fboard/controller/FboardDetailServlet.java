package fboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fboard.FestivalDetailInformation;
import fboard.model.service.FboardService;
import fboard.model.vo.Fboard;

/**
 * Servlet implementation class FboardDetailServlet
 */
@WebServlet("/fbdetail")
public class FboardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FboardDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 축제 상세보기 페이지
//		System.out.println("FboardDetailServlet");

		String fboardNo = request.getParameter("fboardno");

		JSONObject jsonobj = FestivalDetailInformation.festivalDetail(fboardNo);

		JSONObject dataObj = (JSONObject) jsonobj.get("item");
//				System.out.println(dataObj);

		FboardService fbservice = new FboardService();

		// 조회수 증가
		int result = fbservice.updateReadcount(fboardNo);
		System.out.println(fboardNo + " : " + result + " 조회수 증가");

		// 축제 상세 보기
		Fboard fboard = fbservice.selectDetailFboard(fboardNo);

		String startDate = fboard.getFestivalStartDate();
		String endDate = fboard.getFestivalEndDate();
		String festivalmodifiedDate = fboard.getFesivalModifiedDate();
		Date dstartDate = null;
		Date dendDate = null;
		Date dfestivalmodifiedDate = null;

		// String -> Date 축제 시작일, 종료일
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			dstartDate = transFormat.parse(startDate);
			dendDate = transFormat.parse(endDate);
			dfestivalmodifiedDate = transFormat.parse(festivalmodifiedDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// String -> Date 축제 수정일
		SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			dfestivalmodifiedDate = transFormat2.parse(festivalmodifiedDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Date format하기 축제 시작일, 종료일
		transFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		startDate = transFormat.format(dstartDate);
		endDate = transFormat.format(dendDate);

		// Date format하기 축제 수정일
		transFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		festivalmodifiedDate = transFormat.format(dfestivalmodifiedDate);

		String period = startDate + " ~ " + endDate;
//				System.out.println("기간 : " + period);
		String localName = "서울시 " + fboard.getLocalName();
//				System.out.println("개최 지역 : " + localName);

		dataObj.put("period", period);
		dataObj.put("localName", localName);
		dataObj.put("readcount", fboard.getReadcount());
		dataObj.put("festivalmodifiedDate", festivalmodifiedDate);
		System.out.println(dataObj);

		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(dataObj.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
