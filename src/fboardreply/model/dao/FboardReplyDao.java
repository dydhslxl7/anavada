package fboardreply.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fboardreply.model.vo.FboardReply;
import fboard.model.vo.Fboard;

public class FboardReplyDao {
	
	public FboardReplyDao() {
		
	}

	// 댓글 가지고오기
	public ArrayList<FboardReply> selectList(Connection conn, int fboardNo) {
		ArrayList<FboardReply> list = new ArrayList<FboardReply>();
		PreparedStatement stmt = null;
		ResultSet rset = null;

		String query = "select * from fboard_reply where fboard_no = ? order by fboardreply_no desc";

		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, fboardNo);
			
			rset = stmt.executeQuery();

			while (rset.next()) {
				FboardReply fboardR = new FboardReply();

				fboardR.setFboardReplyNo(rset.getInt(1));
				fboardR.setFboardNo(rset.getString(2));
				fboardR.setMemberId(rset.getString(3));
				fboardR.setFboardReplyContent(rset.getString(4));
				fboardR.setFboardReplyLev(rset.getInt(5));
				fboardR.setFboardReplyRef(rset.getInt(6));
				fboardR.setFboardReplyCreatDate(rset.getString(7));

				list.add(fboardR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	
	//댓글 입력하기
	public int insertFboardReply(Connection conn, FboardReply reply) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into fboard_reply values(FBREPLY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, (select to_char(sysdate, 'YYYY.MM.DD HH:MI:SS') from dual))";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getFboardNo());
			pstmt.setString(2, reply.getMemberId());
			pstmt.setString(3, reply.getFboardReplyContent());
			pstmt.setInt(4, reply.getFboardReplyLev());
			pstmt.setInt(5, reply.getFboardReplyRef());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//댓글 삭제하기
	public int deleteFboardReply(Connection conn, int fboardReplyNo, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from fboard_reply where FBOARDREPLY_NO = ? and member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fboardReplyNo);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

}
