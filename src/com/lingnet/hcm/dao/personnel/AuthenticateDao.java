package com.lingnet.hcm.dao.personnel;

import java.util.HashMap;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.util.Pager;
/**
 *	奖励Dao
 */
public interface AuthenticateDao extends BaseDao<PracticeAuthenticate, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData);
}
