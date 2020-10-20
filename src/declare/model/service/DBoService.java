package declare.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;
import java.util.ArrayList;

import declare.model.dao.DBoDao;
import declare.model.vo.DBo;

public class DBoService {
	private DBoDao dbodao = new DBoDao();
	public DBoService() {}
	
	public ArrayList<DBo> selectAll(){ //전체목록보기
		Connection conn = getConnection();
		ArrayList<DBo> list = dbodao.selectAll(conn);
		close(conn);
		return list;
	}
	public DBo selectOne(int dboNo) { //글상세보기
		Connection conn = getConnection();
		DBo dbo = dbodao.selectOne(conn, dboNo);
		close(conn);
		return dbo;
	}
	public int insertDBo(DBo dbo) { //글등록
		Connection conn = getConnection();
		int result = dbodao.insertDBo(conn, dbo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	public int updateDBo(DBo dbo) { //신고처리완료 수정
		Connection conn = getConnection();
		int result = dbodao.updateDBo(conn, dbo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	public int deleteDBo(int dboNo) {//글삭제
		Connection conn = getConnection();
		int result = dbodao.deleteDBo(conn, dboNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	public ArrayList<DBo>searchOne(String keyword){ //글검색기능
		Connection conn = getConnection();
		ArrayList<DBo> list = dbodao.searchOne(conn, keyword);
		close(conn);
		return list;
	}
	public int getListCount() {
		Connection conn = getConnection();
		int listCount = dbodao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public int getListCount(String column, String keyword) {
		Connection conn = getConnection();
		int listCount = dbodao.getListCount(conn, column, keyword);
		close(conn);
		return listCount;
	}

	public ArrayList<DBo> selectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<DBo> list = dbodao.selectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public ArrayList<DBo> searchTorC(int currentPage, int limit, String keyword, String column) {
		Connection conn = getConnection();
		ArrayList<DBo> list = dbodao.searchTorC(conn, currentPage, limit, keyword, column);
		close(conn);
		return list;
	}

	public int getListCount(String user) {
		Connection conn = getConnection();
		int listCount = dbodao.getListCount(conn, user);
		close(conn);
		return listCount;
	}

	public ArrayList<DBo> selectAllUser(int currentPage, int limit, String user) {
		Connection conn = getConnection();
		ArrayList<DBo> list = dbodao.selectAllUser(conn, currentPage, limit, user);
		close(conn);
		return list;
	}

}
