package com.atlzk.atcrowdfunding.manager.dao;

import com.atlzk.atcrowdfunding.bean.Cert;
import java.util.List;

public interface CertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cert record);

    Cert selectByPrimaryKey(Integer id);

    List<Cert> selectAll();

    int updateByPrimaryKey(Cert record);
}