package fboardreply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fboardreply.model.service.FboardReplyService;

/**
 * Servlet implementation class FboardReplyDeleteServlet
 */
@WebServlet("/fbreplydelete.ss")
public class FboardReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FboardReplyDeleteServlet() {
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
		// 댓글 삭제하기
//		System.out.println("FboardReplyDeleteServlet");
		
		int fboardReplyNo = Integer.parseInt(request.getParameter("fboardReplyno"));
		
		String memberId =request.getParameter("memberid");
		
		int result = new FboardReplyService().deleteFboardReply(fboardReplyNo, memberId);
		
//		System.out.println("전송 온 값 : " + fboardReplyNo + ", " + memberId + "댓글 삭제 결과 : " + result);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(Integer.toString(result));	//result = 0 삭제 X, 1 = 삭제 O
		out.flush();
		out.close();
		
	}

}
