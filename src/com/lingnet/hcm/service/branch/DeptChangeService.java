package com.lingnet.hcm.service.branch;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.DeptChange;

public interface DeptChangeService extends BaseService<DeptChange, String> {

    /**
     * 保存方法
     * @Title: saveOrUpdata 
     * @param formdata
     * @param img
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月20日 V 1.0
     */
    public String saveOrUpdata(String formdata, String img) throws Exception;


}
