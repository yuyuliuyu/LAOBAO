package com.lingnet.hcm.service.empdata;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.Attention;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.util.Pager;

/**
 * 员工技能service
 */
public interface AttentionService extends BaseService<Attention, String>{
	public HashMap getListData(Pager pager, String searchData);
}
