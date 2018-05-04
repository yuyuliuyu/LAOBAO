package com.lingnet.hcm.dao.salary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryItems;
import com.lingnet.util.Pager;

/**
 * 薪资项目
 * @ClassName: SalaryItemsDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
public interface SalaryItemsDao extends BaseDao<SalaryItems, String>{

    /**
     * @Title: 获取薪酬类别 
     * @param depId
     * @return 
     * HashMap<String,Object> 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String getItemsTypeListData(String depId);

    /**
     * @Title: 薪资项目对应的薪资类别 
     * @param id
     * @return 
     * List<HashMap<String,Object>> 
     * @author zhanghj
     * @since 2017年3月28日 V 1.0
     */
    public List<HashMap<String, Object>> getSalaryItemAndTypeListData(String id);

    /**
     * @Title: 获取薪资类别下的薪资项目 
     * @param id
     * @return 
     * List<HashMap<String,Object>> 
     * @author zhanghj
     * @since 2017年4月6日 V 1.0
     */
    public Map<String, Object> getSalaryItemListData(String depId, String id,
            String searchData, Pager pager);

    /**
     * @Title: 获取需要分配薪资组的个人信息 
     * @param ids
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月10日 V 1.0
     */
    public List<Map<String, Object>> getPersonalToAllocationListData(String ids, String depId, String recordId);

    /**
     * @Title: 获取个人薪资组 
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月11日 V 1.0
     */
    public String getPersonalGroupListData(String ids, String recordId);

    /**
     * @Title: 获取员工已分配薪资组对应的薪资组工资套中的固定薪酬项目 
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月14日 V 1.0
     */
    public List<Map<String, Object>> getGuDingItems(String id);

    /**
     * @Title: 获取此薪资组工资套中的薪资项目
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月15日 V 1.0
     */
    public List<Map<String, Object>> getSalaryGroupItems(String id);

    /**
     * @Title: 获取有权限的薪资项目和集团统一管控的数据
     * @param depId
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月21日 V 1.0
     */
    public Map<String, Object> getSalaryItemGKListData(String depId,
            String searchData, Pager pager);

    /**
     * @Title: 获取有权限的薪资项目和集团统一管控的数据SQL
     * @param depId
     * @param searchData
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月21日 V 1.0
     */
    public String getSalaryItemGKListDataSql(String depId, String searchData);

    /**
     * @Title: 根据项目类型获取不同的薪资项目
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年6月22日 V 1.0
     */
    public List<Map<String, Object>> getSalaryItemToItemTypeData(String companyId);

    /**
     * @Title: 获取薪资组关联的薪资项目SQL
     * @param salaryGroupId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月4日 V 1.0
     */
    public String getGroupToItems(String salaryGroupId);

    /**
     * @Title: 根据类型获取不同的薪资项目
     * @param depId
     * @param pid
     * @param column
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月7日 V 1.0
     */
    public List<Map<String, Object>> getSalaryItemToTypeData(String depId, Object pid,
            String column);

    /**
     * @Title: 获取个人工资单
     * @param userName
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月19日 V 1.0
     */
    public Map<String, Object> getListData(String userName, String searchData,
            Pager pager);

    /**
     * @Title: 获取部门工资单
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月22日 V 1.0
     */
    public Map<String, Object> getDeptListData(String searchData, Pager pager);
}
