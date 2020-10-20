package jboard.model.dao;

import static common.JDBCTemp.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jboard.model.vo.Comment;

public class CommentDao {
	public CommentDao () {}

	public int insertComment(Connection conn, Comment reply) {
		 int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String query = null;
		 
			 query = "insert into jboard_comment values ("
					+ "JBOARD_COMMENT_SEQ.nextval , ?, ?, sysdate, "
					+ "sysdate, ?, JBOARD_COMMENT_SEQ.nextval ,0, 0 ,0,?)";
		
		
		 try {
			 	pstmt = conn.prepareStatement(query);
			 	pstmt.setString(1, reply.getCommentId());
			 	pstmt.setString(2, reply.getCommentContent());
			 	pstmt.setInt(3, reply.getJboardNo());
			 	pstmt.setString(4, reply.getMemberIp());
			 	result = pstmt.executeUpdate();				 	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		 return result;
	}
	
	public int getCommentCount(Connection conn , int jboardNo) {
		int commentListCount = 0;
		Statement stmt =null;
		ResultSet rset = null;
		String query = "select count(*) from JBOARD_COMMENT where JBOARD_NO = " + jboardNo;
		
		try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				
				if (rset.next()) {
					commentListCount = rset.getInt(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return commentListCount;
}
	

	public int updateReplySeq(Connection conn, Comment reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = null;
		
		if(reply.getCommentLevel() == 1) {
				query = "update jboard_comment set comment_reply_seq = comment_reply_seq + 1 , member_ip = ? "
						+"where comment_ref = ? and comment_level = ?";
		}
		if(reply.getCommentLevel() ==2) {
			query = "update jboard_comment set comment_reply_seq = comment_reply_seq + 1 , member_ip = ? "
					+"where comment_ref = ? and comment_level = ? "
					+ "and comment_reply_ref = ?";
		}
		
		try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, reply.getMemberIp());
				pstmt.setInt(2, reply.getCommentRef());
				pstmt.setInt(3, reply.getCommentLevel());
				if (reply.getCommentLevel() ==2) {
					pstmt.setInt(4, reply.getCommentReplyRef());
				}
				
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	

	public ArrayList<Comment> CommentList(Connection conn, int jboardNo) {
		ArrayList<Comment> list = new ArrayList <Comment>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COMMENT_NO,COMMENT_ID,COMMENT_CONTENT,COMMENT_DATE,COMMENT_LASTMODIFIED,"
							  +"JBOARD_NO,COMMENT_REF,COMMENT_REPLY_REF," 
							  +"COMMENT_LEVEL, COMMENT_REPLY_SEQ,SUBSTR(MEMBER_IP,1,INSTR(MEMBER_IP,'.',1,2)) "
							  +"FROM JBOARD_COMMENT WHERE JBOARD_NO = ? "
							  +"ORDER BY COMMENT_REF ASC, COMMENT_REPLY_REF, COMMENT_LEVEL, comment_reply_seq desc";
		try {
			
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, jboardNo);	
			
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Comment comment = new Comment();
					
					comment.setCommentNo(rset.getInt("comment_no"));
					comment.setCommentId(rset.getString("comment_id"));
					comment.setCommentContent(rset.getString("comment_content"));
					comment.setCommentDate(rset.getDate("comment_date"));
					comment.setCommentLastModified(rset.getDate("comment_lastmodified"));
					comment.setJboardNo(rset.getInt("jboard_no"));
					comment.setCommentRef(rset.getInt("comment_ref"));
					comment.setCommentReplyRef(rset.getInt("comment_reply_ref"));
					comment.setCommentLevel(rset.getInt("comment_level"));
					comment.setCommentReplySeq(rset.getInt("comment_reply_seq"));
					comment.setMemberIp(rset.getString("SUBSTR(MEMBER_IP,1,INSTR(MEMBER_IP,'.',1,2))"));
					list.add(comment);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
	return list; 
	}

	public Comment selectComment(Connection conn, int commentNo) {
		Comment comment = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COMMENT_NO,COMMENT_ID,COMMENT_CONTENT,COMMENT_DATE,COMMENT_LASTMODIFIED,"
						+"JBOARD_NO,COMMENT_REF,COMMENT_REPLY_REF,"
						+"COMMENT_LEVEL, COMMENT_REPLY_SEQ,SUBSTR(MEMBER_IP,1,INSTR(MEMBER_IP,'.',1,2))"
						+ "from jboard_comment where comment_no = ?";
		
		try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, commentNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					comment = new Comment();
					
					comment.setCommentNo(commentNo);
					comment.setCommentId(rset.getString("comment_id"));
					comment.setCommentContent(rset.getString("comment_content"));
					comment.setCommentDate(rset.getDate("comment_date"));
					comment.setCommentLastModified(rset.getDate("comment_lastmodified"));
					comment.setJboardNo(rset.getInt("jboard_no"));
					comment.setCommentRef(rset.getInt("comment_ref"));
					comment.setCommentReplyRef(rset.getInt("comment_reply_ref"));
					comment.setCommentLevel(rset.getInt("comment_level"));
					comment.setCommentReplySeq(rset.getInt("comment_reply_seq"));
					comment.setMemberIp(rset.getString("SUBSTR(MEMBER_IP,1,INSTR(MEMBER_IP,'.',1,2))"));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return comment;
}

	public int insertReplyComment(Connection conn, Comment reply) {
		int result = 0;
		 PreparedStatement pstmt = null;
		 
		 String query = null;
		 
		 if(reply.getCommentLevel() ==1) { 
			 query = "insert into jboard_comment values ("
						+ "JBOARD_COMMENT_SEQ.nextval,"
						+ "?, ?, sysdate, sysdate, ?, ?, JBOARD_COMMENT_SEQ.nextval, "
						+ "1 , ?, ?)";
						
		 }
		 if(reply.getCommentLevel() == 2) {
			 query = "insert into jboard_comment values ("
						+ "JBOARD_COMMENT_SEQ.nextval,"
						+ "?, ?, sysdate, sysdate, ?, ?, "
						+ "?, 2, ?, ?)";	
		 }
		 
		 try {
			 	pstmt = conn.prepareStatement(query);
			 	pstmt.setString(1, reply.getCommentId());
			 	pstmt.setString(2, reply.getCommentContent());
			 	pstmt.setInt(3, reply.getJboardNo());
			 	pstmt.setInt(4, reply.getCommentRef());
		 	 	
			 	if (reply.getCommentLevel() == 1) {
			 	 	pstmt.setInt(5, reply.getCommentReplySeq());
			 	 	pstmt.setString(6,  reply.getMemberIp());
			 	}
			 	if(reply.getCommentLevel() == 2) {
			 			pstmt.setInt(5,  reply.getCommentReplyRef());
			 			pstmt.setInt(6, reply.getCommentReplySeq());
			 			pstmt.setString(7, reply.getMemberIp());
			 	}
			 	result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		 return result;
	}

	public int commentDelete(Connection conn, int commentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from jboard_comment where comment_no = ?";
		
		try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, commentNo);
				
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateComment(Connection conn, Comment reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update jboard_comment set comment_content  = ?, member_ip = ?, "
							+ "comment_lastmodified = sysdate where comment_no = ? ";
		try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, reply.getCommentContent());
				pstmt.setString(2, reply.getMemberIp());
				pstmt.setInt(3,  reply.getCommentNo());
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
		
	

	

	


	


