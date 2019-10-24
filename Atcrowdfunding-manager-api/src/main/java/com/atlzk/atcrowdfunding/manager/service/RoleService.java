package com.atlzk.atcrowdfunding.manager.service;

import java.util.Map;

import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.vo.RoleData;

public interface RoleService {

	Page queryRolePage(Map<String, Object> paramMap);

	int saveRole(Role role);

	int deleteRole(Integer id);

	Role getRoleById(Integer id);

	int updateRole(Role role);

	int doDeleteBatchRoleByVO(RoleData data);

	int saveRolePermissionRelationship(Integer roleid, RoleData data);

}
