package com.lingnet.qxgl.service;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.WorkFlowChild;

public interface WorkFlowService extends BaseService<String, WorkFlowChild>{
	
    public WorkFlowChild findnextbyid(String id);

    public Integer findmaxbypid(String pid);

    /**
     * @Title: 获取某个审批流程的下一个审批岗位
     * @param pid
     * @param nodeid
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月19日 V 1.0
     */
    public Map<String, Object> getNextCheckDept(String pid, String nodeid);
}
