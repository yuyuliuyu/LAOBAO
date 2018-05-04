package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.InjuryDao;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Injury;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.InjuryService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("injuryService")
public class InjuryServiceImpl extends BaseServiceImpl<Injury, String> implements InjuryService{
	@Resource(name = "injuryDao")
	private InjuryDao injuryDao;
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return injuryDao.getListData(pager,searchData,depIds);
	}
}
