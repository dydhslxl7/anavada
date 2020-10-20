package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable{
	private static final long serialVersionUID = 4444L;
	
	private int noNo;
	private String noId;
	private String noTitle;
	private String noContent;
	private java.sql.Date noDate;
	private int noCount;
	private String noOriginal;
	private String noRename;
	
	public Notice() {}

	public Notice(int noNo, String noId, String noTitle, String noContent, Date noDate, int noCount, String noOriginal,
			String noRename) {
		super();
		this.noNo = noNo;
		this.noId = noId;
		this.noTitle = noTitle;
		this.noContent = noContent;
		this.noDate = noDate;
		this.noCount = noCount;
		this.noOriginal = noOriginal;
		this.noRename = noRename;
	}

	public void setNoNo(int noNo) {
		this.noNo = noNo;
	}

	public void setNoId(String noId) {
		this.noId = noId;
	}

	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}

	public void setNoContent(String noContent) {
		this.noContent = noContent;
	}

	public void setNoDate(java.sql.Date noDate) {
		this.noDate = noDate;
	}

	public void setNoCount(int noCount) {
		this.noCount = noCount;
	}

	public void setNoOriginal(String noOriginal) {
		this.noOriginal = noOriginal;
	}

	public void setNoRename(String noRename) {
		this.noRename = noRename;
	}

	public int getNoNo() {
		return noNo;
	}

	public String getNoId() {
		return noId;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public String getNoContent() {
		return noContent;
	}

	public java.sql.Date getNoDate() {
		return noDate;
	}

	public int getNoCount() {
		return noCount;
	}

	public String getNoOriginal() {
		return noOriginal;
	}

	public String getNoRename() {
		return noRename;
	}

	@Override
	public String toString() {
		return "Notice [noNo=" + noNo + ", noId=" + noId + ", noTitle=" + noTitle + ", noContent=" + noContent
				+ ", noDate=" + noDate + ", noCount=" + noCount + ", noOriginal=" + noOriginal + ", noRename="
				+ noRename + "]";
	}
	
}
