package declare.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import declare.model.service.DBoService;
import declare.model.vo.DBo;

/**
 * Servlet implementation class DBoListServlet
 */
@WebServlet("/dbolist")
public class DBoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBoListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 신고게시판 목록보기 처리용 컨트롤러
		
		/*
		 * ArrayList<DBo> list = new DBoService().selectAll(); System.out.println(list);
		 * RequestDispatcher view = null; if(list.size()>0) { view =
		 * request.getRequestDispatcher("views/declare/declare_list.jsp");
		 * request.setAttribute("list", list); view.forward(request, response); }else {
		 * view = request.getRequestDispatcher("views/common/error.jsp");
		 * request.setAttribute("message", "조회된 목록이 없습니다."); view.forward(request,
		 * response); }
		 */
		
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10;
		DBoService dboservice = new DBoService();

		int listCount = dboservice.getListCount();
		System.out.println(listCount + "서블릿");//확인용

		ArrayList<DBo> list = dboservice.selectList(currentPage, limit);
		
		int maxPage = (int) ((double) listCount / limit + 0.9);
		int startPage = (((int) ((double) currentPage / limit + 0.9)) - 1) * limit + 1;
		int endPage = startPage + limit - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		RequestDispatcher view = null;
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/declare/declare_list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);

			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", currentPage + "페이지에 대한 목록 조회 실패!");
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
