package fboard.model.service;

import static common.JDBCTemp.*;
import java.sql.Connection;
import java.util.ArrayList;

import fboard.model.dao.FboardDao;
import fboard.model.vo.Fboard;

public class FboardService {
	// DI
	private FboardDao fdao = new FboardDao();

	public FboardService() {
	}

	// (관리자)축제 추가 비교(축제 번호로)
	public int selectFboard(String fboardNo) {
		Connection conn = getConnection();
		int result = fdao.selectFboard(conn, fboardNo);
		close(conn);
		return result;
	}

	// (관리자) 축제 추가하기
	public int insertFboard(Fboard fboard) {
		Connection conn = getConnection();
		int result = fdao.insertFboard(conn, fboard);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);

		return result;
	}

	// (관리자) 축제 수정날짜 비교 (축제 번호, 수정날짜로)
	public int selectFboard(String fboardNo, String fesivalModifiedDate) {
		Connection conn = getConnection();
		int result = fdao.selectFboard(conn, fboardNo, fesivalModifiedDate);
		close(conn);
		return result;
	}

	// (관리자) 맵 용 축제 게시판 전체 리스트
	public ArrayList<Fboard> selectKList() {
		Connection conn = getConnection();
		ArrayList<Fboard> list = fdao.selectKList(conn);
		close(conn);
		return list;
	}

	// (관리자) 축제 수정날짜 확인 후 있으면 update하기
	public int updateFboard(Fboard fboard) {
		Connection conn = getConnection();
		int result = fdao.updateFboard(conn, fboard);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);

		return result;
	}

	// (관리자) 지난 축제들(이번년도 미만) delete하기
	public void deleteFboard() {
		Connection conn = getConnection();
		int result = fdao.deleteFboard(conn);
		int totaldelete = 0;
		if (result > 0) {
			commit(conn);
			totaldelete += 1;
		} else
			rollback(conn);
		close(conn);
		System.out.println("deletee된 개수 : " + totaldelete);
	}
	
	// (관리자) 축제게시판 전부 삭제하기
	public void deleteAllFboard() {
		Connection conn = getConnection();
		int result = fdao.deleteAllFboard(conn);
		if (result > 0) {
			commit(conn);
			System.out.println("전부 삭제되었습니다");
		} else
			rollback(conn);
		System.out.println("삭제 될게 없습니다");
		close(conn);

	}
	
	// 조회수 증가
	public int updateReadcount(String fboardNo) {
		Connection conn = getConnection();
		int result = fdao.updateReadcount(conn, fboardNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}

	// 축제 상세 보기
	public Fboard selectDetailFboard(String fboardNo) {
		Connection conn = getConnection();
		Fboard fboard = fdao.selectDetailFboard(conn, fboardNo);
		close(conn);
		return fboard;
	}

	// 축제게시판 목록 조회 가지고 오기
	public ArrayList<Fboard> selectList(String lastList, int locationSelect, String sortSelect, String title) {
		Connection conn = getConnection();
		ArrayList<Fboard> list = fdao.selectList(conn, lastList, locationSelect, sortSelect, title);
		close(conn);
		return list;
	}

	// 관리자 확인용 fboard 전체 가지고 오기
	public ArrayList<Fboard> selectFboardList() {
		Connection conn = getConnection();
		ArrayList<Fboard> list = fdao.selectFboardList(conn);
		close(conn);
		return list;
	}

	// 축제 종료일 기준 top8
	public ArrayList<Fboard> selectTop8() {
		Connection conn = getConnection();
		ArrayList<Fboard> list = fdao.selectTop8(conn);
		close(conn);
		return list;
	}

}
