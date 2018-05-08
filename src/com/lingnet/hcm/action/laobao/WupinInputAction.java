package com.lingnet.hcm.action.laobao;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.laobao.Phone;


import com.lingnet.hcm.service.laobao.WupinInputService;
import com.lingnet.hcm.service.personnel.PersonnelUploadService;
import com.lingnet.util.JsonUtil;


public class WupinInputAction extends BaseAction {
	
	
    private File uploadFile;// 导入的文件
    private Phone info;
    @Resource(name="wupinInputService")
    private WupinInputService wupinInputService;
	
    /**
     * 上传页面
     */
    public String upload(){
        return "upload";
    }
	// 列表查询
	@SuppressWarnings({ "rawtypes" })
	public String getListData() {
		List<HashMap> data = wupinInputService.getListData(pager);
		return ajax(Status.success, JsonUtil.Encode(data));
	}
	// 获取人员list列表数据
	public String getPersonData() {
		String josn = JsonUtil.Encode(wupinInputService.getPersonByDepId(
				pager, searchData,ids));
		System.out.println(pager + "    " + searchData);
		return ajax(Status.success, josn);
	}
 /*   *//**
     * 导入人员基本信息
     * @Title: importSave 
     * @return 
     * @author  
     * @since 2017年3月24日 V 1.0
     */
  public String importSave() {
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
        // 获取上传文件的名字
        String[] fileName = wrapper.getFileNames("uploadFile");
        if (fileName.length == 0) {
            return ajax(Status.success, "请选择上传文件！");
        }
        // 文件后缀
        String endSuffix= fileName[0].substring(fileName[0].lastIndexOf("."), fileName[0].length());
        if (!endSuffix.toLowerCase().endsWith("xls")
                && !endSuffix.toLowerCase().endsWith("xlsx")) {
                return ajax(Status.success, "请选择正确的文件类型，必须是以.xls或.xlsx结尾！");
        }
        try {
            String message = wupinInputService.saveImportInfos(endSuffix, uploadFile);
            String msg = "";
            if (message.indexOf("@") == 0) {
                msg = message.substring(1, message.length());
            }
            ServletActionContext.getRequest().getSession().setAttribute("_repeatItem", msg);
            return ajax(Status.success, message);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.indexOf("@") == 0) {
                message = message.substring(1, message.length());
            }
            ServletActionContext.getRequest().getSession().setAttribute("_repeatItem", message);
            return ajax(Status.error, e.getMessage());
        }
    }
/*	public String importSave() {
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this
				.getRequest();
		// 获取上传文件的名字
		String[] fileName = wrapper.getFileNames("uploadFile");
		if (fileName.length == 0) {
			return ajax(Status.success, "请选择上传文件！");
		}
		// 文件后缀
		String endSuffix = fileName[0].substring(fileName[0].lastIndexOf("."),
				fileName[0].length());
		if (!endSuffix.toLowerCase().endsWith("xls")
				&& !endSuffix.toLowerCase().endsWith("xlsx")) {
			return ajax(Status.success, "请选择正确的文件类型，必须是以.xls或.xlsx结尾！");
		}
		try {
			String message =wupinInputService.saveImportInfos(endSuffix,
					uploadFile);
			String msg = "";
			if (message.indexOf("@") == 0) {
				msg = message.substring(1, message.length());
			}
			ServletActionContext.getRequest().getSession()
					.setAttribute("_repeatItem", msg);
			return ajax(Status.success, message);
		} catch (Exception e) {
			String message = e.getMessage();
			if (message.indexOf("@") == 0) {
				message = message.substring(1, message.length());
			}
			ServletActionContext.getRequest().getSession()
					.setAttribute("_repeatItem", message);
			return ajax(Status.error, e.getMessage());
		}
	}*/
	public Phone getInfo() {
		return info;
	}

}
