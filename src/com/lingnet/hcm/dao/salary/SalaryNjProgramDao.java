/**
 * @SalaryNjProgramDao.java
 * @com.lingnet.hcm.dao.salary
 * @Description：
 * 
 * @author zhanghj
 * @copyright  2017
 * @version V
 * @since 2017年6月15日
 */
package com.lingnet.hcm.dao.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryNjProgram;
import com.lingnet.util.Pager;

/**
 * 企业年金缴费分配方案
 * @ClassName: SalaryNjProgramDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月15日 下午4:28:21 
 *  
 */

public interface SalaryNjProgramDao extends BaseDao<SalaryNjProgram, String> {

    /**
     * @Title: 获取年龄系数数据
     * @param companyId
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年6月15日 V 1.0
     */
    public List<Map<String, Object>> getNlxsData(String companyId);

    /**
     * @Title: 获取所有员工的信息SQL
     * @param companyId
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年6月16日 V 1.0
     */
    public String getAllStaffListData(String companyId);

    /**
     * @Title: 获取已经有年金的员工的信息SQL
     * @param companyId
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年6月16日 V 1.0
     */
    public String getAllStaffListDataForRe(String companyId);

    /**
     * @Title: 获取年金计算数据
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月16日 V 1.0
     */
    public Map<String, Object> getNjListData(String searchData, String companyId, Pager pager);

    /**
     * @Title: 获取汇总数据SQL
     * @param companyId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月16日 V 1.0
     */
    public String getTotalDatas(String companyId);

    /**
     * @Title: 获取公司员工月平均工资总额
     * @param companyId
     * @param year
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月16日 V 1.0
     */
    public String getLastYearAvgSalary(String companyId, int year);

}
