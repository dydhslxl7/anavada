package member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberMypageServlet
 */
@WebServlet("/mupdate.cp")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//회원정보 변경
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPwd(request.getParameter("memberPwd"));
		member.setMemberName(request.getParameter("memberName"));
		member.setMemberEmail(request.getParameter("memberEmail"));
		member.setEmailAuth(request.getParameter("emailAuth"));
		member.setMemberPhone(request.getParameter("memberPhone"));

        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");
        
	    Member originalMember = new MemberService().selectMember(memberId);
	    
		if(originalMember == null || !originalMember.getMemberPwd().equals(memberPwd)) {
			//myinfo 서블릿을 실행해서, 내 정보보기 페이지를 내보냄
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('입력하신 기존 비밀번호가 다릅니다.'); location.href=document.referrer;</script>");
			writer.close();
        } else {
        	int result = new MemberService().updateMember(member);
	
			if(result > 0) {
				//myinfo 서블릿을 실행해서, 내 정보보기 페이지를 내보냄
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('수정되었습니다.'); location.href=document.referrer;</script>");
				writer.close();
			} else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
				request.setAttribute("message", member.getMemberId() + " 회원의 정보 수정 실패.");
				view.forward(request, response);
			}
        }
		
	}
}
