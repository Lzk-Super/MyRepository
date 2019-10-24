package com.atlzk.atcrowdfunding.vo;

import java.util.ArrayList;
import java.util.List;

import com.atlzk.atcrowdfunding.bean.MemberCert;
import com.atlzk.atcrowdfunding.bean.Role;

public class RoleData {
	
	private List<Role> userList = new ArrayList<Role>();
	
	private List<Role> datas = new ArrayList<Role>();
	
	private List<Integer> ids;
	
	private List<MemberCert> certtimgs = new ArrayList<MemberCert>();

	public List<Role> getUserList() {
		return userList;
	}

	public void setUserList(List<Role> userList) {
		this.userList = userList;
	}

	public List<Role> getDatas() {
		return datas;
	}

	public void setDatas(List<Role> datas) {
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
