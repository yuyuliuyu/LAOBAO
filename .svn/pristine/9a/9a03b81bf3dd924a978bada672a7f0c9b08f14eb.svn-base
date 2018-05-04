package com.lingnet.hcm.service.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryPaymentProcess;
import com.lingnet.util.Pager;

/**
 * 保险缴费过程表
 * @ClassName: SalaryPaymentProcessService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年4月26日 上午11:03:21 
 *
 */
public interface SalaryPaymentProcessService extends BaseService<SalaryPaymentProcess, String> {

    /**
     * @Title: 获取社保缴费数据 
     * @param companyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月26日 V 1.0
     */
    public Map<String, Object> getPayMentListData(String companyId, Pager pager);

    /**
     * @Title: 保存或者更新社保缴费 
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月26日 V 1.0
     */
    public String saveOrUpdate(String formdata) throws Exception;

    /**
     * @Title: 删除社保缴费数据 
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月26日 V 1.0
     */
    public String remove(String ids);

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
    public Map<String, Object> getInsuranceItemsListData(String id, String fuli, String searchData,
            Pager pager);

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
     * @Title: 获取可以添加的员工保险缴费信息 
     * @param ids 员工ID
     * @param year 计算年
     * @param month 计算月
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月28日 V 1.0
     */
    public List<Map<String, Object>> getCanJoinStaffsListData(String ids, int year, int month);

    /**
     * @Title: 增加或者更新员工缴费信息
     * @param id
     * @param griddata
     * @param columns 列集合
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月28日 V 1.0
     */
    public String saveOrUpdateToPerson(String id, String griddata, String columns) throws Exception;

    /**
     * @Title: 重新公司员工缴费信息
     * @param id
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月28日 V 1.0
     */
    public String updateReloadCalculation(String id);

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
     * @Title: 保存月份缴费信息
     * @param griddata
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年5月3日 V 1.0
     */
    public String saveOrUpdateToPayment(String griddata) throws Exception;

    /**
     * @Title: 获取员工缴费历史
     * @param staffId
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public Map<String, Object> getStaffPaymentHistory(String staffId, int year, Pager pager);

}
