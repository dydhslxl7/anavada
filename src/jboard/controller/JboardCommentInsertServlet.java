package jboard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.CommentService;
import jboard.model.vo.Comment;


/**
 * Servlet implementation class JboardCommentInsertServlet
 */
@WebServlet("/jbcinsert.ss")
public class JboardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JboardCommentInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 댓글 등록 처리용 컨트롤러

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int jboardNo = Integer.parseInt(request.getParameter("jboardno"));

		String commentId = request.getParameter("commentid");
		String commentContent = request.getParameter("commentcontent");
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
		CommentService jbcservice = new CommentService();
		Comment reply = new Comment();
		reply.setJboardNo(jboardNo);
		reply.setCommentContent(commentContent);
		reply.setCommentId(commentId);
		reply.setMemberIp(MemberIp);

		int result = jbcservice.insertComment(reply);
		
		if (result > 0 ) {
			response.sendRedirect("/anavada/jbdetail?jboardno=" + jboardNo);
		}else {
			response.setContentType("text/html; charset=UTF-8");
    		PrintWriter script = response.getWriter();
    		script.println("<script>");
    		script.println("alert('댓글 작성 실패.');");
    		script.println("location.href='/anavada/jbdetail?jboardno="+jboardNo+"';");
    		script.println("</script>");
    		script.close();
		}
	}

}
