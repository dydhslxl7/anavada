package jboard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.JboardService;
import jboard.model.vo.Jboard;

/**
 * Servlet implementation class JboardLikeServlet
 */
@WebServlet("/jblike")
public class JboardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int jboardNo =Integer.parseInt(request.getParameter("jboardno"));
		String memberId = request.getParameter("memberid");
	    JboardService jbservice = new JboardService();
		Jboard jboard = new Jboard();
		
	    jboard.setJboardNo(jboardNo);
	    jboard.setMemberId(memberId);
	    
	    
	    int result = jbservice.jboardInsertLike(jboard);
	    
	    if (result == 1) {
	    	result = jbservice.jboardLikeUp(jboardNo); // 좋아요 누른사람을 성공적으로 insert 했다면 좋아요 + 1 시킴 
	    	if (result == 1) {
	    		response.setContentType("text/html; charset=UTF-8");
	    		PrintWriter script = response.getWriter();
	    		script.println("<script>");
	    		script.println("alert('좋아요.');");
	    		script.println("location.href='/anavada/jbdetail?jboardno=" + jboardNo +"';");
	    		script.println("</script>");
	    		script.close();
	    }else {
	            response.setContentType("text/html; charset=UTF-8");
	            PrintWriter script = response.getWriter();
	            script.println("<script>");
	    		script.println("alert('좋아요 에러 났습니다.');");
	    		script.println("location.href='/anavada/jbdetail?jboardno=" + jboardNo +"';");
	    		script.println("</script>");
	    		script.close();
	    }
		}else {
				
				jbservice.jboardLikeDown(jboardNo);
				jbservice.jboardDeleteLike(jboard);
			  	response.setContentType("text/html; charset=UTF-8");
	            PrintWriter script = response.getWriter();
	            script.println("<script>");
	    		script.println("alert('좋아요를 취소 했습니다.');");
	    		script.println("location.href='/anavada/jbdetail?jboardno=" + jboardNo +"';");
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
