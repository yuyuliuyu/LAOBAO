package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.RelationDao;
import com.lingnet.hcm.dao.empdata.SoldierBackDao;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.entity.person.SoldierBack;
import com.lingnet.hcm.service.empdata.RelationService;
import com.lingnet.hcm.service.empdata.SoldierBackService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 军人复转service实现类
 */
@Service("soldierBackService")
public class SoldierBackServiceImpl extends BaseServiceImpl<SoldierBack, String> implements SoldierBackService{
	
	@Resource(name = "soldierBackDao")
	private SoldierBackDao soldierBackDao;
	
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return soldierBackDao.getListData(pager,searchData,depIds);
	}
	
}
