package com.lingnet.hcm.dao.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryGroupWage;
import com.lingnet.util.Pager;

/**
 * 薪资组工资套
 * @ClassName: SalaryGroupWageDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
public interface SalaryGroupWageDao extends BaseDao<SalaryGroupWage, String>{

    /**
     * @Title: 获取薪资组工资套  
     * @param id
     * @param searchData
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月6日 V 1.0
     */
    public Map<String, Object> getSalaryGroupWageListData(String id,
            String searchData, Pager pager);

    /**
     * @Title: 获取薪资组工资套与薪资项目关联数据  
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月6日 V 1.0
     */
    public Map<String, Object> getGroupWageTypeListData(String id, int sign);

    /**
     * @Title: 根据薪资组获取对应的工资套
     * @param companyId
     * @param groupId
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public List<Map<String, Object>> getGroupWageListData(String companyId,
            String groupId);

    /**
     * @Title: 获取值为0时不发送工资条的薪资项目
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年6月10日 V 1.0
     */
    public List<Map<String, Object>> getValueIsZeroNoSendRecords(String id);

    /**
     * @Title: 获取薪资组 
     * @param companyId
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月22日 V 1.0
     */
    public Map<String, Object> getSalaryGroupListData(String companyId, String searchData, Pager pager);

    /**
     * @Title: 自定义SQL查询
     * @param sql
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月29日 V 1.0
     */
    public Map<String, Object> getCustomizeListData(String sql);

    /**
     * @Title: 获取工资套公式
     * @param id
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月18日 V 1.0
     */
    public String getDeployFormulaSql(String id);

    }
