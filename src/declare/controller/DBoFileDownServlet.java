package declare.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBoFileDownServlet
 */
@WebServlet("/dbofdown")
public class DBoFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBoFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = request.getSession().getServletContext().getRealPath("/resources/dboupfiles");
		
		request.setCharacterEncoding("utf-8"); //필터생기면 지우기
		
		String ofileName = request.getParameter("ofile");
		String rfileName = request.getParameter("rfile");
		
		File readFile = new File(savePath + "\\" + rfileName);
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(readFile));
		
		ServletOutputStream down = response.getOutputStream();
		
		response.setContentType("text/plain; charset=utf-8");
		
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ new String(ofileName.getBytes("utf-8"), "ISO-8859-1") + "\"");
		
		response.setContentLength((int)readFile.length());
		
		int data = -1;
		while((data=bin.read()) != -1) {
			down.write(data);
			down.flush();
		}
		down.close();
		bin.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
