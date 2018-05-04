package com.lingnet.hcm.dao.impl.salary;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryItemsDao;
import com.lingnet.hcm.entity.salary.SalaryItems;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;

/**
 * 薪资项目
 * @ClassName: SalaryItemsDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
@Repository("salaryItemsDao")
public class SalaryItemsDaoImpl extends BaseDaoImplInit<SalaryItems, String> implements SalaryItemsDao {

    @Override
    public String getItemsTypeListData(String depId) {
        StringBuilder builder = new StringBuilder();
        builder.append("   SELECT                                   ");
        builder.append("   TYPES.ID, TYPES.DEP_ID, TYPES.NAME, TYPES.NOTE, TYPES.PID,     ");
        builder.append("   BRANCH.FZX     ");
        builder.append("   FROM XC_SALRY_ITEM_TYPE TYPES            ");
        builder.append("   LEFT JOIN XC_SALARY_ITEM_AND_TYPE SIAT   ");
        builder.append("   ON TYPES.ID = SIAT.TYPE_ID               ");
        builder.append("   LEFT JOIN BRANCH                         ");
        builder.append("   ON TYPES.DEP_ID = BRANCH.ID              ");
        builder.append("   WHERE SIAT.IS_DELETE = 0                 ");

        // 公司
        if (!StringUtils.isBlank(depId)) {
            builder.append("   AND TYPES.DEP_ID = '"+depId+"'                 ");
        }
        builder.append("   ORDER BY TYPES.CREATEDATE ASC            ");

        List<?> list = this.findBySql(builder.toString());
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("depId", obj[1]);
            map.put("name", obj[5]+"_"+obj[2]);
            map.put("note", obj[3]);
            map.put("pid", obj[4]);
            map.put("fzx", obj[5]);
            dataList.add(map);
        }

        return JsonUtil.Encode(dataList);
    }

    @Override
    public List<HashMap<String, Object>> getSalaryItemAndTypeListData(String id) {
        StringBuilder builder = new StringBuilder();
        builder.append("   SELECT                                   ");
        builder.append("   SIAT.ID, ITEMS.DEP_ID, TYPES.NAME,SIAT.TYPE_ID     ");
        builder.append("   FROM XC_SALARY_ITEMS ITEMS            ");
        builder.append("   LEFT JOIN XC_SALARY_ITEM_AND_TYPE SIAT   ");
        builder.append("   ON ITEMS.ID = SIAT.ITEM_ID               ");
        builder.append("   LEFT JOIN XC_SALRY_ITEM_TYPE TYPES            ");
        builder.append("   ON TYPES.ID = SIAT.TYPE_ID               ");
        builder.append("   AND TYPES.IS_DELETE = 0               ");
        builder.append("   WHERE SIAT.IS_DELETE = 0                 ");

        if (!StringUtils.isBlank(id)) {
            builder.append("   AND ITEMS.ID = '"+id+"'                 ");
        }
        builder.append("   ORDER BY SIAT.CREATEDATE ASC            ");

        List<?> list = this.findBySql(builder.toString());
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("typeId", obj[3]);
            map.put("name", obj[2]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public Map<String, Object> getSalaryItemListData(String depId, String id,
            String searchData, Pager pager) {
        StringBuilder builder = new StringBuilder();
        builder.append("   SELECT                                       ");
        builder.append("   ITEMS.ID,ITEMS.NAME,ITEMS.NUMBER_ACCURACY,ITEMS.TYPE  ");
        builder.append("   FROM XC_SALRY_ITEM_TYPE TYPES                ");
        builder.append("   LEFT JOIN XC_SALARY_ITEM_AND_TYPE SIAT       ");
        builder.append("    ON TYPES.ID = SIAT.TYPE_ID                   ");
        builder.append("   LEFT JOIN XC_SALARY_ITEMS ITEMS              ");
        builder.append("    ON ITEMS.ID = SIAT.ITEM_ID                   ");
        builder.append("   WHERE SIAT.IS_DELETE = 0                     ");
        builder.append("    AND TYPES.IS_DELETE = 0                      ");
        builder.append("    AND ITEMS.IS_DELETE = 0                      ");
        builder.append("    AND TYPES.ID = '"+id+"'                 ");
        builder.append("   ORDER BY SIAT.CREATEDATE ASC            ");

        pager = findPagerBySql(pager, builder.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("numberAccuracy", obj[2]);
            map.put("type", obj[3]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public List<Map<String, Object>> getPersonalToAllocationListData(String ids, String depId, String recordId) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT             ");
        sql.append("       JBI.ID, MIN(JBI.JOB_NUMBER) JOBNUMBER, MIN(JBI.NAME) NAME, MIN(NVL(b2.FZX,BRANCH.FZX)) COMPANYNAME, MIN(NVL(QD2.NAME,QD.NAME)) DEPTNAME,   ");
        sql.append("       MIN(NVL(BZGW.POSITION_NAME,JBI.POST)) POST, WM_CONCAT(SG.NAME) SALARYGROUP, MIN(JBI.ON_JOB) ONJOB, MIN(JBI.ON_POST) ONPOST,    ");
        sql.append("       MIN(NVL(SR.FILM_NAME, JBI.FILM_ID)) company_Id, MIN(NVL(SR.DEPTNAME, JBI.DEPART_ID)) dept_Id, SR.ID recordMainId               ");
        sql.append("  FROM                                              ");
        sql.append("    ( SELECT                                                                                                                   ");
        sql.append("        JBI.ID,JBI.DEPART_ID,JBI.FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JBI.POST,JBI.ON_JOB,JBI.ON_POST,JBI.PHONE,JBI.AGE,JBI.SEX,JBI.NAME  ");
        sql.append("        FROM JC_BASIC_INFORMATION JBI                                                                                          ");
        sql.append("        UNION ALL                                                                                                              ");
        sql.append("        SELECT                                                                                                                 ");
        sql.append("        JBI.ID,JPJ.DEP_ID DEPART_ID,JPJ.FIRM_ID FILM_ID,JBI.JOB_NUMBER,JBI.IS_DELETE,JPJ.SPE_POST POST,                        ");
        sql.append("        JBI.ON_JOB,JBI.ON_POST,JBI.PHONE,JBI.AGE,JBI.SEX,JBI.NAME                                                        ");
        sql.append("        FROM JC_BASIC_INFORMATION JBI                                                                                          ");
        sql.append("        LEFT JOIN JC_PT_JOB JPJ                                                                                                ");
        sql.append("        ON JBI.ID = JPJ.PERSON_ID                                                                                              ");
        sql.append("        WHERE JPJ.IS_HOST_POST = '0'                                                                                           ");
        sql.append("        AND JPJ.IS_DELETE = 0                                                                                                  ");
        sql.append("        AND JPJ.STATE = 1                                                                                                      ");
        sql.append("      ) JBI                                                                                                                    ");// 兼职
        sql.append("  LEFT JOIN XC_SALARY_RECORD SR                   ");
        sql.append("  ON SR.STAFF_ID = JBI.ID                 ");
//        sql.append("  AND SR.DEPTNAME = JBI.DEPART_ID                 ");
        sql.append("  LEFT JOIN XC_SALARY_PERSONAL SP                                           ");
        sql.append("  ON JBI.ID = SP.SALARY_RECORD_ID                                           ");
        sql.append("  AND SR.ID = SP.RECORD_MAIN_ID                                               ");
        sql.append("  AND SP.IS_DELETE = 0                                           ");
        sql.append("  AND CASE WHEN SP.IS_OFF = 1                                                                                ");
        sql.append("            THEN CASE WHEN TO_CHAR(SP.OFF_DATE, 'yyyy-MM-dd') <= TO_CHAR(SYSDATE, 'yyyy-MM-dd') THEN 1       ");
        sql.append("                ELSE 0 END                                                                                ");
        sql.append("     ELSE 0 END = 0                                                                                ");
        sql.append("  LEFT JOIN XC_SALARY_GROUP SG                                           ");
        sql.append("  ON SP.SALARY_GROUP_ID = SG.ID                                           ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD                                                ");
        sql.append("  ON QD.ID = JBI.DEPART_ID                                                  ");
        sql.append("  LEFT JOIN QX_DEPARTMENT QD2                                               ");
        sql.append("  ON QD2.ID = SR.DEPTNAME                                                   ");
        sql.append("  LEFT JOIN BRANCH                                                          ");
        sql.append("  ON JBI.FILM_ID = BRANCH.ID                                                ");
        sql.append("  LEFT JOIN BRANCH b2                                                       ");
        sql.append("  ON SR.FILM_NAME = b2.ID                                                   ");
        sql.append("  LEFT JOIN POST_POSITION BZGW                                           ");
        sql.append("  ON SR.POST = BZGW.ID                                           ");

        String[] idsArray = ids.split(",");
        sql.append("  WHERE JBI.ID IN ('"+StringUtils.join(idsArray, "','")+"')                  ");
        if (!recordId.equals("-1"))
            sql.append("  AND SR.ID IN ('"+StringUtils.join(recordId.split(","), "','")+"')                  ");
        sql.append("  AND JBI.IS_DELETE = 0                  ");
        sql.append("  GROUP BY JBI.ID, SR.ID                  ");

        List<?> list = this.findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("companyName", obj[3]);
            map.put("deptName", obj[4]);
            map.put("post", obj[5]);
            map.put("salaryGroup", obj[6]);
            map.put("onJob", obj[7]);
            map.put("onPost", obj[8]);
            map.put("companyId", obj[9]);
            map.put("deptId", obj[10]);
            map.put("recordMainId", obj[11]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public String getPersonalGroupListData(String ids, String recordId) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                 ");
        sql.append("    SP.ID, SG.NAME,SP.OFF_DATE,SP.IS_OFF,SP.START_DATE,SP.SALARY_COM_ID,             ");
        sql.append("    SP.COST_COM_ID,SP.COST_DEPT_ID,SP.COST_SCALE,SP.IS_SALARY,SP.IS_PEI,SP.SALARY_GROUP_ID             ");
        sql.append("    FROM XC_SALARY_PERSONAL SP             ");
        sql.append("    LEFT JOIN XC_SALARY_GROUP SG           ");
        sql.append("    ON SP.SALARY_GROUP_ID = SG.ID          ");

        String[] idsArray = ids.split(",");
        sql.append("  WHERE SP.SALARY_RECORD_ID IN ('"+StringUtils.join(idsArray, "','")+"')                  ");
        if (!recordId.equals("-1"))
            sql.append("  AND SP.RECORD_MAIN_ID IN ('"+StringUtils.join(recordId.split(","), "','")+"')                  ");
        sql.append("    AND SP.IS_DELETE = 0                 ");
        sql.append("    ORDER BY SP.CREATEDATE DESC            ");

        List<?> list = this.findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
//            map.put("name", obj[1]);
            map.put("isOff", obj[3] == null? 0:obj[3]);
            map.put("offDate", obj[2]);
            map.put("startDate", obj[4]);
            map.put("salaryComId", obj[5]);
            map.put("groupName", obj[1]);
            map.put("salaryGroupId", obj[11]);
            map.put("costComId", obj[6]);
            map.put("costDeptId", obj[7]);
            map.put("costScale", obj[8]);
            map.put("isSalary", obj[9]);
            map.put("isPei", obj[10]==null? 0 : obj[10]);
            dataList.add(map);
        }

        return JsonUtil.Encode(dataList);
    }

    @Override
    public List<Map<String, Object>> getGuDingItems(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                          ");
        sql.append("      ITEMS.ID, MIN(ITEMS.NAME) ITEMNAME, MIN(SP.SALARY_GROUP_ID) groupId,            ");
        sql.append("      Min(ITEMS.NUMBER_ACCURACY) xs            ");
        sql.append("    FROM XC_SALARY_ITEMS ITEMS                      ");
        sql.append("    LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAT           ");
        sql.append("    ON ITEMS.ID = GWAT.ITEM_ID                      ");
        sql.append("    AND ITEMS.IS_DELETE = 0                         ");
        sql.append("    LEFT JOIN XC_SALARY_GROUP_WAGE SGW              ");
        sql.append("    ON GWAT.WAGE_ID  = SGW.ID                       ");
        sql.append("    AND SGW.IS_DELETE=0                             ");
        sql.append("    LEFT JOIN XC_SALARY_PERSONAL SP                 ");
        sql.append("    ON SGW.SALARY_GROUP_ID = SP.SALARY_GROUP_ID     ");
        sql.append("    AND SP.IS_DELETE =0                             ");
        sql.append("    AND SP.IS_OFF =0                                ");
        sql.append("    LEFT JOIN JC_BASIC_INFORMATION JBI              ");
        sql.append("    ON SP.SALARY_RECORD_ID = JBI.ID                 ");
        sql.append("    WHERE JBI.ID = '"+id+"'                         ");
        sql.append("      AND ITEMS.FIXED = 1                         ");
        sql.append("    GROUP BY ITEMS.ID                               ");

        List<?> list = this.findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("groupId", obj[2]);
            map.put("xs", obj[3]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public List<Map<String, Object>> getSalaryGroupItems(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                          ");
        sql.append("      ITEMS.ID, MIN(ITEMS.NAME) ITEMNAME, MIN(SG.ID) groupId, MIN(SG.NAME) groupName,            ");
        sql.append("      Min(ITEMS.NUMBER_ACCURACY) xs            ");
        sql.append("    FROM XC_SALARY_ITEMS ITEMS                      ");
        sql.append("    LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAT           ");
        sql.append("    ON ITEMS.ID = GWAT.ITEM_ID                      ");
        sql.append("    AND ITEMS.IS_DELETE = 0                         ");
        sql.append("    LEFT JOIN XC_SALARY_GROUP_WAGE SGW              ");
        sql.append("    ON GWAT.WAGE_ID  = SGW.ID                       ");
        sql.append("    AND SGW.IS_DELETE=0                             ");
        sql.append("    LEFT JOIN XC_SALARY_GROUP SG                 ");
        sql.append("    ON SGW.SALARY_GROUP_ID = SG.ID     ");
        sql.append("    AND SG.IS_DELETE =0                             ");
        sql.append("    WHERE SG.ID = '"+id+"'                         ");
        sql.append("      AND ITEMS.FIXED = 1                         ");
        sql.append("    GROUP BY ITEMS.ID                               ");

        List<?> list = this.findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        DecimalFormat df = new DecimalFormat();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("groupId", obj[2]);
            map.put("groupName", obj[3]);
            if (Integer.valueOf(obj[4].toString()) == 0) {
                df.applyPattern("0");
            } else {
                df.applyPattern("0."+String.format("%0"+obj[4]+"d%n", 0));
            }
            map.put("xs", df.format(0));
            dataList.add(map);
        }
        
        return dataList;
    }

    @Override
    public Map<String, Object> getSalaryItemGKListData(String depId, String searchData, Pager pager) {
        pager = findPagerBySql(pager, getSalaryItemGKListDataSql(depId, searchData));
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("depId", obj[15]);
            map.put("name", obj[1]);
            map.put("numberAccuracy", obj[2]);
            map.put("addOrLess", obj[3]);
            map.put("sx", obj[4]);
            map.put("fixed", obj[5]);
            map.put("yd", obj[6]);
            map.put("bzl", obj[7]);
            map.put("njl", obj[8]);
            map.put("tcl", obj[9]);
            map.put("itemType", obj[10]);
            map.put("itemPro", obj[11]);
            map.put("bj", obj[12]);
            map.put("isDisplay", obj[13]);
            map.put("type", obj[16]);
            map.put("companyName", obj[18]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public String getSalaryItemGKListDataSql(String depId, String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT * FROM                                                                                                                            ");
        sql.append("    (SELECT                                                                                                                            ");
        sql.append("    ALLDATA.ID,MIN(ALLDATA.NAME) NAME,MIN(ALLDATA.NUMBER_ACCURACY) NUMBER_ACCURACY,MIN(ALLDATA.ADD_OR_LESS) ADD_OR_LESS,              ");
        sql.append("    MIN(ALLDATA.SX) SX,MIN(ALLDATA.FIXED) FIXED,MIN(ALLDATA.YD) YD,MIN(ALLDATA.BZL) BZL, MIN(ALLDATA.NJL) NJL,MIN(ALLDATA.TCL) TCL,   ");
        sql.append("    MIN(ALLDATA.ITEM_TYPE) ITEM_TYPE, MIN(ALLDATA.ITEM_PRO) ITEM_PRO, MIN(ALLDATA.BJ) BJ,MIN(ALLDATA.IS_DISPLAY) IS_DISPLAY,          ");
        sql.append("    MIN(ALLDATA.SITNAME) SITNAME,MIN(ALLDATA.DEP_ID) DEP_ID,MIN(ALLDATA.TYPE) TYPE,MIN(ALLDATA.IS_DELETE) IS_DELETE,                  ");
        sql.append("    MIN(ALLDATA.companyName) companyName                  ");
        sql.append("    FROM (                                                                                                                            ");
        sql.append("      SELECT                                                                                                                          ");
        sql.append("        SI.ID,SI.NAME,SI.NUMBER_ACCURACY,SI.ADD_OR_LESS,                                                                              ");
        sql.append("        SI.SX,SI.FIXED,SI.YD,SI.BZL, SI.NJL,SI.TCL,SI.ITEM_TYPE,SI.ITEM_PRO,                                                          ");
        sql.append("        SI.BJ,SI.IS_DISPLAY,SIT.NAME SITNAME,SI.DEP_ID,SI.TYPE,SI.IS_DELETE, B.FZX companyName                                       ");
        sql.append("      FROM XC_SALARY_ITEMS SI                                                                                                         ");
        sql.append("      LEFT JOIN XC_SALARY_ITEM_AND_TYPE SIAT                                                                                          ");
        sql.append("      ON SI.ID = SIAT.ITEM_ID                                                                                                         ");
        sql.append("      AND SIAT.IS_DELETE =0                                                                                                           ");
        sql.append("      LEFT JOIN XC_SALRY_ITEM_TYPE SIT                                                                                                ");
        sql.append("      ON SIT.ID = SIAT.TYPE_ID                                                                                                        ");
        sql.append("      LEFT JOIN BRANCH B                                                                                                ");
        sql.append("      ON B.ID = SI.DEP_ID                                                                                                        ");
        sql.append("      WHERE SI.IS_DELETE =0                                                                                                           ");
        sql.append("      AND SIT.IS_DELETE =0                                                                                                            ");

        String[] src = depId.split(",");
        int len = src.length - 1;
        // 新数组
        String[] newArry = new String[len];
        System.arraycopy(src, 0, newArry, 0, len);
        sql.append("      AND SI.DEP_ID = '"+src[len]+"'                                                              ");
        sql.append("      UNION ALL                                                                                                                       ");
        sql.append("      SELECT                                                                                                                          ");
        sql.append("        SI.ID,SI.NAME,SI.NUMBER_ACCURACY,SI.ADD_OR_LESS,                                                                              ");
        sql.append("        SI.SX,SI.FIXED,SI.YD,SI.BZL, SI.NJL,SI.TCL,SI.ITEM_TYPE,SI.ITEM_PRO,                                                          ");
        sql.append("        SI.BJ,SI.IS_DISPLAY,SIT.NAME SITNAME,SI.DEP_ID,SI.TYPE,SI.IS_DELETE, B.FZX companyName                                        ");
        sql.append("      FROM XC_SALARY_ITEMS SI                                                                                                         ");
        sql.append("      LEFT JOIN XC_SALARY_ITEM_AND_TYPE SIAT                                                                                          ");
        sql.append("      ON SI.ID = SIAT.ITEM_ID                                                                                                         ");
        sql.append("      AND SIAT.IS_DELETE =0                                                                                                           ");
        sql.append("      LEFT JOIN XC_SALRY_ITEM_TYPE SIT                                                                                                ");
        sql.append("      ON SIT.ID = SIAT.TYPE_ID                                                                                                        ");
        sql.append("      LEFT JOIN BRANCH B                                                                                                ");
        sql.append("      ON B.ID = SI.DEP_ID                                                                                                        ");
        sql.append("      WHERE SI.IS_DELETE =0                                                                                                           ");
        sql.append("      AND SIT.IS_DELETE =0                                                                                                            ");
        sql.append("      AND SIT.IS_CONTROL = 1                                                                                                  ");
        sql.append("      AND SI.DEP_ID IN ('"+StringUtils.join(newArry, "','")+"')                                                              ");
        sql.append("    ) ALLDATA                                                                                                                         ");
        sql.append("    WHERE ALLDATA.IS_DELETE = 0                                                                                                      ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 薪酬项目
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("    AND ALLDATA.NAME LIKE '%"+mapData.get("name").trim()+"%'                                                                ");
            }
            // 公司名称
            if (!StringUtils.isBlank(mapData.get("companyName"))) {
                sql.append("    AND ALLDATA.COMPANYNAME LIKE '%"+mapData.get("companyName").trim()+"%'                  ");
            }
            // 项目类型
            if (!StringUtils.isBlank(mapData.get("itemType"))) {
                sql.append("    AND ALLDATA.ITEM_TYPE = "+mapData.get("itemType").trim()+"                  ");
            }
            // 有效
            if (!StringUtils.isBlank(mapData.get("type"))) {
                sql.append("    AND ALLDATA.TYPE = "+mapData.get("type")+"                  ");
            }
            // 已选择的薪资项目
            if (!StringUtils.isBlank(mapData.get("items"))) {
                String[] items = mapData.get("items").split(",");
                sql.append("    AND ALLDATA.ID NOT IN ('"+StringUtils.join(items, "','")+"')                  ");
            }
        }
        sql.append("    GROUP BY ALLDATA.ID                                                                                                               ");
        sql.append("    ORDER BY MIN(ALLDATA.SX) ASC) BIGDATA                                                                                              ");

        return sql.toString();
    }

    @Override
    public List<Map<String, Object>> getSalaryItemToItemTypeData(String companyId) {
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT                                               ");
        sql.append("       SIT.ID PID, SIT.NAME PNAME, SI.ID, SI.NAME,        ");
        sql.append("      CASE WHEN SIT.IS_CONTROL = 1 THEN '集团统一管控_' || JQE.DIC_NAME || '_' || SI.NAME  ");
        sql.append("        ELSE JQE.DIC_NAME || '_' || SI.NAME END ctr                                  ");
        sql.append("     FROM XC_SALRY_ITEM_TYPE SIT                          ");
        sql.append("     LEFT JOIN XC_SALARY_ITEM_AND_TYPE SIAT               ");
        sql.append("     ON SIT.ID = SIAT.TYPE_ID                             ");
        sql.append("     LEFT JOIN XC_SALARY_ITEMS SI                         ");
        sql.append("     ON SIAT.ITEM_ID = SI.ID                              ");
        sql.append("  LEFT JOIN JC_QX_DECTIONARY JQE                          ");
        sql.append("    ON JQE.DIC_VALUE = TO_CHAR(SI.ITEM_TYPE)           ");
        sql.append("   AND JQE.PID = '402881945cc8c4c8015cc926f00d0008'       ");
        sql.append("     WHERE SIT.IS_DELETE = 0                              ");
        sql.append("     AND SI.IS_DELETE = 0                                 ");
        sql.append("     AND SI.DEP_ID IN ('"+StringUtils.join(companyId.split(","), "','")+"')       ");
        sql.append("     ORDER BY SIT.ID ASC                                  ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Object n = "";
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            if (!n.equals(obj[0])) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", obj[0]);
                map.put("name", obj[1]);
                dataList.add(map);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[2]);
            map.put("name", obj[4]);
            map.put("otherName", "S_" + obj[3]);
            map.put("pid", obj[0]);
            dataList.add(map);
            n = obj[0];
        }

        return dataList;
    }

    @Override
    public String getGroupToItems(String salaryGroupId) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                          ");
        sql.append("      ITEMS.ID, MIN(ITEMS.NAME) ITEMNAME, MIN(SG.ID) groupId, MIN(SG.NAME) groupName,            ");
        sql.append("      Min(ITEMS.NUMBER_ACCURACY) xs            ");
        sql.append("    FROM XC_SALARY_ITEMS ITEMS                      ");
        sql.append("    LEFT JOIN XC_GROUP_WAGE_AND_TYPE GWAT           ");
        sql.append("    ON ITEMS.ID = GWAT.ITEM_ID                      ");
        sql.append("    AND ITEMS.IS_DELETE = 0                         ");
        sql.append("    LEFT JOIN XC_SALARY_GROUP_WAGE SGW              ");
        sql.append("    ON GWAT.WAGE_ID  = SGW.ID                       ");
        sql.append("    AND SGW.IS_DELETE=0                             ");
        sql.append("    LEFT JOIN XC_SALARY_GROUP SG                 ");
        sql.append("    ON SGW.SALARY_GROUP_ID = SG.ID     ");
        sql.append("    AND SG.IS_DELETE =0                             ");
        sql.append("    WHERE SG.ID = '"+salaryGroupId+"'               ");
        sql.append("    GROUP BY ITEMS.ID                               ");

        return sql.toString();
    }

    @Override
    public List<Map<String, Object>> getSalaryItemToTypeData(String depId, Object pid, String column) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                          ");
        sql.append("      SI.ID,SI.NAME,SIT.IS_CONTROL,                                 ");
        sql.append("      CASE WHEN SIT.IS_CONTROL = 1 THEN '集团统一管控_' || SI.NAME  ");
        sql.append("        ELSE SI.NAME END ctr                                  ");
        sql.append("    FROM XC_SALARY_ITEMS SI                                         ");
        sql.append("    LEFT JOIN XC_SALARY_ITEM_AND_TYPE SIAT                          ");
        sql.append("    ON SI.ID = SIAT.ITEM_ID                                         ");
        sql.append("    LEFT JOIN XC_SALRY_ITEM_TYPE SIT                                ");
        sql.append("    ON SIT.ID = SIAT.TYPE_ID                                        ");
        sql.append("    WHERE SI.IS_DELETE = 0                                          ");
        sql.append("    AND SIAT.IS_DELETE = 0                                         ");
        sql.append("    AND SI."+column+" = 1                                           ");
        sql.append("    AND SI.DEP_ID IN ('"+StringUtils.join(depId.split(","), "','")+"')       ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[3]);
            map.put("otherName", "S_" + obj[1]);
            map.put("description", "");
            map.put("pid", pid);
            dataList.add(map);
        }

        return dataList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getListData(String userName, String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                              ");
        sql.append("   SWM.ID, MIN(SWM.STAFF_ID) STAFF_ID,MIN(SWM.STAFF_NAME) STAFF_NAME,  ");
        sql.append("   MIN(SWM.COMPANY_ID) COMPANY_ID,MIN(SWM.COMPANY_NAME) COMPANY_NAME,  ");
        sql.append("   MIN(SWM.DEPT_ID) DEPT_ID,MIN(SWM.DEPT_NAME) DEPT_NAME,              ");
        sql.append("   LISTAGG(SWS.IBF_ID, ',') WITHIN GROUP (ORDER BY SWS.SX ASC,SWS.IBF_ID ASC) IBF_ID,  ");
        sql.append("   LISTAGG(SWS.ITEMS_NAME, ',') WITHIN GROUP (ORDER BY SWS.SX ASC,SWS.IBF_ID ASC) ITEMS_NAME,  ");
        sql.append("   LISTAGG(SWS.SALARY_VALUE, ',') WITHIN GROUP (ORDER BY SWS.SX ASC,SWS.IBF_ID ASC) SALARY_VALUE,  ");
        sql.append("   MIN(JBI.JOB_NUMBER) JOB_NUMBER,MIN(JBI.EMP_TYPE) EMP_TYPE,MIN(PDATA.NAME) periodName, ");
        sql.append("   MIN(B.FZX) laowu  ");
        sql.append("   FROM XC_SALARY_WAGE_MAIN SWM                                        ");
        sql.append("   LEFT JOIN XC_SALARY_WAGE_SECOND SWS                                 ");
        sql.append("   ON SWM.ID = SWS.SALARY_WAGE_MAIN_ID                                 ");
        sql.append("   AND SWS.IS_DELETE = 0                                               ");
        sql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                                  ");
        sql.append("   ON SWM.SALARY_ASSIGNATION_ID = SA.ID                                ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                  ");
        sql.append("   ON JBI.ID = SWM.STAFF_ID                                            ");
        sql.append("   LEFT JOIN XC_PERIODDATA PDATA                                       ");
        sql.append("   ON SA.SALARY_PERIOD = PDATA.ID                                      ");
        sql.append("   LEFT JOIN BRANCH B                                       ");
        sql.append("   ON JBI.LABOR_COMPANY = B.ID                                      ");
        sql.append("   WHERE SWM.IS_DELETE = 0                                             ");
        sql.append("   AND JBI.JOB_NUMBER = '"+userName+"'                                 ");
        sql.append("   AND SWM.IS_RELEASE = 1                                              ");
        sql.append("   AND SA.IS_FAFANG = 3                                              ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'    ");
            }
            // 姓名
            if (!StringUtils.isBlank(mapData.get("staffName"))) {
                sql.append("   AND SWM.STAFF_NAME LIKE '%"+mapData.get("staffName").trim()+"%'    ");
            }
            // 公司
            if (!StringUtils.isBlank(mapData.get("companyName"))) {
                sql.append("   AND SWM.COMPANY_NAME LIKE '%"+mapData.get("companyName").trim()+"%'    ");
            }
            // 部门
            if (!StringUtils.isBlank(mapData.get("deptName"))) {
                sql.append("   AND SWM.DEPT_NAME LIKE '%"+mapData.get("deptName").trim()+"%'    ");
            }
            // 员工类型
            if (!StringUtils.isBlank(mapData.get("empType"))) {
                sql.append("   AND JBI.EMP_TYPE = "+mapData.get("empType").trim()+"    ");
            }
            // 劳务公司
            if (!StringUtils.isBlank(mapData.get("laowu"))) {
                sql.append("   AND B.FZX LIKE '%"+mapData.get("laowu").trim()+"%'    ");
            }
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("salaryPeriod"))) {
                String[] salaryPeriod = mapData.get("salaryPeriod").split(",");
                sql.append("   AND SA.SALARY_PERIOD IN ('"+StringUtils.join(salaryPeriod, "','")+"')            ");
            }
        }
        sql.append("   GROUP BY SWM.ID                                                     ");
        sql.append("   ORDER BY MIN(SWM.CREATEDATE) ASC                                    ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("staffName", obj[2]);
            map.put("companyId", obj[3]);
            map.put("companyName", obj[4]);
            map.put("deptId", obj[5]);
            map.put("deptName", obj[6]);
            map.put("itemId", obj[7]);
            map.put("itemName", obj[8]);
            map.put("salaryValue", obj[9]);
            map.put("jobNumber", obj[10]);
            map.put("empType", obj[11]);
            map.put("periodName", obj[12]);
            map.put("laowu", obj[13]);
            dataList.add(map);
        }

        StringBuilder itemsSql = new StringBuilder();
        itemsSql.append("   SELECT                                        ");
        itemsSql.append("     SWS.IBF_ID, MIN(SWS.ITEMS_NAME) ITEMS_NAME, ");
        itemsSql.append("     COUNT(DISTINCT SWS.IBF_ID)                  ");
        itemsSql.append("   FROM XC_SALARY_WAGE_MAIN SWM                  ");
        itemsSql.append("   LEFT JOIN XC_SALARY_WAGE_SECOND SWS           ");
        itemsSql.append("   ON SWM.ID = SWS.SALARY_WAGE_MAIN_ID           ");
        itemsSql.append("   AND SWS.IS_DELETE = 0                         ");
        itemsSql.append("   LEFT JOIN XC_SALARY_RECORD SR                 ");
        itemsSql.append("   ON SWM.STAFF_ID = SR.STAFF_ID                 ");
        itemsSql.append("   WHERE SWM.IS_DELETE = 0                       ");
        itemsSql.append("   AND SWM.IS_RELEASE = 1                        ");
        itemsSql.append("   GROUP BY SWS.IBF_ID                           ");
        itemsSql.append("   ORDER BY MIN(SWS.SX) ASC,SWS.IBF_ID ASC                       ");
        SQLQuery summaryQuery = getSession().createSQLQuery(itemsSql.toString());
        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> summaryList = summaryQuery.list();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("itmeTotal", summaryList);

        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getDeptListData(String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                              ");
        sql.append("   SWM.ID, MIN(SWM.STAFF_ID) STAFF_ID,MIN(SWM.STAFF_NAME) STAFF_NAME,  ");
        sql.append("   MIN(SWM.COMPANY_ID) COMPANY_ID,MIN(SWM.COMPANY_NAME) COMPANY_NAME,  ");
        sql.append("   MIN(SWM.DEPT_ID) DEPT_ID,MIN(SWM.DEPT_NAME) DEPT_NAME,              ");
        sql.append("   LISTAGG(SWS.IBF_ID, ',') WITHIN GROUP (ORDER BY SWS.SX ASC,SWS.IBF_ID ASC) IBF_ID,  ");
        sql.append("   LISTAGG(SWS.ITEMS_NAME, ',') WITHIN GROUP (ORDER BY SWS.SX ASC,SWS.IBF_ID ASC) ITEMS_NAME,  ");
        sql.append("   LISTAGG(SWS.SALARY_VALUE, ',') WITHIN GROUP (ORDER BY SWS.SX ASC,SWS.IBF_ID ASC) SALARY_VALUE,  ");
        sql.append("   MIN(JBI.JOB_NUMBER) JOB_NUMBER,MIN(JBI.EMP_TYPE) EMP_TYPE,MIN(PDATA.NAME) periodName， ");
        sql.append("   MIN(B.FZX) laowu  ");
        sql.append("   FROM XC_SALARY_WAGE_MAIN SWM                                        ");
        sql.append("   LEFT JOIN XC_SALARY_WAGE_SECOND SWS                                 ");
        sql.append("   ON SWM.ID = SWS.SALARY_WAGE_MAIN_ID                                 ");
        sql.append("   AND SWS.IS_DELETE = 0                                               ");
        sql.append("   LEFT JOIN XC_SALARY_ASSIGNATION SA                                  ");
        sql.append("   ON SWM.SALARY_ASSIGNATION_ID = SA.ID                                ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                  ");
        sql.append("   ON JBI.ID = SWM.STAFF_ID                                            ");
        sql.append("   LEFT JOIN XC_PERIODDATA PDATA                                       ");
        sql.append("   ON SA.SALARY_PERIOD = PDATA.ID                                      ");
        sql.append("   LEFT JOIN BRANCH B                                       ");
        sql.append("   ON JBI.LABOR_COMPANY = B.ID                                      ");
        sql.append("   WHERE SWM.IS_DELETE = 0                                             ");
        sql.append("   AND SWM.IS_RELEASE = 1                                              ");
        sql.append("   AND SA.IS_FAFANG = 3                                              ");
        sql.append("   AND SWM.DEPT_ID = '"+LingUtil.userinfo().getDepId()+"'    ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'    ");
            }
            // 姓名
            if (!StringUtils.isBlank(mapData.get("staffName"))) {
                sql.append("   AND SWM.STAFF_NAME LIKE '%"+mapData.get("staffName").trim()+"%'    ");
            }
            // 公司
            if (!StringUtils.isBlank(mapData.get("companyName"))) {
                sql.append("   AND SWM.COMPANY_NAME LIKE '%"+mapData.get("companyName").trim()+"%'    ");
            }
            // 部门
//            if (!StringUtils.isBlank(mapData.get("deptName"))) {
//                sql.append("   AND SWM.DEPT_NAME LIKE '%"+mapData.get("deptName").trim()+"%'    ");
//            }
            // 员工类型
            if (!StringUtils.isBlank(mapData.get("empType"))) {
                sql.append("   AND JBI.EMP_TYPE = "+mapData.get("empType").trim()+"    ");
            }
            // 劳务公司
            if (!StringUtils.isBlank(mapData.get("laowu"))) {
                sql.append("   AND B.FZX LIKE '%"+mapData.get("laowu").trim()+"%'    ");
            }
            // 薪酬期间
            if (!StringUtils.isBlank(mapData.get("salaryPeriod"))) {
                String[] salaryPeriod = mapData.get("salaryPeriod").split(",");
                sql.append("   AND SA.SALARY_PERIOD IN ('"+StringUtils.join(salaryPeriod, "','")+"')            ");
            }
        }
        sql.append("   GROUP BY SWM.ID                                                     ");
        sql.append("   ORDER BY MIN(SWM.CREATEDATE) ASC                                    ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("staffId", obj[1]);
            map.put("staffName", obj[2]);
            map.put("companyId", obj[3]);
            map.put("companyName", obj[4]);
            map.put("deptId", obj[5]);
            map.put("deptName", obj[6]);
            map.put("itemId", obj[7]);
            map.put("itemName", obj[8]);
            map.put("salaryValue", obj[9]);
            map.put("jobNumber", obj[10]);
            map.put("empType", obj[11]);
            map.put("periodName", obj[12]);
            map.put("laowu", obj[13]);
            dataList.add(map);
        }

        StringBuilder itemsSql = new StringBuilder();
        itemsSql.append("   SELECT                                        ");
        itemsSql.append("     SWS.IBF_ID, MIN(SWS.ITEMS_NAME) ITEMS_NAME, ");
        itemsSql.append("     COUNT(DISTINCT SWS.IBF_ID)                  ");
        itemsSql.append("   FROM XC_SALARY_WAGE_MAIN SWM                  ");
        itemsSql.append("   LEFT JOIN XC_SALARY_WAGE_SECOND SWS           ");
        itemsSql.append("   ON SWM.ID = SWS.SALARY_WAGE_MAIN_ID           ");
        itemsSql.append("   AND SWS.IS_DELETE = 0                         ");
        itemsSql.append("   LEFT JOIN XC_SALARY_RECORD SR                 ");
        itemsSql.append("   ON SWM.STAFF_ID = SR.STAFF_ID                 ");
        itemsSql.append("   WHERE SWM.IS_DELETE = 0                       ");
        itemsSql.append("   AND SWM.IS_RELEASE = 1                        ");
        itemsSql.append("   GROUP BY SWS.IBF_ID                           ");
        itemsSql.append("   ORDER BY MIN(SWS.SX) ASC,SWS.IBF_ID ASC                       ");
        SQLQuery summaryQuery = getSession().createSQLQuery(itemsSql.toString());
        summaryQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> summaryList = summaryQuery.list();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        map.put("itmeTotal", summaryList);

        return map;
    }

}
