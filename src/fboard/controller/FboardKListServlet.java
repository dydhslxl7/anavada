package fboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fboard.model.service.FboardService;
import fboard.model.vo.Fboard;

/**
 * Servlet implementation class FboardKListServlet
 */
@WebServlet("/fbklist")
public class FboardKListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FboardKListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 지도용 이번년도 축제게시판 목록 조회 가지고 오기
		FboardService fbservice = new FboardService();
	
		//축제 게시판 가지고 오기
		ArrayList<Fboard> list = fbservice.selectKList();
//		System.out.println(list.size());
		
		RequestDispatcher view = null;
		if(list.size() > 0) {
			view = request.getRequestDispatcher("views/fboard/areaEvent_list.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		} else { 
	         view = request.getRequestDispatcher("views/common/error.jsp");
	         request.setAttribute("message", "축제 정보를 가지고 올 수 없습니다.");
	         view.forward(request, response);
	      }

		System.out.println(list);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
