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
 * Servlet implementation class InquirySearchServlet
 */
@WebServlet("/isearch")
public class InquirySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquirySearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int currentPage = 1;
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));

		int limit = 10;
		
		String selected = request.getParameter("selected");
		String keyword = request.getParameter("keyword");
		
		ArrayList<Inquiry> list = null;
		InquiryService iservice = new InquiryService();
		int totalList = 0;
		
		String id = null;
		if(request.getParameter("member") != null)
			id = request.getParameter("member");
		
		if(id == null) {
			switch(selected) {
			case "title" : list = iservice.searchTCW("t", keyword, currentPage, limit); totalList = iservice.getListCount("t", keyword); break;
			case "content" : list = iservice.searchTCW("c", keyword, currentPage, limit); totalList = iservice.getListCount("c", keyword); break;
			case "writer" : list = iservice.searchTCW("w", keyword, currentPage, limit); totalList = iservice.getListCount("w", keyword); break;
			}
		}else { 
			switch(selected) {
			case "title" : list = iservice.searchUserTC("t", keyword, currentPage, limit, id); totalList = iservice.getListCount("t", keyword, id); break;
			case "content" : list = iservice.searchUserTC("c", keyword, currentPage, limit, id); totalList = iservice.getListCount("c", keyword, id); break;
			}
		}
		
		int totalPage = (int)((double) totalList / limit + 0.9);
		int startPage = ((int)(((double) currentPage / limit) + 0.9) -1) * limit +1;
		int endPage = startPage + limit -1;
		if(endPage > totalPage)
			endPage = totalPage;
		
		
		if(list.size() > -1) {
			
			RequestDispatcher view = null;
			
			if(id == null)
				view = request.getRequestDispatcher("views/inquiry/inquiry_list.jsp");
			else
				view = request.getRequestDispatcher("views/member/MyInquiry.jsp");
			
			request.setAttribute("list", list);
			request.setAttribute("page", currentPage);
			request.setAttribute("totalList", totalList);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("selected", selected);
			request.setAttribute("keyword", keyword);
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
