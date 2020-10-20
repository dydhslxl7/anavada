package admin.fboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fboard.model.service.FboardService;

/**
 * Servlet implementation class FboardAdminDeleteServlet
 */
@WebServlet("/fbdelete.ad")
public class FboardAdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FboardAdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 매년 연도가 바뀌면 이전 년도 축제 해당 게시판 delete
		
		//System.out.println("FboardAdminDeleteServlet");
		
		new FboardService().deleteFboard();
		
		response.sendRedirect("/anavada/views/admin/fboard/adminfboardList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 축제 삭제하기
		doGet(request, response);
	}

}
