package faq.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import faq.model.vo.Faq;
import notice.model.vo.Notice;

public class FaqDao {
	public FaqDao() {}

	public ArrayList<Faq> selectAll(Connection conn) {
		ArrayList<Faq> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from faq";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Faq faq = new Faq();
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setFaqId(rset.getString("faq_id"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setFaqCount(rset.getInt("faq_count"));
				faq.setFaqCategory(rset.getInt("faq_category"));
				
				list.add(faq);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList<Faq> selectCategory(Connection conn, int no) {
		ArrayList<Faq> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from faq where faq_category = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Faq faq = new Faq();
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setFaqId(rset.getString("faq_id"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setFaqCount(rset.getInt("faq_count"));
				faq.setFaqCategory(rset.getInt("faq_category"));
				
				list.add(faq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}
///////////////////////////////////////////////////////////////////
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from faq";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next())
				listCount = rset.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}

		return listCount;
	}
	
	public int getListCount(Connection conn, String column ,String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from faq ";
		
		if(column.equals("t"))
			query += "where faq_title like ?";
		else if(column.equals("c")) query += "where faq_content like ?";
		else query += "where faq_category = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			if(!column.equals("cate"))
			pstmt.setString(1, "%"+keyword+"%");
			else pstmt.setInt(1, Integer.parseInt(keyword));
			rset = pstmt.executeQuery();
			
			if(rset.next())
				listCount = rset.getInt(1);
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Faq> selectAll(Connection conn, int currentPage, int limit) {
		ArrayList<Faq> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from (select rank() over (order by faq_no desc) rnum, faq_no, faq_id, faq_title, faq_content,"
				+ " faq_date, faq_count, faq_category from faq) where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit +1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Faq faq = new Faq();
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setFaqId(rset.getString("faq_id"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setFaqCount(rset.getInt("faq_count"));
				faq.setFaqCategory(rset.getInt("faq_category"));;
				
				list.add(faq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Faq> searchTCC(Connection conn, int currentPage, int limit, String keyword, String column) {
		ArrayList<Faq> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * "
					 + "from (select rank() over (order by faq_no desc) rnum, faq_no, faq_id, faq_title, faq_content, faq_date, faq_count, faq_category "
					 	   + "from faq ";
		
		if(column.equals("t"))
			query += "where faq_title like ?) where rnum >= ? and rnum <= ?";
		else if(column.equals("c")) query += "where faq_content like ?) where rnum >= ? and rnum <= ?";
		else query += "where faq_category = ?) where rnum >= ? and rnum <= ?";
		
		int startRow = (currentPage - 1) * limit +1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			if(!column.equals("cate"))
			pstmt.setString(1, "%" + keyword + "%");
			else pstmt.setInt(1, Integer.parseInt(keyword));
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Faq faq = new Faq();
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setFaqId(rset.getString("faq_id"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setFaqCount(rset.getInt("faq_count"));
				faq.setFaqCategory(rset.getInt("faq_category"));
				
				list.add(faq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertFaq(Connection conn, Faq faq) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "insert into faq values ((select max(faq_no)+1 from faq), 'admin', ?, ?, sysdate, 0, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getFaqContent());
			pstmt.setInt(3, faq.getFaqCategory());
			result = pstmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}System.out.println(result);
		return result; 
	}

	public Faq selectOne(Connection conn, int no) {
		Faq faq = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from faq where faq_no = ?";
		
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				faq = new Faq();
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setFaqId(rset.getString("faq_id"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));
				faq.setFaqDate(rset.getDate("faq_date"));
				faq.setFaqCount(rset.getInt("faq_count"));
				faq.setFaqCategory(rset.getInt("faq_category"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return faq;
	}
	
	public int updateFaq(Connection conn, Faq faq) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update faq set faq_title = ?, faq_content = ?, faq_date = sysdate where faq_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getFaqContent());
			pstmt.setInt(3, faq.getFaqNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteFaq(Connection conn, int[] checkedNum) {
		int result = 0;
		Statement stmt = null;
		
		String query = null;
		if(checkedNum.length == 1)
			query = "delete from faq where faq_no = " + checkedNum[0];
		else {
			query = "delete from faq where faq_no in (";
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
	
}
