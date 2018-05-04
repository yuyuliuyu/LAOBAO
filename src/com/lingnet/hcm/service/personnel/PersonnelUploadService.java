package com.lingnet.hcm.service.personnel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: EmployeeService 
 * @Description: 人员信息service 
 * @author zhangruiling
 * @date 2017年3月20日 下午1:30:07 
 *
 */
public interface PersonnelUploadService extends BaseService<BasicInformation, String>{
	/**
	 * 保存导入的基本信息
	 * @Title: saveImportInfos 
	 * @param uploadFile
	 * @return
	 * @throws Exception 
	 * @author zhangruiling
	 * @since 2017年3月24日 V 1.0
	 */
	public String saveImportInfos(String endSuffix, File uploadFile) throws Exception;
	
	
	public Map getExportInfo(String personId);
	
	public Map getExportPDFInfo(String personId);
	
}
