package admin.notice.answer.model.vo;

import java.sql.Date;

public class Answer implements java.io.Serializable{
	private static final long serialVersionUID = 4446L;
	
	private int anNo;
	private String anContent;
	private Date anDate;
	private int iqNo;
	private String iqId;
	
	public Answer() {}

	public Answer(int anNo, String anContent, Date anDate, int iqNo, String iqId) {
		super();
		this.anNo = anNo;
		this.anContent = anContent;
		this.anDate = anDate;
		this.iqNo = iqNo;
		this.iqId = iqId;
	}

	public void setAnNo(int anNo) {
		this.anNo = anNo;
	}

	public void setAnContent(String anContent) {
		this.anContent = anContent;
	}

	public void setAnDate(Date anDate) {
		this.anDate = anDate;
	}

	public void setIqNo(int iqNo) {
		this.iqNo = iqNo;
	}

	public void setIqId(String iqId) {
		this.iqId = iqId;
	}

	public int getAnNo() {
		return anNo;
	}

	public String getAnContent() {
		return anContent;
	}

	public Date getAnDate() {
		return anDate;
	}

	public int getIqNo() {
		return iqNo;
	}

	public String getIqId() {
		return iqId;
	}

	@Override
	public String toString() {
		return "Answer [anNo=" + anNo + ", anContent=" + anContent + ", anDate=" + anDate + ", iqNo=" + iqNo + ", iqId="
				+ iqId + "]";
	}
	
	
}
