package inquiry.model.vo;

import java.sql.Date;

public class Inquiry implements java.io.Serializable{
	private static final long serialVersionUID = 4446;
	
	private int iqNo;
	private String iqId;
	private String iqTitle;
	private String iqContent;
	private	java.sql.Date iqDate;
	private String iqAnswer;
	private String iqOriginal;
	private String iqRename;
	private String iqOriginal2;
	private String iqRename2;
	private String iqOriginal3;
	private String iqRename3;
	private String iqType;
	
	public Inquiry() {}

	public Inquiry(int iqNo, String iqId, String iqTitle, String iqContent, Date iqDate, String iqAnswer,
			String iqOriginal, String iqRename, String iqOriginal2, String iqRename2, String iqOriginal3,
			String iqRename3, String iqType) {
		super();
		this.iqNo = iqNo;
		this.iqId = iqId;
		this.iqTitle = iqTitle;
		this.iqContent = iqContent;
		this.iqDate = iqDate;
		this.iqAnswer = iqAnswer;
		this.iqOriginal = iqOriginal;
		this.iqRename = iqRename;
		this.iqOriginal2 = iqOriginal2;
		this.iqRename2 = iqRename2;
		this.iqOriginal3 = iqOriginal3;
		this.iqRename3 = iqRename3;
		this.iqType = iqType;
	}

	public void setIqNo(int iqNo) {
		this.iqNo = iqNo;
	}

	public void setIqId(String iqId) {
		this.iqId = iqId;
	}

	public void setIqTitle(String iqTitle) {
		this.iqTitle = iqTitle;
	}

	public void setIqContent(String iqContent) {
		this.iqContent = iqContent;
	}

	public void setIqDate(java.sql.Date iqDate) {
		this.iqDate = iqDate;
	}

	public void setIqAnswer(String iqAnswer) {
		this.iqAnswer = iqAnswer;
	}

	public void setIqOriginal(String iqOriginal) {
		this.iqOriginal = iqOriginal;
	}

	public void setIqRename(String iqRename) {
		this.iqRename = iqRename;
	}

	public void setIqOriginal2(String iqOriginal2) {
		this.iqOriginal2 = iqOriginal2;
	}

	public void setIqRename2(String iqRename2) {
		this.iqRename2 = iqRename2;
	}

	public void setIqOriginal3(String iqOriginal3) {
		this.iqOriginal3 = iqOriginal3;
	}

	public void setIqRename3(String iqRename3) {
		this.iqRename3 = iqRename3;
	}

	public void setIqType(String iqType) {
		this.iqType = iqType;
	}

	public int getIqNo() {
		return iqNo;
	}

	public String getIqId() {
		return iqId;
	}

	public String getIqTitle() {
		return iqTitle;
	}

	public String getIqContent() {
		return iqContent;
	}

	public java.sql.Date getIqDate() {
		return iqDate;
	}

	public String getIqAnswer() {
		return iqAnswer;
	}

	public String getIqOriginal() {
		return iqOriginal;
	}

	public String getIqRename() {
		return iqRename;
	}

	public String getIqOriginal2() {
		return iqOriginal2;
	}

	public String getIqRename2() {
		return iqRename2;
	}

	public String getIqOriginal3() {
		return iqOriginal3;
	}

	public String getIqRename3() {
		return iqRename3;
	}

	public String getIqType() {
		return iqType;
	}

	@Override
	public String toString() {
		return "Inquiry [iqNo=" + iqNo + ", iqId=" + iqId + ", iqTitle=" + iqTitle + ", iqContent=" + iqContent
				+ ", iqDate=" + iqDate + ", iqAnswer=" + iqAnswer + ", iqOriginal=" + iqOriginal + ", iqRename="
				+ iqRename + ", iqOriginal2=" + iqOriginal2 + ", iqRename2=" + iqRename2 + ", iqOriginal3="
				+ iqOriginal3 + ", iqRename3=" + iqRename3 + ", iqType=" + iqType + "]";
	}
	
}
