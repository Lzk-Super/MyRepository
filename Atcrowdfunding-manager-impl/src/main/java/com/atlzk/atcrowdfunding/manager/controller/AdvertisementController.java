package com.atlzk.atcrowdfunding.manager.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atlzk.atcrowdfunding.bean.Advertisement;
import com.atlzk.atcrowdfunding.bean.User;
import com.atlzk.atcrowdfunding.manager.service.AdvertisementService;
import com.atlzk.atcrowdfunding.util.AjaxResult;
import com.atlzk.atcrowdfunding.util.Const;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.util.StringUtil;
import com.atlzk.atcrowdfunding.vo.UserData;




@Controller
@RequestMapping("/advert")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;

	@RequestMapping("/index")
	public String index() {
		return "advert/index";
	}

	@RequestMapping("/add")
	public String add() {
		return "advert/add";
	}
	
	@RequestMapping("/update")
	public String update(Integer id,Map<String, Advertisement> map) {
		
		Advertisement advert = advertisementService.getAdvertById(id);
		
		map.put("advert", advert);
		
		return "advert/update";
	}

	// 条件查询，分页查询整合
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize,
			String queryText) {
		AjaxResult result = new AjaxResult();
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);

			if (StringUtil.isNotEmpty(queryText)) {
				if (queryText.contains("%")) {
					queryText = queryText.replaceAll("%", "\\\\%");
				}
				paramMap.put("queryText", queryText);
			}

			Page page = advertisementService.queryUserPage(paramMap);

			result.setSuccess(true);
			result.setPage(page);

		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败！！！");
		}
		return result; // 将对象序列化为字符串，以JSON流形式返回
	}

	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {

			int count = advertisementService.deleteByPrimaryKey(id);

			result.setSuccess(count == 1);

		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除数据失败！！！");
		}
		return result; // 将对象序列化为字符串，以JSON流形式返回
	}

	@ResponseBody
	@RequestMapping("/doDeleteBatch")
	public Object doDeleteBatch(UserData data) {
		AjaxResult result = new AjaxResult();

		try {

			int count = advertisementService.doDeleteBatchAdvertisementByVO(data);

			result.setSuccess(count == data.getDatas().size());

		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除失败！！！");
		}
		return result; // 将对象序列化为字符串，以JSON流形式返回
	}

	/**
	 * 
	 * 新增
	 * 
	 * @return
	 * 
	 */

	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(HttpServletRequest request, Advertisement advert, HttpSession session) {

		AjaxResult result = new AjaxResult();

		try {

			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;

			MultipartFile mfile = mreq.getFile("advpic");

			String name = mfile.getOriginalFilename();// java.jpg

			String extname = name.substring(name.lastIndexOf(".")); // .jpg

			String iconpath = UUID.randomUUID().toString() + extname; // 232243343.jpg

			ServletContext servletContext = session.getServletContext();

			String realpath = servletContext.getRealPath("/pics");

			String path = realpath + "\\adv\\" + iconpath;

			mfile.transferTo(new File(path));

			User user = (User) session.getAttribute(Const.LOGIN_USER);

			advert.setUserid(user.getId());

			advert.setStatus("1");

			advert.setIconpath(iconpath);

			int count = advertisementService.insertAdvert(advert);

			result.setSuccess(count == 1);

		} catch (Exception e) {

			e.printStackTrace();

			result.setSuccess(false);

		}

		return result;

	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(Integer id,HttpServletRequest request, Advertisement advert, HttpSession session) {
		
		AjaxResult result = new AjaxResult();

		try {

			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;

			MultipartFile mfile = mreq.getFile("advpic");

			String name = mfile.getOriginalFilename();// java.jpg

			String extname = name.substring(name.lastIndexOf(".")); // .jpg

			String iconpath = UUID.randomUUID().toString() + extname; // 232243343.jpg

			ServletContext servletContext = session.getServletContext();

			String realpath = servletContext.getRealPath("/pics");

			String path = realpath + "\\adv\\" + iconpath;

			mfile.transferTo(new File(path));

			User user = (User) session.getAttribute(Const.LOGIN_USER);

			advert.setUserid(user.getId());

			advert.setStatus("1");

			advert.setIconpath(iconpath);

			int count = advertisementService.updateAdvertById(id,advert);

			result.setSuccess(count == 1);

		} catch (Exception e) {

			e.printStackTrace();

			result.setSuccess(false);

		}

		return result;
	}

}
