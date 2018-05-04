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
import com.lingnet.hcm.dao.salary.SalaryTotalAmountDao;
import com.lingnet.hcm.entity.salary.SalaryTotalAmount;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;

/**
 * 总量
 * @ClassName: SalaryTotalAmountDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月19日 上午7:57:22 
 *
 */
@Repository("salaryTotalAmountDao")
public class SalaryTotalAmountDaoImpl extends BaseDaoImplInit<SalaryTotalAmount, String> implements SalaryTotalAmountDao {

    @Override
    public Map<String, Object> getAmountListData(String companyId, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                              ");
        sql.append("     PERIODDATA.ID, MIN(PERIODDATA.NAME) PAYPERIOD,         ");
        sql.append("     MIN(AGAIN.IS_SP) IS_SP         ");
        sql.append("   FROM XC_SALARY_ASSIGNATION SA       ");
        sql.append("   LEFT JOIN XC_PERIODDATA PERIODDATA  ");
        sql.append("   ON SA.SALARY_PERIOD = PERIODDATA.ID ");
        sql.append("   LEFT JOIN XC_PERIOD PERIOD  ");
        sql.append("   ON PERIOD.ID = PERIODDATA.PERIOD_ID ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN AGAIN  ");
        sql.append("   ON AGAIN.COMPANY_ID = SA.COMPANY_ID ");
        sql.append("   AND AGAIN.FP_DATE = SA.SALARY_PERIOD ");
        sql.append("   WHERE SA.IS_DELETE  = 0             ");
        sql.append("     AND PERIOD.DEP_ID  = '"+companyId+"'             ");
        sql.append("     AND PERIOD.IS_DELETE = 0             ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("payPeriod"))) {
                sql.append("   AND PERIODDATA.NAME LIKE '%"+mapData.get("payPeriod").trim()+"%'                                        ");
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
            map.put("isSp", obj[2] == null? 0: obj[2]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getAmountForPeriodListData(String period, String companyId, Pager pager) {
        String join = StringUtils.join(period.split(","), "','");
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT STA.ID,                                                           ");
        sql.append("       MIN(STA.COMPANY_ID) COMPANY_ID,                                        ");
        sql.append("       MIN(BRANCH.FZX) COMPANY,                                               ");
        sql.append("       MIN(QD.ID) DEPTID,                                                     ");
        sql.append("       MIN(QD.NAME) DEPTNAME,                                                 ");
        sql.append("       MIN(STA.PEOPLE_COUNT) PEOPLECOUNT,                                     ");
        sql.append("       MIN(STA.LAST_MONTH_AMOUNT) LAST_MONTH_AMOUNT,                          ");
        sql.append("       MIN(STA.BYJXLDKH) BYJXLDKH,                                            ");
        sql.append("       MIN(STA.SPECIAL_REWARD) SPECIAL_REWARD,                                ");
        sql.append("       MIN(STA.OTHER_REWARD) OTHER_REWARD,                                    ");
        sql.append("       MIN(STA.REWARD_TOTAL) REWARD_TOTAL,                                    ");
        sql.append("       MIN(STA.BYKFPZL) BYKFPZL,                                              ");
        sql.append("       MIN(STA.CUR_MONTH_AMOUNT) CUR_MONTH_AMOUNT,                            ");
        sql.append("       MIN(STA.NOTE) NOTE,                                                    ");
        sql.append("       LISTAGG(NVL(YITEMS.ITEM_ID, ' '), ',') WITHIN GROUP (ORDER BY YITEMS.ITEM_ID ASC) ITEMID,                            ");
        sql.append("       LISTAGG(TO_NUMBER(NVL(YITEMS.STATIC_VALUE, 0)), ',') WITHIN GROUP (ORDER BY YITEMS.ITEM_ID ASC) STATIC_VALUE,        ");
        sql.append("       LISTAGG(NVL(SAGIN.SALARY_ITEMS_ID, ' '), ',') WITHIN GROUP (ORDER BY YITEMS.ITEM_ID ASC) ACTUAL_ITEMID,              ");
        sql.append("       LISTAGG(NVL(SAGIN.REPORT_AMOUNT, 0), ',') WITHIN GROUP (ORDER BY YITEMS.ITEM_ID ASC) REPORT_AMOUNT,                   ");
        sql.append("       LISTAGG(NVL(SAMI.SALARY_ITEMS_ID, ' '), ',') WITHIN GROUP (ORDER BY SAMI.SX ASC,SAMI.SALARY_ITEMS_ID ASC) otherItemsId, ");
        sql.append("       LISTAGG(NVL(SAMI.NEED_AMOUNT, ' '), ',') WITHIN GROUP (ORDER BY SAMI.SX ASC,SAMI.SALARY_ITEMS_ID ASC) otherNeedAmount,  ");
        sql.append("       LISTAGG(NVL(SAMI.ID, ' '), ',') WITHIN GROUP (ORDER BY SAMI.SX ASC,SAMI.SALARY_ITEMS_ID ASC) otherId,  ");
        sql.append("       MIN(QD.PARENT) PARENT,  ");
        sql.append("       MIN(STA.OTHER_REWARD2) OTHER_REWARD2,                                    ");
        sql.append("       MIN(STA.OTHER_REWARD3) OTHER_REWARD3                                    ");
        sql.append("     FROM XC_SALARY_TOTAL_AMOUNT STA                                          ");
        sql.append("     LEFT JOIN BRANCH                                                         ");
        sql.append("     ON STA.COMPANY_ID = BRANCH.ID                                            ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD                                               ");
        sql.append("     ON STA.DEP_ID = QD.ID                                                    ");
        sql.append("     LEFT JOIN                                                                ");
        sql.append("       (SELECT MIN(SA.COMPANY_ID) COMPANY_ID,                                 ");
        sql.append("         SR.DEPTNAME,                                                         ");
        sql.append("         GWAY.ITEM_ID,                                                        ");
        sql.append("         SUM(NVL(SV.STATIC_VALUE, 0)) STATIC_VALUE,                           ");
        sql.append("         MIN(SA.SALARY_PERIOD) SALARY_PERIOD                                  ");
        sql.append("       FROM XC_SALARY_ASSIGNATION SA                                          ");
        sql.append("       LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                                  ");
        sql.append("       ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID                                ");
        sql.append("       AND ASTAFF.IS_DELETE = 0                                               ");
        sql.append("       LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                              ");
        sql.append("       ON ASTAFF.ID      = ASS.ASSIGNATION_STAFF_ID                           ");
        sql.append("       AND ASS.IS_DELETE = 0                                                  ");
        sql.append("       LEFT JOIN XC_SALARY_GROUP_WAGE SGW                                     ");
        sql.append("       ON SA.SALARY_WAGE_ID   = SGW.ID                                        ");
        sql.append("       AND SA.SALARY_GROUP_ID = SGW.SALARY_GROUP_ID                           ");
        sql.append("       AND SA.COMPANY_ID = SGW.COMPANY_ID                           ");
        sql.append("       LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                                  ");
        sql.append("       ON SGW.ID          = GWAY.WAGE_ID                                      ");
        sql.append("       AND ASS.IBF_ID     = GWAY.ITEM_ID                                      ");
        sql.append("       AND GWAY.IS_DELETE = 0                                                 ");
        sql.append("       AND GWAY.IS_ER     = 1                                                 ");
        sql.append("       RIGHT JOIN XC_SALARY_RECORD SR                                         ");
        sql.append("       ON ASTAFF.STAFF_ID = SR.STAFF_ID                                       ");

        StringBuilder secondSqlText = new StringBuilder();
        StringBuilder sqlText = new StringBuilder();
        Map<String, String> search = JsonUtil.parseProperties(pager.getSearchData());
        String fpqx = "";
        if (search != null) {
            // 部门
            if (!StringUtils.isBlank(search.get("deptName"))) {
                sqlText.append("   AND QD.NAME LIKE '%"+search.get("deptName").trim()+"%'            ");
                secondSqlText.append("   AND QD.NAME LIKE '%"+search.get("deptName").trim()+"%'            ");
            }
            // 分配权限（基层、公司）
            if (!StringUtils.isBlank(search.get("fpqx"))) {
                fpqx = search.get("fpqx");
                secondSqlText.append("   AND SR.FPQX = "+search.get("fpqx")+"            ");
            }
        }

        sql.append("       AND SR.FPQX = "+fpqx+"                                                 ");
        sql.append("       LEFT JOIN XC_SALARY_VALUE SV                                           ");
        sql.append("       ON SV.STAFF_ID          = ASTAFF.STAFF_ID                              ");
        sql.append("       AND SV.SALARY_ITEMS_ID  = GWAY.ITEM_ID                                 ");
        sql.append("       WHERE SA.SALARY_PERIOD IN ('"+join+"')                                 ");
        sql.append("       AND SA.COMPANY_ID = '"+companyId+"'     ");
        sql.append("       AND SV.IS_DELETE        = 0                                            ");
        sql.append("       AND SA.IS_DELETE = 0                                            ");
        sql.append("       GROUP BY SR.DEPTNAME, GWAY.ITEM_ID                                     ");
        sql.append("       ) YITEMS                                                               ");
        sql.append("     ON YITEMS.DEPTNAME = STA.DEP_ID                                    ");
        sql.append("     AND YITEMS.SALARY_PERIOD        = STA.FP_DATE                            ");
        sql.append("     LEFT JOIN                                                                ");
        sql.append("       (SELECT SAI.SALARY_ITEMS_ID,                                           ");
        sql.append("         MIN(SAGIN.COMPANY_ID) COMPANY_ID,                                    ");
        sql.append("         SAGIN.DEP_ID,                                                        ");
        sql.append("         SUM(NVL(SAI.REPORT_AMOUNT,0)) REPORT_AMOUNT,                         ");
        sql.append("         SAGIN.FP_DATE,                                                       ");
        sql.append("         SAGIN.FPQX                                                           ");
        sql.append("       FROM XC_SALARY_AGAIN SAGIN                                             ");
        sql.append("       LEFT JOIN XC_SALARY_AGAIN_ITEMS SAI                                    ");
        sql.append("       ON SAGIN.ID           = SAI.SALARY_AGAIN_ID                            ");
        sql.append("       AND SAI.IS_DELETE     = 0                                              ");
        sql.append("       WHERE SAGIN.IS_DELETE = 0                                              ");
        sql.append("       AND SAGIN.FPQX        = "+fpqx+"                                       ");
        sql.append("       AND SAGIN.FP_DATE IN ('"+join+"')                                      ");
        sql.append("       AND SAGIN.COMPANY_ID = '"+companyId+"'                                 ");
        sql.append("       GROUP BY SAGIN.DEP_ID, SAGIN.FP_DATE, SAGIN.FPQX, SAI.SALARY_ITEMS_ID  ");
        sql.append("       ) SAGIN                                                                ");
        sql.append("     ON SAGIN.DEP_ID = STA.DEP_ID                                             ");
        sql.append("     AND STA.FP_DATE = SAGIN.FP_DATE                                          ");
        sql.append("     AND YITEMS.ITEM_ID = SAGIN.SALARY_ITEMS_ID                               ");
        sql.append("     LEFT JOIN XC_SALARY_AMOUNT_ITEM SAMI                              ");
        sql.append("     ON SAMI.TOTAL_AMOUNT_ID = STA.ID                                             ");
        sql.append("     AND SAMI.DEP_ID = STA.DEP_ID                                             ");
        sql.append("     AND SAMI.IS_DELETE = 0                                             ");
        sql.append("     INNER JOIN QX_USER_DATAUTH UD                                        ");// 数据权限
        sql.append("     ON UD.BRANCH_DEP=QD.ID                                        ");
        sql.append("     AND UD.FLG='1'                                        ");
        sql.append("     AND UD.USERID='"+LingUtil.userinfo().getId()+"'                          ");
        sql.append("     WHERE STA.FP_DATE IN ('"+join+"')                                        ");
        sql.append("     AND STA.COMPANY_ID = '"+companyId+"'                                        ");
        sql.append("     AND STA.FPQX = "+fpqx+"                                                  ");
        sql.append("     AND STA.IS_DELETE = 0                                                    ");
        sql.append(sqlText);
        sql.append("     GROUP BY STA.ID                                                          ");
        sql.append("     ORDER BY MIN(QD.DESCRIPTION) ASC                                        ");

        // 二次分配
        StringBuilder secondSql = new StringBuilder();
        secondSql.append("   SELECT                            ");
        secondSql.append("   ASS.IBF_ID, MIN(ASS.IBF_NAME) IBF_NAME    ");
        secondSql.append("   FROM XC_SALARY_TOTAL_AMOUNT STA           ");
        secondSql.append("   LEFT JOIN BRANCH                          ");
        secondSql.append("   ON STA.COMPANY_ID = BRANCH.ID             ");
        secondSql.append("   LEFT JOIN QX_DEPARTMENT QD                ");
        secondSql.append("   ON STA.DEP_ID = QD.ID                     ");
        secondSql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA        ");
        secondSql.append("   ON STA.FP_DATE = SA.SALARY_PERIOD         ");
        secondSql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF     ");
        secondSql.append("   ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID   ");
        secondSql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS ");
        secondSql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID   ");
        secondSql.append("   AND ASS.IS_DELETE = 0                     ");
        secondSql.append("   LEFT JOIN XC_SALARY_RECORD SR   ");
        secondSql.append("   ON ASTAFF.STAFF_ID = SR.STAFF_ID          ");
        secondSql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW   ");
        secondSql.append("   ON SA.SALARY_WAGE_ID = SGW.ID          ");
        secondSql.append("   AND SA.SALARY_GROUP_ID = SGW.SALARY_GROUP_ID          ");
        secondSql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                          ");
        secondSql.append("   ON SGW.ID = GWAY.WAGE_ID                                       ");
        secondSql.append("   AND GWAY.IS_DELETE = 0                                         ");
        secondSql.append("   AND ASS.IBF_ID = GWAY.ITEM_ID                                  ");
        secondSql.append("   WHERE STA.FP_DATE IN ('"+join+"')          ");
        secondSql.append(secondSqlText);
        secondSql.append("   AND GWAY.IS_ER = 1                       ");
        secondSql.append("   AND GWAY.ZLJS = 1                        ");
        secondSql.append("   AND STA.COMPANY_ID = '"+companyId+"'     ");
        secondSql.append("   GROUP BY ASS.IBF_ID                      ");
        secondSql.append("   ORDER BY MIN(ASS.SX) ASC                 ");

        SQLQuery summaryQuery = getSession().createSQLQuery(secondSql.toString());
        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> secondList = summaryQuery.list();

        // 除了1以外的其他实际分配
        StringBuilder otherSecondSql = new StringBuilder();
        otherSecondSql.append("   SELECT                            ");
        otherSecondSql.append("   ASS.IBF_ID, MIN(ASS.IBF_NAME) IBF_NAME    ");
        otherSecondSql.append("   FROM XC_SALARY_TOTAL_AMOUNT STA           ");
        otherSecondSql.append("   LEFT JOIN BRANCH                          ");
        otherSecondSql.append("   ON STA.COMPANY_ID = BRANCH.ID             ");
        otherSecondSql.append("   LEFT JOIN QX_DEPARTMENT QD                ");
        otherSecondSql.append("   ON STA.DEP_ID = QD.ID                     ");
        otherSecondSql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA        ");
        otherSecondSql.append("   ON STA.FP_DATE = SA.SALARY_PERIOD         ");
        otherSecondSql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF     ");
        otherSecondSql.append("   ON SA.ID = ASTAFF.SALARY_ASSIGNATION_ID   ");
        otherSecondSql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS ");
        otherSecondSql.append("   ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID   ");
        otherSecondSql.append("   AND ASS.IS_DELETE = 0                     ");
        otherSecondSql.append("   LEFT JOIN XC_SALARY_RECORD SR   ");
        otherSecondSql.append("   ON ASTAFF.STAFF_ID = SR.STAFF_ID          ");
        otherSecondSql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE SGW   ");
        otherSecondSql.append("   ON SA.SALARY_WAGE_ID = SGW.ID          ");
        otherSecondSql.append("   AND SA.SALARY_GROUP_ID = SGW.SALARY_GROUP_ID          ");
        otherSecondSql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAY                          ");
        otherSecondSql.append("   ON SGW.ID = GWAY.WAGE_ID                                       ");
        otherSecondSql.append("   AND GWAY.IS_DELETE = 0                                         ");
        otherSecondSql.append("   AND ASS.IBF_ID = GWAY.ITEM_ID                                  ");
        otherSecondSql.append("   WHERE STA.FP_DATE IN ('"+join+"')          ");
        otherSecondSql.append(secondSqlText);
        otherSecondSql.append("   AND GWAY.IS_ER = 1                       ");
        otherSecondSql.append("   AND GWAY.ZLJS != 1                      ");
        otherSecondSql.append("   AND STA.COMPANY_ID = '"+companyId+"'     ");
        otherSecondSql.append("   GROUP BY ASS.IBF_ID                      ");
        otherSecondSql.append("   ORDER BY MIN(ASS.SX) ASC                 ");
        SQLQuery summaryQuery2 = getSession().createSQLQuery(otherSecondSql.toString());
        summaryQuery2.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> otherSecondList = summaryQuery2.list();

//        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("companyId", obj[1]);
            map.put("company", obj[2]);
            map.put("deptId", obj[3]);
            map.put("UID", obj[3]);
            map.put("deptName", obj[4]);
            map.put("peopleCount", obj[5]);
            map.put("lastMonthAmount", obj[6]);
            map.put("byjxldkh", obj[7]);
            map.put("specialReward", obj[8]);
            map.put("otherReward", obj[9]);
            map.put("rewardTotal", obj[10]);
            map.put("bykfpzl", obj[11]);
            map.put("curMonthAmount", obj[12]);
            map.put("note", obj[13]);
            map.put("itemId", obj[14]);
            map.put("staticValue", obj[15]);
            map.put("actualItemid", obj[16]);
            map.put("needAmount", obj[17]);
            map.put("otherItemid", obj[18]);
            map.put("otherNeedAmount", obj[19]);
            map.put("otherId", obj[20]);
            map.put("ParentTaskUID", obj[21]);
            map.put("otherReward2", obj[22]);
            map.put("otherReward3", obj[23]);
            dataList.add(map);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", dataList.size());
        map.put("secondAmount", secondList);
        map.put("otherSecondAmount", otherSecondList);

        return map;
    }

}
