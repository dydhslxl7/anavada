package cboard.model.service;
import java.sql.Connection;
import java.util.ArrayList;
import cboard.model.dao.CboardDao;
import cboard.model.vo.Cboard;
import static common.JDBCTemp. *;

public class CboardService {
    private CboardDao cdao = new CboardDao();

    public CboardService() {}

    public ArrayList<Cboard> selectAll(
        int currentPage,
        int limit,
        String local,
        String search,
        String keyword
    ) {
        Connection conn = getConnection();
        ArrayList<Cboard> list = cdao.selectList(
            conn,
            currentPage,
            limit,
            local,
            search,
            keyword
        );
        close(conn);
        return list;
    }

    public int getListCount(String local, String search, String keyword) {
        Connection conn = getConnection();
        int listCount = cdao.getListCount(conn, local, search, keyword);
        close(conn);
        return listCount;
    }

    public void addReadCount(int cboardNum) {
        Connection conn = getConnection();
        int result = cdao.addReadCount(conn, cboardNum);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
    }

    public Cboard selectCboard(int cboardNum) {
        Connection conn = getConnection();
        Cboard cboard = cdao.selectCboard(conn, cboardNum);
        close(conn);
        return cboard;
    }

    public int getAllListCount() {
        Connection conn = getConnection();
        int listCount = cdao.getAllListCount(conn);
        close(conn);
        return listCount;
    }

    public int insertCboard(Cboard cboard) {
        Connection conn = getConnection();
        int result = cdao.insertCboard(conn, cboard);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int deleteCboard(int cboardNum) {
        Connection conn = getConnection();
        int result = cdao.deleteCboard(conn, cboardNum);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int updateCboard(Cboard cboard) {
        Connection conn = getConnection();
        int result = cdao.updateCboard(conn, cboard);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public int upLikeCount(int cboardNum) {
        Connection conn = getConnection();
        int result = cdao.upLikeCount(conn, cboardNum);
        if (result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }

    public ArrayList<Cboard> selectTop2() {
        Connection conn = getConnection();
        ArrayList<Cboard> list = cdao.selectTop2(conn);
        close(conn);
        return list;
    }

	public ArrayList<Cboard> mySelectList(String memberID, int currentPage, int limit, String local, String search,
			String keyword) {
		Connection conn = getConnection();
        ArrayList<Cboard> list = cdao.mySelectList(
            conn,
            memberID,
            currentPage,
            limit,
            local,
            search,
            keyword
        );
        close(conn);
        return list;
	}
	
	public int getMyListCount(String memberID, String local, String search, String keyword) {
		Connection conn = getConnection();
		int listCount = cdao.getMyListCount(conn, memberID, local, search, keyword);
		close(conn);
		return listCount;
	}

}
