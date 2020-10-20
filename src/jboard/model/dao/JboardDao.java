package jboard.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.tomcat.jni.Local;

import jboard.model.vo.Jboard;
import notice.model.vo.Notice;

public class JboardDao {
		public JboardDao () {}

		public int insertJboard(Connection conn, Jboard jboard) {
			int result = 0;
			
			PreparedStatement pstmt = null;

			String query = "insert into jboard values ("
					+ "SEQ_JBOARD_NO.nextval ,? , ? , ? ,sysdate, sysdate, 0, 0, ?, ?, ?, ?, ?, ? , ?, ?,"
					+ "default,?,?,?,?,?)";
			try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1,  jboard.getJboardTitle());
					pstmt.setString(2, jboard.getJboardContent());
					pstmt.setInt(3, jboard.getJboardPrice());
					pstmt.setString(4, jboard.getJboardOrignalFilePath1());
					pstmt.setString(5, jboard.getJboardRenameFilePath1());
					pstmt.setString(6, jboard.getJboardOrignalFilePath2());
					pstmt.setString(7, jboard.getJboardRenameFilePath2());
					pstmt.setString(8, jboard.getJboardOrignalFilePath3());
					pstmt.setString(9, jboard.getJboardRenameFilePath3());
					pstmt.setString(10, jboard.getJboardOrignalFilePath4());
					pstmt.setString(11, jboard.getJboardRenameFilePath4());
					pstmt.setString(12, jboard.getJboardMeet());
					pstmt.setString(13,  jboard.getJboardPost());
					pstmt.setString(14, jboard.getMemberId());
					pstmt.setString(15, jboard.getLocalNo());
					pstmt.setString(16, jboard.getMemberIp());
					
					result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return result;
	}
		


		public int getListCount(Connection conn , String local ,String titleSearch ) {
			int listCount = 0;
			Statement stmt = null;
			ResultSet rset = null;
			//System.out.println( "로컬값 :" +local + "titleSearch값 " + titleSearch);
			String query  = "SELECT COUNT(*) FROM JBOARD "
			+(local != null && !local.equals("0") ? "WHERE LOCAL_NO ="+local : "");
			
			if (titleSearch != null && local==null) {
				query+= " WHERE JBOARD_TITLE LIKE '%" + titleSearch + "%'";
			}else if(titleSearch != null&& local!=null &&!local.equals("0")) {
				query+= " AND JBOARD_TITLE LIKE '%" + titleSearch + "%'";
			}
			
			try {
					stmt = conn.createStatement();
					rset = stmt.executeQuery(query);
					
					if (rset.next()) {
						listCount = rset.getInt(1);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(stmt);
			}
			return listCount;
	}

		public ArrayList<Jboard> selectList(Connection conn, int currentPage, int limit ,String local, String listSearch , String titleSearch) {
			ArrayList<Jboard> list = new ArrayList <Jboard>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "SELECT * " + 
					"FROM (SELECT ROWNUM RNUM, JBOARD_NO, JBOARD_TITLE, JBOARD_CONTENT, " + 
					"JBOARD_PRICE, JBOARD_DATE , JBOARD_UPDATE, " + 
					"JBOARD_COUNT, JBOARD_LIKE, JFILES_ORIGINAL_FILEPATH1, JFILES_RENAME_FILEPATH1, " + 
					"JFILES_ORIGINAL_FILEPATH2, JFILES_RENAME_FILEPATH2 , JFILES_ORIGINAL_FILEPATH3, " +
					"JFILES_RENAME_FILEPATH3, JFILES_ORIGINAL_FILEPATH4 , JFILES_RENAME_FILEPATH4, " +
					"JBOARD_CHECK , JBOARD_MEET, JBOARD_POST, MEMBER_ID, LOCAL_NO, MEMBER_IP "+
					"FROM (SELECT * FROM JBOARD " +
					(local != null && !local.equals("0")? "WHERE LOCAL_NO =? " : "");
					//query +=(titleSearch != null ? "AND JBOARD_TITLE LIKE ? " : "");
					if (titleSearch != null && local==null) { //글제목 검색
						query+= " WHERE JBOARD_TITLE LIKE ? ";
					}else if(titleSearch != null&& local!=null &&!local.equals("0")) {
						query+= " AND JBOARD_TITLE LIKE ? ";
					}
					
					switch((listSearch==null)? listSearch = "latestposts" : listSearch) {
					case("latestposts") : query+= "ORDER BY JBOARD_DATE DESC)) "; break;
					case("highprice") :  query+= "ORDER BY JBOARD_PRICE DESC)) "; break;
					case("lowprice") :  query+= "ORDER BY JBOARD_PRICE ASC)) "; break;
					case("highlike") : query+= "ORDER BY JBOARD_LIKE DESC)) "; break;
					}
					query +="WHERE JBOARD_CHECK = 'Y' ";
					query +=" AND RNUM >=? AND RNUM <=? ";
			
					
				//System.out.println(query);
			
			int startRow = (currentPage - 1) * limit + 1; 
			int endRow = startRow + limit - 1;
			try {
				 	if(local== null && titleSearch == null){
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
				 	}else if (local !=null&& titleSearch ==null){
				 		pstmt = conn.prepareStatement(query);
						pstmt.setString(1, local);
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
				 	} else if (local !=null && titleSearch != null) {
				 		pstmt = conn.prepareStatement(query);
				 		pstmt.setString(1, local);
				 		pstmt.setString(2, "%" + titleSearch +"%");
				 		pstmt.setInt(3, startRow);
						pstmt.setInt(4, endRow);
				 	}else if(local==null && titleSearch != null){
				 		pstmt = conn.prepareStatement(query);
				 		pstmt.setString(1, "%" +titleSearch +"%");
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
				 	}
			
				 	
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						Jboard jboard = new Jboard();
						
						jboard.setJboardNo(rset.getInt("jboard_no"));
						jboard.setJboardTitle(rset.getString("jboard_title"));
						jboard.setJboardContent(rset.getString("jboard_content"));
						jboard.setJboardPrice(rset.getInt("jboard_price"));
						jboard.setJboardDate(rset.getDate("jboard_date"));
						jboard.setJboardUpdate(rset.getDate("jboard_update"));
						jboard.setJboardCount(rset.getInt("jboard_count"));
						jboard.setJboardLike(rset.getInt("jboard_like"));
						jboard.setJboardOrignalFilePath1(rset.getString("JFILES_ORIGINAL_FILEPATH1"));
						jboard.setJboardRenameFilePath1(rset.getString("JFILES_RENAME_FILEPATH1"));
						jboard.setJboardOrignalFilePath2(rset.getString("JFILES_ORIGINAL_FILEPATH2"));
						jboard.setJboardRenameFilePath2(rset.getString("JFILES_RENAME_FILEPATH2"));
						jboard.setJboardOrignalFilePath3(rset.getString("JFILES_ORIGINAL_FILEPATH3"));
						jboard.setJboardRenameFilePath3(rset.getString("JFILES_RENAME_FILEPATH3"));
						jboard.setJboardOrignalFilePath4(rset.getString("JFILES_ORIGINAL_FILEPATH4"));
						jboard.setJboardRenameFilePath4(rset.getString("JFILES_RENAME_FILEPATH4"));
						jboard.setJboardCheck(rset.getString("jboard_check"));
						jboard.setJboardMeet(rset.getString("jboard_meet"));
						jboard.setJboardPost(rset.getString("jboard_post"));
						jboard.setMemberId(rset.getString("member_id"));
						jboard.setLocalNo(rset.getString("local_no"));
						jboard.setMemberIp(rset.getString("member_ip"));
						list.add(jboard);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		return list; 
	}

		public Jboard selectJboard(Connection conn, int jboardNo) {
			Jboard jboard = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "SELECT JBOARD_NO, JBOARD_TITLE, JBOARD_CONTENT," 
								   +"JBOARD_PRICE, JBOARD_DATE , JBOARD_UPDATE," 
								   +"JBOARD_COUNT, JBOARD_LIKE, JFILES_ORIGINAL_FILEPATH1, JFILES_RENAME_FILEPATH1," 
								   +"JFILES_ORIGINAL_FILEPATH2, JFILES_RENAME_FILEPATH2 , JFILES_ORIGINAL_FILEPATH3,"
								   +"JFILES_RENAME_FILEPATH3, JFILES_ORIGINAL_FILEPATH4 , JFILES_RENAME_FILEPATH4,"
								   +"JBOARD_CHECK , JBOARD_MEET, JBOARD_POST, MEMBER_ID, LOCAL_NO,  SUBSTR(MEMBER_IP,1,INSTR(MEMBER_IP,'.',1,2))" 
								   +"FROM JBOARD "  
								   +"WHERE JBOARD_NO= ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, jboardNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					jboard = new Jboard();
					
					jboard.setJboardNo(rset.getInt("jboard_no"));
					jboard.setJboardTitle(rset.getString("jboard_title"));
					jboard.setJboardContent(rset.getString("jboard_content"));
					jboard.setJboardPrice(rset.getInt("jboard_price"));
					jboard.setJboardDate(rset.getDate("jboard_date"));
					jboard.setJboardUpdate(rset.getDate("jboard_update"));
					jboard.setJboardCount(rset.getInt("jboard_count"));
					jboard.setJboardLike(rset.getInt("jboard_like"));
					jboard.setJboardOrignalFilePath1(rset.getString("JFILES_ORIGINAL_FILEPATH1"));
					jboard.setJboardRenameFilePath1(rset.getString("JFILES_RENAME_FILEPATH1"));
					jboard.setJboardOrignalFilePath2(rset.getString("JFILES_ORIGINAL_FILEPATH2"));
					jboard.setJboardRenameFilePath2(rset.getString("JFILES_RENAME_FILEPATH2"));
					jboard.setJboardOrignalFilePath3(rset.getString("JFILES_ORIGINAL_FILEPATH3"));
					jboard.setJboardRenameFilePath3(rset.getString("JFILES_RENAME_FILEPATH3"));
					jboard.setJboardOrignalFilePath4(rset.getString("JFILES_ORIGINAL_FILEPATH4"));
					jboard.setJboardRenameFilePath4(rset.getString("JFILES_RENAME_FILEPATH4"));
					jboard.setJboardCheck(rset.getString("jboard_check"));
					jboard.setJboardMeet(rset.getString("jboard_meet"));
					jboard.setJboardPost(rset.getString("jboard_post"));
					jboard.setMemberId(rset.getString("member_id"));
					jboard.setLocalNo(rset.getString("local_no"));
					jboard.setMemberIp(rset.getString("SUBSTR(MEMBER_IP,1,INSTR(MEMBER_IP,'.',1,2))"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return jboard;
		}

		public int addReadCount(Connection conn, int jboardNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "update jboard set jboard_count= jboard_count + 1 "
					+ "where jboard_no = ?";
			
			try {
					pstmt = conn.prepareStatement(query);
					
					pstmt.setInt(1, jboardNo);
					
					result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}

		public int jboardUpdate(Connection conn, Jboard jboard) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "update jboard set jboard_title = ?, jboard_content  = ? , jboard_price = ?,  "
					+ "jboard_update = default , "
					+ "jfiles_original_filepath1 = ? , jfiles_rename_filepath1 = ? , jfiles_original_filepath2 = ? , "
					+ "jfiles_rename_filepath2 = ? , jfiles_original_filepath3 = ? , jfiles_rename_filepath3 = ? , "
					+ "jfiles_original_filepath4 = ? , jfiles_rename_filepath4 = ? , jboard_meet = ?, jboard_post= ?, local_no= ?, member_ip= ? "
					+ "where jboard_no = ?";
			try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, jboard.getJboardTitle());
					pstmt.setString(2, jboard.getJboardContent());
					pstmt.setInt(3,  jboard.getJboardPrice());
					pstmt.setString(4, jboard.getJboardOrignalFilePath1());
					pstmt.setString(5, jboard.getJboardRenameFilePath1());
					pstmt.setString(6, jboard.getJboardOrignalFilePath2());
					pstmt.setString(7, jboard.getJboardRenameFilePath2());
					pstmt.setString(8, jboard.getJboardOrignalFilePath3());
					pstmt.setString(9, jboard.getJboardRenameFilePath3());
					pstmt.setString(10, jboard.getJboardOrignalFilePath4());
					pstmt.setString(11, jboard.getJboardRenameFilePath4());
					pstmt.setString(12, jboard.getJboardMeet());
					pstmt.setString(13,  jboard.getJboardPost());
					pstmt.setString(14, jboard.getLocalNo());
					pstmt.setString(15, jboard.getMemberIp());
					pstmt.setInt(16,  jboard.getJboardNo());
					result = pstmt.executeUpdate();
		
			} catch (Exception e) {
				 e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return result;
}

		public int jboardDelete(Connection conn, int jboardNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "delete from jboard where jboard_no = ?";
			try {
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1,  jboardNo);
					
					result = pstmt.executeUpdate();
			} catch (Exception e) {
				 e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return result;
			
		}

		public ArrayList<Jboard> selectNewTop3(Connection conn) {
			ArrayList<Jboard> list = new ArrayList<Jboard>();
			Statement stmt = null;
			ResultSet rset = null;

			String query = "SELECT * " + 
								"FROM (SELECT ROWNUM RNUM, JBOARD_NO, JBOARD_TITLE, JBOARD_PRICE, "
								+ " JBOARD_LIKE, JFILES_RENAME_FILEPATH1, JBOARD_DATE " 
								+"FROM (SELECT * FROM JBOARD"  
								+" ORDER BY JBOARD_DATE DESC))" 
								+"WHERE RNUM >=1 AND RNUM <=4";
			try {
					stmt = conn.createStatement();
					rset = stmt.executeQuery(query);
				while(rset.next()) {
					Jboard jboard = new Jboard();

					jboard.setJboardNo(rset.getInt("jboard_no"));
					jboard.setJboardTitle(rset.getString("jboard_title"));
					jboard.setJboardPrice(rset.getInt("jboard_price"));
					jboard.setJboardLike(rset.getInt("jboard_like"));
					jboard.setJboardRenameFilePath1(rset.getString("JFILES_RENAME_FILEPATH1"));
					list.add(jboard);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(stmt);
			}
			return list;
		}
		
		public int insertJboardLike(Connection conn, Jboard jboard) {
			int result = 0;
			
			PreparedStatement pstmt = null;

			String query = "insert into jboard_like values  (?,?)";
			try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1,  jboard.getMemberId());
					pstmt.setInt(2, jboard.getJboardNo());
					result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return result;
	}

		public int jboardLikeUp(Connection conn, int jboardNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "update jboard set jboard_like= jboard_like + 1 "
					+ "where jboard_no = ?";
			
			try {
					pstmt = conn.prepareStatement(query);
					
					pstmt.setInt(1, jboardNo);
					
					result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}

		public int jboardLikeDown(Connection conn, int jboardNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "update jboard set jboard_like= jboard_like - 1 "
					+ "where jboard_no = ?";
			
			try {
					pstmt = conn.prepareStatement(query);
					
					pstmt.setInt(1, jboardNo);
					
					result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
		}

		public int jboardDeleteLike(Connection conn, Jboard jboard) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "delete from jboard_like where member_id = ? and jboard_no = ?";
			try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, jboard.getMemberId());
					pstmt.setInt(2, jboard.getJboardNo());
					result = pstmt.executeUpdate();
			} catch (Exception e) {
				 e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return result;
			
		}
		public ArrayList<Jboard> getLikeMemberList(Connection conn, int jboardno) {
			ArrayList<Jboard> list = new ArrayList<Jboard>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;

			String query = "SELECT MEMBER_ID FROM JBOARD_LIKE WHERE JBOARD_NO = ?";
			try {
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, jboardno);
					rset = pstmt.executeQuery();
				while(rset.next()) {
					Jboard jboard = new Jboard();
					jboard.setMemberId(rset.getString("member_id"));
					list.add(jboard);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return list;
		}

		public int getOneDayLimitCount(Connection conn, String memberId) {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			//System.out.println( "로컬값 :" +local + "titleSearch값 " + titleSearch);
			String query  = "SELECT COUNT(*) FROM JBOARD "
							+"WHERE MEMBER_ID = ? AND TO_CHAR(JBOARD_DATE,'YYYYMMDD') = TO_CHAR(SYSDATE,'YYYYMMDD')";
			
			try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, memberId);
					rset = pstmt.executeQuery();
					
					if (rset.next()) {
						listCount = rset.getInt(1);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return listCount;
	}

		public int getMyListCount(Connection conn, String local, String titleSearch, String memberId) {
			int listCount = 0;
			Statement stmt = null;
			ResultSet rset = null;
			String query  = "SELECT COUNT(*) FROM JBOARD WHERE MEMBER_ID = \'" + memberId + "\' "
			+(local != null && !local.equals("0") ? "AND LOCAL_NO ="+local : "");
			
			if (titleSearch != null ) {
				query+= " AND JBOARD_TITLE LIKE '%" + titleSearch + "%'";
			}
			try {
					stmt = conn.createStatement();
					rset = stmt.executeQuery(query);
					
					if (rset.next()) {
						listCount = rset.getInt(1);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(stmt);
			}
			return listCount;
	}

		public ArrayList<Jboard> selectMyList(Connection conn, int currentPage, int limit, String local, String listSearch, String titleSearch, String memberId ) {
			ArrayList<Jboard> list = new ArrayList <Jboard>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "SELECT * " + 
					"FROM (SELECT ROWNUM RNUM, JBOARD_NO, JBOARD_TITLE, JBOARD_CONTENT, " + 
					"JBOARD_PRICE, JBOARD_DATE , JBOARD_UPDATE, " + 
					"JBOARD_COUNT, JBOARD_LIKE, JFILES_ORIGINAL_FILEPATH1, JFILES_RENAME_FILEPATH1, " + 
					"JFILES_ORIGINAL_FILEPATH2, JFILES_RENAME_FILEPATH2 , JFILES_ORIGINAL_FILEPATH3, " +
					"JFILES_RENAME_FILEPATH3, JFILES_ORIGINAL_FILEPATH4 , JFILES_RENAME_FILEPATH4, " +
					"JBOARD_CHECK , JBOARD_MEET, JBOARD_POST, MEMBER_ID, LOCAL_NO, MEMBER_IP "+
					"FROM (SELECT * FROM JBOARD WHERE MEMBER_ID = \'" + memberId + "\' " +
					(local != null && !local.equals("0")? "AND LOCAL_NO =? " : "")+
					(titleSearch != null? "AND JBOARD_TITLE LIKE ? " : ""); 
						
					
					switch((listSearch==null)? listSearch = "latestposts" : listSearch) {
					case("latestposts") : query+= "ORDER BY JBOARD_DATE DESC)) "; break;
					case("highprice") :  query+= "ORDER BY JBOARD_PRICE DESC)) "; break;
					case("lowprice") :  query+= "ORDER BY JBOARD_PRICE ASC)) "; break;
					case("highlike") : query+= "ORDER BY JBOARD_LIKE DESC)) "; break;
					}
					query +="WHERE JBOARD_CHECK = 'Y' ";
					query +=" AND RNUM >=? AND RNUM <=? ";
			
					
				//System.out.println(query);
			
			int startRow = (currentPage - 1) * limit + 1; 
			int endRow = startRow + limit - 1;
			try {
				 	if(local== null && titleSearch == null){
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
				 	}else if (local !=null&& titleSearch ==null){
				 		pstmt = conn.prepareStatement(query);
						pstmt.setString(1, local);
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
				 	} else if (local !=null && titleSearch != null) {
				 		pstmt = conn.prepareStatement(query);
				 		pstmt.setString(1, local);
				 		pstmt.setString(2, "%" + titleSearch +"%");
				 		pstmt.setInt(3, startRow);
						pstmt.setInt(4, endRow);
				 	}else if(local==null && titleSearch != null){
				 		pstmt = conn.prepareStatement(query);
				 		pstmt.setString(1, "%" +titleSearch +"%");
						pstmt.setInt(2, startRow);
						pstmt.setInt(3, endRow);
				 	}
			
				 	
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						Jboard jboard = new Jboard();
						
						jboard.setJboardNo(rset.getInt("jboard_no"));
						jboard.setJboardTitle(rset.getString("jboard_title"));
						jboard.setJboardContent(rset.getString("jboard_content"));
						jboard.setJboardPrice(rset.getInt("jboard_price"));
						jboard.setJboardDate(rset.getDate("jboard_date"));
						jboard.setJboardUpdate(rset.getDate("jboard_update"));
						jboard.setJboardCount(rset.getInt("jboard_count"));
						jboard.setJboardLike(rset.getInt("jboard_like"));
						jboard.setJboardOrignalFilePath1(rset.getString("JFILES_ORIGINAL_FILEPATH1"));
						jboard.setJboardRenameFilePath1(rset.getString("JFILES_RENAME_FILEPATH1"));
						jboard.setJboardOrignalFilePath2(rset.getString("JFILES_ORIGINAL_FILEPATH2"));
						jboard.setJboardRenameFilePath2(rset.getString("JFILES_RENAME_FILEPATH2"));
						jboard.setJboardOrignalFilePath3(rset.getString("JFILES_ORIGINAL_FILEPATH3"));
						jboard.setJboardRenameFilePath3(rset.getString("JFILES_RENAME_FILEPATH3"));
						jboard.setJboardOrignalFilePath4(rset.getString("JFILES_ORIGINAL_FILEPATH4"));
						jboard.setJboardRenameFilePath4(rset.getString("JFILES_RENAME_FILEPATH4"));
						jboard.setJboardCheck(rset.getString("jboard_check"));
						jboard.setJboardMeet(rset.getString("jboard_meet"));
						jboard.setJboardPost(rset.getString("jboard_post"));
						jboard.setMemberId(rset.getString("member_id"));
						jboard.setLocalNo(rset.getString("local_no"));
						jboard.setMemberIp(rset.getString("member_ip"));
						list.add(jboard);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		return list; 
	}

}
