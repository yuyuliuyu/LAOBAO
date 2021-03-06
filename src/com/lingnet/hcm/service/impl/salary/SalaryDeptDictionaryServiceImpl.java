package com.lingnet.hcm.service.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryDeptDictionary;
import com.lingnet.hcm.service.salary.SalaryDeptDictionaryService;
import com.lingnet.util.JsonUtil;

/**
 * 薪酬岗位字典
 * @ClassName: SalaryDeptDictionaryServiceImpl 
 * @Description: 薪酬岗位字典 
 * @author zhanghj
 * @date 2017年5月16日 上午9:02:29 
 *
 */
@Service("salaryDeptDictionaryService")
public class SalaryDeptDictionaryServiceImpl extends BaseServiceImpl<SalaryDeptDictionary, String>
              implements SalaryDeptDictionaryService {

    @Override
    public List<Map<String, Object>> getDataList(String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("    SELECT ID,                                          ");
        sql.append("      MIN(TYPE) TYPE, MIN(NAME) NAME, MIN(GJDX) GJDX,   ");
        sql.append("      MIN(ZJLB) ZJLB, MIN(NOTE) NOTE,                   ");
        sql.append("      WM_CONCAT(POSITION_NAME) POSITION_NAME, MIN(ZWBM) ZWBM          ");
        sql.append("    FROM                                                ");
        sql.append("      ( SELECT DISTINCT SDD.ID,                         ");
        sql.append("        SDD.TYPE, SDD.NAME, SDD.GJDX, SDD.ZWBM,         ");
        sql.append("        SDD.ZJLB, SDD.NOTE, BZGW.POSITION_NAME,SDD.IS_DELETE          ");
        sql.append("      FROM XC_SALARY_DEPT_DICTIONARY SDD                ");
        sql.append("      LEFT JOIN XC_SALARY_RECORD SR                     ");
        sql.append("      ON SR.SALARY_POST = SDD.ID                        ");
        sql.append("      LEFT JOIN POST_POSITION BZGW                      ");
        sql.append("      ON SR.POST          = BZGW.ID                     ");
        sql.append("      WHERE SDD.IS_DELETE = 0                           ");
        sql.append("      ) BIGDATA                                         ");
        sql.append("    WHERE IS_DELETE=0                                   ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 岗位名称
            if (!StringUtils.isBlank(mapData.get("name"))) {
                sql.append("    AND NAME LIKE '%"+mapData.get("name").trim()+"%'        ");
            }
        }
        sql.append("    GROUP BY ID,ZWBM                                 ");
        sql.append("    ORDER BY ZWBM ASC                              ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("type", obj[1]);
            map.put("name", obj[2]);
            map.put("gjdx", obj[3]);
            map.put("zjlb", obj[4]);
            map.put("note", obj[5]);
            map.put("positionName", obj[6]);
            map.put("zwbm", obj[7]);
            dataList.add(map);
        }

        return dataList;
    }
}
