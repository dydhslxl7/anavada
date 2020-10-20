package admin.declare.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.declare.model.service.DeclareService;
import admin.declare.model.vo.Declare;

/**
 * Servlet implementation class DeclareAdminCountUpServlet
 */
@WebServlet("/dcountup")
public class DeclareAdminCountUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeclareAdminCountUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ArrayList<Declare> list = new DeclareService().countUp();
		int dno = Integer.parseInt(request.getParameter("ddno"));
		System.out.println("dno :" + dno);
		
		
		 int result = new DeclareService().countUp(dno);
		 System.out.println("업 :" + result);//dao에서 db업시킨것
		 response.setContentType("text/html;charset=utf-8");
		 
		/*
		 * if(result > 0) { String data = result
		 * 
		 * }else { RequestDispatcher view =
		 * request.getRequestDispatcher("views/common/error.jsp");
		 * request.setAttribute("message", "카운트 값 가져오기 실패"); view.forward(request,
		 * response); }
		 */
		 ArrayList<Declare> list = new DeclareService().selectAll();
		 System.out.println(list);
		 
		 
		 PrintWriter pw = response.getWriter();
		 pw.println("Count Up");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
