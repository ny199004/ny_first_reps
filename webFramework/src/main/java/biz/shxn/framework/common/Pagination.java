/**
 * 
 */
package biz.shxn.framework.common;

/**
 * @author wangjf
 * @version 1.0, 2017/04/24
 *
 */
public class Pagination {
	
	private static int def_pagesize=10;
	
	/**
	 * 查询结果的起始位置
	 */
	private int offset;
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset < 0 ? 0 : offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
		this.pageNo=offset/this.pageSize;
	}
	
	
	/**
	 * 是否要统计总记录数
	 */
	private boolean countTotalSize;
	/**
	 * @return the countTotalSize
	 */
	public boolean isCountTotalSize() {
		return countTotalSize;
	}
	/**
	 * @param countTotalSize the countTotalSize to set
	 */
	public void setCountTotalSize(boolean countTotalSize) {
		this.countTotalSize = countTotalSize;
	}


	/**
	 * 每页记录数，默认为10
	 */
	private int pageSize=10;
	
	/**
	 * 当前页码，默认为1
	 */
	private int pageNo=1;
	/**
	 * 记录总数，默认为0
	 */
	private int totalCount=0;
	/**
	 * 总页数，默认为0
	 */
	private int totalPage=0;
	
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
			this.pageSize = pageSize<1?def_pagesize:pageSize;
			this.offset = this.pageSize * (this.pageNo - 1);
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo<1?1:pageNo;
		this.offset = this.pageSize * (this.pageNo - 1);
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount<0?0:totalCount;
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		if(totalPage==0&&pageNo>0)
		{
			totalPage=this.totalCount/this.pageNo;
			return (this.totalCount%this.pageNo)>0||totalPage==0?totalPage++:totalPage;
		}
		return this.totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage<0?0:totalPage;
	}
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
