package com.lingnet.hcm.service.salary;

import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.Formula;
import com.lingnet.util.Pager;

/**
 * 自定义sql查询
 * @ClassName: CustomizeService 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月29日 下午10:09:29 
 *
 */
public interface CustomizeService extends BaseService<Formula, String> {

    /**
     * @Title: 获取自定义sql语句列表
     * @param sql
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年6月29日 V 1.0
     */
    public Map<String, Object> getCustomizeListData(String sql, Pager pager);

    /**
     * @Title: 导出Excel
     * @param sql 
     * void 
     * @author zhanghj
     * @since 2017年8月3日 V 1.0
     */
    public void export(String sql);

}
