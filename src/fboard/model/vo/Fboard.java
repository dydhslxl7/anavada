package fboard.model.vo;

import java.sql.Date;

public class Fboard implements java.io.Serializable{

	private static final long serialVersionUID = 5555L;
	private String fboardNo;
	private String festivalTitle;
	private String localNo;
	private String festivalStartDate;
	private String festivalEndDate;
	private String fesivalModifiedDate;
	private String mapX;
	private String mapY;
	private java.sql.Date bModifiedDate;
	private String memberId;
	private int readcount;
	private String thumbnail;
	private String localName;	//지역명
	private int replycount = 0;	//댓글 수
	
	public Fboard() {}

	public Fboard(String fboardNo, String festivalTitle, String localNo, String festivalStartDate,
			String festivalEndDate, String fesivalModifiedDate, String mapX, String mapY, String thumbnail) {
		super();
		this.fboardNo = fboardNo;
		this.festivalTitle = festivalTitle;
		this.localNo = localNo;
		this.festivalStartDate = festivalStartDate;
		this.festivalEndDate = festivalEndDate;
		this.fesivalModifiedDate = fesivalModifiedDate;
		this.mapX = mapX;
		this.mapY = mapY;
		this.thumbnail = thumbnail;
	}

	public Fboard(String fboardNo, String festivalTitle, String localNo, String festivalStartDate,
			String festivalEndDate, String fesivalModifiedDate, String mapX, String mapY, Date bModifiedDate,
			String memberId, int readcount, String thumbnail, String localName, int replycount) {
		super();
		this.fboardNo = fboardNo;
		this.festivalTitle = festivalTitle;
		this.localNo = localNo;
		this.festivalStartDate = festivalStartDate;
		this.festivalEndDate = festivalEndDate;
		this.fesivalModifiedDate = fesivalModifiedDate;
		this.mapX = mapX;
		this.mapY = mapY;
		this.bModifiedDate = bModifiedDate;
		this.memberId = memberId;
		this.readcount = readcount;
		this.thumbnail = thumbnail;
		this.localName = localName;
		this.replycount = replycount;
	}

	public String getFboardNo() {
		return fboardNo;
	}

	public void setFboardNo(String fboardNo) {
		this.fboardNo = fboardNo;
	}

	public String getFestivalTitle() {
		return festivalTitle;
	}

	public void setFestivalTitle(String festivalTitle) {
		this.festivalTitle = festivalTitle;
	}

	public String getLocalNo() {
		return localNo;
	}

	public void setLocalNo(String localNo) {
		this.localNo = localNo;
	}

	public String getFestivalStartDate() {
		return festivalStartDate;
	}

	public void setFestivalStartDate(String festivalStartDate) {
		this.festivalStartDate = festivalStartDate;
	}

	public String getFestivalEndDate() {
		return festivalEndDate;
	}

	public void setFestivalEndDate(String festivalEndDate) {
		this.festivalEndDate = festivalEndDate;
	}

	public String getFesivalModifiedDate() {
		return fesivalModifiedDate;
	}

	public void setFesivalModifiedDate(String fesivalModifiedDate) {
		this.fesivalModifiedDate = fesivalModifiedDate;
	}

	public String getMapX() {
		return mapX;
	}

	public void setMapX(String mapX) {
		this.mapX = mapX;
	}

	public String getMapY() {
		return mapY;
	}

	public void setMapY(String mapY) {
		this.mapY = mapY;
	}

	public java.sql.Date getbModifiedDate() {
		return bModifiedDate;
	}

	public void setbModifiedDate(java.sql.Date bModifiedDate) {
		this.bModifiedDate = bModifiedDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String member_id) {
		this.memberId = member_id;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getLocalName() {
		return localName;
	}
	
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	@Override
	public String toString() {
		return "Fboard [fboardNo=" + fboardNo + ", festivalTitle=" + festivalTitle + ", localNo=" + localNo
				+ ", festivalStartDate=" + festivalStartDate + ", festivalEndDate=" + festivalEndDate
				+ ", fesivalModifiedDate=" + fesivalModifiedDate + ", mapX=" + mapX + ", mapY=" + mapY
				+ ", bModifiedDate=" + bModifiedDate + ", memberId=" + memberId + ", readcount=" + readcount
				+ ", thumbnail=" + thumbnail + ", localName=" + localName + ", replycount=" + replycount + "]";
	}

}

//FBOARD_NO	VARCHAR2(30 BYTE)	No		1	축제게시판 번호
//FESTIVAL_TITLE	VARCHAR2(600 BYTE)	No		2	축제 이름
//LOCAL_NO	VARCHAR2(3 BYTE)	No		3	지역 번호
//FESTIVAL_STARTDATE	VARCHAR2(8 BYTE)	Yes		4	축제 시작일
//FESTIVAL_ENDDATE	VARCHAR2(8 BYTE)	Yes		5	축제 종료일
//FESTIVAL_MODIFIEDDATE	VARCHAR2(20 BYTE)	Yes		6	축제 수정일
//MAP_Y	VARCHAR2(30 BYTE)	Yes		7	축제 위도
//MAP_X	VARCHAR2(30 BYTE)	Yes		8	축제 경도
//BMODIFY_DATE	DATE	Yes	SYSDATE   	9	축제게시판 수정일
//MEMBER_ID	VARCHAR2(20 BYTE)	Yes		10	작성자
//READCOUNT	NUMBER	Yes	0    	11	조회수
//THUMBNAIL	VARCHAR2(100 BYTE)	Yes	'NoImage'	12	썸네일