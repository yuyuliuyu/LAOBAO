package com.lingnet.hcm.dao.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.salary.SalaryPartDao;
import com.lingnet.hcm.entity.salary.SalaryPart;

/**
 * 工资条
 * @ClassName: SalaryPartDaoImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
@Repository("salaryPartDao")
public class SalaryPartDaoImpl extends BaseDaoImplInit<SalaryPart, String> implements SalaryPartDao {

    @Override
    public Map<String, Object> getWageTypeListData(String wageId, String searchData) {
        StringBuilder builder = new StringBuilder();
        builder.append("  SELECT                             ");
        builder.append("  PS.ID,SI.NAME,PS.IS_ZREO,PS.SX,SI.ID itemid     ");
        builder.append("  FROM XC_SALARY_PART SP             ");
        builder.append("  LEFT JOIN XC_PART_SALARY PS        ");
        builder.append("  ON SP.ID = PS.PART_ID              ");
        builder.append("  LEFT JOIN XC_SALARY_ITEMS SI       ");
        builder.append("  ON PS.ITEM_ID = SI.ID              ");
        builder.append("  WHERE SP.IS_DELETE = 0             ");
        builder.append("    AND PS.IS_DELETE = 0               ");
        builder.append("    AND SP.ID = '"+wageId+"'    ");
        builder.append("  ORDER BY PS.SX ASC        ");

        List<?> list = this.findBySql(builder.toString());
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("name", obj[1]);
            map.put("isZreo", obj[2]);
            map.put("sx", obj[3]);
            map.put("itemId", obj[4]);
            dataList.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);

        return map;
    }

}
