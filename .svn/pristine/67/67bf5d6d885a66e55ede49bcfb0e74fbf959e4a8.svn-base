package com.lingnet.hcm.action.salary;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.salary.SalaryPayroll;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.service.salary.PeriodService;
import com.lingnet.hcm.service.salary.SalaryItemsService;
import com.lingnet.hcm.service.salary.SalaryPayrollService;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;

/**
 * 工资单
 * @ClassName: SalaryPagerAction 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年7月19日 下午3:28:26 
 *
 */
public class SalaryPagerAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    @Resource(name="salaryItemsService")
    private SalaryItemsService salaryItemsService;
    @Resource(name="salaryPayrollService")
    private SalaryPayrollService salaryPayrollService;
    @Resource(name="periodService")
    private PeriodService periodService;
    private String userName;
    private Map<String, Object> mapData;

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String show() {
        return "show";
    }

    /**
     * @Title: 部门工资单列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String dept() {
        return "dept";
    }

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
     * @Title: 可查看月份控制列表页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String rollList() {
        return "rolllist";
    }

    /**
     * @Title: 可查看月份控制增加页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String rollAdd() {
        depId = LingUtil.userinfo().getCid();
        return "rolladd";
    }

    /**
     * @Title: 可查看月份控制编辑页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String rollEdit() {
        mapData = salaryPayrollService.getSalaryPayRollData(id);
        depId = LingUtil.userinfo().getCid();
        return "rolladd";
    }

    /**
     * @Title: 薪酬期间选择页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月21日 V 1.0
     */
    public String payPeriodSelector() {
        return "payPeriodSelector";
    }

    /**
     * @Title: 可查看月份控制增加、更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        return ajax(Status.success, salaryPayrollService.saveOrUpdate(formdata));
    }

    /**
     * @Title: 可查看月份控制删除
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String remove() {
        return ajax(Status.success, salaryPayrollService.remove(ids));
    }

    /**
     * @Title: 获取个人工资单
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getListData() {
        String userName = LingUtil.userName();
        return ajax(Status.success, JsonUtil.Encode(salaryItemsService.getListData(userName, searchData, pager)));
    }

    /**
     * @Title: 获取部门工资单
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getDeptListData() {
        return ajax(Status.success, JsonUtil.Encode(salaryItemsService.getDeptListData(searchData, pager)));
    }

    /**
     * @Title: 获取可控制的员工月份信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月21日 V 1.0
     */
    public String getPayRollsData() {
        return ajax(Status.success, JsonUtil.Encode(salaryPayrollService.getPayRollsData(searchData, pager)));
    }

    /**
     * @Title: 获取当前账号所在公司
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月22日 V 1.0
     */
    public String getCurAccountCompany() {
        Map<String, String> map = new HashMap<String, String>();
        QxUser qxUser = getBeanByCriteria(QxUser.class,
                Restrictions.eq("username", userName),
                Restrictions.eq("isDelete", 0));
        if (qxUser == null) {
            map.put("success", "false");
            map.put("info", "该账号不存在");

            return ajax(Status.success, JsonUtil.Encode(map));
        }
        BasicInformation basicInformation = getBeanByCriteria(BasicInformation.class,
                Restrictions.eq("jobNumber", userName),
                Restrictions.eq("isDelete", 0));
        if (basicInformation == null) {
            map.put("success", "false");
            map.put("info", "该账号对应的员工不存在");

            return ajax(Status.success, JsonUtil.Encode(map));
        }
        SalaryRecord record = getBeanByCriteria(SalaryRecord.class, Restrictions.eq("staffId", basicInformation.getId()));
        if (record == null) {
            map.put("success", "false");
            map.put("info", "该账号对应的员工档案不存在");

            return ajax(Status.success, JsonUtil.Encode(map));
        } else {
            map.put("success", "true");
            map.put("info", record.getFilmName());

            return ajax(Status.success, JsonUtil.Encode(map));
        }
    }

    /**
     * @Title: 获取指定数量的薪酬期间
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月22日 V 1.0
     */
    public String getSpeialMonthPeriod() {
        // 获取当前登录账号可查看的薪酬期间
        String userName = LingUtil.userName();
        SalaryPayroll payroll = getBeanByCriteria(SalaryPayroll.class,
                Restrictions.eq("userName", userName),
                Restrictions.eq("isDelete", 0));
        List<Map<String, Object>> list = null;
        if (payroll == null) {
            // 默认可查看工资单的薪酬期间个数（上几个薪酬期间）
            String pr = ToolUtil.getPropert("config.properties", "payroll");
            if (!StringUtils.isBlank(pr)) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                list = periodService.getSpeialMonthPeriod(String.valueOf(year), LingUtil.userinfo().getCid(), Integer.valueOf(pr), "", 1);
            }
        } else {
            switch (payroll.getWatchType()) {
            case 1:// 连续薪酬期间个数
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                list = periodService.getSpeialMonthPeriod(String.valueOf(year), LingUtil.userinfo().getCid(),
                        Integer.valueOf(payroll.getTypeValue()), "", 1);
                break;
            case 2:// 可查看年份
                list = periodService.getSpeialMonthPeriod(payroll.getTypeValue(), LingUtil.userinfo().getCid(), 0, "", 2);
                break;
            case 3:// 可查看哪几个薪酬期间
                list = periodService.getSpeialMonthPeriod("", LingUtil.userinfo().getCid(), 0, payroll.getTypeValue(), 3);
                break;
            default:
                break;
            }
        }

        return ajax(Status.success, JsonUtil.Encode(list));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, Object> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, Object> mapData) {
        this.mapData = mapData;
    }

}
