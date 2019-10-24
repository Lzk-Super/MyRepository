package com.atlzk.atcrowdfunding.manager.dao;

import com.atlzk.atcrowdfunding.bean.Permission;
import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.bean.User;
import com.atlzk.atcrowdfunding.vo.UserData;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    
    //登录验证
	User queryUserLogin(Map<String, Object> map);
	
	//分页查询一页信息
	//List<User> queryUserList(@Param("startIndex") Integer startIndex, @Param("pagesize") Integer pagesize);
	
	List<User> queryUserList(Map<String,Object> paramMap);
	//查询数据总数
	Integer queryUserCount(Map<String,Object> paramMap);

	//int deleteBatchUserByVO(UserData data);
	int deleteBatchUserByVO(List<User> userList);

	List<Role> queryAllRole();

	List<Integer> queryRoleByUserId(Integer id);

	int saveUserRoleRelationship(@Param("userid") Integer userid,@Param("data") UserData data);

	int deleteUserRoleRelationship(@Param("userid") Integer userid,@Param("data") UserData data);

	List<Permission> queryPermissionByUserId(Integer id);
	
}