package com.lingnet.hcm.service.branch;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.ComChange;
import com.lingnet.util.Pager;

public interface ComChangeService extends BaseService<ComChange,String>{

    /**
     * 
     * @Title: 数据列表 
     * @param id
     * @param pager
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月18日 V 1.0
     */
    public String listdata(String id, Pager pager);

    /**
     * 保存方法
     * @Title: saveOrUpdata 
     * @param formdata
     * @param img
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月18日 V 1.0
     */
    public String saveOrUpdata(String formdata, String img) throws Exception;

    /**
     * 删除方法
     * @Title: deleteByIds 
     * @param id
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月18日 V 1.0
     */
    public String deleteByIds(String id) throws Exception;

    /**
     * 提交方法
     * @Title: changeSubmit 
     * @param id
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月20日 V 1.0
     */
    public String changeSubmit(String id) throws Exception;

    /**
     * 审核方法
     * @Title: audit 
     * @param formdata
     * @return
     * @throws Exception 
     * String 
     * @author duanjj
     * @since 2017年4月24日 V 1.0
     */
    public String audit(String formdata) throws Exception;

    /**
     * 待审核数据列表
     * @Title: auditData 
     * @param id
     * @param pager
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月24日 V 1.0
     */
    public String auditData(String id, Pager pager);

}
