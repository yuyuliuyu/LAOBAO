package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryAgainDao;
import com.lingnet.hcm.entity.salary.SalaryAgain;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 二次分配总量主表
 * @ClassName: SalaryAgainDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月21日 下午9:14:43 
 *
 */
@Repository("salaryAgainDao")
public class SalaryAgainDaoImpl extends BaseDaoImplInit<SalaryAgain, String> implements SalaryAgainDao {

    @Resource(name="salaryAssignationService")
    private SalaryAssignationService salaryAssignationService;

    @Override
    public Map<String, Object> getPeriodAmountListData(String searchData, String companyId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                              ");
        sql.append("     PERIODDATA.ID, MIN(PERIODDATA.NAME) PAYPERIOD,         ");
        sql.append("     MIN(AGAIN.REPORT_STATUS) REPORT_STATUS, MIN(AGAIN.IS_SP) IS_SP         ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA       ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN AGAIN  ");
        sql.append("   ON AGAIN.COMPANY_ID = SA.COMPANY_ID ");
        sql.append("   AND AGAIN.FP_DATE = SA.SALARY_PERIOD ");
        sql.append("   AND AGAIN.FPQX = 2 ");
        sql.append("   LEFT JOIN XC_PERIODDATA PERIODDATA  ");
        sql.append("   ON SA.SALARY_PERIOD = PERIODDATA.ID ");
        sql.append("   WHERE SA.IS_DELETE  = 0             ");
        sql.append("   AND AGAIN.IS_DELETE = 0 ");

        // 公司ID
        if (!StringUtils.isBlank(companyId)) {
            sql.append("   AND SA.COMPANY_ID  = '"+companyId+"'             ");
        }

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("payPeriod"))) {
                sql.append("   AND PERIODDATA.NAME LIKE '%"+mapData.get("payPeriod").trim()+"%'                                        ");
            }
            // 上报状态
            if (!StringUtils.isBlank(mapData.get("reportStatus"))) {
                sql.append("   AND NVL(AGAIN.REPORT_STATUS, 0) = "+mapData.get("reportStatus")+"                                        ");
            }
        }
        sql.append("   GROUP BY PERIODDATA.ID            ");
        sql.append("   ORDER BY MIN(PERIODDATA.NAME) DESC       ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("payPeriod", obj[1]);
            map.put("reportStatus", obj[2] == null? 0: obj[2]);
            map.put("isSp", obj[3] == null? 0: obj[3]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getAllSecondAmountListData(String period, String companyId, Pager pager) {
        Map<String, String> mapData = JsonUtil.parseProperties(pager.getSearchData());
//        StringBuilder authSql = new StringBuilder();
//        authSql.append("   SELECT MIN(NVL(AGAIN.REPORT_STATUS, 0)) REPORT_STATUS    ");
//        authSql.append("   FROM QX_DEPARTMENT D                                     ");
//        authSql.append("   LEFT JOIN XC_SALARY_AGAIN AGAIN                          ");
//        authSql.append("   ON D.BARCH_ID = AGAIN.COMPANY_ID                         ");
//        authSql.append("   AND D.ID = AGAIN.DEP_ID                                  ");
//        authSql.append("   AND AGAIN.FP_DATE = '"+period+"'                         ");
//        authSql.append("   WHERE D.BARCH_ID='"+companyId+"'                         ");
//        authSql.append("   AND D.ID = '"+mapData.get("deptId")+"'                   ");
//        authSql.append("   AND AGAIN.FPQX = "+mapData.get("fpqx")+"                   ");
//        authSql.append("   AND D.IS_DELETE =0                                       ");
//        authSql.append("   GROUP BY D.ID                                            ");
//        SQLQuery summaryQuery = getSession().createSQLQuery(authSql.toString());
//        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
//        List<Map<String, Object>> summaryList = summaryQuery.list();

//        pager  = findPagerBySql(pager, getAllSecondAmountListDataSql(period, companyId, pager.getSearchData()));
        List<?> list = findBySql(getAllSecondAmountListDataSql(period, companyId, pager.getSearchData()));
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("jobNumber", obj[2]);
            map.put("name", obj[3]);
            map.put("itemsId", obj[4]);
            map.put("itemsName", obj[5]);
            map.put("staticValue", obj[6]);
            map.put("fpqx", obj[7]);
            map.put("groupName", obj[8]);
            map.put("post", obj[9]);
            map.put("salaryPostName", obj[10]);
            map.put("salaryPost", obj[11]);
            map.put("specialMark", obj[12]);
            map.put("gzxs", obj[13]);
            map.put("actualName", obj[14]);
            map.put("actualAmount", obj[15]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", dataList.size());

        List<SalaryAgain> agin = (List<SalaryAgain>)getSession().createCriteria(SalaryAgain.class)
                .add(Restrictions.eq("companyId", companyId))
                .add(Restrictions.in("depId", mapData.get("deptId").split(",")))
                .add(Restrictions.eq("fpDate", period))
                .add(Restrictions.eq("fpqx", Integer.valueOf(mapData.get("fpqx"))))
                .add(Restrictions.eq("isDelete", 0)).list();
        int status = 0;
        for (SalaryAgain salaryAgain : agin) {
            if (salaryAgain.getReportStatus() == 0) {
                status = 0;
                break;
            } else status = 1;
        }
        map.put("auth", status);

        return map;
    }

    @Override
    public String getAllSecondAmountListDataSql(String period, String companyId, String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                                          ");
        sql.append("   LISTAGG(SAI.ID, ',') WITHIN GROUP (ORDER BY SAI.SX ASC,SAI.SALARY_ITEMS_ID ASC) ID,                             ");
        sql.append("   SAI.STAFF_ID,MIN(SAI.JOB_NUMBER) JOBNUMBER,                                                                     ");
        sql.append("   MIN(SAI.STAFF_NAME) NAME,                                                                                       ");
        sql.append("   LISTAGG(SAI.SALARY_ITEMS_ID, ',') WITHIN GROUP (ORDER BY SAI.SX ASC,SAI.SALARY_ITEMS_ID ASC) ITEMID,            ");
        sql.append("   LISTAGG(SAI.SALARY_ITEMS_NAME, ',') WITHIN GROUP (ORDER BY SAI.SX ASC,SAI.SALARY_ITEMS_ID ASC) ITEMSNAME,       ");
        sql.append("   LISTAGG(SAI.STATIC_VALUE, ',') WITHIN GROUP (ORDER BY SAI.SX ASC,SAI.SALARY_ITEMS_ID ASC) STATIC_VALUE,         ");
        sql.append("   MIN(SAI.FPQX) FPQX, MIN(SAI.SALARY_GROUP_NAME) GROUPNAME,                                                       ");
        sql.append("   MIN(SAI.BZGW) POST, MIN(SAI.JTGW) SPECIFIC_POST, MIN(SAI.XCGW) SALARY_POST,                                     ");
        sql.append("   MIN(SAI.SPECIAL_MARK) SPECIAL_MARK, MIN(SAI.GZXS) GZXS,                                                         ");
        sql.append("   LISTAGG('实际分配', ',') WITHIN GROUP (ORDER BY SAI.SX ASC,SAI.SALARY_ITEMS_ID ASC) NEED_AMOUNT_NAME,           ");
        sql.append("   LISTAGG(NVL(SAI.NEED_AMOUNT, 0), ',') WITHIN GROUP (ORDER BY SAI.SX ASC,SAI.SALARY_ITEMS_ID ASC) NEED_AMOUNT    ");
        sql.append("   FROM XC_SALARY_AGAIN_ITEMS SAI                                                                                  ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN SAGAIN                                                                                ");
        sql.append("   ON SAI.SALARY_AGAIN_ID = SAGAIN.ID                                                                              ");
        sql.append("   LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                                                                ");
        sql.append("   ON SAI.XCGW_ID = SDD.ID                                                                              ");
        sql.append("   WHERE SAGAIN.COMPANY_ID = '"+companyId+"'                                                                                    ");
        sql.append("   AND SAI.IS_DEFAULT_DISPLAY = 1                                                                                         ");
        sql.append("   AND SAI.IS_DELETE = 0                                                                                         ");
        sql.append("   AND SAGAIN.IS_DELETE = 0                                                                                         ");
        sql.append("   AND SAGAIN.FP_DATE = '"+period+"'                                                                                         ");
        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND SAI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND SAI.STAFF_NAME LIKE '%"+mapData.get("name").trim()+"%'                                  ");
            }
            // 部门
//            if (!StringUtils.isBlank(mapData.get("deptId"))) {
            sql.append("   AND SAGAIN.DEP_ID IN('"+StringUtils.join(mapData.get("deptId").split(","), "','")+"') ");
//            }
            // 薪资组
            if (!StringUtils.isBlank(mapData.get("salaryGroup"))) {
                sql.append("   AND SAI.SALARY_GROUP_ID = '"+mapData.get("salaryGroup").trim()+"'                ");
            }
            // 分配权限
            if (!StringUtils.isBlank(mapData.get("fpqx"))) {
                sql.append("   AND SAGAIN.FPQX = '"+mapData.get("fpqx").trim()+"'                                  ");
            }
        }
        sql.append("   GROUP BY SAI.STAFF_ID                                                                                           ");
        sql.append("   ORDER BY MIN(SDD.ZWBM) ASC                                                                ");

        return sql.toString();
    }

    @Override
    public Double getItemTotalValue(String companyId, String deptId, String periodId, String fpqx, String itemId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                       ");
        sql.append("   MIN(ASS.IBF_NAME) IBFNAME,                                   ");
        sql.append("   SUM(TO_NUMBER(NVL(SV.STATIC_VALUE, 0))) STATIC_VALUE         ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA                                ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                        ");
        sql.append("   ON SA.ID  = ASTAFF.SALARY_ASSIGNATION_ID                     ");
        sql.append("   AND ASTAFF.IS_DELETE = 0                                     ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                    ");
        sql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID                      ");
        sql.append("   AND ASS.IS_DELETE = 0                                        ");
        sql.append("   LEFT JOIN XC_SALARY_ITEMS SI                                 ");
        sql.append("   ON ASS.IBF_ID = SI.ID                                        ");
        sql.append("   LEFT JOIN XC_SALARY_VALUE SV                                 ");
        sql.append("   ON SV.STAFF_ID = ASTAFF.STAFF_ID                             ");
        sql.append("   AND SV.SALARY_ITEMS_ID = ASS.IBF_ID                          ");
        sql.append("   AND SV.IS_DELETE = 0                                         ");
        sql.append("   LEFT JOIN XC_SALARY_RECORD SR                                ");
        sql.append("   ON ASTAFF.STAFF_ID = SR.STAFF_ID                             ");
        sql.append("   WHERE SA.COMPANY_ID  = '"+companyId+"'    ");
        sql.append("   AND SA.SALARY_PERIOD = '"+periodId+"'    ");
        sql.append("   AND SI.IS_DISPLAY    = 1                                     ");
        sql.append("   AND SR.DEPTNAME      = '"+deptId+"'    ");
        sql.append("   AND SR.FPQX          = '"+fpqx+"'                            ");
        sql.append("   AND ASS.IBF_ID       = '"+itemId+"'                          ");
        sql.append("   GROUP BY SI.ID                                               ");
        List<?> list = findBySql(sql.toString());
        Double v = 0d;
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            v = Double.valueOf(obj[1].toString());
        }

        return v;
    }

    @Override
    public List<?> getAllSecondForTypeSql(String period, String companyId, String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                      "); 
        sql.append("     LISTAGG(SAI.ID, ',') WITHIN GROUP (ORDER BY ASS.SX ASC) ID,                               ");
        sql.append("     ASTAFF.STAFF_ID,                                                                          ");
        sql.append("     MIN(ASTAFF.JOB_NUMBER) JOBNUMBER,                                                         ");
        sql.append("     MIN(ASTAFF.STAFF_NAME) NAME,                                                              ");
        sql.append("     LISTAGG(ASS.IBF_ID, ',') WITHIN GROUP (ORDER BY ASS.SX ASC) ITEMID,                       ");
        sql.append("     LISTAGG(ASS.IBF_NAME, ',') WITHIN GROUP (ORDER BY ASS.SX ASC) ITEMSNAME,                  ");
        sql.append("     LISTAGG(NVL(ASS.ASSIGNATION_CHARGE, 0), ',') WITHIN GROUP (ORDER BY ASS.SX ASC) CHARGE,   ");
        sql.append("     LISTAGG(NVL(SV.STATIC_VALUE, 0), ',') WITHIN GROUP (ORDER BY ASS.SX ASC) STATIC_VALUE,    ");
        sql.append("     MIN(SR.FPQX) FPQX, MIN(SG.NAME) GROUPNAME,                     ");
        sql.append("     MIN(BZGW.POSITION_NAME) POST, MIN(JTGW.POSITION_NAME) SPECIFIC_POST, MIN(XCGW.NAME) SALARY_POST,   ");
        sql.append("     MIN(NVL(JQE.DIC_NAME,' ')) SPECIAL_MARK, MIN(SR.GZXS) GZXS,   ");
        sql.append("     LISTAGG('实际分配', ',') WITHIN GROUP (ORDER BY ASS.SX ASC) NEED_AMOUNT_NAME,    ");
        sql.append("     LISTAGG(NVL(SAI.NEED_AMOUNT, 0), ',') WITHIN GROUP (ORDER BY ASS.SX ASC) NEED_AMOUNT,    ");
        sql.append("     MIN(SAGIN.ID) SAGIN_ID, MIN(SAGIN.REPORT_STATUS) REPORT_STATUS    ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA                                                               ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP SG                                                                ");
        sql.append("   ON SA.SALARY_GROUP_ID = SG.ID                                                               ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                                                       ");
        sql.append("   ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID                                                     ");
        sql.append("   AND ASTAFF.IS_DELETE = 0                                                     ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                                   ");
        sql.append("   ON ASTAFF.ID      = ASS.ASSIGNATION_STAFF_ID                                                ");
        sql.append("   AND ASS.IS_DELETE = 0                                                                       ");
        sql.append("   LEFT JOIN XC_SALARY_ITEMS SI                                                                ");
        sql.append("   ON ASS.IBF_ID = SI.ID                                                                       ");
        sql.append("   LEFT JOIN XC_SALARY_VALUE SV                                                                ");
        sql.append("   ON SV.STAFF_ID = ASTAFF.STAFF_ID                                                            ");
        sql.append("   AND SV.SALARY_ITEMS_ID = ASS.IBF_ID                                                         ");
        sql.append("   AND SV.IS_DELETE = 0                                                                        ");
        sql.append("   LEFT JOIN XC_SALARY_RECORD SR                                                               ");
        sql.append("   ON ASTAFF.STAFF_ID = SR.STAFF_ID                                                            ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN SAGIN                                                             ");
        sql.append("   ON SAGIN.COMPANY_ID = SA.COMPANY_ID                                                         ");
        sql.append("   AND SAGIN.IS_DELETE = 0                                                                     ");
        sql.append("   AND SAGIN.FPQX = SR.FPQX                                                                    ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN_ITEMS SAI                                                         ");
        sql.append("   ON SAGIN.ID = SAI.SALARY_AGAIN_ID                                                           ");
        sql.append("   AND ASS.IBF_ID = SAI.SALARY_ITEMS_ID                                                        ");
        sql.append("   AND ASTAFF.STAFF_ID = SAI.STAFF_ID                                                          ");
        sql.append("   AND SAI.IS_DELETE = 0                                                                       ");
        sql.append("  LEFT JOIN DEPT_POSITION JTGW                                                                 ");
        sql.append("    ON JTGW.ID = SR.SPECIFIC_POST                                                              ");
        sql.append("  LEFT JOIN POST_POSITION BZGW                                                                 ");
        sql.append("    ON BZGW.ID = SR.POST                                                              ");
        sql.append("  LEFT JOIN XC_SALARY_DEPT_DICTIONARY XCGW                                                                 ");
        sql.append("    ON XCGW.ID = SR.SALARY_POST                                                              ");
        sql.append("  LEFT JOIN JC_QX_DECTIONARY JQE                                                                 ");
        sql.append("    ON JQE.DIC_VALUE = TO_CHAR(SR.SPECIAL_MARK)                                                              ");
        sql.append("   AND JQE.PID = '402881945be55610015be55f9d750010'                                                          ");
        sql.append("   WHERE SA.COMPANY_ID = '"+companyId+"'                                                       ");
        sql.append("   AND SA.SALARY_PERIOD = '"+period+"'                                                         ");
        sql.append("   AND SI.IS_DISPLAY = 1                                                                       ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
             // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND ASTAFF.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND ASTAFF.STAFF_NAME LIKE '%"+mapData.get("name").trim()+"%'                                  ");
            }
            // 部门
            if (!StringUtils.isBlank(mapData.get("deptId"))) {
                sql.append("   AND SR.DEPTNAME = '"+mapData.get("deptId")+"'                                  ");
            }
            // 薪资组
            if (!StringUtils.isBlank(mapData.get("salaryGroup"))) {
                sql.append("   AND SG.ID = '"+mapData.get("salaryGroup").trim()+"'                                  ");
            }
            // 分配权限
            if (!StringUtils.isBlank(mapData.get("fpqx"))) {
                sql.append("   AND SR.FPQX = '"+mapData.get("fpqx").trim()+"'                                  ");
            }
        }
        sql.append("   GROUP BY ASTAFF.STAFF_ID                                                                 ");

        return findBySql(sql.toString());
    }

    @Override
    public List<Map<String, Object>> getAllStaffSalaryGroup(String companyId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT SG.ID, MIN(SG.NAME) NAME       ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA         ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP SG          ");
        sql.append("   ON SA.SALARY_GROUP_ID = SG.ID         ");
        sql.append("   WHERE SA.COMPANY_ID = '"+companyId+"' ");
        sql.append("   AND SA.IS_DELETE = 0                  ");
        sql.append("   GROUP BY SG.ID                        ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public List<Map<String, Object>> getPeriodStaffInfo(String companyId, String period) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT LISTAGG(ASS.ID, ',') WITHIN GROUP (ORDER BY ASS.SX ASC) ID,              ");
        sql.append("      ASTAFF.STAFF_ID,                                                              ");
        sql.append("      LISTAGG(SAI.ID, ',') WITHIN GROUP (ORDER BY ASS.SX ASC) NEEDID,               ");
        sql.append("      LISTAGG(SAI.NEED_AMOUNT, ',') WITHIN GROUP (ORDER BY ASS.SX ASC) NEED_AMOUNT  ");
        sql.append("    FROM XC_SALARY_ASSIGNATION SA                                                   ");
        sql.append("    LEFT JOIN XC_SALARY_GROUP SG                                                    ");
        sql.append("    ON SA.SALARY_GROUP_ID = SG.ID                                                   ");
        sql.append("    LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                                           ");
        sql.append("    ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID                                         ");
        sql.append("    LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                       ");
        sql.append("    ON ASTAFF.ID      = ASS.ASSIGNATION_STAFF_ID                                    ");
        sql.append("    AND ASS.IS_DELETE = 0                                                           ");
        sql.append("    LEFT JOIN XC_SALARY_ITEMS SI                                                    ");
        sql.append("    ON ASS.IBF_ID = SI.ID                                                           ");
        sql.append("    LEFT JOIN XC_SALARY_VALUE SV                                                    ");
        sql.append("    ON SV.STAFF_ID         = ASTAFF.STAFF_ID                                        ");
        sql.append("    AND SV.SALARY_ITEMS_ID = ASS.IBF_ID                                             ");
        sql.append("    AND SV.IS_DELETE       = 0                                                      ");
        sql.append("    LEFT JOIN XC_SALARY_RECORD SR                                                   ");
        sql.append("    ON ASTAFF.STAFF_ID = SR.STAFF_ID                                                ");
        sql.append("    LEFT JOIN XC_SALARY_AGAIN SAGIN                                                 ");
        sql.append("    ON SAGIN.COMPANY_ID = SA.COMPANY_ID                                             ");
        sql.append("    AND SAGIN.IS_DELETE = 0                                                         ");
        sql.append("    AND SAGIN.FPQX      = SR.FPQX                                                   ");
        sql.append("    LEFT JOIN XC_SALARY_AGAIN_ITEMS SAI                                             ");
        sql.append("    ON SAGIN.ID         = SAI.SALARY_AGAIN_ID                                       ");
        sql.append("    AND ASS.IBF_ID      = SAI.SALARY_ITEMS_ID                                       ");
        sql.append("    AND ASTAFF.STAFF_ID = SAI.STAFF_ID                                              ");
        sql.append("    AND SAI.IS_DELETE   = 0                                                         ");
        sql.append("    WHERE SA.COMPANY_ID  = '"+companyId+"'                                          ");
        sql.append("    AND SA.SALARY_PERIOD = '"+period+"'                                             ");
        sql.append("    AND SI.IS_DISPLAY    = 1                                                        ");
        sql.append("    AND SR.FPQX IN (1, 2)                                                           ");
        sql.append("    GROUP BY ASTAFF.STAFF_ID                                                        ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("salaryAgainItems", obj[2]);
            map.put("needAmount", obj[3]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String getPerTypeSecondData(String companyId, String period, String fpqx, String deptId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                 ");
        sql.append("     WM_CONCAT(distinct SAI.SALARY_ITEMS_NAME) IBF_NAME,           ");
        sql.append("     SUM(NVL(SAI.STATIC_VALUE, 0)) STATIC_VALUE,               ");
        sql.append("     SUM(NVL(SAI.NEED_AMOUNT, 0)) NEED_AMOUNT,                 ");
        sql.append("     GWAY.ZLJS                 ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA                          ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN SAGIN                        ");
        sql.append("   ON SAGIN.COMPANY_ID = SA.COMPANY_ID                    ");
        sql.append("   AND SA.SALARY_PERIOD = SAGIN.FP_DATE                   ");
        sql.append("   AND SAGIN.SALARY_ASSIGNATION_ID = SA.ID                ");
        sql.append("   AND SAGIN.FP_DATE = SA.SALARY_PERIOD                   ");
        sql.append("   AND SAGIN.IS_DELETE  = 0                               ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN_ITEMS SAI                    ");
        sql.append("   ON SAGIN.ID = SAI.SALARY_AGAIN_ID                      ");
        sql.append("   AND SAI.FPQX = SAGIN.FPQX                              ");
//        sql.append("   AND SA.SALARY_GROUP_ID = SAI.SALARY_GROUP_ID           ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW                     ");
        sql.append("   ON SA.SALARY_WAGE_ID   = SGW.ID                        ");
//        sql.append("   AND SA.SALARY_GROUP_ID = SGW.SALARY_GROUP_ID           ");
        sql.append("   AND SGW.IS_DELETE = 0                                  ");
        sql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                  ");
        sql.append("   ON SGW.ID = GWAY.WAGE_ID                               ");
        sql.append("   AND SA.SALARY_WAGE_ID = GWAY.WAGE_ID                   ");
        sql.append("   AND SAI.SALARY_ITEMS_ID = GWAY.ITEM_ID                 ");
        sql.append("   AND GWAY.IS_DELETE = 0                                 ");
        sql.append("   WHERE SA.COMPANY_ID = '"+companyId+"'                  ");
        sql.append("   AND SA.SALARY_PERIOD = '"+period+"'                    ");
        sql.append("   AND SAGIN.DEP_ID IN('"+StringUtils.join(deptId.split(","), "','")+"')  ");
        sql.append("   AND SAGIN.FPQX = '"+fpqx+"'                            ");
        sql.append("   AND SA.IS_DELETE = 0                                   ");
        sql.append("   AND SAI.IS_DELETE   = 0                                ");
        sql.append("   GROUP BY GWAY.ZLJS                                     ");
        sql.append("   ORDER BY MIN(SAI.SX) ASC                               ");

        return sql.toString();
    }

    @Override
    public String getOtherAmountValue(String companyId, String period,
            String fpqx, String deptId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                        ");
        sql.append("     SUM(TO_NUMBER(NVL(NEED_AMOUNT, 0))) AMOUNT  ");
        sql.append("   FROM XC_SALARY_TOTAL_AMOUNT STA               ");
        sql.append("   LEFT JOIN XC_SALARY_AMOUNT_ITEM SAI           ");
        sql.append("   ON STA.ID = SAI.TOTAL_AMOUNT_ID               ");
        sql.append("   AND SAI.IS_DELETE = 0                         ");
        sql.append("   AND STA.DEP_ID = SAI.DEP_ID                   ");
        sql.append("   WHERE STA.IS_DELETE = 0                       ");
        sql.append("   AND STA.COMPANY_ID = '"+companyId+"'                       ");
        sql.append("   AND STA.DEP_ID IN('"+StringUtils.join(deptId.split(","), "','")+"')  ");
        sql.append("   AND STA.FP_DATE = '"+period+"'                          ");
        sql.append("   AND STA.FPQX = "+fpqx+"                              ");

        return sql.toString();
    }

}
