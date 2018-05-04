package com.lingnet.hcm.service.salary;

import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.salary.Rate;

/**
 * 税率表
 * @ClassName: RateService 
 * @Description: 税率表
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
public interface RateService extends BaseService<Rate, String> {

    /**
     * @Title: 获取所有税率设置 
     * @return 
     * List<Rate> 
     * @author zhanghj
     * @since 2017年3月23日 V 1.0
     */
    public List<Rate> getAllRateListData();

    /**
     * @Title: 获取税率具体内容  
     * @param id
     * @return 
     * List<Ratedata> 
     * @author zhanghj
     * @since 2017年3月23日 V 1.0
     */
    public List<Map<String, Object>> getRateStaticListData(String id);

    /**
     * @Title: 保存税率 
     * @param formdata
     * @param griddata
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月23日 V 1.0
     */
    public String saveOrUpdate(String formdata, String griddata) throws Exception;

}
