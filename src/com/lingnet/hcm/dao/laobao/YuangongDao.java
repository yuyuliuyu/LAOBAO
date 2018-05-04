package com.lingnet.hcm.dao.laobao;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.laobao.StaffInfo;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.Pager;

public interface YuangongDao extends BaseDao<BasicInformation, String>{

	/**
	 * 人员list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	HashMap<String, Object> getPersonByDepId(Pager pager, String searchData);
	
	StaffInfo getStaffByJobNumber(String jobNumber);
}
