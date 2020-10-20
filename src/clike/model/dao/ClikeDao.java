package clike.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static common.JDBCTemp.*;

public class ClikeDao {
	public ClikeDao() {}

	public int likeable(Connection conn, int cboardNum, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into clike values(?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cboardNum);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
