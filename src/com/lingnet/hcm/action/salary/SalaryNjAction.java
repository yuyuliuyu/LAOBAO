package com.lingnet.hcm.action.salary;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.SalaryNjAge;
import com.lingnet.hcm.service.salary.SalaryNjProgramService;
import com.lingnet.util.JsonUtil;

/**
 * 年金计算
 * @ClassName: SalaryNjAction 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月15日 上午11:47:48 
 *
 */
public class SalaryNjAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    private String companyId;// 公司ID
    private String gridData1;// 职级缴费比例表数据
    private String gridData2;// 年龄倾斜系数表
    private String gridData3;// 年龄系数表
    private String gridData4;// 年龄截止表
    private String qyjfbl;// 企业缴费比例
    private String staffId;// 员工ID
    private String deptLevel;// 职务级别

    @Resource(name="salaryNjProgramService")
    private SalaryNjProgramService salaryNjProgramService;

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
        return "add";
    }

    /**
     * @Title: 年金配置增加更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        try {
            return ajax(Status.success, salaryNjProgramService.saveOrUpdate(companyId, gridData1, gridData2, gridData3, gridData4, formdata));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取职级缴费比例数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月15日 V 1.0
     */
    public String getZjjfblData() {
        return ajax(Status.success, JsonUtil.Encode(salaryNjProgramService.getZjjfblData(companyId)));
    }

    /**
     * @Title: 获取年龄倾斜系数数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月15日 V 1.0
     */
    public String getNlqxxsData() {
        return ajax(Status.success, JsonUtil.Encode(salaryNjProgramService.getNlqxxsData(companyId)));
    }

    /**
     * @Title: 获取年龄系数数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月15日 V 1.0
     */
    public String getNlxsData() {
        return ajax(Status.success, JsonUtil.Encode(salaryNjProgramService.getNlxsData(companyId)));
    }

    /**
     * @Title: 获取年龄截止日期
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月15日 V 1.0
     */
    public String getNlJzDateData() {
        return ajax(Status.success, JsonUtil.Encode(salaryNjProgramService.getNlJzDateData(companyId)));
    }

    /**
     * @Title: 获取年金计算数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getNjListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryNjProgramService.getNjListData(searchData, companyId, pager)));
    }

    /**
     * @Title: 重新初始化
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月16日 V 1.0
     */
    public String updateInit() {
        return ajax(Status.success, salaryNjProgramService.updateInit(companyId, qyjfbl));
    }

    /**
     * @Title: 重新计算
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月16日 V 1.0
     */
    public String updateReCalculate() {
        return ajax(Status.success, salaryNjProgramService.updateReCalculate(companyId, qyjfbl));
    }

    /**
     * @Title: 获取年金汇总
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月6日 V 1.0
     */
    public String getTotalData() {
        Map<String, Object> totalMap = salaryNjProgramService.getNjTotalListData(companyId);
        SalaryNjAge njAge = getBeanByCriteria(SalaryNjAge.class,
                Restrictions.eq("companyId", companyId),
                Restrictions.eq("bz", 2),
                Restrictions.eq("isDelete", 0));
        if (njAge != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
            totalMap.put("ageEnd", formatter.format(njAge.getNljzrq()));
        }

        return ajax(Status.success, JsonUtil.Encode(totalMap));
    }

    /**
     * @Title: 保存年金汇总信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月7日 V 1.0
     */
    public String saveOrUpdateNjTotal() {
        try {
            return ajax(Status.success, salaryNjProgramService.saveOrUpdateNjTotal(griddata, companyId));
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * @Title: 获取职级类别对应的职级缴费比例和职级系数
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月7日 V 1.0
     */
    public String getDeptLevelData() {
        return ajax(Status.success, JsonUtil.Encode(salaryNjProgramService.getDeptLevelData(id, deptLevel, companyId)));
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGridData1() {
        return gridData1;
    }

    public void setGridData1(String gridData1) {
        this.gridData1 = gridData1;
    }

    public String getGridData2() {
        return gridData2;
    }

    public void setGridData2(String gridData2) {
        this.gridData2 = gridData2;
    }

    public String getGridData3() {
        return gridData3;
    }

    public void setGridData3(String gridData3) {
        this.gridData3 = gridData3;
    }

    public String getGridData4() {
        return gridData4;
    }

    public void setGridData4(String gridData4) {
        this.gridData4 = gridData4;
    }

    public String getQyjfbl() {
        return qyjfbl;
    }

    public void setQyjfbl(String qyjfbl) {
        this.qyjfbl = qyjfbl;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

}
