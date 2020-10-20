package admin.notice.faqController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;

/**
 * Servlet implementation class AdminNoticeDeleteServlet
 */
@WebServlet("/afdelete")
public class AdminFaqDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFaqDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] checkRow = request.getParameter("checkRow").split(",");
		
		int[] checkedNum = new int[checkRow.length];
		for(int i=0; i<checkRow.length; i++) {
			checkedNum[i] = Integer.parseInt(checkRow[i]);
		}
		
		FaqService fservice = new FaqService();
		
		int result = fservice.deleteFaq(checkedNum);
		
		if(result > 0) {
			response.sendRedirect("aflist.ss");
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
