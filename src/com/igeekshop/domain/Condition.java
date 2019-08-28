package com.igeekshop.domain;
/**   
* @author:     Fukang
* @date    {date}{time}
*/
public class Condition {
	private String pname;
	private String isHot;
	private String cid;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getIsHot() {
		return isHot;
	}
	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Condition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Condition(String pname, String isHot, String cid) {
		super();
		this.pname = pname;
		this.isHot = isHot;
		this.cid = cid;
	}
}
