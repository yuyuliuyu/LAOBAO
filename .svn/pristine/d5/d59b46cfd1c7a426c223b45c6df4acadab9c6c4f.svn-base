package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryNjProgramDao;
import com.lingnet.hcm.entity.salary.SalaryNjProgram;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 企业年金缴费分配方案
 * @ClassName: SalaryNjProgramDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月15日 下午4:29:29 
 *
 */
@Repository("salaryNjProgramDao")
public class SalaryNjProgramDaoImpl extends BaseDaoImplInit<SalaryNjProgram, String> implements SalaryNjProgramDao {

    @Resource(name="salaryAssignationService")
    private SalaryAssignationService salaryAssignationService;

    @Override
    public List<Map<String, Object>> getNlxsData(String companyId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                          ");
        sql.append("     LISTAGG(SAX.ID, ',') WITHIN GROUP (ORDER BY SAX.SEX ASC) ID,                  ");
        sql.append("     LISTAGG(SAX.ZGNL_MIN, ',') WITHIN GROUP (ORDER BY SAX.SEX ASC) SEXMANLOW,     ");
        sql.append("     LISTAGG(SAX.ZGNL_MAX, ',') WITHIN GROUP (ORDER BY SAX.SEX ASC) SEXMANHIGH,    ");
        sql.append("     MIN(SAX.ZJXS) ZJXS                                                            ");
        sql.append("   FROM JC_SALARY_AGE_XS SAX                                                       ");
        sql.append("   WHERE SAX.IS_DELETE=0                                                           ");
        sql.append("     AND SAX.COMPANY_ID='"+companyId+"'                                            ");
        sql.append("   GROUP BY SAX.ZJXS                                                               ");
        sql.append("   ORDER BY MIN(SAX.ZGNL_MAX) DESC                                                ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            String[] sexlow = obj[1].toString().split(",");
            map.put("sexManLow", sexlow[0]);
            map.put("sexWomanLow", sexlow[1]);
            String[] sexHigh = obj[2].toString().split(",");
            map.put("sexManHigh", sexHigh[0]);
            map.put("sexWomanHigh", sexHigh[1]);
            map.put("zjxs", obj[3]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String getAllStaffListData(String companyId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT JBI.ID,                                                                   ");
        sql.append("     JBI.NAME, JBI.ID_NUMBER, JBI.BORTH_DATE,                                       ");
        sql.append("     JBI.SEX, JBI.ANNUITY_TYPE, SR.FILM_NAME, B.FZX,                                    ");
        sql.append("     SR.DEPTNAME deptId, QD.NAME DEPTNAME,                                          ");
        sql.append("     ROUND(MONTHS_BETWEEN(SNG.NLJZRQ,JBI.BORTH_DATE)/12,2) NLJZRQ1,                 ");// 截至XXX年龄
        sql.append("     ROUND(MONTHS_BETWEEN(SNG2.NLJZRQ,JBI.BORTH_DATE)/12,2) NLJZRQ2,                ");// 截至上年末年龄
        sql.append("     SNP.ZJJFBL,                                                                    ");// 倾斜性缴费职级比例
        sql.append("     NVL(SAQ.ZJXS, 0) qxxnlxs,                                                         ");// 倾斜性缴费年龄系数
        sql.append("     SNP.ZJXS zjxs,                                                                      ");// 基本缴费职级系数
        sql.append("     SAX.ZJXS nlxs,                                                                       ");// 基本缴费年龄系数
        sql.append("     TO_NUMBER(NVL(MS.AVERAGE_SALARY,0)) AVERAGE_SALARY,                           ");// 月平均工资
        sql.append("     JBI.SFLB                            ");// 身份类别
        sql.append("   FROM XC_SALARY_RECORD SR                                                         ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                               ");
        sql.append("   ON SR.STAFF_ID = JBI.ID                                                          ");
        sql.append("   LEFT JOIN BRANCH B                                                               ");
        sql.append("   ON SR.FILM_NAME = B.ID                                                           ");
        sql.append("   LEFT JOIN QX_DEPARTMENT QD                                                       ");
        sql.append("   ON SR.DEPTNAME      = QD.ID                                                      ");
        sql.append("   LEFT JOIN JC_SALARY_NJ_AGE SNG                                                   ");
        sql.append("   ON SR.FILM_NAME = SNG.COMPANY_ID                                                 ");
        sql.append("   AND SNG.IS_DELETE = 0                                                            ");
        sql.append("   AND SNG.BZ = 1                                                                   ");
        sql.append("   LEFT JOIN JC_SALARY_NJ_AGE SNG2                                                  ");
        sql.append("   ON SR.FILM_NAME = SNG2.COMPANY_ID                                                ");
        sql.append("   AND SNG2.IS_DELETE = 0                                                           ");
        sql.append("   AND SNG2.BZ = 2                                                                  ");
        sql.append("   LEFT JOIN JC_SALARY_NJ_PROGRAM SNP                                               ");
        sql.append("   ON SR.FILM_NAME = SNP.COMPANY_ID                                                 ");
        sql.append("   AND JBI.ANNUITY_TYPE = SNP.DEPT_LEVEL                                            ");
        sql.append("   AND SNP.IS_DELETE = 0                                                            ");
        sql.append("   LEFT JOIN JC_SALARY_AGE_QX SAQ                                                   ");
        sql.append("   ON SR.FILM_NAME = SAQ.COMPANY_ID                                                 ");
        sql.append("   AND SAQ.IS_DELETE = 0                                                            ");
        sql.append("   AND CASE WHEN JBI.SEX =1 THEN SAQ.SEX_MAN                                        ");
        sql.append("       WHEN JBI.SEX = 0 THEN SAQ.SEX_WOMAN                                          ");
        sql.append("     ELSE 0 END = TRUNC(MONTHS_BETWEEN(SNG.NLJZRQ,JBI.BORTH_DATE)/12)               ");
        sql.append("   LEFT JOIN JC_SALARY_AGE_XS SAX                                                   ");
        sql.append("   ON SR.FILM_NAME = SAX.COMPANY_ID                                                 ");
        sql.append("   AND SAX.IS_DELETE = 0                                                            ");
        sql.append("   AND CASE WHEN JBI.SEX =1 THEN 1                                                  ");
        sql.append("            WHEN JBI.SEX = 0 THEN 2                                                 ");
        sql.append("       ELSE -1 END = SAX.SEX                                                        ");
        sql.append("   AND (TRUNC(MONTHS_BETWEEN(SNG2.NLJZRQ,JBI.BORTH_DATE)/12) >= SAX.ZGNL_MIN        ");
        sql.append("         AND TRUNC(MONTHS_BETWEEN(SNG2.NLJZRQ,JBI.BORTH_DATE)/12) <= SAX.ZGNL_MAX)  ");
        sql.append("   LEFT JOIN XC_MONTH_SALARY MS                                                   ");
        sql.append("   ON MS.STAFF_ID = SR.STAFF_ID                                                 ");
        sql.append("   AND MS.IS_DELETE = 0                                                 ");
        sql.append("   WHERE JBI.IS_DELETE = 0                                                          ");
        sql.append("   AND SR.FILM_NAME = '"+companyId+"'                         ");

        return sql.toString();
    }

    @Override
    public String getAllStaffListDataForRe(String companyId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT SNF.ID,                                                                   ");
        sql.append("     JBI.NAME, JBI.ID_NUMBER, JBI.BORTH_DATE,                                       ");
        sql.append("     JBI.SEX, SNF.DEPT_LEVEL, SNF.COMPANY_ID, B.FZX,                                    ");
        sql.append("     SR.DEPTNAME deptId, QD.NAME DEPTNAME,                                          ");
        sql.append("     ROUND(MONTHS_BETWEEN(SNG.NLJZRQ,JBI.BORTH_DATE)/12,2) NLJZRQ1,                 ");// 截至XXX年龄
        sql.append("     ROUND(MONTHS_BETWEEN(SNG2.NLJZRQ,JBI.BORTH_DATE)/12,2) NLJZRQ2,                ");// 截至上年末年龄
        sql.append("     SNP.ZJJFBL,                                                                    ");// 倾斜性缴费职级比例
        sql.append("     NVL(SAQ.ZJXS, 0) qxxnlxs,                                                         ");// 倾斜性缴费年龄系数
        sql.append("     SNP.ZJXS zjxs,                                                                      ");// 基本缴费职级系数
        sql.append("     SAX.ZJXS nlxs,                                                                       ");// 基本缴费年龄系数
        sql.append("     TO_NUMBER(NVL(MS.AVERAGE_SALARY,0)) AVERAGE_SALARY                            ");// 月平均工资
        sql.append("   FROM JC_SALARY_NJ_FORMULA SNF                                                         ");
        sql.append("   LEFT JOIN XC_SALARY_RECORD SR                                                         ");
        sql.append("   ON SR.STAFF_ID = SNF.STAFF_ID                                                         ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                               ");
        sql.append("   ON SR.STAFF_ID = JBI.ID                                                          ");
        sql.append("   LEFT JOIN BRANCH B                                                               ");
        sql.append("   ON SNF.COMPANY_ID = B.ID                                                           ");
        sql.append("   LEFT JOIN QX_DEPARTMENT QD                                                       ");
        sql.append("   ON SR.DEPTNAME      = QD.ID                                                      ");
        sql.append("   LEFT JOIN JC_SALARY_NJ_AGE SNG                                                   ");
        sql.append("   ON SR.FILM_NAME = SNG.COMPANY_ID                                                 ");
        sql.append("   AND SNG.IS_DELETE = 0                                                            ");
        sql.append("   AND SNG.BZ = 1                                                                   ");
        sql.append("   LEFT JOIN JC_SALARY_NJ_AGE SNG2                                                  ");
        sql.append("   ON SR.FILM_NAME = SNG2.COMPANY_ID                                                ");
        sql.append("   AND SNG2.IS_DELETE = 0                                                           ");
        sql.append("   AND SNG2.BZ = 2                                                                  ");
        sql.append("   LEFT JOIN JC_SALARY_NJ_PROGRAM SNP                                               ");
        sql.append("   ON SR.FILM_NAME = SNP.COMPANY_ID                                                 ");
        sql.append("   AND SNF.DEPT_LEVEL = SNP.DEPT_LEVEL                                            ");
        sql.append("   AND SNP.IS_DELETE = 0                                                            ");
        sql.append("   LEFT JOIN JC_SALARY_AGE_QX SAQ                                                   ");
        sql.append("   ON SR.FILM_NAME = SAQ.COMPANY_ID                                                 ");
        sql.append("   AND SAQ.IS_DELETE = 0                                                            ");
        sql.append("   AND CASE WHEN JBI.SEX =1 THEN SAQ.SEX_MAN                                        ");
        sql.append("       WHEN JBI.SEX = 0 THEN SAQ.SEX_WOMAN                                          ");
        sql.append("     ELSE 0 END = TRUNC(MONTHS_BETWEEN(SNG.NLJZRQ,JBI.BORTH_DATE)/12)               ");
        sql.append("   LEFT JOIN JC_SALARY_AGE_XS SAX                                                   ");
        sql.append("   ON SNF.COMPANY_ID = SAX.COMPANY_ID                                                 ");
        sql.append("   AND SAX.IS_DELETE = 0                                                            ");
        sql.append("   AND CASE WHEN JBI.SEX =1 THEN 0                                                  ");
        sql.append("            WHEN JBI.SEX = 0 THEN 1                                                 ");
        sql.append("       ELSE -1 END = SAX.SEX                                                        ");
        sql.append("   AND (TRUNC(MONTHS_BETWEEN(SNG2.NLJZRQ,JBI.BORTH_DATE)/12) >= SAX.ZGNL_MIN        ");
        sql.append("         AND TRUNC(MONTHS_BETWEEN(SNG2.NLJZRQ,JBI.BORTH_DATE)/12) <= SAX.ZGNL_MAX)  ");
        sql.append("   LEFT JOIN XC_MONTH_SALARY MS                                                   ");
        sql.append("   ON MS.STAFF_ID = SR.STAFF_ID                                                 ");
        sql.append("   AND MS.IS_DELETE = 0                                                 ");
        sql.append("   WHERE JBI.IS_DELETE = 0                                                          ");
        sql.append("   AND SNF.IS_DELETE = 0                         ");
        sql.append("   AND SR.FILM_NAME = '"+companyId+"'                         ");
        
        return sql.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getNjListData(String searchData, String companyId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT SNF.ID,                                                       ");
        sql.append("     SNF.STAFF_NAME, JBI.ID_NUMBER, JBI.BORTH_DATE,                           ");
        sql.append("     JBI.SEX, SNF.DEPT_LEVEL,SNF.SFLB,SNF.IS_STOP,SNF.LAST_YEAR_TOTAL,    ");
        sql.append("     SNF.END_AGE,SNF.JZSNMNL,SNF.QXXJFZJBL,SNF.QXXJFNLXS,SNF.QXXJFJE,   ");
        sql.append("     SNF.JBJFZJXS,SNF.JBJFNLXS,SNF.JBJFJE,SNF.TOTAL_MONEY, SNF.STAFF_ID               ");
        sql.append("   FROM JC_SALARY_NJ_FORMULA SNF                                        ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                   ");
        sql.append("   ON SNF.STAFF_ID = JBI.ID                                             ");
        sql.append("   WHERE SNF.IS_DELETE = 0                                              ");
        sql.append("   AND SNF.COMPANY_ID = '"+companyId+"'                                 ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND SNF.STAFF_NAME LIKE '%"+mapData.get("name").trim()+"%'                                 ");
            }
        }
        sql.append("   ORDER BY SNF.CREATEDATE ASC                                          ");

        // 获取汇总结果SQL
        SQLQuery summaryQuery = getSession().createSQLQuery(getTotalDatas(companyId));
        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> summaryList = summaryQuery.list();

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffName", obj[1]);
            map.put("idCard", obj[2]);
            map.put("birthDate", obj[3]);
            map.put("sex", obj[4]);
            map.put("deptLevel", obj[5]);
            map.put("sflb", obj[6]);
            map.put("isStop", obj[7]);
            map.put("lastYearTotal", obj[8]);
            map.put("endAge", obj[9]);
            map.put("jzsnmnl", obj[10]);
            map.put("qxxjfzjbl", obj[11]);
            map.put("qxxjfnlxs", obj[12]);
            map.put("qxxjfje", obj[13]);
            map.put("jbjfzjxs", obj[14]);
            map.put("jbjfnlxs", obj[15]);
            map.put("jbjfje", obj[16]);
            map.put("totalMoney", obj[17]);
            map.put("staffId", obj[18]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("summary", summaryList);// 汇总计算

        return map;
    }

    @Override
    public String getTotalDatas(String companyId) {
        // 获取汇总结果SQL
        StringBuilder totalSql = new StringBuilder();
        totalSql.append("   SELECT                                                                                                                  ");
        totalSql.append("     SUM(NVL(TO_NUMBER(SNF.LAST_YEAR_TOTAL), 0)) LAST_YEAR_TOTAL,SUM(NVL(TO_NUMBER(SNF.QXXJFZJBL), 0)) QXXJFZJBL,          ");
        totalSql.append("     SUM(NVL(TO_NUMBER(SNF.QXXJFNLXS), 0)) QXXJFNLXS,SUM(NVL(TO_NUMBER(SNF.QXXJFJE), 0)) QXXJFJE,SUM(NVL(TO_NUMBER(SNF.JBJFZJXS), 0)) JBJFZJXS,    ");
        totalSql.append("     SUM(NVL(TO_NUMBER(SNF.JBJFNLXS), 0)) JBJFNLXS, SUM(NVL(TO_NUMBER(SNF.JBJFJE), 0)) JBJFJE,SUM(NVL(TO_NUMBER(SNF.TOTAL_MONEY), 0)) TOTAL_MONEY,  ");
        totalSql.append("     MIN(SNT.ID) ID, MIN(SNT.COMPANY_ID) COMPANYID, MIN(B.FZX) COMPANYNAME, MIN(SNT.QYJFBL) QYJFBL,COUNT(SNF.ID) ACOUNT, SUM(NVL(SNF.IS_STOP, 0)) STOPS ");
        totalSql.append("   FROM JC_SALARY_NJ_TOTAL SNT                                                                                           ");
        totalSql.append("   LEFT JOIN JC_SALARY_NJ_FORMULA SNF                                   ");
        totalSql.append("   ON SNT.COMPANY_ID = SNF.COMPANY_ID                                             ");
//        totalSql.append("   AND SNT.YEAR = SNF.YEAR                                             ");
        totalSql.append("   AND SNF.IS_DELETE = 0                                             ");
        totalSql.append("   LEFT JOIN BRANCH B                                   ");
        totalSql.append("   ON SNT.COMPANY_ID = B.ID                                             ");
        totalSql.append("   WHERE SNT.IS_DELETE = 0                                                                                                 ");
        totalSql.append("   AND SNT.COMPANY_ID  = '"+companyId+"'                                                                                   ");
        totalSql.append("   GROUP BY SNT.COMPANY_ID                                                                                                 ");

        return totalSql.toString();
    }

    @Override
    public String getLastYearAvgSalary(String companyId, int year) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                      ");
        sql.append("       SUM(TO_NUMBER(NVL(MS.AVERAGE_SALARY,0))) AVERAGE_SALARY ");
        sql.append("   FROM XC_MONTH_SALARY MS                                     ");
        sql.append("   LEFT JOIN XC_SALARY_RECORD SR                               ");
        sql.append("   ON MS.STAFF_ID = SR.STAFF_ID                                ");
        sql.append("   WHERE SR.FILM_NAME = ''                                     ");
        sql.append("   AND MS.EFFECTIVE_YEAR = 0                                   ");
        sql.append("   AND MS.IS_DELETE = 0                                        ");

        List<?> list = findBySql(sql.toString());
        if (list.size() > 0) {
            return (String) list.get(0);
        }

        return "0";
    }

}
