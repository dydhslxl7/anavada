package admin.jboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jboard.model.service.JboardService;

/**
 * Servlet implementation class AdminJboardDeleteServlet
 */
@WebServlet("/adjdelete.ad")
public class AdminJboardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminJboardDeleteServlet() {
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

        JboardService jbservice = new JboardService();
        for (String checkstr : splitCheck) { // 반복1
            if (jbservice.jboardDelete(Integer.parseInt(checkstr)) > 0) {
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
