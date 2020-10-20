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


/**
 * Servlet implementation class JboardCommentDeleteServlet
 */
@WebServlet("/jbcdelete")
public class JboardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int commentNo = Integer.parseInt(request.getParameter("commentno"));
			int jboardNo = Integer.parseInt(request.getParameter("jboardno"));
			if(new CommentService().commentDelete(commentNo) > 0) {
				response.sendRedirect("/anavada/jbdetail?jboardno="+jboardNo);
			}else {
				response.setContentType("text/html; charset=UTF-8");
	    		PrintWriter script = response.getWriter();
	    		script.println("<script>");
	    		script.println("alert('삭제에 실패 했습니다..');");
	    		script.println("location.href='/anavada/jbdetail?jboardno="+jboardNo+"';");
	    		script.println("</script>");
	    		script.close();
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
