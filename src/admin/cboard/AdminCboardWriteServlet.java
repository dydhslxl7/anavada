package admin.cboard;

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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import cboard.model.service.CboardService;
import cboard.model.vo.Cboard;

/**
 * Servlet implementation class AdminCboardWriteServlet
 */
@WebServlet("/adcwrite.ad")
public class AdminCboardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCboardWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "form의 enctype='multipart/form-data' 속성 누락됨");
			view.forward(request, response);
		}

		int maxSize = 1024 * 1024 * 10;

		String savePath = request.getSession().getServletContext().getRealPath("/resources/cboardfiles");

		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());

		Cboard cboard = new Cboard();

		cboard.setCboardTitle(mrequest.getParameter("title"));
		cboard.setMemberId(mrequest.getParameter("writer"));
		cboard.setCboardContent(mrequest.getParameter("content"));
		cboard.setLocalNo(mrequest.getParameter("local"));

		for (int i = 0; i < 4; i++) {
			String originalFileName = mrequest.getFilesystemName("ofile" + (i + 1));
			switch (i + 1) {
			case 1:
				cboard.setCfilesOriginalFilepath1(originalFileName);
				break;
			case 2:
				cboard.setCfilesOriginalFilepath2(originalFileName);
				break;
			case 3:
				cboard.setCfilesOriginalFilepath3(originalFileName);
				break;
			case 4:
				cboard.setCfilesOriginalFilepath4(originalFileName);
				break;
			default:
				break;
			}

			if (originalFileName != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSSSSS");

				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));

				renameFileName += i + "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

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
				switch (i + 1) {
				case 1:
					cboard.setCfilesRenameFilepath1(renameFileName);
					break;
				case 2:
					cboard.setCfilesRenameFilepath2(renameFileName);
					break;
				case 3:
					cboard.setCfilesRenameFilepath3(renameFileName);
					break;
				case 4:
					cboard.setCfilesRenameFilepath4(renameFileName);
					break;
				default:
					break;
				}
			}
		}

		int result = new CboardService().insertCboard(cboard);

		if (result > 0) {
			response.sendRedirect("/anavada/adclistview.ad?page=1&local=0");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('글작성 실패');location.href='/anavada/adclistview.ad?page=1&local=0'" + ";</script>");
			writer.close();
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
