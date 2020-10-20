package jboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import jboard.ImageUtil;
import jboard.model.service.JboardService;
import jboard.model.vo.Jboard;


/**
 * Servlet implementation class JboardInsertServlet
 */
@WebServlet("/jbinsert")
public class JboardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 22L;

    /**
     * Default constructor. 
     */
    public JboardInsertServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) { 
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form 의 enctype='multipart/form-data' 속성 누락됨");
			view.forward(request, response);
		}
		

		
		int maxSize = 1024 * 1024 * 10; //용량 10메가로 제한

		String savePath = request.getSession().getServletContext().getRealPath("/resources/jboardfiles");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		String MemberIp = request.getHeader("X-FORWARDED-FOR"); 
	    if (MemberIp == null || MemberIp.length() == 0) {
	    	MemberIp = request.getHeader("Proxy-Client-IP");
	    }
	    if (MemberIp == null || MemberIp.length() == 0) {
	    	MemberIp = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (MemberIp == null || MemberIp.length() == 0) {
	    	MemberIp = request.getRemoteAddr() ;
	    }
		JboardService jbservice = new JboardService();
		String memberId = mrequest.getParameter("memberid");
		
		int listCount=jbservice.getOneDayLimitCount(memberId); //하루에 작성자가 쓴 게시물 수
		
		Jboard jboard = new Jboard();
		jboard.setJboardPost(mrequest.getParameter("post"));
		jboard.setJboardMeet(mrequest.getParameter("meet"));
		jboard.setLocalNo(mrequest.getParameter("local"));
		jboard.setJboardTitle(mrequest.getParameter("title"));
		jboard.setJboardPrice(Integer.parseInt(mrequest.getParameter("price")));
		jboard.setJboardContent(mrequest.getParameter("content"));
		jboard.setMemberId(mrequest.getParameter("memberid"));
		jboard.setMemberIp(MemberIp);
		
		
		for ( int i=1 ; i<5;i++) {
		String originalFileName= mrequest.getFilesystemName("ofile" +i);

		switch (i) {
		case 1: jboard.setJboardOrignalFilePath1(originalFileName); break;
		case 2: jboard.setJboardOrignalFilePath2(originalFileName); break;
		case 3: jboard.setJboardOrignalFilePath3(originalFileName); break;
		case 4: jboard.setJboardOrignalFilePath4(originalFileName); break;
		}
		if (originalFileName != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			renameFileName +=i +"." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			
			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);
			if (!originFile.renameTo(renameFile)) {
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				int data = -1;
				byte[] buffer = new byte[1024];
				while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
					fout.write(buffer, 0, buffer.length);
				}

				fin.close();
				fout.close();
				originFile.delete(); 
			} 
			
			
			switch (i) {
			case 1: jboard.setJboardRenameFilePath1(renameFileName); break;
			case 2: jboard.setJboardRenameFilePath2(renameFileName); break;
			case 3: jboard.setJboardRenameFilePath3(renameFileName); break;
			case 4: jboard.setJboardRenameFilePath4(renameFileName); break;
			}
			ImageUtil.resize(renameFile, renameFile, 450, 450); //renameFile 크기를 450,450 으로 고정
			
		}
		}
		int result = 0;
		if (listCount <6) {
			result = new JboardService().insertJboard(jboard);
		}
		if (result > 0) {
			
			response.sendRedirect("jblist?page=1");
		} else {
			if (listCount >5) {
	    		response.setContentType("text/html; charset=UTF-8");
	    		PrintWriter script = response.getWriter();
	    		script.println("<script>");
	    		script.println("alert('하루 게시글은 5개를 초과할 수 없습니다.');");
	    		script.println("location.href='/anavada/jblist';");
	    		script.println("</script>");
	    		script.close();
		}
			
		
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
