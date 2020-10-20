package cboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cboard.model.vo.Cboard;
import static common.JDBCTemp.*;

public class CboardDao {
	public CboardDao() {
	}

	public ArrayList<Cboard> mySelectList(Connection conn, String memberID, int currentPage, int limit, String local,
			String search, String keyword) {
		ArrayList<Cboard> list = new ArrayList<Cboard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, cboard_no, member_id, cboard_title, cboard_content, "
				+ "cboard_date, cboard_lastmodified, cboard_viewcount, cboard_replycount, "
				+ "cboard_likecount, local_no, cfiles_original_filepath1, cfiles_rename_filepath1, cfiles_original_filepath2, "
				+ "cfiles_rename_filepath2, cfiles_original_filepath3, cfiles_rename_filepath3, cfiles_original_filepath4, "
				+ "cfiles_rename_filepath4 from (select * from cboard where member_id = ? "
				+ (local != null && !local.equals("0") && !local.equals("null") ? "and local_no = ? " : "");
		if (local != null && !local.equals("null")) {
			query += (search != null && search.equals("title") ? "and cboard_title like ? " : "");
			query += (search != null && search.equals("content") ? "and cboard_content like ? " : "");

		} else {
			query += (search != null && search.equals("title") ? "where cboard_title like ? " : "");
			query += (search != null && search.equals("content") ? "where cboard_content like ? " : "");
		}

		query += "order by cboard_no desc)) where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			int pstmtnum = 1;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(pstmtnum++, memberID);
			if (local != null && !local.equals("0")) {
				pstmt.setString(pstmtnum++, local);
			}
			if ((search != null && search.equals("")) || (search != null && search.equals("title"))
					|| (search != null && search.equals("content")) || (search != null && search.equals("writer"))) {
				pstmt.setString(pstmtnum++, "%" + keyword + "%");
			}
			pstmt.setInt(pstmtnum++, startRow);
			pstmt.setInt(pstmtnum++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Cboard cboard = new Cboard();

				cboard.setCboardNo(rset.getInt("cboard_no"));
				cboard.setMemberId(rset.getString("member_id"));
				cboard.setCboardTitle(rset.getString("cboard_title"));
				cboard.setCboardContent(rset.getString("cboard_content"));
				cboard.setDate(rset.getDate("cboard_date"));
				cboard.setLastmodifiedDate(rset.getDate("cboard_lastmodified"));
				cboard.setCboardViewCount(rset.getInt("cboard_viewcount"));
				cboard.setReplyCount(rset.getInt("cboard_replycount"));
				cboard.setLikeCount(rset.getInt("cboard_likecount"));
				cboard.setLocalNo(rset.getString("local_no"));
				cboard.setCfilesOriginalFilepath1(rset.getString("cfiles_original_filepath1"));
				cboard.setCfilesRenameFilepath1(rset.getString("cfiles_rename_filepath1"));
				cboard.setCfilesOriginalFilepath2(rset.getString("cfiles_original_filepath2"));
				cboard.setCfilesRenameFilepath2(rset.getString("cfiles_rename_filepath2"));
				cboard.setCfilesOriginalFilepath3(rset.getString("cfiles_original_filepath3"));
				cboard.setCfilesRenameFilepath3(rset.getString("cfiles_rename_filepath3"));
				cboard.setCfilesOriginalFilepath4(rset.getString("cfiles_original_filepath4"));
				cboard.setCfilesRenameFilepath4(rset.getString("cfiles_rename_filepath4"));

				list.add(cboard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getMyListCount(Connection conn, String memberID, String local, String search, String keyword) {
		int listCount = 0;

		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from cboard where member_id = \'" + memberID + "\' ";

		query += (local != null && !local.equals("0") ? "and local_no = \'" + local + "\' " : "");
		if (local != null && !local.equals("null")) {
			query += (search != null && search.equals("title") ? " and cboard_title like '%" + keyword + "%' " : " ");
			query += (search != null && search.equals("content") ? " and cboard_content like '%" + keyword + "%' " : "");
		} else {
			query += (search != null && search.equals("title") ? " where cboard_title like '%" + keyword + "%' " : " ");
			query += (search != null && search.equals("content") ? " where cboard_content like '%" + keyword + "%' "
					: "");
		}
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	public ArrayList<Cboard> selectList(Connection conn, int currentPage, int limit, String local, String search,
			String keyword) {
		ArrayList<Cboard> list = new ArrayList<Cboard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from (select rownum rnum, cboard_no, member_id, cboard_title, cboard_"
				+ "content, cboard_date, cboard_lastmodified, cboard_viewcount, cboard_replycount"
				+ ", cboard_likecount, local_no, cfiles_origi"
				+ "nal_filepath1, cfiles_rename_filepath1, cfiles_original_filepath2, cfiles_rena"
				+ "me_filepath2, cfiles_original_filepath3, cfiles_rename_filepath3, cfiles_origi"
				+ "nal_filepath4, cfiles_rename_filepath4 from (select * from cboard "
				+ (local != null && !local.equals("0") ? "where local_no = ? " : "");
		if (local != null && !local.equals("0")) {
			query += (search != null && search.equals("title") ? "and cboard_title like ? " : "");
			query += (search != null && search.equals("content") ? "and cboard_content like ? " : "");
			query += (search != null && search.equals("writer") ? "and member_id like ? " : "");
		} else {
			query += (search != null && search.equals("title") ? "where cboard_title like ? " : "");
			query += (search != null && search.equals("content") ? "where cboard_content like ? " : "");
			query += (search != null && search.equals("writer") ? "where member_id like ? " : "");
		}

		query += "order by cboard_no desc)) where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;

		try {
			int pstmtnum = 1;
			pstmt = conn.prepareStatement(query);
			if (local != null && !local.equals("0")) {
				pstmt.setString(pstmtnum++, local);
			}
			if ((search != null && search.equals("")) || (search != null && search.equals("title"))
					|| (search != null && search.equals("content")) || (search != null && search.equals("writer"))) {
				pstmt.setString(pstmtnum++, "%" + keyword + "%");
			}
			pstmt.setInt(pstmtnum++, startRow);
			pstmt.setInt(pstmtnum++, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Cboard cboard = new Cboard();

				cboard.setCboardNo(rset.getInt("cboard_no"));
				cboard.setMemberId(rset.getString("member_id"));
				cboard.setCboardTitle(rset.getString("cboard_title"));
				cboard.setCboardContent(rset.getString("cboard_content"));
				cboard.setDate(rset.getDate("cboard_date"));
				cboard.setLastmodifiedDate(rset.getDate("cboard_lastmodified"));
				cboard.setCboardViewCount(rset.getInt("cboard_viewcount"));
				cboard.setReplyCount(rset.getInt("cboard_replycount"));
				cboard.setLikeCount(rset.getInt("cboard_likecount"));
				cboard.setLocalNo(rset.getString("local_no"));
				cboard.setCfilesOriginalFilepath1(rset.getString("cfiles_original_filepath1"));
				cboard.setCfilesRenameFilepath1(rset.getString("cfiles_rename_filepath1"));
				cboard.setCfilesOriginalFilepath2(rset.getString("cfiles_original_filepath2"));
				cboard.setCfilesRenameFilepath2(rset.getString("cfiles_rename_filepath2"));
				cboard.setCfilesOriginalFilepath3(rset.getString("cfiles_original_filepath3"));
				cboard.setCfilesRenameFilepath3(rset.getString("cfiles_rename_filepath3"));
				cboard.setCfilesOriginalFilepath4(rset.getString("cfiles_original_filepath4"));
				cboard.setCfilesRenameFilepath4(rset.getString("cfiles_rename_filepath4"));

				list.add(cboard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCount(Connection conn, String local, String search, String keyword) {
		int listCount = 0;

		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from cboard ";

		query += (local != null && !local.equals("0") ? "where local_no = " + local : "");
		if (local != null && !local.equals("0")) {
			query += (search != null && search.equals("title") ? " and cboard_title like '%" + keyword + "%' " : "");
			query += (search != null && search.equals("content") ? " and cboard_content like '%" + keyword + "%' "
					: "");
			query += (search != null && search.equals("writer") ? " and member_id like '%" + keyword + "%' " : "");
		} else {
			query += (search != null && search.equals("title") ? " where cboard_title like '%" + keyword + "%' " : "");
			query += (search != null && search.equals("content") ? " where cboard_content like '%" + keyword + "%' "
					: "");
			query += (search != null && search.equals("writer") ? " where member_id like '%" + keyword + "%' " : "");
		}
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	public int addReadCount(Connection conn, int cboardNum) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update cboard set cboard_viewcount = cboard_viewcount + 1 where cboard_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cboardNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Cboard selectCboard(Connection conn, int cboardNum) {
		Cboard cboard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from cboard where cboard_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cboardNum);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				cboard = new Cboard();
				cboard.setCboardNo(cboardNum);
				cboard.setMemberId(rset.getString("member_id"));
				cboard.setCboardTitle(rset.getString("cboard_title"));
				cboard.setCboardContent(rset.getString("cboard_content"));
				cboard.setDate(rset.getDate("cboard_date"));
				cboard.setLastmodifiedDate(rset.getDate("cboard_lastmodified"));
				cboard.setCboardViewCount(rset.getInt("cboard_viewcount"));
				cboard.setReplyCount(rset.getInt("cboard_replycount"));
				cboard.setLikeCount(rset.getInt("cboard_likecount"));
				cboard.setLocalNo(rset.getString("local_no"));
				cboard.setCfilesOriginalFilepath1(rset.getString("cfiles_original_filepath1"));
				cboard.setCfilesRenameFilepath1(rset.getString("cfiles_rename_filepath1"));
				cboard.setCfilesOriginalFilepath2(rset.getString("cfiles_original_filepath2"));
				cboard.setCfilesRenameFilepath2(rset.getString("cfiles_rename_filepath2"));
				cboard.setCfilesOriginalFilepath3(rset.getString("cfiles_original_filepath3"));
				cboard.setCfilesRenameFilepath3(rset.getString("cfiles_rename_filepath3"));
				cboard.setCfilesOriginalFilepath4(rset.getString("cfiles_original_filepath4"));
				cboard.setCfilesRenameFilepath4(rset.getString("cfiles_rename_filepath4"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return cboard;
	}

	public int getAllListCount(Connection conn) {
		int allListCount = 0;

		Statement stmt = null;
		ResultSet rset = null;

		String query = "select max(cboard_no) from cboard";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				allListCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return allListCount;
	}

	public int insertCboard(Connection conn, Cboard cboard) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into cboard values(cboard_seq.nextval, ?, ?, ?, sysdate, null, default,"
				+ " default, default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cboard.getMemberId());
			pstmt.setString(2, cboard.getCboardTitle());
			pstmt.setString(3, cboard.getCboardContent());
			pstmt.setString(4, cboard.getLocalNo());
			pstmt.setString(5, cboard.getCfilesOriginalFilepath1());
			pstmt.setString(6, cboard.getCfilesRenameFilepath1());
			pstmt.setString(7, cboard.getCfilesOriginalFilepath2());
			pstmt.setString(8, cboard.getCfilesRenameFilepath2());
			pstmt.setString(9, cboard.getCfilesOriginalFilepath3());
			pstmt.setString(10, cboard.getCfilesRenameFilepath3());
			pstmt.setString(11, cboard.getCfilesOriginalFilepath4());
			pstmt.setString(12, cboard.getCfilesRenameFilepath4());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteCboard(Connection conn, int cboardNum) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from cboard where cboard_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cboardNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateCboard(Connection conn, Cboard cboard) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update cboard set cboard_title = ?, cboard_content = ?, cfiles_original_filepa"
				+ "th1 = ?, cfiles_rename_filepath1 = ?, cfiles_original_filepath2 = ?, cfiles_re"
				+ "name_filepath2 = ?,  cfiles_original_filepath3 = ?, cfiles_rename_filepath3 = "
				+ "?, cfiles_original_filepath4 = ?, cfiles_rename_filepath4 = ?, cboard_lastmodi"
				+ "fied = sysdate, local_no = ? where cboard_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cboard.getCboardTitle());
			pstmt.setString(2, cboard.getCboardContent());
			pstmt.setString(3, cboard.getCfilesOriginalFilepath1());
			pstmt.setString(4, cboard.getCfilesRenameFilepath1());
			pstmt.setString(5, cboard.getCfilesOriginalFilepath2());
			pstmt.setString(6, cboard.getCfilesRenameFilepath2());
			pstmt.setString(7, cboard.getCfilesOriginalFilepath3());
			pstmt.setString(8, cboard.getCfilesRenameFilepath3());
			pstmt.setString(9, cboard.getCfilesOriginalFilepath4());
			pstmt.setString(10, cboard.getCfilesRenameFilepath4());
			pstmt.setString(11, cboard.getLocalNo());
			pstmt.setInt(12, cboard.getCboardNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int upLikeCount(Connection conn, int cboardNum) {
		int result = 0;
		Statement stmt = null;

		String query = "update cboard set cboard_likecount = (cboard_likecount + 1) where cboard_no = " + cboardNum;

		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			;
		}
		return result;
	}

	public ArrayList<Cboard> selectTop2(Connection conn) {
		ArrayList<Cboard> list = new ArrayList<Cboard>();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum rnum, cboard_no, local_no, cboard_title, cboard_c"
				+ "ontent, cboard_likecount, cboard_viewcount from (select * from cboard order by"
				+ " cboard_viewcount desc))where rnum >= 1 and rnum <= 2";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Cboard cboard = new Cboard();

				cboard.setCboardNo(rset.getInt("cboard_no"));
				cboard.setLocalNo(rset.getString("local_no"));
				cboard.setCboardTitle(rset.getString("cboard_title"));
				cboard.setCboardContent(rset.getString("cboard_content"));
				cboard.setLikeCount(rset.getInt("cboard_likecount"));
				cboard.setCboardViewCount(rset.getInt("cboard_viewcount"));

				list.add(cboard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

}
