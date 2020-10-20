package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberMypageServlet
 */
@WebServlet("/pwdChange.cp")
public class MemberPwdChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdChangeServlet() {
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
        String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");
        String AuthenticationUser = request.getParameter("AuthenticationUser");

        if(!AuthenticationKey.equals(AuthenticationUser)){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('인증번호 또는 비밀번호가 일치하지 않습니다.\\n다시 입력해주세요.'); location.href='views/member/idpwdChange.jsp';</script>");
			writer.close();
        	
        } else {
        	Member member = new Member();
			member.setMemberId(request.getParameter("memberId"));
			member.setMemberPwd(request.getParameter("memberPwd"));
			
			int result = new MemberService().userPwdUpdate(member);
	        
    		if(result > 0) {
    		    String newPwd = request.getParameter("memberPwd");
    			String newPwd2 = request.getParameter("memberPwd2");
    			
    			if(newPwd.equals(newPwd2)) {
        			response.setContentType("text/html; charset=UTF-8");
        			PrintWriter writer = response.getWriter();
        			writer.println("<script>alert('비밀번호가 변경되었습니다.\\n로그인페이지로 이동합니다.'); location.href='views/member/login.jsp';</script>");
        			writer.close();
    			} else { // 수정 실패시
        			response.setContentType("text/html; charset=UTF-8");
        			PrintWriter writer = response.getWriter();
        			writer.println("<script>alert('입력하신 비밀번호가 일치하지 않습니다.\\n다시 입력해주세요.'); location.href='views/member/idpwdChange.jsp';</script>");
        			writer.close();
        		}
    		}
        }
	}
}
