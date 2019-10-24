package com.atlzk.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlzk.atcrowdfunding.bean.Advertisement;
import com.atlzk.atcrowdfunding.bean.User;
import com.atlzk.atcrowdfunding.manager.dao.AdvertisementMapper;
import com.atlzk.atcrowdfunding.manager.service.AdvertisementService;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.vo.UserData;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	
	@Autowired
	private AdvertisementMapper advertisementMapper;

	public Page queryUserPage(Map<String, Object> paramMap) {
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		
		Integer startIndex = page.getStartIndex();
		
		paramMap.put("startIndex", startIndex);
	
		List<Advertisement> datas = advertisementMapper.queryAdvertisementList(paramMap);
	
		page.setData(datas);
		
		Integer totalsize = advertisementMapper.queryAdvertisementCount(paramMap);
		page.setTotalsize(totalsize);
	
		
		return page;
	}

	public int deleteByPrimaryKey(Integer id) {
		return advertisementMapper.deleteByPrimaryKey(id);
	}

	public int doDeleteBatchAdvertisementByVO(UserData data) {
		return advertisementMapper.deleteBatchAdvertisementByVO(data.getDatas());
	}

	public int insertAdvert(Advertisement advert) {
		return advertisementMapper.insert(advert);
	}

	public Advertisement getAdvertById(Integer id) {
		return advertisementMapper.selectByPrimaryKey(id);
	}

	public int updateAdvertById(Integer id, Advertisement advert) {
		advert.setId(id);
		return advertisementMapper.updateByPrimaryKey(advert);
	}
}
