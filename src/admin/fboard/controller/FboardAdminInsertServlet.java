package admin.fboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fboard.FestivalInformation;
import fboard.model.service.FboardService;
import fboard.model.vo.Fboard;

/**
 * Servlet implementation class FboardAdminInsertServlet
 */
@WebServlet("/fbinsert.ad")
public class FboardAdminInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FboardAdminInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 축제 id로 제공되는 축제 id랑 축제 게시판 번호 비교후 없으면 insert
		//System.out.println("FboardAdminInsertServlet");

		String memberId =request.getParameter("memberid");
//		System.out.println(memberId);
		
		Fboard fboard = new Fboard();
		FboardService fbService = new FboardService();
		int totalInsert = 0;
		JSONArray jsonArray = FestivalInformation.festival();

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject data = (JSONObject) (JSONObject) jsonArray.get(i);

			String fboardNo = data.get("contentid").toString();
			int selectResult = fbService.selectFboard(fboardNo);
			System.out.println("select : " + fboardNo + " 결과(1:유 0:무) : " + selectResult);

			if (selectResult == 0) {
				fboard.setFboardNo(fboardNo);
				fboard.setFestivalTitle(data.get("title").toString());
				fboard.setLocalNo(data.get("sigungucode").toString());
				fboard.setFestivalStartDate(data.get("eventstartdate").toString());
				fboard.setFestivalEndDate(data.get("eventenddate").toString());
				fboard.setFesivalModifiedDate(data.get("modifiedtime").toString());
				fboard.setMapX(data.get("mapx").toString());
				fboard.setMapY(data.get("mapy").toString());
				fboard.setMemberId(memberId);

				String thumbnail;
				if (data.get("firstimage2") == null) {
					thumbnail = "/anavada/resources/images/content/noimage.png";
				} else {
					thumbnail = data.get("firstimage2").toString();
				}

				fboard.setThumbnail(thumbnail);

				fbService.insertFboard(fboard);
				System.out.println("insert된 : " + fboard);
				totalInsert += 1;
			}
		}

		System.out.println("insert한 개수 : " + totalInsert);

		response.sendRedirect("/anavada/views/admin/fboard/adminfboardList.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
