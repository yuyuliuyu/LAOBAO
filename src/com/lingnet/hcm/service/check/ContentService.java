package com.lingnet.hcm.service.check;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkContent;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: ContentService 
 * @Description: 考勤内容service 
 * @author wangqiang
 * @date 2017年4月1日 上午9:09:29 
 *
 */
public interface ContentService extends BaseService<CkContent, String>{
	
	/**
	 * 获取考勤内容信息集合
	 * @Title: getDataByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月1日 V 1.0
	 */
	public Map<String, Object> getDataByCond(Pager pager);
	
	/**
	 * 保存或修改考勤统计信息
	 * @Title: saveOrUpdateInfo 
	 * @param formdata
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年4月1日 V 1.0
	 */
	public void saveOrUpdateInfo(String ztw,String formdata) throws Exception;
	
	/**
	 * 导出考勤内容信息
	 * @Title: exportInfos 
	 * @return 
	 * HSSFWorkbook 
	 * @author 马晓鹏
	 * @since 2017年4月5日 V 1.0
	 */
	public HSSFWorkbook exportInfos();
	
	/**
	 * 获取考勤内容下拉选
	 * @Title: getContentList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public List<Map<String, String>> getContentList();
	
	/**
	 * 获得考勤内容是否存在
	 * @Title: getValidateContent 
	 * @param contentName
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月24日 V 1.0
	 */
	public CkContent getValidateContent(String contentName);
}
