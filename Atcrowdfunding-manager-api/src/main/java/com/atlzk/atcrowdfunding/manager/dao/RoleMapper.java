package com.atlzk.atcrowdfunding.manager.dao;

import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.bean.RolePermission;
import com.atlzk.atcrowdfunding.bean.User;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	List<User> queryRoleList(Map<String, Object> paramMap);

	Integer queryRoleCount(Map<String, Object> paramMap);

	int deleteBatchRoleByVO(List<Role> datas);

	void deleteRolePermissionRelationship(Integer roleid);

	int insertRolePermission(RolePermission rolePermission);

}