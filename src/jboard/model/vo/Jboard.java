package jboard.model.vo;

import java.sql.Date;

public class Jboard implements java.io.Serializable {
		private static final long serialVersionUid = 2L;
		
		public Jboard(){}
		
		private int jboardNo; //게시글번호
		private String jboardTitle; //게시글제목
		private String jboardContent; //게시글 내용
		private int jboardPrice; //게시글에 대한 가격
		private java.sql.Date jboardDate; //게시글등록일자
		private java.sql.Date jboardUpdate; //게시글 수정일자
		private int jboardCount; //게시글조회수
		private int jboardLike; //게시글 좋아요
		private String jboardOrignalFilePath1; //파일의 본래 이름
		private String jboardRenameFilePath1; //파일의 리네임
		private String jboardOrignalFilePath2;
		private String jboardRenameFilePath2;
		private String jboardOrignalFilePath3;
		private String jboardRenameFilePath3;
		private String jboardOrignalFilePath4;
		private String jboardRenameFilePath4;
		private String jboardCheck;	//게시물을 보여줄것인지 가릴것인지 체크
		private String jboardMeet; //직거래
		private String jboardPost; //우편거래
		private String memberId;  //회원의 ID
		private String localNo; //지역번호
		private String MemberIp; //게시글 작성자의 아이피

		public Jboard(int jboardNo, String jboardTitle, String jboardContent, int jboardPrice, Date jboardDate,
				Date jboardUpdate, int jboardCount, int jboardLike, String jboardOrignalFilePath1,
				String jboardRenameFilePath1, String jboardOrignalFilePath2, String jboardRenameFilePath2,
				String jboardOrignalFilePath3, String jboardRenameFilePath3, String jboardOrignalFilePath4,
				String jboardRenameFilePath4, String jboardCheck, String jboardMeet, String jboardPost, String memberId,
				String localNo, String memberIp) {
			super();
			this.jboardNo = jboardNo;
			this.jboardTitle = jboardTitle;
			this.jboardContent = jboardContent;
			this.jboardPrice = jboardPrice;
			this.jboardDate = jboardDate;
			this.jboardUpdate = jboardUpdate;
			this.jboardCount = jboardCount;
			this.jboardLike = jboardLike;
			this.jboardOrignalFilePath1 = jboardOrignalFilePath1;
			this.jboardRenameFilePath1 = jboardRenameFilePath1;
			this.jboardOrignalFilePath2 = jboardOrignalFilePath2;
			this.jboardRenameFilePath2 = jboardRenameFilePath2;
			this.jboardOrignalFilePath3 = jboardOrignalFilePath3;
			this.jboardRenameFilePath3 = jboardRenameFilePath3;
			this.jboardOrignalFilePath4 = jboardOrignalFilePath4;
			this.jboardRenameFilePath4 = jboardRenameFilePath4;
			this.jboardCheck = jboardCheck;
			this.jboardMeet = jboardMeet;
			this.jboardPost = jboardPost;
			this.memberId = memberId;
			this.localNo = localNo;
			MemberIp = memberIp;
		}

		public int getJboardNo() {
			return jboardNo;
		}

		public void setJboardNo(int jboardNo) {
			this.jboardNo = jboardNo;
		}

		public String getJboardTitle() {
			return jboardTitle;
		}

		public void setJboardTitle(String jboardTitle) {
			this.jboardTitle = jboardTitle;
		}

		public String getJboardContent() {
			return jboardContent;
		}

		public void setJboardContent(String jboardContent) {
			this.jboardContent = jboardContent;
		}

		public int getJboardPrice() {
			return jboardPrice;
		}

		public void setJboardPrice(int jboardPrice) {
			this.jboardPrice = jboardPrice;
		}

		public java.sql.Date getJboardDate() {
			return jboardDate;
		}

		public void setJboardDate(java.sql.Date jboardDate) {
			this.jboardDate = jboardDate;
		}

		public java.sql.Date getJboardUpdate() {
			return jboardUpdate;
		}

		public void setJboardUpdate(java.sql.Date jboardUpdate) {
			this.jboardUpdate = jboardUpdate;
		}

		public int getJboardCount() {
			return jboardCount;
		}

		public void setJboardCount(int jboardCount) {
			this.jboardCount = jboardCount;
		}

		public int getJboardLike() {
			return jboardLike;
		}

		public void setJboardLike(int jboardLike) {
			this.jboardLike = jboardLike;
		}

		public String getJboardOrignalFilePath1() {
			return jboardOrignalFilePath1;
		}

		public void setJboardOrignalFilePath1(String jboardOrignalFilePath1) {
			this.jboardOrignalFilePath1 = jboardOrignalFilePath1;
		}

		public String getJboardRenameFilePath1() {
			return jboardRenameFilePath1;
		}

		public void setJboardRenameFilePath1(String jboardRenameFilePath1) {
			this.jboardRenameFilePath1 = jboardRenameFilePath1;
		}

		public String getJboardOrignalFilePath2() {
			return jboardOrignalFilePath2;
		}

		public void setJboardOrignalFilePath2(String jboardOrignalFilePath2) {
			this.jboardOrignalFilePath2 = jboardOrignalFilePath2;
		}

		public String getJboardRenameFilePath2() {
			return jboardRenameFilePath2;
		}

		public void setJboardRenameFilePath2(String jboardRenameFilePath2) {
			this.jboardRenameFilePath2 = jboardRenameFilePath2;
		}

		public String getJboardOrignalFilePath3() {
			return jboardOrignalFilePath3;
		}

		public void setJboardOrignalFilePath3(String jboardOrignalFilePath3) {
			this.jboardOrignalFilePath3 = jboardOrignalFilePath3;
		}

		public String getJboardRenameFilePath3() {
			return jboardRenameFilePath3;
		}

		public void setJboardRenameFilePath3(String jboardRenameFilePath3) {
			this.jboardRenameFilePath3 = jboardRenameFilePath3;
		}

		public String getJboardOrignalFilePath4() {
			return jboardOrignalFilePath4;
		}

		public void setJboardOrignalFilePath4(String jboardOrignalFilePath4) {
			this.jboardOrignalFilePath4 = jboardOrignalFilePath4;
		}

		public String getJboardRenameFilePath4() {
			return jboardRenameFilePath4;
		}

		public void setJboardRenameFilePath4(String jboardRenameFilePath4) {
			this.jboardRenameFilePath4 = jboardRenameFilePath4;
		}

		public String getJboardCheck() {
			return jboardCheck;
		}

		public void setJboardCheck(String jboardCheck) {
			this.jboardCheck = jboardCheck;
		}

		public String getJboardMeet() {
			return jboardMeet;
		}

		public void setJboardMeet(String jboardMeet) {
			this.jboardMeet = jboardMeet;
		}

		public String getJboardPost() {
			return jboardPost;
		}

		public void setJboardPost(String jboardPost) {
			this.jboardPost = jboardPost;
		}

		public String getMemberId() {
			return memberId;
		}

		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}

		public String getLocalNo() {
			return localNo;
		}

		public void setLocalNo(String localNo) {
			this.localNo = localNo;
		}

		public String getMemberIp() {
			return MemberIp;
		}

		public void setMemberIp(String memberIp) {
			MemberIp = memberIp;
		}

		@Override
		public String toString() {
			return "Jboard [jboardNo=" + jboardNo + ", jboardTitle=" + jboardTitle + ", jboardContent=" + jboardContent
					+ ", jboardPrice=" + jboardPrice + ", jboardDate=" + jboardDate + ", jboardUpdate=" + jboardUpdate
					+ ", jboardCount=" + jboardCount + ", jboardLike=" + jboardLike + ", jboardOrignalFilePath1="
					+ jboardOrignalFilePath1 + ", jboardRenameFilePath1=" + jboardRenameFilePath1
					+ ", jboardOrignalFilePath2=" + jboardOrignalFilePath2 + ", jboardRenameFilePath2="
					+ jboardRenameFilePath2 + ", jboardOrignalFilePath3=" + jboardOrignalFilePath3
					+ ", jboardRenameFilePath3=" + jboardRenameFilePath3 + ", jboardOrignalFilePath4="
					+ jboardOrignalFilePath4 + ", jboardRenameFilePath4=" + jboardRenameFilePath4 + ", jboardCheck="
					+ jboardCheck + ", jboardMeet=" + jboardMeet + ", jboardPost=" + jboardPost + ", memberId="
					+ memberId + ", localNo=" + localNo + ", MemberIp=" + MemberIp + "]";
		}
		
		
		
}
