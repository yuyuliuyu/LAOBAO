package com.lingnet.hcm.service.salary;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryAgain;
import com.lingnet.util.Pager;

/**
 * 二次分配总量主表
 * @ClassName: SalaryAgainService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月17日 上午8:12:45 
 *
 */
public interface SalaryAgainService extends BaseService<SalaryAgain, String> {

    /**
     * @Title: 获取员工二次分配
     * @param period
     * @param companyId
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月21日 V 1.0
     */
    public Map<String, Object> getAllSecondAmountListData(String period, String companyId, Pager pager);

    /**
     * @Title: 获取当前公司薪酬期间二次分配总量数据
     * @param searchData
     * @param companyId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年5月22日 V 1.0
     */
    public Map<String, Object> getPeriodAmountListData(String searchData, String companyId, Pager pager);

    /**
     * @Title: 获取所有员工的薪资组
     * @param companyId
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年5月22日 V 1.0
     */
    public List<Map<String, Object>> getAllStaffSalaryGroup(String companyId);

    /**
     * @Title: 二次分配保存更新
     * @param companyId
     * @param deptId
     * @param period
     * @param griddata
     * @param columns
     * @param salaryNames
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年5月22日 V 1.0
     */
    public String saveOrUpdate(String companyId, String deptId, String period, String fpqx, String griddata, String columns, String salaryNames) throws Exception;

    /**
     * @Title: 总量上报
     * @param companyId
     * @param period
     * @param id
     * @param fpqx
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年5月22日 V 1.0
     */
    public String updateSetReportStatus(String companyId, String period, String id, String fpqx) throws Exception;

    /**
     * @Title: 取消总量上报
     * @param companyId
     * @param period
     * @param fpqx
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月25日 V 1.0
     */
    public String updateSetCancelReportStatus(String companyId, String period, String fpqx);

    /**
     * @Title: 判断分配过程是否已提交或者通过之后的操作
     * @param companyId
     * @param salaryPeriod
     * @return 
     * Boolean 
     * @author zhanghj
     * @since 2017年6月3日 V 1.0
     */
    public Boolean isCanReport(String companyId, String salaryPeriod);

    /**
     * @Title: 获取二次分配数据SQL
     * @param period
     * @param companyId
     * @param searchData
     * @return 
     * List<?> 
     * @author zhanghj
     * @since 2017年6月26日 V 1.0
     */
    public List<?> getAllSecondForTypeSql(String period, String companyId,
            String searchData);

    /**
     * @Title: 导入二次分配数据
     * @param endSuffix
     * @param uploadFile
     * @param period
     * @param companyId
     * @param depId
     * @param fpqx 分配权限
     * @param searchData
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年7月12日 V 1.0
     */
    public String uploadSecondItems(String endSuffix, File uploadFile,
            String period, String companyId, String depId, String fpqx, String searchData) throws Exception;

    /**
     * @Title: 导出二次分配数据
     * @param period
     * @param depId
     * @param searchData 
     * void 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public void export(String period, String depId, String searchData);

}
