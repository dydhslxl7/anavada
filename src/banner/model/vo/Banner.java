package banner.model.vo;

public class Banner implements java.io.Serializable{
	public static final long SerializableUID = 6666L;
	
	
	private int bannerNo;  
	private String bannerTitle;
	private String bannerChk;
	private String bannerContent;
	private String bannerOriginal;
	private String bannerRename;
	private String bannerSize;
	private String bannerUrl;
	
	public Banner() {}

	
	public Banner(int bannerNo, String bannerTitle, String bannerChk, String bannerContent, String bannerOriginal,
			String bannerRename, String bannerSize, String bannerUrl) {
		super();
		this.bannerNo = bannerNo;
		this.bannerTitle = bannerTitle;
		this.bannerChk = bannerChk;
		this.bannerContent = bannerContent;
		this.bannerOriginal = bannerOriginal;
		this.bannerRename = bannerRename;
		this.bannerSize = bannerSize;
		this.bannerUrl = bannerUrl;
	}


	public void setBannerNo(int bannerNo) {
		this.bannerNo = bannerNo;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public void setBannerChk(String bannerChk) {
		this.bannerChk = bannerChk;
	}

	
	public void setBannerContent(String bannerContent) {
		this.bannerContent = bannerContent;
	}


	public void setBannerOriginal(String bannerOriginal) {
		this.bannerOriginal = bannerOriginal;
	}

	public void setBannerRename(String bannerRename) {
		this.bannerRename = bannerRename;
	}

	public void setBannerSize(String bannerSize) {
		this.bannerSize = bannerSize;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}


	public static long getSerializableuid() {
		return SerializableUID;
	}

	public int getBannerNo() {
		return bannerNo;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public String getBannerChk() {
		return bannerChk;
	}

	public String getBannerContent() {
		return bannerContent;
	}

	public String getBannerOriginal() {
		return bannerOriginal;
	}

	public String getBannerRename() {
		return bannerRename;
	}

	public String getBannerSize() {
		return bannerSize;
	}
	
	public String getBannerUrl() {
		return bannerUrl;
	}

	@Override
	public String toString() {
		return "Banner [bannerNo=" + bannerNo + ", bannerTitle=" + bannerTitle + ", bannerChk=" + bannerChk
				+ ", bannerContent=" + bannerContent + ", bannerOriginal=" + bannerOriginal + ", bannerRename="
				+ bannerRename + ", bannerSize=" + bannerSize + ", bannerUrl=" + bannerUrl + "]";
	}

}
