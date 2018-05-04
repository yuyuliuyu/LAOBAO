package com.lingnet.qxgl.service.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.qxgl.entity.BaseAttachment;
import com.lingnet.qxgl.service.BaseAttachService;
import com.lingnet.util.ToolUtil;

@Service("baseAttachService")
public class BaseAttachServiceImpl extends
					BaseServiceImpl<BaseAttachment, String> implements BaseAttachService {
	
	
	/**
	 * 保存附件
	 * modelName 模块名称（英文）
	 * parentId  文件id
	 * img       图片路径以逗号分隔
	 * flg       0是新增，1是修改
	 * **/
	public String saveOrUpdate(String modelName,String parentId,String img,String flg){
		
		if(flg!=null&&"1".equals(flg)){
			this.deleteByCriteria(BaseAttachment.class, 
					Restrictions.eq("modelName", modelName),Restrictions.eq("parentId",parentId));
		}
		
		if(img.indexOf("ueditor")!=-1){
            String imgs[] = img.split(",");
            if(img!=null&&img.length()>0){
            	for(int i=0;i<imgs.length;i++){
                	BaseAttachment att = new BaseAttachment();
                	att.setModelName(modelName);
                	att.setParentId(parentId);
                	att.setSort(i+1);
                	if(imgs[i]!=null&&imgs[i].indexOf("ueditor")!=-1){
                		int a = imgs[i].indexOf("ueditor");
                		att.setPath(imgs[i].substring(a-1,imgs[i].length()));
                		String type = imgs[i].substring(imgs[i].lastIndexOf(".")+1);
                    	att.setType(type);
                    	att.setCreatId(ToolUtil.userName());
                    	this.save(att);
                	}
                }
            }
        }else{
        	if(img!=null&&img.length()>0){
        		String imgs[] = img.split(",");
        		for(int i=0;i<imgs.length;i++){
                	BaseAttachment att = new BaseAttachment();
                	att.setModelName(modelName);
                	att.setParentId(parentId);
                	att.setSort(i+1);
                	att.setPath(imgs[i]);
                	String type = imgs[i].substring(imgs[i].lastIndexOf("/."));
                	att.setType(type);
                	att.setCreatId(ToolUtil.userName());
                    this.save(att);
                }
        	}
            
        }
		
		return "success";
	}

}
