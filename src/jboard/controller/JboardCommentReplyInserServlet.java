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
 * Servlet implementation class JboardCommentReplyInserServlet
 */
@WebServlet("/jbcrinsert.ss")
public class JboardCommentReplyInserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardCommentReplyInserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int commentNo = Integer.parseInt(request.getParameter("commentno"));
		int jboardNo = Integer.parseInt(request.getParameter("jboardno"));
				
		String commentContent = request.getParameter("commentcontent");
		String commentWriter = request.getParameter("writer");
		
		CommentService jbcservice = new CommentService();
		
		Comment comment = jbcservice.selectComment(commentNo);
		
		Comment reply = new Comment();
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
		reply.setJboardNo(jboardNo);
		reply.setCommentContent(commentContent);
		reply.setCommentId(commentWriter);
		reply.setCommentLevel(comment.getCommentLevel() + 1);
		reply.setCommentRef(comment.getCommentRef());
		reply.setMemberIp(MemberIp);
		if (reply.getCommentLevel() == 2) {
			reply.setCommentReplyRef(comment.getCommentReplyRef());
		}
		
		reply.setCommentReplySeq(1);
		
		jbcservice.updateReplySeq(reply);
		
		int result = jbcservice.insertReplyComment(reply);
		
		if (result > 0 ) {
			response.sendRedirect("/anavada/jbdetail?jboardno=" + jboardNo);
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", jboardNo + "번 댓글 등록 실패 !");
			view.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
