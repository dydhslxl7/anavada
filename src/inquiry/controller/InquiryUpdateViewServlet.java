package inquiry.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inquiry.model.service.InquiryService;
import inquiry.model.vo.Inquiry;

/**
 * Servlet implementation class InquiryUpdateViewServlet
 */
@WebServlet("/iupdateview")
public class InquiryUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Inquiry inquiry = new InquiryService().selectOne(Integer.parseInt(request.getParameter("no")));
		
		String my = null;
		if(request.getParameter("my") != null)
			my = request.getParameter("my");
		
		
		if(inquiry != null) {
			
			RequestDispatcher view = null;
			
			if(my == null)
				view = request.getRequestDispatcher("views/inquiry/inquiry_updateform.jsp");
			else
				view = request.getRequestDispatcher("views/inquiry/myinquiry_updateform.jsp");
			
			request.setAttribute("inquiry", inquiry);
			request.setAttribute("page", Integer.parseInt(request.getParameter("page")));
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
