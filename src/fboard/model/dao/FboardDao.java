package fboard.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fboard.model.vo.Fboard;

public class FboardDao {
	
	public FboardDao() {
	}
	
	// (관리자) 축제 정보 DB에 저장하기 전에 이미 축제게시판에있는지 확인하기 select
	public int selectFboard(Connection conn, String fboardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) from fboard where fboard_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fboardNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			} else {
				result = rset.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
		// (관리자) 축제 정보 가져와서 추가하기 insert
		public int insertFboard(Connection conn, Fboard fboard) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = "insert into fboard values(?, ?, ?, ?, ?, ?, ?, ?, default, ?, default, ?)";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, fboard.getFboardNo());
				pstmt.setString(2, fboard.getFestivalTitle());
				pstmt.setString(3, fboard.getLocalNo());
				pstmt.setString(4, fboard.getFestivalStartDate());
				pstmt.setString(5, fboard.getFestivalEndDate());
				pstmt.setString(6, fboard.getFesivalModifiedDate());
				pstmt.setString(7, fboard.getMapX());
				pstmt.setString(8, fboard.getMapY());
				pstmt.setString(9, fboard.getMemberId());
				pstmt.setString(10, fboard.getThumbnail());

				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}


		// (관리자) 축제 수정날짜 비교 (축제 번호, 수정날짜로)
		public int selectFboard(Connection conn, String fboardNo, String fesivalModifiedDate) {
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "select count(*) from fboard where fboard_no = ? and festival_modifieddate = ?";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, fboardNo);
				pstmt.setString(2, fesivalModifiedDate);

				rset = pstmt.executeQuery();

				if (rset.next()) {
					result = rset.getInt(1);
					System.out.println("축제 변경 없음");
				} else {
					result = rset.getInt(1);
					System.out.println("축제 변경 있음");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return result;
		}

		// (관리자) 축제게시판 수정하기
		public int updateFboard(Connection conn, Fboard fboard) {
			int result = 0;
			PreparedStatement pstmt = null;

			String query = "update fboard set festival_title = ?, local_no = ?, festival_startdate = ?, festival_enddate = ?, festival_modifieddate = ?, "
					+ "map_y = ?, map_x = ?, bmodify_date = default, thumbnail = ? where fboard_no = ?";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, fboard.getFestivalTitle());
				pstmt.setString(2, fboard.getLocalNo());
				pstmt.setString(3, fboard.getFestivalStartDate());
				pstmt.setString(4, fboard.getFestivalEndDate());
				pstmt.setString(5, fboard.getFesivalModifiedDate());
				pstmt.setString(6, fboard.getMapY());
				pstmt.setString(7, fboard.getMapX());
				pstmt.setString(8, fboard.getThumbnail());
				pstmt.setString(9, fboard.getFboardNo());

				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

		// (관리자) 축제 게시판 삭제하기
		public int deleteFboard(Connection conn) {
			int result = 0;
			Statement stmt = null;

			String query = "delete from fboard where substr(festival_enddate, 1, 4) < extract( year from sysdate)";	// 매년 연도가 바뀌면 이전 년도 축제 해당 게시판 delete

			try {
				stmt = conn.createStatement();
				result = stmt.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(stmt);
			}
			return result;
		}
		
		// (관리자) 축제 게시판 전부 삭제하기
		public int deleteAllFboard(Connection conn) {
			int result = 0;
			Statement stmt = null;

			String query = "delete fboard"; 

			try {
				stmt = conn.createStatement();

				result = stmt.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(stmt);
			}
			return result;
		}
		
		// (관리자) fboard 전체 가지고 오기
		public ArrayList<Fboard> selectFboardList(Connection conn) {
			ArrayList<Fboard> list = new ArrayList<Fboard>();
			Statement stmt = null;
			ResultSet rset = null;

			String query = "select * from fboard order by festival_modifieddate desc";

			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);

				while (rset.next()) {
					Fboard fboard = new Fboard();

					fboard.setFboardNo(rset.getString(1));
					fboard.setFestivalTitle(rset.getString(2));
					fboard.setFestivalStartDate(rset.getString(4));
					fboard.setFestivalEndDate(rset.getString(5));
					fboard.setFesivalModifiedDate(rset.getString(6));
					fboard.setThumbnail(rset.getString(12));

					list.add(fboard);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(stmt);
			}
			return list;
		}
		
		// 카카오맵 축제 게시판 전체 리스트
		public ArrayList<Fboard> selectKList(Connection conn) {
			ArrayList<Fboard> list = new ArrayList<Fboard>();
			Statement stmt = null;
			ResultSet rset = null;

			String query = "select * from fboard left join location using (local_no) where festival_enddate > sysdate -1";	//현재 진행중인 축제 보기

			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);

				while (rset.next()) {
					Fboard fboard = new Fboard();
					
					fboard.setLocalNo(rset.getString(1));
					fboard.setFboardNo(rset.getString(2));
					fboard.setFestivalTitle(rset.getString(3));
					fboard.setFestivalStartDate(rset.getString(4));
					fboard.setFestivalEndDate(rset.getString(5));
					fboard.setFesivalModifiedDate(rset.getString(6));
					fboard.setMapX(rset.getString(7));
					fboard.setMapY(rset.getString(8));
					fboard.setbModifiedDate(rset.getDate(9));
					fboard.setMemberId(rset.getString(10));
					fboard.setReadcount(rset.getInt(11));
					fboard.setThumbnail(rset.getString(12));
					fboard.setLocalName(rset.getString(13));

					list.add(fboard);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(stmt);
			}

			return list;
		}

		//조회수 증가
		public int updateReadcount(Connection conn, String fboardNo) {
			int result = 0;
			PreparedStatement pstmt = null;

			String query = "update fboard set readcount = readcount + 1 where fboard_no = ?";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, fboardNo);

				result = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

		// 축제 상세보기
		public Fboard selectDetailFboard(Connection conn, String fboardNo) {
			Fboard fboard = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;

			String query = "select * from fboard left join location using (local_no) where fboard_no = ?";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, fboardNo);

				rset = pstmt.executeQuery();

				if (rset.next()) {
					fboard = new Fboard();

					fboard.setFestivalStartDate(rset.getString("festival_startdate"));
					fboard.setFestivalEndDate(rset.getString("festival_enddate"));
					fboard.setLocalName(rset.getString("local_name"));
					fboard.setReadcount(rset.getInt("readcount"));
					fboard.setFesivalModifiedDate(rset.getString("festival_modifieddate"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return fboard;
		}

		//게시판리스트
		public ArrayList<Fboard> selectList(Connection conn, String lastList, int locationSelect, String sortSelect, String title) {
			ArrayList<Fboard> list = new ArrayList<Fboard>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;

			// 기본 : allList=on, location=0, sortSelect=enddateAsc (sysdate가 date라서 비교가 정확하게 되지 않아 오늘 날짜를 포함 못시킴)
			String query = "select f.local_no, f.fboard_no, f.festival_title, f.festival_startdate, f.festival_enddate, f.festival_modifieddate, f.map_y, f.map_x, f.bmodify_date, "
					+ "f.member_id, f.readcount, f.thumbnail, l.local_name, (select count(fboard_no) from fboard_reply where fboard_no = f.fboard_no) replycount "
					+ "from fboard f left join location l on (f.local_no = l.local_no) ";

			//지난 축제보기 여부
			if(lastList == "true" || lastList.equals("true")) { 
				//query += "where substr( festival_enddate, 1, 4) = EXTRACT (year from sysdate) "; 
				query += "where festival_enddate < sysdate -1";
			} else { 
				query += "where festival_enddate > sysdate -1 "; 
			} 
			
			//지역 선택
			 if(locationSelect != 0) { 
				 query += " and f.local_no = ? "; 
			 }
			 
			//제목 검색
			 if(!(title == null || title.equals("null"))) {
				 query += " and upper(f.festival_title) like upper(?) "; 
			 }
			 
			//정렬 선택 (종료일 마감순(enddateAsc), 조회수 높은순(readcountDesc), 댓글 많은순(replyDesc))
			if(sortSelect == "enddateAsc" || sortSelect.equals("enddateAsc")) {
				query += " order by festival_enddate asc";
			} else if (sortSelect == "readcountDesc" || sortSelect.equals("readcountDesc")) {
				query += " order by readcount desc";
			}  else if (sortSelect == "replyDesc" || sortSelect.equals("replyDesc")) {
				query += " order by 14 desc";
			}
			
			System.out.println(query);
			
			try {
				pstmt = conn.prepareStatement(query);
				if(locationSelect != 0 && (title == null || title.equals("null"))) {
					pstmt.setString(1, Integer.toString(locationSelect));
				} else if (locationSelect == 0 && !(title == null || title.equals("null"))) {
					pstmt.setString(1, "%" + title + "%");
				} else if (locationSelect != 0 && !(title == null || title.equals("null"))) {
					pstmt.setString(1, Integer.toString(locationSelect));
					pstmt.setString(2, "%" + title + "%");
				}
				
				rset = pstmt.executeQuery();

				while (rset.next()) {
					Fboard fboard = new Fboard();

					fboard.setLocalNo(rset.getString(1));
					fboard.setFboardNo(rset.getString(2));
					fboard.setFestivalTitle(rset.getString(3));
					fboard.setFestivalStartDate(rset.getString(4));
					fboard.setFestivalEndDate(rset.getString(5));
					fboard.setFesivalModifiedDate(rset.getString(6));
					fboard.setMapX(rset.getString(7));
					fboard.setMapY(rset.getString(8));
					fboard.setbModifiedDate(rset.getDate(9));
					fboard.setMemberId(rset.getString(10));
					fboard.setReadcount(rset.getInt(11));
					fboard.setThumbnail(rset.getString(12));
					fboard.setLocalName(rset.getString(13));
					fboard.setReplycount(rset.getInt(14));

					list.add(fboard);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}

			return list;
		}


		// 축제 종료일 기준 top8
		public ArrayList<Fboard> selectTop8(Connection conn) {
			ArrayList<Fboard> list = new ArrayList<Fboard>();
			Statement stmt = null;
			ResultSet rset = null;
			
			String query = "select rownum, fboard_no, festival_title, festival_enddate, thumbnail, local_name " + 
					"from (select * from fboard left join location using (local_no) where festival_enddate > sysdate -1 order by festival_enddate asc) where rownum <= 8";
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);

				while (rset.next()) {
					Fboard fboard = new Fboard();

					fboard.setFboardNo(rset.getString("fboard_no"));
					fboard.setFestivalTitle(rset.getString("festival_title"));
					fboard.setFestivalEndDate(rset.getString("festival_enddate"));
					fboard.setThumbnail(rset.getString("thumbnail"));
					fboard.setLocalName(rset.getString("local_name"));

					list.add(fboard);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(stmt);
			}
			return list;
		}
}
