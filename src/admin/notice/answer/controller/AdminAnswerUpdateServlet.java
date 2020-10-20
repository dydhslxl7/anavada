package admin.notice.answer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.notice.answer.model.service.AnswerService;
import admin.notice.answer.model.vo.Answer;

/**
 * Servlet implementation class AdminAnswerUpdateServlet
 */
@WebServlet("/aaupdateview")
public class AdminAnswerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAnswerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int iqNo = Integer.parseInt(request.getParameter("iqNo"));
		int anNo = Integer.parseInt(request.getParameter("anNo"));
		
		Answer answer = new AnswerService().selectOne(iqNo);
		
		if(answer != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/admin/notice/inquiry/admininquiry_updateform.jsp");
			request.setAttribute("page", page);
			request.setAttribute("answer", answer);
			view.forward(request, response);
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
