package faq.model.vo;

import java.sql.Date;

public class Faq implements java.io.Serializable{
	private static final long serialVersionUID = 4445L;
	
	private int faqNo;
	private String faqId;
	private String faqTitle;
	private String faqContent;
	private java.sql.Date faqDate;
	private int faqCount;
	private int faqCategory;
	
	public Faq() {}

	public Faq(int faqNo, String faqId, String faqTitle, String faqContent, Date faqDate, int faqCount,
			int faqCategory) {
		super();
		this.faqNo = faqNo;
		this.faqId = faqId;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
		this.faqDate = faqDate;
		this.faqCount = faqCount;
		this.faqCategory = faqCategory;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public void setFaqDate(java.sql.Date faqDate) {
		this.faqDate = faqDate;
	}

	public void setFaqCount(int faqCount) {
		this.faqCount = faqCount;
	}

	public void setFaqCategory(int faqCategory) {
		this.faqCategory = faqCategory;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public String getFaqId() {
		return faqId;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public java.sql.Date getFaqDate() {
		return faqDate;
	}

	public int getFaqCount() {
		return faqCount;
	}

	public int getFaqCategory() {
		return faqCategory;
	}

	@Override
	public String toString() {
		return "Faq [faqNo=" + faqNo + ", faqId=" + faqId + ", faqTitle=" + faqTitle + ", faqContent=" + faqContent
				+ ", faqDate=" + faqDate + ", faqCount=" + faqCount + ", faqCategory=" + faqCategory + "]";
	}
	
}
