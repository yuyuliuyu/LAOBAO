package com.lingnet.hcm.service.impl.personnel;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.AwardDao;
import com.lingnet.hcm.dao.personnel.AuthenticateDao;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.hcm.service.empdata.AwardService;
import com.lingnet.hcm.service.personnel.AuthenticateService;
import com.lingnet.util.Pager;

/**
 * 实习生鉴定信息
 * service实现类
 */
@Service("authenticateService")
public class AuthenticateServiceImpl extends BaseServiceImpl<PracticeAuthenticate, String> implements AuthenticateService{
	@Resource(name = "authenticateDao")
	private AuthenticateDao authenticateDao;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		return authenticateDao.getListData(pager,searchData);
	}
}
