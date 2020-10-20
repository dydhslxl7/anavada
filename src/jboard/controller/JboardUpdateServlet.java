package jboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jboard.ImageUtil;
import jboard.model.service.JboardService;
import jboard.model.vo.Jboard;

/**
 * Servlet implementation class JboardUpdateServlet
 */
@WebServlet("/jbupdate")
public class JboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JboardUpdateServlet() {
        super();
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
		int maxSize = 1024 * 1024 * 10;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/jboardfiles");
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Jboard jboard = new Jboard();
		int currentPage = Integer.parseInt(mrequest.getParameter("page"));
		int jboardNo = Integer.parseInt(mrequest.getParameter("jboardno"));
		jboard.setJboardNo(jboardNo);
		jboard.setJboardPost(mrequest.getParameter("post"));
		jboard.setJboardMeet(mrequest.getParameter("meet"));
		jboard.setLocalNo(mrequest.getParameter("local"));
		jboard.setJboardTitle(mrequest.getParameter("title"));
		jboard.setJboardPrice(Integer.parseInt(mrequest.getParameter("price")));
		jboard.setJboardContent(mrequest.getParameter("content"));
		jboard.setMemberId(mrequest.getParameter("memberid"));
		jboard.setMemberIp(MemberIp);
		
	
		
		File newOriginFile = null;
		File originFile = null;
		for (int i = 1; i<5 ; i++) {
		String deleteFlag = mrequest.getParameter("delflag"+i);
		String originFilePath = mrequest.getParameter("ofile"+i);
		String renameFilePath = mrequest.getParameter("rfile"+i);
		String originalFileName = mrequest.getFilesystemName("upfile"+i); 
		 switch (i) {
         case 1: newOriginFile = new File(savePath + "/" + originalFileName);
            	 originFile = new File(savePath + "/" + renameFilePath);		break;
         case 2: newOriginFile = new File(savePath + "/" + originalFileName);
    	 		 originFile = new File(savePath + "/" + renameFilePath);		break;
         case 3: newOriginFile = new File(savePath + "/" + originalFileName);
    	 		 originFile = new File(savePath + "/" + renameFilePath);		break;
         case 4: newOriginFile = new File(savePath + "/" + originalFileName);
    	 		 originFile = new File(savePath + "/" + renameFilePath);		break;
         }

		if (originalFileName != null) {
			
			switch (i) {
			case 1 : jboard.setJboardOrignalFilePath1(originalFileName);		break;
			case 2 : jboard.setJboardOrignalFilePath2(originalFileName);		break;
			case 3 : jboard.setJboardOrignalFilePath3(originalFileName);		break;
			case 4 : jboard.setJboardOrignalFilePath4(originalFileName);		break;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddHHmmss");

			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			renameFileName +=i+ "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

			File renameFile = new File(savePath + "\\" + renameFileName);
			if (!newOriginFile.renameTo(renameFile)) {
				FileInputStream fin = new FileInputStream(newOriginFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				int data = -1;
				byte[] buffer = new byte[1024];
				while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
					fout.write(buffer, 0, buffer.length);
				}

				fin.close();
				fout.close();
				newOriginFile.delete(); 
			} 
			
			ImageUtil.resize(renameFile, renameFile, 450, 450); // 새로 업로드 된 파일 리사이즈
			
			switch (i) {
			case 1 : jboard.setJboardRenameFilePath1(renameFileName);		break;
			case 2 : jboard.setJboardRenameFilePath2(renameFileName);		break;
			case 3 : jboard.setJboardRenameFilePath3(renameFileName);		break;
			case 4 : jboard.setJboardRenameFilePath4(renameFileName);		break;
			}
			//System.out.println("첫번째rename 값 : "+jboard.getJboardRenameFilePath1());
			//System.out.println("첫번째original값:" + originalFileName);
			if(originFilePath != null) {
					originFile.delete();
			}	
		}else if (originFilePath != null && deleteFlag != null && deleteFlag.equals("yes")) {
			//System.out.println("두번째rename 값 : "+jboard.getJboardRenameFilePath1());
			//System.out.println("두번째original값:" + originalFileName);
			switch (i) {
			case 1 :jboard.setJboardOrignalFilePath1(null);
					jboard.setJboardRenameFilePath1(null);		break;
			case 2 :jboard.setJboardOrignalFilePath2(null);
			  		jboard.setJboardRenameFilePath2(null);		break;
			case 3 :jboard.setJboardOrignalFilePath3(null);
			        jboard.setJboardRenameFilePath3(null);		break;
			case 4 :jboard.setJboardOrignalFilePath4(null);
			        jboard.setJboardRenameFilePath4(null);		break;
			}
		originFile.delete();			
			} else if(originFilePath != null && (originalFileName == null ||originFilePath.equals(originalFileName) &&
				  newOriginFile.length() == originFile.length())) {
				   switch (i) { 
				   case 1:jboard.setJboardOrignalFilePath1(originFilePath);
				   		  jboard.setJboardRenameFilePath1(renameFilePath); break;
				   case 2:jboard.setJboardOrignalFilePath2(originFilePath);
			   		  jboard.setJboardRenameFilePath2(renameFilePath); break; 
				   case 3:jboard.setJboardOrignalFilePath3(originFilePath);
				   		  jboard.setJboardRenameFilePath3(renameFilePath); break; 
				   case 4:jboard.setJboardOrignalFilePath4(originFilePath);
				   		  jboard.setJboardRenameFilePath4(renameFilePath); break; 
			}
		}
		}
		int result = new JboardService().jboardUpdate(jboard);
	
		if (result > 0) {
			response.sendRedirect("jblist?page=" + currentPage);
		} else {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", jboardNo +"번 게시원글 수정 실패!");
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
