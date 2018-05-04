package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.empdata.EducationDao;
import com.lingnet.hcm.entity.person.Education;
import com.lingnet.hcm.service.empdata.EducationService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("educationService")
public class EducationServiceImpl extends BaseServiceImpl<Education, String> implements EducationService{
	@Resource(name = "educationDao")
	private EducationDao educationDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return educationDao.getListData(pager,searchData,depIds);
	}
}
