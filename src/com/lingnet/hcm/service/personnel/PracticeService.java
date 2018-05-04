package com.lingnet.hcm.service.personnel;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.util.Pager;

/**
 * 员工技能service
 */
public interface PracticeService extends BaseService<BasicInformation, String>{
	public String saveOrUpdate(String formdata) throws Exception;
}
