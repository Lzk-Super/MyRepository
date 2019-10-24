package com.atlzk.atcrowdfunding.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlzk.atcrowdfunding.bean.Permission;
import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.bean.User;
import com.atlzk.atcrowdfunding.exception.LoginFailException;
import com.atlzk.atcrowdfunding.manager.dao.UserMapper;
import com.atlzk.atcrowdfunding.manager.service.UserService;
import com.atlzk.atcrowdfunding.util.Const;
import com.atlzk.atcrowdfunding.util.MD5Util;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.vo.UserData;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	public User queryUserLogin(Map map) {
		User user = userMapper.queryUserLogin(map);
		if(user == null) {
			throw new LoginFailException("用户名或密码输入错误！！！");
		}
		return user;
	}
	
	
	//分页，条件查询整合
	public Page queryUserPage(Map<String,Object> paramMap) {
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		
		Integer startIndex = page.getStartIndex();
		
		paramMap.put("startIndex", startIndex);
	
		List<User> datas = userMapper.queryUserList(paramMap);
	
		page.setData(datas);
		
		Integer totalsize = userMapper.queryUserCount(paramMap);
		page.setTotalsize(totalsize);
	
		
		return page;
	}

	public int saveUser(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		
		String createtime = sdf.format(date);
		
		user.setCreatetime(createtime);
		
		user.setUserpswd(MD5Util.digest(Const.PASSWORD));
		
		
		
		return userMapper.insert(user);
	}

	public User getUserById(Integer id) {
		
		User user = userMapper.selectByPrimaryKey(id);
		
		return user;
	}

	public int updateUser(User user) {
		int i = userMapper.updateByPrimaryKey(user);
		return i;
	}
	
	//删除一条数据
	public int deleteUser(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	//批量删除（Array数组传值）
	public int doDeleteBatchUser(Integer[] id) {
		int count = 0;

		for(Integer i : id) {
			int j = userMapper.deleteByPrimaryKey(i);
			count += j;
		}
		return count;
	}

	//批量删除（根据VO对象批量传值）
	/*public int doDeleteBatchUserByVO(UserData data) {
		return userMapper.deleteBatchUserByVO(data);
	}*/
	
	//批量删除（List集合传值）
	public int doDeleteBatchUserByVO(UserData data) {
		return userMapper.deleteBatchUserByVO(data.getDatas());
	}


	public List<Role> queryAllRole() {
		return userMapper.queryAllRole();
	}


	public List<Integer> queryRoleByUserId(Integer id) {
		return userMapper.queryRoleByUserId(id);
	}


	public int saveUserRoleRelationship(Integer userid, UserData data) {

		return userMapper.saveUserRoleRelationship(userid,data);
	}


	public int deleteUserRoleRelationship(Integer userid, UserData data) {
		
		return userMapper.deleteUserRoleRelationship(userid,data);
	}


	public List<Permission> queryPermissionByUserId(Integer id) {
		return userMapper.queryPermissionByUserId(id);
	}
	
}
