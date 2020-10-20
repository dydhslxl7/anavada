package clike.model.vo;

import java.io.Serializable;

public class Clike implements Serializable{
	public static final long serialVersionUID = 33L;
	private int cboardNo;
	private String memberId;
	
	public Clike() {}

	public Clike(int cboardNo, String memberId) {
		super();
		this.cboardNo = cboardNo;
		this.memberId = memberId;
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

	@Override
	public String toString() {
		return "Clike [cboardNo=" + cboardNo + ", memberId=" + memberId + "]";
	}
	
	
}
