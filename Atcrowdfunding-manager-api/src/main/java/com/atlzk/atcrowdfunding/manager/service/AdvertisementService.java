package com.atlzk.atcrowdfunding.manager.service;

import java.util.Map;

import com.atlzk.atcrowdfunding.bean.Advertisement;
import com.atlzk.atcrowdfunding.util.Page;
import com.atlzk.atcrowdfunding.vo.UserData;

public interface AdvertisementService {

	Page queryUserPage(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Integer id);

	int doDeleteBatchAdvertisementByVO(UserData data);

	int insertAdvert(Advertisement advert);

	Advertisement getAdvertById(Integer id);

	int updateAdvertById(Integer id, Advertisement advert);

}
