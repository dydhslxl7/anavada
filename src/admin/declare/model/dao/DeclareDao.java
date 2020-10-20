package admin.declare.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import banner.model.vo.Banner;
import declare.model.vo.DBo;
import admin.declare.model.vo.Declare;

public class DeclareDao {
	public DeclareDao() {}
	
	public ArrayList<Declare>selectAll(Connection conn) {//신고목록전체조회
		ArrayList<Declare> list = new ArrayList<Declare>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from declare_admin order by declare_no desc";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Declare declare = new Declare();
				declare.setDeclareNo(rset.getInt("declare_no"));
				declare.setDeclareId(rset.getString("declare_id"));
				declare.setDeclareCount(rset.getInt("declare_count"));
				declare.setDeclareOk(rset.getString("declare_ok"));
								
				list.add(declare);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return list;
	}
	public ArrayList<Declare>selectOne(Connection conn, String keyword) { //black id검색기능
		ArrayList<Declare> list = new ArrayList<Declare>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * form declare_admin where declareId =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+ keyword + "%");
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Declare declare = new Declare();
				declare.setDeclareNo(rset.getInt("declare_no"));
				declare.setDeclareId(rset.getString("declare_id"));
				declare.setDeclareCount(rset.getInt("declare_count"));
				declare.setDeclareOk(rset.getString("declare_ok"));
				
				list.add(declare);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public int insertDeclare(Connection conn, Declare declare) { //new신고자등록
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into declare_admin values ((select max(declare_no) +1 from declare_admin), ?, 1, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, declare.getDeclareId());
			pstmt.setString(2, declare.getDeclareOk());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateDeclare(Connection conn, Declare declare) { //신고횟수, 제한설정 수정기능
		int result =0;
		PreparedStatement pstmt = null;
		
		String query = "update declare_admin set declare_ok = ? where declare_id = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, declare.getDeclareOk());
			pstmt.setString(2, declare.getDeclareId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int deleteDeclare(Connection conn, int[] checkedNum) { //신고자아이디 삭제
		int result = 0;
		Statement stmt = null;
		
		String query = null;
		if(checkedNum.length == 1)
			query = "delete from declare_admin where declare_no = " + checkedNum[0];
		else {
			query = "delete from declare_admin where declare_no in (";
			for(int i=0; i<checkedNum.length-1; i++) {
				query += checkedNum[i]+", ";
			}
			query += checkedNum[checkedNum.length-1]+")";
		}
		try {
			stmt = conn.createStatement();			
			result = stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from declare_admin";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()){
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

	public ArrayList<Declare> selectList(Connection conn, int currentPage, int limit) {
		ArrayList<Declare> list = new ArrayList<Declare>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rank() over(order by declare_no desc) rnum, declare_no, declare_id, declare_count, declare_ok from  declare_admin)  where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Declare declare = new Declare();
				declare.setDeclareNo(rset.getInt("declare_no"));
				declare.setDeclareId(rset.getString("declare_id"));
				declare.setDeclareCount(rset.getInt("declare_count"));
				declare.setDeclareOk(rset.getString("declare_ok"));
								
				list.add(declare);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int countUp(Connection conn, int dno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update declare_admin set declare_count = declare_count + 1 where declare_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
	   return result;
	}

	public int selectMember(Connection conn, String memberid) {
		PreparedStatement pstmt = null;
	      int result = 0;
	      
	      String query = "select * from declare_admin where declare_id = ?";
	      try {
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, memberid);
	         
	         result = pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         close(pstmt);
	      }
	      return result;
	   }



}
