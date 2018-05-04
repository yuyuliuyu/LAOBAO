package com.lingnet.hcm.service.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.CheckSalaryColor;
import com.lingnet.hcm.service.salary.CheckSalaryColorService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 工资分配核对色值配置表
 * @ClassName: CheckSalaryColorServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月28日 下午5:19:19 
 *
 */
@Service("checkSalaryColorService")
public class CheckSalaryColorServiceImpl extends BaseServiceImpl<CheckSalaryColor, String>
              implements CheckSalaryColorService {

    @Override
    public String saveOrUpdate(String formdata) {
        CheckSalaryColor formula = JsonUtil.toObject(formdata, CheckSalaryColor.class);
        if (formula != null) {
            if (StringUtils.isBlank(formula.getId())) {
                CheckSalaryColor newFor = get(CheckSalaryColor.class,
                        Restrictions.eq("companyId", formula.getCompanyId()),
                        Restrictions.eq("ibfId", formula.getIbfId()),
                        Restrictions.eq("isDelete", 0));
                if (newFor !=null) {
                    return "该薪资项目值已经存在";
                }
                formula.setIsDelete(0);
                String id = save(formula);
                if (StringUtils.isBlank(id)) {
                    return "保存失败";
                }
            } else {
                CheckSalaryColor newFor = get(CheckSalaryColor.class,
                        Restrictions.ne("id", formula.getId()),
                        Restrictions.eq("companyId", formula.getCompanyId()),
                        Restrictions.eq("ibfId", formula.getIbfId()),
                        Restrictions.eq("isDelete", 0));
                if (newFor !=null) {
                    return "该薪资项目值已经存在";
                }
                CheckSalaryColor newFormula = get(CheckSalaryColor.class, Restrictions.eq("id", formula.getId()),
                        Restrictions.eq("isDelete", 0));
                if (newFormula == null) {
                    return "该薪资项目值不存在";
                }
                formula.setIsDelete(0);
                newFormula.copyFrom(formula);
                update(newFormula);
            }
        } else {
            return "信息不能为空";
        }

        return "success";
    }

    @Override
    public String remove(String ids) {
        String[] idArray = ids.split(",");
        for (int i = 0, l = idArray.length; i < l; i++) {
            CheckSalaryColor formula = get(CheckSalaryColor.class, Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
            if (formula != null) {
                formula.setIsDelete(1);
                this.update(formula);
            } else {
                return "第"+(i+1)+"条记录不存在";
            }
        }

        return "success";
    }

    @Override
    public Map<String, Object> getListData(String compamyId, Pager pager) {
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                                                ");
        sql.append("    CSC.ID, B.FZX, SI.NAME,CSC.COLOR,CSC.COLOR_DIFF  ");
        sql.append("  FROM XC_CHECK_SALARY_COLOR CSC                        ");
        sql.append("  LEFT JOIN BRANCH B                                    ");
        sql.append("  ON CSC.COMPANY_ID = B.ID                              ");
        sql.append("  LEFT JOIN XC_SALARY_ITEMS SI                          ");
        sql.append("  ON CSC.IBF_ID = SI.ID                                 ");
        sql.append("  WHERE CSC.IS_DELETE = 0                               ");

        Map<String, String> mapData = JsonUtil.parseProperties(pager.getSearchData());
        if (mapData != null) {
            // 公司名
            if (!StringUtils.isBlank(mapData.get("companyName"))) {
                sql.append("  AND B.FZX LIKE '%"+mapData.get("companyName").trim()+"%'  ");
            }
            // 薪资项目
            if (!StringUtils.isBlank(mapData.get("salaryName"))) {
                sql.append("  AND SI.NAME LIKE '%"+mapData.get("salaryName").trim()+"%'  ");
            }
        }
        sql.append("  ORDER BY CSC.CREATEDATE ASC                           ");

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        pager = findPagerBySql(pager, sql.toString());
        List<?> list2 = pager.getResult();
        for (int i = 0, l = list2.size(); i < l; i++) {
            Object[] obj = (Object[]) list2.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("fzx", obj[1]);
            map.put("salaryName", obj[2]);
            map.put("color", obj[3]);
            map.put("colorDiff", obj[4]);
            list.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", list);
        map.put("total", pager.getTotalCount());

        return map;
    }
}
