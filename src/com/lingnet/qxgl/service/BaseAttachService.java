package com.lingnet.qxgl.service;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.BaseAttachment;

public interface BaseAttachService extends BaseService<BaseAttachment, String>{
	
	/**
	 * 保存附件
	 * modelName 模块名称（英文）
	 * parentId  文件id
	 * img       图片路径以逗号分隔
	 * flg       0是新增，1是修改
	 * **/
	public String saveOrUpdate(String modelName,String parentId,String img,String flg);

}
