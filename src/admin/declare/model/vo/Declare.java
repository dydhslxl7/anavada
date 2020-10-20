package admin.declare.model.vo;

public class Declare implements java.io.Serializable {
	public static final long SerializableUID = 6662L;
	
	private int declareNo;
	private String declareId;
	private int declareCount;
	private String declareOk;
	
	public Declare() {}

	public Declare(int declareNo, String declareId, int declareCount, String declareOk) {
		super();
		this.declareNo = declareNo;
		this.declareId = declareId;
		this.declareCount = declareCount;
		this.declareOk = declareOk;
	}

	public int getDeclareNo() {
		return declareNo;
	}

	public void setDeclareNo(int declareNo) {
		this.declareNo = declareNo;
	}

	public String getDeclareId() {
		return declareId;
	}

	public void setDeclareId(String declareId) {
		this.declareId = declareId;
	}

	public int getDeclareCount() {
		return declareCount;
	}

	public void setDeclareCount(int declareCount) {
		this.declareCount = declareCount;
	}

	public String getDeclareOk() {
		return declareOk;
	}

	public void setDeclareOk(String declareOk) {
		this.declareOk = declareOk;
	}

	public static long getSerializableuid() {
		return SerializableUID;
	}

	@Override
	public String toString() {
		return "Declare [declareNo=" + declareNo + ", declareId=" + declareId + ", declareCount=" + declareCount
				+ ", declareOk=" + declareOk + "]";
	}

	
}
