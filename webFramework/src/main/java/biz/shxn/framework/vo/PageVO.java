package biz.shxn.framework.vo;

import java.util.List;



public class PageVO<T> {
	/**
	 * 分页数据集
	 */
	private List<T> items;

	/**
	 * 当前显示的页码
	 */
	private int currentPage = 1;
	/**
	 * 每页显示的记录数
	 */
	private int pageSize = 12;
	/**
	 * 总记录数
	 */
	private int totalCounts = 0;
	
	/**
	 * 总页数
	 */
	private int totalPages = 0;
	
	public PageVO(int currentPage){
		if(currentPage >0){
			this.currentPage = currentPage;
		}else{
			this.currentPage = 1;
		}
	}
	
	public PageVO(int currentPage,int pageSize) {
		
		this(currentPage);
		
		if(pageSize > 0){
			this.pageSize = pageSize;
		}else{
			this.pageSize = 1;
		}
	}
	
	public PageVO(int currentPage,int pageSize, int totalCounts) {
		this(currentPage,pageSize);
		if(totalCounts >= 0){
			this.pageSize = pageSize;
		}else{
			this.pageSize = 0;
		}
	}
	/**
	 * 求总页数
	 * @return
	 */
	public int getTotalPages() {
		if ((totalCounts % pageSize) == 0) {
			totalPages = totalCounts / pageSize;
		} else {
			totalPages = totalCounts / pageSize + 1;
		}
		return totalPages;
	}
	/**
	 * 总记录数
	 * @return
	 */
	public int getTotalCounts() {
		return totalCounts;
	}
	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean isHasNextPage() {
		if(currentPage < getTotalPages()){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean isHasPreviousPage() {

		if(currentPage > 1){
			return true;
		}else{
			return false;
		}

	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
		//this.totalCounts=items.size();
		//getTotalPages();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
