package com.lingnet.hcm.service.laobao;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.laobao.BiaoZhun;
import com.lingnet.util.Pager;

public interface WupingangweiService extends BaseService<BiaoZhun, String>{

	public HashMap getPersonByDepId(Pager pager,String searchData);
	
	public void saveOrUpdate(String gwId, String gridData)throws Exception;

	public HSSFWorkbook exportInfos();

	/**
	 * @Title: 获取岗位关联的物品信息
	 * @param searchData
	 * @param pager
	 * @return 
	 * Map<String,Object> 
	 * @author zhanghj
	 * @since 2017年7月25日 V 1.0
	 */
    public Map<String, Object> getGwAndWpData(String searchData, Pager pager);

    /**
     * @Title: 获取当前用户可以领取的物品信息
     * @param staffId
     * @param searchData
     * @param pager
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年7月25日 V 1.0
     */
    public Map<String, Object> getCurStaffWupinData(String staffId,String departId,
            String searchData, Pager pager);

    /**
     * @Title: 保存员工领取的物品
     * @param griddata
     * @param staffId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月25日 V 1.0
     */
    public String saveLingquWp(String griddata, String staffId);


	
}
