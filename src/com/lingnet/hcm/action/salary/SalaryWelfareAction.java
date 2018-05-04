package com.lingnet.hcm.action.salary;

import com.lingnet.common.action.BaseAction;

/**
 * 员工福利
 * @ClassName: SalaryWelfareAction 
 * @Description: 员工福利
 * @author zhanghj
 * @date 2017年3月16日 下午4:54:37 
 *
 */
public class SalaryWelfareAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

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
     * @Title: 项目福利
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String type() {
        return "type";
    }

    /**
     * @Title: 项目福利编辑
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String typeAdd() {
        return "typeadd";
    }

}
