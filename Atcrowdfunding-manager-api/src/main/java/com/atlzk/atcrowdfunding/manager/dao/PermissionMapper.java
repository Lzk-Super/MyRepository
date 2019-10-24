package com.atlzk.atcrowdfunding.manager.dao;

import com.atlzk.atcrowdfunding.bean.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

	Permission queryRootPermission();

	List<Permission> queryChildrenPermissionByPid(Integer id);

	List<Permission> queryAllPermission();

	List<Integer> queryPermissionIdsByRoleId(Integer roleid);
}