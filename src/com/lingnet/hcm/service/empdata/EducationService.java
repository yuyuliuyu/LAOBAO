package com.lingnet.hcm.service.empdata;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.Education;
import com.lingnet.util.Pager;

/**
 * 工作履历service
 */
public interface EducationService extends BaseService<Education, String>{
	public HashMap getListData(Pager pager, String searchData);
}
