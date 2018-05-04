package com.lingnet.hcm.service.personnel;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.util.Pager;

/**
 * 员工技能service
 */
public interface ChangeService extends BaseService<Change, String>{
	public HashMap getListData(Pager pager, String searchData);
	
	public String saveOrUpdate(String formdata) throws Exception;
	
	public String saveOrUpdateLigang(String formdata,String username,String isEnd) throws Exception;
	
	public String saveOrUpdateOnpost(String formdata) throws Exception;
}
