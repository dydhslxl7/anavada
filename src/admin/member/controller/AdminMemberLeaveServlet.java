package admin.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class AdminLeaveServlet
 */
@WebServlet("/adminleave.ad")
public class AdminMemberLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberLeaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Chk = request.getParameter("checkarr");
		String[] dataChk = Chk.split(",");
		
		int leaveCount = 0;
		MemberService mservice = new MemberService();
		
		for(String checkstr : dataChk) { //반복문

			int result = mservice.leaveMember(checkstr);

			if (result > 0) {
				leaveCount++;
			}
		}
		PrintWriter writer = response.getWriter();
		writer.print(leaveCount + "/" + dataChk.length);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
