package jboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.JboardService;

/**
 * Servlet implementation class JboardDeleteServlet
 */
@WebServlet("/jbdelete")
public class JboardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int jboardNo = Integer.parseInt(request.getParameter("jboardno"));
		
		if (new JboardService().jboardDelete(jboardNo) > 0) {
			for (int i = 1; i < 5; i++) {
				String renameFileName = request.getParameter("rfile" +i);
				if (renameFileName != null) {
					String savePath = request.getSession().getServletContext().getRealPath("resources/jboardfiles");
					new File(savePath+ "\\" + renameFileName).delete();
				}
			}
			response.sendRedirect("/anavada/jblist?page=1");
		} else {
				response.setContentType("text/html); charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글삭제 실패');");
				script.println("location.href='anavada/jbdetail?jboardno=" + jboardNo + "';");
;				script.println("</script>");
						
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
