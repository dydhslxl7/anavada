package jboard.model.service;

import static common.JDBCTemp.close;
import static common.JDBCTemp.commit;
import static common.JDBCTemp.getConnection;
import static common.JDBCTemp.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jboard.model.dao.CommentDao;
import jboard.model.vo.Comment;


public class CommentService {
	private CommentDao cdao = new CommentDao();
	public CommentService() {}
	
		public int insertComment(Comment reply) {
			Connection conn = getConnection();
			int result = cdao.insertComment(conn, reply);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}
		


		public ArrayList<Comment> CommentList(int jboardNo) {
			Connection conn = getConnection();
			ArrayList<Comment> list = cdao.CommentList(conn, jboardNo);
			close(conn);
			return list;
		}

		public int getCommentCount(int jboardNo) {
			Connection conn = getConnection();
			int commentCount = cdao.getCommentCount(conn, jboardNo);
			close(conn);
					
			return commentCount;
		}

		public Comment selectComment(int commentNo) {
			Connection conn = getConnection();
			Comment board = cdao.selectComment(conn, commentNo);
			close(conn);
			
			return board;
		}

		public void updateReplySeq(Comment reply) {
			Connection conn = getConnection();
			int result = cdao.updateReplySeq(conn, reply);
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			
		}

		public int insertReplyComment(Comment reply) {
			Connection conn = getConnection();
			int result = cdao.insertReplyComment(conn, reply);
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
			
		}
		
		public int commentDelete(int commentNo) {
			Connection conn = getConnection();
			int result = cdao.commentDelete(conn, commentNo );
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}

		public int updateComment(Comment reply) {
			Connection conn = getConnection();
			int result = cdao.updateComment(conn, reply);
			if (result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
			return result;
		}
}


		
	
