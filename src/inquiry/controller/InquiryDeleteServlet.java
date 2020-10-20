package inquiry.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.notice.answer.model.service.AnswerService;
import inquiry.model.service.InquiryService;

/**
 * Servlet implementation class InquiryDeleteServlet
 */
@WebServlet("/idelete")
public class InquiryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	int iqNo = Integer.parseInt(request.getParameter("no"));

    	int result = new InquiryService().deleteInquiry(iqNo);

		String savePath = request.getSession().getServletContext().getRealPath("/resources/noticefiles/inquiryfiles");
		
		String rfile = request.getParameter("rfile");
		String rfile2 = request.getParameter("rfile2");
		String rfile3 = request.getParameter("rfile3");
		
		String my = null;
		if(request.getParameter("my") != null)
			my = request.getParameter("my");
		
		if(result > 0) {
			
			AnswerService aservice = new AnswerService();
			aservice.deleteAnswer(aservice.searchAnNo(iqNo));
			
			if(rfile != null) {
				new File(savePath + "\\" + rfile).delete();
			}if(rfile2 != null) {
				new File(savePath + "\\" + rfile2).delete();
			}if(rfile3 != null) {
				new File(savePath + "\\" + rfile3).delete();
			}
			
			if(my == null)
				response.sendRedirect("ilist");
			else response.sendRedirect("miq?member="+request.getParameter("member"));
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
