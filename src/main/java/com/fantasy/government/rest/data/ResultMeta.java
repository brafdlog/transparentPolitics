package com.fantasy.government.rest.data;

public class ResultMeta {
	
	private String next;
	private String previous;
	private Integer total_count;
	private Integer offset;
	private Integer limit;
	
	public String getNext() {
		return this.next;
	}
	public void setNext(String value) {
		this.next = value;
	}
	public String getPrevious() {
		return this.previous;
	}
	public void setPrevious(String value) {
		this.previous = value;
	}
	public Integer getTotal_count() {
		return this.total_count;
	}
	public void setTotal_count(Integer value) {
		this.total_count = value;
	}
	public Integer getOffset() {
		return this.offset;
	}
	public void setOffset(Integer value) {
		this.offset = value;
	}
	public Integer getLimit() {
		return this.limit;
	}
	public void setLimit(Integer value) {
		this.limit = value;
	}

}