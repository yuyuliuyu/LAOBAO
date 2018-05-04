package com.lingnet.hcm.service.check;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkInstitution;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: InstitutionService 
 * @Description: 班制名称service 
 * @author wangqiang
 * @date 2017年3月31日 上午11:02:52 
 *
 */
public interface InstitutionService extends BaseService<CkInstitution, String>{
	
	/**
	 * 获取班制名称信息集合
	 * @Title: getDataByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public Map<String, Object> getDataByCond(Pager pager);
	
	/**
	 * 保存或修改班制名称信息
	 * @Title: saveOrUpdateInfo 
	 * @param formdata
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public void saveOrUpdateInfo(String formdata) throws Exception;
	
	/**
	 * 导出班制名称信息
	 * @Title: exportInfos 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public HSSFWorkbook exportInfos();
	
	/**
	 * 获得班制名称下拉
	 * @Title: getInstituteList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public List<Map<String, String>> getInstitutionList(String dayFlag);
	
	/**
	 * 查询班制名称是否存在
	 * @Title: getValidateInstitueName 
	 * @param institueName
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	public CkInstitution getValidateInstitueName(String institueName);
}
