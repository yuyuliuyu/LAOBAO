package com.lingnet.hcm.service.impl.personnel;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.personnel.AbroadDao;
import com.lingnet.hcm.entity.person.Abroad;
import com.lingnet.hcm.service.personnel.AbroadService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.Pager;

/**
 * 出国service实现类
 */
@Service("abroadService")
public class AbroadServiceImpl extends BaseServiceImpl<Abroad, String> implements AbroadService{
	
	@Resource(name = "abroadDao")
	private AbroadDao abroadDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
	    return abroadDao.getListData(pager, searchData,depIds);
	}
}
