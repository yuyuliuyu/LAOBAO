package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.AttentionDao;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.EmpSkillDao;
import com.lingnet.hcm.entity.person.Attention;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.hcm.service.empdata.AttentionService;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.EmpSkillService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("attentionService")
public class AttentionServiceImpl extends BaseServiceImpl<Attention, String> implements AttentionService{
	@Resource(name = "attentionDao")
	private AttentionDao attentionDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return attentionDao.getListData(pager,searchData,depIds);
	}
}
