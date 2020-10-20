package banner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banner.model.service.BannerService;
import banner.model.vo.Banner;

/**
 * Servlet implementation class BannerAdminSelOneServlet
 */
@WebServlet("/bselone.ad")
public class BannerAdminSelOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BannerAdminSelOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bannerNo = Integer.parseInt(request.getParameter("bannerNo"));
		Banner banner = new BannerService().selectOne(bannerNo);

		RequestDispatcher view = null;
		if (banner != null) {
			view = request.getRequestDispatcher("views/admin/banner/bannerUpdateView.jsp");
			request.setAttribute("banner", banner);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", bannerNo + "번 글에 대한 상세보기 요청 실패했습니다.");
			view.forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
