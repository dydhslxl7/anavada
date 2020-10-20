package admin.notice.inquiryController;

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
 * Servlet implementation class AdminInquirySearchServlet
 */
@WebServlet("/aisearch")
public class AdminInquirySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInquirySearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //필터생기면 지우기
		
		String selected = request.getParameter("selected");
		String keyword = request.getParameter("keyword");
		InquiryService iservice = new InquiryService();
		
		int currentPage = 1;
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		
		int countList = 6;
		int countPage = 10;
		
		int totalList = 0;
		
		ArrayList<Inquiry> list = null;
		switch(selected) {
		case "title" : list = iservice.searchTCW("t", keyword, currentPage, countList); totalList = iservice.getListCount("t", keyword); break;
		case "content" : list = iservice.searchTCW("c", keyword, currentPage, countList); totalList = iservice.getListCount("c", keyword); break;
		case "writer" : list = iservice.searchTCW("w", keyword, currentPage, countList); totalList = iservice.getListCount("w", keyword); break;
		}
		
		int totalPage = (int)((double)totalList / countList + 0.9) ;
		int startPage = (((int)((double)currentPage / countPage + 0.9)) - 1) * countPage + 1;
		int endPage = startPage + countPage - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		
		RequestDispatcher view = null;
		if(list.size() > -1) {
			view = request.getRequestDispatcher("views/admin/notice/inquiry/admininquiry_list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalList", totalList);
			request.setAttribute("keyword", keyword);
			request.setAttribute("selected", selected);
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
