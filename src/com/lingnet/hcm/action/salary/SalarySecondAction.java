package com.lingnet.hcm.action.salary;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.SalaryGroup;
import com.lingnet.hcm.service.salary.SalaryAgainService;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;

/**
 * 二次分配
 * @ClassName: SalarySecondAction 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月17日 上午8:27:54 
 *
 */
public class SalarySecondAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;
    private String companyId;// 公司ID
    private String period;// 薪酬期间
    private String fpqx;// 分配权限
    private String columns;// 薪资标准列ID
    private String salaryGroupId;// 薪资组ID
    private String salaryNames;// 薪资标准列名
    private String defaultPrinter;// 默认打印机
    private String deptName;// 部门名称
    private String groupName;// 薪资组名称
    private String fpqxName;// 分配权限名称
    private File uploadFile;// 导入的文件

    @Resource(name="salaryAgainService")
    private SalaryAgainService salaryAgainService;

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String list() {
        companyId = LingUtil.userinfo().getCid();
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
        return "add";
    }

    /**
     * @Title: 二次分配详细数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月17日 V 1.0
     */
    public String jsonList() {
        companyId = LingUtil.userinfo().getCid();
        return "jsonlist";
    }

    /**
     * @Title: 二次分配打印
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月26日 V 1.0
     */
    public String print() {
        // 配置的打印机
        String salaryRecord = ToolUtil.getPropert("printer.properties", "GZD");
        if (StringUtils.isBlank(salaryRecord)) {
            // 查找默认打印机
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
            defaultPrinter = ps.getName();
        } else defaultPrinter = salaryRecord;

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            QxDepartment department = getBeanByCriteria(QxDepartment.class, Restrictions.eq("id", mapData.get("deptId")));
            if (department != null) deptName = department.getName();
            SalaryGroup group = getBeanByCriteria(SalaryGroup.class, Restrictions.eq("id", mapData.get("salaryGroup")));
            if (group != null) groupName = group.getName();
            fpqxName = Integer.valueOf(mapData.get("fpqx")) == 1? "公司" : "基层";
        }

        return "print";
    }

    /**
     * @Title: 二次分配保存更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            return ajax(Status.success, salaryAgainService.saveOrUpdate(companyId, depId, period, fpqx, griddata, columns, salaryNames));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 上报
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月22日 V 1.0
     */
    public String updateSetReportStatus() {
        try {
            return ajax(Status.success, salaryAgainService.updateSetReportStatus(companyId, period, id, fpqx));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 取消上报
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月22日 V 1.0
     */
    public String updateSetCancelReportStatus() {
        return ajax(Status.success, salaryAgainService.updateSetCancelReportStatus(companyId, period, fpqx));
    }

    /**
     * @Title: 获取所有员工的薪资组
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月22日 V 1.0
     */
    public String getAllStaffSalaryGroup() {
        return ajax(Status.success, JsonUtil.Encode(salaryAgainService.getAllStaffSalaryGroup(companyId)));
    }

    /**
     * @Title: 获取当前公司薪酬期间二次分配总量数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getPeriodAmountListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAgainService.getPeriodAmountListData(searchData, companyId, pager)));
    }

    /**
     * @Title: 获取员工二次分配
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getAllSecondAmountListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAgainService.getAllSecondAmountListData(period, companyId, pager)));
    }

    /**
     * @Title: 获取员工二次分配
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getAllSecondForTypeData() {
        return ajax(Status.success, JsonUtil.Encode(salaryAgainService.getAllSecondForTypeSql(period, companyId, searchData)));
    }

    /**
     * @Title: 导入二次分配数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String uploadSecondItems() {
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
            String message = salaryAgainService.uploadSecondItems(endSuffix, uploadFile, period, companyId, depId, fpqx, searchData);
            return ajax(Status.success, message);
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 导出薪资项目 
     * void 
     * @author zhanghj
     * @since 2017年6月21日 V 1.0
     */
    public void export() {
        salaryAgainService.export(period, depId, searchData);
    }

    @Actions(
            {@Action(value = "/jfmxmb",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "jfmxmb" }),
            @Action(value = "/salaryItem",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "salaryItem" }),
            @Action(value = "/avgSalary",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "avgSalary" }),
            @Action(value = "/adjustStand",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "adjustStand" }),
            @Action(value = "/checkModel",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "checkModel" }),
            @Action(value = "/adjustFixed",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "adjustFixed" }),
            @Action(value = "/highCamera",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "highCamera" }),
            @Action(value = "/secondAdjust",
            results = { @Result(name = SUCCESS, type = "chain", location = "fileDownload") },
            params = {"name", "secondAdjust" })})
    public String downloadFile() {
        return SUCCESS;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getSalaryNames() {
        return salaryNames;
    }

    public void setSalaryNames(String salaryNames) {
        this.salaryNames = salaryNames;
    }

    public String getFpqx() {
        return fpqx;
    }

    public void setFpqx(String fpqx) {
        this.fpqx = fpqx;
    }

    public String getSalaryGroupId() {
        return salaryGroupId;
    }

    public void setSalaryGroupId(String salaryGroupId) {
        this.salaryGroupId = salaryGroupId;
    }

    public String getDefaultPrinter() {
        return defaultPrinter;
    }

    public void setDefaultPrinter(String defaultPrinter) {
        this.defaultPrinter = defaultPrinter;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFpqxName() {
        return fpqxName;
    }

    public void setFpqxName(String fpqxName) {
        this.fpqxName = fpqxName;
    }

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

}
