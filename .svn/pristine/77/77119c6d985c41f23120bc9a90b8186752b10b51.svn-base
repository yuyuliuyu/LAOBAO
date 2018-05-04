package com.lingnet.hcm.action.salary;

import java.util.Map;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.service.salary.SalaryBaseProcessService;
import com.lingnet.hcm.service.salary.SalaryPaymentProcessService;
import com.lingnet.hcm.service.salary.SalaryRecordService;
import com.lingnet.util.JsonUtil;

/**
 * 社保缴费
 * @ClassName: SalaryPaymentAction 
 * @Description: 社保缴费 
 * @author zhanghj
 * @date 2017年4月26日 上午10:09:52 
 *
 */
public class SalaryPaymentAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;
    private String companyId;// 公司ID
    private Map<String, Object> mapInfo;// 前台数据
    private int year;// 计算年
    private int month;// 计算月
    private String columns;// 列集合
    private String fuli;// 福利ID
    private String staffId;// 员工ID

    @Resource(name="salaryPaymentProcessService")
    private SalaryPaymentProcessService salaryPaymentProcessService;
    @Resource(name="salaryRecordService")
    private SalaryRecordService salaryRecordService;
    @Resource(name="salaryBaseProcessService")
    private SalaryBaseProcessService salaryBaseProcessService;

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String list() {
        return "list";
    }

    /**
     * @Title: 列表增加页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String add() {
        return "add";
    }

    /**
     * @Title: 列表编辑页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String edit() {
        mapInfo = salaryPaymentProcessService.getJfProcessData(id);
        return "add";
    }

    /**
     * @Title: 员工缴费修改页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public String jfEdit() {
        mapInfo = salaryRecordService.getEmployeeBaseInfo(staffId);
        return "jfedit";
    }

    /**
     * @Title: 缴费单位页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String company() {
        return "company";
    }

    /**
     * @Title: 员工选择页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String person() {
        return "person";
    }

    /**
     * @Title: 员工选择页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String basePerson() {
        return "baseperson";
    }

    /**
     * @Title: 获取社保缴费数据 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月24日 V 1.0
     */
    public String getPayMentListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryPaymentProcessService.getPayMentListData(companyId, pager)));
    }

    /**
     * @Title: 社保缴费数据保存
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            return ajax(Status.success, salaryPaymentProcessService.saveOrUpdate(formdata));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取有社保的员工
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月10日 V 1.0
     */
    public String getPersonalToAllocationListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryPaymentProcessService.getPersonalToAllocationListData(id, ids, year, month, pager)));
    }

    /**
     * @Title: 删除社保缴费数据 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月24日 V 1.0
     */
    public String remove() {
        return ajax(Status.success, salaryPaymentProcessService.remove(ids));
    }

    /**
     * @Title: 员工保险缴费信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public String getInsuranceItemsListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryPaymentProcessService.getInsuranceItemsListData(id, fuli, searchData, pager)));
    }

    /**
     * @Title: 获取可以添加的员工保险缴费信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月19日 V 1.0
     */
    public String getCanJoinStaffsListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryPaymentProcessService.getCanJoinStaffsListData(ids, year, month)));
    }

    /**
     * @Title: 增加或者更新员工缴费信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月25日 V 1.0
     */
    public String saveOrUpdateToPerson() {
        try {
            return ajax(Status.success, salaryPaymentProcessService.saveOrUpdateToPerson(id, griddata, columns));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 重新公司员工缴费信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月25日 V 1.0
     */
    public String updateReloadCalculation() {
        return ajax(Status.success, JsonUtil.Encode(salaryPaymentProcessService.updateReloadCalculation(id)));
    }

    /**
     * @Title: 获取个人员工缴费信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public String getStaffPaymentData() {
        return ajax(Status.success, JsonUtil.Encode(salaryPaymentProcessService.getStaffPaymentData(id, staffId)));
    }

    /**
     * @Title: 保存月份缴费信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public String saveOrUpdateToPayment() {
        try {
            return ajax(Status.success, salaryPaymentProcessService.saveOrUpdateToPayment(griddata));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取员工缴费历史
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月3日 V 1.0
     */
    public String getStaffPaymentHistory() {
        return ajax(Status.success, JsonUtil.Encode(salaryPaymentProcessService.getStaffPaymentHistory(staffId, year, pager)));
    }

    /**
     * @Title: 缴费基数计算
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdateToBase() {
        try {
            return ajax(Status.success, salaryBaseProcessService.saveOrUpdate(formdata));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取计算缴费基数列表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getJsBaseSalaryDatas() {
        return ajax(Status.success, JsonUtil.Encode(salaryBaseProcessService.getJsBaseSalaryDatas(companyId, pager)));
    }

    /**
     * @Title: 获取员工缴费基数列表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getJsBaseSalaryListDatas() {
        return ajax(Status.success, JsonUtil.Encode(salaryBaseProcessService.getJsBaseSalaryListDatas(id, fuli, searchData, pager)));
    }

    /**
     * @Title: 获取有社保的员工
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年4月10日 V 1.0
     */
    public String getChoseBasePersonalListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryBaseProcessService.getChoseBasePersonalListData(id, ids, year, month, pager)));
    }

    /**
     * @Title: 增加或者更新员工缴费基数信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月20日 V 1.0
     */
    public String saveOrUpdateToBasePerson() {
        try {
            return ajax(Status.success, salaryBaseProcessService.saveOrUpdateToBasePerson(id, griddata, columns));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 重新计算员工缴费基数
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月21日 V 1.0
     */
    public String updateReloadBase() {
        try {
            return ajax(Status.success, salaryBaseProcessService.updateReloadBase(id));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    public Map<String, Object> getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(Map<String, Object> mapInfo) {
        this.mapInfo = mapInfo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getFuli() {
        return fuli;
    }

    public void setFuli(String fuli) {
        this.fuli = fuli;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

}
