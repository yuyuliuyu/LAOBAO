package com.lingnet.hcm.dao.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.salary.SalaryBaseProcess;
import com.lingnet.util.Pager;

/**
 * 缴费基数过程表
 * @ClassName: SalaryBaseProcessDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月19日 下午4:22:58 
 *
 */
public interface SalaryBaseProcessDao extends BaseDao<SalaryBaseProcess, String>{

    /**
     * @Title: 获取是否存在相同的组织以及相同的地域
     * @param id
     * @param org
     * @param area
     * @param year
     * @param month
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getSampleOrgAndAreaData(String id, String org, String area,
            int year, int month);

    /**
     * @Title: 获取所有参与计算的员工
     * @param depts
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getCanSbPeoples(String depts, String areaId, String fuli);

    /**
     * @Title: 获取计算缴费基数
     * @param companyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public Map<String, Object> getJsBaseSalaryDatas(String companyId,
            Pager pager);

    /**
     * @Title: 获取缴费基数过程数据
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public Map<String, Object> getBaseJfProcessData(String id);

    /**
     * @Title: 获取员工缴费基数列表
     * @param id
     * @param fuli
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public Map<String, Object> getJsBaseSalaryListDatas(String id, String fuli,
            String searchData, Pager pager);

    /**
     * @Title: 获取缴费基数过程下的员工SQL
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年6月21日 V 1.0
     */
    public String getAllStaffBasesDataSql(String id);

    /**
     * @Title: 获取有社保的员工
     * @param id
     * @param ids
     * @param year
     * @param month
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月21日 V 1.0
     */
    public Map<String, Object> getChoseBasePersonalListData(String id,
            String ids, int year, int month, Pager pager);

}
