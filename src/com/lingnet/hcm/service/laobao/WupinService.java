package com.lingnet.hcm.service.laobao;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.laobao.Wupin;
import com.lingnet.util.Pager;

public interface WupinService extends BaseService<Wupin, String>{

	public String json(String key, Pager pager);
	public String saveOrUpdate(String formdata);
	public HSSFWorkbook exportInfos();
	/*public void saveOrUpdate(String name, String maqm, String daqm,String cxgz, String dxgz, String yrf, String yy, String yx,String maqx, String jyx, String jyst, String dhst,BackendSecurityMetadataSource backendSecurityMetadataSource);*/
	/**
	 * @Title: 发放标准数据保存或者更新
	 * @param formdata
	 * @return 
	 * String 
	 * @author zhanghj
	 * @since 2017年7月25日 V 1.0
	 */
    public String saveOrUpdateBz(String formdata);

    /**
     * @Title: 获取需要审批的领取物品
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月25日 V 1.0
     */
    public Map<String, Object> getNeedCheckWp(String node1,String node2, Pager pager);

    /**
     * @Title: 同意申请物品
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月26日 V 1.0
     */
    public String updateToAgree(String ids);

    /**
     * @Title: 不同意申请物品
     * @param ids
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月26日 V 1.0
     */
    public String updateToDisAgree(String ids);
}
