package admin.cboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cboard.model.service.CboardService;
import cboard.model.vo.Cboard;

/**
 * Servlet implementation class AdminCboardListServlet
 */
@WebServlet("/adclistview.ad")
public class AdminCboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCboardListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		String local = "0";

		if (request.getParameter("local") != null && !request.getParameter("local").equals("null")) {
			local = request.getParameter("local");
		}

		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");

		int limit = 10;
		CboardService cservice = new CboardService();
		int listCount = cservice.getListCount(local, search, keyword);
		int allListCount = cservice.getAllListCount();

		ArrayList<Cboard> list = cservice.selectAll(currentPage, limit, local, search, keyword);

		int maxPage = (int) ((double) listCount / limit + 0.9);
		int startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}
		if (endPage == 0) {
			endPage = 1;
		}

		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/admin/cboard/communityAdminList.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("local", local);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/admin/cboard/communityAdminList.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("local", local);
			request.setAttribute("search", search);
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
