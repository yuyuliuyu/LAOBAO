package com.lingnet.hcm.service.personnel;

import java.util.HashMap;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.hcm.entity.person.PracticeCheck;
import com.lingnet.util.Pager;

/**
 * 实习生考核信息
 * service
 */
public interface CheckService extends BaseService<PracticeCheck, String>{
	public HashMap getListData(Pager pager, String searchData);
	
	public Map<String, Object> getDataByCond(Pager pager, String state,String personId);
}
