package com.atlzk.atcrowdfunding.manager.dao;

import com.atlzk.atcrowdfunding.bean.MemberProjectFollow;
import java.util.List;

public interface MemberProjectFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberProjectFollow record);

    MemberProjectFollow selectByPrimaryKey(Integer id);

    List<MemberProjectFollow> selectAll();

    int updateByPrimaryKey(MemberProjectFollow record);
}