package cboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
 * Servlet implementation class CboardUpdateServlet
 */
@WebServlet("/cupdate")
public class CboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CboardUpdateServlet() {
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

		cboard.setCboardNo(Integer.parseInt(mrequest.getParameter("cnum")));
		cboard.setCboardTitle(mrequest.getParameter("title"));
		cboard.setCboardContent(mrequest.getParameter("content"));
		cboard.setLocalNo(mrequest.getParameter("local"));
		File newOriginFile = null;
		File originFile = null;
		String deleteFlag = null;
		String originFilePath = null;
		String renameFilePath = null;
		String originalFileName = null;
		String renameFileName = null;

		for (int i = 0; i < 4; i++) {
			deleteFlag = mrequest.getParameter("delflag" + (i + 1));
			originFilePath = mrequest.getParameter("ofile" + (i + 1));
			renameFilePath = mrequest.getParameter("rfile" + (i + 1));
			originalFileName = mrequest.getFilesystemName("upfile" + (i + 1));
			switch (i + 1) {
			case 1:
				newOriginFile = new File(savePath + "/" + originalFileName);
				originFile = new File(savePath + "/" + renameFilePath);
				break;
			case 2:
				newOriginFile = new File(savePath + "/" + originalFileName);
				originFile = new File(savePath + "/" + renameFilePath);
				break;
			case 3:
				newOriginFile = new File(savePath + "/" + originalFileName);
				originFile = new File(savePath + "/" + renameFilePath);
				break;
			case 4:
				newOriginFile = new File(savePath + "/" + originalFileName);
				originFile = new File(savePath + "/" + renameFilePath);
				break;
			default:
				break;
			}

			if (originalFileName != null) {
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
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSSSSS");

				renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));

				renameFileName += i + "." + originFilePath.substring(originFilePath.lastIndexOf(".") + 1);

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
				}

				if (originFilePath != null) {
					originFile.delete();
				}
			} else if (originFilePath != null && deleteFlag != null && deleteFlag.equals("yes")) {
				switch (i + 1) {
				case 1:
					cboard.setCfilesOriginalFilepath1(null);
					cboard.setCfilesRenameFilepath1(null);
					break;
				case 2:
					cboard.setCfilesOriginalFilepath2(null);
					cboard.setCfilesRenameFilepath2(null);
					break;
				case 3:
					cboard.setCfilesOriginalFilepath3(null);
					cboard.setCfilesRenameFilepath3(null);
					break;
				case 4:
					cboard.setCfilesOriginalFilepath4(null);
					cboard.setCfilesRenameFilepath4(null);
					break;
				}
				originFile.delete();
			} else if (originFilePath != null
					&& (originalFileName == null || originFilePath.equals(originalFileName) && originFilePath.contentEquals(originalFileName) && newOriginFile.length() == originFile.length())) {
				switch (i + 1) {
				case 1:
					cboard.setCfilesOriginalFilepath1(originFilePath);
					cboard.setCfilesRenameFilepath1(renameFilePath);
					break;
				case 2:
					cboard.setCfilesOriginalFilepath2(originFilePath);
					cboard.setCfilesRenameFilepath2(renameFilePath);
					break;
				case 3:
					cboard.setCfilesOriginalFilepath3(originFilePath);
					cboard.setCfilesRenameFilepath3(renameFilePath);
					break;
				case 4:
					cboard.setCfilesOriginalFilepath4(originFilePath);
					cboard.setCfilesRenameFilepath4(renameFilePath);
					break;
				}
			}

		}
		int result = new CboardService().updateCboard(cboard);
		if (result > 0) {
			response.sendRedirect("/anavada/cdetail?cnum=" + cboard.getCboardNo());
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('글수정 실패');location.href='/anavada/cdetail?cnum=" + cboard.getCboardNo() + "';</script>");
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
