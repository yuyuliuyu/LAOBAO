package com.lingnet.hcm.dao.laobao;

import java.util.HashMap;

import com.lingnet.util.Pager;

public  interface WupinhistoryDao {
	 HashMap<String, Object> getPersonByDepId(Pager pager, String searchData,String ids);
}
