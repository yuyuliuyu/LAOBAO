package com.lingnet.hcm.service.laobao;

import java.io.File;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.laobao.Phone;

public interface WupinInputService extends BaseService<Phone, String> {
	String saveImportInfos(String endSuffix, File uploadFile) throws Exception;

}
