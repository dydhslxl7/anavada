package admin.cboard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cboard.model.service.CboardService;
import cboard.model.vo.Cboard;

/**
 * Servlet implementation class AdminCboardDeleteServlet
 */
@WebServlet("/adcdelete.ad")
public class AdminCboardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCboardDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String check = request.getParameter("checkarr");
		String[] splitCheck = check.split(",");
		int successCount = 0;

		CboardService cservice = new CboardService();
		for (String checkstr : splitCheck) { // 반복1
			Cboard cboard = cservice.selectCboard(Integer.parseInt(checkstr));
			String rfile1 = (cboard.getCfilesRenameFilepath1() != null ? cboard.getCfilesRenameFilepath1() : null);
			String rfile2 = (cboard.getCfilesRenameFilepath2() != null ? cboard.getCfilesRenameFilepath2() : null);
			String rfile3 = (cboard.getCfilesRenameFilepath3() != null ? cboard.getCfilesRenameFilepath3() : null);
			String rfile4 = (cboard.getCfilesRenameFilepath4() != null ? cboard.getCfilesRenameFilepath4() : null);
			String[] rfiles = {rfile1, rfile2, rfile3, rfile4}; 
			if (cservice.deleteCboard(Integer.parseInt(checkstr)) > 0) {
				for (String rfile : rfiles) {
					if (rfile != null) {
						String savePath = request.getSession().getServletContext().getRealPath("resources/cboardfiles");
						new File(savePath + "\\" + rfile).delete();
					}
				}
				successCount++;
			}
		}

		PrintWriter writer = response.getWriter();
		writer.print(successCount + "/" + splitCheck.length);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
