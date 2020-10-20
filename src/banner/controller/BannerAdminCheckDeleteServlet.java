package banner.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.declare.model.service.DeclareService;
import banner.model.service.BannerService;

/**
 * Servlet implementation class BannerAdminCheckDeleteServlet
 */
@WebServlet("/bcheckdel")
public class BannerAdminCheckDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BannerAdminCheckDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkRow = request.getParameter("checkRow").split(",");
		System.out.println(request.getParameter("checkRow")+", "+checkRow.length);
		
		int[] checkedNum = new int[checkRow.length];
		for(int i=0; i<checkRow.length; i++) {
			checkedNum[i] = Integer.parseInt(checkRow[i]);
			System.out.print(checkedNum[i]+", 서블릿");
		}
		
		BannerService bservice = new BannerService();
		
		ArrayList<String> list = bservice.selectRfiles(checkedNum);
		String savePath = request.getSession().getServletContext().getRealPath("/resources/bannerfiles");
		
		int result = bservice.deletecheBanner(checkedNum);

		if(result > 0)
			for(String rfile : list)
				new File(savePath + "\\" + rfile).delete();
			response.sendRedirect("blist.ad");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
