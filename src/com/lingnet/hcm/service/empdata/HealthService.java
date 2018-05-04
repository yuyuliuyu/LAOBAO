package com.lingnet.hcm.service.empdata;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Health;
import com.lingnet.util.Pager;

/**
 * 工健康档案service
 */
public interface HealthService extends BaseService<Health, String>{
	public HashMap getListData(Pager pager, String searchData);
}
