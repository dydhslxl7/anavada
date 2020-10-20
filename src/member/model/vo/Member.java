package member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable {
	public Member() {}
	
	private static final long selectServiceUID = 1L;
	
	private String mNumber;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberEmail;
	private String emailAuth;
	private String memberPhone;
	private java.sql.Date joinDate;
	private java.sql.Date lastAccessDate;
	private String newPwd;
	private String newPwdOK;
	private String declareOK;
	private String secessionOK;
	private java.sql.Date secessionDate;
	
	public Member(String mNumber, String memberId, String memberPwd, String memberName, String memberEmail,
			String emailAuth, String memberPhone, Date joinDate, Date lastAccessDate, String newPwd, String newPwdOK,
			String declareOK, String secessionOK, Date secessionDate) {
		super();
		this.mNumber = mNumber;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.emailAuth = emailAuth;
		this.memberPhone = memberPhone;
		this.joinDate = joinDate;
		this.lastAccessDate = lastAccessDate;
		this.newPwd = newPwd;
		this.newPwdOK = newPwdOK;
		this.declareOK = declareOK;
		this.secessionOK = secessionOK;
		this.secessionDate = secessionDate;
	}
	public String getmNumber() {
		return mNumber;
	}
	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getEmailAuth() {
		return emailAuth;
	}
	public void setEmailAuth(String emailAuth) {
		this.emailAuth = emailAuth;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public java.sql.Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(java.sql.Date joinDate) {
		this.joinDate = joinDate;
	}
	public java.sql.Date getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(java.sql.Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getNewPwdOK() {
		return newPwdOK;
	}
	public void setNewPwdOK(String newPwdOK) {
		this.newPwdOK = newPwdOK;
	}
	public String getDeclareOK() {
		return declareOK;
	}
	public void setDeclareOK(String declareOK) {
		this.declareOK = declareOK;
	}
	public String getSecessionOK() {
		return secessionOK;
	}
	public void setSecessionOK(String secessionOK) {
		this.secessionOK = secessionOK;
	}
	public java.sql.Date getSecessionDate() {
		return secessionDate;
	}
	public void setSecessionDate(java.sql.Date secessionDate) {
		this.secessionDate = secessionDate;
	}
	public static long getSelectserviceuid() {
		return selectServiceUID;
	}
	@Override
	public String toString() {
		return "Member [mNumber=" + mNumber + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", memberEmail=" + memberEmail + ", emailAuth=" + emailAuth + ", memberPhone="
				+ memberPhone + ", joinDate=" + joinDate + ", lastAccessDate=" + lastAccessDate + ", newPwd=" + newPwd
				+ ", newPwdOK=" + newPwdOK + ", declareOK=" + declareOK + ", secessionOK=" + secessionOK
				+ ", secessionDate=" + secessionDate + "]";
	}
}
