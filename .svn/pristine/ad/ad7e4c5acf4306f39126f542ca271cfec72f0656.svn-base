package com.lingnet.hcm.service.salary;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryBaseProcess;
import com.lingnet.util.Pager;

/**
 * 缴费基数过程表
 * @ClassName: SalaryBaseProcessService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月19日 下午4:11:07 
 *
 */
public interface SalaryBaseProcessService extends BaseService<SalaryBaseProcess, String> {

    /**
     * @Title: 缴费基数计算
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String saveOrUpdate(String formdata) throws Exception;

    /**
     * @Title: 获取计算缴费列表
     * @param companyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public Map<String, Object> getJsBaseSalaryDatas(String companyId, Pager pager);

    /**
     * @Title: 获取缴费基数过程
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
     * @Title: 增加或者更新员工缴费基数信息
     * @param id
     * @param griddata
     * @param columns
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年6月20日 V 1.0
     */
    public String saveOrUpdateToBasePerson(String id, String griddata,
            String columns) throws Exception;

    /**
     * @Title: 重新计算员工缴费基数
     * @param id
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年6月21日 V 1.0
     */
    public String updateReloadBase(String id) throws Exception;

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
    public Map<String, Object> getChoseBasePersonalListData(String id, String ids, int year,
            int month, Pager pager);

}
