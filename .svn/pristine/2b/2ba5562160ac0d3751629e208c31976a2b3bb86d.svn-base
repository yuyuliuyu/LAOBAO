package com.lingnet.hcm.dao.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryPaymentProcess;
import com.lingnet.util.Pager;

/**
 * 保险缴费
 * @ClassName: SalaryPaymentProcessDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年4月26日 上午11:42:00 
 *
 */
public interface SalaryPaymentProcessDao extends BaseDao<SalaryPaymentProcess, String>{

    /**
     * @Title: 获取社保缴费数据 
     * @param id
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月26日 V 1.0
     */
    public Map<String, Object> getPayMentListData(String id, Pager pager);

    /**
     * @Title: 获取缴费过程数据 
     * @param id
     * @return 
     * Map<String,String> 
     * @author zhanghj
     * @since 2017年4月26日 V 1.0
     */
    public Map<String, Object> getJfProcessData(String id);

    /**
     * @Title: 员工保险缴费信息 
     * @param id
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月27日 V 1.0
     */
    public Map<String, Object> getInsuranceItemsListData(String id, String fuli,
            String searchData, Pager pager);

    /**
     * @Title: 获取有社保的员工 
     * @param id 缴费过程ID
     * @param ids
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月27日 V 1.0
     */
    public Map<String, Object> getPersonalToAllocationListData(String id, String ids, int year, int month, Pager pager);

    /**
     * @Title: 获取缴社保的人员SQL(在停保之前)
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月27日 V 1.0
     */
    public String getCanSbPeoples(String depts, String staffIds, int year, int month, String searchdata);

    /**
     * @Title: 获取可以添加的员工保险缴费信息
     * @param ids
     * @param year
     * @param month
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月28日 V 1.0
     */
    public List<Map<String, Object>> getCanJoinStaffsListData(String ids,
            int year, int month);

    /**
     * @Title: 获取已经保存的缴社保的人员SQL
     * @param id
     * @param searchData
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月28日 V 1.0
     */
    public String getAllStaffJnData(String id, String searchData);

    /**
     * @Title: 获取是否存在相同的组织以及相同的地域
     * @param org
     * @param area
     * @param year
     * @param month
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月2日 V 1.0
     */
    public String getSampleOrgAndAreaData(String id, String org, String area, int year, int month);

    /**
     * @Title: 获取个人员工缴费信息
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public List<Map<String, Object>> getStaffPaymentData(String id, String staffId);

    /**
     * @Title: 获取员工缴费历史
     * @param staffId
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public Map<String, Object> getStaffPaymentHistory(String staffId, int year, Pager pager);

    /**
     * @Title: 获取所有员工缴费信息
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public List<Map<String, Object>> getAllStaffPaymentsData(String id);

    /**
     * @Title: 获取福利项目对应的保留小数规则
     * @param string
     * @return 
     * Map<String,String> 
     * @author zhanghj
     * @since 2017年5月11日 V 1.0
     */
    public Map<String, Object> getPaymentceilMathData(String string);

}
