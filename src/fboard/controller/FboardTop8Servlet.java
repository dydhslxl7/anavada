package fboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fboard.model.service.FboardService;
import fboard.model.vo.Fboard;
/**
 * Servlet implementation class FboardTop6Servlet
 */
@WebServlet("/fbtop8")
public class FboardTop8Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FboardTop8Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 축제 종료일 기준 top8
		ArrayList<Fboard> list = new FboardService().selectTop8();
		//System.out.println(list.size());
		
		JSONObject sendJSON = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Fboard fboard : list) {
			JSONObject job = new JSONObject();
			
			job.put("fboardNo", fboard.getFboardNo());
			job.put("festivalTitle", URLEncoder.encode(fboard.getFestivalTitle(), "utf-8"));
			job.put("festivalEndDate", fboard.getFestivalEndDate());
			job.put("thumbnail", fboard.getThumbnail());
			job.put("localName", fboard.getLocalName());
			
			jarr.add(job);
		}	//for each
		
		sendJSON.put("list", jarr);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(sendJSON.toJSONString());
		out.flush();
		out.close();
		
		//System.out.println(sendJSON);
	}

}
