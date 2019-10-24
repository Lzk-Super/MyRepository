package com.atlzk.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atlzk.atcrowdfunding.bean.Permission;
import com.atlzk.atcrowdfunding.manager.service.PermissionService;
import com.atlzk.atcrowdfunding.util.AjaxResult;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
	
	//跳转到permission/index.jsp
	@RequestMapping("/index")
	public String index() {
		
		return "permission/index";
		
	}	
	
	//跳转到permission/index.jsp
	@RequestMapping("/add")
	public String add() {
		
		return "permission/add";
		
	}
	
	@RequestMapping("/update")
	public String update(Integer id,Map<String, Permission> map) {
		
		Permission permission = permissionService.getPermissionById(id);
		
		map.put("permission", permission);
		
		return "permission/update";
	}
	
//	//从数据库加载许可树
//	@ResponseBody
//	@RequestMapping("/loadData")
//	public Object loadData() {
//		
//		AjaxResult result = new AjaxResult();
//		try {
//			//父
//		    Permission permission = new	Permission();
//		    permission.setName("系统权限菜单");
//		    
//		    //子
//		    List<Permission> children = new ArrayList<Permission>();
//		    
//		    Permission permission1 = new Permission();
//		    permission1.setName("控制面板");
//		    
//		    Permission permission2 = new Permission();
//		    permission2.setName("权限管理");
//		    
//		    children.add(permission1);
//		    children.add(permission2);
//		    //设置父子关系
//		    permission.setChildren(children);
//			
//			result.setSuccess(true);
//			result.setData(permission);
//		} catch (Exception e) {
//			result.setSuccess(false);
//			
//			result.setMessage("加载许可树失败");
//		}
//		
//		return result;
//	}	
	
//	//从数据库加载许可树Demo2
//	@ResponseBody
//	@RequestMapping("/loadData")
//	public Object loadData() {
//		
//		AjaxResult result = new AjaxResult();
//		try {
//			Permission root = permissionService.queryRootPermission();
//
//			root.setOpen(true);
//
//			List<Permission> childredPermissons =  permissionService.queryChildrenPermissionByPid(root.getId());
//
//			root.setChildren(childredPermissons);
//
//			for (Permission childredPermisson : childredPermissons) {
//
//				childredPermisson.setOpen(true);
//
//				List<Permission> innerChildredPermissons =  permissionService.queryChildrenPermissionByPid(childredPermisson.getId());
//
//				childredPermisson.setChildren(innerChildredPermissons);
//
//			}
//
//			result.setData(root);
//			result.setSuccess(true);
//		} catch (Exception e) {
//			result.setSuccess(false);
//			
//			result.setMessage("加载许可树失败");
//		}
//		
//		return result;
//	}		
	
	//从数据库加载许可树Demo3-循环
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		
		AjaxResult result = new AjaxResult();
		try {
			//Demo 5 一次查询所有数据,使用Map存放数据进行判断,减少循环次数
			
			List<Permission> root = new ArrayList<Permission>();

			List<Permission> permissions =  permissionService.queryAllPermission();

			Map<Integer,Permission> map = new HashMap<Integer,Permission>();

			for (Permission permission : permissions) {

				map.put(permission.getId(), permission);

			}

			for (Permission permission : permissions) {

				//子节点

				Permission child = permission ; //假设为子菜单
	
				if(child.getPid() == 0 ){
					
					child.setOpen(true);
					
					root.add(permission);
	
				}else{
	
					//父节点
					
					Permission parent = map.get(child.getPid());
		
					parent.getChildren().add(permission);        
	
				}

			}
			result.setSuccess(true);
			result.setData(root);

			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("加载许可树失败");
		}
		
		return result;
	}	
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission) {
		AjaxResult result = new AjaxResult();
		try {

			int count = permissionService.savePermission(permission);
			
			result.setSuccess(count == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("保存失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {

			int count = permissionService.deletePermission(id);
			
			result.setSuccess(count == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(Permission permission) {
		AjaxResult result = new AjaxResult();
		try {

			int count = permissionService.updatePermission(permission);
			
			result.setSuccess(count == 1);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("修改失败！！！");
		}
		return result;	//将对象序列化为字符串，以JSON流形式返回
	}
}
