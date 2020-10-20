package banner.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banner.model.service.BannerService;

/**
 * Servlet implementation class BannerAdminDeleteServlet
 */
@WebServlet("/bdelete.ad")
public class BannerAdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BannerAdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 배너 삭제 처리용 컨트롤러
				int bannerNo = Integer.parseInt(request.getParameter("bannerNo"));
				System.out.println(bannerNo + "서블릿");

				int result = new BannerService().deleteBanner(bannerNo);
				System.out.println(result + bannerNo);

				if (result > 0) {
					// 공지글 삭제시 저장된 파일도 삭제 처리함
					String renameFileName = request.getParameter("rfile");
					if (renameFileName != null) {
						String savePath = request.getSession().getServletContext().getRealPath("/resources/bannerfiles");
						new File(savePath + "\\" + renameFileName).delete();
					}
					response.sendRedirect("blist.ad");
				} else {
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", bannerNo + "번글 삭제 실패!");
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
