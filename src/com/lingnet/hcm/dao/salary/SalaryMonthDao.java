package com.lingnet.hcm.dao.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryMonth;
import com.lingnet.util.Pager;

/**
 * 平均工资计算过程与薪资项目关联表
 * @ClassName: SalaryMonthDao 
 * @Description: 平均工资计算过程与薪资项目关联表 
 * @author zhanghj
 * @date 2017年4月25日 上午7:59:59 
 *
 */
public interface SalaryMonthDao extends BaseDao<SalaryMonth, String>{

    /**
     * @Title: 获取关联到的薪资项目 
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月25日 V 1.0
     */
    public List<Map<String, Object>> getMonthItemForId(String id);

    /**
     * @Title: 获取需要计算平均工资的员工 
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月25日 V 1.0
     */
    public List<Map<String, Object>> getPersonalToAllocationListData(String id);

    /**
     * @Title: 获取月平均工资的员工 
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月25日 V 1.0
     */
    public Map<String, Object> getMonthSalaryListData(String id, Pager pager);

    /**
     * @Title: 获取维护的员工月平均工资
     * @param depId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月28日 V 1.0
     */
    public Map<String, Object> getMonthAvgSalary(String depId, Pager pager);

    /**
     * @Title: 获取参与计算的员工信息
     * @param id
     * @param year
     * @param companyId
     * @param type 计算类别：1：不足整月的按整月计算 2：不足整月的舍弃次月
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月30日 V 1.0
     */
    public List<Map<String, Object>> getCanJsStaffs(String id, int year, String companyId, String type);

    /**
     * @Title: 重新计算过程中存在的员工SQL
     * @param id
     * @param year
     * @param companyId
     * @param type 计算类别：1：不足整月的按整月计算 2：不足整月的舍弃次月
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月31日 V 1.0
     */
    public String updateReloadCalculation(String id, int year, String companyId, String type);

}
