package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.PunishDao;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.Punish;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.PunishService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("punishService")
public class PunishServiceImpl extends BaseServiceImpl<Punish, String> implements PunishService{
	@Resource(name = "punishDao")
	private PunishDao punishDao;
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return punishDao.getListData(pager,searchData,depIds);
	}
}
