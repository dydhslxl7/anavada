package inquiry.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import inquiry.model.service.InquiryService;
import inquiry.model.vo.Inquiry;

/**
 * Servlet implementation class InquiryUpdateInquiryServlet
 */
@WebServlet("/iupdateiq")
public class InquiryUpdateInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InquiryUpdateInquiryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int maxSize = 1024 * 1024 * 10;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/noticefiles/inquiryfiles");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());

		int no = Integer.parseInt(mrequest.getParameter("no"));

		Inquiry inquiry = new Inquiry();
		inquiry.setIqNo(no);
		inquiry.setIqTitle(mrequest.getParameter("title"));
		inquiry.setIqContent(mrequest.getParameter("content"));

		switch(mrequest.getParameter("type")) {
		case "member" : inquiry.setIqType("회원정보"); break;
		case "error" : inquiry.setIqType("오류"); break;
		case "proposal" : inquiry.setIqType("제안하기"); break;
		case "etc" : inquiry.setIqType("기타"); break;
		}
		
		inquiry.setIqOriginal(mrequest.getParameter("ofile"));
		inquiry.setIqRename(mrequest.getParameter("rfile"));
		inquiry.setIqOriginal2(mrequest.getParameter("ofile2"));
		inquiry.setIqRename2(mrequest.getParameter("rfile2"));
		inquiry.setIqOriginal3(mrequest.getParameter("ofile3"));
		inquiry.setIqRename3(mrequest.getParameter("rfile3"));
		
		
		Enumeration em = mrequest.getFileNames();
		
		int bonusNumber = 1;
		while(em.hasMoreElements()) {

			String file = (String)em.nextElement();
			String ofileName = mrequest.getFilesystemName(file);
			String ofileOriginalName = mrequest.getOriginalFileName(file);

			if(ofileName != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String rfileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				rfileName += bonusNumber + "." + ofileName.substring(ofileName.lastIndexOf(".")+1);

				File ofile = new File(savePath + "/" + ofileName);
				File rfile = new File(savePath + "\\" + rfileName);

				if(!ofile.renameTo(rfile)) {

					FileInputStream fin = new FileInputStream(ofile);
					FileOutputStream fout = new FileOutputStream(rfile);

					int data = -1;
					byte[] buffer = new byte[1024];
					while((data = fin.read(buffer, 0, buffer.length)) != -1) {
						fout.write(buffer, 0, buffer.length);
					}
					fin.close();
					fout.close();
					ofile.delete();
				}

				switch(file) {
				case "newofile1" : inquiry.setIqOriginal(ofileOriginalName); inquiry.setIqRename(rfileName); break;
				case "newofile2" : inquiry.setIqOriginal2(ofileOriginalName); inquiry.setIqRename2(rfileName); break;
				case "newofile3" : inquiry.setIqOriginal3(ofileOriginalName); inquiry.setIqRename3(rfileName); break;

				}

			}
			bonusNumber++;
		}


		InquiryService iservice = new InquiryService();
		int result = iservice.updateInquiry(inquiry);
		
		String my = null;
		if(request.getParameter("my") != null)
			my = request.getParameter("my");
		
		if(result > 0) {
			
			inquiry = iservice.selectOne(no);
			RequestDispatcher view = null;
			
			if(my == null)
				view = request.getRequestDispatcher("views/inquiry/inquiry_view.jsp");
			else
				view = request.getRequestDispatcher("views/inquiry/myinquiry_view.jsp");
			
			request.setAttribute("inquiry", inquiry);
			request.setAttribute("page", Integer.parseInt(mrequest.getParameter("page")));
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
