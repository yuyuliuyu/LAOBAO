package com.lingnet.hcm.dao.impl.salary;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryAssignationDao;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.salary.Period;
import com.lingnet.hcm.entity.salary.Perioddata;
import com.lingnet.hcm.entity.salary.SalaryAssignation;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;

/**
 * 薪酬分配过程
 * @ClassName: SalaryAssignationDaoImpl 
 * @Description: 薪酬分配过程 
 * @author zhanghj
 * @date 2017年5月10日 上午9:58:58 
 *
 */
@Repository("salaryAssignationDao")
public class SalaryAssignationDaoImpl extends BaseDaoImplInit<SalaryAssignation, String> implements SalaryAssignationDao {

    @Resource(name="salaryAssignationService")
    private SalaryAssignationService salaryAssignationService;

    @Override
    public Map<String, Object> getSalaryAssignationListData(String companyId, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                        ");
        sql.append("   SA.ID, SA.NAME,SG.NAME GROUPNAME,SGW.NAME WAGENAME,PERIODDATA.NAME PAYPERIOD,  ");
        sql.append("   SA.IS_SP, SA.IS_FAFANG,B.FZX  ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA                                                 ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP SG                                                  ");
        sql.append("   ON SA.SALARY_GROUP_ID = SG.ID                                                 ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW                                            ");
        sql.append("   ON SA.SALARY_WAGE_ID = SGW.ID                                                 ");
        sql.append("   AND SGW.IS_DELETE = 0                                                         ");
        sql.append("   LEFT JOIN XC_PERIODDATA PERIODDATA                                            ");
        sql.append("   ON SA.SALARY_PERIOD = PERIODDATA.ID                                           ");
        sql.append("   LEFT JOIN BRANCH B                                            ");
        sql.append("   ON SA.COMPANY_ID = B.ID                                           ");
        sql.append("   WHERE SA.IS_DELETE = 0                                           ");

        // 公司ID
        sql.append("   AND SA.COMPANY_ID IN ('"+StringUtils.join(getAuthCompany(), "','")+"') ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("payPeriod"))) {
                sql.append("   AND PERIODDATA.NAME LIKE '%"+mapData.get("payPeriod").trim()+"%'  ");
            }
            // 已提交状态
            if (!StringUtils.isBlank(mapData.get("isSp"))) {
                sql.append("   AND SA.IS_SP = "+mapData.get("isSp").trim()+"                                        ");
            }
        }
        sql.append("   ORDER BY SA.CREATEDATE DESC                                                   ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("groupName", obj[2]);
            map.put("wageName", obj[3]);
            map.put("payPeriod", obj[4]);
            map.put("isSp", obj[5]);
            map.put("isFafang", obj[6]);
            map.put("companyName", obj[7]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public Map<String, Object> getSalaryAssignationForAuthListData(String userName, String searchData, Pager pager) {
        StringBuilder authSql = new StringBuilder();
        authSql.append("    SELECT                             ");
        authSql.append("      WFC.ID,WFC.APRO_ID,WFC.APRO_MAN  ");
        authSql.append("    FROM JC_WORK_FLOW WF               ");
        authSql.append("    LEFT JOIN JC_WORK_FLOWC WFC        ");
        authSql.append("      ON WF.ID = WFC.PID               ");
        authSql.append("    LEFT JOIN JC_QX_DECTIONARY JQD        ");
        authSql.append("      ON WF.WORK_FLOW_TYPE = JQD.DIC_VALUE               ");
        authSql.append("     AND JQD.PID = '4028818d5b7046b2015b707328fa0031'               ");
        authSql.append("    WHERE WF.WORK_FLOW_STATE = 1       ");
        authSql.append("      AND JQD.DIC_VALUE = '4' ");// 薪酬审批
        authSql.append("      AND WFC.APRO_ID = '"+userName+"'");

        List<?> list = findBySql(authSql.toString());
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
        }

        List<HashMap<String, Object>> checkDataList = new ArrayList<HashMap<String, Object>>();
        if (map.get("jobNumber") != null) {
            StringBuilder checkSql = new StringBuilder();
            checkSql.append("   SELECT SA.ID,                                                  ");
            checkSql.append("     MIN(SA.NAME) name,                                           ");
            checkSql.append("     MIN(SG.NAME) GROUPNAME,                                      ");
            checkSql.append("     MIN(SGW.NAME) WAGENAME,                                      ");
            checkSql.append("     MIN(PERIODDATA.NAME) PAYPERIOD,                              ");
            checkSql.append("     LISTAGG(AR.APRO_MAN, '-->') WITHIN GROUP (ORDER BY AR.SORT ASC) APRO_MAN,                             ");
            checkSql.append("     LISTAGG(AR.IS_SP, ',') WITHIN GROUP (ORDER BY AR.SORT ASC) IS_SP,                           ");
            checkSql.append("     LISTAGG(AR.APRO_ID, ',') WITHIN GROUP (ORDER BY AR.SORT ASC) APRO_ID,                          ");
            checkSql.append("     MIN(SA.IS_SP) CHECK_STATUS, MIN(B.FZX) COMPANYNAME,                                   ");
            checkSql.append("     MIN(B.ID) CompanyId, MIN(SA.SALARY_GROUP_ID) SALARY_GROUP_ID,                         ");
            checkSql.append("     MIN(SA.SALARY_RATE) SALARY_RATE, MIN(SA.SALARY_PERIOD) SALARY_PERIOD,                 ");
            checkSql.append("     MIN(SA.SALARY_WAGE_ID) SALARY_WAGE_ID                                   ");
            checkSql.append("   FROM XC_SALARY_ASSIGNATION SA                                  ");
            checkSql.append("   LEFT JOIN XC_SALARY_GROUP SG                                   ");
            checkSql.append("   ON SA.SALARY_GROUP_ID = SG.ID                                  ");
            checkSql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW                             ");
            checkSql.append("   ON SA.SALARY_WAGE_ID = SGW.ID                                  ");
            checkSql.append("   AND SGW.IS_DELETE    = 0                                       ");
            checkSql.append("   LEFT JOIN XC_PERIODDATA PERIODDATA                             ");
            checkSql.append("   ON SA.SALARY_PERIOD = PERIODDATA.ID                            ");
            checkSql.append("   left join                                                      ");
            checkSql.append("   (                                                              ");
            checkSql.append("     SELECT                                                       ");
            checkSql.append("       MIN(AR.SALARY_ASSIGNATION_ID) SALARY_ASSIGNATION_ID,       ");
            checkSql.append("       MIN(WF.APRO_MAN) APRO_MAN,                                 ");
            checkSql.append("       MAX(NVL(AR.IS_SP, -1)) IS_SP,                              ");
            checkSql.append("       WF.APRO_ID, MIN(AR.NOTE) NOTE,MIN(WF.SORT) SORT            ");
            checkSql.append("       FROM JC_WORK_FLOWC WF                                      ");
            checkSql.append("       LEFT JOIN XC_ASSIGNATION_RECORD AR                         ");
            checkSql.append("       ON WF.APRO_ID = ar.ASSIGNATION_ACCOUNT                     ");
            checkSql.append("       AND AR.IS_GIVEUP = 0                                ");
            checkSql.append("       AND AR.IS_DELETE = 0                                ");
            checkSql.append("       WHERE WF.PID   = '402881945bec28ab015bec383aee0011'        ");
            checkSql.append("       GROUP BY WF.APRO_ID                                        ");
            checkSql.append("   ) AR                                                           ");
            checkSql.append("   ON AR.SALARY_ASSIGNATION_ID = SA.ID                            ");
            checkSql.append("   LEFT JOIN BRANCH B                                            ");
            checkSql.append("   ON SA.COMPANY_ID = B.ID                                           ");
            checkSql.append("   WHERE SA.IS_DELETE  = 0                                        ");
            checkSql.append("     AND SA.IS_SP IN (1, 2, 3)                                    ");// 已提交、未通过、已通过

            Map<String, String> mapData = JsonUtil.parseProperties(searchData);
            if (mapData != null) {
                // 分配过程
                if (!StringUtils.isBlank(mapData.get("name"))) {
                    checkSql.append("   AND SA.NAME LIKE '%"+mapData.get("name").trim()+"%'  ");
                }
                // 薪酬期间
                if (!StringUtils.isBlank(mapData.get("payPeriod"))) {
                    checkSql.append("   AND PERIODDATA.NAME LIKE '%"+mapData.get("payPeriod").trim()+"%'  ");
                }
                // 已提交状态
                if (!StringUtils.isBlank(mapData.get("isSp"))) {
                    checkSql.append("   AND SA.IS_SP = "+mapData.get("isSp").trim()+"                     ");
                }
            }
            checkSql.append("   GROUP BY SA.ID                                                 ");
            checkSql.append("   ORDER BY MIN(SA.CREATEDATE) DESC                               ");

            // 查找第一个审核的人
            List<WorkFlowChild> childs = salaryAssignationService.getOrderList(
                    WorkFlowChild.class, Order.asc("sort"),
                    Restrictions.eq("pid", "402881945bec28ab015bec383aee0011"));
            pager  = findPagerBySql(pager, checkSql.toString());
            List<?> checkList = pager.getResult();
            for (int i=0, l = checkList.size(); i < l; i++) {
                Object[] obj = (Object[]) checkList.get(i);
                HashMap<String, Object> checkMap = new HashMap<String, Object>();
                checkMap.put("id", obj[0]);
                checkMap.put("name", obj[1]);
                checkMap.put("groupName", obj[2]);
                checkMap.put("wageName", obj[3]);
                checkMap.put("payPeriod", obj[4]);
                checkMap.put("companyName", obj[9]);
                checkMap.put("companyId", obj[10]);// 公司ID
                checkMap.put("groupId", obj[11]);// 薪资组ID
                checkMap.put("salaryRate", obj[12]);// 发薪频率
                checkMap.put("salaryPeriod", obj[13]);// 薪酬期间
                checkMap.put("salaryWageId", obj[14]);// 工资套
                Object aproMan = obj[5];
                Object isSp = obj[6];
                Object aproId = obj[7];
                if (obj[5] == null) {
                    if (childs.size() > 0) {
                        aproId = childs.get(0).getAppid();
                        aproMan = childs.get(0).getAppman();
                    }
                } else {
                    // 查找最后一位审核状态
                    String[] aproIdArray = aproId.toString().split(",");
                    String[] isSpArray = isSp.toString().split(",");
                    String lastStatus = isSpArray[isSpArray.length-1];
                    if (lastStatus.equals("1")) {
                        StringBuilder nextSql = new StringBuilder();
                        nextSql.append("    SELECT I.*                                                 ");
                        nextSql.append("    FROM                                                       ");
                        nextSql.append("      (SELECT T.APRO_ID, LEAD(T.APRO_MAN,1, -1) OVER(ORDER BY T.SORT ASC) NEXTS_ACOUNT, ");
                        nextSql.append("        LEAD(T.APRO_ID,1,0) OVER(ORDER BY T.SORT ASC) NEXTS    ");
                        nextSql.append("      FROM JC_WORK_FLOWC T                                     ");
                        nextSql.append("      WHERE T.PID   = '402881945bec28ab015bec383aee0011'       ");
                        nextSql.append("      ) I                                                      ");
                        nextSql.append("    WHERE I.APRO_ID='"+aproIdArray[isSpArray.length-1]+"'      ");
                        List<?> nextList = findBySql(nextSql.toString());
                        if (nextList.size() > 0) {
                            Object[] objNext = (Object[]) nextList.get(0);
                            aproId = objNext[1].equals("-1") ? "" : objNext[2];
                            aproMan = objNext[1].equals("-1") ? "无" : objNext[1];
                        } else {
                            aproId = null;
                            aproMan = "无";
                        }
                    } else {
                        aproId = null;
                        aproMan = "无";
                    }
                }
                checkMap.put("aproId", aproId);
                checkMap.put("aproMan", aproMan);
                checkMap.put("isSp", obj[8]);
                checkDataList.add(checkMap);
            }
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("data", checkDataList);
        dataMap.put("total", pager.getTotalCount());

        return dataMap;
    }

    @Override
    public Map<String, Object> getSalaryAssignationForAuthListData2(String userName, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT SA.ID,                           ");
        sql.append("     MIN(SA.NAME) name,                              ");
        sql.append("     MIN(SG.NAME) GROUPNAME,                    ");
        sql.append("     MIN(SGW.NAME) WAGENAME,                    ");
        sql.append("     MIN(PERIODDATA.NAME) PAYPERIOD,            ");
        sql.append("     LISTAGG(AR.IS_SP, ',') WITHIN GROUP (ORDER BY AR.CREATEDATE DESC) IS_SP,                ");
        sql.append("     MIN(B.FZX) COMPANYNAME, MIN(B.ID) CompanyId,                       ");
        sql.append("     MIN(SA.SALARY_GROUP_ID) SALARY_GROUP_ID,   ");
        sql.append("     MIN(SA.SALARY_RATE) SALARY_RATE,           ");
        sql.append("     MIN(SA.SALARY_PERIOD) SALARY_PERIOD,       ");
        sql.append("     MIN(SA.SALARY_WAGE_ID) SALARY_WAGE_ID,     ");
        sql.append("     MIN(SA.PROCESS) PROCESS, MIN(SA.IS_SP) CHECK_STATUS,      ");
        sql.append("     LISTAGG(AR.ASSIGNATION_ACCOUNT, ',') WITHIN GROUP(ORDER BY AR.CREATEDATE DESC) APR_ID      ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA           ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP SG            ");
        sql.append("   ON SA.SALARY_GROUP_ID = SG.ID           ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW      ");
        sql.append("   ON SA.SALARY_WAGE_ID = SGW.ID           ");
        sql.append("   AND SGW.IS_DELETE    = 0                ");
        sql.append("   LEFT JOIN XC_PERIODDATA PERIODDATA      ");
        sql.append("   ON SA.SALARY_PERIOD = PERIODDATA.ID     ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_RECORD AR      ");
        sql.append("   ON AR.SALARY_ASSIGNATION_ID = SA.ID     ");
        sql.append("   AND AR.IS_GIVEUP = 0     ");
        sql.append("   AND AR.IS_DELETE = 0     ");
        sql.append("   LEFT JOIN BRANCH B                      ");
        sql.append("   ON SA.COMPANY_ID   = B.ID               ");
        sql.append("   LEFT JOIN XC_CHECK_PROCESS CP           ");
        sql.append("   ON SA.ID = CP.SALARY_ASSIGNATION_ID     ");
        sql.append("   WHERE SA.IS_DELETE = 0                  ");
        sql.append("   AND SA.IS_SP IN (1, 2, 3)               ");// 已提交、未通过、已通过
        sql.append("   AND CP.IS_DELETE = 0                    ");
        sql.append("   AND CP.STAFF_ID = '"+userName+"'        ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 分配过程
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND SA.NAME LIKE '%"+mapData.get("name").trim()+"%'  ");
            }
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("payPeriod"))) {
                sql.append("   AND PERIODDATA.NAME LIKE '%"+mapData.get("payPeriod").trim()+"%'  ");
            }
            // 已提交状态
            if (!StringUtils.isBlank(mapData.get("isSp"))) {
                sql.append("   AND SA.IS_SP = "+mapData.get("isSp").trim()+"                     ");
            }
        }
        sql.append("   GROUP BY SA.ID                               ");
        sql.append("   ORDER BY MIN(SA.CREATEDATE) DESC             ");

        pager  = findPagerBySql(pager, sql.toString());
        List<HashMap<String, Object>> checkDataList = new ArrayList<HashMap<String, Object>>();
        List<?> checkList = pager.getResult();
        for (int i=0, l = checkList.size(); i < l; i++) {
            Object[] obj = (Object[]) checkList.get(i);
            HashMap<String, Object> checkMap = new HashMap<String, Object>();
            checkMap.put("id", obj[0]);
            checkMap.put("name", obj[1]);
            checkMap.put("groupName", obj[2]);
            checkMap.put("wageName", obj[3]);
            checkMap.put("payPeriod", obj[4]);
            checkMap.put("companyName", obj[6]);
            checkMap.put("companyId", obj[7]);// 公司ID
            checkMap.put("groupId", obj[8]);// 薪资组ID
            checkMap.put("salaryRate", obj[9]);// 发薪频率
            checkMap.put("salaryPeriod", obj[10]);// 薪酬期间
            checkMap.put("salaryWageId", obj[11]);// 工资套

            // 获取待审核的账号
            Object aproId = "";
            Object aproMan = "";
            if (obj[5] == null) {
                // 查找第一个审核的人
                StringBuilder firstSql = new StringBuilder();
                firstSql.append("  SELECT                                  ");
                firstSql.append("      USERS.ID, USERS.USERNAME,USERS.NAME ");
                firstSql.append("  FROM XC_CHECK_PROCESS CP                ");
                firstSql.append("  LEFT JOIN QX_USERS USERS                ");
                firstSql.append("  ON CP.STAFF_ID = USERS.USERNAME         ");
                firstSql.append("  WHERE CP.SALARY_ASSIGNATION_ID='"+checkMap.get("id")+"'       ");
                firstSql.append("  AND CP.IS_DELETE = 0                    ");
                firstSql.append("  ORDER BY CP.CREATEDATE DESC             ");
                List<?> nextList = findBySql(firstSql.toString());
                if (nextList.size() > 0) {
                    Object[] objFirst = (Object[]) nextList.get(0);
                    aproId = objFirst[0];
                    aproMan = objFirst[2] + "("+objFirst[1]+")";
                }
            } else {
                String[] isSpArray = obj[5].toString().split(",");
                String lastStatus = isSpArray[0];
                String[] aproIdArray = obj[14].toString().split(",");
                if (lastStatus.equals("1")) {
                    StringBuilder nextManSql = new StringBuilder();
                    nextManSql.append("    SELECT I.*                                                 ");
                    nextManSql.append("    FROM                                                       ");
                    nextManSql.append("      (SELECT USERS.ID, USERS.USERNAME,       ");
                    nextManSql.append("         LEAD(USERS.NAME,1, -1) OVER( ORDER BY CP.CREATEDATE ASC) NEXTS_ACOUNT,      ");
                    nextManSql.append("         LEAD(CP.STAFF_ID,1, -1) OVER( ORDER BY CP.CREATEDATE ASC) NEXTS      ");
                    nextManSql.append("       FROM XC_CHECK_PROCESS CP              ");
                    nextManSql.append("       LEFT JOIN QX_USERS USERS              ");
                    nextManSql.append("       ON CP.STAFF_ID = USERS.USERNAME       ");
                    nextManSql.append("       WHERE CP.SALARY_ASSIGNATION_ID = '"+obj[0]+"'   ");
                    nextManSql.append("       AND CP.IS_DELETE = 0                  ");
                    nextManSql.append("       ORDER BY CP.CREATEDATE DESC           ");
                    nextManSql.append("    ) I                                                      ");
                    nextManSql.append("    WHERE I.USERNAME = '"+aproIdArray[0]+"' ");
                    List<?> nextList = findBySql(nextManSql.toString());
                    if (nextList.size() > 0) {
                        Object[] objNext = (Object[]) nextList.get(0);
                        aproId = objNext[3].equals("-1") ? "" : objNext[0];
                        aproMan = objNext[3].equals("-1") ? "无" : (objNext[2] + "("+objNext[3]+")");
                    } else {
                        aproId = null;
                        aproMan = "无";
                    }
                } else {
                    aproId = "";
                    aproMan = "无";
                }
            }
            checkMap.put("isSp", obj[13]);
            checkMap.put("aproId", aproId);
            checkMap.put("aproMan", aproMan);
            checkMap.put("process", obj[12]);
            checkDataList.add(checkMap);
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("data", checkDataList);
        dataMap.put("total", pager.getTotalCount());

        return dataMap;
    }

    @Override
    public String isCheckAUth(String ids, String userName) {
        // 只有最后审核通过的有权限修改为不通过
        String[] idsArray = ids.split(",");
        Map<String, Object> infoMap = new HashMap<String, Object>();
        for (int ii = 0; ii < idsArray.length; ii++) {
            StringBuilder sql = new StringBuilder();
            sql.append("     SELECT                                                                    ");
            sql.append("     WFC.APRO_ID,                                                               ");
            sql.append("     LISTAGG(WFC.APRO_MAN, ',') WITHIN GROUP (ORDER BY AR.CHECK_DATE) APRO_MAN, ");
            sql.append("     LISTAGG(AR.IS_SP, ',') WITHIN GROUP (ORDER BY AR.CHECK_DATE) IS_SP,        ");
            sql.append("     MIN(SA.IS_FAFANG) IS_FAFANG        ");
            sql.append("     FROM JC_WORK_FLOW WF                                                      ");
            sql.append("     LEFT JOIN JC_WORK_FLOWC WFC                                                     ");
            sql.append("     ON WF.ID = WFC.PID                                                       ");
            sql.append("     LEFT JOIN XC_ASSIGNATION_RECORD AR                                        ");
            sql.append("     ON WFC.APRO_ID    = AR.ASSIGNATION_ACCOUNT                                 ");
            sql.append("     AND AR.SALARY_ASSIGNATION_ID = '"+idsArray[ii]+"'  ");
            sql.append("     AND AR.IS_GIVEUP = 0                                                      ");
            sql.append("     AND AR.IS_DELETE = 0                                                      ");
            sql.append("     LEFT JOIN XC_SALARY_ASSIGNATION SA                                        ");
            sql.append("     ON SA.ID = AR.SALARY_ASSIGNATION_ID                                       ");
            sql.append("     WHERE WF.ID   = '402881945bec28ab015bec383aee0011'                        ");
            sql.append("     AND WF.WORK_FLOW_STATE = 1                                                      ");
            sql.append("     GROUP BY WFC.APRO_ID                                                       ");
            sql.append("     ORDER BY MIN(WFC.SORT) ASC                                                 ");

            List<?> list = findBySql(sql.toString());
            if (list.size() == 0) {
                infoMap.put("result", "fail");
                infoMap.put("info", "该审批流程不存在");

                return JsonUtil.Encode(infoMap);
            }
            for (int i=0, l = list.size(); i < l; i++) {
                // 查找第一位审核者
                Object[] firstObj = (Object[]) list.get(0);

                // 上一位审核者
                Object[] theOneObj = (Object[]) list.get(i==0 ? 0 : i-1);
                // 当前登录用户
                if (firstObj[0].equals(userName)) {// 正好是第一个审核者登录
                    if (firstObj[2] == null) {// 没有审核记录
                        infoMap.put("result", "success");
                        infoMap.put("info", "");

                        return JsonUtil.Encode(infoMap);// 当前登录人审核
                    }
                }

                Object[] obj = (Object[]) list.get(i);
                // 是否发放、发布
                if (obj[3] != null && Integer.valueOf(obj[3].toString()) > 0) {
                    infoMap.put("result", "13");
                    infoMap.put("info", ii+1);

                    return JsonUtil.Encode(infoMap);// 已经发放
                }
                // 当前登录用户
                if (obj[0].equals(userName)) {
                    if (obj[2] == null && i > 0) {
                        if (theOneObj[2] == null) {
                            infoMap.put("result", "11");
                            infoMap.put("info", ii+1);

                            return JsonUtil.Encode(infoMap);// 等待上一位审核者审核
                        } else {
                            String[] checks = theOneObj[2].toString().split(",");
                            if (checks[checks.length-1].equals("0")) {
                                infoMap.put("result", "12");
                                infoMap.put("info", ii+1);

                                return JsonUtil.Encode(infoMap);// 上一位审核者没有审核通过
                            } else {
                                infoMap.put("result", "success");
                                infoMap.put("info", "");

                                return JsonUtil.Encode(infoMap);// 当前登录人审核
                            }
                        }
                    }
                    String[] checks = obj[2].toString().split(",");
                    // 最后一次的审查结果
                    String lastRes = checks[checks.length-1];

                    // 不是最后的审核者
                    if (i != (list.size() - 1)) {
                        // 下一位审核者是否已经审核
                        Object[] lastObj = (Object[]) list.get(i+1);
                        if (lastObj[2] != null) {
                            String[] lastChecks = lastObj[2].toString().split(",");
                            
                            // 下一位审核者已经审核通过
                            if (lastChecks[lastChecks.length-1].equals("1")) {
                                infoMap.put("result", "3");
                                infoMap.put("info", ii+1);

                                return JsonUtil.Encode(infoMap);// 当前人不可以再次审核，下一位审核者通过，不必再审核
                            } else {
                                infoMap.put("result", "4");
                                infoMap.put("info", ii+1);

                                return JsonUtil.Encode(infoMap);// 当前人不可以再次审核，下一位审核者不通过，不必再审核
                            }
                        }

                        if (lastRes.equals("0")) {// 审核不通过不可以再审核
                            infoMap.put("result", "1");
                            infoMap.put("info", ii+1);

                            return JsonUtil.Encode(infoMap);
                        } else if (lastRes.equals("1")) {// 审核通过也可以反审核
                            infoMap.put("result", "2");
                            infoMap.put("info", "");

                            return JsonUtil.Encode(infoMap);
                        }
                    } else {
                        // 是最后一位审核者
                        if (lastRes.equals("0")) {// 审核不通过不可以再审核
                            infoMap.put("result", "1");
                            infoMap.put("info", ii+1);

                            return JsonUtil.Encode(infoMap);
                        }
                        infoMap.put("result", "success");
                        infoMap.put("info", "");

                        return JsonUtil.Encode(infoMap);// 当前登录人审核
                    }
                }
            }
        }

        infoMap.put("result", "fail");
        infoMap.put("info", "请选择一条记录");

        return JsonUtil.Encode(infoMap);
    }

    @Override
    public String isCheckAUth2(String ids, String userName) {
        Map<String, Object> infoMap = new HashMap<String, Object>();
        StringBuilder nextManSql = new StringBuilder();
        nextManSql.append("   SELECT USERS.ID, USERS.USERNAME, USERS.NAME,SA.IS_FAFANG ");
        nextManSql.append("   FROM JC_WORK_FLOW JWF                                    ");
        nextManSql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                       ");
        nextManSql.append("   ON SA.PROCESS = JWF.ID                                   ");
        nextManSql.append("   LEFT JOIN XC_CHECK_PROCESS CP                            ");
        nextManSql.append("   ON CP.SALARY_ASSIGNATION_ID = SA.ID                      ");
        nextManSql.append("   AND CP.IS_DELETE = 0                                     ");
        nextManSql.append("   LEFT JOIN QX_USERS USERS                                 ");
        nextManSql.append("   ON CP.STAFF_ID = USERS.USERNAME                          ");
        nextManSql.append("   WHERE SA.ID = '"+ids+"'         ");
        nextManSql.append("   ORDER BY CP.CREATEDATE DESC                              ");
        List<?> nextList = findBySql(nextManSql.toString());
        if (nextList.size() > 0) {
            Object[] objNext = (Object[]) nextList.get(0);
            // 是否发放、发布
            if (objNext[3] != null && Integer.valueOf(objNext[3].toString()) > 0) {
                infoMap.put("result", "13");
                infoMap.put("info", 1);

                return JsonUtil.Encode(infoMap);// 已经发放
            }
            if (!objNext[1].equals(userName)) {
                infoMap.put("result", "3");
                infoMap.put("info", 1);

                return JsonUtil.Encode(infoMap);// 审核人不是当前登录人
            } else {
                infoMap.put("result", "success");
                infoMap.put("info", "");

                return JsonUtil.Encode(infoMap);// 当前登录人审核
            }
        } else {
            infoMap.put("result", "4");
            infoMap.put("info", "选中的第"+1+"条记录的审批流程不存在");

            return JsonUtil.Encode(infoMap);
        }
    }

    @Override
    public Map<String, Object> getCheckListData(String id, Pager pager) {
        StringBuilder checkSql = new StringBuilder();
        checkSql.append("   SELECT USERS.NAME,                                            ");
        checkSql.append("     NVL(AR.IS_SP, -1) IS_SP,                                    ");
        checkSql.append("     USERS.USERNAME,                                             ");
        checkSql.append("     AR.NOTE,                                                    ");
        checkSql.append("     TO_CHAR(AR.CHECK_DATE, 'yyyy-mm-dd hh24:mi:ss') CHECK_DATE  ");
        checkSql.append("   FROM XC_ASSIGNATION_RECORD AR                                 ");
        checkSql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                            ");
        checkSql.append("   ON AR.SALARY_ASSIGNATION_ID = SA.ID                           ");
        checkSql.append("   LEFT JOIN QX_USERS USERS                                      ");
        checkSql.append("   ON USERS.USERNAME = AR.ASSIGNATION_ACCOUNT                    ");
        checkSql.append("   WHERE AR.SALARY_ASSIGNATION_ID = '"+id+"'                     ");
        checkSql.append("   AND AR.IS_DELETE = 0                                          ");
        checkSql.append("   ORDER BY AR.CHECK_DATE DESC                                   ");

        pager = findPagerBySql(pager, checkSql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("aproMan", obj[0]);
            map.put("isSp", obj[1]);
            map.put("aproId", obj[2]);
            map.put("note", obj[3]);
            map.put("checkDate", obj[4]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public List<Map<String, Object>> getNeedAssignationData(String groupId, String wageId) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT JBI.ID,                                                 ");
        sql.append("  MIN(JBI.JOB_NUMBER) JOBNUMBER,                                 ");
        sql.append("  MIN(JBI.NAME) NAME,                                            ");
        sql.append("  CONCAT_ARRAY(CAST(COLLECT(SI.ID ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_VARCHAR2)) ITEMSID,                           ");
        sql.append("  CONCAT_ARRAY(CAST(COLLECT(SI.NAME ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_VARCHAR2)) ITEMSNAME,                       ");
        sql.append("  CONCAT_ARRAY(CAST(COLLECT(NVL(SF.ID, ' ') ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_VARCHAR2)) FORMULAID,               ");
        sql.append("  CONCAT_ARRAY(CAST(COLLECT(NVL(SF.CNT_MATH, 0) ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_VARCHAR2)) FORMULA,               ");
        sql.append("  CONCAT_ARRAY(CAST(COLLECT(NVL(SI.NUMBER_ACCURACY, 0) ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_VARCHAR2)) XS,               ");
        sql.append("  CONCAT_ARRAY_NUMBER(CAST(COLLECT(NVL(GWAY.SX, 0) ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_NUMBER)) SX,               ");
        sql.append("  MIN(NVL(SR.DEPTNAME, ' ')) DEPART_ID,             ");
        sql.append("  MIN(NVL(QD.NAME, ' ')) DEPART_NAME,             ");
        sql.append("  MIN(NVL(B.ID, ' ')) COMPANY_ID,             ");
        sql.append("  MIN(NVL(B.FZX, ' ')) COMPANY_NAME,             ");
        sql.append("  CONCAT_ARRAY(CAST(COLLECT(NVL(SV.STATIC_VALUE, 0) ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_VARCHAR2)) STATIC_VALUE,               ");
        sql.append("  MIN(NVL(SR.SPECIFIC_POST, ' ')) JTGW_ID, MIN(NVL(JTGW.POSITION_NAME, ' ')) JTGW_NAME,            ");
        sql.append("  CONCAT_ARRAY_NUMBER(CAST(COLLECT(GWAY.IS_ER ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_NUMBER)) IS_DISPLAY,               ");
        sql.append("  CONCAT_ARRAY_NUMBER(CAST(COLLECT(SI.IS_NUMBER ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_NUMBER)) IS_NUMBER,               ");
        sql.append("  CONCAT_ARRAY_NUMBER(CAST(COLLECT(SI.ADD_OR_LESS ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_NUMBER)) ADD_OR_LESS,               ");
        sql.append("  MIN(SR.FPQX) FPQX,                    ");
        sql.append("  MIN(NVL(SR.POST, ' ')) BZGW_ID, MIN(NVL(BZGW.POSITION_NAME, ' ')) BZGW_NAME,            ");
        sql.append("  MIN(NVL(SR.SALARY_POST, ' ')) XCGW_ID, MIN(NVL(SDD.NAME, ' ')) XCGW_NAME,            ");
        sql.append("  MIN(NVL(SR.GZXS, 0)) GZXS, MIN(NVL(JQD.DIC_NAME, ' ')) SPECIAL_MARK,            ");
        sql.append("  CONCAT_ARRAY_NUMBER(CAST(COLLECT(GWAY.IS_DISPLAY ORDER BY GWAY.SX ASC, SI.ID ASC) AS TAB_NUMBER)) defaultDisplay, ");
        sql.append("  MIN(JBI.ON_JOB) onJob, MIN(JBI.PRACTICE_NUM) PRACTICE_NUM  ");
        sql.append("  FROM XC_SALARY_GROUP_WAGE SGW                                  ");
        sql.append("  LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                          ");
        sql.append("  ON SGW.ID = GWAY.WAGE_ID                                       ");
        sql.append("  AND GWAY.TYPE = 1                                         ");
        sql.append("  AND GWAY.IS_DELETE = 0                                         ");
        sql.append("  LEFT JOIN XC_SALARY_PERSONAL SP                                ");
        sql.append("  ON SGW.SALARY_GROUP_ID = SP.SALARY_GROUP_ID                    ");
        sql.append("  AND SP.IS_DELETE = 0                                           ");
        sql.append("  LEFT JOIN XC_SALARY_RECORD SR                             ");
        sql.append("  ON SP.SALARY_RECORD_ID = SR.STAFF_ID                                ");
        sql.append("  AND SP.RECORD_MAIN_ID = SR.ID                                ");
        sql.append("  LEFT JOIN JC_BASIC_INFORMATION JBI                             ");
        sql.append("  ON SR.STAFF_ID = JBI.ID                                ");
        sql.append("  LEFT JOIN BRANCH B                             ");
        sql.append("  ON SR.FILM_NAME = B.ID                                ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                             ");
        sql.append("  ON SR.DEPTNAME = QD.ID                                ");
        sql.append("  LEFT JOIN XC_SALARY_ITEMS SI                                   ");
        sql.append("  ON GWAY.ITEM_ID = SI.ID                                        ");
        sql.append("  LEFT JOIN XC_SALARY_FORMULA SF                                 ");
        sql.append("  ON (GWAY.ID = SF.BIND_ID OR GWAY.SALARY_WAGE_ID = SF.BIND_ID)  ");// 公式关联
        sql.append("  AND SF.IS_DELETE = 0                                        ");
        sql.append("  AND SF.IS_DEFAULT = 1                                        ");
        sql.append("  LEFT JOIN XC_SALARY_VALUE SV                                        ");
        sql.append("  ON SR.STAFF_ID = SV.STAFF_ID                                        ");
        sql.append("  AND GWAY.ITEM_ID = SV.SALARY_ITEMS_ID                                        ");
        sql.append("  AND SGW.SALARY_GROUP_ID = SV.SALARY_GROUP_ID                                 ");
        sql.append("  AND SV.RECORD_MAIN_ID = SR.ID                                 ");
        sql.append("  AND SV.IS_DELETE = 0                                        ");
        sql.append("  LEFT JOIN DEPT_POSITION JTGW                                                                 ");
        sql.append("    ON JTGW.ID = SR.SPECIFIC_POST                                                              ");
        sql.append("  LEFT JOIN POST_POSITION BZGW                                                                 ");
        sql.append("    ON BZGW.ID = SR.POST                                                              ");
        sql.append("  LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                                                 ");
        sql.append("    ON SR.SALARY_POST = SDD.ID                                                              ");
        sql.append("  LEFT JOIN JC_QX_DECTIONARY JQD                                                                 ");
        sql.append("    ON SR.SPECIAL_MARK = JQD.DIC_VALUE                                                              ");
        sql.append("   AND JQD.PID = '402881945be55610015be55f9d750010'                                                              ");
        sql.append("  LEFT JOIN JC_EMP_DIMISSION JED                                                                 ");
        sql.append("    on JBI.JOB_NUMBER = JED.JOB_NUMBER                                                              ");
        sql.append("  WHERE SGW.SALARY_GROUP_ID = '"+groupId+"' ");
        sql.append("  AND SGW.ID = '"+wageId+"'                ");
        sql.append("  AND CASE WHEN JBI.ON_JOB = 3 THEN TO_CHAR (NVL(JED.LEAVE_DATE, SYSDATE),'yyyy-MM')     ");// 离职人员离职月可以显示，下一个月不显示
        sql.append("      ELSE TO_CHAR(SYSDATE, 'yyyy-MM') END >= TO_CHAR(SYSDATE, 'yyyy-MM')     ");
        sql.append("  AND JBI.IS_DELETE = 0                                        ");
        sql.append("   AND CASE WHEN SP.IS_OFF = 1                                                                            ");// 薪资组注销日期之前的人员
        sql.append("            THEN CASE WHEN TO_CHAR(SP.OFF_DATE, 'yyyy-MM-dd') <= TO_CHAR(SYSDATE, 'yyyy-MM-dd') THEN 1    ");
        sql.append("                ELSE 0 END                                                                                ");
        sql.append("      ELSE 0 END = 0                                                                                      ");
        sql.append("  GROUP BY JBI.ID, JBI.CREATEDATE                                ");
        sql.append("  ORDER BY JBI.CREATEDATE DESC                                   ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", Integer.valueOf(obj[27].toString()) == 0? obj[28] : obj[1]);// 实习期的工号由实习编号提供
            map.put("name", obj[2]);
            map.put("itemsId", LingUtil.ClobToString((Clob)obj[3]));
            map.put("itemsName", LingUtil.ClobToString((Clob)obj[4]));
            map.put("formulaId", LingUtil.ClobToString((Clob)obj[5]));
            map.put("formula", LingUtil.ClobToString((Clob)obj[6]));
            map.put("xs", LingUtil.ClobToString((Clob)obj[7]));
            map.put("sx", LingUtil.ClobToString((Clob)obj[8]));
            map.put("departId", obj[9]);
            map.put("deptName", obj[10]);
            map.put("companyId", obj[11]);
            map.put("companyName", obj[12]);
            map.put("salaryValue", LingUtil.ClobToString((Clob)obj[13]));
            map.put("jtgwId", obj[14]);
            map.put("jtgwName", obj[15]);
            map.put("isDisplay", LingUtil.ClobToString((Clob)obj[16]));
            map.put("isNumber", LingUtil.ClobToString((Clob)obj[17]));
            map.put("addOrLess", LingUtil.ClobToString((Clob)obj[18]));
            map.put("fpqx", obj[19]);
            map.put("bzgwId", obj[20]);
            map.put("bzgwName", obj[21]);
            map.put("xcgwId", obj[22]);
            map.put("xcgwName", obj[23]);
            map.put("gzxs", obj[24]);
            map.put("specialMark", obj[25]);
            map.put("defaultDisplay", LingUtil.ClobToString((Clob)obj[26]));
            map.put("onJob", obj[27]);// 在职状态 0：实习期
            map.put("practiceNum", obj[28]);// 实习编号
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String doFunction(String procName, String str) {
        final String procNamefi = procName;
        final String[] strArr = str.split(",");
        return getHibernateTemplate().execute(new HibernateCallback<String>() {

            public String doInHibernate(Session session) throws HibernateException, SQLException{ 
                String procWh = "";
                for (int i = 0; i < strArr.length; i++) {
                    if(i==0){
                        procWh =  procWh + "?";
                    }else{
                        procWh =  procWh + ",?";
                    }
                }
                String proc = "{call "+ procNamefi + "("+procWh+",?,?)}";
                // 执行存储过程 
                CallableStatement stCall = session.connection().prepareCall(proc);
                for (int i = 0; i < strArr.length; i++) {
                    stCall.setString(i+1,strArr[i]);
                }
                stCall.registerOutParameter(strArr.length+1, Types.INTEGER);
                stCall.registerOutParameter(strArr.length+2, Types.VARCHAR);
                stCall.execute();
//                ResultSet rs = stCall.executeQuery();// 执行存储过程
//                int flag = 0;
//                if(rs != null && rs.next()){
//                    flag = rs.getInt("RETURN_PARAM");
//                }

                return "success"; 
            } 
        });
    }

    @Override
    public List<Map<String, Object>> getNeedAsignData(String groupId, String wageId) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT JBI.ID,                                                                        ");
        sql.append("      JBI.JOB_NUMBER,                                                                   ");
        sql.append("      JBI.NAME,                                                                         ");
        sql.append("      SI.ID ITEMSID,                                                                    ");
        sql.append("      SI.NAME ITEMSNAME,                                                                ");
        sql.append("      NVL(SF.ID, ' ') FORMULAID,                                                        ");
        sql.append("      NVL(SF.CNT_MATH, 'result = 0') FORMULA,                                           ");
        sql.append("      NVL(SI.NUMBER_ACCURACY, 0) XS,                                                    ");
        sql.append("      NVL(GWAY.SX, 0) SX,                                                               ");
        sql.append("      NVL(SR.DEPTNAME, ' ') DEPART_ID,                                                  ");
        sql.append("      NVL(QD.NAME, ' ') DEPART_NAME,                                                    ");
        sql.append("      NVL(B.ID, ' ') COMPANY_ID,                                                        ");
        sql.append("      NVL(B.FZX, ' ') COMPANY_NAME,                                                     ");
        sql.append("      NVL(SV.STATIC_VALUE, 0) STATIC_VALUE,                                             ");
        sql.append("      NVL(SR.SPECIFIC_POST, ' ') JTGW_ID,                                               ");
        sql.append("      NVL(JTGW.POSITION_NAME, ' ') JTGW_NAME,                                           ");
        sql.append("      GWAY.IS_ER IS_DISPLAY,                                                            ");
        sql.append("      SI.IS_NUMBER,                                                                     ");
        sql.append("      SI.ADD_OR_LESS,                                                                   ");
        sql.append("      NVL(SR.FPQX, 2) FPQX,                                                             ");
        sql.append("      NVL(SR.POST, ' ') BZGW_ID,                                                        ");
        sql.append("      NVL(BZGW.POSITION_NAME, ' ') BZGW_NAME,                                           ");
        sql.append("      NVL(SR.SALARY_POST, ' ') XCGW_ID,                                                 ");
        sql.append("      NVL(SDD.NAME, ' ') XCGW_NAME,                                                     ");
        sql.append("      NVL(SR.GZXS, 0) GZXS,                                                             ");
        sql.append("      NVL(JQD.DIC_NAME, ' ') SPECIAL_MARK,                                              ");
        sql.append("      GWAY.IS_DISPLAY defaultDisplay,                                                   ");
        sql.append("      JBI.ON_JOB onJob,                                                                 ");
        sql.append("      JBI.PRACTICE_NUM PRACTICE_NUM                                                     ");
        sql.append("  FROM XC_SALARY_RECORD SR                                                         ");
        sql.append("  LEFT JOIN XC_SALARY_PERSONAL SP                                                 ");
        sql.append("  ON SP.SALARY_RECORD_ID = SR.STAFF_ID                                                     ");
        sql.append("  AND SP.RECORD_MAIN_ID  = SR.ID                                                                ");
        sql.append("  AND SP.IS_DELETE       = 0                                                                ");
        sql.append("  LEFT JOIN XC_SALARY_GROUP_WAGE SGW                                                       ");
        sql.append("  ON SGW.SALARY_GROUP_ID = SP.SALARY_GROUP_ID                                           ");
        sql.append("  LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                                                            ");
        sql.append("  ON SGW.ID          = GWAY.WAGE_ID                                                         ");
        sql.append("  AND GWAY.TYPE      = 1                                                  ");
        sql.append("  AND GWAY.IS_DELETE = 0                                                        ");
        sql.append("  LEFT JOIN JC_BASIC_INFORMATION JBI                                                    ");
        sql.append("  ON SR.STAFF_ID = JBI.ID                                                               ");
        sql.append("  LEFT JOIN BRANCH B                                                                    ");
        sql.append("  ON SR.FILM_NAME = B.ID                                                                ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                                                            ");
        sql.append("  ON SR.DEPTNAME = QD.ID                                                                ");
        sql.append("  LEFT JOIN XC_SALARY_ITEMS SI                                                          ");
        sql.append("  ON GWAY.ITEM_ID = SI.ID                                                               ");
        sql.append("  LEFT JOIN XC_SALARY_FORMULA SF                                                        ");
        sql.append("  ON ( GWAY.ID           = SF.BIND_ID                                                   ");
        sql.append("  OR GWAY.SALARY_WAGE_ID = SF.BIND_ID )                                                 ");
        sql.append("  AND SF.IS_DELETE       = 0                                                            ");
        sql.append("  AND SF.IS_DEFAULT      = 1                                                            ");
        sql.append("  LEFT JOIN XC_SALARY_VALUE SV                                                          ");
        sql.append("  ON SR.STAFF_ID          = SV.STAFF_ID                                                 ");
        sql.append("  AND GWAY.ITEM_ID        = SV.SALARY_ITEMS_ID                                          ");
        sql.append("  AND SGW.SALARY_GROUP_ID = SV.SALARY_GROUP_ID                                          ");
        sql.append("  AND SV.RECORD_MAIN_ID   = SR.ID                                                       ");
        sql.append("  AND SV.IS_DELETE        = 0                                                           ");
        sql.append("  LEFT JOIN DEPT_POSITION JTGW                                                          ");
        sql.append("  ON JTGW.ID = SR.SPECIFIC_POST                                                         ");
        sql.append("  LEFT JOIN POST_POSITION BZGW                                                          ");
        sql.append("  ON BZGW.ID = SR.POST                                                                  ");
        sql.append("  LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                               ");
        sql.append("  ON SR.SALARY_POST = SDD.ID                                                            ");
        sql.append("  LEFT JOIN JC_QX_DECTIONARY JQD                                                        ");
        sql.append("  ON SR.SPECIAL_MARK = JQD.DIC_VALUE                                                    ");
        sql.append("  AND JQD.PID = '402881945be55610015be55f9d750010'                                      ");
        sql.append("  LEFT JOIN JC_EMP_DIMISSION JED                                                        ");
        sql.append("  ON JBI.JOB_NUMBER = JED.JOB_NUMBER                                                    ");
        sql.append("  WHERE SGW.SALARY_GROUP_ID = '"+groupId+"'  ");
        sql.append("  AND SGW.ID = '"+wageId+"'                  ");
//        sql.append("  AND CASE WHEN JBI.ON_JOB = 3 THEN TO_CHAR (NVL(JED.LEAVE_DATE, SYSDATE),'yyyy-MM')     ");// 离职人员离职月可以显示，下一个月不显示
//        sql.append("      ELSE TO_CHAR(SYSDATE, 'yyyy-MM') END >= TO_CHAR(SYSDATE, 'yyyy-MM')     ");
        sql.append("  AND TO_CHAR(SYSDATE, 'yyyyMM') - DECODE(JBI.ON_JOB, 3, TO_CHAR(NVL(JED.LEAVE_DATE, SYSDATE),'yyyyMM'), TO_CHAR(SYSDATE, 'yyyyMM')) <= 1 ");
        sql.append("  AND JBI.IS_DELETE = 0                                        ");
        sql.append("  AND CASE WHEN SP.IS_OFF = 1                                                                            ");// 薪资组注销日期之前的人员
        sql.append("            THEN CASE WHEN TO_CHAR(SP.OFF_DATE, 'yyyy-MM-dd') <= TO_CHAR(SYSDATE, 'yyyy-MM-dd') THEN 1    ");
        sql.append("                ELSE 0 END                                                                                ");
        sql.append("      ELSE 0 END = 0                                                                                      ");
        sql.append("  ORDER BY SR.ID  DESC                                                                 ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", Integer.valueOf(obj[27].toString()) == 0? obj[28] : obj[1]);// 实习期的工号由实习编号提供
            map.put("name", obj[2]);
            map.put("itemsId", obj[3]);
            map.put("itemsName", obj[4]);
            map.put("formulaId", obj[5]);
            map.put("formula", obj[6]);
            map.put("xs", obj[7]);
            map.put("sx", obj[8]);
            map.put("departId", obj[9]);
            map.put("deptName", obj[10]);
            map.put("companyId", obj[11]);
            map.put("companyName", obj[12]);
            map.put("salaryValue", obj[13]);
            map.put("jtgwId", obj[14]);
            map.put("jtgwName", obj[15]);
            map.put("isDisplay", obj[16]);
            map.put("isNumber", obj[17]);
            map.put("addOrLess", obj[18]);
            map.put("fpqx", obj[19]);
            map.put("bzgwId", obj[20]);
            map.put("bzgwName", obj[21]);
            map.put("xcgwId", obj[22]);
            map.put("xcgwName", obj[23]);
            map.put("gzxs", obj[24]);
            map.put("specialMark", obj[25]);
            map.put("defaultDisplay", obj[26]);
            map.put("onJob", obj[27]);// 在职状态 0：实习期
            map.put("practiceNum", obj[28]);// 实习编号
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String getCurItemsFormula(String id, String assignStaffId) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT SF.ID, SF.CONTENT, SF.CNT_MATH, ASS.ID assId,  ");
        sql.append("    ASTAFF.STAFF_ID, ASS.IBF_ID, ASS.IBF_NAME,ASTAFF.COMPANY_ID,  ");
        sql.append("    ASS.NUMBER_ACCURACY, SFD.GROUP_WAGE_ID, ASS.SX, ASS.ADD_OR_LESS   ");
        sql.append("  FROM XC_SALARY_FORMULA SF                             ");
        sql.append("  LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                 ");
        sql.append("  ON ( GWAY.ID = SF.BIND_ID                             ");
        sql.append("  OR GWAY.SALARY_WAGE_ID = SF.BIND_ID )                 ");
        sql.append("  AND GWAY.IS_DELETE     = 0                            ");
        sql.append("  LEFT JOIN XC_SALARY_GROUP_WAGE SGW                    ");
        sql.append("  ON SGW.ID = GWAY.WAGE_ID                              ");
        sql.append("  AND SGW.IS_DELETE = 0                                 ");
        sql.append("  LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS             ");
        sql.append("  ON GWAY.ITEM_ID = ASS.IBF_ID                          ");
        sql.append("  LEFT JOIN XC_SALARY_ASSIGNATION SA                    ");
        sql.append("  ON SGW.SALARY_GROUP_ID = SA.SALARY_GROUP_ID           ");
        sql.append("  AND SA.SALARY_WAGE_ID = SGW.ID                        ");
        sql.append("  LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                 ");
        sql.append("  ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID               ");
        sql.append("  AND ASTAFF.SALARY_ASSIGNATION_ID = SA.ID              ");
        sql.append("  LEFT JOIN XC_SALARY_FORMULA_DEPLOY SFD              ");
        sql.append("  ON SFD.GROUP_WAGE_ID = SGW.ID              ");
        sql.append("  AND SFD.GWAT_ID = GWAY.ID              ");
        sql.append("  WHERE SF.IS_DELETE  = 0                               ");
        sql.append("  AND SF.IS_DEFAULT   = 1                               ");
        sql.append("  AND ASTAFF.IS_DELETE = 0                               ");
        sql.append("  AND SA.IS_DELETE = 0                                  ");
        sql.append("  AND SA.ID = '"+id+"'                                  ");

        // 分配员工信息
        if (!StringUtils.isBlank(assignStaffId)) {
            String newSql = LingUtil.getOracleInMethodDeal(" ASTAFF.ID", 800, Arrays.asList(assignStaffId.split(",")));
            sql.append("   AND " + newSql);
        }
        sql.append("  ORDER BY SFD.EXECUTE_COUNT ASC, ASS.IBF_ID ASC,ASTAFF.STAFF_ID ASC          ");

        return sql.toString();
    }

    @Override
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public String saveSalaryItemsValue(final List<Map<String, Object>> list) {
        String sql = "UPDATE XC_ASSIGNATION_STAFF_SALARY SET ASSIGNATION_CHARGE=? WHERE ID=? AND IS_DELETE = 0";
        int[] result = getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Map<String, Object> map = list.get(i);
                ps.setString(1, map.get("charge").toString());
                ps.setString(2, map.get("id").toString());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });

        return result.length > 0? "success" : "fail";
    }

    @Override
    public Map<String, Object> getAssignationForStaffData(String id, String ids, String searchData, Pager pager) {
        String sql = getAssignationForStaffDataSql(id, ids, searchData);
//        pager = findPagerBySql(pager, sql);
//        List<?> list = pager.getResult();
        List<?> list = findBySql(sql);

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("jobNumber", obj[2]);
            map.put("name", obj[3]);
            map.put("itemsId", LingUtil.ClobToString((Clob)obj[4]));
            map.put("itemsName", LingUtil.ClobToString((Clob)obj[5]));
            map.put("charge", LingUtil.ClobToString((Clob)obj[6]));
            map.put("assId", LingUtil.ClobToString((Clob)obj[7]));
            map.put("isNumber", LingUtil.ClobToString((Clob)obj[8]));
            map.put("departId", obj[9]);
            map.put("addOrLess", LingUtil.ClobToString((Clob)obj[10]));
            map.put("companyName", obj[11]);
            map.put("deptName", obj[12]);
            map.put("gwName", obj[13]);
            map.put("isAllMonth", obj[14]);
            map.put("bzgwName", obj[15]);
            map.put("xcgwName", obj[16]);
            dataList.add(map);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
//        map.put("total", pager.getTotalCount());
        map.put("summary", getTotalData(id));// 汇总计算

        return map;
    }

    @Override
    public Map<String, Object> getCompareAssignationForStaffData(String id, String ids, String searchData, String conditionData) {
        List<?> list = findBySql(getCheckPeriodData(id, searchData, conditionData));

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("jobNumber", obj[2]);
            map.put("name", obj[3]);
            map.put("itemsId", LingUtil.ClobToString((Clob)obj[4]));
            map.put("itemsName", LingUtil.ClobToString((Clob)obj[5]));
            map.put("charge", LingUtil.ClobToString((Clob)obj[6]));
            map.put("assId", LingUtil.ClobToString((Clob)obj[7]));
            map.put("isNumber", LingUtil.ClobToString((Clob)obj[8]));
            map.put("departId", obj[9]);
            map.put("addOrLess", LingUtil.ClobToString((Clob)obj[10]));
            map.put("type", obj[11]);
            map.put("periodName", obj[12]);
            map.put("checkId", obj[13]);
            try {
                map.put("note", LingUtil.ClobToString((java.sql.Clob)obj[14]));
            } catch (Exception e) {
                map.put("note", "");
            }
            map.put("companyName", obj[16]);
            map.put("deptName", obj[17]);
            map.put("gwName", obj[18]);
            map.put("isAllMonth", obj[19]);
            dataList.add(map);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);

        return map;
    }

    @Override
    public String getCheckPeriodData(String id, String searchData, String conditionData) {
        Map<String, String> conditionMap = JsonUtil.parseProperties(conditionData);
        // 查找上个月的薪酬期间
        Perioddata perioddata = salaryAssignationService.get(Perioddata.class, Restrictions.eq("id", conditionMap.get("salaryPeriod")));
        if (perioddata == null) perioddata = new Perioddata();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(perioddata.getStartDate());
        String date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) +"-" + calendar.get(Calendar.DATE);

        String lastPeriiodId = "";

        // 一月份需要获取去年的值
//        if ((calendar.get(Calendar.MONTH) + 1) == 1) {
//            Period period = salaryAssignationService.get(Period.class, Restrictions.eq("id", perioddata.getPeriodId()));
//            if (period != null) {
//                Period period2 = salaryAssignationService.get(Period.class,
//                        Restrictions.eq("year", period.getYear()-1),
//                        Restrictions.eq("payPerid", period.getPayPerid()));
//                if (period2 != null) {
//                    List<Perioddata> listPerioddatas = salaryAssignationService.getOrderList(Perioddata.class,
//                            Order.desc("startDate"),
//                            Restrictions.eq("periodId", period2.getId()));
//                    lastPeriiodId = listPerioddatas.size() > 0? listPerioddatas.get(0).getId() : "";
//                }
//            }
//        } else {
//            List<Perioddata> listPerioddatas = salaryAssignationService.getOrderList(Perioddata.class,
//                    Order.desc("startDate"),
//                    Restrictions.eq("periodId", perioddata.getPeriodId()),
//                    Restrictions.sqlRestriction("START_DATE < TO_DATE('"+date+"', 'yyyy-MM-dd')"));
//
//            // 上一个薪酬区间
//            lastPeriiodId = listPerioddatas.size() > 0? listPerioddatas.get(0).getId() : "";
//        }

        StringBuilder conSql = new StringBuilder();
        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        String choseItem = "";
        if (mapData != null) {
            // 职工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                conSql.append("  AND TT.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                                 ");
            }
            // 职工名称
            if (!StringUtils.isBlank(mapData.get("name"))) {
                conSql.append("  AND TT.STAFF_NAME LIKE '%"+mapData.get("name").trim()+"%'                                 ");
            }
            // 部门
            if (!StringUtils.isBlank(mapData.get("deptId"))) {
                conSql.append("   AND TT.DEPT_ID IN ('"+StringUtils.join(mapData.get("deptId").split(","), "','")+"') ");
            }
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("periods"))) {
                lastPeriiodId = mapData.get("periods");
            }
            // 薪资项目
            if (!StringUtils.isBlank(mapData.get("salaryItem"))) {
                // 比较符号（大于、小于、等于）
                if (!StringUtils.isBlank(mapData.get("itemX"))) {
                    String  ge = "";
                    switch (Integer.valueOf(mapData.get("itemX"))) {
                    case 0:// 大于
                        ge = ">";
                        break;
                    case 1:// 小于
                        ge = "<";
                        break;
                    case 2:// 等于
                        ge = "=";
                        break;
                    case 3:// 大于等于
                        ge = ">=";
                        break;
                    case 4:// 小于等于
                        ge = "<=";
                        break;
                    }
                    // 值
                    if (!StringUtils.isBlank(mapData.get("itemValue"))) {
                        choseItem = " WHERE REGEXP_SUBSTR(CHARGE,'[^,]+',1,"+mapData.get("index")+",'i') "+ge+" "+mapData.get("itemValue");
                    }
                }
            }
        }
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                       ");
        sql.append("     CDATA.ID,STAFFID,JOBNUMBER,CDATA.NAME,ITEMSID,ITEMSNAME,CHARGE,ASSID,IS_NUMBER,                  ");
        sql.append("     DEPART_ID,ADD_OR_LESS,TYP,PeriodName, CDATA.CHECK_ID, CMS.NOTE,CDATA.COMPANY_ID,                 ");
        sql.append("     CDATA.COMPANY_NAME, CDATA.DEPT_NAME, CDATA.GW_NAME, CDATA.IS_ALL_MONTH                 ");
        sql.append("   FROM                                                                                         ");
        sql.append("     (SELECT *                                                                                  ");
        sql.append("     FROM                                                                                       ");
        sql.append("       (SELECT TT.ID,                                                                           ");
        sql.append("         MIN(TT.STAFF_ID) STAFFID,                                                              ");
        sql.append("         MIN(TT.JOB_NUMBER) JOBNUMBER,                                                          ");
        sql.append("         MIN(TT.STAFF_NAME) NAME,                                                               ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.IBF_ID ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) ITEMSID,                          ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.IBF_NAME ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) ITEMSNAME,                  ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.ASSIGNATION_CHARGE ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) CHARGE,                  ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.ASSID ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) ASSID,                  ");
        sql.append("         CONCAT_ARRAY_NUMBER(CAST(COLLECT(TT.IS_NUMBER ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_NUMBER)) IS_NUMBER,                  ");
        sql.append("         MIN(TT.DEPTNAME) DEPART_ID,                                                            ");
        sql.append("         CONCAT_ARRAY_NUMBER(CAST(COLLECT(TT.ADD_OR_LESS ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_NUMBER)) ADD_OR_LESS,                  ");
        sql.append("         '1' typ,                                                            ");
        sql.append("         MIN(TT.NAME) PeriodName, MIN(TT.Check_ID) Check_ID, MIN(TT.COMPANY_ID) COMPANY_ID,                 ");
        sql.append("         MIN(TT.COMPANY_NAME) COMPANY_NAME, MIN(TT.DEPT_NAME) DEPT_NAME, MIN(TT.GW_NAME) GW_NAME, ");
        sql.append("         MIN(TT.IS_ALL_MONTH) IS_ALL_MONTH, MIN(TT.START_DATE) START_DATE, ");
        sql.append("         MIN(TT.DEPT_ID) DEPT_ID, MIN(TT.XCGW_ID) XCGW_ID ");
        sql.append("       FROM                                                                                     ");
        sql.append("         (SELECT ASTAFF.ID,                                                                     ");
        sql.append("           ASTAFF.STAFF_ID,                                                                     ");
        sql.append("           ASTAFF.JOB_NUMBER,                                                                   ");
        sql.append("           ASTAFF.STAFF_NAME,                                                                   ");
        sql.append("           ASS.IBF_ID,                                                                          ");
        sql.append("           ASS.IBF_NAME,                                                                        ");
        sql.append("           ASS.ASSIGNATION_CHARGE,                                                              ");
        sql.append("           ASS.ID ASSID,                                                                        ");
        sql.append("           ASTAFF.IS_DELETE,                                                                    ");
        sql.append("           ASTAFF.CREATEDATE,                                                                   ");
        sql.append("           ASS.SX,                                                                              ");
        sql.append("           SI.IS_NUMBER,                                                                        ");
        sql.append("           SR.DEPTNAME,                                                                         ");
        sql.append("           SI.ADD_OR_LESS,                                                                      ");
        sql.append("           PERIODDATA.NAME, SA.ID Check_ID, SA.COMPANY_ID,                                      ");
        sql.append("           ASTAFF.COMPANY_NAME, ASTAFF.DEPT_NAME, ASTAFF.GW_NAME, ASTAFF.DEPT_ID,               ");
        sql.append("           ASTAFF.IS_ALL_MONTH, PERIODDATA.START_DATE, ASTAFF.XCGW_ID                ");
        sql.append("         FROM XC_ASSIGNATION_STAFF ASTAFF                                                       ");
        sql.append("         LEFT JOIN XC_SALARY_RECORD SR                                                          ");
        sql.append("         ON SR.STAFF_ID = ASTAFF.STAFF_ID                                                       ");
        sql.append("         LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                              ");
        sql.append("         ON ASTAFF.ID      = ASS.ASSIGNATION_STAFF_ID                                           ");
        sql.append("         AND ASS.IS_DEFAULT_DISPLAY = 1                 ");
        sql.append("         AND ASS.IS_DELETE = 0                                                                  ");
        sql.append("         LEFT JOIN XC_SALARY_ITEMS SI                                                           ");
        sql.append("         ON ASS.IBF_ID                      = SI.ID                                             ");
        sql.append("         LEFT JOIN XC_SALARY_ASSIGNATION SA                                                     ");
        sql.append("         ON ASTAFF.SALARY_ASSIGNATION_ID = SA.ID                                            ");
        sql.append("         LEFT JOIN XC_PERIODDATA PERIODDATA                                                     ");
        sql.append("         ON PERIODDATA.ID = SA.SALARY_PERIOD                                                ");
        sql.append("         WHERE ASTAFF.SALARY_ASSIGNATION_ID = '"+id+"'                ");
        sql.append("         AND ASTAFF.IS_DELETE               = 0                                                 ");
        sql.append("         ORDER BY ASTAFF.CREATEDATE DESC, ASTAFF.STAFF_ID ASC, ASS.SX ASC                       ");
        sql.append("         ) TT                                                                                   ");
        sql.append("       WHERE TT.IS_DELETE = 0                                                                   ");
        sql.append(conSql);
        sql.append("       GROUP BY TT.ID, TT.CREATEDATE                                                            ");
        sql.append("       ORDER BY TT.CREATEDATE DESC                                                              ");
        sql.append("       ) CUR                                                                                    ");
        sql.append(choseItem);
        sql.append("     UNION ALL                                                                                  ");
        sql.append("     SELECT *                                                                                   ");
        sql.append("     FROM                                                                                       ");
        sql.append("       (SELECT TT.ID,                                                                           ");
        sql.append("         MIN(TT.STAFF_ID) STAFFID,                                                              ");
        sql.append("         MIN(TT.JOB_NUMBER) JOBNUMBER,                                                          ");
        sql.append("         MIN(TT.STAFF_NAME) NAME,                                                               ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.IBF_ID ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) ITEMSID,                          ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.IBF_NAME ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) ITEMSNAME,                  ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.ASSIGNATION_CHARGE ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) CHARGE,                  ");
        sql.append("         CONCAT_ARRAY(CAST(COLLECT(TT.ASSID ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_VARCHAR2)) ASSID,                  ");
        sql.append("         CONCAT_ARRAY_NUMBER(CAST(COLLECT(TT.IS_NUMBER ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_NUMBER)) IS_NUMBER,                  ");
        sql.append("         MIN(TT.DEPTNAME) DEPART_ID,                                                            ");
        sql.append("         CONCAT_ARRAY_NUMBER(CAST(COLLECT(TT.ADD_OR_LESS ORDER BY TT.SX ASC, TT.IBF_ID ASC) AS TAB_NUMBER)) ADD_OR_LESS,                  ");
        sql.append("         '2' typ,                                                            ");
        sql.append("         MIN(TT.NAME) PeriodName, MIN(TT.Check_ID) Check_ID, MIN(TT.COMPANY_ID) COMPANY_ID,                 ");
        sql.append("         MIN(TT.COMPANY_NAME) COMPANY_NAME, MIN(TT.DEPT_NAME) DEPT_NAME, MIN(TT.GW_NAME) GW_NAME, ");
        sql.append("         MIN(TT.IS_ALL_MONTH) IS_ALL_MONTH, MIN(TT.START_DATE) START_DATE,  ");
        sql.append("         MIN(TT.DEPT_ID) DEPT_ID, MIN(TT.XCGW_ID) XCGW_ID ");
        sql.append("       FROM                                                                                     ");
        sql.append("         (SELECT ASTAFF.ID,                                                                     ");
        sql.append("           ASTAFF.STAFF_ID,                                                                     ");
        sql.append("           ASTAFF.JOB_NUMBER,                                                                   ");
        sql.append("           ASTAFF.STAFF_NAME,                                                                   ");
        sql.append("           ASS.IBF_ID,                                                                          ");
        sql.append("           ASS.IBF_NAME,                                                                        ");
        sql.append("           ASS.ASSIGNATION_CHARGE,                                                              ");
        sql.append("           ASS.ID ASSID,                                                                        ");
        sql.append("           ASTAFF.IS_DELETE,                                                                    ");
        sql.append("           ASTAFF.CREATEDATE,                                                                   ");
        sql.append("           ASS.SX,                                                                              ");
        sql.append("           SI.IS_NUMBER,                                                                        ");
        sql.append("           SR.DEPTNAME,                                                                         ");
        sql.append("           SI.ADD_OR_LESS,                                                                      ");
        sql.append("           PERIODDATA.NAME, SA.ID Check_ID, SA.COMPANY_ID,                                      ");
        sql.append("           ASTAFF.COMPANY_NAME, ASTAFF.DEPT_NAME, ASTAFF.GW_NAME, ASTAFF.DEPT_ID,              ");
        sql.append("           ASTAFF.IS_ALL_MONTH, PERIODDATA.START_DATE, ASTAFF.XCGW_ID          ");
        sql.append("         FROM XC_ASSIGNATION_STAFF ASTAFF                                                       ");
        sql.append("         LEFT JOIN XC_SALARY_RECORD SR                                                          ");
        sql.append("         ON SR.STAFF_ID = ASTAFF.STAFF_ID                                                       ");
        sql.append("         LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                              ");
        sql.append("         ON ASTAFF.ID      = ASS.ASSIGNATION_STAFF_ID                                           ");
        sql.append("         AND ASS.IS_DEFAULT_DISPLAY = 1                 ");
        sql.append("         AND ASS.IS_DELETE = 0                                                                  ");
        sql.append("         LEFT JOIN XC_SALARY_ITEMS SI                                                           ");
        sql.append("         ON ASS.IBF_ID = SI.ID                                                                  ");
        sql.append("         LEFT JOIN XC_SALARY_ASSIGNATION SA                                                     ");
        sql.append("         ON ASTAFF.SALARY_ASSIGNATION_ID = SA.ID                                                ");
        sql.append("         LEFT JOIN XC_PERIODDATA PERIODDATA                                                     ");
        sql.append("         ON PERIODDATA.ID = SA.SALARY_PERIOD                                                ");
        sql.append("         WHERE SA.SALARY_GROUP_ID        = '"+conditionMap.get("groupId")+"'                    ");
        sql.append("         AND SA.SALARY_WAGE_ID           = '"+conditionMap.get("salaryWageId")+"'               ");
//        sql.append("         AND SA.SALARY_PERIOD            = '"+ lastPeriiodId +"'                   ");
        sql.append("         AND SA.SALARY_PERIOD            IN ('"+StringUtils.join(lastPeriiodId.split(","), "','")+"')                   ");
        sql.append("         AND SA.COMPANY_ID               = '"+conditionMap.get("companyId")+"'                   ");
        sql.append("         AND ASTAFF.IS_DELETE            = 0                                                    ");
        sql.append("         ORDER BY ASTAFF.CREATEDATE DESC, ASTAFF.STAFF_ID ASC, ASS.SX ASC                       ");
        sql.append("         ) TT                                                                                   ");
        sql.append("       WHERE TT.IS_DELETE = 0                                                                   ");
        sql.append(conSql);
        sql.append("       GROUP BY TT.ID, TT.CREATEDATE                                                            ");
        sql.append("       ORDER BY TT.CREATEDATE DESC                                                              ");
        sql.append("       ) CUR2                                                                                   ");
        sql.append("     ) CDATA                                                                                    ");
        sql.append("   LEFT JOIN XC_CHECK_MONTH_SALARY CMS                                                          ");
        sql.append("   ON CMS.CHECK_ID = CDATA.CHECK_ID                                                          ");
        sql.append("   AND CMS.STAFF_ID = CDATA.STAFFID                                                          ");
        sql.append("   AND CMS.IS_DELETE = 0                                                                     ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QX      ");
        sql.append("     ON QX.ID = CDATA.DEPT_ID        ");
        sql.append("     LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD      ");
        sql.append("     ON SDD.ID = CDATA.XCGW_ID        ");
        sql.append("   ORDER BY QX.DESCRIPTION ASC, SDD.ZWBM ASC                                         ");

        return sql.toString();
    }

    @Override
    public String getAssignationForStaffDataSql(String id, String ids, String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                                               ");
        sql.append("     ASTAFF.ID,                                                                                                         ");
        sql.append("     MIN(ASTAFF.STAFF_ID) STAFFID,                                                                                      ");
        sql.append("     MIN(ASTAFF.JOB_NUMBER) JOBNUMBER,                                                                                  ");
        sql.append("     MIN(ASTAFF.STAFF_NAME) NAME,                                                                                       ");
        sql.append("     CONCAT_ARRAY(CAST(COLLECT(ASS.IBF_ID ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) ITEMSID,               ");
        sql.append("     CONCAT_ARRAY(CAST(COLLECT(ASS.IBF_NAME ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) ITEMSNAME,           ");
        sql.append("     CONCAT_ARRAY(CAST(COLLECT(ASS.ASSIGNATION_CHARGE ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) CHARGE,    ");
        sql.append("     CONCAT_ARRAY(CAST(COLLECT(ASS.ID ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) ASSID,                     ");
        sql.append("     CONCAT_ARRAY_NUMBER(CAST(COLLECT(ASS.IS_NUMBER ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_NUMBER)) IS_NUMBER,     ");
        sql.append("     MIN(ASTAFF.DEPT_ID) DEPART_ID,                                                                                     ");
        sql.append("     CONCAT_ARRAY_NUMBER(CAST(COLLECT(ASS.ADD_OR_LESS ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_NUMBER)) ADD_OR_LESS, ");
        sql.append("     MIN(ASTAFF.COMPANY_NAME) COMPANY_NAME,                                                                             ");
        sql.append("     MIN(ASTAFF.DEPT_NAME) DEPT_NAME,                                                                                   ");
        sql.append("     MIN(ASTAFF.GW_NAME) GW_NAME,                                                                                       ");
        sql.append("     MIN(ASTAFF.IS_ALL_MONTH) IS_ALL_MONTH,                                                                             ");
        sql.append("     MIN(ASTAFF.BZGW_NAME) BZGW_NAME,                                                                                   ");
        sql.append("     MIN(ASTAFF.XCGW_NAME) XCGW_NAME                                                                                    ");
        sql.append("   FROM XC_ASSIGNATION_STAFF ASTAFF                                                                                     ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                                                            ");
        sql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID                                                                              ");
        sql.append("   AND ASS.IS_DEFAULT_DISPLAY = 1                                                                                       ");
        sql.append("   AND ASS.IS_DELETE          = 0                                                                                       ");
        sql.append("   LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                                                              ");
        sql.append("   ON SDD.ID = ASTAFF.XCGW_ID                                                                                           ");
        sql.append("   WHERE ASTAFF.SALARY_ASSIGNATION_ID = '"+id+"'                                                                       ");
        sql.append("   AND ASTAFF.IS_DELETE = 0                                                                                             ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 职工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("  AND ASTAFF.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                                 ");
            }
            // 职工名称
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("  AND ASTAFF.STAFF_NAME LIKE '%"+mapData.get("name").trim()+"%'                                 ");
            }
            // 部门
            if (!StringUtils.isBlank(mapData.get("deptId"))) {
                sql.append("  AND ASTAFF.DEPT_ID = '"+mapData.get("deptId").trim()+"'                                 ");
            }
        }

        // 核算数据id
        if (!StringUtils.isBlank(ids)) {
            String newSql = LingUtil.getOracleInMethodDeal(" ASTAFF.ID", 800, Arrays.asList(ids.split(",")));
            sql.append("  AND " + newSql);
        }
        sql.append("   GROUP BY ASTAFF.ID                                                                                                   ");
        sql.append("   ORDER BY MIN(SDD.ZWBM) ASC, MIN(ASTAFF.JOB_NUMBER) ASC                                                               ");

        return sql.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getTotalData(String id) {
        StringBuilder summarySql = new StringBuilder();
        summarySql.append("    SELECT                                                          ");
        summarySql.append("        ASS.IBF_ID IBFID,                                           ");
        summarySql.append("        SUM( CASE WHEN ASS.ADD_OR_LESS = 2 THEN -TO_NUMBER(NVL(ASS.ASSIGNATION_CHARGE, 0))      ");
        summarySql.append("                 ELSE TO_NUMBER(NVL(ASS.ASSIGNATION_CHARGE, 0)) END) CHARGE,      ");
        summarySql.append("        MIN(NVL(ASS.NUMBER_ACCURACY, 0)) XS                          ");
        summarySql.append("      FROM XC_ASSIGNATION_STAFF ASTAFF                              ");
        summarySql.append("      LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                     ");
        summarySql.append("      ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID                       ");
        summarySql.append("      AND ASS.IS_DELETE = 0                                         ");
        summarySql.append("      WHERE ASTAFF.SALARY_ASSIGNATION_ID = '"+id+"'                 ");
        summarySql.append("      AND ASTAFF.IS_DELETE = 0                                      ");
        summarySql.append("      AND ASS.IS_NUMBER = 1  ");// 数字验证
        summarySql.append("    GROUP BY ASS.IBF_ID,ASS.SX                                      ");
        summarySql.append("    ORDER BY ASS.SX ASC                                             ");
        SQLQuery summaryQuery = getSession().createSQLQuery(summarySql.toString());
        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> summaryList = summaryQuery.list();

        return summaryList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getDeptTotalData(String id) {
        StringBuilder summarySql = new StringBuilder();
        summarySql.append("    SELECT                                                          ");
        summarySql.append("        MIN(ASS.IBF_ID) IBFID,                                           ");
        summarySql.append("        SUM( CASE WHEN ASS.ADD_OR_LESS = 2 THEN -TO_NUMBER(NVL(ASS.ASSIGNATION_CHARGE, 0))      ");
        summarySql.append("                 ELSE TO_NUMBER(NVL(ASS.ASSIGNATION_CHARGE, 0)) END) CHARGE,      ");
        summarySql.append("        MIN(NVL(ASS.NUMBER_ACCURACY, 0)) XS                          ");
        summarySql.append("      FROM XC_ASSIGNATION_STAFF ASTAFF                              ");
        summarySql.append("      LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                     ");
        summarySql.append("      ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID                       ");
        summarySql.append("      AND ASS.IS_DELETE = 0                                         ");
        summarySql.append("      WHERE ASTAFF.SALARY_ASSIGNATION_ID = '"+id+"'                 ");
        summarySql.append("      AND ASTAFF.IS_DELETE = 0                                      ");
        summarySql.append("      AND ASS.IS_NUMBER = 1  ");// 数字验证
        summarySql.append("    GROUP BY ASS.IBF_ID,ASS.SX                                      ");
        summarySql.append("    ORDER BY ASS.SX ASC                                             ");
        SQLQuery summaryQuery = getSession().createSQLQuery(summarySql.toString());
        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> summaryList = summaryQuery.list();
        
        return summaryList;
    }

    @Override
    public List<Map<String, Object>> getSelectedStaffAssignData(String assignStaffId, String assignId, String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                    ");
        sql.append("   CONCAT_ARRAY(CAST(COLLECT(ASS.ID ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) ID,   ");
        sql.append("   CONCAT_ARRAY(CAST(COLLECT(ASS.IBF_ID ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) ITEMSID,   ");
        sql.append("   CONCAT_ARRAY(CAST(COLLECT(TO_CHAR(NVL(SF.CNT_MATH, 'result=0')) ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) FORMULA   ");
        sql.append("   FROM XC_ASSIGNATION_STAFF ASTAFF                          ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                 ");
        sql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID                   ");
        sql.append("   AND ASS.IS_DELETE = 0                                     ");
        sql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                        ");
        sql.append("   ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID                   ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW                        ");
        sql.append("   ON SA.SALARY_WAGE_ID = SGW.ID                             ");
        sql.append("   AND SGW.IS_DELETE = 0                                     ");
        sql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                     ");
        sql.append("   ON SGW.ID = GWAY.WAGE_ID                                  ");
        sql.append("   AND GWAY.IS_DELETE = 0                                    ");
        sql.append("   AND GWAY.ITEM_ID = ASS.IBF_ID                             ");
        sql.append("   LEFT JOIN XC_SALARY_FORMULA SF                            ");
//        sql.append("   ON GWAY.ID = SF.BIND_ID                                   ");
        sql.append("   ON (GWAY.ID = SF.BIND_ID OR GWAY.SALARY_WAGE_ID = SF.BIND_ID)   ");
        sql.append("   AND SF.IS_DELETE = 0                                      ");
        sql.append("   AND SF.IS_DEFAULT = 1                                     ");
        sql.append("   WHERE ASTAFF.IS_DELETE = 0                                  ");

        // 如果薪酬分配过程ID存在说明全表操作
        if (!StringUtils.isBlank(assignStaffId)) {
            sql.append("   AND SA.ID = '" + assignStaffId + "'");
        } else {
            String newSql = LingUtil.getOracleInMethodDeal(" ASTAFF.ID", 800, Arrays.asList(assignId.split(",")));
            sql.append("   AND " + newSql);

            // 如果薪资项目不存在说明全部的薪资项目参与计算
            if (!StringUtils.isBlank(id)) {
                sql.append("   AND ASS.IBF_ID = '" + id + "'");
            }
        }
        sql.append("   GROUP BY ASTAFF.ID                        ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", LingUtil.ClobToString((Clob)obj[0]));
            map.put("itemsId", LingUtil.ClobToString((Clob)obj[1]));
            map.put("formula", LingUtil.ClobToString((Clob)obj[2]));
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public List<Map<String, String>> getAllGroupWageFinancialList(String assignStaffId, String itemid) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT DISTINCT                                    ");
        sql.append("   SF.ID, TO_CHAR(SF.CONTENT) CONTENT, TO_CHAR(SF.CNT_MATH) CNTMATH           ");
        sql.append("   FROM XC_SALARY_FORMULA SF                          ");
        sql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY              ");
        sql.append("   ON (GWAY.ID = SF.BIND_ID OR GWAY.SALARY_WAGE_ID = SF.BIND_ID)                            ");
        sql.append("   AND GWAY.IS_DELETE = 0                             ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW                 ");
        sql.append("   ON SGW.ID = GWAY.WAGE_ID                           ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS          ");
        sql.append("   ON GWAY.ITEM_ID = ASS.IBF_ID                       ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF              ");
        sql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID            ");
        sql.append("   AND ASTAFF.IS_DELETE = 0                           ");
        sql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                 ");
        sql.append("   ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID            ");
        sql.append("   WHERE SF.IS_DELETE = 0                             ");
        sql.append("   AND SA.ID = '"+assignStaffId+"'     ");
        sql.append("   AND ASS.IBF_ID = '"+itemid+"'");

        List<?> list = findBySql(sql.toString());
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", obj[0]+"");
            try {
                map.put("cntMath", obj[2].toString());
                map.put("content", obj[1].toString());
            } catch (Exception e) {
                map.put("cntMath", "");
                map.put("content", "");
            }
            map.put("type", "1");
            dataList.add(map);
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "0");
        map.put("content", "简单计算公式");
        map.put("type", "2");
        dataList.add(map);

        return dataList;
    }

    @Override
    public Map<String, Object> getNeedCheckStaffData(String id, Pager pager) {
        // 获取薪酬核算过程中的薪资组ID与工资套ID
        String selectAssign = "SELECT SALARY_GROUP_ID, SALARY_WAGE_ID FROM XC_SALARY_ASSIGNATION WHERE ID = '"+id+"'";
        List<?> selectList = findBySql(selectAssign);
        String groupId = "";
        String wageId = "";
        if (selectList.size() > 0) {
            Object[] obj = (Object[]) selectList.get(0);
            groupId = obj[0].toString();
            wageId = obj[1].toString();
        }

        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT MIN(JBI.ID) ID,                                     ");
        sql.append("      MIN(JBI.JOB_NUMBER) JOBNUMBER,                   ");
        sql.append("      MIN(JBI.NAME) NAME,                              ");
        sql.append("      MIN(BRANCH.FZX) company,                         ");
        sql.append("      MIN(QD.NAME) deptName,                           ");
        sql.append("      MIN(JBI.POST) post,                              ");
        sql.append("  LISTAGG(SI.ID, ',;') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) ITEMSID,                           ");
        sql.append("  LISTAGG(SI.NAME, ',;') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) ITEMSNAME,                       ");
        sql.append("  LISTAGG(NVL(GWAY.SX, 0), ',;') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) SX,                       ");
        sql.append("      MIN(QD.ID) deptId,                     ");
        sql.append("      MIN(BRANCH.ID) company_Id,                        ");
        sql.append("  LISTAGG(NVL(SF.CNT_MATH, 0), ',;') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) FORMULA,              ");
        sql.append("  LISTAGG(NVL(SI.NUMBER_ACCURACY, 0), ',;') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) XS,            ");
        sql.append("  LISTAGG(NVL(SV.STATIC_VALUE, 0), ',;') WITHIN GROUP (ORDER BY GWAY.SX ASC) STATIC_VALUE,            ");
        sql.append("  MIN(NVL(SR.SPECIFIC_POST, ' ')) JTGW_ID, MIN(NVL(JTGW.POSITION_NAME, ' ')) JTGW_NAME,            ");
        sql.append("  LISTAGG(GWAY.IS_ER, ',;') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) IS_DISPLAY,            ");
        sql.append("  LISTAGG(SI.IS_NUMBER, ',') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) IS_NUMBER,                       ");
        sql.append("  LISTAGG(SI.ADD_OR_LESS, ',') WITHIN GROUP (ORDER BY GWAY.SX ASC, SI.ID ASC) ADD_OR_LESS,                    ");
        sql.append("  MIN(SR.FPQX) FPQX,                    ");
        sql.append("  MIN(NVL(SR.POST, ' ')) BZGW_ID, MIN(NVL(BZGW.POSITION_NAME, ' ')) BZGW_NAME,            ");
        sql.append("  MIN(NVL(SR.SALARY_POST, ' ')) XCGW_ID, MIN(NVL(SDD.NAME, ' ')) XCGW_NAME,            ");
        sql.append("  MIN(NVL(SR.GZXS, 0)) GZXS, MIN(NVL(JQD.DIC_NAME, ' ')) SPECIAL_MARK            ");
        sql.append("    FROM XC_SALARY_GROUP_WAGE SGW                      ");
        sql.append("    LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY              ");
        sql.append("    ON SGW.ID = GWAY.WAGE_ID                           ");
        sql.append("    AND GWAY.IS_DELETE = 0                             ");
        sql.append("    LEFT JOIN XC_SALARY_PERSONAL SP                    ");
        sql.append("    ON SGW.SALARY_GROUP_ID = SP.SALARY_GROUP_ID        ");
        sql.append("    AND SP.IS_DELETE       = 0                         ");
        sql.append("    LEFT JOIN XC_SALARY_RECORD SR                      ");
        sql.append("    ON SP.SALARY_RECORD_ID = SR.STAFF_ID               ");
        sql.append("    LEFT JOIN JC_BASIC_INFORMATION JBI                 ");
        sql.append("    ON SR.STAFF_ID = JBI.ID                            ");
        sql.append("    LEFT JOIN XC_SALARY_ITEMS SI                       ");
        sql.append("    ON GWAY.ITEM_ID = SI.ID                            ");
        sql.append("    LEFT JOIN QX_DEPARTMENT QD                         ");
        sql.append("    ON QD.ID = SR.DEPTNAME                           ");
        sql.append("    LEFT JOIN BRANCH                                   ");
        sql.append("    ON SR.FILM_NAME = BRANCH.ID                         ");
        sql.append("  LEFT JOIN XC_SALARY_FORMULA SF                                 ");
        sql.append("  ON (GWAY.ID = SF.BIND_ID OR GWAY.SALARY_WAGE_ID = SF.BIND_ID)  ");// 公式关联
        sql.append("  AND SF.IS_DELETE = 0                                        ");
        sql.append("  AND SF.IS_DEFAULT = 1                                        ");
        sql.append("  LEFT JOIN XC_SALARY_VALUE SV                                        ");
        sql.append("  ON SR.STAFF_ID = SV.STAFF_ID                                        ");
        sql.append("  AND GWAY.ITEM_ID = SV.SALARY_ITEMS_ID                                        ");
        sql.append("  AND SGW.SALARY_GROUP_ID = SV.SALARY_GROUP_ID                                 ");
        sql.append("  AND SV.IS_DELETE = 0                                        ");
        sql.append("  LEFT JOIN DEPT_POSITION JTGW                                                                 ");
        sql.append("    ON JTGW.ID = SR.SPECIFIC_POST                                                              ");
        sql.append("  LEFT JOIN POST_POSITION BZGW                                                                 ");
        sql.append("    ON BZGW.ID = SR.POST                                                              ");
        sql.append("  LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                                                 ");
        sql.append("    ON SR.SALARY_POST = SDD.ID                                                              ");
        sql.append("  LEFT JOIN JC_QX_DECTIONARY JQD                                                                 ");
        sql.append("    ON SR.SPECIAL_MARK = JQD.DIC_VALUE                                                              ");
        sql.append("   AND JQD.PID = '402881945be55610015be55f9d750010'                                                              ");
        sql.append("  LEFT JOIN JC_EMP_DIMISSION JED                                                                 ");
        sql.append("    ON JBI.JOB_NUMBER = JED.JOB_NUMBER                                                              ");
        sql.append("    WHERE SGW.SALARY_GROUP_ID = '"+groupId+"'          ");
        sql.append("    AND SGW.ID = '"+wageId+"'                          ");
        sql.append("    AND CASE WHEN JBI.ON_JOB = 3 THEN TO_CHAR (NVL(JED.LEAVE_DATE, SYSDATE),'yyyy-MM')     ");// 离职人员离职月可以显示，下一个月不显示
        sql.append("        ELSE TO_CHAR(SYSDATE, 'yyyy-MM') END >= TO_CHAR(SYSDATE, 'yyyy-MM')     ");
        sql.append("    AND JBI.IS_DELETE=0                            ");
        sql.append("    AND JBI.ID NOT IN                                  ");
        sql.append("      (SELECT ASSTAFF.STAFF_ID                         ");
        sql.append("      FROM XC_SALARY_ASSIGNATION SA                    ");
        sql.append("      LEFT JOIN XC_ASSIGNATION_STAFF ASSTAFF           ");
        sql.append("      ON SA.ID    = ASSTAFF.SALARY_ASSIGNATION_ID      ");
        sql.append("      WHERE SA.ID = '"+id+"' ");
        sql.append("      AND ASSTAFF.IS_DELETE = 0                        ");
        sql.append("      )                                                ");

        Map<String, String> mapData = JsonUtil.parseProperties(pager.getSearchData());
        if (mapData != null) {
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                        ");
            }
        }
        sql.append("    GROUP BY SR.ID                     ");
        sql.append("    ORDER BY MIN(SR.CREATEDATE) DESC                       ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("company", obj[3]);
            map.put("deptName", obj[4]);
            map.put("post", obj[5]);
            map.put("itemsId", obj[6]);
            map.put("itemsName", obj[7]);
            map.put("sx", obj[8]);
            map.put("deptId", obj[9]);
            map.put("companyId", obj[10]);
            map.put("formula", obj[11]);
            map.put("xs", obj[12]);
            map.put("salaryValue", obj[13]);
            map.put("jtgwId", obj[14]);
            map.put("jtgwName", obj[15]);
            map.put("isDisplay", obj[16]);
            map.put("isNumber", obj[17]);
            map.put("addOrLess", obj[18]);
            map.put("fpqx", obj[19]);
            map.put("bzgwId", obj[20]);
            map.put("bzgwName", obj[21]);
            map.put("xcgwId", obj[22]);
            map.put("xcgwName", obj[23]);
            map.put("gzxs", obj[24]);
            map.put("specialMark", obj[25]);
            dataList.add(map);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public String getLastMonthSalaryPeriod(String salaryPeriod) {
        Map<String, Object> map = new HashMap<String, Object>();
        Perioddata perioddata = salaryAssignationService.get(Perioddata.class, Restrictions.eq("id", salaryPeriod));
        if (perioddata != null) {
            Period period = salaryAssignationService.get(Period.class, Restrictions.eq("id", perioddata.getPeriodId()));
            if (period == null) {
                map.put("success", 0);
                map.put("info", perioddata.getName() + "薪酬期间不存在");

                return JsonUtil.Encode(map);
            } else if (period.getIsDelete() == 1) {
                map.put("success", 0);
                map.put("info", period.getYear() + "年度的薪酬期间不存在，已经被删除");

                return JsonUtil.Encode(map);
            }
            // 本月开始日期
            Date curDate = perioddata.getStartDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            calendar.add(Calendar.MONTH, -1);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;

            // 查找上月
            Period lastPeriod = salaryAssignationService.get(Period.class,
                    Restrictions.eq("id", perioddata.getPeriodId()),
                    Restrictions.eq("year", year),
                    Restrictions.eq("isDelete", 0));
            if (lastPeriod == null) {
                map.put("success", 0);
                map.put("info", year + "年度的薪酬期间不存在");

                return JsonUtil.Encode(map);
            }

            StringBuilder sql = new StringBuilder();
            sql.append("   SELECT PE.ID, PE.START_DATE, PE.END_DATE         ");
            sql.append("   FROM XC_PERIOD P                                 ");
            sql.append("   LEFT JOIN XC_PERIODDATA PE                       ");
            sql.append("   ON P.ID = PE.PERIOD_ID                           ");
            sql.append("   WHERE P.YEAR = "+year+"                          ");
            sql.append("   AND P.DEP_ID = '"+lastPeriod.getDepId()+"'       ");
            sql.append("   AND P.IS_DELETE = 0                              ");
            sql.append("   AND TO_CHAR(PE.START_DATE, 'yyyy-MM') = '"+year+"-"+String.format("%02d", month)+"'");

            List<?> list = findBySql(sql.toString());
            if (list.size() > 0) {
                Object[] obj = (Object[]) list.get(0);

                map.put("success", 1);
                map.put("info", obj[0].toString());

                return JsonUtil.Encode(map);
            }
        }

        map.put("success", 0);
        map.put("info", perioddata.getName() + "薪酬期间不存在");

        return JsonUtil.Encode(map);
    }

    @Override
    public List<Map<String, Object>> getFpqxPeopleCount(String assianId, String companyId, String fpqx, String salaryPeriodId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT COUNT(ASTAFF.ID) PEOPLE,          "); 
        sql.append("     MIN(SR.FPQX) FPQX, SR.DEPTNAME                      ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA         ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF         ");
        sql.append("   ON ASTAFF.SALARY_ASSIGNATION_ID = SA.ID         ");
//        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI       ");
//        sql.append("   ON ASTAFF.STAFF_ID = JBI.ID              ");
        sql.append("   LEFT JOIN XC_SALARY_RECORD SR            ");
        sql.append("   ON ASTAFF.STAFF_ID = SR.STAFF_ID         ");
        sql.append("   AND ASTAFF.DEPT_ID = SR.DEPTNAME         ");
        sql.append("   WHERE SA.SALARY_PERIOD = '"+salaryPeriodId+"'  ");
        sql.append("   AND SA.ID = '"+assianId+"'                 ");
        sql.append("   AND SA.COMPANY_ID = '"+companyId+"'                 ");
        sql.append("   AND SR.FPQX = "+fpqx+"                 ");
        sql.append("   AND ASTAFF.IS_DELETE = 0                 ");
        sql.append("   GROUP BY SR.DEPTNAME                     ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("peoples", obj[0]);
            map.put("fpqx", obj[1]);
            map.put("deptId", obj[2]);
            list2.add(map);
        }

        return list2;
    }

    @Override
    public String[] getAuthCompany() {
        String userId = LingUtil.userinfo().getId();
        String conpanyId = LingUtil.userinfo().getCid();

        // 获取下属有权限的公司
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT B.ID                                 ");
        sql.append("    FROM BRANCH B                               ");
        sql.append("    INNER JOIN QX_USER_DATAUTH UD               ");
        sql.append("    ON UD.BRANCH_DEP =B.ID                      ");
        sql.append("    AND UD.FLG       ='0'                       ");
        sql.append("    AND UD.USERID    = '"+userId+"'             ");
        sql.append("    AND (B.ID='"+conpanyId+"' OR B.PID = '"+conpanyId+"')                 ");
        sql.append("    WHERE B.IS_DELETE = 0                       ");

        List<?> data = this.findBySql(sql.toString());
        String[] obj = data.toArray(new String[data.size()]);

        return obj;
    }

    @Override
    public String getNeedSendSalaryRecordsSql(String ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT ID,STAFF_ID,JOB_NUMBER,STAFF_NAME,ITEM_ID,IBF_NAME,ASSIGNATION_CHARGE,                  ");
        sql.append("        COMPANY_ID,COMPANY_NAME,DEPT_ID,DEPT_NAME,SX                  ");
        sql.append("     FROM                                                                                       ");
        sql.append("       (SELECT SA.ID,                                                                           ");
        sql.append("         ASTAFF.STAFF_ID,                                                         ");
        sql.append("         MIN(ASTAFF.JOB_NUMBER) JOB_NUMBER,                                                     ");
        sql.append("         MIN(ASTAFF.STAFF_NAME) STAFF_NAME,                                                     ");
        sql.append("         LISTAGG(ASS.IBF_ID, ',') WITHIN GROUP (ORDER BY PS.SX ASC,PS.ITEM_ID ASC) ITEM_ID,          ");
        sql.append("         LISTAGG(ASS.IBF_NAME, ',') WITHIN GROUP (ORDER BY PS.SX ASC,PS.ITEM_ID ASC) IBF_NAME,       ");
        sql.append("         LISTAGG(ASS.ASSIGNATION_CHARGE, ',') WITHIN GROUP (ORDER BY PS.SX ASC,PS.ITEM_ID ASC) ASSIGNATION_CHARGE,");
        sql.append("         MAX(CASE WHEN SP.NO_SEND = ASS.IBF_ID AND TO_NUMBER(ASS.ASSIGNATION_CHARGE) = 0 THEN 1      ");
        sql.append("           ELSE 0 END) NO_SEND,                                                                 ");
        sql.append("         MIN(ASTAFF.COMPANY_ID) COMPANY_ID, MIN(ASTAFF.COMPANY_NAME) COMPANY_NAME,                                                   ");
        sql.append("         MIN(ASTAFF.DEPT_ID) DEPT_ID, MIN(ASTAFF.DEPT_NAME) DEPT_NAME,                                               ");
        sql.append("         LISTAGG(PS.SX, ',') WITHIN GROUP (ORDER BY PS.SX ASC,PS.ITEM_ID ASC) SX");
        sql.append("       FROM XC_SALARY_ASSIGNATION SA                                              ");
        sql.append("       LEFT JOIN XC_SALARY_GROUP_WAGE SGW                                         ");
        sql.append("       ON SA.SALARY_WAGE_ID = SGW.ID                                              ");
        sql.append("       AND SGW.IS_DELETE    = 0                                                   ");
        sql.append("       AND SA.COMPANY_ID    = SGW.COMPANY_ID                                      ");
        sql.append("       LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                                      ");
        sql.append("       ON ASTAFF.SALARY_ASSIGNATION_ID = SA.ID                                    ");
        sql.append("       AND ASTAFF.IS_DELETE            = 0                                        ");
        sql.append("       LEFT JOIN XC_SALARY_RECORD SR                                              ");
        sql.append("       ON SR.STAFF_ID = ASTAFF.STAFF_ID                                           ");
        sql.append("       LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                  ");
        sql.append("       ON ASTAFF.ID      = ASS.ASSIGNATION_STAFF_ID                               ");
        sql.append("       AND ASS.IS_DELETE = 0                                                      ");
        sql.append("       LEFT JOIN XC_SALARY_PART SP                                                ");
        sql.append("       ON SGW.ID         = SP.GROUP_WAGE_AND_TYPE_ID                              ");
        sql.append("       AND SP.IS_DELETE  = 0                                                      ");
        sql.append("       AND SP.IS_DEFAULT = 1                                                      ");
        sql.append("       RIGHT JOIN XC_PART_SALARY PS                                               ");
        sql.append("       ON SP.ID         = PS.PART_ID                                              ");
        sql.append("       AND PS.IS_DELETE = 0                                                       ");
        sql.append("       AND ASS.IBF_ID   = PS.ITEM_ID                                              ");
        sql.append("       WHERE SA.IS_DELETE = 0                                                     ");
        sql.append("       AND SA.ID IN ('"+StringUtils.join(ids.split(","), "','")+"')               ");
        sql.append("       AND CASE WHEN TO_NUMBER(ASS.ASSIGNATION_CHARGE) = 0 THEN PS.IS_ZREO        ");
        sql.append("           ELSE 1 END = 1                                                         ");
        sql.append("       AND SR.ZFXS = 1                                                            ");// 自发薪可以发工资条
        sql.append("       GROUP BY SA.ID, ASTAFF.STAFF_ID                                            ");
        sql.append("       ORDER BY SA.ID ASC,ASTAFF.STAFF_ID ASC) ASSIGN                                                           ");
        sql.append("     WHERE ASSIGN.NO_SEND = 0                                                                   ");

        return sql.toString();
    }

    @Override
    public String getSelectReCalculateStaffsItem(String ids, String itemID) {
        StringBuilder sql = new StringBuilder();
        sql.append("      SELECT                                        ");
        sql.append("        ASS.ID, ASTAFF.STAFF_ID,SI.NUMBER_ACCURACY, ASTAFF.COMPANY_ID, ASTAFF.SALARY_ASSIGNATION_ID  ");
        sql.append("      FROM XC_ASSIGNATION_STAFF ASTAFF              ");
        sql.append("      LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS     ");
        sql.append("      ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID       ");
        sql.append("      AND ASS.IS_DELETE = 0                         ");
        sql.append("      LEFT JOIN XC_SALARY_ITEMS SI                  ");
        sql.append("      ON ASS.IBF_ID = SI.ID                         ");
        sql.append("      WHERE ASTAFF.ID IN ('"+StringUtils.join(ids.split(","), "','")+"')                            ");
        sql.append("      AND ASS.IBF_ID = '"+itemID+"'                           ");

        return sql.toString();
    }

    @Override
    public String getStaffItemsCharge(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                ");
        sql.append("     ASTAFF.STAFF_ID, ASS.IBF_ID,NVL(ASS.ASSIGNATION_CHARGE, 0) ASSIGNATION_CHARGE, ");
        sql.append("     SAI.ID, SAI.SALARY_ITEMS_NAME, SAGAIN.REPORT_STATUS                    ");
        sql.append("   FROM XC_ASSIGNATION_STAFF ASTAFF                      ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS             ");
        sql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID               ");
        sql.append("   AND ASS.IS_DELETE = 0                                 ");
        sql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                    ");
        sql.append("   ON ASTAFF.SALARY_ASSIGNATION_ID = SA.ID               ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN SAGAIN                      ");
        sql.append("   ON SA.COMPANY_ID = SAGAIN.COMPANY_ID                  ");
        sql.append("   AND SA.SALARY_PERIOD = SAGAIN.FP_DATE                 ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN_ITEMS SAI                   ");
        sql.append("   ON SAGAIN.ID = SAI.SALARY_AGAIN_ID                    ");
        sql.append("   AND SAI.STAFF_ID = ASTAFF.STAFF_ID                    ");
        sql.append("   AND ASS.IBF_ID = SAI.SALARY_ITEMS_ID                  ");
        sql.append("   WHERE ASTAFF.IS_DELETE = 0                            ");
        sql.append("   AND ASTAFF.SALARY_ASSIGNATION_ID = '"+id+"'                 ");
        sql.append("   AND SAGAIN.IS_DELETE = 0                              ");
        sql.append("   AND SAI.IS_DELETE = 0                                 ");
        sql.append("   ORDER BY ASTAFF.STAFF_ID ASC                          ");

        return sql.toString();
    }

    @Override
    public String getSecondOtherItemsData(String companyId, String deptId,
            String fpqx, String salaryPeriod) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                     ");
        sql.append("   SAI.SALARY_ITEMS_ID, MIN(SAI.SALARY_ITEMS_NAME) ITEMNAME,  ");
        sql.append("   SUM(NVL(SV.STATIC_VALUE, 0)) STATIC_VALUE,MIN(GWAY.SX) SX,                ");
        sql.append("   MIN(SI.NUMBER_ACCURACY) NUMBER_ACCURACY                  ");
        sql.append("   FROM  XC_SALARY_ASSIGNATION SA                             ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN SAGIN                            ");
        sql.append("   ON SAGIN.COMPANY_ID  = SA.COMPANY_ID                       ");
        sql.append("   AND SA.SALARY_PERIOD = SAGIN.FP_DATE                       ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN_ITEMS SAI                        ");
        sql.append("   ON SAGIN.ID = SAI.SALARY_AGAIN_ID                          ");
        sql.append("   AND SAI.IS_DELETE = 0                                      ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW                         ");
        sql.append("   ON SA.SALARY_WAGE_ID   = SGW.ID                            ");
        sql.append("   AND SA.SALARY_GROUP_ID = SGW.SALARY_GROUP_ID               ");
        sql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                      ");
        sql.append("   ON SGW.ID = GWAY.WAGE_ID                                   ");
        sql.append("   AND SAI.SALARY_ITEMS_ID = GWAY.ITEM_ID                     ");
        sql.append("   LEFT JOIN XC_SALARY_VALUE SV                               ");
        sql.append("   ON SV.STAFF_ID = SAI.STAFF_ID                              ");
        sql.append("   AND SV.SALARY_ITEMS_ID = SAI.SALARY_ITEMS_ID               ");
        sql.append("   AND SV.SALARY_GROUP_ID = SA.SALARY_GROUP_ID                ");
        sql.append("   LEFT JOIN XC_SALARY_ITEMS SI                               ");
        sql.append("   ON SI.ID = SAI.SALARY_ITEMS_ID                              ");
        sql.append("   WHERE SA.IS_DELETE = 0                                     ");
        sql.append("   AND SAGIN.COMPANY_ID = '"+companyId+"'                                  ");
        sql.append("   AND SAGIN.DEP_ID = '"+deptId+"'                                      ");
        sql.append("   AND SAGIN.FP_DATE = '"+salaryPeriod+"'                               ");
        sql.append("   AND SAGIN.FPQX = "+fpqx+"                                     ");
        sql.append("   AND SAGIN.IS_DELETE = 0                                    ");
        sql.append("   AND GWAY.IS_DELETE = 0                                     ");
        sql.append("   AND GWAY.IS_ER = 1                                         ");
        sql.append("   AND GWAY.ZLJS != 1                                         ");
        sql.append("   GROUP BY SAI.SALARY_ITEMS_ID                               ");

        return sql.toString();
    }

}
