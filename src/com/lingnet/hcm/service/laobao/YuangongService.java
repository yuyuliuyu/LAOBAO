package com.lingnet.hcm.service.laobao;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.laobao.StaffInfo;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.Pager;

public interface YuangongService extends BaseService<BasicInformation, String>{

	/**
	 *  人员list列表
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap getPersonByDepId(Pager pager,String searchData);
	
	public StaffInfo getStaffByJobNumber(String jobNumber);

	public String saveOrUpdate(String formdata, String branchId) throws Exception;
}
