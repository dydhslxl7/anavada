package jboard.controller;

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

import jboard.model.service.JboardService;
import jboard.model.vo.Jboard;

/**
 * Servlet implementation class JboardNewTop3Servlet
 */
@WebServlet("/jtop3")
public class JboardNewTop3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardNewTop3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		ArrayList<Jboard> list = new JboardService().selectNewTop3();
		
		JSONObject sendJSON = new JSONObject();
		
		JSONArray jarr = new JSONArray();

		for(Jboard jboard : list) {

				JSONObject job = new JSONObject();
				job.put("jboardno" , jboard.getJboardNo());
				job.put("title", URLEncoder.encode(jboard.getJboardTitle(), "utf-8"));
				job.put("price", jboard.getJboardPrice());
				job.put("like", jboard.getJboardLike());
				job.put("pic",jboard.getJboardRenameFilePath1());
				
				jarr.add(job);
				
		}


		sendJSON.put("list" , jarr);
		

		response.setContentType("application/json ; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(sendJSON.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
