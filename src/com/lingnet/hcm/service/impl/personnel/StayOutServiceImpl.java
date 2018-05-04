package com.lingnet.hcm.service.impl.personnel;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.personnel.StayOutDao;
import com.lingnet.hcm.entity.person.StayOut;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.StayOutService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("stayOutService")
public class StayOutServiceImpl extends BaseServiceImpl<StayOut, String> implements StayOutService{
	
	@Resource(name = "stayOutDao")
	private StayOutDao stayOutDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
	    return stayOutDao.getListData(pager, searchData,depIds);
	}
}
