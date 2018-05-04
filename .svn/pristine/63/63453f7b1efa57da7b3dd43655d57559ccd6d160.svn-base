package com.lingnet.hcm.service.salary;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryAccount;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.util.Pager;

/**
 * 工资档案
 * @ClassName: SalaryRecordDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
public interface SalaryRecordService extends BaseService<SalaryRecord, String> {

    /**
     * @Title: 获取员工工资档案 
     * @param id
     * @param searchData
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月7日 V 1.0
     */
    public Map<String, Object> getStaffSalaryListData(String id, String deptId, String searchData, Pager pager);

    /**
     * @Title: 更新员工工资档案 
     * @param formdata
     * @param griddata
     * @param standardData
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月7日 V 1.0
     */
    public String saveOrUpdate(String formdata, String griddata, String standardData, int isChange, int isGridChange) throws Exception;

    /**
     * @Title: 获取某一员工工资档案信息 
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月10日 V 1.0
     */
    public Map<String, Object> getStaffSalaryData(String id, String depId, String recordId);

    /**
     * @Title: 赋值页面分类项目的url 
     * @param id
     * @return 
     * List<String[]> 
     * @author zhanghj
     * @since 2017年4月13日 V 1.0
     */
    public List<String[]> getStandardItems(String id, String recordId);

    /**
     * @Title: 获取分类薪资项目 
     * @param id
     * @param type
     * @param isDisplay 显示标准项目
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月13日 V 1.0
     */
    public List<Map<String, Object>> getSalaryStandardItems(String id, int type, int isDisplay, String recordId);

    /**
     * @Title: 获取员工收入 
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月13日 V 1.0
     */
    public Map<String, Object> getHistorySalaryRecord(String id);

    /**
     * @Title: 获取实习员工以及异动人员列表数据 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月13日 V 1.0
     */
    public Map<String, Object> getPracticeEmployeeListData(String depId, String searchData, Pager pager);

    /**
     * @Title: 获取员工基本信息
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月13日 V 1.0
     */
    public Map<String, Object> getEmployeeBaseInfo(String id);

    /**
     * @Title: 获取员工薪酬薪资组值 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月14日 V 1.0
     */
    public List<Map<String, Object>> getSalaryGroupValueListData(String id);

    /**
     * @Title: 更新员工薪酬
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年3月8日 V 1.0
     */
    public String saveOrUpdateToStaff(String formdata, String griddata, String gridData, String gridAccountData) throws Exception;

    /**
     * @Title: 获取异动薪资调整数据
     * @param depId
     * @param searchData
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月15日 V 1.0
     */
    public Map<String, Object> getAllSalaryAdjustListData(String depId, String searchData, Pager pager);

    /**
     * @Title: 获取员工计算公式 
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月17日 V 1.0
     */
    public List<Map<String, String>> getAllGroupFinancialList(String id);

    /**
     * @Title: 获取员工调整薪酬历史记录
     * @param staffId
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月17日 V 1.0
     */
    public Map<String, Object> getAllRecordHistoryListData(String staffId, Pager pager);

    /**
     * @Title: 获取需要调整工资数据 
     * @param ids
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月18日 V 1.0
     */
    public List<Map<String, Object>> getStaffForAdjustListData(String ids);

    /**
     * @Title: 调整员工薪酬 
     * @param gridData
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月18日 V 1.0
     */
    public String saveOrUpdateToStaffSalary(String gridData) throws Exception;

    /**
     * @Title: 获取员工账号信息 
     * @param id
     * @return 
     * List<SalaryAccount> 
     * @author zhanghj
     * @since 2017年4月25日 V 1.0
     */
    public List<SalaryAccount> getSalaryAccountListData(String id, String recordId);

    /**
     * @Title: 获取员工岗位等变动信息
     * @param staffId
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月5日 V 1.0
     */
    public Map<String, Object> getChangeHistoryForStaff(String staffId, Pager pager);

    /**
     * @Title: 导入调整员工薪酬固定项目值
     * @param endSuffix
     * @param uploadFile
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年7月3日 V 1.0
     */
    public String uploadFixedItemsValues(String endSuffix, File uploadFile) throws Exception;

    /**
     * @Title: 导入员工标准项目值
     * @param endSuffix
     * @param uploadFile
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年7月3日 V 1.0
     */
    public String uploadStandValues(String endSuffix, File uploadFile) throws Exception;

    /**
     * @Title: 导出调整员工薪酬固定项目值
     * @param searchData 
     * void 
     * @author zhanghj
     * @since 2017年7月4日 V 1.0
     */
    public void export(String searchData);

    /**
     * @Title: 删除档案
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月13日 V 1.0
     */
    public String remove(String ids);

}
