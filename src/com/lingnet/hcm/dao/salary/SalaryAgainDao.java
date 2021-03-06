package com.lingnet.hcm.dao.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryAgain;
import com.lingnet.util.Pager;

/**
 * 二次分配总量主表
 * @ClassName: SalaryAgainDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月21日 下午9:12:54 
 *
 */
public interface SalaryAgainDao extends BaseDao<SalaryAgain, String>{

    /**
     * @Title: 获取员工二次分配
     * @param period
     * @param companyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月21日 V 1.0
     */
    public Map<String, Object> getAllSecondAmountListData(String period,
            String companyId, Pager pager);

    /**
     * @Title: 获取当前公司薪酬期间二次分配总量数据
     * @param searchData
     * @param companyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月22日 V 1.0
     */
    public Map<String, Object> getPeriodAmountListData(String searchData, String companyId,
            Pager pager);

    /**
     * @Title: 获取所有员工的薪资组
     * @param companyId
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月22日 V 1.0
     */
    public List<Map<String, Object>> getAllStaffSalaryGroup(String companyId);

    /**
     * @Title: 获取需要更新的员工薪资标准值
     * @param companyId
     * @param period
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月25日 V 1.0
     */
    public List<Map<String, Object>> getPeriodStaffInfo(String companyId, String period);

    /**
     * @Title: 获取二次分配数据SQL
     * @param period
     * @param companyId
     * @return 
     * List<?> 
     * @author zhanghj
     * @since 2017年6月26日 V 1.0
     */
    public List<?> getAllSecondForTypeSql(String period, String companyId, String searchData);

    /**
     * @Title: 获取绩效奖励 总量
     * @param itemId
     * @return 
     * Double 
     * @author zhanghj
     * @since 2017年6月29日 V 1.0
     */
    public Double getItemTotalValue(String companyId, String deptId, String periodId, String fpqx, String itemId);

    /**
     * @Title: 获取员工二次分配SQL
     * @param period
     * @param companyId
     * @param searchData
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String getAllSecondAmountListDataSql(String period, String companyId, String searchData);

    /**
     * @Title: 获取二次分配不同总量计算的总量数据
     * @param companyId
     * @param period
     * @param fpqx
     * @param deptId
     * @return 
     * String
     * @author zhanghj
     * @since 2017年8月2日 V 1.0
     */
    public String getPerTypeSecondData(String companyId, String period, String fpqx, String deptId);

    /**
     * @Title: 获取其他薪资项目的分配总量
     * @param companyId
     * @param period
     * @param fpqx
     * @param deptId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月19日 V 1.0
     */
    public String getOtherAmountValue(String companyId, String period,
            String fpqx, String deptId);

}
