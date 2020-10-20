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
@WebServlet("/myInfoPwdChange.cp")
public class MyInfoPwdChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoPwdChange() {
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
		//패스워드 변경
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPwd(request.getParameter("memberPwd"));
		member.setNewPwd(request.getParameter("newPwd"));
		member.setNewPwdOK(request.getParameter("newPwdOK"));
		
        String memberId = request.getParameter("memberId");
        String memberPwd = request.getParameter("memberPwd");
        String newPwd = request.getParameter("newPwd");
        String newPwdOK = request.getParameter("newPwdOK");
        
	    Member originalMember = new MemberService().selectMember(memberId);
	    
		String returnValue = null;

		if(originalMember.getMemberPwd().equals(memberPwd) && newPwd.equals(newPwdOK) && newPwd.getBytes().length > 1 && newPwdOK.getBytes().length > 1) {
	        
        	int result = new MemberService().updateMemberPwd(member);
        		    
			if(result > 0) {
				returnValue = "ok";
			}
			
		} else if(memberPwd.getBytes().length < 1) {
			returnValue = "not";
		} else if(!originalMember.getMemberPwd().equals(memberPwd)) {
			returnValue = "not2";
		} else if(newPwd.getBytes().length < 1 ) {
			returnValue = "not3";
		} else if(newPwdOK.getBytes().length < 1 ) {
			returnValue = "not4";
		} else if(!newPwd.equals(newPwdOK)) {
			returnValue = "not5";
		}
		
		//출력스트림 만들고 값 내보내기
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();

	}
}
