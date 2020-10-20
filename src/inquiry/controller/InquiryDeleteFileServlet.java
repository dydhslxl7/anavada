package inquiry.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import inquiry.model.service.InquiryService;
import inquiry.model.vo.Inquiry;

/**
 * Servlet implementation class InquiryDeleteFileServlet
 */
@WebServlet("/ideletefile")
public class InquiryDeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDeleteFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		int rnum = Integer.parseInt(request.getParameter("value"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/noticefiles/inquiryfiles");
		
		InquiryService iservice = new InquiryService();
		int result = 0; String rfile = null;
		switch(rnum) {
		case 1 : rfile = iservice.selectRfiles(no, 1); result = iservice.deleteFiles(no, 1); break;
		case 2 : rfile = iservice.selectRfiles(no, 2); result = iservice.deleteFiles(no, 2); break;
		case 3 : rfile = iservice.selectRfiles(no, 3); result = iservice.deleteFiles(no, 3); break;
		}
		
		if(result > 0) {
			
			switch(rnum) {
			case 1 : new File(savePath + "\\" + rfile).delete(); break;
			case 2 : new File(savePath + "\\" + rfile).delete(); break;
			case 3 : new File(savePath + "\\" + rfile).delete(); break;
			}
			
			response.sendRedirect("iupdateview?no="+no+"&page="+page);
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
