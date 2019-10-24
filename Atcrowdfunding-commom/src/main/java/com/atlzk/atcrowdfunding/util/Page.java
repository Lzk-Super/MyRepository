package com.atlzk.atcrowdfunding.util;

import java.util.List;



public class Page {
	
	private Integer pageno; //当前页码
	private Integer pagesize;//页面数据数量
	private List data;
	private Integer totalsize;//数据总量
	private Integer totalno;//数据总页码数
	
	public Page(Integer pageno,Integer pagesize) {
		if(pageno <= 0)
			this.pageno = 1;
		else {
			this.pageno = pageno;	
		}
		
		if(pagesize <= 0)
			this.pagesize = 10;
		else {
			this.pagesize = pagesize;	
		}

	}
	
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Integer getTotalsize() {
		return totalsize;
	}
	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
		this.totalno = totalsize%pagesize == 0 ? totalsize/pagesize : totalsize/pagesize + 1;
	}
	public Integer getTotalno() {
		return totalno;
	}
	private void setTotalno(Integer totalno) {
		this.totalno = totalno;
	}
	
	public Integer getStartIndex() {
		return (this.pageno-1) * pagesize;
	}
	
}
