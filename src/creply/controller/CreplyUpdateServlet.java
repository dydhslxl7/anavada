package creply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import creply.model.service.CreplyService;

/**
 * Servlet implementation class CreplyUpdateServlet
 */
@WebServlet("/crupdate.ss")
public class CreplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreplyUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int creplyNum = Integer.parseInt(request.getParameter("crnum"));
		int cboardNum = Integer.parseInt(request.getParameter("cnum"));
		String content = request.getParameter("content");

		CreplyService crservice = new CreplyService();

		int result = crservice.updateCreply(creplyNum, content);

		if (result > 0) {
			response.sendRedirect("/anavada/cdetail?cnum=" + cboardNum);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('댓글 수정 실패.');location.href='/anavada/cdetail?cnum=" + cboardNum + "';</script>");
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
