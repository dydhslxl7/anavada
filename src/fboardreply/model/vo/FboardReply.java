package fboardreply.model.vo;

public class FboardReply implements java.io.Serializable {

	private static final long serialVersionUID = 5444L;
	
	private int fboardReplyNo;
	private String fboardNo;
	private String memberId;
	private String fboardReplyContent;
	private int fboardReplyLev;
	private int fboardReplyRef;
	private String fboardReplyCreatDate;
	
	public FboardReply() {
	}
	
	public FboardReply(int fboardReplyNo, String fboardNo, String memberId, String fboardReplyContent,
			int fboardReplyLev, int fboardReplyRef, String fboardReplyCreatDate) {
		super();
		this.fboardReplyNo = fboardReplyNo;
		this.fboardNo = fboardNo;
		this.memberId = memberId;
		this.fboardReplyContent = fboardReplyContent;
		this.fboardReplyLev = fboardReplyLev;
		this.fboardReplyRef = fboardReplyRef;
		this.fboardReplyCreatDate = fboardReplyCreatDate;
	}

	public int getFboardReplyNo() {
		return fboardReplyNo;
	}

	public void setFboardReplyNo(int fboardReplyNo) {
		this.fboardReplyNo = fboardReplyNo;
	}

	public String getFboardNo() {
		return fboardNo;
	}

	public void setFboardNo(String fboardNo) {
		this.fboardNo = fboardNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getFboardReplyContent() {
		return fboardReplyContent;
	}

	public void setFboardReplyContent(String fboardReplyContent) {
		this.fboardReplyContent = fboardReplyContent;
	}

	public int getFboardReplyLev() {
		return fboardReplyLev;
	}

	public void setFboardReplyLev(int fboardReplyLev) {
		this.fboardReplyLev = fboardReplyLev;
	}

	public int getFboardReplyRef() {
		return fboardReplyRef;
	}

	public void setFboardReplyRef(int fboardReplyRef) {
		this.fboardReplyRef = fboardReplyRef;
	}

	public String getFboardReplyCreatDate() {
		return fboardReplyCreatDate;
	}

	public void setFboardReplyCreatDate(String fboardReplyCreatDate) {
		this.fboardReplyCreatDate = fboardReplyCreatDate;
	}

	@Override
	public String toString() {
		return "FboardReply [fboardReplyNo=" + fboardReplyNo + ", fboardNo=" + fboardNo + ", memberId=" + memberId
				+ ", fboardReplyContent=" + fboardReplyContent + ", fboardReplyLev=" + fboardReplyLev
				+ ", fboardReplyRef=" + fboardReplyRef
				+ ", fboardReplyCreatDate=" + fboardReplyCreatDate + "]";
	}
	
}

//FBOARDREPLY_NO	NUMBER	No		1	댓글 번호
//FBOARD_NO	VARCHAR2(30 BYTE)	No		2	게시판 번호
//MEMBER_ID	VARCHAR2(20 BYTE)	No		3	작성자 id
//FBOARDREPLY_CONTENT	VARCHAR2(4000 BYTE)	No		4	댓글 내용
//FBOARDREPLY_LEV	NUMBER	No		5	글레벨
//FBOARDREPLY_SEQ	NUMBER	No		6	댓글 순서
//FBOARDREPLY_REF	NUMBER	No		7	댓글 번호
//FBOARDREPLY_CREATDATE	DATE	No	"SYSDATE	"	8	댓글 작성날짜