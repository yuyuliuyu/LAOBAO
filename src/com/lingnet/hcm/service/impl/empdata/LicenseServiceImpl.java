package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.AwardDao;
import com.lingnet.hcm.dao.empdata.LicenseDao;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.License;
import com.lingnet.hcm.service.empdata.AwardService;
import com.lingnet.hcm.service.empdata.LicenseService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 证照service实现类
 */
@Service("licenseService")
public class LicenseServiceImpl extends BaseServiceImpl<License, String> implements LicenseService{
	@Resource(name = "licenseDao")
	private LicenseDao licenseDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return licenseDao.getListData(pager,searchData,depIds);
	}
}
