package com.lingnet.hcm.service.salary;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryWage;
import com.lingnet.util.Pager;

/**
 * 工资套
 * @ClassName: SalaryWageService 
 * @Description: 工资套
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
public interface SalaryWageService extends BaseService<SalaryWage, String> {

    /**
     * @Title: 增加或者更新工资套 
     * @param formdata
     * @param gridData
     * @return
     * @throws Exception 
     * String 
     * @author zhanghj
     * @since 2017年3月29日 V 1.0
     */
    public String saveOrUpdate(String formdata, String gridData) throws Exception;

    /**
     * @Title: 删除工资套  
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月29日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 获取工资套 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月29日 V 1.0
     */
    public Map<String, Object> getSalaryWageListData(String companyId, String searchData, Pager pager);

    /**
     * @Title: 获取工资套与薪资项目关联数据 
     * @param id
     * @return 
     * List<SalaryWageAndType> 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public Map<String, Object> getWageSalaryListData(String id);

    /**
     * @Title: 工资套是否被薪资组工资套引用，薪资组工资套是否在工资分配中
     * @param salaryWageId
     * @param companyId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月12日 V 1.0
     */
    public String checkWageStatus(String salaryWageId);

    /**
     * @Title: 获取生效日期后的工资套
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月13日 V 1.0
     */
    public Map<String, Object> getSalaryWageEffectListData(Pager pager);

    /**
     * @Title: 获取选中公司以及上一级公司
     * @param companyId
     * @param userId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月4日 V 1.0
     */
    public String getItemsCompanys(String companyId, String userId);

}
