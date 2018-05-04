package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.HealthDao;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Health;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.HealthService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("healthService")
public class HealthServiceImpl extends BaseServiceImpl<Health, String> implements HealthService{
	@Resource(name = "healthDao")
	private HealthDao healthDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return healthDao.getListData(pager,searchData,depIds);
	}
}
