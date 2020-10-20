package admin.cboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cboard.model.service.CboardService;
import cboard.model.vo.Cboard;
import creply.model.service.CreplyService;
import creply.model.vo.Creply;

/**
 * Servlet implementation class AdminCboardDetailViewServlet
 */
@WebServlet("/adcdetail.ad")
public class AdminCboardDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCboardDetailViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int cboardNum = Integer.parseInt(request.getParameter("cnum"));
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		String local = request.getParameter("local");
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");

		CboardService cservice = new CboardService();

		int allListCount = cservice.getAllListCount();

		cservice.addReadCount(cboardNum);

		Cboard cboard = cservice.selectCboard(cboardNum);

		CreplyService crservice = new CreplyService();
		ArrayList<Creply> rlist = crservice.creplyList(cboardNum);
		ArrayList<Creply> srlist = crservice.subCreplyList(cboardNum);
		int allReplyCount = crservice.allReplyCount(cboardNum);

		RequestDispatcher view = null;
		if (cboard != null) {
			view = request.getRequestDispatcher("views/admin/cboard/communityAdminDetailView.jsp");
			request.setAttribute("rlist", rlist);
			request.setAttribute("srlist", srlist);
			request.setAttribute("cboard", cboard);
			request.setAttribute("page", currentPage);
			request.setAttribute("local", local);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("allListCount", allListCount);
			request.setAttribute("allReplyCount", allReplyCount);
			view.forward(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('존재하지 않는 글입니다.');location.href='/anavada/adclistview.ad?page=1&l" + "ocal=0';</script>");
			writer.close();
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
