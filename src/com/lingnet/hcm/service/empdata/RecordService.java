package com.lingnet.hcm.service.empdata;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.Record;
import com.lingnet.util.Pager;

/**
 * 档案service
 */
public interface RecordService extends BaseService<Record, String>{
	public HashMap getListData(Pager pager, String searchData);
	
	public String saveOrUpdata(String formdata) throws Exception;
}
