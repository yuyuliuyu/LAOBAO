package com.lingnet.qxgl.dao;

import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.WorkFlowChild;
/**
 * 
 * @ClassName: WorkFlowChildDao 
 * @Description: 工作流节点Dao 
 * @author wangqiang
 * @date 2017年5月3日 上午11:17:20 
 *
 */
public interface WorkFlowChildDao extends BaseDao<WorkFlowChild, String>{

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
