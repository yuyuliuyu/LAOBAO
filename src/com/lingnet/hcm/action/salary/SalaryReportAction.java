package com.lingnet.hcm.action.salary;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.service.salary.SalaryInsuranceService;
import com.lingnet.util.JsonUtil;

/**
 * 报表管理
 * @ClassName: SalaryReportAction 
 * @Description: 报表管理
 * @author zhanghj
 * @date 2017年3月16日 下午4:54:37 
 *
 */
public class SalaryReportAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    @Resource(name="salaryInsuranceService")
    private SalaryInsuranceService salaryInsuranceService;

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
     * @Title: 修改页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String edit() {
        return "edit";
    }

    /**
     * @Title: 工资报表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String salary() {
        return "salary";
    }

    /**
     * @Title: 财务报表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String financial() {
        return "financial";
    }

    /**
     * @Title: 获取工资报表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月2日 V 1.0
     */
    public String getSlaryReportListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryInsuranceService.getSlaryReportListData(searchData, pager)));
    }

    /**
     * @Title: 导出工资列表 
     * void 
     * @author zhanghj
     * @since 2017年6月4日 V 1.0
     */
    public void export() {
        salaryInsuranceService.export(searchData);
    }

}
