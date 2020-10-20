package admin.fboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fboard.FestivalInformation;
import fboard.model.service.FboardService;
import fboard.model.vo.Fboard;

/**
 * Servlet implementation class FboardAdminUpdateServlet
 */
@WebServlet("/fbupdate.ad")
public class FboardAdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FboardAdminUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 등록된 축제 수정일이(modifiedtime) 변경된 경우(select 중복체크) 해당 축제 update
		
		//System.out.println("FboardAdminUpdateServlet");
		
		JSONArray jsonArray = FestivalInformation.festival();

		Fboard fboard = new Fboard();
		FboardService fbService = new FboardService();
		int totalUpdate = 0;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject data = (JSONObject) (JSONObject) jsonArray.get(i);

			String fboardNo = data.get("contentid").toString();

			String fesivalModifiedDate = data.get("modifiedtime").toString();

			int selectResult = new FboardService().selectFboard(fboardNo, fesivalModifiedDate);
			System.out.println("select : " + fboardNo + " 결과(1:유 100:무 0:해당축제 데이터가 없음) : " + selectResult);
			// selectResult == 1 해당 축제 수정이 없을 경우

			if (selectResult == 0) { // 축제 정보가 아예 없을 경우, 축제 수정일이 바뀌었을 경우
				System.out.println("축제 번호 : " + fboardNo + "축제 수정일 : " + fesivalModifiedDate);

				fboard.setFboardNo(fboardNo);
				fboard.setFestivalTitle(data.get("title").toString());
				fboard.setLocalNo(data.get("sigungucode").toString());
				fboard.setFestivalStartDate(data.get("eventstartdate").toString());
				fboard.setFestivalEndDate(data.get("eventenddate").toString());
				fboard.setFesivalModifiedDate(data.get("modifiedtime").toString());
				fboard.setMapX(data.get("mapx").toString());
				fboard.setMapY(data.get("mapy").toString());

				String thumbnail;
				if (data.get("firstimage2") == null) {
					System.out.println("썸네일 null");
					thumbnail = "/anavada/resources/images/noimage.png";
				} else {
					thumbnail = data.get("firstimage2").toString();
				}

				fboard.setThumbnail(thumbnail);

				fbService.updateFboard(fboard);
				System.out.println("update된 : " + fboard);
				totalUpdate += 1;
			}

		}
		System.out.println("update한 개수 : " + totalUpdate);

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
