package com.lingnet.hcm.action.salary;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.salary.CheckSalaryColor;
import com.lingnet.hcm.entity.salary.SalaryItems;
import com.lingnet.hcm.service.salary.CheckSalaryColorService;
import com.lingnet.util.JsonUtil;

/**
 * 色值配置
 * @ClassName: CheckSalaryAction 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月28日 下午5:25:46 
 *
 */
public class CheckSalaryAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    private String salaryName;// 薪资项目名称
    private CheckSalaryColor checkSalaryColor;

    @Resource(name="checkSalaryColorService")
    private CheckSalaryColorService checkSalaryColorService;

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
        checkSalaryColor = getBeanByCriteria(CheckSalaryColor.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (checkSalaryColor != null) {
            depId = checkSalaryColor.getCompanyId();
            SalaryItems items = getBeanByCriteria(SalaryItems.class,
                    Restrictions.eq("id", checkSalaryColor.getIbfId()), Restrictions.eq("isDelete", 0));
            if (items != null) salaryName = items.getName();
        }

        return "add";
    }

    /**
     * @Title: 色值保存更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate() {
        return ajax(Status.success, checkSalaryColorService.saveOrUpdate(formdata));
    }

    /**
     * @Title: 色值删除
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月24日 V 1.0
     */
    public String remove() {
        return ajax(Status.success, checkSalaryColorService.remove(ids));
    }

    /**
     * @Title: 获取全部色值
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getListData() {
        return ajax(Status.success, JsonUtil.Encode(checkSalaryColorService.getListData(depId, pager)));
    }

    public CheckSalaryColor getCheckSalaryColor() {
        return checkSalaryColor;
    }

    public void setCheckSalaryColor(CheckSalaryColor checkSalaryColor) {
        this.checkSalaryColor = checkSalaryColor;
    }

    public String getSalaryName() {
        return salaryName;
    }

    public void setSalaryName(String salaryName) {
        this.salaryName = salaryName;
    }

}
