package inquiry.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inquiry.model.service.InquiryService;
import inquiry.model.vo.Inquiry;

/**
 * Servlet implementation class InquiryListServlet
 */
@WebServlet("/ilist")
public class InquiryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
//		String user = request.getParameter("user");
		
		int limit = 10;
		
		InquiryService iservice = new InquiryService();
//		int totalList = iservice.getListCount(user);
		int totalList = iservice.getListCount();
		
		int totalPage = (int)((double) totalList / limit + 0.9);
		int startPage = ((int)(((double) currentPage / limit) + 0.9) -1) * limit +1;
		int endPage = startPage + limit -1;
		if(endPage > totalPage)
			endPage = totalPage;
		
//		ArrayList<Inquiry> list = iservice.selectAllUser(currentPage, limit, user);
		ArrayList<Inquiry> list = iservice.selectAll(currentPage, limit);
		
		if(list.size() > -1) {
			RequestDispatcher view = request.getRequestDispatcher("views/inquiry/inquiry_list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("totalList", totalList);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("page", currentPage);
			request.setAttribute("totalPage", totalPage);
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
