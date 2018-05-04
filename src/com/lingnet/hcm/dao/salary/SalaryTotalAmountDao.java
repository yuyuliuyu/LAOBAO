package com.lingnet.hcm.dao.salary;

import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryTotalAmount;
import com.lingnet.util.Pager;

/**
 * 总量
 * @ClassName: SalaryTotalAmountDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月19日 上午7:56:36 
 *
 */
public interface SalaryTotalAmountDao extends BaseDao<SalaryTotalAmount, String>{

    /**
     * @Title: 获取总量数据
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月19日 V 1.0
     */
    public Map<String, Object> getAmountListData(String companyId, String searchData, Pager pager);

    /**
     * @Title: 获取薪酬期间总量数据
     * @param period
     * @param companyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月19日 V 1.0
     */
    public Map<String, Object> getAmountForPeriodListData(String period, String companyId,
            Pager pager);

}
