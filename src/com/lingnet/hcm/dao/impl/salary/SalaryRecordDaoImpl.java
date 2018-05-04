package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryRecordDao;
import com.lingnet.hcm.entity.salary.Rate;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.service.salary.RateService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;

/**
 * 工资档案
 * @ClassName: SalaryRecordDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
@Repository("salaryRecordDao")
public class SalaryRecordDaoImpl extends BaseDaoImplInit<SalaryRecord, String> implements SalaryRecordDao {

    @Resource(name="rateService")
    private RateService rateService;

    @Override
    public Map<String, Object> getStaffSalaryListData(String id, String deptId, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                                                                                                        ");
        sql.append("  JBI.ID, MIN(JBI.JOB_NUMBER) jobNumber, MIN(JBI.NAME) name, MIN(NVL(B2.FZX,BRANCH.FZX)) fzx, MIN(JBI.SEX) sex, MIN(JBI.AGE) age, MIN(JBI.PHONE) phone,");
        sql.append("  MIN(NVL(QD2.NAME,QD.NAME)) DEPTNAME,MIN(NVL(SR.POST,JBI.POST_ID)) postId,MIN(NVL(BZGW.POSITION_NAME,JBI.POST)) post,WM_CONCAT(SG.NAME) SALARYGROUP,         ");
        sql.append("  MIN(NVL(SR.DEPTNAME,JBI.DEPART_ID)) dept_id, MIN(NVL(SR.ID, -1)) recordId, MIN(SR.SALARY_POST_NAME) xcgw, MIN(SR.FPQX) FPQX, "
                + "MIN(JBI.DEPART_NAME) rdeptName, MIN(JBI.JOB_NUMBER_JT) jobNumberOrg           ");
        sql.append("  FROM                                                                                  ");
        sql.append("    ( SELECT                                                                                                                   ");
        sql.append("        JBI.ID,JBI.DEPART_ID,JBI.FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JBI.POST,JBI.POST_ID,JBI.PHONE,"
                + "JBI.AGE,JBI.SEX,JBI.NAME,JBI.DEPART_NAME, JBI.JOB_NUMBER_JT  ");
        sql.append("        FROM JC_BASIC_INFORMATION JBI                                                                                          ");
        sql.append("        UNION ALL                                                                                                              ");
        sql.append("        SELECT                                                                                                                 ");
        sql.append("        JBI.ID,JPJ.DEP_ID DEPART_ID,JPJ.FIRM_ID FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JPJ.SPE_POST POST,                        ");
        sql.append("        JPJ.STANDARD_POST_ID POST_ID,JBI.PHONE,JBI.AGE,JBI.SEX,JBI.NAME,JBI.DEPART_NAME, JBI.JOB_NUMBER_JT                     ");
        sql.append("        FROM JC_BASIC_INFORMATION JBI                                                                                          ");
        sql.append("        LEFT JOIN JC_PT_JOB JPJ                                                                                                ");
        sql.append("        ON JBI.ID = JPJ.PERSON_ID                                                                                              ");
        sql.append("        WHERE JPJ.IS_HOST_POST = '0'                                                                                           ");
        sql.append("        AND JPJ.IS_DELETE = 0                                                                                                  ");
        sql.append("        AND JPJ.STATE = 1                                                                                                      ");
        sql.append("      ) JBI                                                                                                                    ");// 兼职
        sql.append("  LEFT JOIN XC_SALARY_RECORD SR                   ");
        sql.append("  ON SR.STAFF_ID = JBI.ID                 ");
        sql.append("  AND SR.DEPTNAME = NVL(SR.DEPTNAME, JBI.DEPART_ID)                 ");// TODO
        sql.append("  LEFT JOIN XC_SALARY_PERSONAL SP                                                                                    ");
        sql.append("  ON SP.SALARY_RECORD_ID = SR.STAFF_ID                                                                              ");
        sql.append("  AND SR.ID = SP.RECORD_MAIN_ID                                                                              ");
        sql.append("  AND SP.IS_DELETE = 0                                                                                ");
        sql.append("  AND CASE WHEN SP.IS_OFF = 1                                                                                ");
        sql.append("            THEN CASE WHEN TO_CHAR(SP.OFF_DATE, 'yyyy-MM-dd') <= TO_CHAR(SYSDATE, 'yyyy-MM-dd') THEN 1       ");
        sql.append("                ELSE 0 END                                                                                ");
        sql.append("     ELSE 0 END = 0                                                                                ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                                                                             ");
        sql.append("  ON QD.ID = JBI.DEPART_ID                                                                               ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD2                                                                            ");
        sql.append("  ON QD2.ID = SR.DEPTNAME                                                                                ");
        sql.append("  LEFT JOIN BRANCH                                                                                       ");
        sql.append("  ON JBI.FILM_ID = BRANCH.ID                                                                             ");
        sql.append("  LEFT JOIN BRANCH b2                                                                                    ");
        sql.append("  ON SR.FILM_NAME = b2.ID                                                                                ");
        sql.append("  LEFT JOIN XC_SALARY_GROUP SG                                           ");
        sql.append("  ON SP.SALARY_GROUP_ID = SG.ID                                           ");
        sql.append("  LEFT JOIN POST_POSITION BZGW                                           ");
        sql.append("  ON SR.POST = BZGW.ID                                           ");
        sql.append("  LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                           ");
        sql.append("  ON SR.SALARY_POST = SDD.ID                                           ");
        sql.append("   WHERE JBI.IS_DELETE = 0                                       ");
        if (!StringUtils.isBlank(id)) {
            sql.append("  AND (JBI.ID = '"+id+"' OR SR.STAFF_ID = '"+id+"') ");
        }

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                                        ");
            }
            // 姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                        ");
            }
            // 部门
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                String[] deptArray = mapData.get("depId").split(",");
                sql.append("  AND JBI.DEPART_ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 存在薪资组
            if (!StringUtils.isBlank(mapData.get("group"))) {
                // 存在
                if (mapData.get("group").equals("1")) {
                    sql.append("  AND NVL2(SP.SALARY_GROUP_ID, 1, 0) = 1");
                } else {// 不存在
                    sql.append("  AND NVL2(SP.SALARY_GROUP_ID, 1, 0) = 0");
                }
            }
            // 薪资组
            if (!StringUtils.isBlank(mapData.get("salaryGroup"))) {
                String[] deptArray = mapData.get("salaryGroup").split(",");
                sql.append("  AND SP.SALARY_GROUP_ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 薪酬岗位
            if (!StringUtils.isBlank(mapData.get("xcgw"))) {
                String[] deptArray = mapData.get("xcgw").split(",");
                sql.append("  AND SR.SALARY_POST IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 分配权限
            if (!StringUtils.isBlank(mapData.get("fpqx"))) {
                sql.append("  AND SR.FPQX = "+mapData.get("fpqx")+" ");
            }
        }
        sql.append("   GROUP BY JBI.ID                                                          ");
        sql.append("   ORDER BY MIN(SDD.ZWBM) ASC                                        ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("companyId", obj[3]);
            map.put("detpName", obj[7]);
            map.put("sex", obj[4]);
            map.put("age", obj[5]);
            map.put("phone", obj[6]);
            map.put("post", obj[9]);
            map.put("salaryGroup", obj[10]);
            map.put("deptId", obj[11]);
            map.put("recordId", obj[12]);
            map.put("xcgw", obj[13]);
            map.put("fpqx", obj[14]);
            map.put("rDeptName", obj[15]);
            map.put("jobNumberOrg", obj[16]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getStaffSalaryData(String id, String depId, String recordId) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                                                                                                        ");
        sql.append("  JBI.ID, JBI.JOB_NUMBER, JBI.NAME, JBI.SEX,JBI.AGE,JBI.PHONE, JBI.ID_TYPE,JBI.ID_NUMBER, JBI.EMP_TYPE, SB.INSOL_DATE, JBI.COUNTRYSID_DATE,          ");
        sql.append("  JBI.SET_WORK_DATE, JBI.IN_COMPANY_TIME, JBI.IN_COMPANY_TIME2, RCM.CONTR_START, JBI.LABOR_COMPANY, JBI.IN_PORT_TIME,JBI.IN_PORT_TIME2, SR.KJGL, (TO_CHAR(SB.OUTSOL_DATE, 'yyyy')-TO_CHAR(SB.INSOL_DATE, 'yyyy') + 1) SOLDIER_AGE, ");
        sql.append("  NVL(SR.FILM_NAME, JBI.FILM_ID) FILM_ID, NVL(SR.DEPTNAME, QD2.ID) DEPT_ID, ");
        sql.append("  NVL(b2.FZX, BRANCH.FZX) FILM_NAME, NVL(QD3.NAME, QD2.NAME) DEPTNAME, NVL(SR.CLASS_NO, CC.CLASS_NO) CLASS_NO,          ");
        sql.append("  NVL(SR.KQDEPT, QD.ID) KQID, NVL(QD1.NAME, QD.NAME) KQDEPTS, NVL(SR.POST, JBI.POST_ID) POST, NVL(PP.POSITION_NAME, JBI.POST) POSTNAME, NVL(SR.SPECIFIC_POST, JBI.SPECIFIC_POST_ID) SPECIFIC_POST, ");
        sql.append("  NVL(JTGW.POSITION_NAME, JBI.SPECIFIC_POST) SPECIFIC_POST_NAME, SR.SALARY_POST, SR.SALARY_POST_NAME, ");
        sql.append("  NVL(SR.IS_JZ, NVL2(JPJ.IS_HOST_POST, DECODE(JPJ.IS_HOST_POST, 0, 1, 1, 0), 0)) sfjz, SR.IS_BZGS, NVL(SR.IS_SPECIAL_HOUR, JBI.IS_SPECIAL_HOUR) IS_SPECIAL_HOUR, NVL(SR.CLASS_GROUP, JBI.CLASS_GROUP) CLASS_GROUP,          ");
        sql.append("  SR.FPQX, SR.ZFXS, SR.GZXS, SR.SPECIAL_MARK, SR.GD_DATE          ");
        sql.append("  FROM                                                                                  ");
        sql.append("        ( SELECT                                                                                                                          ");
        sql.append("            JBI.ID,JBI.DEPART_ID,JBI.FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JBI.POST,JBI.POST_ID,JBI.PHONE,JBI.AGE,JBI.SEX,JBI.NAME,        ");
        sql.append("            JBI.CHECK_UNIT_ID,JBI.CLASS_NO,JBI.CLASS_GROUP,JBI.IS_SPECIAL_HOUR,JBI.SPECIFIC_POST_ID,JBI.SPECIFIC_POST,JBI.IN_PORT_TIME2,  ");
        sql.append("            JBI.IN_PORT_TIME,JBI.LABOR_COMPANY,JBI.IN_COMPANY_TIME2,JBI.IN_COMPANY_TIME,JBI.SET_WORK_DATE,JBI.COUNTRYSID_DATE,            ");
        sql.append("            JBI.EMP_TYPE,JBI.ID_NUMBER,JBI.ID_TYPE                                                                                        ");
        sql.append("            FROM JC_BASIC_INFORMATION JBI                                                                                                 ");
        sql.append("            UNION ALL                                                                                                                     ");
        sql.append("            SELECT                                                                                                                        ");
        sql.append("            JBI.ID,JPJ.DEP_ID DEPART_ID,JPJ.FIRM_ID FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JPJ.SPE_POST POST,                               ");
        sql.append("            JPJ.STANDARD_POST_ID POST_ID,JBI.PHONE,JBI.AGE,JBI.SEX,JBI.NAME,JBI.CHECK_UNIT_ID,JBI.CLASS_NO,                               ");
        sql.append("            JBI.CLASS_GROUP,JBI.IS_SPECIAL_HOUR,JPJ.SPE_POST_ID SPECIFIC_POST_ID,JPJ.SPE_POST SPECIFIC_POST,JBI.IN_PORT_TIME2,            ");
        sql.append("            JBI.IN_PORT_TIME,JBI.LABOR_COMPANY,JBI.IN_COMPANY_TIME2,JBI.IN_COMPANY_TIME,JBI.SET_WORK_DATE,JBI.COUNTRYSID_DATE,            ");
        sql.append("            JBI.EMP_TYPE,JBI.ID_NUMBER,JBI.ID_TYPE                                                                                        ");
        sql.append("            FROM JC_BASIC_INFORMATION JBI                                                                                                 ");
        sql.append("            LEFT JOIN JC_PT_JOB JPJ                                                                                                       ");
        sql.append("            ON JBI.ID = JPJ.PERSON_ID                                                                                                     ");
        sql.append("            WHERE JPJ.IS_HOST_POST = '0'                                                                                                  ");
        sql.append("            AND JPJ.IS_DELETE = 0                                                                                                         ");
        sql.append("            AND JPJ.STATE = 1                                                                                                             ");
        sql.append("          ) JBI                                                                                                                           ");
        sql.append("  LEFT JOIN XC_SALARY_RECORD SR                                                                                 ");
        sql.append("    ON JBI.ID = SR.STAFF_ID                                                                                       ");
//        sql.append("    AND JBI.DEPART_ID = SR.DEPTNAME                                                                                  ");// TODO
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                                                                                    ");
        sql.append("    ON QD.ID = JBI.CHECK_UNIT_ID                                                                                  ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD1                                                                                    ");
        sql.append("    ON QD1.ID = SR.KQDEPT                                                                                  ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD2                                                                                    ");
        sql.append("    ON QD2.ID = JBI.DEPART_ID                                                                                  ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD3                                                                                    ");
        sql.append("    ON QD3.ID = SR.DEPTNAME                                                                                  ");
        sql.append("  LEFT JOIN BRANCH                                                                                    ");
        sql.append("    ON JBI.FILM_ID = BRANCH.ID                                                                                  ");
        sql.append("  LEFT JOIN BRANCH b2                                                                                    ");
        sql.append("    ON SR.FILM_NAME = b2.ID                                                                                  ");
        sql.append("  LEFT JOIN CK_CLASS CC                                                                                    ");
        sql.append("    ON JBI.CLASS_NO = CC.CLASS_NO                                                                                  ");
        sql.append("  LEFT JOIN JC_PT_JOB JPJ                                                                                    ");
        sql.append("    ON JBI.JOB_NUMBER = JPJ.PERSON_ID                                                                                  ");
        sql.append("  LEFT JOIN DEPT_POSITION JTGW                                                                                    ");
        sql.append("    ON JTGW.ID = SR.SPECIFIC_POST                                                                                  ");
        sql.append("  LEFT JOIN POST_POSITION PP                                                                                    ");
        sql.append("    ON PP.ID = JTGW.POSITION_ID                                                                                  ");
        sql.append("  LEFT JOIN RS_CONTRACT_MANAGER RCM                                                                                    ");
        sql.append("    ON JBI.JOB_NUMBER = RCM.USERID                                                                                  ");
        sql.append("  LEFT JOIN JC_SOLDIER_BACK SB                                                                                    ");// 复转军人
        sql.append("    ON JBI.JOB_NUMBER = SB.JOB_NUMBER                                                                              ");
        sql.append("  WHERE JBI.IS_DELETE = 0                                       ");
        sql.append("    AND JBI.ID = '"+id+"' ");
        if ("-1".equals(recordId)) {
            sql.append("    AND JBI.DEPART_ID = '"+depId+"' ");
        } else {
            sql.append("    AND SR.ID = '"+recordId+"' ");
        }

        SQLQuery query = getSession().createSQLQuery(sql.toString());
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.list();
        Map<String, Object> map = new HashMap<String, Object>();
        if (list.size() > 0) {
            map = list.get(0);
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> getSalaryStandardItems(String id, int type, int isDisplay, String recordId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT MIN(SV.STAFF_ID) ID,                          ");
        sql.append("     MIN(SI.NAME) SALARYNAME,                           ");
        sql.append("     MIN(NVL(SV.STATIC_VALUE, 0)) SV,                   ");
        sql.append("     MIN(SV.ID) SVID,                                   ");
        sql.append("     MIN(SI.NUMBER_ACCURACY) XS,                        ");
        sql.append("     MIN(SP.SALARY_GROUP_ID) GROUPID,                   ");
        sql.append("     SV.SALARY_ITEMS_ID ITEMID,                         ");
        sql.append("     MIN(SG.NAME) GROUPNAME                             ");
        sql.append("   FROM XC_SALARY_PERSONAL SP                           ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP_WAGE WAGE                  ");
        sql.append("   ON SP.SALARY_GROUP_ID = WAGE.SALARY_GROUP_ID         ");
        sql.append("   AND WAGE.IS_DELETE = 0                               ");
        sql.append("   LEFT JOIN XC_GROUP_WAGE_AND_TYPE WAT                 ");
        sql.append("   ON WAGE.ID = WAT.WAGE_ID                             ");
        sql.append("   AND WAT.IS_DELETE = 0                                ");
        sql.append("   LEFT JOIN XC_SALARY_VALUE SV                         ");
        sql.append("   ON SP.SALARY_GROUP_ID = SV.SALARY_GROUP_ID           ");
        sql.append("   AND SV.RECORD_MAIN_ID  = SP.RECORD_MAIN_ID           ");
        sql.append("   AND WAT.ITEM_ID = SV.SALARY_ITEMS_ID                 ");
        sql.append("   AND SV.IS_DELETE = 0                                 ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP SG                         ");
        sql.append("   ON SG.ID = SP.SALARY_GROUP_ID                        ");
        sql.append("   LEFT JOIN XC_SALARY_ITEMS SI                         ");
        sql.append("   ON SV.SALARY_ITEMS_ID = SI.ID                        ");
        sql.append("   WHERE SP.IS_DELETE = 0                               ");
        sql.append("   AND SV.STAFF_ID = '"+id+"'                           ");
        sql.append("   AND SV.RECORD_MAIN_ID = '"+recordId+"'               ");

        switch (type) {
        case 1:// 固定薪资项目
            sql.append("   AND SI.FIXED = 1                                           ");
            break;
        case 2:// 异动项目
            sql.append("   AND SI.YD = 1                                           ");
            break;
        case 3:// 标准类项目
            sql.append("   AND SI.BZL = 1                                           ");
            break;
        case 4:// 年金类项目
            sql.append("   AND SI.NJL = 1                                           ");
            break;
        case 5:// 统筹类项目
            sql.append("   AND SI.TCL = 1                                           ");
            break;

        default:
            break;
        }

        // 是否显示标准项目
        if (isDisplay == 1) {
            sql.append("   AND SI.IS_DISPLAY = 1                                           ");
        }
        sql.append("   GROUP BY SP.SALARY_GROUP_ID, SV.SALARY_ITEMS_ID            ");
        sql.append("   ORDER BY MIN(SI.SX) ASC, SV.SALARY_ITEMS_ID ASC      ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("salaryName", obj[1]);
            map.put("staticValue", obj[2].toString().trim());
            map.put("staticId", obj[3]);
            map.put("xs", obj[4]);
            map.put("groupId", obj[5]);
            map.put("itemId", obj[6]);
            map.put("slaryGeoupName", obj[7]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public Map<String, Object> getHistorySalaryRecord(String id) {
        Map<String, Object> m = new HashMap<String, Object>();
        return m;
    }

    @Override
    public Map<String, Object> getPracticeEmployeeListData(String depId, String searchData, Pager pager) {
        pager = findPagerBySql(pager, getPracticeEmployeeListDataSql(searchData));
        List<?> list= pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("companyId", obj[3]);// 现公司
            map.put("detpName", obj[7]);// 现部门
            map.put("sex", obj[4]);
            map.put("age", obj[5]);
            map.put("phone", obj[6]);
            map.put("post", obj[9]);// 现岗位
            map.put("jobLevel", obj[10]);
            map.put("beginTime", obj[11]);
            Rate rate = rateService.get(Rate.class, Restrictions.eq("id", "2"));
            Double v = 0d;
            if (rate != null && obj[12] != null) {
                if (Integer.valueOf(obj[12].toString()) == 1) {
                    v = rate.getNativeMoney();
                } else if (Integer.valueOf(obj[12].toString()) == 2) {
                    v = rate.getForeignMoney();
                }
            }
            map.put("qzd", v);
            map.put("jsType", obj[12]);
            map.put("startDate", obj[13]);
            map.put("effectDate", obj[14]);
            map.put("changeType", obj[15]);
            map.put("empChangeId", obj[16]);
            map.put("beginDate", obj[17]);
            map.put("originalCompany", obj[18]);// 原公司
            map.put("originalDept", obj[19]);// 原部门
            map.put("originalJtgw", obj[20]);// 原具体岗位
            map.put("salaryName", obj[21]);// 现薪酬岗位
            map.put("nowBzgw", obj[22]);// 现标准岗位
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public String getPracticeEmployeeListDataSql(String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                                                                    ");
        sql.append("  MIN(JBI.ID) ID, MIN(JBI.JOB_NUMBER) JOB_NUMBER, MIN(JBI.NAME) NAME, MIN(NVL(B.FZX, BRANCH.FZX)) FZX, MIN(JBI.SEX) SEX, MIN(JBI.AGE) AGE, MIN(JBI.PHONE) PHONE,");
        sql.append("  MIN(NVL(QD1.NAME, QD.NAME)) DEPTNAME,MIN(JBI.POST_ID) POST_ID,MIN(NVL(JTGW.POSITION_NAME, JBI.POST)) POST,MIN(JBI.JOB_LEVEL) JOB_LEVEL,MIN(JBI.BEGIN_TIME) BEGIN_TIME,          ");
        sql.append("  MIN(SR.JS_TYPE) JS_TYPE,MIN(SR.START_DATE) kaishi,MIN(SR.EFFECT_DATE) EFFECT_DATE, MIN(JEC.CHANGE_TYPE) CHANGE_TYPE, MIN(JEC.ID) JCID,  ");
        sql.append("  MIN(JEC.BEGIN_DATE) BEGIN_DATE,MIN(JEC.FILM) FILM, MIN(JEC.DEP) DEP,MIN(JEC.SP_POST) SP_POST, MIN(SDD.NAME) SALARY_NAME,MIN(NVL(BZGW.POSITION_NAME,JBI.POST)) bzgw   ");
        sql.append("  FROM                                               ");

        sql.append("    ( SELECT                                                                                                                   ");
        sql.append("        JBI.ID,JBI.DEPART_ID,JBI.FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JBI.POST,JBI.POST_ID,JBI.PHONE,JBI.AGE,JBI.SEX,JBI.NAME, ");
        sql.append("        JBI.JOB_LEVEL,JBI.BEGIN_TIME  ");
        sql.append("        FROM JC_BASIC_INFORMATION JBI                                                                                          ");
        sql.append("        UNION ALL                                                                                                              ");
        sql.append("        SELECT                                                                                                                 ");
        sql.append("        JBI.ID,JPJ.DEP_ID DEPART_ID,JPJ.FIRM_ID FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JPJ.SPE_POST POST,                        ");
        sql.append("        JPJ.STANDARD_POST_ID POST_ID,JBI.PHONE,JBI.AGE,JBI.SEX,JBI.NAME,                                                       ");
        sql.append("        JBI.JOB_LEVEL,JBI.BEGIN_TIME                                                       ");
        sql.append("        FROM JC_BASIC_INFORMATION JBI                                                                                          ");
        sql.append("        LEFT JOIN JC_PT_JOB JPJ                                                                                                ");
        sql.append("        ON JBI.ID = JPJ.PERSON_ID                                                                                              ");
        sql.append("        WHERE JPJ.IS_HOST_POST = '0'                                                                                           ");
        sql.append("        AND JPJ.IS_DELETE = 0                                                                                                  ");
        sql.append("        AND JPJ.STATE = 1                                                                                                      ");
        sql.append("      ) JBI                                                                                                                    ");// 兼职
        
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                                                 ");
        sql.append("  ON QD.ID = JBI.DEPART_ID                                                   ");
        sql.append("  LEFT JOIN BRANCH                                                           ");
        sql.append("  ON JBI.FILM_ID = BRANCH.ID                                                 ");
        sql.append("  LEFT JOIN XC_SALARY_RECORD SR                                               ");
        sql.append("  ON SR.STAFF_ID = JBI.ID                                                 ");
        sql.append("  LEFT JOIN BRANCH B                                                           ");
        sql.append("  ON SR.FILM_NAME = B.ID                                                 ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD1                                                 ");
        sql.append("  ON QD1.ID = SR.DEPTNAME                                                   ");
        sql.append("  LEFT JOIN DEPT_POSITION JTGW                                               ");
        sql.append("  ON JTGW.ID = SR.SPECIFIC_POST                                         ");
        sql.append("  LEFT JOIN POST_POSITION BZGW                                               ");
        sql.append("  ON BZGW.ID = SR.POST                                         ");
        sql.append("  LEFT JOIN JC_EMP_CHANGE JEC                                               ");
        sql.append("  ON JEC.JOB_NUMBER = JBI.JOB_NUMBER                                         ");
        sql.append("  LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                    ");
        sql.append("  ON SR.SALARY_POST = SDD.ID                                         ");
        sql.append("  WHERE JBI.IS_DELETE = 0                                       ");
//        sql.append("  AND NVL(JEC.IS_DEAL, 0) = 0                                         ");

        // 显示上一个月到现在异动的人员信息
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-01";
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String curDate = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
        sql.append("  AND JEC.CHANGEDATE >= TO_DATE('"+date+"', 'yyyy-MM-dd')                                         ");
        sql.append("  AND JEC.CHANGEDATE <= TO_DATE('"+curDate+"', 'yyyy-MM-dd')                                         ");

        Map<String, String> search = JsonUtil.parseProperties(searchData);
        if (search != null) {
            if (!StringUtils.isBlank(search.get("depId"))) {
                String[] deptArray = search.get("depId").split(",");
                sql.append("  AND JBI.DEPART_ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 工号
            if (!StringUtils.isBlank(search.get("jobNumber"))) {
                sql.append("  AND JBI.JOB_NUMBER LIKE '%"+search.get("jobNumber").trim()+"%'");
            }
            // 姓名
            if (!StringUtils.isBlank(search.get("name"))) {
                sql.append("  AND JBI.NAME LIKE '%"+search.get("name").trim()+"%'");
            }
        }
        sql.append("  GROUP BY JBI.ID                                                          ");
        sql.append("  ORDER BY MAX(JEC.CREATEDATE) DESC               ");

        return sql.toString();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map<String, Object> getEmployeeBaseInfo(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                                                                    ");
        sql.append("  JBI.ID, JBI.JOB_NUMBER, JBI.NAME, NVL(B2.FZX, BRANCH.FZX) FZX, JBI.SEX, JBI.AGE, JBI.PHONE,");
        sql.append("  NVL(QD1.NAME, QD.NAME) DEPTNAME,NVL(SR.SPECIFIC_POST, JBI.SPECIFIC_POST_ID) SPECIFIC_POST_ID,");
        sql.append("  NVL(JTGW.POSITION_NAME, JBI.SPECIFIC_POST) SPECIFIC_POST,JBI.JOB_LEVEL,NVL(B2.ID, BRANCH.ID) bId,          ");
        sql.append("  SR.SALARY_POST, SDD.NAME salaryName          ");
        sql.append("  FROM JC_BASIC_INFORMATION JBI                                              ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                                                  ");
        sql.append("  ON QD.ID = JBI.DEPART_ID                                                    ");
        sql.append("  LEFT JOIN BRANCH                                                           ");
        sql.append("  ON JBI.FILM_ID = BRANCH.ID                                                 ");
        sql.append("  LEFT JOIN XC_SALARY_RECORD SR                                               ");
        sql.append("  ON SR.STAFF_ID = JBI.ID                                                 ");
        sql.append("  LEFT JOIN BRANCH B2                                                           ");
        sql.append("  ON SR.FILM_NAME = B2.ID                                                 ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD1                                                 ");
        sql.append("  ON QD1.ID = SR.DEPTNAME                                                   ");
        sql.append("  LEFT JOIN DEPT_POSITION JTGW                                               ");
        sql.append("  ON JTGW.ID = SR.SPECIFIC_POST                                         ");
        sql.append("  LEFT JOIN XC_SALARY_DEPT_DICTIONARY SDD                                    ");
        sql.append("  ON SR.SALARY_POST = SDD.ID                                         ");
        sql.append("  WHERE JBI.IS_DELETE = 0                                       ");
        sql.append("    AND JBI.ID = '"+id+"'                                       ");

        List list = findBySql(sql.toString());
        List<Object[]> listObj = list;
        Map<String, Object> map = new HashMap<String, Object>();
        if (listObj.size() == 0) {
            return map;
        }
        Object[] obj = (Object[]) listObj.get(0);
        map.put("id", obj[0]);
        map.put("jobNumber", obj[1]);
        map.put("name", obj[2]);
        map.put("company", obj[3]);
        map.put("detpName", obj[7]);
        map.put("sex", obj[4]);
        map.put("age", obj[5]);
        map.put("phone", obj[6]);
        map.put("post", obj[9]);
        map.put("jobLevel", obj[10]);
        map.put("companyId", obj[11]);
        map.put("salaryPostId", obj[12]);
        map.put("salaryPostName", obj[13]);

        return map;
    }

    @Override
    public List<Map<String, Object>> getSalaryGroupValueListData(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                       ");
        sql.append("   SV.ID,SV.SALARY_GROUP_ID, SG.NAME GROUPNAME, SV.SALARY_ITEMS_ID,   ");
        sql.append("   ITEMS.NAME ITEMNAME,SV.STATIC_VALUE,ITEMS.NUMBER_ACCURACY   ");
        sql.append("   FROM XC_SALARY_VALUE SV                                      ");
        sql.append("   LEFT JOIN XC_SALARY_GROUP SG                                 ");
        sql.append("   ON SV.SALARY_GROUP_ID = SG.ID                                ");
        sql.append("   LEFT JOIN XC_SALARY_ITEMS ITEMS                              ");
        sql.append("   ON SV.SALARY_ITEMS_ID = ITEMS.ID                             ");
        sql.append("   WHERE SV.STAFF_ID='"+id+"' AND SV.IS_DELETE = 0                    ");
        sql.append("   AND ITEMS.YD = 1                    ");
        sql.append("   ORDER BY SV.CREATEDATE ASC                                 ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("salaryGroupId", obj[1]);
            map.put("groupName", obj[2]);
            map.put("salaryItemsId", obj[3]);
            map.put("itemName", obj[4]);
            map.put("staticValue", obj[5]);
            map.put("target", obj[6]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public Map<String, Object> getAllSalaryAdjustListData(String depId, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT JBI.ID,                                        ");
        sql.append("       MIN(JBI.JOB_NUMBER) JOBNUMBER,                      ");
        sql.append("       MIN(JBI.NAME) NAME,                                 ");
        sql.append("       MIN(BRANCH.FZX) FZX,                                ");
        sql.append("       MIN(QD.NAME) DEPTNAME,                              ");
        sql.append("       MIN(JBI.POST_ID) POSTID,                            ");
        sql.append("       MIN(JBI.POST) POST,                                 ");
        sql.append("       MIN(JBI.JOB_LEVEL) jobLevel,                        ");
        sql.append("       MIN(JBI.ON_JOB) onJob,                        ");
        sql.append("       WM_CONCAT(SV.SALARY_ITEMS_ID) WMID,                 ");
        sql.append("       WM_CONCAT(ITEMS.NAME) WMNAME,                        ");
        sql.append("       WM_CONCAT(SV.STATIC_VALUE) VALUE                        ");
        sql.append("     FROM JC_BASIC_INFORMATION JBI                         ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD                            ");
        sql.append("       ON QD.ID = JBI.DEPART_ID                              ");
        sql.append("     LEFT JOIN BRANCH                                      ");
        sql.append("       ON JBI.FILM_ID  = BRANCH.ID                           ");
        sql.append("     LEFT JOIN XC_SALARY_VALUE SV                          ");
        sql.append("       ON JBI.ID = SV.STAFF_ID                               ");
        sql.append("     AND SV.IS_DELETE=0                                    ");
        sql.append("     LEFT JOIN XC_SALARY_ITEMS ITEMS                       ");
        sql.append("       ON SV.SALARY_ITEMS_ID = ITEMS.ID                      ");
        sql.append("     WHERE JBI.IS_DELETE = 0                ");
        Map<String, String> search = JsonUtil.parseProperties(searchData);

        if (search != null) {
            // 部门
            if (!StringUtils.isBlank(search.get("depId"))) {
                String[] deptArray = search.get("depId").split(",");
                sql.append("  AND JBI.DEPART_ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 工号
            if (!StringUtils.isBlank(search.get("jobNumber"))) {
                sql.append("  AND JBI.JOB_NUMBER LIKE '%"+search.get("jobNumber").trim()+"%'");
            }
            // 姓名
            if (!StringUtils.isBlank(search.get("name"))) {
                sql.append("  AND JBI.NAME LIKE '%"+search.get("name").trim()+"%'");
            }
            // 薪资组
            if (!StringUtils.isBlank(search.get("salaryGroup"))) {
                sql.append("  AND SV.SALARY_GROUP_ID = '"+search.get("salaryGroup").trim()+"'");
            }
        }
        sql.append("     GROUP BY JBI.ID, JBI.CREATEDATE                       ");
        sql.append("     ORDER BY JBI.CREATEDATE DESC                          ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list= pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("company", obj[3]);
            map.put("detpName", obj[4]);
            map.put("post", obj[6]);
            map.put("jobLevel", obj[7]);
            map.put("onJob", obj[8]);
            map.put("wmName", obj[10]);
            map.put("staticValue", obj[11]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public List<Map<String, Object>> getStaffForAdjustListData(String ids) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT JBI.ID,                                        ");
        sql.append("       MIN(JBI.JOB_NUMBER) JOBNUMBER,                      ");
        sql.append("       MIN(JBI.NAME) NAME,                                 ");
        sql.append("       MIN(BRANCH.FZX) FZX,                                ");
        sql.append("       MIN(QD.NAME) DEPTNAME,                              ");
        sql.append("       MIN(JBI.POST_ID) POSTID,                            ");
        sql.append("       MIN(JBI.POST) POST,                                 ");
        sql.append("       MIN(JBI.JOB_LEVEL) jobLevel,                        ");
        sql.append("       MIN(JBI.ON_JOB) onJob,                        ");
        sql.append("       WM_CONCAT(SV.SALARY_ITEMS_ID) WMID,                 ");
        sql.append("       WM_CONCAT(ITEMS.NAME) WMNAME,                        ");
        sql.append("       WM_CONCAT(SV.STATIC_VALUE) VALUE,                    ");
        sql.append("       WM_CONCAT(ITEMS.NUMBER_ACCURACY) NUMBER_ACCURACY,    ");
        sql.append("       WM_CONCAT(SV.ID) svId                               ");
        sql.append("     FROM JC_BASIC_INFORMATION JBI                         ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD                            ");
        sql.append("       ON QD.ID = JBI.DEPART_ID                              ");
        sql.append("     LEFT JOIN BRANCH                                      ");
        sql.append("       ON JBI.FILM_ID  = BRANCH.ID                           ");
        sql.append("     LEFT JOIN XC_SALARY_VALUE SV                          ");
        sql.append("       ON JBI.ID = SV.STAFF_ID                               ");
        sql.append("     AND SV.IS_DELETE=0                                    ");
        sql.append("     LEFT JOIN XC_SALARY_ITEMS ITEMS                       ");
        sql.append("       ON SV.SALARY_ITEMS_ID = ITEMS.ID                      ");
        sql.append("     WHERE JBI.IS_DELETE = 0                ");

        if (!StringUtils.isBlank(ids)) {
            String[] deptArray = ids.split(",");
            sql.append("  AND JBI.ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
        }
        sql.append("     GROUP BY JBI.ID                       ");

        List<?> list= findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("company", obj[3]);
            map.put("detpName", obj[4]);
            map.put("post", obj[6]);
            map.put("jobLevel", obj[7]);
            map.put("onJob", obj[8]);
            map.put("wmId", obj[9]);
            map.put("wmName", obj[10]);
            map.put("staticValue", obj[11]);
            map.put("xs", obj[12]);
            map.put("svId", obj[13]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public List<Map<String, String>> getAllGroupFinancialList(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT *                                                     ");
        sql.append("   FROM                                                         ");
        sql.append("     (SELECT SF.ID, SF.CONTENT, SF.CNT_MATH, WAT.SX             ");
        sql.append("     FROM XC_SALARY_FORMULA SF                                  ");
        sql.append("     LEFT JOIN XC_GROUP_WAGE_AND_TYPE WAT                       ");
        sql.append("       ON WAT.ID         = SF.BIND_ID                             ");
        sql.append("     WHERE WAT.ITEM_ID = '"+id+"'     ");
        sql.append("       AND SF.IS_DELETE  = 0                                      ");
        sql.append("       AND WAT.IS_DELETE =0                                       ");
        sql.append("     UNION ALL                                                  ");
        sql.append("      SELECT SF.ID, SF.CONTENT, SF.CNT_MATH, WAT.SX                          ");
        sql.append("     FROM XC_SALARY_FORMULA SF                                  ");
        sql.append("     LEFT JOIN XC_SALARY_WAGE_AND_TYPE wat                      ");
        sql.append("       ON WAT.ID         = SF.BIND_ID                             ");
        sql.append("     WHERE WAT.ITEM_ID = '"+id+"'     ");
        sql.append("       AND SF.IS_DELETE  = 0                                      ");
        sql.append("       AND WAT.IS_DELETE =0                                       ");
        sql.append("     ) c                                                        ");
        sql.append("   ORDER BY c.SX ASC                                            ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", obj[0]+"");
            try {
                map.put("cntMath", LingUtil.ClobToString((java.sql.Clob)obj[2]));
                map.put("content", LingUtil.ClobToString((java.sql.Clob)obj[1]));
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
    public Map<String, Object> getAllRecordHistoryListData(String staffId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT SH.ID, SI.NAME,                 ");
        sql.append("      SH.ADJUST_FRONT,              ");
        sql.append("      SH.ADJUST_NEXT,               ");
        sql.append("      SH.ADJUST_DATE,               ");
        sql.append("      SH.NOTE                       ");
        sql.append("    FROM XC_SALARY_HISTORY SH       ");
        sql.append("    LEFT JOIN XC_SALARY_ITEMS SI    ");
        sql.append("      ON SH.ITEMS_ID = SI.ID          ");
        sql.append("    LEFT JOIN JC_BASIC_INFORMATION JBI    ");
        sql.append("      ON SH.STAFF_ID = JBI.ID          ");
        sql.append("    WHERE SH.STAFF_ID = '"+staffId+"'          ");
        Map<String, String> search = JsonUtil.parseProperties(pager.getSearchData());
        if (search != null) {
            // 工号
            if (!StringUtils.isBlank(search.get("jobNumber"))) {
                sql.append("  AND JBI.JOB_NUMBER LIKE '%"+search.get("jobNumber")+"%' ");
            }

            // 姓名
            if (!StringUtils.isBlank(search.get("name"))) {
                sql.append("  AND JBI.NAME LIKE '%"+search.get("name")+"%' ");
            }
        }

        sql.append("     ORDER BY SH.CREATEDATE DESC                          ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list= pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("adjustFront", obj[2]);
            map.put("adjustNext", obj[3]);
            map.put("adjustDate", obj[4]);
            map.put("note", obj[5]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getChangeHistoryForStaff(String staffId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT MIN(JEP.CHANGE_TYPE) CHANGE_TYPE,                                                      ");
        sql.append("     MIN(JEP.FILM) FILM, MIN(JEP.DEP) DEP,                                                       ");
        sql.append("     MIN(JEP.SP_POST) SP_POST, MIN(JEP.SALARY_POST_NAME) SALARY_POST_NAME,                       ");
        sql.append("     MIN(JEP.FILM_NAME2) FILM_NAME2, MIN(JEP.DEP_NAME2) DEP_NAME2,                               ");
        sql.append("     MIN(JEP.CHANGEDATE) CHANGEDATE, MIN(JEP.SPAN_POST_NAME2) SPAN_POST_NAME2,                   ");
        sql.append("     MIN(JEP.SP_POST_NAME2) SP_POST_NAME2, MIN(JEP.SALARY_POST_NAME2) SALARY_POST_NAME2,         ");
        sql.append("     LISTAGG(NVL(SSH.IBF_ID, ' '), ',') WITHIN GROUP (ORDER BY SSH.IBF_ID ASC) IBF_ID,               ");
        sql.append("     LISTAGG(NVL(SSH.ITEMS_NAME, ' '), ',') WITHIN GROUP (ORDER BY SSH.IBF_ID ASC) ITEMS_NAME,       ");
        sql.append("     LISTAGG(NVL(SSH.ADJUST_FRONT, ' '), ',') WITHIN GROUP (ORDER BY SSH.IBF_ID ASC) ADJUST_FRONT,   ");
        sql.append("     LISTAGG(NVL(SSH.ADJUST_NEXT, ' '), ',') WITHIN GROUP (ORDER BY SSH.IBF_ID ASC) ADJUST_NEXT      ");
        sql.append("   FROM XC_SALARY_RECORD SR                                                                      ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                                            ");
        sql.append("   ON SR.STAFF_ID = JBI.ID                                                                       ");
        sql.append("   INNER JOIN JC_EMP_CHANGE JEP                                                                  ");
        sql.append("   ON JEP.JOB_NUMBER = JBI.JOB_NUMBER                                                            ");
        sql.append("   LEFT JOIN XC_SALARY_STAND_HISTORY SSH                                                         ");
        sql.append("   ON SSH.STAFF_ID = SR.STAFF_ID                                                                 ");
        sql.append("   AND SSH.EMP_CHANGE_ID = JEP.ID                                                                ");
        sql.append("   WHERE SR.STAFF_ID = '"+staffId+"'                                                             ");
        sql.append("   GROUP BY JEP.ID                                                                               ");
        sql.append("   ORDER BY MIN(JEP.CREATEDATE) DESC                                                             ");

        // 查找异动的标准项目
        StringBuilder ydSql = new StringBuilder();
        ydSql.append("   SELECT SSH.IBF_ID,MIN(SSH.ITEMS_NAME) ITEMS_NAME      ");
        ydSql.append("   FROM XC_SALARY_STAND_HISTORY SSH                      ");
        ydSql.append("   WHERE SSH.STAFF_ID= '"+staffId+"' ");
        ydSql.append("   GROUP BY SSH.IBF_ID                                   ");
        ydSql.append("   ORDER BY SSH.IBF_ID ASC                               ");
        SQLQuery query = getSession().createSQLQuery(ydSql.toString());
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> allYdItems = query.list();

        pager = findPagerBySql(pager, sql.toString());
        List<?> list= pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("changeType", obj[0]);// 异动类型
            map.put("originalCompany", obj[1]);// 原公司
            map.put("originalDept", obj[2]);// 原部门
            map.put("originalJtgw", obj[3]);// 原具体岗位
            map.put("salaryPostName", obj[4]);// 原薪酬岗位
            map.put("nowCompany", obj[5]);// 现在公司
            map.put("nowDept", obj[6]);// 现在部门
            map.put("changeDate", obj[7]);// 异动时间
            map.put("nowJtgw", obj[9]);// 现在具体岗位
            map.put("nowBzgw", obj[8]);// 现在标准岗位
            map.put("salaryPostName2", obj[10]);// 现在薪酬岗位
            map.put("ibfId", obj[11]);// 薪资项目ID
            map.put("itemsName", obj[12]);// 薪资项目名称
            map.put("adjustFront", obj[13]);// 调整前值
            map.put("adjustNext", obj[14]);// 调整后值
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("ydItems", allYdItems);

        return map;
    }

}
