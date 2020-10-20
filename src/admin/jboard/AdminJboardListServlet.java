package admin.jboard;

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
 * Servlet implementation class AdminJboardListServlet
 */
@WebServlet("/adjlistview.ad")
public class AdminJboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminJboardListServlet() {
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
		System.out.println("local : " + local);
		System.out.println("titleSearch : " + titleSearch);
		System.out.println("listSearch : " + listSearch);
		if (listSearch== null|| listSearch.equals("null")) {
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
		
		ArrayList<Jboard> jlist = jbservice.selectList(currentPage, limit, local, listSearch, titleSearch);
		
		
				
		int maxPage = (int)((double)listCount / limit + 0.9);
		int startPage = (((int)((double)currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit -1;
		if (maxPage < endPage) {
				endPage = maxPage;
		}
		RequestDispatcher view = null;
		if (jlist.size() > 0 ) {
				view = request.getRequestDispatcher("views/admin/jboard/jboardAdminList.jsp");
				request.setAttribute("jlist", jlist);
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
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message",  currentPage + " 페이지에 대한 목록 조회 실패!");
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
