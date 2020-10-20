package admin.notice.answer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.notice.answer.model.service.AnswerService;
import inquiry.model.service.InquiryService;

/**
 * Servlet implementation class AdminAnswerDeleteServlet
 */
@WebServlet("/aadelete")
public class AdminAnswerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAnswerDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int iqNo = Integer.parseInt(request.getParameter("iqNo"));
		int anNo = Integer.parseInt(request.getParameter("anNo"));
		
		int result = new AnswerService().deleteAnswer(anNo);
		
		if(result > 0) {
			
			new InquiryService().changeIqAnswer("N", iqNo);
			
			response.sendRedirect("aidetail?no="+iqNo+"&page="+Integer.parseInt(request.getParameter("page")));
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
