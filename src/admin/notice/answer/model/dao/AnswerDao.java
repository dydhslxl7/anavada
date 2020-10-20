package admin.notice.answer.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import admin.notice.answer.model.vo.Answer;

public class AnswerDao {
	
	public AnswerDao() {}

	public Answer selectOne(Connection conn, int no) {
		Answer answer = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from answer where iq_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				answer = new Answer();
				answer.setAnNo(rset.getInt("an_no"));
				answer.setAnContent(rset.getString("an_content"));
				answer.setAnDate(rset.getDate("an_date"));
				answer.setIqNo(rset.getInt("iq_no"));
				answer.setIqId(rset.getString("iq_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return answer;
	}

	public int deleteAnswer(Connection conn, int anNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from answer where an_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, anNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAnswer(Connection conn, Answer answer) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update answer set an_content = ?, an_date = sysdate where an_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, answer.getAnContent());
			pstmt.setInt(2, answer.getAnNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAnswer(Connection conn, Answer answer) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into answer values ((select max(an_no)+1 from answer), ?, sysdate, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, answer.getAnContent());
			pstmt.setInt(2, answer.getIqNo());
			pstmt.setString(3, answer.getIqId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int searchAnNo(Connection conn, int iqNo) {
		int anNo = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select an_no from answer where iq_no = " + iqNo;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				anNo = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		
		return anNo;
	}
	
}
