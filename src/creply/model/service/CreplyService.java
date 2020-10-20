package creply.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import cboard.model.vo.Cboard;
import creply.model.dao.CreplyDao;
import creply.model.vo.Creply;

public class CreplyService {
	private CreplyDao crdao = new CreplyDao();
	
	public CreplyService() {}

	public ArrayList<Creply> creplyList(int cboardNo) {
		Connection conn = getConnection();
		ArrayList<Creply> rlist = crdao.selectList(conn, cboardNo);
		close(conn);
		return rlist;
	}

	public int allReplyCount(int cboardNum) {
		Connection conn = getConnection();
		int replyCount = crdao.getAllReplyListCount(conn, cboardNum);
		close(conn);
		return replyCount;
	}

	public int insertCreply(Creply creply) {
		Connection conn = getConnection();
		int result = crdao.insertCreply(conn, creply);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<Creply> subCreplyList(int cboardNum) {
		Connection conn = getConnection();
		ArrayList<Creply> rlist = crdao.selectSubReplyList(conn, cboardNum);
		close(conn);
		return rlist;
	}

	public int deleteCreply(int creplyNum, int depth) {
		Connection conn = getConnection();
		int result = crdao.deleteCreply(conn, creplyNum, depth);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateCreply(int creplyNum, String content) {
		Connection conn = getConnection();
		int result = crdao.updateCreply(conn, creplyNum, content);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
