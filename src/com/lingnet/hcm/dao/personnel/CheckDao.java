package com.lingnet.hcm.dao.personnel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.hcm.entity.person.PracticeCheck;
import com.lingnet.util.Pager;
/**
 *	考核信息Dao
 */
public interface CheckDao extends BaseDao<PracticeCheck, String>{
	public HashMap<String, Object> getListData(Pager pager, String searchData);
	
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId, String jobNumber, String state,String personId);
}
