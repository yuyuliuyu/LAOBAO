package com.lingnet.hcm.dao.salary;

import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryPart;

/**
 * 工资条
 * @ClassName: SalaryPartDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
public interface SalaryPartDao extends BaseDao<SalaryPart, String>{

    /**
     * @Title: 获取工资条对应的薪资项目
     * @param wageId
     * @return 
     * HashMap<String,Object> 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public Map<String, Object> getWageTypeListData(String wageId, String searchData);

}
