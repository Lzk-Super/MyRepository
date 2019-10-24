package com.atlzk.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.bean.User;
import com.atlzk.atcrowdfunding.manager.service.UserService;
import com.atlzk.atcrowdfunding.util.AjaxResult;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.util.StringUtil;
import com.atlzk.atcrowdfunding.vo.UserData;

@Controller()
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "user/add";
	}
	
	@RequestMapping("/update")
	public String update(Integer id,Map<String, User> map) {
		
		User user = userService.getUserById(id);
		
		map.put("user", user);
		
		return "user/update";
	}
	
	//条件查询，分页查询整合
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
						@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize,
						String queryText) {
		AjaxResult result = new AjaxResult();
		try {
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			
			if(StringUtil.isNotEmpty(queryText)) {
				if(queryText.contains("%")) {
					queryText = queryText.replaceAll("%", "\\\\%");
				}
				paramMap.put("queryText", queryText);
			}
			
			Page page = userService.queryUserPage(paramMap);
			
			result.setSuccess(true);
			result.setPage(page);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(User user) {
		AjaxResult result = new AjaxResult();
		try {

			int count = userService.saveUser(user);
			
			result.setSuccess(count == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("保存失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(User user) {
		AjaxResult result = new AjaxResult();
		try {

			int count = userService.updateUser(user);
			
			result.setSuccess(count == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("修改失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {

			int count = userService.deleteUser(id);
			
			result.setSuccess(count == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	//接收一个参数名带多个值
/*	@ResponseBody
	@RequestMapping("/doDeleteBatch")
	public Object doDeleteBatch(Integer[] id) {
		AjaxResult result = new AjaxResult();

		try {

			int count = userService.doDeleteBatchUser(id);
			
			result.setSuccess(count == id.length);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}*/
	
	//接受多条数据
//	@ResponseBody
//	@RequestMapping("/doDeleteBatch")
//	public Object doDeleteBatch(UserData data) {
//		AjaxResult result = new AjaxResult();
//
//		try {
//
//			int count = userService.doDeleteBatchUserByVO(data);
//			
//			result.setSuccess(count == data.getDatas().size());
//			
//		} catch (Exception e) {
//			result.setSuccess(false);
//			e.printStackTrace();
//			result.setMessage("删除失败！！！");
//		}
//		return result;	//将对象序列化为字符串，以JSON流形式返回
//	}
	
	@ResponseBody
	@RequestMapping("/doDeleteBatch")
	public Object doDeleteBatch(UserData data) {
		AjaxResult result = new AjaxResult();

		try {

			int count = userService.doDeleteBatchUserByVO(data);
			
			result.setSuccess(count == data.getDatas().size());
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	//显示分配页面数据
	@RequestMapping("/assignRole")
	public String assignRole(Integer id,Map<String, List<Role>> map) {
		//查询所有角色
		List<Role> allListRole = userService.queryAllRole();
		
		//根据用户id查询出已分配角色的角色id
		List<Integer> roleIds = userService.queryRoleByUserId(id);
		
		//未分配集合
		List<Role> leftRoleList = new ArrayList<Role>();
		
		//已分配集合
		List<Role> rightRoleList = new ArrayList<Role>();
		
		for(Role role : allListRole) {
			
			if(roleIds.contains(role.getId())) {
				rightRoleList.add(role);
			}else {
				leftRoleList.add(role);
			}
		}
		
		map.put("leftRoleList", leftRoleList);
		map.put("rightRoleList", rightRoleList);
		
		return "user/assignRole";
	}
	
	
	 //分配角色
	@ResponseBody
	@RequestMapping("/doAssignRole")
	public Object doAssignRole(Integer userid,UserData data) {
		AjaxResult result = new AjaxResult();
		
		
		if(data.getIds() == null) {
			result.setSuccess(false);
			result.setMessage("请选择要分配的角色！！！");
			return result;
		}
		
		try {

			userService.saveUserRoleRelationship(userid,data);
			
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("分配角色失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	//取消分配角色
	@ResponseBody
	@RequestMapping("/doUnAssignRole")
	public Object doUnAssignRole(Integer userid,UserData data) {
		AjaxResult result = new AjaxResult();
		if(data.getIds() == null) {
			result.setSuccess(false);
			result.setMessage("请选择要取消分配的角色！！！");
			return result;
		}
		
		try {

			userService.deleteUserRoleRelationship(userid,data);
			
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("取消角色失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
}
