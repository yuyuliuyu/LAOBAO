package com.lingnet.hcm.action.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.WorkFlowPrent;
import com.lingnet.hcm.entity.salary.Perioddata;
import com.lingnet.hcm.entity.salary.SalaryAssignation;
import com.lingnet.hcm.entity.salary.SalaryGroupWage;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;

/**
 * 审批
 * @ClassName: SalaryApprovalAction 
 * @Description: 审批 
 * @author zhanghj
 * @date 2017年5月27日 下午1:44:15 
 *
 */
public class SalaryApprovalAction extends BaseAction {

    private static final long serialVersionUID = 3560893634944218724L;

    private String salaryPeriodText;// 薪酬期间文本
    private String salaryWageName;// 工资套名称
    private String companyId;// 公司ID
    private String assignStaffId;// 薪酬核算数据主表ID
    private String assignId;// 薪酬核算数据薪资中间表ID
    private String columns;// 薪资列集合
    private String formula;// 公式
    private String itemID;// 薪资ID
    private String selectData;// 选择的员工数据
    private int isIgnore;// 是否忽略未提交或者未通过的二次分配项目
    private String defaultPrinter;// 默认打印机
    private String authEdit;// 权限修改
    private SalaryAssignation salaryAssignation;
    private String conditionData;// 查询条件
    private String periods;// 薪酬期间

    @Resource(name="salaryAssignationService")
    private SalaryAssignationService salaryAssignationService;

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
     * @Title: 每月薪酬总额上报展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String allocation() {
        return "allocation";
    }

    /**
     * @Title: 工资分配过程
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String process() {
        // 获取登录用户所在公司
        companyId = LingUtil.userinfo().getCid();

        return "process";
    }

    /**
     * @Title: 增加薪酬分配过程
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String processadd() {
        return "processadd";
    }

    /**
     * @Title: 编辑薪酬分配过程
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String processEdit() {
        salaryAssignation = getBeanByCriteria(SalaryAssignation.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (salaryAssignation != null) {
            companyId = salaryAssignation.getCompanyId();
            Perioddata perioddata = getBeanByCriteria(Perioddata.class, Restrictions.eq("id", salaryAssignation.getSalaryPeriod()));
            if (perioddata != null) {
                salaryPeriodText = perioddata.getName();
                SalaryGroupWage groupWage = getBeanByCriteria(SalaryGroupWage.class, Restrictions.eq("id", salaryAssignation.getSalaryGroupId()));
                if (groupWage != null) {
                    salaryWageName = groupWage.getName();
                }
            }
        }
        return "processadd";
    }

    /**
     * @Title: 薪酬区间选择
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String payPeriodSelector() {
        return "payPeriodSelector";
    }

    /**
     * @Title: 薪酬核算数据展现页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String processData() {
        salaryAssignation = getBeanByCriteria(SalaryAssignation.class, Restrictions.eq("id", id));
        if (null != salaryAssignation) {
            if (salaryAssignation.getIsSp() == 3) {
                WorkFlowPrent flowPrent = getBeanByCriteria(WorkFlowPrent.class,
                        Restrictions.eq("id", "402881945bec28ab015bec383aee0011"),
                        Restrictions.eq("state", 1));
                if (flowPrent != null) {
                    WorkFlowChild child = getBeanByCriteria(WorkFlowChild.class,
                            Restrictions.eq("pid", flowPrent.getId()),
                            Restrictions.eq("appid", LingUtil.userName()),
                            Restrictions.eq("isCheckEdit", 1));
                    if (child != null) {
                        authEdit = "1";
                    }
                }
            }
            companyId = salaryAssignation.getCompanyId();
        }

        return "processdata";
    }

    /**
     * @Title: 增加核算员工展示页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月13日 V 1.0
     */
    public String staff() {
        return "staff";
    }

    /**
     * @Title: 工资表打印预览页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String printSalary() {
        salaryAssignation = getBeanByCriteria(SalaryAssignation.class, Restrictions.eq("id", id));
        if (salaryAssignation != null) {
            Perioddata perioddata = getBeanByCriteria(Perioddata.class, Restrictions.eq("id", salaryAssignation.getSalaryPeriod()));
            if (perioddata != null) {
                salaryPeriodText = perioddata.getName();
            }
        }

        // 领薪部门
        Branch branch = getBeanByCriteria(Branch.class, Restrictions.eq("id", LingUtil.userinfo().getCid()));
        if (branch != null) {
            data = branch.getFzx();
        }

        // 配置的打印机
        String salaryRecord = ToolUtil.getPropert("printer.properties", "GZD");
        if (StringUtils.isBlank(salaryRecord)) {
            // 查找默认打印机
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
            defaultPrinter = ps.getName();
        } else defaultPrinter = salaryRecord;

        return "printsalary";
    }

    /**
     * @Title: 薪酬核算数据修改页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String adjust() {
        return "adjust";
    }

    /**
     * @Title: 工资审批发放
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String check() {
        // 获取登录用户所在公司
        companyId = LingUtil.userinfo().getCid();

        return "check";
    }

    /**
     * @Title: 审核展示页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String checkData() {
        return "checkdata";
    }

    /**
     * @Title: 审核意见
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String audit() {
        return "audit";
    }

    /**
     * @Title: 公式选择
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月12日 V 1.0
     */
    public String financial() {
        return "financial";
    }

    /**
     * @Title: 薪酬期间对比展示页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String period() {
        return "period";
    }

    /**
     * @Title: 核对的薪酬期间
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月26日 V 1.0
     */
    public String checkwin() {
        return "checkwin";
    }

    /**
     * @Title: 保存或者更新分配过程
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            return ajax(Status.success, salaryAssignationService.saveOrUpdate(formdata));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 保存选择的员工到核算过程
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月13日 V 1.0
     */
    public String saveSelectStaffData() {
        try {
            return ajax(Status.success, salaryAssignationService.saveSelectStaffData(id, selectData));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取分配过程
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String getSalaryAssignationListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getSalaryAssignationListData(companyId, searchData, pager)));
    }

    /**
     * @Title: 删除分配过程
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String remove() {
        try {
            return ajax(Status.success, salaryAssignationService.remove(ids));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 删除分配核算数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String removeForAssign() {
        try {
            return ajax(Status.success, salaryAssignationService.removeForAssign(ids));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取已经分配的员工薪资项目对比数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String getCompareAssignationForStaffData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getCompareAssignationForStaffData(id, ids, searchData, conditionData)));
    }

    /**
     * @Title: 重新初始化员工发放数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String updateAssignationForStaffData() {
        try {
            return ajax(Status.success, salaryAssignationService.updateAssignationForStaffData(id));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 重新计算
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String updateReCalculateStaffsItem() {
        return ajax(Status.success, salaryAssignationService.updateReCalculateStaffsItem(assignStaffId, assignId, id));
    }

    /**
     * @Title: 更新已经选中的员工薪资项目值
     * void 
     * @author zhanghj
     * @since 2017年5月11日 V 1.0
     */
    public String updateSelectStaffSalaryData() {
        try {
            return ajax(Status.success, salaryAssignationService.updateSelectStaffSalaryData(griddata, columns));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取薪资组工资套中薪资项目关联的公式
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月12日 V 1.0
     */
    public String getAllGroupWageFinancialList() {
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getAllGroupWageFinancialList(assignStaffId, id)));
    }

    /**
     * @Title: 根据默认公式计算薪资项目的值
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String updateSelectReCalculateStaffsItem() {
        return ajax(Status.success, salaryAssignationService.updateSelectReCalculateStaffsItem(ids, itemID, formula));
    }

    /**
     * @Title: 提交审批
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String commitCheck() {
        return ajax(Status.success, salaryAssignationService.commitCheck(id));
    }

    /**
     * @Title: 批量提交审批
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String batchCommitCheck() {
        return ajax(Status.success, salaryAssignationService.batchCommitCheck(ids, isIgnore, formdata));
    }

    /**
     * @Title: 导出表格
     * void 
     * @author zhanghj
     * @since 2017年5月13日 V 1.0
     */
    public void export() {
        salaryAssignationService.export(id, searchData);
    }

    /**
     * @Title: 获取需要核算的员工数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月13日 V 1.0
     */
    public String getNeedCheckStaffData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getNeedCheckStaffData(id, pager)));
    }

    /**
     * @Title: 获取打印机列表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月15日 V 1.0
     */
    public String getPrinterList() {
        PrintService[] psZebra = PrintServiceLookup.lookupPrintServices(null, null);
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        if (psZebra != null) {
            for (int i = 0; i < psZebra.length; i++) {
                Map<String, String> printer = new HashMap<String, String>();
                printer.put("id", psZebra[i].getName());
                printer.put("name", psZebra[i].getName());
                list.add(printer);
            }
        }

        return ajax(Status.success, JsonUtil.Encode(list));
    }

    /**
     * @Title: 发放数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月15日 V 1.0
     */
    public String updateReleaseData() {
        return ajax(Status.success, salaryAssignationService.updateReleaseData(ids));
    }

    /**
     * @Title: 取消发放数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月15日 V 1.0
     */
    public String updateNoReleaseData() {
        return ajax(Status.success, salaryAssignationService.updateNoReleaseData(ids));
    }

    /**
     * @Title: 根据权限获取不同的审核数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月25日 V 1.0
     */
    public String getSalaryAssignationForAuthListData() {
        String userName = LingUtil.userName();
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getSalaryAssignationForAuthListData(userName, searchData, pager)));
    }

    /**
     * @Title: 获取审核流程数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月26日 V 1.0
     */
    public String getCheckListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getCheckListData(id, pager)));
    }

    /**
     * @Title: 判断选择的维护过程是否有权限操作
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月26日 V 1.0
     */
    public String isCheckAUth() {
        String userName = LingUtil.userName();
        return ajax(Status.success, salaryAssignationService.isCheckAUth(ids, userName));
    }

    /**
     * @Title: 与多个薪酬区间核对
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月28日 V 1.0
     */
    public String checkPeriodData() {
        return ajax(Status.success, salaryAssignationService.checkPeriodData(id, conditionData, periods));
    }

    /**
     * @Title: 导出审批数据 
     * void 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public void exportCheckData() {
        salaryAssignationService.exportCheckData(id, searchData, conditionData);
    }

    /**
     * @Title: 获取当前公司下部门
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String getCurComDepts() {
        SalaryAssignation agin = getBeanByCriteria(SalaryAssignation.class,
                Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (agin != null) companyId = agin.getCompanyId();
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getCurComDepts(companyId)));
    }

    public SalaryAssignation getSalaryAssignation() {
        return salaryAssignation;
    }

    public void setSalaryAssignation(SalaryAssignation salaryAssignation) {
        this.salaryAssignation = salaryAssignation;
    }

    public String getSalaryPeriodText() {
        return salaryPeriodText;
    }

    public void setSalaryPeriodText(String salaryPeriodText) {
        this.salaryPeriodText = salaryPeriodText;
    }

    public String getSalaryWageName() {
        return salaryWageName;
    }

    public void setSalaryWageName(String salaryWageName) {
        this.salaryWageName = salaryWageName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getAssignStaffId() {
        return assignStaffId;
    }

    public void setAssignStaffId(String assignStaffId) {
        this.assignStaffId = assignStaffId;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSelectData() {
        return selectData;
    }

    public void setSelectData(String selectData) {
        this.selectData = selectData;
    }

    public String getDefaultPrinter() {
        return defaultPrinter;
    }

    public void setDefaultPrinter(String defaultPrinter) {
        this.defaultPrinter = defaultPrinter;
    }

    public String getAuthEdit() {
        return authEdit;
    }

    public void setAuthEdit(String authEdit) {
        this.authEdit = authEdit;
    }

    public int getIsIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(int isIgnore) {
        this.isIgnore = isIgnore;
    }

    public String getConditionData() {
        return conditionData;
    }

    public void setConditionData(String conditionData) {
        this.conditionData = conditionData;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

}
