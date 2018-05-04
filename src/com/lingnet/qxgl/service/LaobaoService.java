package com.lingnet.qxgl.service;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.Branch;

public interface LaobaoService extends BaseService<Branch, String>{

    /**
     * 获取当前用户有数据权限的部门树 数据
     * @Title: getTreeListByUser 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String getTreeListByUser();
}
