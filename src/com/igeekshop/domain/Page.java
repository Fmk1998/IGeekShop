package com.igeekshop.domain;

import java.util.ArrayList;
import java.util.List;

/**   
* @author:     Fukang
 * @param <T>
* @date    {date}{time}
*/
public class Page<T> {
	// 1 当前页
    private int currentPage;
    // 2 当前页显示的条数
    private int currentCount;
    // 3 总条数
    private int totalCount;
    // 4 总页数
    private int totalPage;
    // 5 每页显示的数据
    private List<T> list;
    public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	private List<T> productList = new ArrayList<T>();
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getProductList() {
		return productList;
	}
	public void setProductList(List<T> productList) {
		this.productList = productList;
	}
}
