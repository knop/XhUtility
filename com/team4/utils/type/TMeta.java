package com.team4.utils.type;

/**
 *  @Project       : TPO
 *  @Program Name  : com.team4.utils.type.TMeta.java
 *  @Class Name    : TMeta
 *  @Description   : 类描述
 *  @Author        : Xiaohui Chen
 *  @Creation Date : 2013-6-18 下午11:06:00 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  username   2013-6-18       TODO
 */
public class TMeta {

	private int total_count;
	private int offset;
	private int limit;
	private String previous;
	private String next;
	
//	/**
//	*  @BareFieldName : totalCount
//	*  @return  the totalCount
//	*/
//	public int getTotalCount() {
//		return totalCount;
//	}
//	
//	/**
//	 * @param totalCount the totalCount to set
//	 */
//	public void setTotalCount(int totalCount) {
//		this.totalCount = totalCount;
//	}

	/**
	*  @BareFieldName : offset
	*  @return  the offset
	*/
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	*  @BareFieldName : limit
	*  @return  the limit
	*/
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	*  @BareFieldName : previous
	*  @return  the previous
	*/
	public String getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(String previous) {
		this.previous = previous;
	}

	/**
	*  @BareFieldName : next
	*  @return  the next
	*/
	public String getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(String next) {
		this.next = next;
	}

	/**
	*  @BareFieldName : total_count
	*  @return  the total_count
	*/
	public int getTotal_count() {
		return total_count;
	}

	/**
	 * @param total_count the total_count to set
	 */
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	
}
