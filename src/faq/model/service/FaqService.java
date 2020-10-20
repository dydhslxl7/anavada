package faq.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import faq.model.dao.FaqDao;
import faq.model.vo.Faq;
import notice.model.vo.Notice;

import static common.JDBCTemp.*;

public class FaqService {
	
	private FaqDao fdao = new FaqDao();
	
	public FaqService() {}

	public ArrayList<Faq> selectAll() {
		Connection conn = getConnection();
		ArrayList<Faq> list = fdao.selectAll(conn);
		close(conn);
		return list;
	}

	public ArrayList<Faq> selectCategory(int no) {
		Connection conn = getConnection();
		ArrayList<Faq> list = fdao.selectCategory(conn, no);
		close(conn);
		return list;
	}
///////////////////////////////////////////////////////////////////
	public int getListCount() {
		Connection conn = getConnection();
		int result = fdao.getListCount(conn);
		close(conn);
		return result;
	}

	public int getListCount(String column, String keyword) {
		Connection conn = getConnection();
		int listCount = fdao.getListCount(conn, column, keyword);
		close(conn);
		return listCount;
	}
	
	public ArrayList<Faq> selectAll(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<Faq> list = fdao.selectAll(conn, currentPage, limit);
		close(conn);
		return list;
	}
	
	public ArrayList<Faq> searchTCC(int currentPage, int limit, String keyword, String column) {
		Connection conn = getConnection();
		ArrayList<Faq> list = fdao.searchTCC(conn, currentPage, limit, keyword, column);
		close(conn);
		return list;
	}

	public int insertFaq(Faq faq) {
		Connection conn = getConnection();
		int result = fdao.insertFaq(conn, faq);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}

	public Faq selectOne(int no) {
		Connection conn = getConnection();
		Faq faq = fdao.selectOne(conn, no);
		close(conn);
		return faq;
	}
	
	public int updateFaq(Faq faq) {
		Connection conn = getConnection();
		int result = fdao.updateFaq(conn, faq);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}
	
	public int deleteFaq(int[] checkedNum) {
		Connection conn = getConnection();
		int result = fdao.deleteFaq(conn, checkedNum);
		if(result > 0)
			commit(conn);
		else rollback(conn);
		return result;
	}
	
}
