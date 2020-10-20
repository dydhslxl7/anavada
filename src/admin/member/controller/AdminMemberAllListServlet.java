package admin.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberAllListServlet
 */
@WebServlet("/mlist.ad")
public class AdminMemberAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberAllListServlet() {
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
	    String secessionOK = request.getParameter("secessionOK"); 
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");

		int limit = 10;
		MemberService mservice = new MemberService();
		int listCount = mservice.getListCount(search, keyword);
		int slistCount = mservice.getSListCount(search, keyword);
		ArrayList<Member> list = new MemberService().selectAllList(currentPage, limit, search, keyword, secessionOK);

		int maxPage = (int)((double)listCount / limit + 0.9);
		int smaxPage = (int)((double)slistCount / limit + 0.9);
		int startPage = (((int)((double)currentPage / limit + 0.9)) -1) * limit + 1;
		int endPage = startPage + limit - 1;
		
		if(maxPage < endPage && secessionOK.equals("N")) {
			endPage = maxPage;
		} else if(smaxPage < endPage && secessionOK.equals("Y")) {
			endPage = smaxPage;
		}

		int listNum = listCount + (1 - currentPage) * limit;
		int slistNum = slistCount + (1 - currentPage) * limit;
		
		RequestDispatcher view = null;
		if(secessionOK.equals("N")) { //전체 회원 조회 성공시
			view = request.getRequestDispatcher("views/admin/member/memberList.jsp");
			request.setAttribute("list", list);
			request.setAttribute("listNum", listNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("secessionOK", secessionOK);
			view.forward(request, response);
		} else if(secessionOK.equals("Y")) { //전체 탈퇴회원 조회 성공시
			view = request.getRequestDispatcher("views/admin/member/memberSecession.jsp");
			request.setAttribute("list", list);
			request.setAttribute("slistNum", slistNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("slistCount", slistCount);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("secessionOK", secessionOK);
			view.forward(request, response);
		} else { //전체 조회 실패시
			view = request.getRequestDispatcher("views/common/error.jsp");
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
