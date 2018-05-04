package com.lingnet.hcm.service.contract;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.LaborOutsourceApp;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: LaborOutsourceAppService 
 * @Description: 外包合同service 
 * @author duanjj
 * @date 2017年4月25日 下午2:43:29 
 *
 */
public interface LaborOutsourceAppService extends BaseService<LaborOutsourceApp,String> {

    /**
     * @Title: listdata 
     * @param id
     * @param pager
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月25日 V 1.0
     */
    public String listdata(String id, Pager pager);

    /**
     * 保存方法
     * @Title: saveOrUpdata 
     * @param formdata
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月25日 V 1.0
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
     * @since 2017年4月25日 V 1.0
     */
    public String deleteByIds(String id) throws Exception;

    /**
     * 提交审核
     * @Title: changeSubmit 
     * @param id
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月25日 V 1.0
     */
    public String changeSubmit(String id) throws Exception;

    /**
     * 审核列表页面数据
     * @Title: auditData 
     * @param id
     * @param pager
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月25日 V 1.0
     */
    public String auditData(String id, Pager pager);

    /**
     * 审核方法
     * @Title: audit 
     * @param formdata
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月25日 V 1.0
     */
    public String audit(String formdata) throws Exception;

}
