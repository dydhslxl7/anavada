package jboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.CommentService;
import jboard.model.service.JboardService;
import jboard.model.vo.Comment;
import jboard.model.vo.Jboard;


/**
 * Servlet implementation class JboardDetailViewServlet
 */
@WebServlet("/jbdetail")
public class JboardDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
				int jboardNo = Integer.parseInt(request.getParameter("jboardno"));
				
				int currentPage = 1;
				if (request.getParameter("page") != null) {
					currentPage = Integer.parseInt(request.getParameter("page"));
				}
				
				
				Jboard jboard = new JboardService().selectJboard(jboardNo);
				JboardService jbservice = new JboardService();
				jbservice.addReadCount(jboardNo);
				
				String post = jboard.getJboardPost();
				String meet = jboard.getJboardMeet();
				
				CommentService jbcservice = new CommentService();
				ArrayList<Comment> list = jbcservice.CommentList(jboardNo);
				int commentListCount = jbcservice.getCommentCount(jboardNo);
				ArrayList<Jboard> likeMemberList = jbservice.getLikeMemberList(jboardNo);
				RequestDispatcher view = null;
				if(jboard != null) {
					view = request.getRequestDispatcher("views/jboard/product_view.jsp");
					request.setAttribute("jboardno", jboardNo);
					request.setAttribute("jboard", jboard);
					request.setAttribute("list", list);
					request.setAttribute("commentlistcount", commentListCount);
					request.setAttribute("page", currentPage);
					request.setAttribute("post", post);
					request.setAttribute("meet", meet);
					request.setAttribute("likememberlist", likeMemberList);
					view.forward(request, response);
				}else {
					view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", jboardNo + "번 글에 대한 상세보기 요청 실패!");
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
