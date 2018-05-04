package com.lingnet.hcm.dao.salary;

import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryWage;
import com.lingnet.util.Pager;

/**
 * 工资套
 * @ClassName: SalaryWageDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
public interface SalaryWageDao extends BaseDao<SalaryWage, String>{

    /**
     * @Title: 获取工资套
     * @param wageId
     * @return 
     * HashMap<String,Object> 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public Map<String, Object> getWageTypeListData(String wageId);

    /**
     * @Title: 获取生效日期后的工资套
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月22日 V 1.0
     */
    public Map<String, Object> getSalaryWageEffectListData(Pager pager);

}
