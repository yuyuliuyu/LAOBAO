package com.lingnet.hcm.dao.contract;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.AutoProjectCode;


public interface AutoProjectCodeDao extends BaseDao<AutoProjectCode,String> {

    public List<AutoProjectCode> findCode();

}
