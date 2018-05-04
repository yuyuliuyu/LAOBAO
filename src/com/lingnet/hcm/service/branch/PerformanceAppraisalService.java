package com.lingnet.hcm.service.branch;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.basic.PerformanceAppraisal;
import com.lingnet.util.Pager;

/**
 * 绩效考核
 * @ClassName: PerformanceAppraisalService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年7月12日 下午5:26:12 
 *
 */
public interface PerformanceAppraisalService extends BaseService<PerformanceAppraisal, String> {

    /**
     * @Title: 列表数据展现
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public Map<String, Object> jsonlist(String searchData, Pager pager);

    /**
     * @Title: 保存或者更新
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String saveOrUpdate(String formdata);

    /**
     * @Title: 删除方法
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 确认按钮
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String rebiut(String ids);

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
    public Map<String, Object> getCurAndDeptDatas(String userId, String searchData,
            Pager pager);

    /**
     * @Title: 考核兑现
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String checkSure(String ids);

    /**
     * @Title: 考核不兑现
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String checkCancel(String ids);

    /**
     * @Title: 绩效考核一览导出 
     * @param searchData 
     * void 
     * @author zhanghj
     * @since 2017年8月13日 V 1.0
     */
    public void export(String searchData);

    /**
     * @Title: 个人绩效考核一览导出 
     * @param userId 
     * @param searchData 
     * void 
     * @author zhanghj
     * @since 2017年8月13日 V 1.0
     */
    public void exportForPer(String userId, String searchData);

}
