package admin.notice.faqController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.notice.answer.model.service.AnswerService;
import admin.notice.answer.model.vo.Answer;
import faq.model.service.FaqService;
import faq.model.vo.Faq;

/**
 * Servlet implementation class AdminFaqDetailServlet
 */
@WebServlet("/afdetail")
public class AdminFaqDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFaqDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int no = Integer.parseInt(request.getParameter("no"));
    	Faq faq = new FaqService().selectOne(no);

    	int currentPage = 1;
    	if(request.getParameter("page") != null)
    		currentPage = Integer.parseInt(request.getParameter("page"));

    	String selected = null;
    	String keyword = null;
    	if(request.getParameter("selected") != null && request.getParameter("keyword") != null) {
    		selected = request.getParameter("selected");
    		keyword = request.getParameter("keyword");
    	}

    	if(faq != null) {
    		RequestDispatcher view = request.getRequestDispatcher("views/admin/notice/faq/adminfaq_view.jsp");
    		request.setAttribute("faq", faq);
    		request.setAttribute("page", currentPage);
    		request.setAttribute("selected", selected);
    		request.setAttribute("keyword", keyword);
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
