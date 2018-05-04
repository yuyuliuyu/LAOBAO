package com.lingnet.hcm.action.salary;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.service.salary.CustomizeService;
import com.lingnet.util.JsonUtil;

/**
 * 自定义SQL查询
 * @ClassName: CustomizeAction 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月29日 下午10:06:14 
 *
 */
public class CustomizeAction extends BaseAction {

    private static final long serialVersionUID = 8601228601683546158L;

    private String sql;

    @Resource(name="customizeService")
    private CustomizeService customizeService;

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
        return "add";
    }

    /**
     * @Title: 获取自定义sql语句列表
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月30日 V 1.0
     */
    public String getCustomizeListData() {
        return ajax(Status.success, JsonUtil.Encode(customizeService.getCustomizeListData(sql, pager)));
    }

    /**
     * @Title: 导出Excel
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月3日 V 1.0
     */
    public void export() {
        customizeService.export(sql);
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

}
