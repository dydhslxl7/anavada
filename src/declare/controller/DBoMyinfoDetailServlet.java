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
 * Servlet implementation class DBoAdminDetailServlet
 */
@WebServlet("/dbodetail")
public class DBoMyinfoDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBoMyinfoDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 신고게시판 신고글 마이페이지에서 상세보기 처리용 컨트롤러
        int dboNo = Integer.parseInt(request.getParameter("dboNo"));
				DBo dbo = new DBoService().selectOne(dboNo);
		
		RequestDispatcher view = null;
		if(dbo != null) {
			view = request.getRequestDispatcher("views/declare/declare_myinfo_detail_view.jsp");
			request.setAttribute("dbo", dbo);
			view.forward(request, response);
		}else { 
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", dboNo + "번 글에 대한 상세보기 요청 실패했습니다.");
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
