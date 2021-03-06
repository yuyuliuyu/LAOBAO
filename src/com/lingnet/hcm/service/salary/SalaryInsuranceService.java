package com.lingnet.hcm.service.salary;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.SalaryInsurance;
import com.lingnet.util.Pager;

/**
 * 缴费标准表
 * @ClassName: SalaryInsuranceService 
 * @Description: 缴费标准表 
 * @author zhanghj
 * @date 2017年4月19日 上午10:27:10 
 *
 */
public interface SalaryInsuranceService extends BaseService<SalaryInsurance, String> {

    /**
     * @Title: 获取缴纳标准列表数据 
     * @param depId
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public Map<String, Object> getPaymentInsuranceListData(String depId, String searchData,
            Pager pager);

    /**
     * @Title: 缴纳标准保存 
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public String saveOrUpdate(String formdata);

    /**
     * @Title: 删除缴纳标准 
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 获取参保人员信息 
     * @param depId
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public Map<String, Object> getInsuranceItemsListData(String depId, String searchData, Pager pager);

    /**
     * @Title: 参保人员信息绑定保险信息 
     * @param formdata
     * @param griddata
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月19日 V 1.0
     */
    public String saveOrUpdateToInsurance(String formdata, String griddata) throws Exception;

    /**
     * @Title: 查询无参保人员 
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public Map<String, Object> getNoInsuranceOfStaffListData(String depId, String searchData, Pager pager);

    /**
     * @Title: 查询已选择的无参保人员
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public List<Map<String, Object>> getNoChoseStaffListData(String ids, String recordMainId);

    /**
     * @Title: 获取参保项目  
     * @param id 公司ID
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月20日 V 1.0
     */
    public List<Map<String, Object>> getNeedJoinInsuranceItemsData(String ids, String searchData, String recordMainId);

    /**
     * @Title:  获取选中的员工的福利项目 
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月21日 V 1.0
     */
    public List<Map<String, Object>> getChoseStaffInsurances(String id);

    /**
     * @Title: 停保处理 
     * @param id
     * @param griddata
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月21日 V 1.0
     */
    public String updateStopInsurance(String id, String griddata) throws Exception;

    /**
     * @Title: 获取员工信息 
     * @param id
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年4月24日 V 1.0
     */
    public Map<String, Object> getEmployeeBaseInfo(String id);

    /**
     * @Title: 获取员工保险信息 
     * @param id
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年4月24日 V 1.0
     */
    public List<Map<String, Object>> getStaffCanBaoInfo(String id);

    /**
     * @Title: 设置缴费基数 
     * @param id
     * @param griddata
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年4月24日 V 1.0
     */
    public String updateToJfJs(String id, String griddata) throws Exception;

    /**
     * @Title: 按照不同的进位规则对值进行操作
     * @param df
     * @param rule 进位规则
     * @param xs 保留位数
     * @param preValue 原始值
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月9日 V 1.0
     */
    public String ceilMath(DecimalFormat df, int rule, String xs, Double preValue);

    /**
     * @Title: 获取工资报表
     * @param searchData
     * @param pager
     * @return 
     * Object 
     * @author zhanghj
     * @since 2017年6月2日 V 1.0
     */
    public Map<String, Object> getSlaryReportListData(String searchData, Pager pager);

    /**
     * @Title: 获取报盘文件
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月3日 V 1.0
     */
    public Map<String, Object> getOffBankListData(String searchData, Pager pager);

    /**
     * @Title: 导出工资列表
     * @param searchData 
     * void 
     * @author zhanghj
     * @since 2017年6月4日 V 1.0
     */
    public void export(String searchData);

    /**
     * @Title: 导出银行报盘
     * @param searchData 
     * void 
     * @author zhanghj
     * @since 2017年6月6日 V 1.0
     */
    public void exportForBank(String searchData);

    /**
     * @Title: 获取员工保险缴费情况
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月6日 V 1.0
     */
    public Map<String, Object> getInsuranceStaffListData(String searchData, Pager pager);

    /**
     * @Title: 获取当前公司或者部门中所有人员选择的福利项目
     * @param depId
     * @param effectiveYear
     * @param effectiveMonth
     * @return 
     * List<Map<String,Object>> 
     * @author zhanghj
     * @since 2017年6月6日 V 1.0
     */
    public List<Map<String, Object>> getCurCompanyInsurance(String depId, String effectiveYear,
            String effectiveMonth);

    /**
     * @Title: 批量核对
     * @param formdata
     * @param uploadFile
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年6月6日 V 1.0
     */
    public String beachCheck(String formdata, String endSuffix, File uploadFile) throws Exception;

    /**
     * @Title: 获取已经核对的保险信息
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月7日 V 1.0
     */
    public Map<String, Object> getInsuranceCheckListData(String companyId, String searchData, Pager pager);

    /**
     * @Title: 更新员工缴费金额
     * @param griddata
     * @param column
     * @return 
     * String 
     * @author zhanghj
     * @throws Exception 
     * @since 2017年6月26日 V 1.0
     */
    public String updateStaffBaseInsurance(String griddata, String column) throws Exception;

    /**
     * @Title: 根据计算出的缴费基数更新员工社保缴费
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月20日 V 1.0
     */
    public String updateInsurance(String formdata);

}
