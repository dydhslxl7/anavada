package admin.notice.noticeController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class AdminNoticeDetailServlet
 */
@WebServlet("/andetail")
public class AdminNoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Notice notice = new NoticeService().selectOne(Integer.parseInt(request.getParameter("no")));
		
		int currentPage = 1;
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		String selected = null;
		String keyword = null;
		if(request.getParameter("selected") != null && request.getParameter("keyword") != null) {
			selected = request.getParameter("selected");
			keyword = request.getParameter("keyword");
		}
		int listCount = new NoticeService().getListCount();
		
		if(notice != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/admin/notice/adminnotice_view.jsp");
			request.setAttribute("notice", notice);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("selected", selected);
			request.setAttribute("keyword", keyword);
			request.setAttribute("totalList", listCount);
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
