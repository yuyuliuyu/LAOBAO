package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryOrgInsuranceDao;
import com.lingnet.hcm.entity.salary.SalaryOrgInsurance;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 团体商业保险缴费
 * @ClassName: SalaryOrgInsuranceDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月14日 下午9:09:47 
 *
 */
@Repository("salaryOrgInsuranceDao")
public class SalaryOrgInsuranceDaoImpl extends BaseDaoImplInit<SalaryOrgInsurance, String> implements SalaryOrgInsuranceDao {

    @Resource(name="salaryAssignationService")
    private SalaryAssignationService salaryAssignationService;

    @Override
    public Map<String, Object> getListData(String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                ");
        sql.append("       SOI.ID,B.FZX,SOI.CBGS,JIB.INSUR_NAME,SOI.BF,      ");
        sql.append("       SOI.BXFL,SOI.SYYG,SOI.BEGIN_DATE,SOI.END_DATE,    ");
        sql.append("       SOI.BXJEMS,SOI.NOTE                               ");
        sql.append("   FROM XC_SALARY_ORG_INSURANCE SOI                      ");
        sql.append("   LEFT JOIN BRANCH B                                    ");
        sql.append("   ON SOI.INSURANCE_ID= B.ID                             ");
        sql.append("   LEFT JOIN JC_INSURANCE_BENEFITS JIB                   ");
        sql.append("   ON SOI.IBF_ID = JIB.ID                                ");
        sql.append("   WHERE SOI.IS_DELETE = 0                               ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 投保单位
            if (!StringUtils.isBlank(mapData.get("insuranceName"))) {
                sql.append("   AND B.FZX LIKE '%"+mapData.get("insuranceName").trim()+"%'  ");
            }
            // 承保公司
            if (!StringUtils.isBlank(mapData.get("cbgs"))) {
                sql.append("   AND SOI.CBGS LIKE '%"+mapData.get("cbgs").trim()+"%'        ");
            }
            // 福利项目
            if (!StringUtils.isBlank(mapData.get("bxName"))) {
                sql.append("   AND JIB.INSUR_NAME LIKE '%"+mapData.get("bxName").trim()+"%' ");
            }
            // 单位
            if (!StringUtils.isBlank(mapData.get("depId"))) {
//                sql.append("  AND B.ID IN ('"+StringUtils.join(mapData.get("depId").split(","), "','")+"') ");
            }
        }
        sql.append("   ORDER BY SOI.CREATEDATE DESC                          ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("tbdw", obj[1]);
            map.put("cbgs", obj[2]);
            map.put("bxName", obj[3]);
            map.put("bf", obj[4]);
            map.put("bxfl", obj[5]);
            map.put("syyg", obj[6]);
            map.put("beginDate", obj[7]);
            map.put("endDate", obj[8]);
            map.put("bxjems", obj[9]);
            map.put("note", obj[10]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public Map<String, Object> getPerListData(String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                        ");
        sql.append("       SPI.ID,JBI.JOB_NUMBER,JBI.NAME,QD.NAME DEPTNME,SPI.YEAR,JIB.INSUR_NAME,SPI.BF,SPI.BXFL,  ");
        sql.append("       SPI.GSJFJE,SPI.GRJFJE,SPI.BEGIN_DATE,SPI.BBXR,SPI.YTBRGX,SPI.ID_CARD,     ");
        sql.append("       SPI.SEX,SPI.BIRTH_DATE                                                    ");
        sql.append("   FROM XC_SALARY_PER_INSURANCE SPI                                              ");
        sql.append("   LEFT JOIN XC_SALARY_RECORD SR                                            ");
        sql.append("   ON SPI.STAFF_ID = SR.STAFF_ID                                                      ");
        sql.append("   LEFT JOIN JC_BASIC_INFORMATION JBI                                            ");
        sql.append("   ON SPI.STAFF_ID = JBI.ID                                                      ");
        sql.append("   LEFT JOIN JC_INSURANCE_BENEFITS JIB                                           ");
        sql.append("   ON SPI.IBF_ID = JIB.ID                                                        ");
        sql.append("   LEFT JOIN QX_DEPARTMENT QD                                                    ");
        sql.append("   ON SPI.DEPT_ID = QD.ID                                                        ");
        sql.append("   WHERE SPI.IS_DELETE = 0                               ");

        Map<String, String> mapData = JsonUtil.parseProperties(pager.getSearchData());
        if (mapData != null) {
            // 员工姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("   AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                                  ");
            }
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("   AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                                  ");
            }
        }
        sql.append("   ORDER BY SPI.CREATEDATE DESC                                                  ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[1]);
            map.put("name", obj[2]);
            map.put("deptName", obj[3]);
            map.put("year", obj[4]);
            map.put("insuranceName", obj[5]);
            map.put("bf", obj[6]);
            map.put("bxfl", obj[7]);
            map.put("gsjfje", obj[8]);
            map.put("grjfje", obj[9]);
            map.put("beginDate", obj[10]);
            map.put("bbxr", obj[11]);
            map.put("ytbrgx", obj[12]);
            map.put("idCard", obj[13]);
            map.put("sex", obj[14]);
            map.put("birthDate", obj[15]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public Map<String, Object> getAllPersonalListData(String searchData, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT                                                     ");
        sql.append("      JBI.ID,JBI.NAME,JBI.JOB_NUMBER,B.FZX,QD.NAME DEPTNAME,   ");
        sql.append("      JBI.SPECIFIC_POST,JBI.ON_JOB,JBI.ON_POST,JBI.DEPART_ID                 ");
        sql.append("    FROM XC_SALARY_RECORD SR                              ");
        sql.append("    LEFT JOIN JC_BASIC_INFORMATION JBI                              ");
        sql.append("    ON JBI.ID = SR.STAFF_ID                                      ");
        sql.append("    LEFT JOIN BRANCH B                                         ");
        sql.append("    ON SR.FILM_NAME = B.ID                                      ");
        sql.append("    LEFT JOIN QX_DEPARTMENT QD                                 ");
        sql.append("    ON SR.DEPTNAME = QD.ID                                   ");
        sql.append("   WHERE JBI.IS_DELETE = 0                               ");

        Map<String, String> mapData = JsonUtil.parseProperties(pager.getSearchData());
        if (null != mapData) {
            // 部门
//            if (!StringUtils.isBlank(mapData.get("depId"))) {
                sql.append("  AND SR.DEPTNAME IN ('"+StringUtils.join(mapData.get("depId").split(","), "','")+"')             ");
//            }
            // 工号
            if (!StringUtils.isBlank(mapData.get("jobNumber"))) {
                sql.append("  AND JBI.JOB_NUMBER LIKE '%"+mapData.get("jobNumber").trim()+"%'                         ");
            }
            // 姓名
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("  AND JBI.NAME LIKE '%"+mapData.get("name").trim()+"%'                         ");
            }
        }
        sql.append("     ORDER BY SR.CREATEDATE ASC                   ");

        pager  = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("jobNumber", obj[2]);
            map.put("name", obj[1]);
            map.put("company", obj[3]);
            map.put("deptId", obj[8]);
            map.put("deptName", obj[4]);
            map.put("specificPost", obj[5]);
            map.put("onJob", obj[6]);
            map.put("onPost", obj[7]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

}
