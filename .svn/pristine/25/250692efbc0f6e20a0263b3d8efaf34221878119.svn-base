package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryWageDao;
import com.lingnet.hcm.entity.salary.SalaryWage;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 薪资项目
 * @ClassName: SalaryItemsDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
@Repository("salaryWageDao")
public class SalaryWageDaoImpl extends BaseDaoImplInit<SalaryWage, String> implements SalaryWageDao {

    @Override
    public Map<String, Object> getWageTypeListData(String wageId) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                                 ");
        sql.append("   WAT.ID,MIN(WAT.WAGE_ID) WAGE_ID,MIN(ITEMS.NAME) NAME,MIN(WAT.SX) SX,MIN(WAT.HIGH) HIGH,MIN(WAT.LOW) LOW,MIN(WAT.IS_ER) IS_ER,");
        sql.append("   MIN(WAT.IS_DISPLAY) IS_DISPLAY,MIN(WAT.IS_DELETE) IS_DELETE,MIN(WAT.TYPE) TYPE,  ");
        sql.append("   MIN(ITEMS.ITEM_TYPE) ITEM_TYPE,MIN(WAT.ITEM_ID) ITEM_ID, MIN(ITEMS.IS_NUMBER) IS_NUMBER, COUNT(SF.ID) SFID,  ");
        sql.append("   MIN(WAT.ZLJS) ZLJS,MIN(WAT.ZLBJ) ZLBJ ");
        sql.append("   FROM XC_SALARY_WAGE WAGE                                                                               ");
        sql.append("   LEFT JOIN XC_SALARY_WAGE_AND_TYPE WAT                                                                  ");
        sql.append("   ON WAGE.ID = WAT.WAGE_ID                                                                               ");
        sql.append("   LEFT JOIN XC_SALARY_ITEMS ITEMS                                                                        ");
        sql.append("   ON ITEMS.ID = WAT.ITEM_ID                                                                              ");
        sql.append("   LEFT JOIN XC_SALARY_FORMULA SF                                                                        ");
        sql.append("   ON WAT.ID = SF.BIND_ID                                                                              ");
        sql.append("   AND SF.IS_DELETE = 0                                                                              ");
        sql.append("   WHERE WAGE.ID = '"+wageId+"' AND WAGE.IS_DELETE=0                                                       ");
        sql.append("    AND WAT.IS_DELETE=0                                                       ");
        sql.append("   GROUP BY WAT.ID                                                                                    ");
        sql.append("   ORDER BY MIN(WAT.SX) ASC                                                                                    ");

        List<?> list = this.findBySql(sql.toString());
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("wageId", obj[1]);
            map.put("name", obj[2]);
            map.put("sx", obj[3]);
            map.put("high", obj[4]);
            map.put("low", obj[5]);
            map.put("isEr", obj[6]);
            map.put("isDisplay", obj[7]);
            map.put("type", obj[9]);
            map.put("itemType", obj[10]);
            map.put("itemId", obj[11]);
            map.put("salaryWageId", obj[0]);
            map.put("isNumber", obj[12]);
            map.put("formulaCount", obj[13]);
            map.put("zljs", obj[14]);
            map.put("zlbj", obj[15]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);

        return map;
    }

    @Override
    public Map<String, Object> getSalaryWageEffectListData(Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                      ");
        sql.append("     SW.ID,B.FZX,SW.NAME,SW.SIGN,SW.TYPE,SW.EFFECT_DATE        ");
        sql.append("   FROM XC_SALARY_WAGE SW                                      ");
        sql.append("   LEFT JOIN BRANCH B                                          ");
        sql.append("   ON SW.COMPANY_ID = B.ID                                     ");
        sql.append("   WHERE SW.IS_DELETE = 0                                      ");
        sql.append("     AND SW.TYPE = 1                                      ");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        sql.append("   AND SW.EFFECT_DATE <= TO_DATE('"+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH) +1)+"-"+cal.get(Calendar.DATE)+"', 'yyyy-MM-dd')   ");

        Map<String, String> mapData = JsonUtil.parseProperties(pager.getSearchData());
        if (mapData != null) {
            // 公司ID
            if (!StringUtils.isBlank(mapData.get("companyId"))) {
                sql.append("     AND SW.COMPANY_ID = '"+mapData.get("companyId").trim()+"' ");
            }
            // 公司名称
            if (!StringUtils.isBlank(mapData.get("companyName"))) {
                sql.append("     AND B.FZX LIKE '%"+mapData.get("companyName").trim()+"%' ");
            }
            // 工资套名称
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("     AND SW.NAME LIKE '%"+mapData.get("name").trim()+"%' ");
            }
            // 工资套类型
            if (!StringUtils.isBlank(mapData.get("sign"))) {
                sql.append("     AND SW.SIGN = "+mapData.get("sign"));
            }
        }
        sql.append("   ORDER BY SW.CREATEDATE ASC                                      ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("companyName", obj[1]);
            map.put("name", obj[2]);
            map.put("sign", obj[3]);
            map.put("type", obj[4]);
            map.put("effectDate", obj[5]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return map;
    }

}
