package admin.notice.faqController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.service.FaqService;
import faq.model.vo.Faq;

/**
 * Servlet implementation class AdminFaqUpdateFaqServlet
 */
@WebServlet("/afupfaq")
public class AdminFaqUpdateFaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFaqUpdateFaqServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		Faq faq = new Faq();
		faq.setFaqNo(no);
		faq.setFaqTitle(request.getParameter("title"));
		faq.setFaqContent(request.getParameter("content"));
		faq.setFaqCategory(Integer.parseInt(request.getParameter("checkCate")));
		
		
		FaqService fservice = new FaqService();
		int result = fservice.updateFaq(faq);
		
		if(result > 0) {
			faq = fservice.selectOne(no);
			RequestDispatcher view = request.getRequestDispatcher("views/admin/notice/faq/adminfaq_view.jsp");
			request.setAttribute("faq", faq);
			request.setAttribute("page", Integer.parseInt(request.getParameter("page")));
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
