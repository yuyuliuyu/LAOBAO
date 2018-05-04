package com.lingnet.hcm.service.empdata;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.hcm.entity.person.Talk;
import com.lingnet.util.Pager;

/**
 * 员工技能service
 */
public interface TalkService extends BaseService<Talk, String>{
	public HashMap getListData(Pager pager, String searchData);
}
