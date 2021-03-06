package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/nsearch")
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String selected = request.getParameter("selected");
		String keyword = request.getParameter("keyword");
		NoticeService nservice = new NoticeService();
		
		int currentPage = 1;
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		int limit = 10;
		
		int listCount = 0;
		
		ArrayList<Notice> list = null;
		if(selected.equals("title")) {
			list = nservice.searchTorC(currentPage, limit, keyword, selected);
			listCount = nservice.getListCount("title", keyword);
		}else {
			list = nservice.searchTorC(currentPage, limit, keyword, selected);
			listCount = nservice.getListCount("content", keyword);
		}
		
		int maxPage = (int)((double)listCount / limit + 0.9) ;
		int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;
		if(endPage > maxPage)
			endPage = maxPage;
		
		Notice notice = nservice.selectCountTop1();
		
		RequestDispatcher view = null;
		if(list.size() > -1 && notice != null) {
			view = request.getRequestDispatcher("views/notice/notice_list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("notice", notice);
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
