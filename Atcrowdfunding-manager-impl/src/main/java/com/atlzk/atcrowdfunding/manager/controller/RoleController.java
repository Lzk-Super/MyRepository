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

import com.atlzk.atcrowdfunding.bean.Permission;
import com.atlzk.atcrowdfunding.bean.Role;
import com.atlzk.atcrowdfunding.controllerutil.BaseController;
import com.atlzk.atcrowdfunding.manager.service.PermissionService;
import com.atlzk.atcrowdfunding.manager.service.RoleService;
import com.atlzk.atcrowdfunding.util.AjaxResult;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.util.StringUtil;
import com.atlzk.atcrowdfunding.vo.RoleData;

@Controller()
@RequestMapping("/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	//跳转到user/index.jsp
	@RequestMapping("/index")
	public String index() {
		return "role/index";
		
	}
	
	//跳转到user/add.jsp
	@RequestMapping("/add")
	public String add() {
		return "role/add";
	}
	
	//跳转到user/update.jsp
	@RequestMapping("/update")
	public String update(Integer id,Map<String, Role> map) {
		
		Role role = roleService.getRoleById(id);
		
		map.put("role", role);
		
		return "role/update";
	}
	
	//角色权限控制
	@RequestMapping("/assign")
	public String assignPermission() {
		return "role/assign";
	}
	
	//条件查询，分页查询整合
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
						@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize,
						String queryText) {
		AjaxResult result = new AjaxResult();
//		start();
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
			
			Page page = roleService.queryRolePage(paramMap);

			result.setSuccess(true);
			result.setPage(page);
			
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	//添加角色
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Role role) {
		AjaxResult result = new AjaxResult();
		try {

			int count = roleService.saveRole(role);
			
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
	public Object doUpdate(Role role) {
		AjaxResult result = new AjaxResult();
		try {

			int count = roleService.updateRole(role);
			
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

			int count = roleService.deleteRole(id);
			
			result.setSuccess(count == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	//接受多条数据,批量删除
	@ResponseBody
	@RequestMapping("/doDeleteBatch")
	public Object doDeleteBatch(RoleData data) {
		AjaxResult result = new AjaxResult();

		try {

			int count = roleService.doDeleteBatchRoleByVO(data);
			
			result.setSuccess(count == data.getDatas().size());
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	//从数据库加载许可树Demo3-循环
		@ResponseBody
		@RequestMapping("/loadAssign")
		public Object loadAssign(Integer roleid) {
			
			List<Permission> rootPermissions = new ArrayList<Permission>();

			List<Permission> permissons =  permissionService.queryAllPermission();
			
			//根据角色id查询该角色之前所分配的许可
			List<Integer> permissionIdsForRoleId = permissionService.queryPermissionIdsByRoleId(roleid);

			Map<Integer,Permission> map = new HashMap<Integer,Permission>();

			for (Permission permission : permissons) {

				map.put(permission.getId(), permission);
				
				if(permissionIdsForRoleId.contains(permission.getId())) {
					permission.setChecked(true);
				}

			}

			 

			for (Permission permission : permissons) {

			//子节点

			Permission child = permission ; //假设为子菜单

			if(child.getPid() == 0 ){
				
				permission.setOpen(true);

				rootPermissions.add(permission); //根节点

			}else{

			//父节点

			Permission parent = map.get(child.getPid());

			parent.getChildren().add(permission);                                                

			 

			}

			}

			return rootPermissions;
		}
		
		//给角色分配许可
		@ResponseBody
		@RequestMapping("/doAssignPermission")
		public Object doAssignPermission(Integer roleid,RoleData data) {
			AjaxResult result = new AjaxResult();
			try {

				int count = roleService.saveRolePermissionRelationship(roleid,data);
				
				result.setSuccess(count == data.getIds().size());
				
			} catch (Exception e) {
				result.setSuccess(false);
				e.printStackTrace();
				result.setMessage("删除失败！！！");
			}
			return result;	//将对象序列化为字符串，以JSON流形式返回
		}
}
