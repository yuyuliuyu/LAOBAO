package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.EmpSkillDao;
import com.lingnet.hcm.dao.empdata.SkilledEmpDao;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.EmpSkill;
import com.lingnet.hcm.entity.person.SkilledEmp;
import com.lingnet.hcm.service.empdata.CareerService;
import com.lingnet.hcm.service.empdata.EmpSkillService;
import com.lingnet.hcm.service.empdata.SkilledEmpService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 技工信息service实现类
 */
@Service("skilledEmpService")
public class SkilledEmpServiceImpl extends BaseServiceImpl<SkilledEmp, String> implements SkilledEmpService{
	@Resource(name = "skilledEmpDao")
	private SkilledEmpDao skilledEmpDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return skilledEmpDao.getListData(pager,searchData,depIds);
	}
	
}
