package com.lingnet.hcm.service.laobao;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.laobao.History;
import com.lingnet.util.Pager;

public interface WupinhistoryService extends BaseService<History, String>{

	public HashMap getPersonByDepId(Pager pager,String searchData,String ids);
	
	public HSSFWorkbook exportInfos();
}
