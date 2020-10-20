package declare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import declare.model.service.DBoService;
import declare.model.vo.DBo;

/**
 * Servlet implementation class DBoUpdateViewServlet
 */
@WebServlet("/dboupmove.ad")
public class DBoUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBoUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 신고게시글 수정페이지로 이동 처리용 컨트롤러
		
		int dboNo = Integer.parseInt(request.getParameter("dboNo"));
		
		DBo dbo = new DBoService().selectOne(dboNo);
		System.out.println(dbo);
		
		RequestDispatcher view = null;
		if(dbo != null) {
			view = request.getRequestDispatcher("views/declare/declare_updateview.jsp");
			request.setAttribute("dbo", dbo);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", dboNo + "번 글에 대한 수정페이지 요청 실패!");
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
