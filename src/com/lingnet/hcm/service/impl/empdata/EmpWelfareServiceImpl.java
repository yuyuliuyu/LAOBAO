package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.EmpWelfareDao;
import com.lingnet.hcm.entity.person.EmpWelfare;
import com.lingnet.hcm.service.empdata.EmpWelfareService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 工作履历service实现类
 */
@Service("empWelfareService")
public class EmpWelfareServiceImpl extends BaseServiceImpl<EmpWelfare, String> implements EmpWelfareService{
	@Resource(name = "empWelfareDao")
	private EmpWelfareDao empWelfareDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return empWelfareDao.getListData(pager,searchData,depIds);
	}
}
