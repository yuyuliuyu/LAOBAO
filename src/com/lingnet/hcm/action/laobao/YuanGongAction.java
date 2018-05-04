package com.lingnet.hcm.action.laobao;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.laobao.StaffInfo;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.laobao.YuangongService;
import com.lingnet.util.JsonUtil;

public class YuanGongAction extends BaseAction{
	
	@Resource(name="yuangongService")
    private YuangongService yuangongService;

    public String list(){
        return "list";
    }
    
    //获取人员list列表数据
    public String getPersonData(){
    	String josn=JsonUtil.Encode(yuangongService.getPersonByDepId(pager,searchData));
    	System.out.println(pager+"    "+searchData);
    	return ajax(Status.success,josn);
    }
    
    private BasicInformation information;
    
    private StaffInfo staffinfo;
    
  //人事资料添加页
    public String add(){
    	information=this.getBeanById(BasicInformation.class, id);
/*    	staffinfo=yuangongService.getStaffByJobNumber(job_number);
    	System.out.println(id);*/
        return "add";
    }
   // 申请劳保物品
    public String reply(){
    	information=this.getBeanById(BasicInformation.class, id);
/*    	staffinfo=yuangongService.getStaffByJobNumber(job_number);
    	System.out.println(id);*/
        return "reply";
    }
    /**
     * 删除
     */
    public String delete(){
        try {
            /*将实现方法放到del中*/
        	yuangongService.delete(id);
        } catch (Exception e) {
            return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
        }
        return ajax(Status.success, "success");
    }
    
    
    
	public BasicInformation getInformation() {
		return information;
	}
	public void setInformation(BasicInformation information) {
		this.information = information;
	}

	public StaffInfo getStaffinfo() {
		return staffinfo;
	}

	public void setStaffinfo(StaffInfo staffinfo) {
		this.staffinfo = staffinfo;
	}
	
}
