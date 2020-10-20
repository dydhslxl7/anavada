package admin.notice.noticeController;

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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class AdminNoticeInsertServlet
 */
@WebServlet("/aninsert.ss")
public class AdminNoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int maxSize = 1024 * 1024 * 10;
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/noticefiles");
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		Notice notice = new Notice();
		notice.setNoTitle(mrequest.getParameter("title"));
//최후에 바꾸기    notice.setNoId(mrequest.getParameter("writer"));
		notice.setNoContent(mrequest.getParameter("content"));
		String ofileName = mrequest.getFilesystemName("ofile");
		notice.setNoOriginal(ofileName);
		
		if(ofileName != null) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String rfileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
			rfileName += "." + ofileName.substring(ofileName.lastIndexOf(".")+1);
			
			
			File ofile = new File(savePath + "\\" + ofileName);
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
			
			notice.setNoRename(rfileName);
		}
		
		int result = new NoticeService().insertNotice(notice);
		
		if(result > 0)
			response.sendRedirect("anlist.ss");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
