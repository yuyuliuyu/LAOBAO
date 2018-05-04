package com.lingnet.hcm.service.impl.salary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.Formula;
import com.lingnet.hcm.service.salary.FormulaService;
import com.lingnet.util.JsonUtil;

/**
 * 公式表
 * @ClassName: FormulaServiceImpl 
 * @Description: 公式表
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
@Service("formulaService")
public class FormulaServiceImpl extends BaseServiceImpl<Formula, String>
              implements FormulaService {

    ////////////////////////////////  以下不属于对外开放的方法  ///////////////////////////////////////////
    @Override
    public String saveOrUpdate(String formdata) {
        Formula formula = JsonUtil.toObject(formdata, Formula.class);
        if (formula != null) {
            if (StringUtils.isBlank(formula.getPid())) {
                formula.setPid("-1");
            }
            if (StringUtils.isBlank(formula.getId())) {
                Formula newFor = get(Formula.class, Restrictions.eq("methodEn", formula.getMethodEn()), Restrictions.eq("isDelete", 0));
                if (newFor !=null) {
                    return "该公式已经存在";
                }
                formula.setIsDelete(0);
                String id = save(formula);
                if (StringUtils.isBlank(id)) {
                    return "保存失败";
                }
            } else {
                Formula newFor = get(Formula.class, Restrictions.ne("id", formula.getId()),
                        Restrictions.eq("methodEn", formula.getMethodEn()), Restrictions.eq("isDelete", 0));
                if (newFor !=null) {
                    return "该公式已经存在";
                }
                Formula newFormula = get(Formula.class, Restrictions.eq("id", formula.getId()),
                        Restrictions.eq("isDelete", 0));
                if (newFormula == null) {
                    return "该公式不存在";
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
            Formula formula = get(Formula.class, Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
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
    public List<Map<String, Object>> getFullFormulaListData(String searchData) {
        Conjunction and = Restrictions.conjunction();
        Map<String, String> map = JsonUtil.parseProperties(searchData);
        if (map != null) {
            // 全局公式名称
            if (!StringUtils.isBlank(map.get("method"))) {
                and.add(Restrictions.like("method", map.get("method"), MatchMode.ANYWHERE));
            }
        }
        List<Formula> list = getOrderList(Formula.class, Order.asc("lineIndex"), Restrictions.eq("isDelete", 0), and);
        List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
        for (Formula formula : list) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("id", formula.getId());
            m.put("pid", formula.getPid());
            m.put("method", formula.getMethod());
            m.put("methodCn", formula.getMethodCn());
            m.put("methodEn", formula.getMethodEn());
            m.put("executeIndex", formula.getExecuteIndex());
            m.put("lineIndex", formula.getLineIndex());
            m.put("pubClass", formula.getPubClass());
            m.put("description", formula.getDescription());
            m.put("isDelete", formula.getIsDelete());
//            m.put("service", "formulaService");
            newList.add(m);
        }

        return newList;
    }
}
