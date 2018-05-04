package com.lingnet.hcm.service.check;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.check.CkClass;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: ClassGroupService 
 * @Description: 班组信息service 
 * @author wangqiang
 * @date 2017年3月22日 下午2:11:17 
 *
 */
public interface ClassGroupService extends BaseService<CkClass, String>{
	
	/**
	 * 获得所有班组信息下拉集合
	 * @Title: getAllClassInfos 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月22日 V 1.0
	 */
	public List<CkClass> getAllClassInfos();
	
	/**
	 * 获得班组分页信息集合
	 * @Title: getDataByCond 
	 * @param pager
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public Map<String, Object> getDataByCond(Pager pager);
	
	/**
	 * 保存或修改班组信息
	 * @Title: saveOrUpdateInfo 
	 * @param formdata
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public void saveOrUpdateInfo(String formdata) throws Exception;
	
	/**
	 * 导出班组信息
	 * @Title: exportInfos 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public HSSFWorkbook exportInfos();
	
	/**
	 * 查询班号是否已存在
	 * @Title: getValidateClassNo 
	 * @param classNo
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月25日 V 1.0
	 */
	public CkClass getValidateClassNo(String classNo);
}
