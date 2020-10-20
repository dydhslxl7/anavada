package creply.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Creply implements Serializable{
	public static final long serialVersionUID = 32L;
	private int creplyNo;
	private int cbaordNo;
	private String memberId;
	private Date creplyDate;
	private String creplyContent;
	private int parantReply;
	private int creplyDepth;
	
	public Creply() {}

	public Creply(int creplyNo, int cbaordNo, String memberId, Date creplyDate, String creplyContent, int parantReply, int creplyDepth) {
		super();
		this.creplyNo = creplyNo;
		this.cbaordNo = cbaordNo;
		this.memberId = memberId;
		this.creplyDate = creplyDate;
		this.creplyContent = creplyContent;
		this.parantReply = parantReply;
		this.creplyDepth = creplyDepth;
	}

	public int getCreplyNo() {
		return creplyNo;
	}

	public void setCreplyNo(int creplyNo) {
		this.creplyNo = creplyNo;
	}

	public int getCbaordNo() {
		return cbaordNo;
	}

	public void setCbaordNo(int cbaordNo) {
		this.cbaordNo = cbaordNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getCreplyDate() {
		return creplyDate;
	}

	public void setCreplyDate(Date creplyDate) {
		this.creplyDate = creplyDate;
	}

	public String getCreplyContent() {
		return creplyContent;
	}

	public void setCreplyContent(String creplyContent) {
		this.creplyContent = creplyContent;
	}

	public int getParantReply() {
		return parantReply;
	}

	public void setParantReply(int parantReply) {
		this.parantReply = parantReply;
	}

	public int getCreplyDepth() {
		return creplyDepth;
	}

	public void setCreplyDepth(int creplyDepth) {
		this.creplyDepth = creplyDepth;
	}

	@Override
	public String toString() {
		return "Creply [creplyNo=" + creplyNo + ", cbaordNo=" + cbaordNo + ", memberId=" + memberId + ", creplyDate=" + creplyDate + ", creplyContent=" + creplyContent + ", parantReply=" + parantReply
				+ ", creplyDepth=" + creplyDepth + "]";
	}
	
}
