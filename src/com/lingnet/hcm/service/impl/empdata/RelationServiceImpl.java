package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.RelationDao;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.service.empdata.RelationService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("relationService")
public class RelationServiceImpl extends BaseServiceImpl<Relation, String> implements RelationService{
	
	@Resource(name = "relationDao")
	private RelationDao relationDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return relationDao.getListData(pager,searchData,depIds);
	}
	
}
