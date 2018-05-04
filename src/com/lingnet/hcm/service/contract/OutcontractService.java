package com.lingnet.hcm.service.contract;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.Outcontract;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: OutcontractService 
 * @Description: 外包合同Service
 * @author duanjj
 * @date 2017年4月26日 下午4:16:48 
 *
 */
public interface OutcontractService extends BaseService<Outcontract,String> {

    /**
     * 列表页面数据展示
     * @Title: listdata 
     * @param pager
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月26日 V 1.0
     */
    public String listdata(Pager pager);

    /**
     * 合同备案保存方法
     * @Title: saveOrUpdata 
     * @param formdata
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月26日 V 1.0
     */
    public String saveOrUpdata(String formdata) throws Exception;

    /**
     * 删除方法
     * @Title: deleteByIds 
     * @param id
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月26日 V 1.0
     */
    public String deleteByIds(String id) throws Exception;

}
