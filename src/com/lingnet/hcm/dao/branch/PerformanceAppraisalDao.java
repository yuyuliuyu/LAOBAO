package com.lingnet.hcm.dao.branch;

import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.basic.PerformanceAppraisal;
import com.lingnet.util.Pager;

/**
 * 绩效考核
 * @ClassName: PerformanceAppraisalDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年7月12日 下午6:26:33 
 *
 */
public interface PerformanceAppraisalDao extends BaseDao<PerformanceAppraisal, String>{

    /**
     * @Title: 获取当前公司所有人信息
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public Map<String, Object> getCurComStaffDatas(String searchData, Pager pager);

    /**
     * @Title: 获取当前登录人以及领导部门下的人的考核信息
     * @param userId
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public Map<String, Object> getCurAndDeptDatas(String userId,
            String searchData, Pager pager);

    /**
     * @Title: 个人绩效一览SQL
     * @param userId
     * @param searchData
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月13日 V 1.0
     */
    public String[] getCurAndDeptDatasSql(String userId, String searchData);

}
