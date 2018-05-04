package com.lingnet.hcm.service.salary;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryPart;
import com.lingnet.util.Pager;

/**
 * 工资条与薪资项目关联表
 * @ClassName: SalaryPartService 
 * @Description: 工资条与薪资项目关联表 
 * @author zhanghj
 * @date 2017年4月6日 下午4:25:44 
 *
 */
public interface SalaryPartService extends BaseService<SalaryPart, String> {

    /**
     * @Title: 获取工资条 
     * @param id
     * @param searchData
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月6日 V 1.0
     */
    public Map<String, Object> getSalaryWageListData(String id, String searchData, Pager pager);

    /**
     * @Title: 工资条增加或者更新 
     * @param formdata
     * @param griddata
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月6日 V 1.0
     */
    public String saveOrUpdate(String formdata, String griddata) throws Exception;

    /**
     * @Title: 删除工资条 
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月6日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 获取工资条对应的薪资项目 
     * @param id
     * @param searchData
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月7日 V 1.0
     */
    public Map<String, Object> getSalaryWageItemsListData(String id, String searchData);

    /**
     * @Title: 设置默认工资条 
     * @param id
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月7日 V 1.0
     */
    public String saveDefault(String id);

}
