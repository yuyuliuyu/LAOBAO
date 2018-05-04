package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.EmpSkillDao;
import com.lingnet.hcm.dao.empdata.TalkDao;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.hcm.entity.person.Talk;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.EmpSkillService;
import com.lingnet.hcm.service.empdata.TalkService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("talkService")
public class TalkServiceImpl extends BaseServiceImpl<Talk, String> implements TalkService{
	@Resource(name = "talkDao")
	private TalkDao talkDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return talkDao.getListData(pager,searchData,depIds);
	}
	
}
