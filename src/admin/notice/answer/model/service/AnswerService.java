package admin.notice.answer.model.service;

import static common.JDBCTemp.*;

import java.sql.Connection;

import admin.notice.answer.model.dao.AnswerDao;
import admin.notice.answer.model.vo.Answer;

public class AnswerService {
	private AnswerDao adao = new AnswerDao();
	
	public AnswerService() {}

	public Answer selectOne(int no) {
		Connection conn = getConnection();
		Answer answer = adao.selectOne(conn, no);
		close(conn);
		return answer;
	}

	public int deleteAnswer(int anNo) {
		Connection conn = getConnection();
		int result = adao.deleteAnswer(conn, anNo);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public int updateAnswer(Answer answer) {
		Connection conn = getConnection();
		int result = adao.updateAnswer(conn, answer);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public int insertAnswer(Answer answer) {
		Connection conn = getConnection();
		int result = adao.insertAnswer(conn, answer);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public int searchAnNo(int iqNo) {
		Connection conn = getConnection();
		int anNo = adao.searchAnNo(conn, iqNo);
		close(conn);
		return anNo;
	}
	
	
}
