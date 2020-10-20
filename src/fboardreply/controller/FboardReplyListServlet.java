package fboardreply.controller;

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

import fboardreply.model.service.FboardReplyService;
import fboardreply.model.vo.FboardReply;
import fboard.model.service.FboardService;
import fboard.model.vo.Fboard;

/**
 * Servlet implementation class FboardReplyListServlet
 */
@WebServlet("/fbreplylist")
public class FboardReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FboardReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 가지고오기
//		System.out.println("FboardReplyListServlet");

		int fboardNo = Integer.parseInt(request.getParameter("fboardno"));
		
		ArrayList<FboardReply> list = new FboardReplyService().selectList(fboardNo);
		System.out.println("List : " + list.size());
		
		JSONObject sendJSON = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(FboardReply fboardR : list) {
			JSONObject job = new JSONObject();
			
			job.put("fboardReplyNo", fboardR.getFboardReplyNo());
			job.put("fboardNo", fboardR.getFboardNo());
			job.put("memberId", fboardR.getMemberId());
			job.put("fboardReplyContent", URLEncoder.encode(fboardR.getFboardReplyContent(), "utf-8"));
			job.put("fboardReplyLev", fboardR.getFboardReplyLev());
			job.put("fboardReplyRef", fboardR.getFboardReplyRef());
			job.put("fboardReplyCreatDate", fboardR.getFboardReplyCreatDate().toString());
			
			jarr.add(job);
		}	//for each
		
		sendJSON.put("list", jarr);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(sendJSON.toJSONString());
		out.flush();
		out.close();
		
//		System.out.println("댓글 : " + sendJSON);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
