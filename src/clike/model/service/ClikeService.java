package clike.model.service;

import clike.model.dao.ClikeDao;
import static common.JDBCTemp.*;

import java.sql.Connection;

public class ClikeService {
	private ClikeDao cldao = new ClikeDao();
	public ClikeService() {}

	public int likeable(int cboardNum, String memberId) {
		Connection conn = getConnection();
		int result = cldao.likeable(conn, cboardNum, memberId);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
}
