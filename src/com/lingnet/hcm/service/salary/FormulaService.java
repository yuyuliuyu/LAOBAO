package com.lingnet.hcm.service.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.Formula;

/**
 * 公式表
 * @ClassName: FormulaService 
 * @Description: 公式表
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
public interface FormulaService extends BaseService<Formula, String> {

    /**
     * @Title: 增加公式 
     * @param formdata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public String saveOrUpdate(String formdata);

    /**
     * @Title: 删除 
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public String remove(String ids);

    /**
     * @Title: 获取全局公式 
     * @param searchData
     * @return 
     * List<Formula> 
     * @author zhanghj
     * @since 2017年3月31日 V 1.0
     */
    public List<Map<String, Object>> getFullFormulaListData(String searchData);

}
