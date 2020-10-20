package admin.jboard;

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
 * Servlet implementation class AdminJboardDetailServlet
 */
@WebServlet("/adjbdetail")
public class AdminJboardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminJboardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int jboardno = Integer.parseInt(request.getParameter("jboardno"));
		
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		
		Jboard jboard = new JboardService().selectJboard(jboardno);
		JboardService jbservice = new JboardService();
		jbservice.addReadCount(jboardno);
		
		String post = jboard.getJboardPost();
		String meet = jboard.getJboardMeet();
		
		CommentService jbcservice = new CommentService();
		ArrayList<Comment> clist = jbcservice.CommentList(jboardno);
		int commentListCount = jbcservice.getCommentCount(jboardno);
		RequestDispatcher view = null;
		if(jboard != null) {
			view = request.getRequestDispatcher("views/admin/jboard/jboardAdminDetailView.jsp");
			request.setAttribute("jboardno", jboard);
			request.setAttribute("clist", clist);
			request.setAttribute("commentlistcount", commentListCount);
			request.setAttribute("page", currentPage);
			request.setAttribute("post", post);
			request.setAttribute("meet", meet);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", jboardno + "번 글에 대한 상세보기 요청 실패!");
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
