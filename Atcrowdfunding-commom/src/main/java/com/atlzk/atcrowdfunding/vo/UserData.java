package com.atlzk.atcrowdfunding.vo;

import java.util.ArrayList;
import java.util.List;

import com.atlzk.atcrowdfunding.bean.MemberCert;
import com.atlzk.atcrowdfunding.bean.User;

public class UserData {
	
	private List<User> userList = new ArrayList<User>();
	
	private List<User> datas = new ArrayList<User>();
	
	private List<Integer> ids;
	
	private List<MemberCert> certtimgs = new ArrayList<MemberCert>();

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getDatas() {
		return datas;
	}

	public void setDatas(List<User> datas) {
		this.datas = datas;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<MemberCert> getCerttimgs() {
		return certtimgs;
	}

	public void setCerttimgs(List<MemberCert> certtimgs) {
		this.certtimgs = certtimgs;
	}
	
	
	
}
