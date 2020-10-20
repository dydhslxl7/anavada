package jboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.JboardService;
import jboard.model.vo.Jboard;

/**
 * Servlet implementation class JboardListViewServlet
 */
@WebServlet("/jblist")
public class JboardListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardListViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String titleSearch = request.getParameter("titlesearch");
		String listSearch = request.getParameter("listsearch");
		
		String local = request.getParameter("local");
		if (listSearch== null|| listSearch.equals("null")) { //문자열 null이 들어오는 상황이 발생
			listSearch = null;
		}
		if (local == null || local.equals("null") ||local.equals("0")) {
			local = null;
		}
		if (titleSearch == null || titleSearch.equals("null")||titleSearch.getBytes().length==0) {
			titleSearch = null;
		}
		int currentPage = 1;
		
		if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		int limit = 10;

		JboardService jbservice = new JboardService();
		
		int listCount = jbservice.getListCount(local, titleSearch);
		
		ArrayList<Jboard> list = jbservice.selectList(currentPage, limit, local, listSearch, titleSearch);
		
		
				
		int maxPage = (int)((double)listCount / limit + 0.9);
		int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit -1;
		if (maxPage < endPage) {
				endPage = maxPage;
		}
		RequestDispatcher view = null;
		if (list.size() > 0 ) {
				view = request.getRequestDispatcher("views/jboard/product_list.jsp");
				request.setAttribute("list", list);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("listCount", listCount);
				request.setAttribute("local" , local);
				request.setAttribute("listsearch", listSearch);
				request.setAttribute("titlesearch", titleSearch);
				request.setAttribute("page", currentPage);
				view.forward(request, response);
				
		}else {
				view = request.getRequestDispatcher("views/jboard/product_list.jsp");
				request.setAttribute("list", list);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("listCount", listCount);
				request.setAttribute("local" , local);
				request.setAttribute("listsearch", listSearch);
				request.setAttribute("titlesearch", titleSearch);
				request.setAttribute("page", currentPage);
				view.forward(request, response);		}
	
}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
