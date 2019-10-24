package com.atlzk.atcrowdfunding.manager.dao;

import com.atlzk.atcrowdfunding.bean.Advertisement;
import com.atlzk.atcrowdfunding.bean.User;

import java.util.List;
import java.util.Map;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertisement record);

    Advertisement selectByPrimaryKey(Integer id);

    List<Advertisement> selectAll();

    int updateByPrimaryKey(Advertisement record);

	List<Advertisement> queryAdvertisementList(Map<String, Object> paramMap);

	Integer queryAdvertisementCount(Map<String, Object> paramMap);

	int deleteBatchAdvertisementByVO(List<User> datas);
}