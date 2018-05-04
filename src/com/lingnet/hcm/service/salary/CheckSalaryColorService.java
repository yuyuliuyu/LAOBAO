package com.lingnet.hcm.service.salary;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.CheckSalaryColor;
import com.lingnet.util.Pager;

/**
 * 工资分配核对色值配置表
 * @ClassName: CheckSalaryColorService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月28日 下午5:19:14 
 *
 */
public interface CheckSalaryColorService extends BaseService<CheckSalaryColor, String> {

    /**
     * @Title: 色值保存更新
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月28日 V 1.0
     */
    public String saveOrUpdate(String formdata);

    /**
     * @Title: 色值删除
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月28日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 获取全部色值
     * @param compamyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月28日 V 1.0
     */
    public Map<String, Object> getListData(String compamyId, Pager pager);

}
