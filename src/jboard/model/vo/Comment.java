package jboard.model.vo;

import java.sql.Date;

public class Comment implements java.io.Serializable {
	private static final long serialVersionUID = 222L;
		public Comment(){}
		
		private int commentNo; //댓글번호
		private String commentId; //작성자ID
		private String commentContent; //댓글내용
		private java.sql.Date commentDate; //댓글  등록일자
		private java.sql.Date commentLastModified; //댓글 수정일자
		private int jboardNo; //원글 번호
		private int commentRef; //댓글 위치
		private int commentReplyRef; //댓글구분
		private int commentLevel; //댓글레벨
		private int commentReplySeq; //댓글순번
		private String MemberIp; //댓글 작성자의 아이피
		public Comment(int commentNo, String commentId, String commentContent, Date commentDate,
				Date commentLastModified, int jboardNo, int commentRef, int commentReplyRef, int commentLevel,
				int commentReplySeq, String memberIp) {
			super();
			this.commentNo = commentNo;
			this.commentId = commentId;
			this.commentContent = commentContent;
			this.commentDate = commentDate;
			this.commentLastModified = commentLastModified;
			this.jboardNo = jboardNo;
			this.commentRef = commentRef;
			this.commentReplyRef = commentReplyRef;
			this.commentLevel = commentLevel;
			this.commentReplySeq = commentReplySeq;
			MemberIp = memberIp;
		}
		public int getCommentNo() {
			return commentNo;
		}
		public void setCommentNo(int commentNo) {
			this.commentNo = commentNo;
		}
		public String getCommentId() {
			return commentId;
		}
		public void setCommentId(String commentId) {
			this.commentId = commentId;
		}
		public String getCommentContent() {
			return commentContent;
		}
		public void setCommentContent(String commentContent) {
			this.commentContent = commentContent;
		}
		public java.sql.Date getCommentDate() {
			return commentDate;
		}
		public void setCommentDate(java.sql.Date commentDate) {
			this.commentDate = commentDate;
		}
		public java.sql.Date getCommentLastModified() {
			return commentLastModified;
		}
		public void setCommentLastModified(java.sql.Date commentLastModified) {
			this.commentLastModified = commentLastModified;
		}
		public int getJboardNo() {
			return jboardNo;
		}
		public void setJboardNo(int jboardNo) {
			this.jboardNo = jboardNo;
		}
		public int getCommentRef() {
			return commentRef;
		}
		public void setCommentRef(int commentRef) {
			this.commentRef = commentRef;
		}
		public int getCommentReplyRef() {
			return commentReplyRef;
		}
		public void setCommentReplyRef(int commentReplyRef) {
			this.commentReplyRef = commentReplyRef;
		}
		public int getCommentLevel() {
			return commentLevel;
		}
		public void setCommentLevel(int commentLevel) {
			this.commentLevel = commentLevel;
		}
		public int getCommentReplySeq() {
			return commentReplySeq;
		}
		public void setCommentReplySeq(int commentReplySeq) {
			this.commentReplySeq = commentReplySeq;
		}
		public String getMemberIp() {
			return MemberIp;
		}
		public void setMemberIp(String memberIp) {
			MemberIp = memberIp;
		}
		@Override
		public String toString() {
			return "Comment [commentNo=" + commentNo + ", commentId=" + commentId + ", commentContent=" + commentContent
					+ ", commentDate=" + commentDate + ", commentLastModified=" + commentLastModified + ", jboardNo="
					+ jboardNo + ", commentRef=" + commentRef + ", commentReplyRef=" + commentReplyRef
					+ ", commentLevel=" + commentLevel + ", commentReplySeq=" + commentReplySeq + ", MemberIp="
					+ MemberIp + "]";
		}
		
		
		
}
