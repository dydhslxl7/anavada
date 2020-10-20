package admin.notice.answer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.notice.answer.model.service.AnswerService;
import admin.notice.answer.model.vo.Answer;
import inquiry.model.service.InquiryService;

/**
 * Servlet implementation class AdminAnswerInsertServlet
 */
@WebServlet("/aainsert.ss")
public class AdminAnswerInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAnswerInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		int iqNo = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		
		Answer answer = new Answer();
		answer.setAnContent(content);
		answer.setIqNo(iqNo);
		answer.setIqId(id);
		
		int result = new AnswerService().insertAnswer(answer);
		
		if(result > 0) {
			
			new InquiryService().changeIqAnswer("Y", iqNo);
			
			response.sendRedirect("aidetail?no="+iqNo);
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
