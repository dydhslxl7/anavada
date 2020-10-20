package jboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.CommentService;
import jboard.model.vo.Comment;

/**
 * Servlet implementation class JboardCommentUpdateServlet
 */
@WebServlet("/jbcupdate.ss")
public class JboardCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		int jboardNo = Integer.parseInt(request.getParameter("jboardno"));
		Comment reply = new Comment();
		reply.setCommentNo(Integer.parseInt(request.getParameter("commentno")));
		reply.setCommentContent(request.getParameter("content"));
		reply.setMemberIp(MemberIp);
		int result = new CommentService().updateComment(reply);
		
		if (result > 0) {
				response.sendRedirect("/anavada/jbdetail?jboardno=" + jboardNo);
		}else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", reply.getCommentNo() + "번 댓글 수정 실패!");
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
