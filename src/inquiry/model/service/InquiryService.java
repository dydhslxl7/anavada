package inquiry.model.service;

import static common.JDBCTemp.*;
import java.sql.Connection;
import java.util.ArrayList;

import inquiry.model.dao.InquiryDao;
import inquiry.model.vo.Inquiry;
import notice.model.vo.Notice;

public class InquiryService {
	private InquiryDao idao = new InquiryDao();
	
	public InquiryService() {}

	public int getListCount() {
		Connection conn = getConnection();
		int totalList = idao.getListCount(conn);
		close(conn);
		return totalList;
	}
	
	public int getListCount(String user) {
		Connection conn = getConnection();
		int totalList = idao.getListCount(conn, user);
		close(conn);
		return totalList;
	}
	
	public int getListCount(String column, String keyword) {
		Connection conn = getConnection();
		int totalList = idao.getListCount(conn, column, keyword);
		close(conn);
		return totalList;
	}

	public int getListCount(String column, String keyword, String id) {
		Connection conn = getConnection();
		int totalList = idao.getListCount(conn, column, keyword, id);
		close(conn);
		return totalList;
	}

	public ArrayList<Inquiry> selectAll(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Inquiry> list = idao.selectAll(conn, currentPage, limit);
		close(conn);
		return list;
	}
	
	public ArrayList<Inquiry> selectAllUser(int currentPage, int limit, String user) {
		Connection conn = getConnection();
		ArrayList<Inquiry> list = idao.selectAllUser(conn, currentPage, limit, user);
		close(conn);
		return list;
	}
	
	public ArrayList<Inquiry> searchTCW(String column, String keyword, int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Inquiry> list = idao.searchTCW(conn, column, keyword, currentPage, limit);
		close(conn);
		return list;
	}
	
	public ArrayList<Inquiry> searchUserTC(String column, String keyword, int currentPage, int limit, String id) {
		Connection conn = getConnection();
		ArrayList<Inquiry> list = idao.searchUserTC(conn, column, keyword, currentPage, limit, id);
		close(conn);
		return list;
	}

	public Inquiry selectOne(int no) {
		Connection conn = getConnection();
		Inquiry inquiry = idao.selectOne(conn, no);
		close(conn);
		return inquiry;
	}

	public int insertInquiry(Inquiry inquiry) {
		Connection conn = getConnection();
		int result = idao.insertInquiry(conn, inquiry);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public int deleteInquiry(int no) {
		Connection conn = getConnection();
		int result = idao.deleteInquiry(conn, no);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public int deleteFiles(int no, int rnum) {
		Connection conn = getConnection();
		int result = idao.deleteInquiry(conn, no, rnum);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public String selectRfiles(int no, int rnum) {
		Connection conn = getConnection();
		String rfile = idao.selectRfiles(conn, no, rnum);
		close(conn);
		return rfile;
	}

	public int updateInquiry(Inquiry inquiry) {
		Connection conn = getConnection();
		int result = idao.updateInquiry(conn, inquiry);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public void changeIqAnswer(String change, int iqNo) {
		Connection conn = getConnection();
		int result = idao.changeIqAnswer(conn, change, iqNo);
		if(result > 0)
			commit(conn);
		else rollback(conn);
	}
	
}
