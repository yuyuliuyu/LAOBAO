package com.lingnet.hcm.service.laobao;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.laobao.Phone;
import com.lingnet.util.Pager;


public interface WupinInputService extends BaseService<Phone, String> {
	String saveImportInfos(String endSuffix, File uploadFile) throws Exception;
	public HashMap getPersonByDepId(Pager pager,String searchData,String ids);
	@SuppressWarnings("rawtypes")
	List<HashMap> getListData(Pager pager);

}
