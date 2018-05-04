package com.lingnet.hcm.action.salary;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.Formula;
import com.lingnet.hcm.service.salary.FormulaService;
import com.lingnet.util.JsonUtil;

/**
 * 全局公式
 * @ClassName: FormulaAction 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月31日 下午3:26:33 
 *
 */
public class FormulaAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    @Resource(name="formulaService")
    private FormulaService formulaService;
    private Formula formula;
    private String pid;
    private String parentName;

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String list() {
        return "list";
    }

    /**
     * @Title: 列表增加页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String add() {
        Formula formula = formulaService.get(Formula.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (formula != null) {
            pid = formula.getId();
            parentName = formula.getMethod();
        }
        return "add";
    }

    /**
     * @Title: 列表编辑页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String edit() {
        formula = formulaService.get(Formula.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (formula != null) {
            pid = formula.getPid();
            Formula f = getBeanByCriteria(Formula.class, Restrictions.eq("id", pid), Restrictions.eq("isDelete", 0));
            if(f != null) parentName = f.getMethod();
        }
        return "add";
    }

    /**
     * @Title: 父级选择
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年6月19日 V 1.0
     */
    public String tree() {
        return "tree";
    }

    /**
     * @Title: 全局公式保存更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        return ajax(Status.success, formulaService.saveOrUpdate(formdata));
    }

    /**
     * @Title: 全局公式删除
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String remove() {
        return ajax(Status.success, formulaService.remove(ids));
    }

    /**
     * @Title: 获取全局公式
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getFullFormulaListData() {
        return ajax(Status.success, JsonUtil.Encode(formulaService.getFullFormulaListData(searchData)));
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

}
