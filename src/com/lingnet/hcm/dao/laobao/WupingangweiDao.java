package com.lingnet.hcm.dao.laobao;

import java.util.HashMap;

import com.lingnet.util.Pager;

public interface WupingangweiDao {

	HashMap<String, Object> getPersonByDepId(Pager pager, String searchData);
}
