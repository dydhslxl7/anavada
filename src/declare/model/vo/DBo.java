package declare.model.vo;

import java.sql.Date;

public class DBo implements java.io.Serializable {
	public static final long SerializableUID = 6661L; 
	
	private int dboNo; //글번호
	private String dboMid; //멤버아이디(신고자)
	private String dboTitle; //제목
	private java.sql.Date dboDate; //날짜
	private String dboType; //신고유형
	private String dboContent; //내용
	private String dboOriginal; //원본
	private String dboRename; //바뀐
	private String dboUrl; //연결된 링크(해당신고글)
	private String dboBId; //블랙아이디(신고당한자)
	private String dboChe; //신고처리체크
	private int dboCount;
	
	public DBo() {}


   	

	public DBo(int dboNo, String dboMid, String dboTitle, Date dboDate, String dboType, String dboContent,
			String dboOriginal, String dboRename, String dboUrl, String dboBId, String dboChe, int dboCount) {
		super();
		this.dboNo = dboNo;
		this.dboMid = dboMid;
		this.dboTitle = dboTitle;
		this.dboDate = dboDate;
		this.dboType = dboType;
		this.dboContent = dboContent;
		this.dboOriginal = dboOriginal;
		this.dboRename = dboRename;
		this.dboUrl = dboUrl;
		this.dboBId = dboBId;
		this.dboChe = dboChe;
		this.dboCount = dboCount;
	}


	public String getDboTitle() {
		return dboTitle;
	}

	public void setDboTitle(String dboTitle) {
		this.dboTitle = dboTitle;
	}

	public int getDboNo() {
		return dboNo;
	}

	public void setDboNo(int dboNo) {
		this.dboNo = dboNo;
	}

	public String getDboMid() {
		return dboMid;
	}

	public void setDboMid(String dboMid) {
		this.dboMid = dboMid;
	}

	public java.sql.Date getDboDate() {
		return dboDate;
	}

	public void setDboDate(java.sql.Date dboDate) {
		this.dboDate = dboDate;
	}

	public String getDboType() {
		return dboType;
	}

	public void setDboType(String dboType) {
		this.dboType = dboType;
	}

	public String getDboContent() {
		return dboContent;
	}

	public void setDboContent(String dboContent) {
		this.dboContent = dboContent;
	}

	public String getDboOriginal() {
		return dboOriginal;
	}

	public void setDboOriginal(String dboOriginal) {
		this.dboOriginal = dboOriginal;
	}

	public String getDboRename() {
		return dboRename;
	}

	public void setDboRename(String dboRename) {
		this.dboRename = dboRename;
	}

	public String getDboUrl() {
		return dboUrl;
	}

	public void setDboUrl(String dboUrl) {
		this.dboUrl = dboUrl;
	}

	public String getDboBId() {
		return dboBId;
	}

	public void setDboBId(String dboBId) {
		this.dboBId = dboBId;
	}

		
	public String getDboChe() {
		return dboChe;
	}


	public void setDboChe(String dboChe) {
		this.dboChe = dboChe;
	}


	public static long getSerializableuid() {
		return SerializableUID;
	}
	
	
	public int getDboCount() {
		return dboCount;
	}

	public void setDboCount(int dboCount) {
		this.dboCount = dboCount;
	}


	@Override
	public String toString() {
		return "DBo [dboNo=" + dboNo + ", dboMid=" + dboMid + ", dboTitle=" + dboTitle + ", dboDate=" + dboDate
				+ ", dboType=" + dboType + ", dboContent=" + dboContent + ", dboOriginal=" + dboOriginal
				+ ", dboRename=" + dboRename + ", dboUrl=" + dboUrl + ", dboBId=" + dboBId + ", dboChe=" + dboChe
				+ ", dboCount=" + dboCount + "]";
	}


}
