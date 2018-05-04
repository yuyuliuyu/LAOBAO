package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.lingnet.hcm.dao.salary.SalaryInsuranceDao;
import com.lingnet.hcm.dao.salary.SalaryRecordDao;
import com.lingnet.hcm.entity.salary.SalaryInsurance;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.service.salary.SalaryInsuranceService;
import com.lingnet.qxgl.dao.BranchDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;

/**
 * 缴费标准表
 * @ClassName: SalaryInsuranceDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年4月19日 下午4:26:24 
 *
 */
@Repository("salaryInsuranceDao")
public class SalaryInsuranceDaoImpl extends BaseDaoImplInit<SalaryInsurance, String> implements SalaryInsuranceDao {

    @Resource(name="salaryInsuranceService")
    private SalaryInsuranceService salaryInsuranceService;
    @Resource(name="salaryRecordDao")
    private SalaryRecordDao salaryRecordDao;
    @Resource(name="branchDao")
    private BranchDao branchDao;

    @Override
    public Map<String, Object> getNoInsuranceOfStaffListData(String depId, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT JBI.ID,                            ");
        sql.append("       MIN(JBI.JOB_NUMBER) JOBNUMBER,          ");
        sql.append("       MIN(JBI.NAME) NAME,                     ");
        sql.append("       MIN(BRANCH.FZX) FZX,                    ");
        sql.append("       MIN(JBI.SEX) SEX,                       ");
        sql.append("       MIN(JBI.AGE) AGE,                       ");
        sql.append("       MIN(JBI.PHONE) PHONE,                   ");
        sql.append("       MIN(QD.NAME) DEPTNAME,                  ");
        sql.append("       MIN(SR.POST) POSTID,                ");
        sql.append("       MIN(PP.POSITION_NAME) POST,                      ");
        sql.append("       MIN(JBI.ON_JOB) onJob, MIN(SR.ID) recordMainId        ");
        sql.append("     FROM XC_SALARY_RECORD SR             ");
        sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI             ");
        sql.append("     ON SR.STAFF_ID = JBI.ID                  ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD                ");
        sql.append("     ON QD.ID = SR.DEPTNAME                  ");
        sql.append("     LEFT JOIN BRANCH                          ");
        sql.append("     ON SR.FILM_NAME = BRANCH.ID                ");
        sql.append("     LEFT JOIN XC_INSURANCE_MIDDLE IM           ");
        sql.append("     ON IM.STAFF_ID = SR.STAFF_ID                   ");
        sql.append("     AND IM.IS_DELETE = 0               ");
        sql.append("     LEFT JOIN POST_POSITION PP           ");
        sql.append("     ON SR.POST = PP.ID                   ");
        sql.append("     WHERE IM.ID IS NULL                       ");
        sql.append("       AND JBI.IS_DELETE = 0               ");

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
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name")+"%'                                        ");
            }
        }
        sql.append("     GROUP BY JBI.ID            ");
        sql.append("     ORDER BY MIN(SR.CREATEDATE) DESC              ");

        pager  = findPagerBySql(pager, sql.toString());
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
            map.put("onJob", obj[10]);
            map.put("recordMainId", obj[11]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public List<Map<String, Object>> getNoChoseStaffListData(String ids, String recordMainId) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT JBI.ID,                            ");
        sql.append("       JBI.JOB_NUMBER JOBNUMBER,          ");
        sql.append("       JBI.NAME,                     ");
        sql.append("       BRANCH.FZX,                    ");
        sql.append("       JBI.SEX,                       ");
        sql.append("       JBI.AGE,                       ");
        sql.append("       JBI.PHONE,                   ");
        sql.append("       QD.NAME DEPTNAME,                  ");
        sql.append("       SR.POST POSTID,                ");
        sql.append("       PP.POSITION_NAME POST,                      ");
        sql.append("       JBI.ON_JOB onJob, SR.FILM_NAME                        ");
        sql.append("     FROM XC_SALARY_RECORD SR             ");
        sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI             ");
        sql.append("     ON SR.STAFF_ID = JBI.ID                  ");
        sql.append("     LEFT JOIN QX_DEPARTMENT QD                ");
        sql.append("     ON QD.ID = SR.DEPTNAME                  ");
        sql.append("     LEFT JOIN BRANCH                          ");
        sql.append("     ON SR.FILM_NAME = BRANCH.ID                ");
        sql.append("     LEFT JOIN XC_INSURANCE_MIDDLE IM           ");
        sql.append("     ON IM.STAFF_ID = SR.STAFF_ID                   ");
        sql.append("     AND IM.DEP_ID = SR.DEPTNAME                   ");
        sql.append("     AND IM.IS_DELETE = 0               ");
        sql.append("     LEFT JOIN POST_POSITION PP          ");
        sql.append("     ON SR.POST = PP.ID                   ");
        sql.append("     WHERE IM.ID IS NULL                       ");
        sql.append("       AND JBI.IS_DELETE = 0               ");

        String[] deptArray = ids.split(",");
        sql.append("  AND SR.STAFF_ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
        sql.append("  AND SR.ID IN ('"+StringUtils.join(recordMainId.split(","), "','")+"') ");
        sql.append("     ORDER BY SR.CREATEDATE ASC              ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
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
            map.put("onJob", obj[10]);
            map.put("isDaiJiao", 0);
            map.put("comId", obj[11]);
            dataList.add(map);
        }

        return dataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getInsuranceItemsListData(String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT MIN(SR.STAFF_ID) ID,                              ");
        sql.append("      MIN(JBI.JOB_NUMBER) JOBNUMBER,            ");
        sql.append("      MIN(JBI.NAME) NAME,                       ");
        sql.append("      MIN(BRANCH.FZX) FZX,                      ");
        sql.append("      MIN(JBI.SEX) SEX,                         ");
        sql.append("      MIN(JBI.AGE) AGE,                         ");
        sql.append("      MIN(JBI.PHONE) PHONE,                     ");
        sql.append("      MIN(QD.NAME) DEPTNAME,                    ");
        sql.append("      MIN(SR.POST) POSTID,                  ");
        sql.append("      MIN(BZGW.POSITION_NAME) POST,                       ");
        sql.append("      MIN(JBI.ON_JOB) ONJOB,                    ");
        sql.append("      MIN(IM.IS_DAIJIAO) ISDAIJIAO,             ");
        sql.append("      WM_CONCAT(JIB.ID) FULIID,                 ");
        sql.append("      WM_CONCAT(JIB.INSUR_NAME) FULINAME,        ");
        sql.append("      WM_CONCAT(JA.NAME) AREA,        ");
        sql.append("      WM_CONCAT(JF.FZX) JFCOMPANY,        ");
        sql.append("      WM_CONCAT(II.BASE_COMPANY) baseCompany,        ");
        sql.append("      WM_CONCAT(II.BASE_PERSONAL) basePersonal,      ");
        sql.append("      WM_CONCAT(II.PAYMENT_COMPANY) PAYMENT_COMPANY,           ");
        sql.append("      WM_CONCAT(II.PAYMENT_PERSONAL) PAYMENT_PERSONAL,         ");
        sql.append("      WM_CONCAT(II.ID) INSURANCE_ID           ");
        sql.append("    FROM XC_INSURANCE_MIDDLE IM                     ");
        sql.append("    LEFT JOIN XC_INSURANCE_ITEMS II                 ");
        sql.append("    ON IM.ID = II.INSURANCE_MIDDLE_ID               ");
        sql.append("    AND TO_Date(II.YEAR || '-' || II.MONTH , 'yyyy-MM') <= SYSDATE               ");
        sql.append("    AND II.IS_DELETE = 0                            ");
        sql.append("    LEFT JOIN JC_INSURANCE_BENEFITS JIB             ");
        sql.append("    ON II.IBF_ID = JIB.ID                           ");
        sql.append("    left join JC_PAY_AREAS JPA                      ");
        sql.append("    ON II.AREA = JPA.ID                             ");
        sql.append("    LEFT JOIN JC_AREAS JA                           ");
        sql.append("    ON JPA.AREA_CITY = JA.ID                        ");
        sql.append("    LEFT JOIN XC_SALARY_RECORD SR                   ");
        sql.append("    ON IM.STAFF_ID    = SR.STAFF_ID                 ");
        sql.append("    LEFT JOIN JC_BASIC_INFORMATION JBI              ");
        sql.append("    ON SR.STAFF_ID = JBI.ID                         ");
        sql.append("    LEFT JOIN QX_DEPARTMENT QD                      ");
        sql.append("    ON QD.ID = SR.DEPTNAME                        ");
        sql.append("    LEFT JOIN BRANCH                                ");
        sql.append("    ON SR.FILM_NAME = BRANCH.ID                      ");
        sql.append("    LEFT JOIN BRANCH jf                             ");
        sql.append("    ON II.JF_COMPANY = jf.ID                        ");
        sql.append("    LEFT JOIN XC_SALARY_INSURANCE SI                ");
        sql.append("    ON II.IBF_ID = SI.NAME                          ");
        sql.append("    LEFT JOIN DEPT_POSITION JTGW                    ");
        sql.append("    ON JTGW.ID = SR.SPECIFIC_POST                   ");
        sql.append("    LEFT JOIN POST_POSITION BZGW                    ");
        sql.append("    ON BZGW.ID = SR.POST                            ");
        sql.append("    WHERE  IM.IS_DELETE  = 0                        ");
        sql.append("    AND JBI.IS_DELETE = 0                           ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 部门ID
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                String[] deptArray = mapData.get("depId").split(",");
                sql.append("  AND SR.DEPTNAME IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                        ");
            }
            // 是否代缴
            if (!StringUtils.isBlank(mapData.get("isDaiJiao"))) {
                sql.append("   AND IM.IS_DAIJIAO = "+mapData.get("isDaiJiao")+"                                        ");
            }
        }
        sql.append("    GROUP BY SR.ID, SR.CREATEDATE             ");
        sql.append("    ORDER BY SR.CREATEDATE DESC                ");

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
            map.put("company", obj[3]);
            map.put("detpName", obj[7]);
            map.put("sex", obj[4]);
            map.put("age", obj[5]);
            map.put("phone", obj[6]);
            map.put("post", obj[9]);
            map.put("onJob", obj[10]);
            map.put("isDaiJiao", obj[11]);
            map.put("flId", obj[12]);
            map.put("flName", obj[13]);
            map.put("jfArea", obj[14]);
            map.put("jfCompany", obj[15]);
            map.put("baseCompany", obj[16]);
            map.put("basePersonal", obj[17]);
            map.put("sCompany", obj[18]);
            map.put("sPersonal", obj[19]);
            map.put("insuranceId", obj[20]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("fulis", allFls);

        return map;
    }

    @Override
    public List<Map<String, Object>> getChoseStaffInsurances(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                   ");
        sql.append("    ITEMS.ID, JIB.INSUR_NAME,                ");
        sql.append("    SI.BI_COMPANY, SI.BI_PERSONAL, ITEMS.BASE_COMPANY, ITEMS.BASE_PERSONAL,   ");
        sql.append("    ITEMS.PAYMENT_COMPANY, ITEMS.PAYMENT_PERSONAL   ");
        sql.append("    FROM XC_INSURANCE_MIDDLE IM              ");
        sql.append("    LEFT JOIN XC_INSURANCE_ITEMS ITEMS       ");
        sql.append("    ON IM.ID = ITEMS.INSURANCE_MIDDLE_ID     ");
        sql.append("    LEFT JOIN JC_INSURANCE_BENEFITS JIB      ");
        sql.append("    ON ITEMS.IBF_ID = JIB.ID                 ");
        sql.append("    LEFT JOIN XC_SALARY_INSURANCE SI      ");
        sql.append("    ON ITEMS.IBF_ID = SI.NAME                 ");
        sql.append("    WHERE IM.STAFF_ID = '"+id+"'                   ");
        sql.append("    AND IM.IS_DELETE = 0                     ");
        sql.append("    AND ITEMS.IS_DELETE = 0                  ");
        sql.append("    ORDER BY ITEMS.CREATEDATE DESC                ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Calendar cal= Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("endYear", year);
            map.put("endMonth", month);
            map.put("biCompany", obj[2]);
            map.put("biPersonal", obj[3]);
            map.put("baseCompany", obj[4]);
            map.put("basePersonal", obj[5]);
            map.put("jnCompany", obj[6]);
            map.put("jnPersonal", obj[7]);
            dataList.add(map);
        }

        return dataList;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map<String, Object> getEmployeeBaseInfo(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                                                                    ");
        sql.append("  JBI.ID, JBI.JOB_NUMBER, JBI.NAME, NVL(b2.FZX,BRANCH.FZX) FZX, JBI.SEX, JBI.AGE, JBI.PHONE,");
        sql.append("  NVL(QD2.NAME,QD.NAME) DEPTNAME,JBI.POST_ID,JBI.POST,JBI.JOB_LEVEL,BRANCH.ID bId, NVL(MS.AVERAGE_SALARY, 0) AVERAGE_SALARY          ");
        sql.append("  FROM JC_BASIC_INFORMATION JBI                                              ");
        sql.append("  LEFT JOIN XC_SALARY_RECORD SR                                                  ");
        sql.append("  ON JBI.ID = SR.STAFF_ID                                                    ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                                                  ");
        sql.append("  ON QD.ID = JBI.DEPART_ID                                                    ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD2                                                 ");
        sql.append("  ON QD2.ID = SR.DEPTNAME                                                    ");
        sql.append("  LEFT JOIN BRANCH                                                           ");
        sql.append("  ON JBI.FILM_ID = BRANCH.ID                                                 ");
        sql.append("  LEFT JOIN BRANCH b2                                                        ");
        sql.append("  ON SR.FILM_NAME = b2.ID                                                    ");
        sql.append("  LEFT JOIN XC_MONTH_SALARY MS                                               ");
        sql.append("  ON MS.STAFF_ID = SR.STAFF_ID                                               ");
        sql.append("  AND MS.EFFECTIVE_YEAR = (TO_CHAR(SYSDATE, 'yyyy') - 1)                     ");
        sql.append("  WHERE JBI.IS_DELETE = 0                                       ");
        sql.append("    AND JBI.ID = '"+id+"'                                       ");

        List list = findBySql(sql.toString());
        List<Object[]> listObj = list;
        Map<String, Object> map = new HashMap<String, Object>();
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
        map.put("averageSalary", obj[12]);

        return map;
    }

    @Override
    public List<Map<String, Object>> getStaffCanBaoInfo(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                                                           ");
        sql.append("    II.ID,II.IBF_ID, II.AREA,BRANCH.FZX,II.JF_ACCOUNT,II.BASE_COMPANY,II.BASE_PERSONAL,II.YEAR,II.MONTH, ");
        sql.append("    SI.HIGH, SI.LOW ");
        sql.append("    FROM XC_INSURANCE_MIDDLE IM                                                        ");
        sql.append("    LEFT JOIN XC_INSURANCE_ITEMS II                                                                  ");
        sql.append("    ON IM.ID = II.INSURANCE_MIDDLE_ID                                                                ");
        sql.append("    LEFT JOIN BRANCH                                                                  ");
        sql.append("    ON II.JF_COMPANY = BRANCH.ID                                                                ");
        sql.append("    LEFT JOIN XC_SALARY_INSURANCE SI                                                            ");
        sql.append("    ON II.IBF_ID = SI.NAME                                                                ");
        sql.append("    WHERE                                                                                            ");
        sql.append("        IM.STAFF_ID ='"+id+"'                                                                        ");
        sql.append("      AND IM.IS_DELETE  = 0                                                                           ");
        sql.append("      AND II.IS_DELETE = 0                                                                           ");
        sql.append("      AND SI.IS_DELETE = 0                                                                           ");

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
            map.put("high", obj[9]);
            map.put("low", obj[10]);
            dataList.add(map);
        }

        return dataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getNeedJoinInsuranceItemsData(String ids, String searchData, String recordMainId) {
        // 档案ID
        String[] records = recordMainId.split(",");
        String recordId = records[0];
        String[] idsArray = ids.split(",");
        List<SalaryRecord> info = getSession()
                .createCriteria(SalaryRecord.class)
                .add(Restrictions.eq("staffId", idsArray[0]))
                .add(Restrictions.eq("id", recordId)).list();
        String companyId = "";
        String companyName = "";
        if (info.size() > 0) {
            companyId = info.get(0).getFilmName();
            Branch branch = branchDao.get(Restrictions.eq("id", companyId));
            companyName = branch == null? "" : branch.getFzx();
        }

        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                 ");
        sql.append("    JBI.ID,JBI.INSUR_NAME,SI.BI_COMPANY,SI.BI_PERSONAL,                  ");
        sql.append("    SI.HIGH,SI.LOW,SI.NUMBER_ACCURACY, SI.PAY_CARRY_RULE, SI.AREA,JA_CITY.NAME city, SI.STAFF_TYPE,        ");
        sql.append("    JBI.IS_PAY_BY_PERSON        ");
        sql.append("    FROM XC_SALARY_INSURANCE SI            ");
        sql.append("    LEFT JOIN JC_INSURANCE_BENEFITS JBI    ");
        sql.append("    ON SI.NAME = JBI.ID                    ");
        sql.append("    LEFT JOIN JC_PAY_AREAS JPA    ");
        sql.append("    ON SI.AREA = JPA.ID                    ");
        sql.append("    LEFT JOIN JC_AREAS JA_CITY    ");
        sql.append("    ON JPA.AREA_CITY = JA_CITY.ID                    ");
        sql.append("    WHERE SI.IS_DELETE = 0                 ");
        sql.append("      AND SI.COMPANY_ID = '"+companyId+"'                 ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 福利项目
            if (!StringUtils.isBlank(mapData.get("fuliName"))) {
                sql.append("      AND JBI.INSUR_NAME LIKE '%"+mapData.get("fuliName").trim()+"%'                 ");
            }
        }

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        sql.append("      AND TO_DATE(SI.YEAR || '-' || SI.MONTH || '-1', 'yyyy-MM-dd') <=   ");
        sql.append("        TO_DATE("+year+" || '-' || "+month+" || '-1', 'yyyy-MM-dd')     ");
        sql.append("    ORDER BY SI.CREATEDATE ASC             ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ibfId", obj[0]);
            map.put("name", obj[1]);
            map.put("jfCompany", companyId);
            map.put("jfCompanyName", companyName);
            map.put("year", year);
            map.put("month", month);
            map.put("biCompany", obj[2]);
            map.put("biPersonal", obj[3]);
            map.put("high", obj[4]);
            map.put("low", obj[5]);
            map.put("xs", obj[6]);
            map.put("payCarryRule", obj[7]);
            map.put("area", obj[8]);
            map.put("staffType", obj[10]);
            map.put("isPerson", obj[11]);// 是否个人缴纳 0 否 1 是
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public Map<String, Object> getSlaryReportListData(String searchData, Pager pager) {
        pager  = findPagerBySql(pager, getSlaryReportSql(searchData));
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("jobNumber", obj[2]);
            map.put("name", obj[3]);
            map.put("itemsId", obj[4]);
            map.put("itemsName", obj[5]);
            map.put("charge", obj[6]);
            map.put("assId", obj[7]);
            map.put("companyName", obj[8]);
            map.put("deptName", obj[9]);
            map.put("payPeriod", obj[10]);
            map.put("sex", obj[11]);
            map.put("empType", obj[12]);
            map.put("jobLevel", obj[13]);
            map.put("cbdw", obj[14]);
            map.put("cb", obj[15]);
            map.put("sffx", obj[16]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public String getSlaryReportSql(String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT TT.ID,                                                               ");
        sql.append("      TT.STAFF_ID,                                                 ");
        sql.append("      TT.JOB_NUMBER,                                             ");
        sql.append("      TT.STAFF_NAME,                                                  ");
        sql.append("      TT.IBF_ID,             ");
        sql.append("      TT.IBF_NAME,         ");
        sql.append("      TT.ASSIGNATION_CHARGE,  ");
        sql.append("      TT.ASSID,                ");
        sql.append("      TT.FZX FILM_NAME,                                              ");
        sql.append("      TT.DEPTNAME DEPART_ID,                                               ");
        sql.append("      TT.NAME PERIOD, TT.SEX, TT.EMP_TYPE, TT.JOB_LEVEL, ");
        sql.append("      SP.COST_COM_ID, B.FZX cb, SP.IS_SALARY                    ");
        sql.append("    FROM (                                                                      ");
        sql.append("      SELECT MIN(ASTAFF.ID) ID,                                                                                       ");
        sql.append("        MIN(ASTAFF.STAFF_ID) STAFF_ID,                                                                                ");
        sql.append("        MIN(ASTAFF.JOB_NUMBER) JOB_NUMBER,                                                                            ");
        sql.append("        MIN(ASTAFF.STAFF_NAME) STAFF_NAME,                                                                            ");
        sql.append("        CONCAT_ARRAY(CAST(COLLECT(ASS.IBF_ID ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) IBF_ID,           ");
        sql.append("        CONCAT_ARRAY(CAST(COLLECT(ASS.IBF_NAME ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) IBF_NAME,       ");
        sql.append("        CONCAT_ARRAY(CAST(COLLECT(ASS.ASSIGNATION_CHARGE ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) ASSIGNATION_CHARGE,   ");
        sql.append("        CONCAT_ARRAY(CAST(COLLECT(ASS.ID ORDER BY ASS.SX ASC, ASS.IBF_ID ASC) AS TAB_VARCHAR2)) ASSID, MIN(ASTAFF.IS_DELETE) IS_DELETE, ");
        sql.append("        MIN(ASTAFF.COMPANY_NAME) FZX, MIN(ASTAFF.DEPT_NAME) DEPTNAME, MIN(ASTAFF.DEPT_ID) DEPTID, MIN(ASTAFF.CREATEDATE) CREATEDATE, MIN(ASS.SX) SX, ");
        sql.append("        MIN(XP.NAME) NAME, MIN(JBI.SEX) SEX, MIN(JBI.EMP_TYPE) EMP_TYPE, MIN(JBI.JOB_LEVEL) JOB_LEVEL,             ");
        sql.append("        MIN(SA.SALARY_GROUP_ID) SALARY_GROUP_ID             ");
        sql.append("      FROM XC_SALARY_ASSIGNATION SA                                                                                  ");
        sql.append("      LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                                                                          ");
        sql.append("      ON ASTAFF.SALARY_ASSIGNATION_ID = SA.ID                                                                        ");
        sql.append("      AND ASTAFF.IS_DELETE            = 0                                                                            ");
        sql.append("      LEFT JOIN XC_SALARY_RECORD SR                                                                                  ");
        sql.append("      ON SR.STAFF_ID = ASTAFF.STAFF_ID                                                                               ");
        sql.append("      LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                                                      ");
        sql.append("      ON ASTAFF.ID      = ASS.ASSIGNATION_STAFF_ID                                                                   ");
        sql.append("      AND ASS.IS_DELETE = 0                                                                                          ");

        StringBuilder searchSql = new StringBuilder();
        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 部门ID
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                String[] deptArray = mapData.get("depId").split(",");
                searchSql.append("  AND TT.DEPTID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 职工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                searchSql.append("  AND TT.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                                 ");
            }
            // 职工名称
            if (!StringUtils.isBlank(mapData.get("name"))) {
                searchSql.append("  AND TT.STAFF_NAME LIKE '%"+mapData.get("name").trim()+"%'                                 ");
            }
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("payPeriod"))) {
                searchSql.append("  AND TT.NAME LIKE '%"+mapData.get("payPeriod").trim()+"%'                                 ");
            }
            // 性别
            if (!StringUtils.isBlank(mapData.get("sex"))) {
                searchSql.append("  AND TT.SEX = "+mapData.get("sex")+"                                 ");
            }
            // 员工类型
            if (!StringUtils.isBlank(mapData.get("empType"))) {
                searchSql.append("  AND TT.EMP_TYPE = "+mapData.get("empType")+"                                 ");
            }
            // 职务级别
            if (!StringUtils.isBlank(mapData.get("zwjb"))) {
                searchSql.append("  AND TT.JOB_LEVEL = '"+mapData.get("zwjb")+"'                                 ");
            }
            // 成本单位
            if (!StringUtils.isBlank(mapData.get("cbdw"))) {
                searchSql.append("  AND SP.COST_COM_ID IN ('"+StringUtils.join(mapData.get("cbdw").split(","), "','")+"') ");
            }
            // 是否发薪
            if (!StringUtils.isBlank(mapData.get("sffx"))) {
                searchSql.append("  AND SP.IS_SALARY = "+mapData.get("sffx"));
            }
            // 薪资项目
            if (!StringUtils.isBlank(mapData.get("salaryItem"))) {
                String[] itemArray = mapData.get("salaryItem").split(",");
                sql.append("  AND ASS.IBF_ID IN ('"+StringUtils.join(itemArray, "','")+"') ");
            }
        }

        sql.append("      LEFT JOIN XC_SALARY_ITEMS SI                                                                                   ");
        sql.append("      ON ASS.IBF_ID = SI.ID                                                                                          ");
        sql.append("      LEFT JOIN XC_PERIODDATA XP                                                                                     ");
        sql.append("      ON SA.SALARY_PERIOD = XP.ID                                                                                    ");
        sql.append("      LEFT JOIN JC_BASIC_INFORMATION JBI                                                                             ");
        sql.append("      ON SR.STAFF_ID = JBI.ID                                                                                        ");
        sql.append("      WHERE SA.IS_DELETE = 0                                                                                         ");
        sql.append("      GROUP BY ASTAFF.ID                                                                                             ");
        sql.append("      ORDER BY MIN(ASTAFF.STAFF_ID) ASC                                                                              ");
        sql.append("    ) TT                                                                        ");
        sql.append("      LEFT JOIN XC_SALARY_GROUP SG                                           ");
        sql.append("      ON TT.SALARY_GROUP_ID = SG.ID                                           ");
        sql.append("      LEFT JOIN XC_SALARY_PERSONAL SP                                       ");
        sql.append("      ON SP.SALARY_RECORD_ID = TT.STAFF_ID                                  ");
        sql.append("      AND SP.SALARY_GROUP_ID = SG.ID                                  ");
        sql.append("      LEFT JOIN BRANCH B                                           ");
        sql.append("      ON SP.COST_COM_ID = B.ID                                           ");
        sql.append("    WHERE TT.IS_DELETE = 0                                                      ");
        sql.append(searchSql);
        sql.append("    ORDER BY TT.CREATEDATE DESC                                                 ");

        return sql.toString();
    }

    @Override
    public Map<String, Object> getOffBankListData(String searchData, Pager pager) {
        pager  = findPagerBySql(pager, getOffBankSql(searchData));
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("jobNumber", obj[2]);
            map.put("name", obj[3]);
            map.put("itemsId", obj[4]);
            map.put("itemsName", obj[5]);
            map.put("charge", obj[6]);
            map.put("assId", obj[7]);
            map.put("companyName", obj[8]);
            map.put("deptName", obj[9]);
            map.put("payPeriod", obj[10]);
            map.put("idCard", obj[11]);
            map.put("accountBank", obj[12]);
            map.put("dicName", obj[13]);
            map.put("account", obj[14]);
            map.put("userName", obj[15]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }


    @Override
    public String getOffBankSql(String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT TT.ID,                                                               ");
        sql.append("      MIN(TT.STAFF_ID) STAFFID,                                                 ");
        sql.append("      MIN(TT.JOB_NUMBER) JOBNUMBER,                                             ");
        sql.append("      MIN(TT.STAFF_NAME) NAME,                                                  ");
        sql.append("      LISTAGG(TT.IBF_ID, ',') WITHIN GROUP(ORDER BY TT.SX) ITEMSID,             ");
        sql.append("      LISTAGG(TT.IBF_NAME, ',') WITHIN GROUP(ORDER BY TT.SX) ITEMSNAME,         ");
        sql.append("      LISTAGG(TT.ASSIGNATION_CHARGE, ',') WITHIN GROUP(ORDER BY TT.SX) CHARGE,  ");
        sql.append("      LISTAGG(TT.ASSID, ',') WITHIN GROUP(ORDER BY TT.SX) ASSID,                ");
        sql.append("      MIN(TT.FZX) FILM_NAME,                                              ");
        sql.append("      MIN(TT.DEPTNAME) DEPART_ID,                                               ");
        sql.append("      MIN(TT.NAME) PERIOD,                                                       ");
        sql.append("      MIN(TT.IDCARD) IDCARD, MIN(TT.ACCOUNT_BANK) ACCOUNT_BANK,                  ");
        sql.append("      MIN(TT.DIC_NAME) DIC_NAME, MIN(TT.ACCOUNT) ACCOUNT,                  ");
        sql.append("      MIN(TT.USER_NAME) USER_NAME                   ");
        sql.append("    FROM (                                                                      ");
        sql.append("      SELECT ASTAFF.ID,                                                         ");
        sql.append("        ASTAFF.STAFF_ID,                                                        ");
        sql.append("        ASTAFF.JOB_NUMBER,                                                      ");
        sql.append("        ASTAFF.STAFF_NAME,                                                      ");
        sql.append("        ASS.IBF_ID,                                                             ");
        sql.append("        ASS.IBF_NAME,                                                           ");
        sql.append("        ASS.ASSIGNATION_CHARGE,                                                 ");
        sql.append("        ASS.ID ASSID,                                                           ");
        sql.append("        ASTAFF.IS_DELETE,                                                       ");
        sql.append("        ASTAFF.CREATEDATE,                                                      ");
        sql.append("        ASS.SX,                                                                 ");
        sql.append("        ASTAFF.COMPANY_NAME FZX,                                                           ");
        sql.append("        ASTAFF.DEPT_NAME DEPTNAME,                                                       ");
        sql.append("        ASTAFF.DEPT_ID DEPTID,                                                           ");
        sql.append("        XP.NAME,                                                                ");
        sql.append("        JBI.ID_NUMBER IDCARD,SAC.ACCOUNT_BANK,                                   ");
        sql.append("        JQD.DIC_NAME,SAC.ACCOUNT,SAC.USER_NAME                                    ");
        sql.append("      FROM XC_SALARY_ASSIGNATION SA                                             ");
        sql.append("      LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                                     ");
        sql.append("      ON ASTAFF.SALARY_ASSIGNATION_ID = SA.ID                                   ");
        sql.append("      AND ASTAFF.IS_DELETE = 0                                                  ");
        sql.append("      LEFT JOIN JC_BASIC_INFORMATION JBI                      ");
        sql.append("      ON ASTAFF.STAFF_ID = JBI.ID                               ");
        sql.append("      LEFT JOIN XC_SALARY_RECORD SR                               ");
        sql.append("      ON SR.STAFF_ID = ASTAFF.STAFF_ID                               ");
        sql.append("      LEFT JOIN XC_SALARY_ACCOUNT SAC                          ");
        sql.append("      ON ASTAFF.STAFF_ID = SAC.STAFF_ID                            ");
        sql.append("      AND SR.ID = SAC.RECORD_MAIN_ID                            ");
        sql.append("      AND SAC.IS_DELETE = 0                                    ");
        sql.append("      LEFT JOIN JC_QX_DECTIONARY JQD                          ");
        sql.append("      ON JQD.DIC_VALUE = SAC.ACCOUNT_BANK                    ");
        sql.append("      AND JQD.PID = '402881945bd6397b015bd66c787e0009'       ");
        sql.append("      LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                 ");
        sql.append("      ON ASTAFF.ID = ASS.ASSIGNATION_STAFF_ID                                   ");
        sql.append("      AND ASS.IS_DELETE = 0                                                     ");

        StringBuilder searchSql = new StringBuilder();
        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 部门ID
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                String[] deptArray = mapData.get("depId").split(",");
                searchSql.append("  AND TT.DEPTID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 职工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                searchSql.append("  AND TT.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                                 ");
            }
            // 职工名称
            if (!StringUtils.isBlank(mapData.get("name"))) {
                searchSql.append("  AND TT.STAFF_NAME LIKE '%"+mapData.get("name").trim()+"%'                                 ");
            }
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("payPeriod"))) {
                searchSql.append("  AND TT.NAME LIKE '%"+mapData.get("payPeriod").trim()+"%'                                 ");
            }
            // 薪资项目
            if (!StringUtils.isBlank(mapData.get("salaryItem"))) {
                String[] itemArray = mapData.get("salaryItem").split(",");
                sql.append("  AND ASS.IBF_ID IN ('"+StringUtils.join(itemArray, "','")+"') ");
            }
        }
        sql.append("      LEFT JOIN XC_PERIODDATA XP                                                ");
        sql.append("      ON SA.SALARY_PERIOD = XP.ID                                               ");
        sql.append("      WHERE SA.IS_DELETE = 0                                                    ");
        sql.append("      ORDER BY SA.ID ASC, ASTAFF.STAFF_ID ASC, ASS.SX ASC                       ");
        sql.append("    ) TT                                                                        ");
        sql.append("    WHERE TT.IS_DELETE = 0                                                      ");
        sql.append(searchSql);
        sql.append("    GROUP BY TT.ID,TT.CREATEDATE                                                ");
        sql.append("    ORDER BY TT.CREATEDATE DESC                                                 ");

        return sql.toString();
    }

    @Override
    public Map<String, Object> getInsuranceCheckListData(String companyId, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                             ");
        sql.append("     PCM.ID, MIN(PCM.COMPANY_ID) COMPANY_ID, MIN(B.FZX) FZX, MIN(CMI.JF_YEAR) JF_YEAR, ");
        sql.append("     MIN(CMI.JF_MONTH) JF_MONTH, MIN(JIB.ID) INSURANCEID, MIN(JIB.INSUR_NAME) INSUR_NAME,  ");
        sql.append("     MIN(PCM.DOC_LOCATION) DOC_LOCATION  ");
        sql.append("   FROM XC_PAYMENT_CHECK_MONTH PCM                                    ");
        sql.append("   LEFT JOIN XC_CHECK_MONTH_INFO CMI                                  ");
        sql.append("   ON PCM.ID  = CMI.PAYMENT_CHECK_MONTH_ID                            ");
        sql.append("   AND CMI.IS_DELETE = 0                                              ");
        sql.append("   LEFT JOIN JC_INSURANCE_BENEFITS JIB                                ");
        sql.append("   ON JIB.ID = PCM.INSURANCE_ID                                       ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                 ");
        sql.append("   ON CMI.STAFF_ID = JBI.ID                                           ");
        sql.append("   LEFT JOIN BRANCH B                                                 ");
        sql.append("   ON PCM.COMPANY_ID = B.ID                                           ");
        sql.append("   WHERE PCM.IS_DELETE = 0                                            ");

//        if (!StringUtils.isBlank(companyId)) {
            sql.append("  AND PCM.COMPANY_ID IN ('"+StringUtils.join(companyId.split(","), "','")+"') ");
//        }

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 缴费年
            if (!StringUtils.isBlank(mapData.get("jsYear"))) {
                sql.append("   AND CMI.JF_YEAR = "+mapData.get("jsYear").trim()+"                                        ");
            }
            // 缴费月
            if (!StringUtils.isBlank(mapData.get("jsMonth"))) {
                sql.append("   AND CMI.JF_MONTH = "+mapData.get("jsMonth").trim()+"                                        ");
            }
            // 保险项目
            if (!StringUtils.isBlank(mapData.get("insurance"))) {
                sql.append("   AND JIB.ID = '"+mapData.get("insurance").trim()+"'                                        ");
            }
        }
        sql.append("   GROUP BY PCM.ID                   ");
        sql.append("   ORDER BY MIN(CMI.JF_YEAR) DESC, MIN(CMI.JF_MONTH) DESC, MIN( PCM.CREATEDATE) DESC                   ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("companyId", obj[1]);// 公司ID
            map.put("company", obj[2]);
            map.put("jfDate", obj[3]+"年"+obj[4]+"月");
            map.put("insuranceId", obj[5]);
            map.put("insuranceName", obj[6]);
            map.put("docLocation", obj[7]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getInsuranceStaffListData(String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                        ");
        sql.append("    PHYSICALDATA.ID,                ");
        sql.append("    PHYSICALDATA.JOBNUMBER,         ");
        sql.append("    PHYSICALDATA.NAME,              ");
        sql.append("    PHYSICALDATA.ISDAIJIAO,         ");
        sql.append("    PHYSICALDATA.YEAR,              ");
        sql.append("    PHYSICALDATA.MONTH,             ");
        sql.append("    PHYSICALDATA.PJGZ,              ");
        sql.append("    PHYSICALDATA.FULIID,            ");
        sql.append("    PHYSICALDATA.FULINAME,          ");
        sql.append("    PHYSICALDATA.AREA,              ");
        sql.append("    PHYSICALDATA.JFCOMPANY,         ");
        sql.append("    PHYSICALDATA.BASECOMPANY,       ");
        sql.append("    PHYSICALDATA.BASEPERSONAL,      ");
        sql.append("    PHYSICALDATA.PAYMENT_COMPANY,   ");
        sql.append("    PHYSICALDATA.PAYMENT_PERSONAL,  ");
        sql.append("    PHYSICALDATA.FZX,               ");
        sql.append("    PHYSICALDATA.DEPTNAME,          ");
        sql.append("    PHYSICALDATA.STAFFID,           ");
        sql.append("    PHYSICALDATA.PI_ID,             ");
        sql.append("    PHYSICALDATA.BI_COMPANY,        ");
        sql.append("    PHYSICALDATA.BI_PERSONAL,       ");
        sql.append("    PHYSICALDATA.IS_PAY_BACK,       ");
        sql.append("    PHYSICALDATA.PAY_BACK_YEAR,     ");
        sql.append("    PHYSICALDATA.PAY_BACK_MONTH,    ");
        sql.append("    PHYSICALDATA.IDCARD,            ");
        sql.append("    PHYSICALDATA.KIND,              ");
        sql.append("    CMP.NOTE                        ");
        sql.append("   FROM                                                                                           ");
        sql.append("     (SELECT LIVE.ID,                                                                             ");
        sql.append("       LIVE.JOBNUMBER, LIVE.NAME, LIVE.ISDAIJIAO, LIVE.YEAR, LIVE.MONTH,                          ");
        sql.append("       LIVE.PJGZ, LIVE.FULIID, LIVE.FULINAME, LIVE.AREA, LIVE.JFCOMPANY,                          ");
        sql.append("       LIVE.BASECOMPANY, LIVE.BASEPERSONAL, LIVE.PAYMENT_COMPANY,                                 ");
        sql.append("       LIVE.PAYMENT_PERSONAL, LIVE.FZX, LIVE.DEPTNAME, LIVE.STAFFID,                              ");
        sql.append("       LIVE.PI_ID, LIVE.BI_COMPANY, LIVE.BI_PERSONAL, LIVE.IS_PAY_BACK,                           ");
        sql.append("       LIVE.PAY_BACK_YEAR, LIVE.PAY_BACK_MONTH, LIVE.IDCARD, 1 KIND                               ");
        sql.append("     FROM                                                                                         ");
        sql.append("       (SELECT MIN(PP.ID) ID,                                                                     ");
        sql.append("         MIN(JBI.JOB_NUMBER) JOBNUMBER,                                                           ");
        sql.append("         MIN(JBI.NAME) NAME,                                                                      ");
        sql.append("         MIN(PP.IS_DAIJIAO) ISDAIJIAO,                                                            ");
        sql.append("         MIN(PP.JF_YEAR) YEAR,                                                                    ");
        sql.append("         MIN(PP.JF_MONTH) MONTH,                                                                  ");
        sql.append("         MIN(PP.PJGZ) PJGZ,                                                                       ");
        sql.append("         WM_CONCAT(JIB.ID) FULIID,                                                                ");
        sql.append("         WM_CONCAT(JIB.INSUR_NAME) FULINAME,                                                      ");
        sql.append("         WM_CONCAT(PI.AREA) AREA,                                                                 ");
        sql.append("         WM_CONCAT(PI.JF_COMPANY) JFCOMPANY,                                                      ");
        sql.append("         WM_CONCAT(PI.BASE_COMPANY) BASECOMPANY,                                                  ");
        sql.append("         WM_CONCAT(PI.BASE_PERSONAL) BASEPERSONAL,                                                ");
        sql.append("         WM_CONCAT(PI.PAYMENT_COMPANY) PAYMENT_COMPANY,                                           ");
        sql.append("         WM_CONCAT(PI.PAYMENT_PERSONAL) PAYMENT_PERSONAL,                                         ");
        sql.append("         MIN(BRANCH.FZX) FZX,                                                                     ");
        sql.append("         MIN(QD.NAME) DEPTNAME,                                                                   ");
        sql.append("         MIN(JBI.ID) STAFFID,                                                                     ");
        sql.append("         MIN(PI.ID) PI_ID,                                                                        ");
        sql.append("         WM_CONCAT(SI.BI_COMPANY) BI_COMPANY,                                                     ");
        sql.append("         WM_CONCAT(SI.BI_PERSONAL) BI_PERSONAL,                                                   ");
        sql.append("         MIN(PP.IS_PAY_BACK) IS_PAY_BACK,                                                         ");
        sql.append("         MIN(PP.PAY_BACK_YEAR) PAY_BACK_YEAR,                                                     ");
        sql.append("         MIN(PP.PAY_BACK_MONTH) PAY_BACK_MONTH,                                                   ");
        sql.append("         MIN(JBI.ID_NUMBER) IDCARD                                                                ");
        sql.append("       FROM XC_PROCESS_PAYMENT PP                                                                 ");
        sql.append("       LEFT JOIN XC_PAYMENT_INSURANCE PI                                                          ");
        sql.append("       ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                                    ");
        sql.append("       LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                        ");
        sql.append("       ON PI.IBF_ID = JIB.ID                                                                      ");
        sql.append("       LEFT JOIN XC_SALARY_RECORD SR                                                              ");
        sql.append("       ON SR.STAFF_ID = PP.STAFF_ID                                                               ");
        sql.append("       LEFT JOIN JC_BASIC_INFORMATION JBI                                                         ");
        sql.append("       ON SR.STAFF_ID = JBI.ID                                                                    ");
        sql.append("       LEFT JOIN QX_DEPARTMENT QD                                                                 ");
        sql.append("       ON QD.ID = SR.DEPTNAME                                                                     ");
        sql.append("       LEFT JOIN BRANCH                                                                           ");
        sql.append("       ON SR.FILM_NAME = BRANCH.ID                                                                ");
        sql.append("       LEFT JOIN XC_SALARY_INSURANCE SI                                                           ");
        sql.append("       ON JIB.ID = SI.NAME                                                                        ");
        sql.append("       LEFT JOIN XC_SALARY_PAYMENT_PROCESS SPP                                                    ");
        sql.append("       ON PP.SALARY_PAYMENT_PROCESS_ID = SPP.ID                                                   ");
        sql.append("       WHERE PP.IS_DELETE = 0                                                                     ");
        sql.append("       AND SPP.IS_DELETE = 0                                                                      ");
        sql.append("       AND PI.IS_DELETE = 0                                                                       ");

        StringBuilder checkSql = new StringBuilder();
        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        String fuli = "";
        if (mapData != null) {
            // 部门ID
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                sql.append("  AND SR.FILM_NAME = '"+mapData.get("depId")+"' ");
                checkSql.append("  AND PCM.COMPANY_ID = '"+mapData.get("depId")+"' ");
            }
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                        ");
            }
            // 缴费年
            if (!StringUtils.isBlank(mapData.get("jsYear"))) {
                sql.append("   AND PP.JF_YEAR = "+mapData.get("jsYear").trim()+"                                        ");
                checkSql.append("   AND CMI.JF_YEAR = "+mapData.get("jsYear").trim()+"                                        ");
            }
            // 缴费月
            if (!StringUtils.isBlank(mapData.get("jsMonth"))) {
                sql.append("   AND PP.JF_MONTH = "+mapData.get("jsMonth").trim()+"                                        ");
                checkSql.append("   AND CMI.JF_MONTH = "+mapData.get("jsMonth").trim()+"                                        ");
            }
            // 福利项目
            if (!StringUtils.isBlank(mapData.get("insuranceId"))) {
                sql.append("   AND JIB.ID = '"+mapData.get("insuranceId").trim()+"'                                        ");
                checkSql.append("   AND PCM.INSURANCE_ID = '"+mapData.get("insuranceId").trim()+"'                                        ");
                fuli = "   ID = '"+mapData.get("insuranceId").trim()+"'";
            }
        }
        sql.append("       GROUP BY JBI.ID,                                                                           ");
        sql.append("         PP.IS_PAY_BACK,                                                                          ");
        sql.append("         PP.CREATEDATE                                                                            ");
        sql.append("       ORDER BY MIN(PP.JF_YEAR) DESC,                                                             ");
        sql.append("         MIN(PP.JF_MONTH) DESC                                                                    ");
        sql.append("       ) LIVE                                                                                     ");
        sql.append("     UNION ALL                                                                                    ");
        sql.append("     SELECT CMI.ID,                                                                               ");
        sql.append("       JBI.JOB_NUMBER JOBNUMBER, CMI.NAME, 0 ISDAIJIAO, CMI.JF_YEAR YEAR, CMI.JF_MONTH MONTH,    ");
        sql.append("       '' PJGZ, PCM.INSURANCE_ID FULIID, JIB.INSUR_NAME FULINAME, '' AREA, CMI.COMPANY JFCOMPANY, ");
        sql.append("       '' BASECOMPANY, CMI.JFJS BASEPERSONAL, CMI.PAYMENT_COMPANY, CMI.PAYMENT_PERSONAL,          ");
        sql.append("       '' FZX, '' DEPTNAME, CMI.STAFF_ID STAFFID, '' PI_ID, '' BI_COMPANY,                        ");
        sql.append("       '' BI_PERSONAL, CMI.JF_TYPE IS_PAY_BACK, CMI.PAY_BACK_YEAR, CMI.PAY_BACK_MONTH,            ");
        sql.append("       CMI.ID_CARD IDCARD, 2 KIND                                                                 ");
        sql.append("     FROM XC_PAYMENT_CHECK_MONTH PCM                                                              ");
        sql.append("     LEFT JOIN XC_CHECK_MONTH_INFO CMI                                                            ");
        sql.append("     ON PCM.ID  = CMI.PAYMENT_CHECK_MONTH_ID                                                      ");
        sql.append("     AND CMI.IS_DELETE = 0                                                                        ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                          ");
        sql.append("     ON JIB.ID = PCM.INSURANCE_ID                                                                 ");
        sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI                                                           ");
        sql.append("     ON CMI.STAFF_ID = JBI.ID                                                                     ");
        sql.append("     WHERE PCM.IS_DELETE = 0                                                                      ");
        sql.append(checkSql);
        sql.append("     ) PHYSICALDATA                                                                               ");
        sql.append("   LEFT JOIN XC_CHECK_MONTH_PROPERTIES CMP                                                        ");
        sql.append("   ON PHYSICALDATA.ID = CMP.CHECK_ID                                                              ");
        sql.append("   AND PHYSICALDATA.STAFFID = CMP.STAFF_ID                                                        ");
        sql.append("   AND PHYSICALDATA.FULIID = CMP.INSURANCE_ID                                                     ");
        sql.append("   AND PHYSICALDATA.YEAR = CMP.EFFECTIVE_YEAR                                                     ");
        sql.append("   AND PHYSICALDATA.MONTH = CMP.EFFECTIVE_MONTH                                                   ");
        sql.append("   ORDER BY PHYSICALDATA.STAFFID ASC                                                              ");

        pager  = findPagerBySql(pager, sql.toString());

        // 查找福利项目
        String lookFuli = "";
        if (!StringUtils.isBlank(fuli)) {
            lookFuli = " WHERE " + fuli;
        }
        SQLQuery query = getSession().createSQLQuery("SELECT ID, INSUR_NAME FROM JC_INSURANCE_BENEFITS "+lookFuli+" ORDER BY CREATEDATE ASC");
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
            map.put("idCard", obj[24]);
            map.put("kind", obj[25]);
            try {
                map.put("note", LingUtil.ClobToString((java.sql.Clob)obj[26]));
            } catch (Exception e) {
                map.put("note", "");
            }
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("fulis", allFls);

        return map;
    }

    @Override
    public String getInsuranceStaffListDataSql(String searchData) {
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
        sql.append("    MIN(PP.IS_PAY_BACK) IS_PAY_BACK,MIN(PP.PAY_BACK_YEAR) PAY_BACK_YEAR,MIN(PP.PAY_BACK_MONTH) PAY_BACK_MONTH, ");
        sql.append("    MIN(JBI.ID_NUMBER) IDCARD ");
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
        sql.append("    LEFT JOIN XC_SALARY_PAYMENT_PROCESS SPP                           ");
        sql.append("    ON PP.SALARY_PAYMENT_PROCESS_ID = SPP.ID                        ");
        sql.append("    WHERE  PP.IS_DELETE  = 0                    ");
        sql.append("    AND SPP.IS_DELETE = 0                       ");
        sql.append("    AND PI.IS_DELETE = 0                       ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 部门ID
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                sql.append("  AND SR.FILM_NAME = '"+mapData.get("depId")+"' ");
            }
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                        ");
            }
            // 缴费年
            if (!StringUtils.isBlank(mapData.get("effectiveYear"))) {
                sql.append("   AND PP.JF_YEAR = "+mapData.get("effectiveYear").trim()+"                                        ");
            }
            // 缴费月
            if (!StringUtils.isBlank(mapData.get("effectiveMonth"))) {
                sql.append("   AND PP.JF_MONTH = "+mapData.get("effectiveMonth").trim()+"                                        ");
            }
            // 保险项目
            if (!StringUtils.isBlank(mapData.get("insuranceId"))) {
                sql.append("   AND JIB.ID = '"+mapData.get("insuranceId").trim()+"'                                        ");
            }
        }
        sql.append("    GROUP BY JBI.ID, PP.IS_PAY_BACK,PP.CREATEDATE                                                               ");
        sql.append("    ORDER BY MIN(PP.JF_YEAR) DESC,MIN(PP.JF_MONTH) DESC                                           ");

        return sql.toString();
    }

    @Override
    public List<Map<String, Object>> getCurCompanyInsurance(String depId, String effectiveYear, String effectiveMonth) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                     ");
        sql.append("      PPF.FULI_ID, MIN(JIB.INSUR_NAME) INSUR_NAME                              ");
        sql.append("    FROM XC_SALARY_PAYMENT_PROCESS SPP         ");
        sql.append("    LEFT JOIN XC_PAYMENT_PROCESS_FULI PPF      ");
        sql.append("    ON SPP.ID = PPF.SALARY_PAYMENT_PROCESS_ID  ");
        sql.append("    LEFT JOIN JC_INSURANCE_BENEFITS JIB      ");
        sql.append("    ON PPF.FULI_ID = JIB.ID  ");
        sql.append("    LEFT JOIN XC_PAYMENT_PROCESS_DEPT PPD      ");
        sql.append("    ON SPP.ID = PPD.SALARY_PAYMENT_PROCESS_ID  ");
        sql.append("    WHERE SPP.IS_DELETE = 0                    ");
        sql.append("    AND PPD.DEPT_ID IN ('"+StringUtils.join(depId.split(","), "','")+"')     ");
        sql.append("    AND PPF.IS_DELETE = 0                      ");
        sql.append("    AND SPP.EFFECTIVE_YEAR = "+effectiveYear+"              ");
        sql.append("    AND SPP.EFFECTIVE_MONTH = "+effectiveMonth+"                ");
        sql.append("    GROUP BY PPF.FULI_ID                       ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("text", obj[1]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String getCheckInsuranceData(String formdata) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                  ");
        sql.append("    NVL(ID, ' ') ID,                                                                                    ");
        sql.append("    NVL(JOBNUMBER, ' ') JOBNUMBER,                                                                             ");
        sql.append("    LAG(JOBNUMBER, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastJobNumber,           ");
        sql.append("    LEAD(JOBNUMBER, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextJobNumber,          ");
        sql.append("    NVL(NAME, ' ') NAME,                                                                                  ");
        sql.append("    LAG(NAME, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastName,                     ");
        sql.append("    LEAD(NAME, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextName,                    ");
        sql.append("    NVL(YEAR, 0) YEAR,                                                                                  ");
        sql.append("    LAG(YEAR, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastYear,                     ");
        sql.append("    LEAD(YEAR, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextYear,                    ");
        sql.append("    NVL(MONTH, 0) MONTH,                                                                                 ");
        sql.append("    LAG(MONTH, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastMonth,                   ");
        sql.append("    LEAD(MONTH, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextMonth,                  ");
        sql.append("    NVL(IS_PAY_BACK, 0) IS_PAY_BACK,                                                                           ");
        sql.append("    LAG(IS_PAY_BACK, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastBj,                ");
        sql.append("    LEAD(IS_PAY_BACK, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextBj,               ");
        sql.append("    NVL(PAY_BACK_YEAR, 0) PAY_BACK_YEAR,                                                                         ");
        sql.append("    LAG(PAY_BACK_YEAR, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC)lastBjYear,           ");
        sql.append("    LEAD(PAY_BACK_YEAR, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextBjYear,         ");
        sql.append("    NVL(PAY_BACK_MONTH, 0) PAY_BACK_MONTH,                                                                        ");
        sql.append("    LAG(PAY_BACK_MONTH, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastBjMonth,        ");
        sql.append("    LEAD(PAY_BACK_MONTH, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextBjMonth,       ");
        sql.append("    NVL(FULIID, ' ') FULIID,                                                                                ");
        sql.append("    NVL(FULINAME, ' ') FULINAME,                                                                              ");
        sql.append("    NVL(JFCOMPANY, ' ') JFCOMPANY,                                                                             ");
        sql.append("    LAG(JFCOMPANY, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastJfCompany,           ");
        sql.append("    LEAD(JFCOMPANY, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextJfCompany,          ");
        sql.append("    NVL(BASECOMPANY, '0') BASECOMPANY,                                                                           ");
        sql.append("    LAG(BASECOMPANY, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastBASECOMPANY,       ");
        sql.append("    LEAD(BASECOMPANY, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextBASECOMPANY,      ");
        sql.append("    NVL(BASEPERSONAL, '0') BASEPERSONAL,                                                                          ");
        sql.append("    LAG(BASEPERSONAL, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastBASEPERSONAL,     ");
        sql.append("    LEAD(BASEPERSONAL, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextBASEPERSONAL,    ");
        sql.append("    NVL(PAYMENT_COMPANY, '0') PAYMENT_COMPANY,                                                                       ");
        sql.append("    LAG(PAYMENT_COMPANY, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastPayCompany,    ");
        sql.append("    LEAD(PAYMENT_COMPANY, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextPayCompany,   ");
        sql.append("    NVL(PAYMENT_PERSONAL, '0') PAYMENT_PERSONAL,                                                                      ");
        sql.append("    LAG(PAYMENT_PERSONAL, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastPayPersonal,  ");
        sql.append("    LEAD(PAYMENT_PERSONAL, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextPayPersonal, ");
        sql.append("    NVL(STAFFID, ' ') STAFFID,                                                                               ");
        sql.append("    LAG(STAFFID, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastStaffId,               ");
        sql.append("    LEAD(STAFFID, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextStaffId,              ");
        sql.append("    NVL(IDCARD, ' ') IDCARD,                                                                                ");
        sql.append("    LAG(IDCARD, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastIDCARD,                 ");
        sql.append("    LEAD(IDCARD, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextIDCARD,                ");
        sql.append("    KIND,                                                                                  ");
        sql.append("    LAG(KIND, 1, -2) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) lastKind,                     ");
        sql.append("    LEAD(KIND, 1, -1) OVER(ORDER BY PHYSICALDATA.STAFFID ASC) nextKind                     ");
        sql.append("   FROM                                                                                           ");
        sql.append("     (SELECT LIVE.ID,                                                                             ");
        sql.append("       LIVE.JOBNUMBER, LIVE.NAME, LIVE.ISDAIJIAO, LIVE.YEAR, LIVE.MONTH,                          ");
        sql.append("       LIVE.PJGZ, LIVE.FULIID, LIVE.FULINAME, LIVE.AREA, LIVE.JFCOMPANY,                          ");
        sql.append("       LIVE.BASECOMPANY, LIVE.BASEPERSONAL, LIVE.PAYMENT_COMPANY,                                 ");
        sql.append("       LIVE.PAYMENT_PERSONAL, LIVE.FZX, LIVE.DEPTNAME, LIVE.STAFFID,                              ");
        sql.append("       LIVE.PI_ID, LIVE.BI_COMPANY, LIVE.BI_PERSONAL, LIVE.IS_PAY_BACK,                           ");
        sql.append("       LIVE.PAY_BACK_YEAR, LIVE.PAY_BACK_MONTH, LIVE.IDCARD, 1 KIND                               ");
        sql.append("     FROM                                                                                         ");
        sql.append("       (SELECT MIN(PP.ID) ID,                                                                     ");
        sql.append("         MIN(JBI.JOB_NUMBER) JOBNUMBER,                                                           ");
        sql.append("         MIN(JBI.NAME) NAME,                                                                      ");
        sql.append("         MIN(PP.IS_DAIJIAO) ISDAIJIAO,                                                            ");
        sql.append("         MIN(PP.JF_YEAR) YEAR,                                                                    ");
        sql.append("         MIN(PP.JF_MONTH) MONTH,                                                                  ");
        sql.append("         MIN(PP.PJGZ) PJGZ,                                                                       ");
        sql.append("         WM_CONCAT(JIB.ID) FULIID,                                                                ");
        sql.append("         WM_CONCAT(JIB.INSUR_NAME) FULINAME,                                                      ");
        sql.append("         WM_CONCAT(PI.AREA) AREA,                                                                 ");
        sql.append("         WM_CONCAT(PI.JF_COMPANY) JFCOMPANY,                                                      ");
        sql.append("         WM_CONCAT(PI.BASE_COMPANY) BASECOMPANY,                                                  ");
        sql.append("         WM_CONCAT(PI.BASE_PERSONAL) BASEPERSONAL,                                                ");
        sql.append("         WM_CONCAT(PI.PAYMENT_COMPANY) PAYMENT_COMPANY,                                           ");
        sql.append("         WM_CONCAT(PI.PAYMENT_PERSONAL) PAYMENT_PERSONAL,                                         ");
        sql.append("         MIN(BRANCH.FZX) FZX,                                                                     ");
        sql.append("         MIN(QD.NAME) DEPTNAME,                                                                   ");
        sql.append("         MIN(JBI.ID) STAFFID,                                                                     ");
        sql.append("         MIN(PI.ID) PI_ID,                                                                        ");
        sql.append("         WM_CONCAT(SI.BI_COMPANY) BI_COMPANY,                                                     ");
        sql.append("         WM_CONCAT(SI.BI_PERSONAL) BI_PERSONAL,                                                   ");
        sql.append("         MIN(PP.IS_PAY_BACK) IS_PAY_BACK,                                                         ");
        sql.append("         MIN(PP.PAY_BACK_YEAR) PAY_BACK_YEAR,                                                     ");
        sql.append("         MIN(PP.PAY_BACK_MONTH) PAY_BACK_MONTH,                                                   ");
        sql.append("         MIN(JBI.ID_NUMBER) IDCARD                                                                ");
        sql.append("       FROM XC_PROCESS_PAYMENT PP                                                                 ");
        sql.append("       LEFT JOIN XC_PAYMENT_INSURANCE PI                                                          ");
        sql.append("       ON PP.ID = PI.SALARY_PAYMENT_PROCESS_ID                                                    ");
        sql.append("       LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                        ");
        sql.append("       ON PI.IBF_ID = JIB.ID                                                                      ");
        sql.append("       LEFT JOIN XC_SALARY_RECORD SR                                                              ");
        sql.append("       ON SR.STAFF_ID = PP.STAFF_ID                                                               ");
        sql.append("       LEFT JOIN JC_BASIC_INFORMATION JBI                                                         ");
        sql.append("       ON SR.STAFF_ID = JBI.ID                                                                    ");
        sql.append("       LEFT JOIN QX_DEPARTMENT QD                                                                 ");
        sql.append("       ON QD.ID = SR.DEPTNAME                                                                     ");
        sql.append("       LEFT JOIN BRANCH                                                                           ");
        sql.append("       ON SR.FILM_NAME = BRANCH.ID                                                                ");
        sql.append("       LEFT JOIN XC_SALARY_INSURANCE SI                                                           ");
        sql.append("       ON JIB.ID = SI.NAME                                                                        ");
        sql.append("       LEFT JOIN XC_SALARY_PAYMENT_PROCESS SPP                                                    ");
        sql.append("       ON PP.SALARY_PAYMENT_PROCESS_ID = SPP.ID                                                   ");
        sql.append("       WHERE PP.IS_DELETE = 0                                                                     ");
        sql.append("       AND SPP.IS_DELETE = 0                                                                      ");
        sql.append("       AND PI.IS_DELETE = 0                                                                       ");

        StringBuilder checkSql = new StringBuilder();
        Map<String, String> mapData = JsonUtil.parseProperties(formdata);
        if (mapData != null) {
            // 部门ID
            if (!StringUtils.isBlank(mapData.get("depId"))) {
                String[] deptArray = mapData.get("depId").split(",");
                sql.append("  AND SR.FILM_NAME IN ('"+StringUtils.join(deptArray, "','")+"') ");
                checkSql.append("  AND PCM.COMPANY_ID IN ('"+StringUtils.join(deptArray, "','")+"') ");
            }
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                      ");
            }
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                        ");
            }
            // 缴费年
            if (!StringUtils.isBlank(mapData.get("effectiveYear"))) {
                sql.append("   AND PP.JF_YEAR = "+mapData.get("effectiveYear").trim()+"                                        ");
                checkSql.append("   AND CMI.JF_YEAR = "+mapData.get("effectiveYear").trim()+"                                        ");
            }
            // 缴费月
            if (!StringUtils.isBlank(mapData.get("effectiveMonth"))) {
                sql.append("   AND PP.JF_MONTH = "+mapData.get("effectiveMonth").trim()+"                                        ");
                checkSql.append("   AND CMI.JF_MONTH = "+mapData.get("effectiveMonth").trim()+"                                        ");
            }
            // 福利项目
            if (!StringUtils.isBlank(mapData.get("insuranceId"))) {
                sql.append("   AND JIB.ID = '"+mapData.get("insuranceId").trim()+"'                                        ");
                checkSql.append("   AND PCM.INSURANCE_ID = '"+mapData.get("insuranceId").trim()+"'                                        ");
            }
        }
        sql.append("       GROUP BY JBI.ID,                                                                           ");
        sql.append("         PP.IS_PAY_BACK,                                                                          ");
        sql.append("         PP.CREATEDATE                                                                            ");
        sql.append("       ORDER BY MIN(PP.JF_YEAR) DESC,                                                             ");
        sql.append("         MIN(PP.JF_MONTH) DESC                                                                    ");
        sql.append("       ) LIVE                                                                                     ");
        sql.append("     UNION ALL                                                                                    ");
        sql.append("     SELECT CMI.ID,                                                                               ");
        sql.append("       JBI.JOB_NUMBER JOBNUMBER, JBI.NAME, 0 ISDAIJIAO, CMI.JF_YEAR YEAR, CMI.JF_MONTH MONTH,    ");
        sql.append("       '' PJGZ, PCM.INSURANCE_ID FULIID, JIB.INSUR_NAME FULINAME, '' AREA, CMI.COMPANY JFCOMPANY, ");
        sql.append("       '' BASECOMPANY, CMI.JFJS BASEPERSONAL, CMI.PAYMENT_COMPANY, CMI.PAYMENT_PERSONAL,          ");
        sql.append("       '' FZX, '' DEPTNAME, CMI.STAFF_ID STAFFID, '' PI_ID, '' BI_COMPANY,                        ");
        sql.append("       '' BI_PERSONAL, CMI.JF_TYPE IS_PAY_BACK, CMI.PAY_BACK_YEAR, CMI.PAY_BACK_MONTH,                     ");
        sql.append("       CMI.ID_CARD IDCARD, 2 KIND                                                                 ");
        sql.append("     FROM XC_PAYMENT_CHECK_MONTH PCM                                                              ");
        sql.append("     LEFT JOIN XC_CHECK_MONTH_INFO CMI                                                            ");
        sql.append("     ON PCM.ID  = CMI.PAYMENT_CHECK_MONTH_ID                                                      ");
        sql.append("     AND CMI.IS_DELETE = 0                                                                        ");
        sql.append("     LEFT JOIN JC_INSURANCE_BENEFITS JIB                                                          ");
        sql.append("     ON JIB.ID = PCM.INSURANCE_ID                                                                 ");
        sql.append("     LEFT JOIN JC_BASIC_INFORMATION JBI                                                           ");
        sql.append("     ON CMI.STAFF_ID = JBI.ID                                                                     ");
        sql.append("     WHERE PCM.IS_DELETE = 0                                                                      ");
        sql.append(checkSql);
        sql.append("     ) PHYSICALDATA                                                                               ");
        sql.append("   ORDER BY PHYSICALDATA.STAFFID ASC                                                              ");

        return sql.toString();
    }

    @Override
    public Map<String, Object> getPaymentInsuranceListData(String depId, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                           ");
        sql.append("     SI.ID, B.FZX,IB.INSUR_NAME,JA_CITY.NAME,                      ");
        sql.append("     SI.HIGH,SI.LOW,SI.BI_COMPANY, SI.BI_PERSONAL,SI.YEAR, SI.MONTH,SI.STAFF_TYPE ");
        sql.append("   FROM XC_SALARY_INSURANCE SI                                      ");
        sql.append("   LEFT JOIN JC_INSURANCE_BENEFITS IB                               ");
        sql.append("   ON SI.NAME = IB.ID                                               ");
        sql.append("   LEFT JOIN BRANCH B                                               ");
        sql.append("   ON SI.COMPANY_ID = B.ID                                          ");
        sql.append("   LEFT JOIN JC_PAY_AREAS JPA                                            ");
        sql.append("   ON SI.AREA = JPA.ID                                               ");
        sql.append("   LEFT JOIN JC_AREAS JA                                            ");
        sql.append("   ON JPA.AREA_PROVINCE = JA.ID                                               ");
        sql.append("   LEFT JOIN JC_AREAS JA_CITY                                            ");
        sql.append("   ON JPA.AREA_CITY = JA_CITY.ID                                               ");
        sql.append("   WHERE SI.IS_DELETE = 0                                           ");
        sql.append("    AND SI.COMPANY_ID IN ('"+StringUtils.join(depId.split(","), "','")+"')   ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 福利项目
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND IB.INSUR_NAME LIKE '%"+mapData.get("name").trim()+"%' ");
            }
            // 缴费地域
            if (!StringUtils.isBlank(mapData.get("area"))) {
                sql.append("   AND JA_CITY.NAME LIKE '%"+mapData.get("area").trim()+"%' ");
            }
        }
        sql.append("   ORDER BY SI.CREATEDATE ASC                                       ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("companyName", obj[1]);
            map.put("name", obj[2]);
            map.put("area", obj[3]);
            map.put("high", obj[4]);
            map.put("low", obj[5]);
            map.put("biCompany", obj[6]);
            map.put("biPersonal", obj[7]);
            map.put("year", obj[8]);
            map.put("month", obj[9]);
            map.put("staffType", obj[10]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

}
