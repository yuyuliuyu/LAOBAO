package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.AwardDao;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.service.empdata.AwardService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("awardService")
public class AwardServiceImpl extends BaseServiceImpl<Award, String> implements AwardService{
	@Resource(name = "awardDao")
	private AwardDao awardDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return awardDao.getListData(pager,searchData,depIds);
	}
}
