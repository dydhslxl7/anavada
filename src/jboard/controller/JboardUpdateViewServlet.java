package jboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.JboardService;
import jboard.model.vo.Jboard;

/**
 * Servlet implementation class JboardUpdateViewServlet
 */
@WebServlet("/jbupview")
public class JboardUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jboardNo = Integer.parseInt(request.getParameter("jboardno"));
		int currentPage = Integer.parseInt(request.getParameter("page"));

		Jboard jboard = new JboardService().selectJboard(jboardNo);
		String post = jboard.getJboardPost();
		String meet = jboard.getJboardMeet(); 
		String local = jboard.getLocalNo();
		
		String MemberIp = request.getHeader("X-FORWARDED-FOR"); 
	    if (MemberIp == null || MemberIp.length() == 0) {
	    	MemberIp = request.getHeader("Proxy-Client-IP");
	    }
	    if (MemberIp == null || MemberIp.length() == 0) {
	    	MemberIp = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (MemberIp == null || MemberIp.length() == 0) {
	    	MemberIp = request.getRemoteAddr() ;
	    }
		RequestDispatcher view = null;
		if (jboard != null) {
				view = request.getRequestDispatcher("views/jboard/product_updateView.jsp");
				request.setAttribute("jboardno", jboard);
				request.setAttribute("page", currentPage);
				request.setAttribute("post", post);
				request.setAttribute("meet", meet);
				request.setAttribute("local", local);
				request.setAttribute("memberip", MemberIp);
				view.forward(request, response);
		}else {
				view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", jboardNo + " 번 게시글 수정페이지로 이동 실패!");
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
