package com.atlzk.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atlzk.atcrowdfunding.bean.Permission;
import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.bean.User;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.vo.UserData;

public interface UserService {
	public User queryUserLogin(Map map) ;
	
	public int saveUser(User user);

	public Page queryUserPage(Map<String,Object> paramMap);

	public User getUserById(Integer id);

	public int updateUser(User user);

	public int deleteUser(Integer id);

	public int doDeleteBatchUser(Integer[] id);
	
	public int doDeleteBatchUserByVO(UserData data);

	public List<Role> queryAllRole();

	public List<Integer> queryRoleByUserId(Integer id);

	public int saveUserRoleRelationship(Integer userid, UserData data);

	public int deleteUserRoleRelationship(Integer userid, UserData data);

	public List<Permission> queryPermissionByUserId(Integer id);

}
