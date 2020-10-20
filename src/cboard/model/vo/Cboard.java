package cboard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Cboard implements Serializable{
	public static final long serialVersionUID = 31L;
	
	private int cboardNo;
	private String memberId;
	private String cboardTitle;
	private String cboardContent;
	private Date date;
	private Date lastmodifiedDate;
	private int cboardViewCount;
	private int replyCount;
	private int likeCount;
	private String localNo;
	private String cfilesOriginalFilepath1;
	private String cfilesRenameFilepath1;
	private String cfilesOriginalFilepath2;
	private String cfilesRenameFilepath2;
	private String cfilesOriginalFilepath3;
	private String cfilesRenameFilepath3;
	private String cfilesOriginalFilepath4;
	private String cfilesRenameFilepath4;

	public Cboard() {}

	public Cboard(int cboardNo, String memberId, String cboardTitle, String cboardContent, Date date,
			Date lastmodifiedDate, int cboardViewCount, int replyCount, int likeCount, 
			String localNo, String cfilesOriginalFilepath1, String cfilesRenameFilepath1,
			String cfilesOriginalFilepath2, String cfilesRenameFilepath2, String cfilesOriginalFilepath3,
			String cfilesRenameFilepath3, String cfilesOriginalFilepath4, String cfilesRenameFilepath4) {
		super();
		this.cboardNo = cboardNo;
		this.memberId = memberId;
		this.cboardTitle = cboardTitle;
		this.cboardContent = cboardContent;
		this.date = date;
		this.lastmodifiedDate = lastmodifiedDate;
		this.cboardViewCount = cboardViewCount;
		this.replyCount = replyCount;
		this.likeCount = likeCount;
		this.localNo = localNo;
		this.cfilesOriginalFilepath1 = cfilesOriginalFilepath1;
		this.cfilesRenameFilepath1 = cfilesRenameFilepath1;
		this.cfilesOriginalFilepath2 = cfilesOriginalFilepath2;
		this.cfilesRenameFilepath2 = cfilesRenameFilepath2;
		this.cfilesOriginalFilepath3 = cfilesOriginalFilepath3;
		this.cfilesRenameFilepath3 = cfilesRenameFilepath3;
		this.cfilesOriginalFilepath4 = cfilesOriginalFilepath4;
		this.cfilesRenameFilepath4 = cfilesRenameFilepath4;
	}

	public int getCboardNo() {
		return cboardNo;
	}

	public void setCboardNo(int cboardNo) {
		this.cboardNo = cboardNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCboardTitle() {
		return cboardTitle;
	}

	public void setCboardTitle(String cboardTitle) {
		this.cboardTitle = cboardTitle;
	}

	public String getCboardContent() {
		return cboardContent;
	}

	public void setCboardContent(String cboardContent) {
		this.cboardContent = cboardContent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getLastmodifiedDate() {
		return lastmodifiedDate;
	}

	public void setLastmodifiedDate(Date lastmodifiedDate) {
		this.lastmodifiedDate = lastmodifiedDate;
	}

	public int getCboardViewCount() {
		return cboardViewCount;
	}

	public void setCboardViewCount(int cboardViewCount) {
		this.cboardViewCount = cboardViewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getLocalNo() {
		return localNo;
	}

	public void setLocalNo(String localNo) {
		this.localNo = localNo;
	}

	public String getCfilesOriginalFilepath1() {
		return cfilesOriginalFilepath1;
	}

	public void setCfilesOriginalFilepath1(String cfilesOriginalFilepath1) {
		this.cfilesOriginalFilepath1 = cfilesOriginalFilepath1;
	}

	public String getCfilesRenameFilepath1() {
		return cfilesRenameFilepath1;
	}

	public void setCfilesRenameFilepath1(String cfilesRenameFilepath1) {
		this.cfilesRenameFilepath1 = cfilesRenameFilepath1;
	}

	public String getCfilesOriginalFilepath2() {
		return cfilesOriginalFilepath2;
	}

	public void setCfilesOriginalFilepath2(String cfilesOriginalFilepath2) {
		this.cfilesOriginalFilepath2 = cfilesOriginalFilepath2;
	}

	public String getCfilesRenameFilepath2() {
		return cfilesRenameFilepath2;
	}

	public void setCfilesRenameFilepath2(String cfilesRenameFilepath2) {
		this.cfilesRenameFilepath2 = cfilesRenameFilepath2;
	}

	public String getCfilesOriginalFilepath3() {
		return cfilesOriginalFilepath3;
	}

	public void setCfilesOriginalFilepath3(String cfilesOriginalFilepath3) {
		this.cfilesOriginalFilepath3 = cfilesOriginalFilepath3;
	}

	public String getCfilesRenameFilepath3() {
		return cfilesRenameFilepath3;
	}

	public void setCfilesRenameFilepath3(String cfilesRenameFilepath3) {
		this.cfilesRenameFilepath3 = cfilesRenameFilepath3;
	}

	public String getCfilesOriginalFilepath4() {
		return cfilesOriginalFilepath4;
	}

	public void setCfilesOriginalFilepath4(String cfilesOriginalFilepath4) {
		this.cfilesOriginalFilepath4 = cfilesOriginalFilepath4;
	}

	public String getCfilesRenameFilepath4() {
		return cfilesRenameFilepath4;
	}

	public void setCfilesRenameFilepath4(String cfilesRenameFilepath4) {
		this.cfilesRenameFilepath4 = cfilesRenameFilepath4;
	}

	@Override
	public String toString() {
		return "Cboard [cboardNo=" + cboardNo + ", memberId=" + memberId + ", cboardTitle=" + cboardTitle
				+ ", cboardContent=" + cboardContent + ", date=" + date + ", lastmodifiedDate=" + lastmodifiedDate
				+ ", cboardViewCount=" + cboardViewCount + ", replyCount=" + replyCount + ", likeCount=" + likeCount
				+ ", reportCount=" + ", localNo=" + localNo
				+ ", cfilesOriginalFilepath1=" + cfilesOriginalFilepath1 + ", cfilesRenameFilepath1="
				+ cfilesRenameFilepath1 + ", cfilesOriginalFilepath2=" + cfilesOriginalFilepath2
				+ ", cfilesRenameFilepath2=" + cfilesRenameFilepath2 + ", cfilesOriginalFilepath3="
				+ cfilesOriginalFilepath3 + ", cfilesRenameFilepath3=" + cfilesRenameFilepath3
				+ ", cfilesOriginalFilepath4=" + cfilesOriginalFilepath4 + ", cfilesRenameFilepath4="
				+ cfilesRenameFilepath4 + "]";
	}
	
}
