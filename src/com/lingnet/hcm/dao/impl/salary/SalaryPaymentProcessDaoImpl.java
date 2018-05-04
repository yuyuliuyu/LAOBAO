package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryPaymentProcessDao;
import com.lingnet.hcm.entity.salary.SalaryPaymentProcess;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 保险缴费
 * @ClassName: SalaryPaymentProcessDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年4月26日 上午11:42:20 
 *
 */
@Repository("salaryPaymentProcessDao")
public class SalaryPaymentProcessDaoImpl extends BaseDaoImplInit<SalaryPaymentProcess, String> implements SalaryPaymentProcessDao {

    @Override
    public Map<String, Object> getPayMentListData(String companyId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT                                                                                 ");
        sql.append("     SPP.ID,MIN(SPP.NAME) name,WM_CONCAT(DISTINCT PPC.COMPANY_ID) JFCOMPANYID,WM_CONCAT(DISTINCT PPA.AREA_ID) JFAREAID,");
        sql.append("     WM_CONCAT(DISTINCT BRANCH.FZX) JFCOMPANY,WM_CONCAT(DISTINCT AREAS.NAME) JFAREA,");
        sql.append("     WM_CONCAT(DISTINCT PPF.FULI_ID) FULIID,WM_CONCAT(DISTINCT PPD.DEPT_ID) DEPT_ID,");
        sql.append("     WM_CONCAT(DISTINCT JIB.INSUR_NAME) FULI,WM_CONCAT(DISTINCT NVL(JOINCOM.FZX,DEPT.NAME)) JOINCOM,          ");
        sql.append("     MIN(SPP.EFFECTIVE_YEAR) year,MIN(SPP.EFFECTIVE_MONTH) month,                           ");
        sql.append("     MIN(SPP.NOTE) note,                            ");
        sql.append("     SUM(TO_NUMBER(NVL(PI.PAYMENT_COMPANY, 0))) SUMCOM,                            ");
        sql.append("     SUM(TO_NUMBER(NVL(PI.PAYMENT_PERSONAL, 0))) SUMPER                            ");
        sql.append("     FROM XC_SALARY_PAYMENT_PROCESS SPP                                                     ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_COMPANY PPC                                               ");
        sql.append("     ON SPP.ID= PPC.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPC.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN BRANCH                                                                       ");
        sql.append("     ON PPC.COMPANY_ID = BRANCH.ID                                                          ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_AREA PPA                                                  ");
        sql.append("     ON SPP.ID= PPA.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPA.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_PAY_AREAS JPA                                                               ");
        sql.append("     ON PPA.AREA_ID = JPA.ID                                                              ");
        sql.append("     LEFT JOIN JC_AREAS AREAS                                                               ");
        sql.append("     ON JPA.AREA_CITY = AREAS.ID                                                              ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_FULI PPF                                                  ");
        sql.append("     ON SPP.ID= PPF.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPF.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                       ");
        sql.append("     ON PPF.FULI_ID = JIB.ID                                                                 ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_DEPT PPD                                                  ");
        sql.append("     ON SPP.ID= PPD.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPD.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN BRANCH JOINCOM                                                               ");
        sql.append("     ON PPD.DEPT_ID = JOINCOM.ID                                                            ");
        sql.append("     LEFT JOIN QX_DEPARTMENT DEPT                                                           ");
        sql.append("     ON PPD.DEPT_ID = DEPT.ID                                                               ");
        sql.append("     LEFT JOIN XC_PROCESS_PAYMENT PP                                                           ");
        sql.append("     ON PP.SALARY_PAYMENT_PROCESS_ID = SPP.ID                                                  ");
        sql.append("     AND PP.IS_DELETE  = 0                                                  ");
        sql.append("     LEFT JOIN XC_PAYMENT_INSURANCE PI                                                           ");
        sql.append("     ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                                  ");
        sql.append("     AND PI.IBF_ID = JIB.ID                                                  ");
        sql.append("     AND PI.IS_DELETE = 0                                                  ");
        sql.append("     WHERE SPP.IS_DELETE = 0                                                               ");

        // 参与计算的组织
        if (!StringUtils.isBlank(companyId)) {
            sql.append("       AND (JOINCOM.ID = '"+companyId+"' OR DEPT.ID = '"+companyId+"')                     ");
        }
        sql.append("     GROUP BY SPP.ID,SPP.EFFECTIVE_YEAR,SPP.EFFECTIVE_MONTH                                 ");
        sql.append("     ORDER BY SPP.EFFECTIVE_YEAR DESC,SPP.EFFECTIVE_MONTH DESC                              ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("companyId", obj[2]);
            map.put("companyName", obj[4]);
            map.put("areaId", obj[3]);
            map.put("areaName", obj[5]);
            map.put("fuliId", obj[6]);
            map.put("fuliName", obj[8]);
            map.put("deptId", obj[7]);
            map.put("deptName", obj[9]);
            map.put("effectDate", obj[10]+"年"+obj[11]+"月");
            map.put("note", obj[12]);
            map.put("sumCom", obj[13]);
            map.put("sumPer", obj[14]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Map<String, Object> getJfProcessData(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT                                                                                 ");
        sql.append("     SPP.ID,MIN(SPP.NAME) name,WM_CONCAT(DISTINCT PPC.COMPANY_ID) JFCOMPANYID,WM_CONCAT(DISTINCT PPA.AREA_ID) JFAREAID,");
        sql.append("     WM_CONCAT(DISTINCT BRANCH.FZX) JFCOMPANY,WM_CONCAT(DISTINCT AREAS.NAME) JFAREA,");
        sql.append("     WM_CONCAT(DISTINCT PPF.FULI_ID) FULIID,WM_CONCAT(DISTINCT PPD.DEPT_ID) DEPT_ID,");
        sql.append("     WM_CONCAT(DISTINCT JIB.INSUR_NAME) FULI,WM_CONCAT(DISTINCT NVL(JOINCOM.FZX,DEPT.NAME)) JOINCOM,          ");
        sql.append("     MIN(SPP.EFFECTIVE_YEAR) year,MIN(SPP.EFFECTIVE_MONTH) month,                           ");
        sql.append("     MIN(SPP.NOTE) note                            ");
        sql.append("     FROM XC_SALARY_PAYMENT_PROCESS SPP                                                     ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_COMPANY PPC                                               ");
        sql.append("     ON SPP.ID= PPC.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPC.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN BRANCH                                                                       ");
        sql.append("     ON PPC.COMPANY_ID = BRANCH.ID                                                          ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_AREA PPA                                                  ");
        sql.append("     ON SPP.ID= PPA.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPA.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_PAY_AREAS JPA                                                               ");
        sql.append("     ON PPA.AREA_ID = JPA.ID                                                              ");
        sql.append("     LEFT JOIN JC_AREAS AREAS                                                               ");
        sql.append("     ON JPA.AREA_CITY = AREAS.ID                                                              ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_FULI PPF                                                  ");
        sql.append("     ON SPP.ID= PPF.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPF.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                       ");
        sql.append("     ON PPF.FULI_ID = JIB.ID                                                                 ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_DEPT PPD                                                  ");
        sql.append("     ON SPP.ID= PPD.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPD.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN BRANCH JOINCOM                                                               ");
        sql.append("     ON PPD.DEPT_ID = JOINCOM.ID                                                            ");
        sql.append("     LEFT JOIN QX_DEPARTMENT DEPT                                                           ");
        sql.append("     ON PPD.DEPT_ID = DEPT.ID                                                               ");
        sql.append("     WHERE SPP.ID = '"+id+"'                                                               ");
        sql.append("     GROUP BY SPP.ID                                                                ");

        List list = findBySql(sql.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        if (list.size() > 0) {
            List<Object[]> listObj = list;
            Object[] obj = listObj.get(0);
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("companyId", obj[2]);
            map.put("companyName", obj[4]);
            map.put("areaId", obj[3]);
            map.put("areaName", obj[5]);
            map.put("fuliId", obj[6]);
            map.put("fuliName", obj[8]);
            map.put("deptId", obj[7]);
            map.put("deptName", obj[9]);
            map.put("effectiveYear", obj[10]);
            map.put("effectiveMonth", obj[11]);
            map.put("note", obj[12]);
        }
        map.put("processStatus", 0);

        // 判断分配过程在此薪酬区间已经存在
        if (null != map.get("companyId")) {
            StringBuilder periodsql = new StringBuilder();
            periodsql.append("   SELECT                                                                        ");
            periodsql.append("   COUNT(SA.ID) ID  ");
            periodsql.append("   FROM XC_SALARY_ASSIGNATION SA                                                 ");
            periodsql.append("   LEFT JOIN XC_PERIODDATA PERIODDATA                                            ");
            periodsql.append("   ON SA.SALARY_PERIOD = PERIODDATA.ID                                           ");
            periodsql.append("   WHERE SA.IS_DELETE = 0                                           ");
            periodsql.append("     AND SA.COMPANY_ID IN ('"+StringUtils.join(map.get("companyId").toString().split(","), "','")+"')   ");
            periodsql.append("     AND TO_CHAR(PERIODDATA.START_DATE, 'yyyy-MM') = TO_CHAR(TO_DATE('"+map.get("effectiveYear")+"-"+map.get("effectiveMonth")+"', 'yyyy-MM'), 'yyyy-MM')   ");
            periodsql.append("     AND (SA.ALLOW_EDIT = 0 OR (SA.IS_SP = 1 OR SA.IS_SP = 3)) ");
            List periodList = findBySql(periodsql.toString());
            if (periodList.size() > 0) {
                Object obj = periodList.get(0);
                map.put("processStatus", Integer.valueOf(obj.toString()) == 0 ? 0 : 1);
            }
        }

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getInsuranceItemsListData(String id, String fuli, String searchData, Pager pager) {
        String sql = getAllStaffJnData(id, searchData);
        pager  = findPagerBySql(pager, sql);

        // 查找福利项目
        String[] fulis = fuli.split(",");
        SQLQuery query = getSession().createSQLQuery("SELECT ID, INSUR_NAME FROM JC_INSURANCE_BENEFITS WHERE ID IN ('"+StringUtils.join(fulis, "','")+"') ORDER BY CREATEDATE ASC");
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> allFls = query.list();

        // 汇总计算
        StringBuilder summarySql = new StringBuilder();
        summarySql.append("    SELECT                                                                     ");
        summarySql.append("    WM_CONCAT(JIB.ID) FULIID, WM_CONCAT(SUM(TO_NUMBER(NVL(PI.PAYMENT_COMPANY, 0)))) SUMCOM, ");
        summarySql.append("    WM_CONCAT(SUM(TO_NUMBER(NVL(PI.PAYMENT_PERSONAL, 0)))) SUMPER   ");
        summarySql.append("    FROM XC_PROCESS_PAYMENT PP                                                                    ");
        summarySql.append("    LEFT JOIN XC_PAYMENT_INSURANCE PI                                                             ");
        summarySql.append("    ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                                       ");
        summarySql.append("    LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                           ");
        summarySql.append("    ON PI.IBF_ID = JIB.ID                                                                         ");
        summarySql.append("    WHERE  PP.IS_DELETE  = 0                    ");
        summarySql.append("    AND PI.IS_DELETE = 0                       ");
        summarySql.append("    AND PP.SALARY_PAYMENT_PROCESS_ID = '"+id+"'                       ");
        summarySql.append("    GROUP BY JIB.ID                       ");
        SQLQuery summaryQuery = getSession().createSQLQuery(summarySql.toString());
        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> summaryList = summaryQuery.list();

        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("isDaiJiao", obj[3]);
            map.put("jfDate", obj[4]+"年"+obj[5]+"月");
            map.put("pjgz", obj[6]);
            map.put("flId", obj[7]);
            map.put("jfFuliName", obj[8]);
            map.put("jfArea", obj[9]);
            map.put("jfCompany", obj[10]);
            map.put("baseCompany", obj[11]);
            map.put("basePersonal", obj[12]);
            map.put("sCompany", obj[13]);
            map.put("sPersonal", obj[14]);
            map.put("company", obj[15]);
            map.put("deptName", obj[16]);
            map.put("staffId", obj[17]);
            map.put("isPayBack", obj[21]);
            map.put("payBackYear", obj[22]);
            map.put("payBackMonth", obj[23]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("fulis", allFls);
        map.put("summary", summaryList);

        return map;
    }

    @Override
    public String getAllStaffJnData(String id, String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                                                        ");
        sql.append("    MIN(PP.ID) ID,MIN(JBI.JOB_NUMBER) JOBNUMBER,MIN(JBI.NAME) NAME,MIN(PP.IS_DAIJIAO) ISDAIJIAO,  ");
        sql.append("    MIN(PP.JF_YEAR) YEAR,MIN(PP.JF_MONTH) MONTH,MIN(PP.PJGZ) PJGZ,                                ");
        sql.append("    WM_CONCAT(JIB.ID) FULIID,                                                                     ");
        sql.append("    WM_CONCAT(JIB.INSUR_NAME) FULINAME,                                                           ");
        sql.append("    WM_CONCAT(PI.AREA) AREA,                                                                      ");
        sql.append("    WM_CONCAT(PI.JF_COMPANY) JFCOMPANY,                                                              ");
        sql.append("    WM_CONCAT(PI.BASE_COMPANY) BASECOMPANY,                                                       ");
        sql.append("    WM_CONCAT(PI.BASE_PERSONAL) BASEPERSONAL,                                                      ");
        sql.append("    WM_CONCAT(PI.PAYMENT_COMPANY) PAYMENT_COMPANY,                                                       ");
        sql.append("    WM_CONCAT(PI.PAYMENT_PERSONAL) PAYMENT_PERSONAL,                                                      ");
        sql.append("    MIN(BRANCH.FZX) FZX,                      ");
        sql.append("    MIN(QD.NAME) DEPTNAME,                    ");
        sql.append("    MIN(JBI.id) staffId,                    ");
        sql.append("    MIN(PI.ID) PI_ID,                    ");
        sql.append("    WM_CONCAT(SI.BI_COMPANY) BI_COMPANY,        ");
        sql.append("    WM_CONCAT(SI.BI_PERSONAL) BI_PERSONAL,        ");
        sql.append("    MIN(PP.IS_PAY_BACK) IS_PAY_BACK,MIN(PP.PAY_BACK_YEAR) PAY_BACK_YEAR,MIN(PP.PAY_BACK_MONTH) PAY_BACK_MONTH ");
        sql.append("    FROM XC_PROCESS_PAYMENT PP                                                                    ");
        sql.append("    LEFT JOIN XC_PAYMENT_INSURANCE PI                                                             ");
        sql.append("    ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                                       ");
        sql.append("    LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                           ");
        sql.append("    ON PI.IBF_ID = JIB.ID                                                                         ");
        sql.append("    LEFT JOIN XC_SALARY_RECORD SR                                                            ");
        sql.append("    ON SR.STAFF_ID    = PP.STAFF_ID                                                               ");
        sql.append("    LEFT JOIN JC_BASIC_INFORMATION JBI                                                            ");
        sql.append("    ON SR.STAFF_ID    = JBI.ID                                                                    ");
        sql.append("    LEFT JOIN QX_DEPARTMENT QD                      ");
        sql.append("    ON QD.ID = SR.DEPTNAME                        ");
        sql.append("    LEFT JOIN BRANCH                                ");
        sql.append("    ON SR.FILM_NAME = BRANCH.ID                      ");
        sql.append("    LEFT JOIN XC_SALARY_INSURANCE SI                           ");
        sql.append("    ON JIB.ID = SI.NAME                        ");
        sql.append("    WHERE  PP.IS_DELETE  = 0                    ");
        sql.append("    AND PI.IS_DELETE = 0                       ");
        sql.append("    AND PP.SALARY_PAYMENT_PROCESS_ID = '"+id+"'                       ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 部门ID
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                String[] deptArray = mapData.get("depId").split(",");
                sql.append("  AND JBI.DEPART_ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                        ");
            }
        }
        sql.append("    GROUP BY JBI.ID, PP.IS_PAY_BACK, PP.CREATEDATE                                                               ");
        sql.append("    ORDER BY PP.CREATEDATE DESC                                                                  ");

        return sql.toString();
    }

    @Override
    public Map<String, Object> getPersonalToAllocationListData(String id, String ids, int year, int month, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT IM.STAFF_ID,                           ");
        sql.append("       MIN(JBI.JOB_NUMBER) jobNumber,MIN(JBI.NAME) name , MIN(BRANCH.FZX) company, MIN(QD.NAME) deptName,   ");
        sql.append("       MIN(BZGW.POSITION_NAME) post, MIN(JBI.ON_JOB) onJob, MIN(JBI.ON_POST) onPost,MIN(IM.IS_DAIJIAO) IS_DAIJIAO,              ");
        sql.append("       WM_CONCAT(SI.NAME) FULIID,                 ");
        sql.append("       WM_CONCAT(JIB.INSUR_NAME) FULINAME,        ");
        sql.append("       WM_CONCAT(JA.NAME) AREA,        ");
        sql.append("       WM_CONCAT(B.FZX) JFCOMPANY,        ");
        sql.append("       WM_CONCAT(II.BASE_COMPANY) baseCompany,        ");
        sql.append("       WM_CONCAT(II.BASE_PERSONAL) basePersonal,        ");
        sql.append("       WM_CONCAT(II.PAYMENT_COMPANY) PAYMENT_COMPANY,           ");
        sql.append("       WM_CONCAT(II.PAYMENT_PERSONAL) PAYMENT_PERSONAL           ");
        sql.append("     FROM XC_INSURANCE_MIDDLE IM                  ");
        sql.append("     LEFT JOIN XC_INSURANCE_ITEMS II              ");
        sql.append("     ON IM.ID = II.INSURANCE_MIDDLE_ID            ");
        sql.append("     AND II.IS_DELETE = 0                            ");
        sql.append("     LEFT JOIN BRANCH B              ");
        sql.append("     ON II.JF_COMPANY = B.ID            ");
        sql.append("     LEFT JOIN XC_SALARY_RECORD SR                     ");
        sql.append("     ON SR.STAFF_ID = IM.STAFF_ID                   ");
        sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI              ");
        sql.append("     ON JBI.ID = SR.STAFF_ID            ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD              ");
        sql.append("     ON QD.ID = SR.DEPTNAME            ");
        sql.append("     LEFT JOIN BRANCH              ");
        sql.append("     ON SR.FILM_NAME = BRANCH.ID            ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB             ");
        sql.append("     ON II.IBF_ID = JIB.ID                           ");
        sql.append("     LEFT JOIN JC_PAY_AREAS JPA                      ");
        sql.append("     ON II.AREA = JPA.ID                             ");
        sql.append("     LEFT JOIN JC_AREAS JA                           ");
        sql.append("     ON JPA.AREA_CITY = JA.ID                        ");
        sql.append("     LEFT JOIN XC_SALARY_INSURANCE SI                           ");
        sql.append("     ON JIB.ID = SI.NAME                        ");
        sql.append("     LEFT JOIN POST_POSITION BZGW                    ");
        sql.append("     ON BZGW.ID = SR.POST                            ");
        sql.append("     LEFT JOIN JC_EMP_DIMISSION JED                   ");
        sql.append("     ON JBI.JOB_NUMBER = JED.JOB_NUMBER               ");
        sql.append("     WHERE                                        ");
        sql.append("     II.IS_DELETE = 0 AND JBI.IS_DELETE = 0 AND      ");
        sql.append("     "+year+" >= II.YEAR AND "+month+" >= II.MONTH AND  ");
        sql.append("     (CASE WHEN II.IS_END = 1 THEN II.END_YEAR    ");
        sql.append("        ELSE "+year+"-1 END                       ");
        sql.append("     ) < "+year+"                                 ");
        sql.append("     AND                                          ");
        sql.append("     (CASE WHEN II.IS_END = 1 THEN II.END_MONTH   ");
        sql.append("        ELSE "+month+"-1 END                      ");
        sql.append("     ) < "+month+"                                ");
        sql.append("    AND CASE WHEN JBI.ON_JOB = 3 THEN TO_CHAR (NVL(JED.LEAVE_DATE, SYSDATE),'yyyy-MM')     ");// 离职人员离职月可以显示，下一个月不显示
        sql.append("        ELSE TO_CHAR(SYSDATE, 'yyyy-MM') END >= TO_CHAR(SYSDATE, 'yyyy-MM')     ");

        // 生效年月
        sql.append("     AND SI.YEAR <= "+year+" AND SI.MONTH <= "+month);

        // 不在本保险缴费的员工之内的员工
        sql.append(" AND SR.STAFF_ID NOT IN ");
        sql.append(" (SELECT PP.STAFF_ID FROM XC_PROCESS_PAYMENT PP WHERE PP.SALARY_PAYMENT_PROCESS_ID = '"+id+"' ");
        sql.append("   AND PP.IS_DELETE = 0) ");

        // 参与组织（公司或者部门）
        if (!StringUtils.isBlank(ids)) {
            sql.append("     AND (BRANCH.ID IN ('"+StringUtils.join(ids.split(","), "','")+"') ");
            sql.append("        OR QD.ID IN ('"+StringUtils.join(ids.split(","), "','")+"')) ");
        }

        Map<String, String> mapData = JsonUtil.parseProperties(pager.getSearchData());
        if (null != mapData) {
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("  AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                         ");
            }
            // 姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("  AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                         ");
            }

            // 参与计算的福利
            if (!StringUtils.isBlank(mapData.get("fuli"))) {
                String[] fulis = mapData.get("fuli").split(",");
                sql.append("  AND II.IBF_ID IN ('"+StringUtils.join(fulis, "','")+"')                         ");
            }
        }
        sql.append("     GROUP BY IM.STAFF_ID,IM.CREATEDATE           ");
        sql.append("     ORDER BY IM.CREATEDATE ASC                   ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("company", obj[3]);
            map.put("deptName", obj[4]);
            map.put("post", obj[5]);
            map.put("onPost", obj[7]);
            map.put("idDaiJiao", obj[8]);
            map.put("flId", obj[9]);
            map.put("flName", obj[10]);
            map.put("jfArea", obj[11]);
            map.put("jfCompany", obj[12]);
            map.put("baseCompany", obj[13]);
            map.put("basePersonal", obj[14]);
            map.put("sCompany", obj[15]);
            map.put("sPersonal", obj[16]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public List<Map<String, Object>> getCanJoinStaffsListData(String ids, int year, int month) {
        String sql = getCanSbPeoples("", ids, year, month, "{}");

        List<?> list = findBySql(sql);
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("company", obj[3]);
            map.put("deptName", obj[4]);
            map.put("jfDate", year+"年"+month+"月");
            map.put("post", obj[5]);
            map.put("onPost", obj[7]);
            map.put("isDaiJiao", obj[8]);
            map.put("flId", obj[9]);
            map.put("flName", obj[10]);
            map.put("jfArea", obj[11]);
            map.put("jfCompany", obj[12]);
            map.put("baseCompany", obj[13]);
            map.put("basePersonal", obj[14]);
            map.put("sCompany", obj[15]);
            map.put("sPersonal", obj[16]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String getCanSbPeoples(String depts, String staffIds, int year, int month, String searchdata) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT IM.STAFF_ID,                           ");
        sql.append("       MIN(JBI.JOB_NUMBER) jobNumber,MIN(JBI.NAME) name , MIN(BRANCH.FZX) company, MIN(QD.NAME) deptName,   ");
        sql.append("       MIN(BZGW.POSITION_NAME) post, MIN(JBI.ON_JOB) onJob, MIN(JBI.ON_POST) onPost,MIN(IM.IS_DAIJIAO) IS_DAIJIAO,              ");
        sql.append("       WM_CONCAT(SI.NAME) FULIID,                 ");
        sql.append("       WM_CONCAT(JIB.INSUR_NAME) FULINAME,        ");
        sql.append("       WM_CONCAT(JA.NAME) AREA,        ");
        sql.append("       WM_CONCAT(B.FZX) JFCOMPANY,        ");
        sql.append("       WM_CONCAT(II.BASE_COMPANY) baseCompany,        ");
        sql.append("       WM_CONCAT(II.BASE_PERSONAL) basePersonal,        ");
        sql.append("       WM_CONCAT(II.PAYMENT_COMPANY) PAYMENT_COMPANY,           ");
        sql.append("       WM_CONCAT(II.PAYMENT_PERSONAL) PAYMENT_PERSONAL           ");
        sql.append("     FROM XC_INSURANCE_MIDDLE IM                  ");
        sql.append("     LEFT JOIN XC_INSURANCE_ITEMS II              ");
        sql.append("     ON IM.ID = II.INSURANCE_MIDDLE_ID            ");
        sql.append("     AND II.IS_DELETE = 0                            ");
        sql.append("     LEFT JOIN BRANCH B              ");
        sql.append("     ON II.JF_COMPANY = B.ID            ");
        sql.append("     LEFT JOIN XC_SALARY_RECORD SR                     ");
        sql.append("     ON SR.STAFF_ID = IM.STAFF_ID                   ");
        sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI              ");
        sql.append("     ON JBI.ID = SR.STAFF_ID            ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD              ");
        sql.append("     ON QD.ID = SR.DEPTNAME            ");
        sql.append("     LEFT JOIN BRANCH              ");
        sql.append("     ON SR.FILM_NAME = BRANCH.ID            ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB             ");
        sql.append("     ON II.IBF_ID = JIB.ID                           ");
        sql.append("     LEFT JOIN JC_PAY_AREAS JPA                      ");
        sql.append("     ON II.AREA = JPA.ID                             ");
        sql.append("     LEFT JOIN JC_AREAS JA                           ");
        sql.append("     ON JPA.AREA_CITY = JA.ID                        ");
        sql.append("     LEFT JOIN XC_SALARY_INSURANCE SI                           ");
        sql.append("     ON JIB.ID = SI.NAME                        ");
        sql.append("     LEFT JOIN POST_POSITION BZGW                    ");
        sql.append("     ON BZGW.ID = SR.POST                            ");
        sql.append("     LEFT JOIN JC_EMP_DIMISSION JED                      ");
        sql.append("     ON JBI.JOB_NUMBER = JED.JOB_NUMBER                  ");
        sql.append("     WHERE                                        ");
        sql.append("     II.IS_DELETE = 0 AND JBI.IS_DELETE = 0 AND     ");
        sql.append("     "+year+" >= II.YEAR AND "+month+" >= II.MONTH AND  ");
        sql.append("     (CASE WHEN II.IS_END = 1 THEN II.END_YEAR    ");
        sql.append("        ELSE "+year+"-1 END                       ");
        sql.append("     ) < "+year+"                                 ");
        sql.append("     AND                                          ");
        sql.append("     (CASE WHEN II.IS_END = 1 THEN II.END_MONTH   ");
        sql.append("        ELSE "+month+"-1 END                      ");
        sql.append("     ) < "+month+"                                ");
        sql.append("    AND CASE WHEN JBI.ON_JOB = 3 THEN TO_CHAR (NVL(JED.LEAVE_DATE, SYSDATE),'yyyy-MM')     ");// 离职人员离职月可以显示，下一个月不显示
        sql.append("        ELSE TO_CHAR(SYSDATE, 'yyyy-MM') END >= TO_CHAR(SYSDATE, 'yyyy-MM')     ");

        // 生效年月
        sql.append("     AND SI.YEAR <= "+year+" AND SI.MONTH <= "+month);

        // 参与组织（公司或者部门）
        if (!StringUtils.isBlank(depts)) {
            sql.append("     AND (BRANCH.ID IN ('"+StringUtils.join(depts.split(","), "','")+"') ");
            sql.append("        OR QD.ID IN ('"+StringUtils.join(depts.split(","), "','")+"')) ");
        }

        // 员工ID
        if (!StringUtils.isBlank(staffIds)) {
            sql.append("     AND JBI.ID IN ('"+StringUtils.join(staffIds.split(","), "','")+"') ");
        }

        Map<String, String> map = JsonUtil.parseProperties(searchdata);
        if (null != map) {
            // 工号
            if (!StringUtils.isBlank(map.get("jobNumber"))) {
                sql.append("  AND JBI.JOB_NUMBER LIKE '%"+map.get("jobNumber").trim()+"%'                         ");
            }
            // 姓名
            if (!StringUtils.isBlank(map.get("name"))) {
                sql.append("  AND JBI.NAME LIKE '%"+map.get("name").trim()+"%'                         ");
            }

            // 参与计算的福利
            if (!StringUtils.isBlank(map.get("fuli"))) {
                String[] fulis = map.get("fuli").split(",");
                sql.append("  AND II.IBF_ID IN ('"+StringUtils.join(fulis, "','")+"')                         ");
            }
        }
        sql.append("     GROUP BY IM.STAFF_ID,IM.CREATEDATE           ");
        sql.append("     ORDER BY IM.CREATEDATE ASC                   ");

        return sql.toString();
    }

    @Override
    public String getSampleOrgAndAreaData(String id, String org, String area, int year, int month) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT                                                                                 ");
        sql.append("     COUNT(SPP.ID) ID                            ");
        sql.append("     FROM XC_SALARY_PAYMENT_PROCESS SPP                                                     ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_COMPANY PPC                                               ");
        sql.append("     ON SPP.ID= PPC.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPC.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_AREA PPA                                                  ");
        sql.append("     ON SPP.ID= PPA.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPA.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_FULI PPF                                                  ");
        sql.append("     ON SPP.ID= PPF.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPF.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN XC_PAYMENT_PROCESS_DEPT PPD                                                  ");
        sql.append("     ON SPP.ID= PPD.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPD.IS_DELETE = 0                                               ");
        sql.append("     WHERE SPP.EFFECTIVE_YEAR = "+year+" AND SPP.EFFECTIVE_MONTH = "+month);
        sql.append("       AND SPP.IS_DELETE = 0");
        String[] orgs = org.split(",");
        sql.append("  AND PPD.DEPT_ID IN ('"+StringUtils.join(orgs, "','")+"')                         ");
        String[] areas = area.split(",");
        sql.append("  AND PPA.AREA_ID IN ('"+StringUtils.join(areas, "','")+"')                         ");

        // 不与自身比较
        if (!StringUtils.isBlank(id)) {
            sql.append("  AND SPP.ID <> '"+id +"'                         ");
        }

        Object query = this.getSession().createSQLQuery(sql.toString()).uniqueResult();

        return Integer.valueOf(query.toString()) > 0 ? "fail" : "success";
    }

    @Override
    public List<Map<String, Object>> getStaffPaymentData(String id, String staffId) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                                                           ");
        sql.append("    PI.ID,II.IBF_ID, II.AREA,BRANCH.FZX,II.JF_ACCOUNT,II.BASE_COMPANY,II.BASE_PERSONAL,             ");
        sql.append("    II.YEAR,II.MONTH, PI.PAYMENT_COMPANY, PI.PAYMENT_PERSONAL, PP.IS_PAY_BACK,");
        sql.append("    PP.PAY_BACK_YEAR, PP.PAY_BACK_MONTH");
        sql.append("    FROM XC_INSURANCE_MIDDLE IM                                                                      ");
        sql.append("    LEFT JOIN XC_INSURANCE_ITEMS II                                                                  ");
        sql.append("    ON IM.ID = II.INSURANCE_MIDDLE_ID                                                                ");
        sql.append("    LEFT JOIN BRANCH                                                                                 ");
        sql.append("    ON II.JF_COMPANY = BRANCH.ID                                                                     ");
        sql.append("    RIGHT JOIN XC_PROCESS_PAYMENT PP                                                                 ");
        sql.append("    ON PP.STAFF_ID = IM.STAFF_ID                                                                     ");
        sql.append("    RIGHT JOIN XC_PAYMENT_INSURANCE PI                                                               ");
        sql.append("    ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                                          ");
        sql.append("    AND II.IBF_ID = PI.IBF_ID                                                          ");
        sql.append("    WHERE                                                                                            ");
        sql.append("        IM.STAFF_ID ='"+staffId+"'                                                                   ");
        sql.append("      AND PP.ID ='"+id+"'                                                                   ");
        sql.append("      AND PP.IS_DELETE = 0                                                                   ");
        sql.append("      AND II.IS_DELETE = 0                                                                           ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("ibfId", obj[1]);
            map.put("area", obj[2]);
            map.put("jfCompany", obj[3]);
            map.put("jfAccount", obj[4]);
            map.put("baseCompany", obj[5]);
            map.put("basePersonal", obj[6]);
            map.put("year", obj[7]);
            map.put("month", obj[8]);
            map.put("paymentCompany", obj[9]);
            map.put("paymentPersonal", obj[10]);
            map.put("isPayBack", obj[11]);
            map.put("payBackYear", obj[12]);
            map.put("payBackMonth", obj[13]);
            dataList.add(map);
        }

        return dataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getStaffPaymentHistory(String staffId, int year, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                                                        ");
        sql.append("    MIN(PP.ID) ID,MIN(JBI.JOB_NUMBER) JOBNUMBER,MIN(JBI.NAME) NAME,MIN(PP.IS_DAIJIAO) ISDAIJIAO,  ");
        sql.append("    MIN(PP.JF_YEAR) YEAR,MIN(PP.JF_MONTH) MONTH,MIN(PP.PJGZ) PJGZ,                                ");
        sql.append("    WM_CONCAT(JIB.ID) FULIID,                                                                     ");
        sql.append("    WM_CONCAT(JIB.INSUR_NAME) FULINAME,                                                           ");
        sql.append("    WM_CONCAT(PI.AREA) AREA,                                                                      ");
        sql.append("    WM_CONCAT(PI.JF_COMPANY) JFCOMPANY,                                                              ");
        sql.append("    WM_CONCAT(PI.BASE_COMPANY) BASECOMPANY,                                                       ");
        sql.append("    WM_CONCAT(PI.BASE_PERSONAL) BASEPERSONAL,                                                      ");
        sql.append("    WM_CONCAT(PI.PAYMENT_COMPANY) PAYMENT_COMPANY,                                                       ");
        sql.append("    WM_CONCAT(PI.PAYMENT_PERSONAL) PAYMENT_PERSONAL,                                                      ");
        sql.append("    MIN(BRANCH.FZX) FZX,                      ");
        sql.append("    MIN(QD.NAME) DEPTNAME,                    ");
        sql.append("    MIN(JBI.id) staffId,                    ");
        sql.append("    MIN(PI.ID) PI_ID,                    ");
        sql.append("    WM_CONCAT(SI.BI_COMPANY) BI_COMPANY,        ");
        sql.append("    WM_CONCAT(SI.BI_PERSONAL) BI_PERSONAL,        ");
        sql.append("    MIN(PP.IS_PAY_BACK) IS_PAY_BACK,MIN(PP.PAY_BACK_YEAR) PAY_BACK_YEAR,MIN(PP.PAY_BACK_MONTH) PAY_BACK_MONTH ");
        sql.append("    FROM XC_PROCESS_PAYMENT PP                                                                    ");
        sql.append("    LEFT JOIN XC_PAYMENT_INSURANCE PI                                                             ");
        sql.append("    ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                                       ");
        sql.append("    LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                           ");
        sql.append("    ON PI.IBF_ID = JIB.ID                                                                         ");
        sql.append("    LEFT JOIN JC_BASIC_INFORMATION JBI                                                            ");
        sql.append("    ON PP.STAFF_ID    = JBI.ID                                                                    ");
        sql.append("    LEFT JOIN QX_DEPARTMENT QD                      ");
        sql.append("    ON QD.ID = JBI.DEPART_ID                        ");
        sql.append("    LEFT JOIN BRANCH                                ");
        sql.append("    ON JBI.FILM_ID = BRANCH.ID                      ");
        sql.append("    LEFT JOIN XC_SALARY_INSURANCE SI                           ");
        sql.append("    ON JIB.ID = SI.NAME                        ");
        sql.append("    WHERE  PP.IS_DELETE  = 0                    ");
        sql.append("    AND PI.IS_DELETE = 0                       ");
        sql.append("    AND JBI.ID = '"+staffId+"'                       ");
        sql.append("    AND PP.JF_YEAR = "+year+"                       ");
        sql.append("    GROUP BY PP.JF_YEAR,PP.IS_PAY_BACK,PP.JF_MONTH                                               ");
        sql.append("    ORDER BY PP.JF_YEAR DESC,PP.JF_MONTH DESC                                     ");

        pager  = findPagerBySql(pager, sql.toString());

        // 查找福利项目
        SQLQuery query = getSession().createSQLQuery("SELECT ID, INSUR_NAME FROM JC_INSURANCE_BENEFITS ORDER BY CREATEDATE ASC");
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> allFls = query.list();

        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("isDaiJiao", obj[3]);
            map.put("jfDate", obj[4]+"年"+obj[5]+"月");
            map.put("pjgz", obj[6]);
            map.put("flId", obj[7]);
            map.put("jfFuliName", obj[8]);
            map.put("jfArea", obj[9]);
            map.put("jfCompany", obj[10]);
            map.put("baseCompany", obj[11]);
            map.put("basePersonal", obj[12]);
            map.put("sCompany", obj[13]);
            map.put("sPersonal", obj[14]);
            map.put("company", obj[15]);
            map.put("deptName", obj[16]);
            map.put("staffId", obj[17]);
            map.put("isPayBack", obj[21]);
            map.put("payBackYear", obj[22]);
            map.put("payBackMonth", obj[23]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("fulis", allFls);

        return map;
    }

    @Override
    public List<Map<String, Object>> getAllStaffPaymentsData(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("      SELECT WM_CONCAT(PI.ID) ID,                                                  ");
        sql.append("        WM_CONCAT(II.BASE_COMPANY) baseCompany,        ");
        sql.append("        WM_CONCAT(II.BASE_PERSONAL) basePersonal,      ");
        sql.append("        WM_CONCAT(II.PAYMENT_COMPANY) PAYMENT_COMPANY,                       ");
        sql.append("        WM_CONCAT(II.PAYMENT_PERSONAL) PAYMENT_PERSONAL,                     ");
        sql.append("        WM_CONCAT(SI.PAY_CARRY_RULE) PAY_CARRY_RULE,                      ");
        sql.append("        WM_CONCAT(SI.NUMBER_ACCURACY) NUMBER_ACCURACY                      ");
        sql.append("      FROM XC_PROCESS_PAYMENT PP                                             ");
        sql.append("      LEFT JOIN XC_PAYMENT_INSURANCE PI                                      ");
        sql.append("      ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                ");
        sql.append("      LEFT JOIN JC_BASIC_INFORMATION JBI                                     ");
        sql.append("      ON PP.STAFF_ID = JBI.ID                                                ");
        sql.append("      RIGHT JOIN XC_INSURANCE_MIDDLE IM                                       ");
        sql.append("      ON PP.STAFF_ID = IM.STAFF_ID                                           ");
        sql.append("      RIGHT JOIN XC_INSURANCE_ITEMS II                                        ");
        sql.append("      ON IM.ID                         = II.INSURANCE_MIDDLE_ID              ");
        sql.append("      AND PI.IBF_ID = II.IBF_ID                                              ");
        sql.append("      LEFT JOIN XC_SALARY_INSURANCE SI               ");
        sql.append("      ON PI.IBF_ID = SI.NAME                         ");
        sql.append("      WHERE PP.IS_DELETE               = 0                                   ");
        sql.append("      AND PI.IS_DELETE                 = 0                                   ");
        sql.append("      AND PP.SALARY_PAYMENT_PROCESS_ID = '"+id+"'      ");
        sql.append("      GROUP BY JBI.ID                                                        ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("baseCompany", obj[1]);
            map.put("basePersonal", obj[2]);
            map.put("sCompany", obj[3]);
            map.put("sPersonal", obj[4]);
            map.put("payCarryRule", obj[5]);
            map.put("xs", obj[6]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public Map<String, Object> getPaymentceilMathData(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                                         ");
        sql.append("  PI.ID, SI.PAY_CARRY_RULE, SI.NUMBER_ACCURACY   ");
        sql.append("  FROM XC_PAYMENT_INSURANCE PI                   ");
        sql.append("  LEFT JOIN XC_SALARY_INSURANCE SI               ");
        sql.append("  ON PI.IBF_ID = SI.NAME                         ");
        sql.append("  WHERE PI.ID != '"+id+"'                        ");

        List<?> list = findBySql(sql.toString());
        Map<String, Object> data = new HashMap<String, Object>();
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            data.put("id", obj[0]);
            data.put("payCarryRule", obj[1]);
            data.put("xs", obj[2]);
        }

        return data;
    }

}
