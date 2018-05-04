package com.lingnet.hcm.action.salary;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.CheckProcess;
import com.lingnet.hcm.entity.salary.Perioddata;
import com.lingnet.hcm.entity.salary.SalaryAssignation;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;

/**
 * 薪酬核算
 * @ClassName: SalaryQuotaAction 
 * @Description: 薪酬核算 
 * @author zhanghj
 * @date 2017年3月15日 上午9:39:21 
 *
 */
public class SalaryQuotaAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;
    private String salaryPeriodText;// 薪酬期间文本
    private String companyId;// 公司ID
    private String assignStaffId;// 薪酬核算数据主表ID
    private String assignId;// 薪酬核算数据薪资中间表ID
    private String columns;// 薪资列集合
    private String formula;// 公式
    private String itemID;// 薪资ID
    private String selectData;// 选择的员工数据
    private int isIgnore;// 是否忽略未提交或者未通过的二次分配项目
    private String defaultPrinter;// 默认打印机
    private String pid;// 流程选择父id
    private String nodeId;// 上一个节点id
    private SalaryAssignation salaryAssignation;
    private File uploadFile;// 导入的文件

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
        companyId = LingUtil.userinfo().getCid();
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
        SalaryAssignation assignation = getBeanByCriteria(SalaryAssignation.class,
                Restrictions.eq("id", ids), Restrictions.eq("isDelete", 0));
        if (assignation != null) {
            pid = assignation.getProcess();
            // 获取上一个节点ID
            List<CheckProcess> checkProcesss = getOrderBeanListByCriteria(CheckProcess.class, Order.desc("createDate"),
                    Restrictions.eq("salaryAssignationId", assignation.getId()),
                    Restrictions.eq("isDelete", 0));
            if (checkProcesss.size() > 0) {
                nodeId = checkProcesss.get(0).getNodeId();
            }
        }
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
     * @Title: 流程选择
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月12日 V 1.0
     */
    public String authpro() {
        SalaryAssignation assignation = getBeanByCriteria(SalaryAssignation.class,
                Restrictions.eq("id", ids), Restrictions.eq("isDelete", 0));
        if (assignation != null) pid = assignation.getProcess();
        return "authpro";
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
     * @Title: 获取已经分配的员工薪资项目数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    public String getAssignationForStaffData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getAssignationForStaffData(id, ids, searchData, pager)));
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
     * @Title: 更新总量信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月13日 V 1.0
     */
    public String updateTotalAmount() {
        try {
            return ajax(Status.success, salaryAssignationService.updateTotalAmount(id));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 计算薪酬过程的员工公式
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月15日 V 1.0
     */
    public String saveItemsFormula() {
        try {
            return ajax(Status.success, salaryAssignationService.saveItemsFormula(id, assignStaffId));
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
    @Deprecated
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
     * @Title: 发布工资条
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月11日 V 1.0
     */
    public String updateSendSalaryRecord() {
        try {
            return ajax(Status.success, salaryAssignationService.updateSendSalaryRecord(ids));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 取消发布工资条
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月11日 V 1.0
     */
    public String updateNoSendSalaryRecord() {
        try {
            return ajax(Status.success, salaryAssignationService.updateNoSendSalaryRecord(ids));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 根据权限获取不同的审核数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月25日 V 1.0
     */
    @Deprecated
    public String getSalaryAssignationForAuthListData() {
        String userName = LingUtil.userName();
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getSalaryAssignationForAuthListData(userName, searchData, pager)));
    }

    /**
     * @Title: 根据权限获取不同的审核数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月25日 V 1.0
     */
    public String getSalaryAssignationForAuthListData2() {
        String userName = LingUtil.userName();
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getSalaryAssignationForAuthListData2(userName, searchData, pager)));
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
    @Deprecated
    public String isCheckAUth() {
        String userName = LingUtil.userName();
        return ajax(Status.success, salaryAssignationService.isCheckAUth(ids, userName));
    }

    /**
     * @Title: 判断选择的维护过程是否有权限操作
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月26日 V 1.0
     */
    public String isCheckAUth2() {
        String userName = LingUtil.userName();
        return ajax(Status.success, salaryAssignationService.isCheckAUth2(ids, userName));
    }

    /**
     * @Title: 审核通过
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月1日 V 1.0
     */
    @Deprecated
    public String updateCheckPassed() {
        String userName = LingUtil.userName();
        return ajax(Status.success, salaryAssignationService.updateCheckPassed(ids, userName, formdata));
    }

    /**
     * @Title: 审核通过
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月1日 V 1.0
     */
    public String updateCheckPassed2() {
        String userName = LingUtil.userName();
        try {
            return ajax(Status.success, salaryAssignationService.updateCheckPassed2(ids, userName, formdata));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 生成报盘文件
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月28日 V 1.0
     */
    @Deprecated
    public String createBankFile() {
        return ajax(Status.success, salaryAssignationService.createBankFile(ids));
    }

    /**
     * @Title: 将维护过程数据分配到二次分配对应的薪资项目总量
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月1日 V 1.0
     */
    public String updateAllot() {
        return ajax(Status.success, salaryAssignationService.updateAllot(id));
    }

    /**
     * @Title: 获取二次分配数据分配到核算数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月1日 V 1.0
     */
    public String updateCheckSecondData() {
        return ajax(Status.success, salaryAssignationService.updateCheckSecondData(id, ids));
    }

    /**
     * @Title: 允许薪酬期间保险缴费修改
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月1日 V 1.0
     */
    public String allowIbfEdit() {
        return ajax(Status.success, salaryAssignationService.allowIbfEdit(id));
    }

    /**
     * @Title: 导入核算数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月8日 V 1.0
     */
    public String uploadCheckData() {
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
        // 获取上传文件的名字
        String[] fileName=wrapper.getFileNames("uploadFile");
        if (fileName.length == 0) {
            return ajax(Status.success, "请选择上传文件！");
        }
        // 文件后缀
        String endSuffix= fileName[0].substring(fileName[0].lastIndexOf("."), fileName[0].length());
        if (!endSuffix.toLowerCase().endsWith("xls")
                && !endSuffix.toLowerCase().endsWith("xlsx")) {
            return ajax(Status.success, "请选择正确的文件类型，必须是以.xls或.xlsx结尾！");
        }
        try {
            String message = salaryAssignationService.uploadSecondItems(endSuffix, uploadFile, id);
            return ajax(Status.success, message);
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 根据部门获取数据（打印用）
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月14日 V 1.0
     */
    public String getDeptsData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAssignationService.getDeptsData(id, searchData)));
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

    public int getIsIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(int isIgnore) {
        this.isIgnore = isIgnore;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

}
