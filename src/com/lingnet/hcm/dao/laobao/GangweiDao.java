package com.lingnet.hcm.dao.laobao;

import java.util.HashMap;
import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.Pager;

public interface GangweiDao extends BaseDao<BasicInformation, String>{

	
	public List getUsersByRoleId(Pager pager,String roleId);
	public HashMap getPersonByDepId(Pager pager, String searchData);
}
