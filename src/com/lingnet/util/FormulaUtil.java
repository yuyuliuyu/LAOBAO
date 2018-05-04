package com.lingnet.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.InsuranceItems;
import com.lingnet.hcm.entity.salary.InsuranceMiddle;
import com.lingnet.hcm.entity.salary.MonthSalary;
import com.lingnet.hcm.entity.salary.Perioddata;
import com.lingnet.hcm.entity.salary.Rate;
import com.lingnet.hcm.entity.salary.Ratedata;
import com.lingnet.hcm.entity.salary.SalaryInsurance;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.entity.salary.SalaryValue;
import com.lingnet.hcm.service.salary.SalaryFormulaService;
import com.lingnet.qxgl.entity.JcDictionary;

/**
 * 公式解析
 * @ClassName: FormulaUtil 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月29日 下午12:52:20 
 *
 */
@Service("formulaUtil")
public class FormulaUtil extends BaseAction {

    private static final long serialVersionUID = -5406590856072164378L;

    @Resource(name="salaryFormulaService")
    private SalaryFormulaService salaryFormulaService;
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * @Title: 获取当前日期 
     * @return 
     * Date 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public Date getCurDate() {
        return new Date();
    }

    /**
     * @Title: 提取年 
     * @param date
     * @return 
     * int 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);

        return year;
    }

    /**
     * @Title: 提取月 
     * @param date
     * @return 
     * int 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);

        return month;
    }

    /**
     * @Title: 提取日 
     * @param date
     * @return 
     * int 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return day;
    }

    /**
     * @Title: 获取某年某月的天数 
     * @param year
     * @param month
     * @return 
     * int 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        cal.add(Calendar.DATE, -1);
        int days = cal.get(Calendar.DAY_OF_MONTH);

        return days;
    }

    /**
     * @Title: 获取员工ID
     * @param staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月5日 V 1.0
     */
    public String getGridRowStaffId(String B_staffId) {
        return B_staffId;
    }

    /**
     * @Title: 基本工资计算
     * @param staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月5日 V 1.0
     */
    public String getBaseSalary(String B_staffId) {
        SalaryRecord record = getBeanByCriteria(SalaryRecord.class, Restrictions.eq("staffId", B_staffId));
        if (record != null) {
            // 获取薪酬岗位
            String salaryPost = record.getSalaryPost();
            if (!StringUtils.isBlank(salaryPost)) {
                StringBuilder sql = new StringBuilder();
                sql.append("     SELECT SR.STAFF_ID, NVL(LLEVEL.XS, 0)+NVL(SBD.XS, 0) XS,  ");
                sql.append("        NVL((TO_CHAR(SB.OUTSOL_DATE, 'yyyy')-TO_CHAR(SB.INSOL_DATE, 'yyyy') + 1), 0) SOLDIER_AGE,   ");
                sql.append("        NVL(JBI.AGE, 0) AGE, NVL(JBI.SEX, -1) SEX,   ");
                sql.append("        NVL(FLOOR(SYSDATE - TO_DATE(TO_CHAR(JBI.IN_PORT_TIME, 'yyyy-MM-dd'),'yyyy-MM-dd')), 0) joinG,   ");// 入港时间--》得到入港天数
                sql.append("        NVL(TO_CHAR(SYSDATE, 'yyyy')-TO_CHAR(SR.GD_DATE, 'yyyy'), 0) GD_DATE    ");// 股东双方日期
                sql.append("     FROM XC_SALARY_RECORD SR                                  ");
                sql.append("     LEFT JOIN                                                 ");
                sql.append("       (SELECT SBD.ZJLB_CODE, SDD.NAME,                        ");
                sql.append("         SDD.ID, SBD.XS                                        ");
                sql.append("       FROM XC_SALARY_BASE_DICTIONARY SBD                      ");
                sql.append("       LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                 ");
                sql.append("       ON SBD.ZJLB_CODE  = SDD.ZJLB                            ");
                sql.append("       AND SDD.IS_DELETE = 0                                   ");
                sql.append("       WHERE SBD.TYPE    = 1                                   ");
                sql.append("       AND SBD.IS_DELETE = 0                                   ");
                sql.append("       ) LLEVEL                                                ");
                sql.append("       ON SR.SALARY_POST = LLEVEL.ID                           ");
                sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI                        ");
                sql.append("       ON SR.STAFF_ID = JBI.ID                                 ");
                sql.append("     LEFT JOIN XC_SALARY_BASE_DICTIONARY SBD                   ");
                sql.append("       ON JBI.EMP_TYPE = SBD.ZJLB_CODE                         ");
                sql.append("      AND SBD.TYPE = 2                                         ");
                sql.append("      AND SBD.IS_DELETE = 0                                    ");
                sql.append("     LEFT JOIN JC_SOLDIER_BACK SB                   ");
                sql.append("       ON JBI.JOB_NUMBER = SB.JOB_NUMBER                         ");
                sql.append("     WHERE SR.STAFF_ID = '"+B_staffId+"'                         ");
                List<?> list = salaryFormulaService.findBySql(sql.toString());
                int totalXs = 0;
                if (list.size() > 0) {
                    Object[] obj = (Object[]) list.get(0);
                    int xs = Integer.valueOf(obj[1].toString());
                    int soldierAge = Integer.valueOf(obj[2].toString());// 在岗工龄= 军龄+进入股东双方日期
                    int gdDate = Integer.valueOf(obj[6].toString());// 股东双方日期
                    int age = Integer.valueOf(obj[3].toString());
                    int sex = Integer.valueOf(obj[4].toString());
                    int joinG = Integer.valueOf(obj[5].toString());

                    // 查找在港工龄系数
                    int zgglxs = 0;
                    String zgglxsSql = "SELECT XS FROM XC_SALARY_BASE_DICTIONARY WHERE " + (soldierAge + gdDate) + " >= TO_NUMBER(ZJLB_CODE)"
                            + " AND " + (soldierAge + gdDate) + " < TO_NUMBER(ZJLB_CODE2) AND TYPE = 3";
                    List<?> zgglxsList = findBySql(zgglxsSql);
                    if (zgglxsList.size() > 0) {
                        zgglxs = Integer.valueOf(zgglxsList.get(0).toString());
                    }

                    // 年龄倾斜系数
                    int nlqxxs = 0;
                    if (sex != -1) {
                        if (sex == 1) sex = 4;// 男
                        else sex = 5;// 女
                        String ageSql = "SELECT XS FROM XC_SALARY_BASE_DICTIONARY WHERE " + age + " >= TO_NUMBER(ZJLB_CODE)"
                                + " AND " + age + " < TO_NUMBER(ZJLB_CODE2) AND TYPE = " + sex;
                        List<?> ageList = findBySql(ageSql);
                        if (ageList.size() > 0) {
                            nlqxxs = Integer.valueOf(ageList.get(0).toString());
                        }
                    }
                    totalXs = xs + zgglxs + nlqxxs;

                    // 3*单价*系数
                    int year = joinG / 365;
                    if (year > 0) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        int month = calendar.get(Calendar.MONTH) + 1;
                        if (month == 1) {
                            year += 1;// 一月份为初始月开始计算
                        }
                    }

                    // 获取职级单价字典
                    Double price = 0d;
                    JcDictionary jcDictionary = getBeanByCriteria(JcDictionary.class, Restrictions.eq("pid", "402881945c75bcc7015c75c319d20007"),
                            Restrictions.eq("dicname", (year==0?1: year)+""));
                    if (jcDictionary != null) {
                        price = Double.valueOf(jcDictionary.getDicvalue());
                    } else {
                        price = 1d;
                    }

                    return String.valueOf(3 * price * totalXs);
                } else return "0";
            } else {
                return "0";
            }
        } else {
            return "0";
        }
    }

    /**
     * @Title: 获取工月平均工资
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月10日 V 1.0
     */
    public String getStaffMonthAvgSalary(String B_staffId, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        MonthSalary monthSalary = getBeanByCriteria(MonthSalary.class,
                Restrictions.eq("staffId", B_staffId),
                Restrictions.eq("effectiveYear", year - offset),
                Restrictions.eq("isDelete", 0));
        if (monthSalary == null) {
            return "0";
        } else return monthSalary.getAverageSalary();
    }

    /**
     * @Title: 获取保险中公式的值
     * @param B_companyId
     * @param B_staffId
     * @param T_salaryPeriod
     * @param gs
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月20日 V 1.0
     */
    public String getSbGs(String B_companyId, String B_staffId, String T_salaryPeriod, String gs) {
        // 得到计算公式并计算
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("B_staffId", B_staffId);
        map.put("B_companyId", B_companyId);
        map.put("T_assignationId", T_salaryPeriod);
        Object v = salaryFormulaService.formula(gs, map);

        return v.toString();
    }

    /**
     * @Title: 获取公司缴费基数
     * @param insuranceId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getBaseCompanyToJf(String B_staffId, String insuranceId) {
        if (StringUtils.isBlank(insuranceId)) {
            return "0";
        }
        InsuranceMiddle middle = getBeanByCriteria(InsuranceMiddle.class,
                Restrictions.eq("staffId", B_staffId),
                Restrictions.eq("isDelete", 0));
        if (null != middle) {
            InsuranceItems items = getBeanByCriteria(InsuranceItems.class,
                    Restrictions.eq("insuranceMiddleId", middle.getId()),
                    Restrictions.eq("ibfId", insuranceId),
                    Restrictions.eq("isDelete", 0));
            if (null != items) {
                return items.getBaseCompany();
            } else return "0";
        } else return "0";
    }

    /**
     * @Title: 获取公司缴费比例
     * @param insuranceId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getCompanyJfBl(String B_companyId, String insuranceId) {
        if (StringUtils.isBlank(insuranceId)) {
            return "0";
        }
        SalaryInsurance insurance = getBeanByCriteria(SalaryInsurance.class,
                Restrictions.eq("name", insuranceId),
                Restrictions.eq("companyId", B_companyId),
                Restrictions.eq("isDelete", 0));
        if (insurance != null)
            return String.valueOf(Double.valueOf(insurance.getBiCompany()) * 0.01);
        else return "0";
    }

    /**
     * @Title: 获取保险公司缴费公式
     * @param insuranceId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    @Deprecated
    public String getCompanyJf(String B_companyId, String insuranceId) {
        if (StringUtils.isBlank(insuranceId)) {
            return "0";
        }
        SalaryInsurance insurance = getBeanByCriteria(SalaryInsurance.class,
                Restrictions.eq("name", insuranceId),
                Restrictions.eq("companyId", B_companyId),
                Restrictions.eq("isDelete", 0));
        if (insurance != null)
            return insurance.getCompanyFormulaMath();
        else return "0";
    }

    /**
     * @Title: 获取员工缴费基数
     * @param insuranceId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getBasePersonalToJf(String B_staffId, String insuranceId) {
        if (StringUtils.isBlank(insuranceId)) {
            return "0";
        }
        InsuranceMiddle middle = getBeanByCriteria(InsuranceMiddle.class,
                Restrictions.eq("staffId", B_staffId),
                Restrictions.eq("isDelete", 0));
        if (null != middle) {
            InsuranceItems items = getBeanByCriteria(InsuranceItems.class,
                    Restrictions.eq("insuranceMiddleId", middle.getId()),
                    Restrictions.eq("ibfId", insuranceId),
                    Restrictions.eq("isDelete", 0));
            if (null != items) {
                return items.getBasePersonal();
            } else return "0";
        } else return "0";
    }

    /**
     * @Title: 获取员工缴费比例
     * @param insuranceId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String getPersonalJfBl(String B_companyId, String insuranceId) {
        if (StringUtils.isBlank(insuranceId)) {
            return "0";
        }
        SalaryInsurance insurance = getBeanByCriteria(SalaryInsurance.class,
                Restrictions.eq("name", insuranceId),
                Restrictions.eq("companyId", B_companyId),
                Restrictions.eq("isDelete", 0));
        if (insurance != null)
            return String.valueOf(Double.valueOf(insurance.getBiPersonal()) * 0.01);
        else return "0";
    }

    /**
     * @Title: 获取保险员工缴费公式
     * @param insuranceId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    @Deprecated
    public String getPersonalJf(String B_companyId, String insuranceId) {
        if (StringUtils.isBlank(insuranceId)) {
            return "0";
        }
        SalaryInsurance insurance = getBeanByCriteria(SalaryInsurance.class,
                Restrictions.eq("name", insuranceId),
                Restrictions.eq("companyId", B_companyId),
                Restrictions.eq("isDelete", 0));
        if (insurance != null) {
            return insurance.getPersonalFormulaMath();
        }
        else return "0";
    }

    /**
     * @Title: 获取公司保险项目薪酬期间正常缴费额
     * @param B_companyId 公司ID
     * @param B_staffId 员工ID
     * @param T_salaryPeriod 薪酬期间
     * @param insuranceId 保险项目
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月30日 V 1.0
     */
    public String getComInsuranceCharge(String B_companyId, String B_staffId, String T_salaryPeriod, String insuranceId) {
        Object v = "0";
        List<?> list = findBySql(getInsuranceChargeSql(B_companyId, B_staffId, insuranceId, T_salaryPeriod, 0));
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            v = obj[1] == null ? "0" : obj[1];
        }

        return v.toString();
    }

    /**
     * @Title: 获取公司保险项目薪酬期间补缴缴费额
     * @param B_companyId 公司ID
     * @param B_staffId 员工ID
     * @param T_salaryPeriod 薪酬期间
     * @param insuranceId 保险项目
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月30日 V 1.0
     */
    public String getComInsuranceBjCharge(String B_companyId, String B_staffId, String T_salaryPeriod, String insuranceId) {
        Object v = "0";
        List<?> list = findBySql(getInsuranceChargeSql(B_companyId, B_staffId, insuranceId, T_salaryPeriod, 1));
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            v = obj[1] == null ? "0" : obj[1];
        }

        return v.toString();
    }

    /**
     * @Title: 获取个人保险项目薪酬期间正常缴费额
     * @param B_companyId 公司ID
     * @param B_staffId 员工ID
     * @param T_salaryPeriod 薪酬期间
     * @param insuranceId 保险项目
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月30日 V 1.0
     */
    public String getPerInsuranceCharge(String B_companyId, String B_staffId, String T_salaryPeriod, String insuranceId) {
        Object v = "0";
        List<?> list = findBySql(getInsuranceChargeSql(B_companyId, B_staffId, insuranceId, T_salaryPeriod, 0));
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            v = obj[2] == null ? "0" : obj[2];
        }

        return v.toString();
    }

    /**
     * @Title: 获取个人保险项目薪酬期间补缴缴费额
     * @param B_companyId 公司ID
     * @param B_staffId 员工ID
     * @param T_salaryPeriod 薪酬期间
     * @param insuranceId 保险项目
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月30日 V 1.0
     */
    public String getPerInsuranceBjCharge(String B_companyId, String B_staffId, String T_salaryPeriod, String insuranceId) {
        Object v = "0";
        List<?> list = findBySql(getInsuranceChargeSql(B_companyId, B_staffId, insuranceId, T_salaryPeriod, 1));
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            v = obj[2] == null ? "0" : obj[2];
        }

        return v.toString();
    }

    /**
     * @Title: 获取员工关联薪资项目值
     * @param staffId
     * @param itemsId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月26日 V 1.0
     */
    public String getStaffStandItemsValue(String B_staffId, String itemsId) {
        SalaryValue value = getBeanByCriteria(SalaryValue.class,
                Restrictions.eq("staffId", B_staffId),
                Restrictions.eq("salaryItemsId", itemsId),
                Restrictions.eq("isDelete", 0));
        if (value == null) return "0";
        return value.getStaticValue();
    }

    /**
     * @Title: 获取薪酬核算过程员工薪资项目的值
     * @param B_staffId
     * @param T_assignationId 分配过程ID
     * @param itemsId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月5日 V 1.0
     */
    public String getStaffRowItemsValue(String B_staffId, String T_assignationId, String itemsId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT ASS.ASSIGNATION_CHARGE, ASS.ADD_OR_LESS, ASS.NUMBER_ACCURACY    ");
        sql.append("   FROM XC_ASSIGNATION_STAFF ASTAFF            ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS   ");
        sql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID     ");
        sql.append("   AND ASS.IS_DELETE = 0                       ");
        sql.append("   WHERE ASTAFF.STAFF_ID = ?                  ");
        sql.append("   AND ASTAFF.SALARY_ASSIGNATION_ID = ?       ");
        sql.append("   AND ASS.IBF_ID = ?                         ");
        sql.append("   AND ASTAFF.IS_DELETE = 0                         ");

        Object v = "0";
        Map<String, Object> result = null;
        try {
            Object[] args = new Object[]{B_staffId, T_assignationId, itemsId};
            result = (Map<String, Object>)jdbcTemplate.queryForMap(sql.toString(), args);
        } catch (DataAccessException e) {
        }
        if (null != result) {
            if (result.get("ASSIGNATION_CHARGE") != null && result.get("ADD_OR_LESS") != null) {
                if (Integer.valueOf(result.get("ADD_OR_LESS").toString()) == 2) {// 减项
                    Double d = Double.valueOf(result.get("ASSIGNATION_CHARGE").toString());
                    v = d==0?"0":String.valueOf(-d);
                } else v = result.get("ASSIGNATION_CHARGE");
            }
            DecimalFormat df = new DecimalFormat();
            v = numberAccuracy(df, result.get("NUMBER_ACCURACY").toString(), v);
        }

        return v.toString();
    }

    /**
     * @Title: 根据薪酬核算过程薪资项目的默认公式计算薪资项目的费用
     * @param B_staffId
     * @param T_assignationId 分配过程ID
     * @param itemsId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月6日 V 1.0
     */
    public String getStaffRowItemsFormulaValue(String B_staffId, String T_assignationId, String itemsId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT SF.ID,                               ");
        sql.append("     TO_CHAR(SF.CONTENT) CONTENT,                       ");
        sql.append("     TO_CHAR(SF.CNT_MATH) CNTMATH,                       ");
        sql.append("     ASS.ID salaryId, ASS.ADD_OR_LESS,                   ");
        sql.append("     ASS.NUMBER_ACCURACY                   ");
        sql.append("   FROM XC_SALARY_FORMULA SF                            ");
        sql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                ");
        sql.append("   ON (GWAY.ID            = SF.BIND_ID                  ");
        sql.append("   OR GWAY.SALARY_WAGE_ID = SF.BIND_ID)                 ");
        sql.append("   AND GWAY.IS_DELETE     = 0                           ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW                   ");
        sql.append("   ON SGW.ID = GWAY.WAGE_ID                             ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS            ");
        sql.append("   ON GWAY.ITEM_ID = ASS.IBF_ID                         ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                ");
        sql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID      ");
        sql.append("   AND ASTAFF.IS_DELETE = 0                             ");
        sql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                   ");
        sql.append("   ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID    ");
        sql.append("   AND SGW.SALARY_GROUP_ID = SA.SALARY_GROUP_ID    ");
        sql.append("   WHERE SF.IS_DELETE = 0                               ");
        sql.append("   AND SF.IS_DEFAULT = 1                                ");
        sql.append("   AND SA.ID = '"+T_assignationId+"'                    ");
        sql.append("   AND ASS.IBF_ID = '"+itemsId+"'                       ");
        sql.append("   AND ASTAFF.STAFF_ID = '"+B_staffId+"'                ");

        List<?> list = findBySql(sql.toString());
        Object v = "0";
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            Object vObj = obj[2] == null ? "0" : obj[2];

            // 得到计算公式并计算
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("B_staffId", B_staffId);
            map.put("T_assignationId", T_assignationId);
            v = salaryFormulaService.formula(vObj.toString(), map);
            DecimalFormat df = new DecimalFormat();
            v = numberAccuracy(df, obj[5].toString(), v);

            // 更新此薪资项目的费用
            String updateSql = "UPDATE XC_ASSIGNATION_STAFF_SALARY SET ASSIGNATION_CHARGE=? WHERE ID=? AND IS_DELETE = 0";
            jdbcTemplate.update(updateSql, new Object[]{v, obj[3]});
            if (Integer.valueOf(obj[4].toString()) == 2) {// 减项
                Double d = Double.valueOf(v.toString());
                v = d==0?"0":String.valueOf(-d);
            }
        }

        return v.toString();

    }

    /**
     * @Title: 获取员工薪酬期间绩效考核奖励分数
     * @param B_staffId
     * @param T_salaryPeriod 薪酬期间
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String getStaffJxkhJlScore(String B_staffId, String T_salaryPeriod) {
        // 根据薪酬期间算出对应的年份与月份
        Perioddata perioddata = getBeanByCriteria(Perioddata.class, Restrictions.eq("id", T_salaryPeriod));
        int year = 0;
        int month = 0;
        if (perioddata != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(perioddata.getStartDate());
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                ");
        sql.append("     SUM(TO_NUMBER(JPA.PERFOR_REWARD_SCROE)) SCORE       ");
        sql.append("   FROM JC_PERFORMANCE_APPRAISAL JPA                     ");
        sql.append("   WHERE JPA.USERID = '"+B_staffId+"' ");
        sql.append("   AND JPA.IS_DELETE = 0                                 ");
        sql.append("   AND JPA.PERFOR_STATE = 1                              ");
        sql.append("   AND TO_CHAR(JPA.PERFOR_DATE, 'yyyy-MM') = TO_CHAR(TO_DATE('"+year+"-"+month+"', 'yyyy-MM'), 'yyyy-MM')   ");

        List<?> list = findBySql(sql.toString());
        Object v = "0";
        if (list.size() > 0) {
            Object obj = list.get(0);
            v = obj == null ? "0" : obj;
        }

        return v.toString();
    }

    /**
     * @Title: 获取员工薪酬期间绩效考核奖励金额
     * @param B_staffId
     * @param T_salaryPeriod 薪酬期间
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String getStaffJxkhJlMoney(String B_staffId, String T_salaryPeriod) {
        // 根据薪酬期间算出对应的年份与月份
        Perioddata perioddata = getBeanByCriteria(Perioddata.class, Restrictions.eq("id", T_salaryPeriod));
        int year = 0;
        int month = 0;
        if (perioddata != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(perioddata.getStartDate());
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                ");
        sql.append("     SUM(TO_NUMBER(JPA.PERFOR_REWARD_MONEY)) MONEY       ");
        sql.append("   FROM JC_PERFORMANCE_APPRAISAL JPA                     ");
        sql.append("   WHERE JPA.USERID = '"+B_staffId+"' ");
        sql.append("   AND JPA.IS_DELETE = 0                                 ");
        sql.append("   AND JPA.PERFOR_STATE = 1                              ");
        sql.append("   AND TO_CHAR(JPA.PERFOR_DATE, 'yyyy-MM') = TO_CHAR(TO_DATE('"+year+"-"+month+"', 'yyyy-MM'), 'yyyy-MM')   ");
        
        List<?> list = findBySql(sql.toString());
        Object v = "0";
        if (list.size() > 0) {
            Object obj = list.get(0);
            v = obj == null ? "0" : obj;
        }
        
        return v.toString();
    }

    /**
     * @Title: 获取员工薪酬期间绩效考核扣罚分数
     * @param B_staffId
     * @param T_salaryPeriod 薪酬期间
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String getStaffJxkhKfScore(String B_staffId, String T_salaryPeriod) {
        // 根据薪酬期间算出对应的年份与月份
        Perioddata perioddata = getBeanByCriteria(Perioddata.class, Restrictions.eq("id", T_salaryPeriod));
        int year = 0;
        int month = 0;
        if (perioddata != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(perioddata.getStartDate());
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                ");
        sql.append("     SUM(TO_NUMBER(JPA.PERFOR_PUNISH_SCROE)) SCORE       ");
        sql.append("   FROM JC_PERFORMANCE_APPRAISAL JPA                     ");
        sql.append("   WHERE JPA.USERID = '"+B_staffId+"' ");
        sql.append("   AND JPA.IS_DELETE = 0                                 ");
        sql.append("   AND JPA.PERFOR_STATE = 1                              ");
        sql.append("   AND TO_CHAR(JPA.PERFOR_DATE, 'yyyy-MM') = TO_CHAR(TO_DATE('"+year+"-"+month+"', 'yyyy-MM'), 'yyyy-MM')   ");
        
        List<?> list = findBySql(sql.toString());
        Object v = "0";
        if (list.size() > 0) {
            Object obj = list.get(0);
            v = obj == null ? "0" : obj;
        }
        
        return v.toString();
    }

    /**
     * @Title: 获取员工薪酬期间绩效考核扣罚金额
     * @param B_staffId
     * @param T_salaryPeriod 薪酬期间
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String getStaffJxkhKfMoney(String B_staffId, String T_salaryPeriod) {
        // 根据薪酬期间算出对应的年份与月份
        Perioddata perioddata = getBeanByCriteria(Perioddata.class, Restrictions.eq("id", T_salaryPeriod));
        int year = 0;
        int month = 0;
        if (perioddata != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(perioddata.getStartDate());
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                ");
        sql.append("     SUM(TO_NUMBER(JPA.PERFOR_PUNISH_MONEY)) MONEY       ");
        sql.append("   FROM JC_PERFORMANCE_APPRAISAL JPA                     ");
        sql.append("   WHERE JPA.USERID = '"+B_staffId+"' ");
        sql.append("   AND JPA.IS_DELETE = 0                                 ");
        sql.append("   AND JPA.PERFOR_STATE = 1                              ");
        sql.append("   AND TO_CHAR(JPA.PERFOR_DATE, 'yyyy-MM') = TO_CHAR(TO_DATE('"+year+"-"+month+"', 'yyyy-MM'), 'yyyy-MM')   ");
        
        List<?> list = findBySql(sql.toString());
        Object v = "0";
        if (list.size() > 0) {
            Object obj = list.get(0);
            v = obj == null ? "0" : obj;
        }

        return v.toString();
    }

    /**
     * @Title: 获取员工工资档案里的值
     * @param B_staffId
     * @param B_salaryGroupId 薪资组
     * @param itemsId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月16日 V 1.0
     */
    public String getStaffStandValue(String B_staffId, String B_salaryGroupId, String itemsId) {
        SalaryValue salaryValue = getBeanByCriteria(SalaryValue.class,
                Restrictions.eq("staffId", B_staffId),
                Restrictions.eq("salaryGroupId", B_salaryGroupId),
                Restrictions.eq("salaryItemsId", itemsId),
                Restrictions.eq("isDelete", 0));
        String value = "0";
        if (salaryValue != null) {
            value = salaryValue.getStaticValue();
        }

        return value;
    }

    /**
     * @Title: 获取员工薪酬期间出勤天数
     * @param B_staffId
     * @param year
     * @param month
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月24日 V 1.0
     */
    public String getAttendDays(String B_staffId, int year, int month) {
        List<?> list = getAttendDaysSql(B_staffId, year, month);
        Object v = "0";
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            Object gzxs = obj[0];
            if (gzxs != null) {
                if (gzxs.equals("计时")) {
                    v = obj[1];
                }
            }
        }

        return v.toString();
    }

    /**
     * @Title: 获取员工薪酬期间夜班天数
     * @param B_staffId
     * @param year
     * @param month
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月24日 V 1.0
     */
    public String getNightDays(String B_staffId, int year, int month) {
        List<?> list = getAttendDaysSql(B_staffId, year, month);
        Object v = "0";
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            Object gzxs = obj[0];
            if (gzxs != null) {
                if (gzxs.equals("计时")) {
                    v = obj[2];
                }
            }
        }

        return v.toString();
    }

    /**
     * @Title: 获取员工薪酬期间节加天数
     * @param B_staffId
     * @param year
     * @param month
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月24日 V 1.0
     */
    public String getJieJDays(String B_staffId, int year, int month) {
        List<?> list = getAttendDaysSql(B_staffId, year, month);
        Object v = "0";
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            Object gzxs = obj[0];
            if (gzxs != null) {
                if (gzxs.equals("计时")) {
                    v = obj[2];
                }
            }
        }

        return v.toString();
    }

    /**
     * @Title: 获取员工薪酬期间休加天数
     * @param B_staffId
     * @param year
     * @param month
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月24日 V 1.0
     */
    public String getXiuDays(String B_staffId, int year, int month) {
        List<?> list = getAttendDaysSql(B_staffId, year, month);
        Object v = "0";
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            Object gzxs = obj[0];
            if (gzxs != null) {
                if (gzxs.equals("计时")) {
                    v = obj[3];
                }
            }
        }

        return v.toString();
    }

    /**
     * @Title: 获取起征点
     * @param B_staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月28日 V 1.0
     */
    public String getStartPoint(String B_staffId) {
        Object v = "0";
        SalaryRecord record = getBeanByCriteria(SalaryRecord.class, Restrictions.eq("staffId", B_staffId));
        if (null != record) {
            Rate rate = getBeanByCriteria(Rate.class, Restrictions.eq("type", 1));
            if (null != rate) {
                if (1 == record.getJsType()) {// 本国
                    v= rate.getNativeMoney();
                } else if (2 == record.getJsType()) {// 外国
                    v= rate.getForeignMoney();
                }
            }
        }

        return v.toString();
    }

    /**
     * @Title: 个人薪金税
     * @param B_staffId
     * @param startPoint 起征点
     * @param money 钱
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月28日 V 1.0
     */
    public String getSalaryRate(String B_staffId, Object startPoint, Object money) {
        Rate rate = getBeanByCriteria(Rate.class, Restrictions.eq("type", 1));
        Double f = 0d;
        if (rate != null) {
            Double s = Double.valueOf(money.toString()) - Double.valueOf(startPoint.toString());
            Conjunction and = Restrictions.conjunction();
            and.add(Restrictions.lt("low", s));
            and.add(Restrictions.ge("high", s));
            Ratedata ratedata = getBeanByCriteria(Ratedata.class,
                    Restrictions.eq("rateId", rate.getId()), and);
            if (null != ratedata) {
                f = s * ratedata.getRate() * 0.01 - ratedata.getKcs();
            }
        }

        return String.valueOf(f);
    }

    /**
     * @Title: 获取员工全部年假
     * @param B_staffId
     * @param year
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月31日 V 1.0
     */
    public String getLegalDays(String B_staffId, int year) {
        return getStaffLeaveYxDays(B_staffId, year)[0].toString();
    }

    /**
     * @Title: 获取员工应休年假
     * @param B_staffId
     * @param year
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月31日 V 1.0
     */
    public String getShouldTakeDays(String B_staffId, int year) {
        return getStaffLeaveYxDays(B_staffId, year)[1].toString();
    }

    /**
     * @Title: 获取员工已休年假记录（存在小数的情况）
     * @param B_staffId
     * @param year
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月31日 V 1.0
     */
    public String getHaveTakeDays(String B_staffId, int year) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                ");
        sql.append("   SUM(CALR.DAYS) YX                     ");
        sql.append("   FROM CK_ANNUAL_LEAVE_RECORD CALR      ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI    ");
        sql.append("   ON CALR.JOB_NUMBER = JBI.JOB_NUMBER   ");
        sql.append("   WHERE JBI.ID = '"+B_staffId+"'        ");
        sql.append("   AND CALR.YEAR_CALENDAR = "+year+"         ");
        sql.append("   GROUP BY JBI.ID                       ");
        List<?> list = findBySql(sql.toString());
        Object v = "0";
        if (list.size() > 0) {
            v = list.get(0);
        }

        return v.toString();
    }

    /**
     * @Title: 获取员工剩余年假
     * @param B_staffId
     * @param year
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月31日 V 1.0
     */
    public String getSurplusDays(String B_staffId, int year) {
        Double allDays = Double.valueOf(getShouldTakeDays(B_staffId, year)) - Double.valueOf(getHaveTakeDays(B_staffId, year));

        return String.valueOf(Math.ceil(allDays));
    }

    /**
     * @Title: 员工是否存在兼职
     * @param B_deptId
     * @param B_staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月5日 V 1.0
     */
    public String getStaffExistJob(String B_deptId, String B_staffId) {
        return getStaffExistBase(B_deptId, B_staffId, 1);
    }

    /**
     * @Title: 员工是否标准工时
     * @param B_deptId
     * @param B_staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月5日 V 1.0
     */
    public String getStaffStandTime(String B_deptId, String B_staffId) {
        return getStaffExistBase(B_deptId, B_staffId, 2);
    }

    /**
     * @Title: 员工是否特殊工时
     * @param B_deptId
     * @param B_staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月5日 V 1.0
     */
    public String getStaffSpecialTime(String B_deptId, String B_staffId) {
        return getStaffExistBase(B_deptId, B_staffId, 3);
    }

    /**
     * @Title: 员工分配权限
     * @param B_deptId
     * @param B_staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月5日 V 1.0
     */
    public String getStaffFpqx(String B_deptId, String B_staffId) {
        return getStaffExistBase(B_deptId, B_staffId, 4);
    }

    /**
     * @Title: 员工支付形式
     * @param B_deptId
     * @param B_staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月5日 V 1.0
     */
    public String getStaffPayType(String B_deptId, String B_staffId) {
        return getStaffExistBase(B_deptId, B_staffId, 5);
    }

    /**
     * @Title: 员工特殊标记
     * @param B_deptId
     * @param B_staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月5日 V 1.0
     */
    public String getStaffSpecialSign(String B_deptId, String B_staffId) {
        return getStaffExistBase(B_deptId, B_staffId, 6);
    }

    /**
     * @Title: 员工是否存在基数
     * @param B_deptId
     * @param B_staffId
     * @param type
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月5日 V 1.0
     */
    private String getStaffExistBase(String B_deptId, String B_staffId, int type) {
        String res = "";
        SalaryRecord record = getBeanByCriteria(SalaryRecord.class,
                Restrictions.eq("staffId", B_staffId),
                Restrictions.eq("deptname", B_deptId));
        switch (type) {
        case 1:// 兼职
            res = null == record.getIsJz()? "否": record.getIsJz() == 0? "否" : "是";
            break;
        case 2:// 是否标准工时
            res = null == record.getIsBzgs()? "否": record.getIsBzgs() == 0? "否" : "是";
            break;
        case 3:// 是否特殊工时
            res = null == record.getIsSpecialHour()? "否": record.getIsSpecialHour() == 0? "否" : "是";
            break;
        case 4:// 分配权限
            res = null == record.getFpqx()? "": record.getFpqx() == 1? "公司" : "基层";
            break;
        case 5:// 支付形式
            res = null == record.getZfxs()? "": record.getZfxs() == 1? "自发薪" : "股东劳务费";
            break;
        case 6:// 特殊标记
            if (null != record.getSpecialMark()) {
                JcDictionary dictionary = getBeanByCriteria(JcDictionary.class, Restrictions.eq("dicvalue", record.getSpecialMark()+""));
                if (null != dictionary) res = dictionary.getDicname();
            }
            break;

        default:
            break;
        }

        return res;
    }

    /**
     * @Title: 员工全部年假以及应休年假
     * @param B_staffId
     * @param year
     * @return 
     * Object[] 
     * @author zhanghj
     * @since 2017年7月31日 V 1.0
     */
    private Object[] getStaffLeaveYxDays(String B_staffId, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int curYear = cal.get(Calendar.YEAR);
        Double legalDay = 0d;// 全部休假天数
        Double actualDay = 0d;//应休休假天数
        if (year <= curYear) {
            StringBuilder sql = new StringBuilder();
            sql.append("   SELECT                                   ");
            sql.append("     JOB_NUMBER, NAME, CLASS_NAME,          ");
            sql.append("     TO_CHAR(SET_WORK_DATE, 'yyyy-MM-dd') SET_WORK_DATE,  ");
            sql.append("     TO_CHAR(IN_PORT_TIME, 'yyyy-MM-dd') IN_PORT_TIME,    ");
            sql.append("     NVL(JBI.ANNUAL_LEAVE, 0) ANNUAL_LEAVE    ");
            sql.append("   FROM JC_BASIC_INFORMATION JBI            ");
            sql.append("   LEFT JOIN JC_PT_JOB JPJ                  ");
            sql.append("   ON JPJ.PERSON_ID = JBI.ID                ");
            sql.append("   LEFT JOIN CK_CLASS CC                    ");
            sql.append("   ON JBI.CLASS_NO = CC.CLASS_NO            ");
            sql.append("   WHERE JBI.IS_DELETE  = 0                 ");
            sql.append("   AND JPJ.IS_HOST_POST = '1'               ");
            sql.append("   AND JBI.ID = '"+B_staffId+"'             ");
            List<?> list = findBySql(sql.toString());
            if (list.size() > 0) {
                Object[] obj = (Object[]) list.get(0);
                String workDate = obj[3].toString();// 开始工作日期
                String portDate = obj[4].toString();//入港时间
                String thisDate = "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdfAll = new SimpleDateFormat("yyyy-MM-dd");
                if (year < Integer.parseInt(sdf.format(new Date()))){//输入年度小于当前年份
                    thisDate = year + "-12-31";
                } else if (year == Integer.parseInt(sdf.format(new Date()))){
                    thisDate = sdfAll.format(new Date());
                }
                int kjgl=Integer.parseInt(obj[5].toString());// 年假扣减工龄
                Date d=new Date();
                try {
                    d = sdfAll.parse(workDate);
                } catch (ParseException e) { 
                    e.printStackTrace();
                }
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(d);
                int gznx= cal2.get(Calendar.YEAR);
                int sjnx=gznx+kjgl;
                workDate=sjnx+workDate.substring(4);

                // 当前日期距离开始工作日期相差月份数
                int legalMonths = countMonths(workDate, thisDate);

                // 搜索年份距离开始工作日期相差月份数
                int actualMonths = countMonths(portDate, thisDate);
                Double allDay = 0d;// 法定休假天数
                if (legalMonths <= 0){
                    allDay = 0d;
                } else if (legalMonths > 0 && legalMonths < 12){
                    allDay = 0d;
                } else if (legalMonths/12 >=1 && legalMonths/12 < 10){
                    allDay = 5d;
                } else if (legalMonths/12 >= 10 && legalMonths/12 < 20){
                    allDay = 10d;
                } else {
                    allDay = 15d;
                }
                SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
                if (actualMonths > 0){
                    if (Integer.parseInt(portDate.substring(0, 4)) == Integer.parseInt(thisDate.substring(0, 4))){
                        if (Integer.parseInt(portDate.substring(0, 4)) == Integer.parseInt(sdfMonth.format(new Date()))){
                            int thisMonth = Integer.parseInt(sdfMonth.format(new Date()))-Integer.parseInt(portDate.substring(5, 7))+1;
                            legalDay = thisMonth/12.0*allDay;
                        } else {
                            int thisMonth = 12-Integer.parseInt(portDate.substring(5, 7))+1;
                            legalDay = thisMonth/12.0*allDay;
                        }
                    } else {
                        legalDay = allDay;
                    }
                    if (year < Integer.parseInt(sdf.format(new Date()))){//输入年度小于当前年份
                        if (Integer.parseInt(thisDate.substring(0, 4)) == Integer.parseInt(portDate.substring(0, 4))){
                            int thisMonth = Integer.parseInt(thisDate.substring(5, 7))-Integer.parseInt(portDate.substring(5, 7))+1;
                            actualDay = thisMonth/12.0*allDay;
                        } else {
                            actualDay = allDay;
                        }
                    } else if (year == Integer.parseInt(sdf.format(new Date()))){
                        if (Integer.parseInt(thisDate.substring(0, 4)) == Integer.parseInt(portDate.substring(0, 4))){
                            int thisMonth = Integer.parseInt(sdfMonth.format(new Date()))-Integer.parseInt(portDate.substring(5, 7))+1;
                            actualDay = thisMonth/12.0*allDay;
                        } else {
                            actualDay = allDay;
                        }
                    }
                }
            }
        }
        Object[] obj = new Object[2];
        obj[0] = legalDay;
        obj[1] = actualDay;

        return obj;
    }

    /**
     * @Title: 获取薪酬期间员工出勤天数、夜班、节加、休加（计时）
     * @param B_staffId
     * @param year
     * @param month
     * @return 
     * List<?> 
     * @author zhanghj
     * @since 2017年7月24日 V 1.0
     */
    private List<?> getAttendDaysSql(String B_staffId, int year, int month) {
        String date = "";
        String _year = String.valueOf(year);
        String _month = String.format("%02d", month);
        date = _year.substring(2) + _month.substring(2);
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                              ");
        sql.append("     CMS.WAGE_FORM,CMS.ATTENDANCE_DAYS,CMS.NIGHT_DAYS, ");
        sql.append("     CMS.VACATION_MODULUS,CMS.OVERTIME_MODULUS         ");
        sql.append("   FROM CK_MONTH_STATISTICS CMS                        ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                  ");
        sql.append("   ON CMS.JOB_NUMBER = JBI.JOB_NUMBER                  ");
        sql.append("   WHERE JBI.ID = '"+B_staffId+"'                      ");
        sql.append("   AND CMS.YEAR_MONTH = '"+date+"'                     ");

        return findBySql(sql.toString());
    }

    /**
     * @Title: 根据公司，员工，福利项目以及是否补缴得到员工缴费信息sql语句
     * @param companyId
     * @param starffId
     * @param insuranceId
     * @param T_salaryPeriod
     * @param isPayOff
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月30日 V 1.0
     */
    private String getInsuranceChargeSql(String companyId, String starffId, String insuranceId, String T_salaryPeriod, int isPayOff) {
        // 根据薪酬期间算出对应的年份与月份
        Perioddata perioddata = getBeanByCriteria(Perioddata.class, Restrictions.eq("id", T_salaryPeriod));
        int year = 0;
        int month = 0;
        if (perioddata != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(perioddata.getStartDate());
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH) + 1;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT PP.ID,                                              ");
        sql.append("       PI.PAYMENT_COMPANY, PI.PAYMENT_PERSONAL,  ");
        sql.append("       PP.IS_PAY_BACK, PP.PAY_BACK_YEAR, PP.PAY_BACK_MONTH      ");
        sql.append("     FROM XC_PROCESS_PAYMENT PP                                 ");
        sql.append("     LEFT JOIN XC_PAYMENT_INSURANCE PI                          ");
        sql.append("     ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                    ");
        sql.append("     LEFT JOIN XC_SALARY_RECORD SR                              ");
        sql.append("     ON SR.STAFF_ID = PP.STAFF_ID                               ");
        sql.append("     WHERE PP.IS_DELETE = 0                                     ");
        sql.append("     AND PI.IS_DELETE = 0                                       ");
        sql.append("     AND SR.STAFF_ID = '"+starffId+"'                           ");
        sql.append("     AND SR.FILM_NAME = '"+companyId+"'                         ");
        sql.append("     AND PI.IBF_ID = '"+insuranceId+"'                          ");
        sql.append("     AND PP.IS_PAY_BACK = "+isPayOff+"                          ");
        sql.append("     AND PP.JF_YEAR = "+year+"                          ");
        sql.append("     AND PP.JF_MONTH = "+month+"                        ");

        return sql.toString();
    }

    /**
     * @Title: 计算年份之间月份
     * @param date1
     * @param date2
     * @return 
     * int 
     * @author zhanghj
     * @since 2017年7月31日 V 1.0
     */
    private int countMonths(String date1,String date2) {
        Calendar c1;
        Calendar c2;
        int year;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            c1 = Calendar.getInstance();
            c2 = Calendar.getInstance();
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));
            year = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);

            return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * @Title: 计算保留值
     * @param df
     * @param xs
     * @param value
     * @return 
     * Object 
     * @author zhanghj
     * @since 2017年5月12日 V 1.0
     */
    private String numberAccuracy(DecimalFormat df, String xs, Object value) {
        // 小数位数
        if (Integer.valueOf(xs) == 0) {
            df.applyPattern("0");
        } else {
            df.applyPattern("0."+String.format("%0"+ xs +"d%n", 0));
        }

        return df.format(Double.valueOf(value.toString())).trim();
    }

}
