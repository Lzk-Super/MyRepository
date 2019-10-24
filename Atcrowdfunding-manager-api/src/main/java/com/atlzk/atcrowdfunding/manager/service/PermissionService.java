package com.atlzk.atcrowdfunding.manager.service;

import java.util.List;

import com.atlzk.atcrowdfunding.bean.Permission;

public interface PermissionService {

	Permission queryRootPermission();

	List<Permission> queryChildrenPermissionByPid(Integer id);

	List<Permission> queryAllPermission();

	int savePermission(Permission permission);

	int deletePermission(Integer id);

	Permission getPermissionById(Integer id);

	int updatePermission(Permission permission);

	List<Integer> queryPermissionIdsByRoleId(Integer roleid);

}
