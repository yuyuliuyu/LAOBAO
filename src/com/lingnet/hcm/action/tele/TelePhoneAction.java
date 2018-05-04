package com.lingnet.hcm.action.tele;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.service.laobao.WupinhistoryService;
import com.lingnet.hcm.service.tele.TelePhoneService;
import com.lingnet.qxgl.entity.Branch;

public class TelePhoneAction extends BaseAction {

	@Resource(name = "telephoneService")
	private TelePhoneService telephoneService;

	private String ids;
	private String departId;
	private Branch branch2;
	private String id;
	
	
	
	
	
	//删除
	public String delete(){

		try {
			/*将实现方法放到del中*/
			telephoneService.delete(ids);
		} catch (Exception e) {
			return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
		}
		return ajax(Status.success, "success");


	}

}
