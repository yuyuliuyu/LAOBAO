package com.lingnet.hcm.dao.check;

import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.check.CkContent;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: ContentDao 
 * @Description: 考勤内容Dao 
 * @author wangqiang
 * @date 2017年4月1日 上午9:06:10 
 *
 */
public interface ContentDao extends BaseDao<CkContent, String>{
	
	/**
	 * 获得考勤内容信息集合
	 * @Title: getInfoByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月1日 V 1.0
	 */
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId);
	
	/**
	 * 获得考勤内容信息集合
	 * @Title: getAllInfoList 
	 * @return 
	 * List<Map<String,String>> 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public List<Map<String, String>> getAllInfoList(String userId);
	
	/**
	 * 获取考勤内容下拉
	 * @Title: getContentList 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月5日 V 1.0
	 */
	public List<Map<String, String>> getContentList(String userId);
	
	/**
	 * 获得考勤内容是否存在
	 * @Title: getValidateContent 
	 * @param contentName
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月24日 V 1.0
	 */
	public CkContent getValidateContent(String userId, String contentName);
	
	/**
	 * 根据部门ID查询考勤信息集合
	 * @Title: getContentListByDepId 
	 * @param checkUnitId
	 * @param contentList
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	public List<CkContent> getContentListByDepId(String checkUnitId, List<String> contentList);
}
