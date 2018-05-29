package biz.shxn.framework.vo;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
*/
public class ValueObject {
	/** */
	private long serialVersionUID = 1L;
	
	private String objectId;
	
	/** */
	private String name;
	
	/** */
	private String recordState;
	
	/** */
	private String delFlag;
	
	/** */
	private String creatorId;
	
	/** */
	private String creationTime;
	
	/** */
	private String lastUpdateId;
	
	/** */
	private String lastUpdateTime;

	private int totalCount;

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the recordState
	 */
	public String getRecordState() {
		return recordState;
	}

	/**
	 * @param recordState the recordState to set
	 */
	public void setRecordState(String recordState) {
		this.recordState = recordState;
	}

	/**
	 * @return the delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return the creatorId
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @return the creationTime
	 */
	public String getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the lastUpdateId
	 */
	public String getLastUpdateId() {
		return lastUpdateId;
	}

	/**
	 * @param lastUpdateId the lastUpdateId to set
	 */
	public void setLastUpdateId(String lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
