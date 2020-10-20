package fboardreply.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import fboardreply.model.dao.FboardReplyDao;
import fboardreply.model.vo.FboardReply;
import fboard.model.vo.Fboard;

public class FboardReplyService {
	//DI
	private FboardReplyDao frdao = new FboardReplyDao();
	
	public FboardReplyService() {		
	}

	//댓글 가지고 오기
	public ArrayList<FboardReply> selectList(int fboardNo) {
		Connection conn = getConnection();
		ArrayList<FboardReply> list = frdao.selectList(conn, fboardNo);
		close(conn);
		return list;
	}

	//댓글 입력하기
	public int insertFboardReply(FboardReply reply ) {
		Connection conn = getConnection();
		int result = frdao.insertFboardReply(conn, reply);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}

	//댓글 삭제하기
	public int deleteFboardReply(int fboardReplyNo, String memberId) {
		Connection conn = getConnection();
		int result = frdao.deleteFboardReply(conn, fboardReplyNo, memberId);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}
	
	
}
