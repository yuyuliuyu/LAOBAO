package com.lingnet.hcm.service.check;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkStandard;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: StandardService 
 * @Description: 考勤标准service 
 * @author wangqiang
 * @date 2017年3月22日 下午2:11:17 
 *
 */
public interface StandardService extends BaseService<CkStandard, String>{
	
	/**
	 * 获取考勤标准信息集合
	 * @Title: getDataByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public Map<String, Object> getDataByCond(Pager pager);
	
	/**
	 * 保存或修改考勤标准信息
	 * @Title: saveOrUpdateInfo 
	 * @param formdata
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public void saveOrUpdateInfo(Map<String,String> dataMap) throws Exception;
	
	/**
	 * 导出考勤标准信息
	 * @Title: exportInfos 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public HSSFWorkbook exportInfos();
}
