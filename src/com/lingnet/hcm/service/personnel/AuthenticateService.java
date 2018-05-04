package com.lingnet.hcm.service.personnel;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.util.Pager;

/**
 * 实习生鉴定信息
 * service
 */
public interface AuthenticateService extends BaseService<PracticeAuthenticate, String>{
	public HashMap getListData(Pager pager, String searchData);
}
