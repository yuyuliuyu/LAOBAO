package com.lingnet.hcm.service.salary;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryPayroll;
import com.lingnet.util.Pager;

/**
 * 工资单可查看月份表
 * @ClassName: SalaryPayrollService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年7月21日 上午9:10:01 
 *
 */
public interface SalaryPayrollService extends BaseService<SalaryPayroll, String> {

    /**
     * @Title: 可查看月份控制增加、更新
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月21日 V 1.0
     */
    public String saveOrUpdate(String formdata);

    /**
     * @Title: 可查看月份控制删除
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月21日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 获取可控制的员工月份信息
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月21日 V 1.0
     */
    public Map<String, Object> getPayRollsData(String searchData, Pager pager);

    /**
     * @Title: 获取工资单设置信息
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月22日 V 1.0
     */
    public Map<String, Object> getSalaryPayRollData(String id);

}
