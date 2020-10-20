package cboard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cboard.model.service.CboardService;
import clike.model.service.ClikeService;

/**
 * Servlet implementation class UpLikeCountServlet
 */
@WebServlet("/uplike")
public class UpLikeCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpLikeCountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cboardNum = Integer.parseInt(request.getParameter("cnum"));
		String memberId = request.getParameter("memberId");
		
		int likeable = new ClikeService().likeable(cboardNum, memberId);
		
		if (likeable > 0) {
			int result = new CboardService().upLikeCount(cboardNum);
			response.sendRedirect("/anavada/cdetail?cnum=" + cboardNum);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('이미 좋아한 게시글입니다.');location.href='/anavada/cdetail?cnum=" + cboardNum + "';</script>");
			writer.close();
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
