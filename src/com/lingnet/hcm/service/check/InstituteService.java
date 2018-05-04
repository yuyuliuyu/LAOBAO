package com.lingnet.hcm.service.check;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkEmpInstitute;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: InstituteService 
 * @Description: 考勤班次service 
 * @author wangqiang
 * @date 2017年3月31日 上午11:02:52 
 *
 */
public interface InstituteService extends BaseService<CkEmpInstitute, String>{
	
	/**
	 * 获取考勤班次信息集合
	 * @Title: getDataByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public Map<String, Object> getDataByCond(Pager pager, String typeFlag);
	
	/**
	 * 保存或修改考勤班次信息
	 * @Title: saveOrUpdateInfo 
	 * @param formdata
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public void saveOrUpdateInfo(String formdata) throws Exception;
	
	/**
	 * 导出考勤班次信息
	 * @Title: exportInfos 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月31日 V 1.0
	 */
	public HSSFWorkbook exportInfos();
	
	/**
	 * 保存白班班制信息
	 * @Title: saveInstituteInfo 
	 * @param dataMap
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年4月14日 V 1.0
	 */
	public void saveInstituteInfo(Map<String, String> dataMap) throws Exception;
	
	/**
	 * 修改白班班制信息
	 * @Title: updateInstituteInfo 
	 * @param dataMap
	 * @param empInstitute
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年12月15日 V 1.0
	 */
	public void updateInstituteInfo(Map<String, String> dataMap, CkEmpInstitute empInstitute) throws Exception;
	
	/**
	 * 验证班制信息是否已存在
	 * @Title: getValidateInstituteId 
	 * @param instituteId
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	CkEmpInstitute getValidateInstituteId(String instituteId);
}
