package com.atlzk.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.bean.RolePermission;
import com.atlzk.atcrowdfunding.bean.User;
import com.atlzk.atcrowdfunding.manager.dao.RoleMapper;
import com.atlzk.atcrowdfunding.manager.service.RoleService;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.vo.RoleData;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleMapper roleMapper;
	
	public Page queryRolePage(Map<String, Object> paramMap) {
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		
		Integer startIndex = page.getStartIndex();
		
		paramMap.put("startIndex", startIndex);
	
		List<User> datas = roleMapper.queryRoleList(paramMap);
	
		page.setData(datas);
		
		Integer totalsize = roleMapper.queryRoleCount(paramMap);
		page.setTotalsize(totalsize);
	
		
		return page;
	}

	public int saveRole(Role role) {
		return roleMapper.insert(role);
	}

	public int deleteRole(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	public Role getRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public int updateRole(Role role) {
		return roleMapper.updateByPrimaryKey(role);
	}

	public int doDeleteBatchRoleByVO(RoleData data) {
		return roleMapper.deleteBatchRoleByVO(data.getDatas());
	}

	public int saveRolePermissionRelationship(Integer roleid, RoleData data) {
		
		roleMapper.deleteRolePermissionRelationship(roleid);
		
		int totalCount = 0;
		List<Integer> ids = data.getIds();
		for(Integer id :ids) {
			RolePermission rolePermission = new RolePermission(); 
			rolePermission.setRoleid(roleid);
			rolePermission.setPermissionid(id);
			int count = roleMapper.insertRolePermission(rolePermission);
			totalCount += count;
			
		}
		return totalCount;
	}

}
