package com.lingnet.hcm.service.salary;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryOrgInsurance;
import com.lingnet.util.Pager;

/**
 * 团体商业保险缴费
 * @ClassName: SalaryOrgInsuranceService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月14日 下午4:32:10 
 *
 */
public interface SalaryOrgInsuranceService extends BaseService<SalaryOrgInsurance, String> {

    /**
     * @Title: 删除
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月14日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 增加或者更新
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月14日 V 1.0
     */
    public String saveOrUpdate(String formdata);

    /**
     * @Title: 获取商业保险保费数据
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月14日 V 1.0
     */
    public Map<String, Object> getListData(String searchData, Pager pager);

    /**
     * @Title: 个人商业保险缴费保存更新
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月14日 V 1.0
     */
    public String saveOrUpdateToPer(String formdata);

    /**
     * @Title: 个人商业保险缴费删除
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月14日 V 1.0
     */
    public String removeToPer(String ids);

    /**
     * @Title: 个人商业保险缴费列表数据
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月14日 V 1.0
     */
    public Map<String, Object> getPerListData(String searchData, Pager pager);

    /**
     * @Title: 获取所有的员工
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月15日 V 1.0
     */
    public Map<String, Object> getAllPersonalListData(String searchData, Pager pager);

}
