package declare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import declare.model.service.DBoService;
import declare.model.vo.DBo;

/**
 * Servlet implementation class DBoAdminUpdateServlet
 */
@WebServlet("/dboupdate.ad")
public class DBoAdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBoAdminUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 신고게시판 수정 처리용 컨트롤러

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("서블릿왓나?");
		DBo dbo = new DBo();
		
		dbo.setDboChe(request.getParameter("che"));
		dbo.setDboNo(Integer.parseInt(request.getParameter("no")));
	System.out.println("=============="
			+request.getParameter("che")+request.getParameter("no"));
						
		int result = new DBoService().updateDBo(dbo);		

		// 7.받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			response.sendRedirect("dbolist");
			System.out.println(result);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", dbo.getDboNo() + "번 공지사항 수정 실패!");
			view.forward(request, response);
		}
	}

}
