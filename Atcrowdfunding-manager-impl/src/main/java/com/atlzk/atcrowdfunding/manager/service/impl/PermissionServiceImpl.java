package com.atlzk.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlzk.atcrowdfunding.bean.Permission;
import com.atlzk.atcrowdfunding.manager.dao.PermissionMapper;
import com.atlzk.atcrowdfunding.manager.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	PermissionMapper permissionMapper;
	
	public Permission queryRootPermission() {
		return permissionMapper.queryRootPermission();
	}

	public List<Permission> queryChildrenPermissionByPid(Integer id) {
		return permissionMapper.queryChildrenPermissionByPid(id);
	}

	public List<Permission> queryAllPermission() {
		
		return permissionMapper.queryAllPermission();
	}

	public int savePermission(Permission permission) {
		permission.setIcon("glyphicon glyphicon glyphicon-tasks");
		return permissionMapper.insert(permission);
	}

	public int deletePermission(Integer id) {
		return permissionMapper.deleteByPrimaryKey(id);
	}

	public Permission getPermissionById(Integer id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	public int updatePermission(Permission permission) {
		return permissionMapper.updateByPrimaryKey(permission);
	}

	public List<Integer> queryPermissionIdsByRoleId(Integer roleid) {
		return permissionMapper.queryPermissionIdsByRoleId(roleid);
	}

}
