package admin.creply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import creply.model.service.CreplyService;
import creply.model.vo.Creply;

/**
 * Servlet implementation class SubCreplyWriteServlet
 */
@WebServlet("/adscwrite.ss")
public class SubCreplyWriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubCreplyWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int cboardNum = Integer.parseInt(request.getParameter("cnum"));
        String writer = request.getParameter("writer");
        String content = request.getParameter("content");
        int parantNum = Integer.parseInt(request.getParameter("prnum"));

        System
            .out
            .println(cboardNum);
        System
            .out
            .println(writer);
        System
            .out
            .println(parantNum);
        System
            .out
            .println(content);

        Creply creply = new Creply();

        creply.setCbaordNo(cboardNum);
        creply.setMemberId(writer);
        creply.setCreplyContent(content);
        creply.setParantReply(parantNum);

        int result = new CreplyService().insertCreply(creply);

        RequestDispatcher view = null;
        if (result > 0) {
            response.sendRedirect("/anavada/adcdetail.ad?cnum=" + cboardNum);
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter pwriter = response.getWriter();
            pwriter.println(
                "<script>alert('대댓글 작성 실패.');location.href='/anavada/cdetail?cnum=" +
                cboardNum + "';</script>"
            );
            pwriter.close();
        }
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
