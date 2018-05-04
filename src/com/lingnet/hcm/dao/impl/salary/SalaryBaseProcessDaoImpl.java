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
import com.lingnet.hcm.dao.salary.SalaryBaseProcessDao;
import com.lingnet.hcm.entity.salary.SalaryBaseProcess;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 缴费基数过程表
 * @ClassName: SalaryBaseProcessDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月19日 下午4:23:02 
 *
 */
@Repository("salaryBaseProcessDao")
public class SalaryBaseProcessDaoImpl extends BaseDaoImplInit<SalaryBaseProcess, String> implements SalaryBaseProcessDao {

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
    public String getCanSbPeoples(String depts, String areaId, String fuli) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT                                                  ");
        sql.append("       SR.STAFF_ID,JBI.NAME,JA.NAME AREA,II.IBF_ID,NVL(SI.BASE_COMPANY_MATH, '0') BASE_COMPANY_MATH,   ");
        sql.append("       NVL(SI.BASE_PERSONAL_MATH, '0') BASE_PERSONAL_MATH  ");
        sql.append("     FROM XC_SALARY_RECORD SR                                ");
        sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI                      ");
        sql.append("     ON SR.STAFF_ID =JBI.ID                                  ");
        sql.append("     LEFT JOIN XC_INSURANCE_MIDDLE IM                        ");
        sql.append("     ON SR.STAFF_ID = IM.STAFF_ID                            ");
        sql.append("     AND IM.IS_DELETE = 0                                    ");
        sql.append("     LEFT JOIN XC_INSURANCE_ITEMS II                         ");
        sql.append("     ON IM.ID = II.INSURANCE_MIDDLE_ID                       ");
        sql.append("     AND II.IS_DELETE = 0                                    ");
        sql.append("     LEFT JOIN XC_SALARY_INSURANCE SI                        ");
        sql.append("     ON II.IBF_ID = SI.NAME                                  ");
        sql.append("     AND SI.IS_DELETE = 0                                    ");
        sql.append("     LEFT JOIN BRANCH B                      ");
        sql.append("     ON SR.FILM_NAME = B.ID                  ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD              ");
        sql.append("     ON QD.ID = SR.DEPTNAME                  ");
        sql.append("     LEFT JOIN JC_PAY_AREAS JPA                      ");
        sql.append("     ON II.AREA = JPA.ID                             ");
        sql.append("     LEFT JOIN JC_AREAS JA                           ");
        sql.append("     ON JPA.AREA_CITY = JA.ID                        ");
        sql.append("     WHERE JBI.IS_DELETE =0                                  ");
        sql.append("     AND (B.ID IN ('"+StringUtils.join(depts.split(","), "','")+"') ");
        sql.append("        OR QD.ID IN ('"+StringUtils.join(depts.split(","), "','")+"')) ");

        // 缴费地域
        String[] areas = areaId.split(",");
        sql.append("  AND II.AREA IN ('"+StringUtils.join(areas, "','")+"')                         ");

        // 福利
        String[] fulis = fuli.split(",");
        sql.append("  AND II.IBF_ID IN ('"+StringUtils.join(fulis, "','")+"')                         ");
        sql.append("     ORDER BY SR.STAFF_ID ASC                                ");

        return sql.toString();
    }

    @Override
    public Map<String, Object> getJsBaseSalaryDatas(String companyId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT                                                                                 ");
        sql.append("     SPP.ID,MIN(SPP.NAME) name,WM_CONCAT(DISTINCT PPC.COMPANY_ID) JFCOMPANYID,WM_CONCAT(DISTINCT PPA.AREA_ID) JFAREAID,");
        sql.append("     WM_CONCAT(DISTINCT BRANCH.FZX) JFCOMPANY,WM_CONCAT(DISTINCT AREAS.NAME) JFAREA,");
        sql.append("     WM_CONCAT(DISTINCT PPF.FULI_ID) FULIID,WM_CONCAT(DISTINCT PPD.DEPT_ID) DEPT_ID,");
        sql.append("     WM_CONCAT(DISTINCT JIB.INSUR_NAME) FULI,WM_CONCAT(DISTINCT NVL(JOINCOM.FZX,DEPT.NAME)) JOINCOM,          ");
        sql.append("     MIN(SPP.EFFECTIVE_YEAR) year,MIN(SPP.EFFECTIVE_MONTH) month,                           ");
        sql.append("     MIN(SPP.NOTE) note                            ");
        sql.append("     FROM XC_SALARY_BASE_PROCESS SPP                                                     ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_COMPANY PPC                                               ");
        sql.append("     ON SPP.ID= PPC.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPC.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN BRANCH                                                                       ");
        sql.append("     ON PPC.COMPANY_ID = BRANCH.ID                                                          ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_AREA PPA                                                  ");
        sql.append("     ON SPP.ID= PPA.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPA.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_PAY_AREAS JPA                                                               ");
        sql.append("     ON PPA.AREA_ID = JPA.ID                                                              ");
        sql.append("     LEFT JOIN JC_AREAS AREAS                                                               ");
        sql.append("     ON JPA.AREA_CITY = AREAS.ID                                                              ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_FULI PPF                                                  ");
        sql.append("     ON SPP.ID= PPF.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPF.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                       ");
        sql.append("     ON PPF.FULI_ID = JIB.ID                                                                 ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_DEPT PPD                                                  ");
        sql.append("     ON SPP.ID= PPD.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPD.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN BRANCH JOINCOM                                                               ");
        sql.append("     ON PPD.DEPT_ID = JOINCOM.ID                                                            ");
        sql.append("     LEFT JOIN QX_DEPARTMENT DEPT                                                           ");
        sql.append("     ON PPD.DEPT_ID = DEPT.ID                                                               ");
        sql.append("     LEFT JOIN XC_BASE_PAYMENT PP                                                           ");
        sql.append("     ON PP.SALARY_PAYMENT_PROCESS_ID = SPP.ID                                                  ");
        sql.append("     AND PP.IS_DELETE  = 0                                                  ");
        sql.append("     LEFT JOIN XC_BASE_ITEMS PI                                                           ");
        sql.append("     ON PP.ID = PI.BASE_PAYMENT_ID                                                  ");
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
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map<String, Object> getBaseJfProcessData(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT                                                                                 ");
        sql.append("     SPP.ID,MIN(SPP.NAME) name,WM_CONCAT(DISTINCT PPC.COMPANY_ID) JFCOMPANYID,WM_CONCAT(DISTINCT PPA.AREA_ID) JFAREAID,");
        sql.append("     WM_CONCAT(DISTINCT BRANCH.FZX) JFCOMPANY,WM_CONCAT(DISTINCT AREAS.NAME) JFAREA,");
        sql.append("     WM_CONCAT(DISTINCT PPF.FULI_ID) FULIID,WM_CONCAT(DISTINCT PPD.DEPT_ID) DEPT_ID,");
        sql.append("     WM_CONCAT(DISTINCT JIB.INSUR_NAME) FULI,WM_CONCAT(DISTINCT NVL(JOINCOM.FZX,DEPT.NAME)) JOINCOM,          ");
        sql.append("     MIN(SPP.EFFECTIVE_YEAR) year,MIN(SPP.EFFECTIVE_MONTH) month,                           ");
        sql.append("     MIN(SPP.NOTE) note                            ");
        sql.append("     FROM XC_SALARY_BASE_PROCESS SPP                                                     ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_COMPANY PPC                                               ");
        sql.append("     ON SPP.ID= PPC.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPC.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN BRANCH                                                                       ");
        sql.append("     ON PPC.COMPANY_ID = BRANCH.ID                                                          ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_AREA PPA                                                  ");
        sql.append("     ON SPP.ID= PPA.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPA.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_PAY_AREAS JPA                                                               ");
        sql.append("     ON PPA.AREA_ID = JPA.ID                                                              ");
        sql.append("     LEFT JOIN JC_AREAS AREAS                                                               ");
        sql.append("     ON JPA.AREA_CITY = AREAS.ID                                                              ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_FULI PPF                                                  ");
        sql.append("     ON SPP.ID= PPF.SALARY_PAYMENT_PROCESS_ID                                               ");
        sql.append("     AND PPF.IS_DELETE = 0                                               ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                       ");
        sql.append("     ON PPF.FULI_ID = JIB.ID                                                                 ");
        sql.append("     LEFT JOIN XC_BASE_PROCESS_DEPT PPD                                                  ");
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

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getJsBaseSalaryListDatas(String id, String fuli, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                                                        ");
        sql.append("    MIN(PP.ID) ID,MIN(JBI.JOB_NUMBER) JOBNUMBER,MIN(JBI.NAME) NAME,MIN(PP.IS_DAIJIAO) ISDAIJIAO,  ");
        sql.append("    MIN(PP.JF_YEAR) YEAR,MIN(PP.JF_MONTH) MONTH,MIN(PP.PJGZ) PJGZ,                                ");
        sql.append("    WM_CONCAT(JIB.ID) FULIID,                                                                     ");
        sql.append("    WM_CONCAT(JIB.INSUR_NAME) FULINAME,                                                           ");
        sql.append("    WM_CONCAT(PI.AREA) AREA,                                                                      ");
        sql.append("    WM_CONCAT(PI.BASE_COMPANY) BASECOMPANY,                                                       ");
        sql.append("    WM_CONCAT(PI.BASE_PERSONAL) BASEPERSONAL,                                                      ");
        sql.append("    MIN(BRANCH.FZX) FZX,                      ");
        sql.append("    MIN(QD.NAME) DEPTNAME,                    ");
        sql.append("    MIN(JBI.id) staffId,                    ");
        sql.append("    MIN(PI.ID) PI_ID                    ");
        sql.append("    FROM XC_BASE_PAYMENT PP                                                                    ");
        sql.append("    LEFT JOIN XC_BASE_ITEMS PI                                                             ");
        sql.append("    ON PP.ID = PI.BASE_PAYMENT_ID                                                       ");
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
        sql.append("    GROUP BY JBI.ID, PP.CREATEDATE                                                               ");
        sql.append("    ORDER BY PP.CREATEDATE DESC                                                                  ");

        pager  = findPagerBySql(pager, sql.toString());

        // 查找福利项目
        String[] fulis = fuli.split(",");
        SQLQuery query = getSession().createSQLQuery("SELECT ID, INSUR_NAME FROM JC_INSURANCE_BENEFITS WHERE ID IN ('"+StringUtils.join(fulis, "','")+"') ORDER BY CREATEDATE ASC");
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
            map.put("baseCompany", obj[10]);
            map.put("basePersonal", obj[11]);
            map.put("company", obj[12]);
            map.put("deptName", obj[13]);
            map.put("staffId", obj[14]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("fulis", allFls);

        return map;
    }

    @Override
    public String getAllStaffBasesDataSql(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                                             ");
        sql.append("      BI.ID,BP.STAFF_ID,JA.NAME AREA,NVL(SI.BASE_COMPANY_MATH, '0') BASE_COMPANY_MATH, ");
        sql.append("      NVL(SI.BASE_PERSONAL_MATH, '0') BASE_PERSONAL_MATH,SI.PAY_CARRY_RULE,SI.NUMBER_ACCURACY,  ");
        sql.append("      BP.ID BID  ");
        sql.append("    FROM XC_BASE_PAYMENT BP                                                            ");
        sql.append("    LEFT JOIN XC_BASE_ITEMS BI                                                         ");
        sql.append("    ON BP.ID = BI.BASE_PAYMENT_ID                                                      ");
        sql.append("    AND BI.IS_DELETE = 0                                                               ");
        sql.append("    RIGHT JOIN XC_INSURANCE_MIDDLE IM                                                  ");
        sql.append("    ON BP.STAFF_ID = IM.STAFF_ID                                                       ");
        sql.append("    RIGHT JOIN XC_INSURANCE_ITEMS II                                                   ");
        sql.append("    ON IM.ID = II.INSURANCE_MIDDLE_ID                                                  ");
        sql.append("    AND BI.IBF_ID = II.IBF_ID                                                          ");
        sql.append("    LEFT JOIN XC_SALARY_INSURANCE SI                                                   ");
        sql.append("    ON BI.IBF_ID = SI.NAME                                                             ");
        sql.append("    LEFT JOIN JC_PAY_AREAS JPA                                                         ");
        sql.append("    ON II.AREA = JPA.ID                                                                ");
        sql.append("    LEFT JOIN JC_AREAS JA                                                              ");
        sql.append("    ON JPA.AREA_CITY = JA.ID                                                           ");
        sql.append("    WHERE BP.IS_DELETE = 0                                                             ");
        sql.append("    AND BP.SALARY_PAYMENT_PROCESS_ID = '"+id+"'                                        ");
        sql.append("    ORDER BY BP.STAFF_ID ASC                                                           ");

        return sql.toString();
    }

    @Override
    public Map<String, Object> getChoseBasePersonalListData(String id,
            String ids, int year, int month, Pager pager) {
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
        sql.append("     WHERE                                        ");
        sql.append("     II.IS_DELETE = 0 AND                         ");
        sql.append("     "+year+" >= II.YEAR AND "+month+" >= II.MONTH AND  ");
        sql.append("     (CASE WHEN II.IS_END = 1 THEN II.END_YEAR    ");
        sql.append("        ELSE "+year+"-1 END                       ");
        sql.append("     ) < "+year+"                                 ");
        sql.append("     AND                                          ");
        sql.append("     (CASE WHEN II.IS_END = 1 THEN II.END_MONTH   ");
        sql.append("        ELSE "+month+"-1 END                      ");
        sql.append("     ) < "+month+"                                ");

        // 生效年月
        sql.append("     AND SI.YEAR <= "+year+" AND SI.MONTH <= "+month);

        // 不在本保险缴费的员工之内的员工
        sql.append(" AND SR.STAFF_ID NOT IN ");
        sql.append(" (SELECT PP.STAFF_ID FROM XC_BASE_PAYMENT PP WHERE PP.SALARY_PAYMENT_PROCESS_ID = '"+id+"' ");
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

}
