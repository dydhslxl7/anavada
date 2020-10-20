package admin.cboard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cboard.model.service.CboardService;

/**
 * Servlet implementation class CboardDeleteServlet
 */
@WebServlet("/adcdeleteone.ad")
public class AdminCboardDeleteOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCboardDeleteOneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cboardNum = Integer.parseInt(request.getParameter("cnum"));

		if (new CboardService().deleteCboard(cboardNum) > 0) {
			for (int i = 0; i < 4; i++) {
				String renameFileName = request.getParameter("rfile" + (i + 1));
				if (renameFileName != null) {
					String savePath = request.getSession().getServletContext().getRealPath("resources/cboardfiles");
					new File(savePath + "\\" + renameFileName).delete();
				}
			}
			response.sendRedirect("/anavada/adclistview.ad?page=1&local=0");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(
					"<script>alert('글삭제 실패');location.href='/anavada/adcdetail.ad?cnum=" + cboardNum + "';</script>");
			writer.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
