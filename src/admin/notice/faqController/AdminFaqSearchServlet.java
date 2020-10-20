package admin.notice.faqController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;
import faq.model.vo.Faq;

/**
 * Servlet implementation class AdminInquirySearchServlet
 */
@WebServlet("/afsearch")
public class AdminFaqSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFaqSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //필터생기면 지우기
		
		String selected = request.getParameter("selected");
		String keyword = request.getParameter("keyword");
		FaqService fservice = new FaqService();
		
		int currentPage = 1;
		if(request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		
		int countList = 6;
		int countPage = 10;
		
		int totalList = 0;
		
		ArrayList<Faq> list = null;
		switch(selected) {
		case "title" : list = fservice.searchTCC(currentPage, countList, keyword, "t"); totalList = fservice.getListCount("t", keyword); break;
		case "content" : list = fservice.searchTCC(currentPage, countList, keyword, "c"); totalList = fservice.getListCount("c", keyword); break;
		case "cate"	: list = fservice.searchTCC(currentPage, countList, keyword, "cate"); totalList = fservice.getListCount("cate", keyword); break;
		}
		
		int totalPage = (int)((double)totalList / countList + 0.9) ;
		int startPage = (((int)((double)currentPage / countPage + 0.9)) - 1) * countPage + 1;
		int endPage = startPage + countPage - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		
		RequestDispatcher view = null;
		if(list.size() > -1) {
			view = request.getRequestDispatcher("views/admin/notice/faq/adminfaq_list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalList", totalList);
			request.setAttribute("keyword", keyword);
			request.setAttribute("selected", selected);
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
