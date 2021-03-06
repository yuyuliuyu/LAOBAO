package com.lingnet.hcm.action.check;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;   
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.check.CkEvectionRecord;
import com.lingnet.hcm.entity.check.CkOvertimeRecord;
import com.lingnet.hcm.entity.check.CkOvertimeSub;
import com.lingnet.hcm.entity.check.CkRestRecord;
import com.lingnet.hcm.entity.person.PracticeCheck;
import com.lingnet.hcm.service.check.CheckInfosService;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.service.WorkFlowService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
/**
 * 
 * @ClassName: OvertimeSubAction 
 * @Description: 流程审批控制层 
 * @author mxp
 * @date 2017年5月3日 下午4:28:50 
 *
 */
public class OvertimeSubAction extends BaseAction{ 
	
    private static final long serialVersionUID = 1L;
    
    @Resource(name = "workflowservice")
    private WorkFlowService workflowservice;
    
    @Resource(name = "checkInfosService")
	private CheckInfosService checkInfosService;
        
    private String id;
    private CkOvertimeSub evection;
    private String jsondate;
    private String advid;
    private List<CkOvertimeSub> evectionlist;
    private String type;    //选中的审批流程节点类别
    private String pid;  //选中的审批流程节点id
    private String state;
    private String processId;
    private String nodeId;// 当前节点ID
    public String giveapp(){
       return "give";
    }
    public String list(){
        return "list";
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String jsonlist(){
        try {
//            this.findPager(CkOvertimeSub.class, pager, Restrictions.eq("pid", pid)); 
            pager=this.findPagerByOrder(CkOvertimeSub.class, pager, Order.asc("auditDate"), Restrictions.eq("pid", pid));
        } catch (Exception e) { 
            e.printStackTrace();
        } 
        Map result = new HashMap();
        List<Map<String, String>> retmap=new ArrayList<Map<String,String>>();
        for (int i = 0; i < pager.getResult().size(); i++) {
            Map<String, String> data=new HashMap<String, String>();
            CkOvertimeSub advasub=(CkOvertimeSub) pager.getResult().get(i);
            data.put("id", advasub.getId());
            data.put("auditName", advasub.getAuditName());
            data.put("auditDate", new SimpleDateFormat("yyyy-MM-dd").format(advasub.getAuditDate()));
            data.put("opinion", advasub.getOpinion());
            if(advasub.getAuditStatus()!=null&&advasub.getAuditStatus()==1){ 
                data.put("auditStatus", "同意");
            } else if(advasub.getAuditStatus()!=null&&advasub.getAuditStatus()==0){ 
                data.put("auditStatus", "驳回");
            }
            retmap.add(data);
        }
        result.put("data", retmap);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }
    public String add(){
        if(id==null||"".equals(id.trim())){
            evection=new CkOvertimeSub();
            QxUser userinfo=LingUtil.userinfo();
            evection.setAuditId(userinfo.getUsername());
            evection.setAuditName(userinfo.getName());
            evection.setAuditDate(new Date());
            evection.setEvectType(type);
            evection.setPid(pid); 
        }else{
            evection=this.getBeanById(CkOvertimeSub.class, id); 
        }
        PracticeCheck check = getBeanByCriteria(PracticeCheck.class, Restrictions.eq("id", pid));
        if (check != null) {
        	advid = check.getProcessId();
        	nodeId = check.getNodeId();
        }

//        if(advid==null||"".equals(advid.trim())){
//            return "error";
//        }else{
//            advid=this.getBeanById(WorkFlowChild.class, advid).getAppid();
//        }
        return "add";
    }
    
    public String saveOrUpdate(){
        if(jsondate==null||"".equals(jsondate.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        evection=JsonUtil.toObject(jsondate, CkOvertimeSub.class); 
        if(evection==null){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else if(evection.getId()==null||"".equals(evection.getId().trim())){ 
        	/*驳回，需把上级*/
        	WorkFlowChild flowchil= null;
            switch (Integer.parseInt(evection.getEvectType())) {
	            case 1: //出差管理
	            	//调用一下，转到下一个审批人的方法。
	            	CkEvectionRecord evecinfo=this.getBeanById(CkEvectionRecord.class, evection.getPid());
	                if(evection.getAuditStatus()==0){//审核不通过
	                    if(evecinfo==null){
	                        return ajax(Status.success,"数据错误，请联系管理员");
	                    }else{
	                    	evecinfo.setAuditStatus(3);//不通过1
	                        evecinfo.setNodeId(null);
	                    }
	                }else{//审核通过
	                	flowchil= workflowservice.findnextbyid(evecinfo.getNodeId());
	                	if (flowchil == null){
	                		evecinfo.setAuditStatus(2);//通过
                            evecinfo.setField1(null);
                            evecinfo.setNodeId(null);
	                		try {
	                			//修改出差日期的考勤为差
								checkInfosService.updateCheckInfoByDate("差", evecinfo.getStartDate(), evecinfo.getEndDate(), evecinfo.getJobNumber());
							} catch (Exception e) {
								e.printStackTrace();
							}
	                	} else {
	                	    evecinfo.setField1(evection.getField1());
	                	    evecinfo.setNodeId(evection.getNodeId());
	                		evecinfo.setAuditStatus(1);//审核中
	                		evecinfo.setNodeId(flowchil.getId());
	                	}
	                }
	                this.update(evecinfo);
	                break;
	            case 2: //休假管理
	            	//调用一下，转到下一个审批人的方法。
	            	CkRestRecord restinfo=this.getBeanById(CkRestRecord.class, evection.getPid());
	                if(evection.getAuditStatus()==0){//审核不通过
		                if(restinfo==null){
		                    return ajax(Status.success,"数据错误，请联系管理员");
		                }else{
		                	restinfo.setAuditStatus(3);//不通过
		                	restinfo.setNodeId(null);
		                }
	                }else{//审核通过
	                	flowchil= workflowservice.findnextbyid(restinfo.getNodeId());
	                	if (flowchil == null){
	                		restinfo.setAuditStatus(2);//通过
	                		restinfo.setField1(null);
	                		restinfo.setNodeId(null);
	                		try {
	                			//修改休假日期的考勤为休
								checkInfosService.updateCheckInfoByDate("休", restinfo.getStartDate(), restinfo.getEndDate(), restinfo.getJobNumber());
							} catch (Exception e) {
								e.printStackTrace();
							}
	                	} else {
	                	    restinfo.setField1(evection.getField1());
	                	    restinfo.setNodeId(evection.getNodeId()); 
	                		restinfo.setAuditStatus(1);//审核中
	                		restinfo.setNodeId(flowchil.getId());
	                	}
	                }
	                this.update(restinfo);
	                break;
	            case 3:
	                CkOvertimeRecord overtimeinfo=this.getBeanById(CkOvertimeRecord.class, evection.getPid());
	                if(evection.getAuditStatus()==0){//审核不通过
		                if(overtimeinfo==null){
		                    return ajax(Status.success,"数据错误，请联系管理员");
		                }else{
		                	overtimeinfo.setAuditStatus(3);//不通过
		                	overtimeinfo.setNodeId(null);
		                }
	                }else{
	                	flowchil= workflowservice.findnextbyid(overtimeinfo.getNodeId());
	                	if (flowchil == null){
	                		overtimeinfo.setAuditStatus(2);//通过
	                		overtimeinfo.setField1(null);
	                		overtimeinfo.setNodeId(null);
	                		try {
	                			//修改加班日期的考勤为加班
								checkInfosService.updateOverTimeByDate("1", overtimeinfo.getStartDate(), overtimeinfo.getEndDate(), overtimeinfo.getJobNumber());
							} catch (Exception e) {
								e.printStackTrace();
							}
	                	} else {
	                	    overtimeinfo.setField1(evection.getField1());
                            overtimeinfo.setNodeId(evection.getNodeId()); 
	                		overtimeinfo.setAuditStatus(1);//审核中
	                		overtimeinfo.setNodeId(flowchil.getId());
	                	}
	                }
	                this.update(overtimeinfo);
	                break;
	                
	            case 10:
	            	PracticeCheck pche=this.getBeanById(PracticeCheck.class, evection.getPid());
	                if(evection.getAuditStatus()==0){//审核不通过
		                if(pche==null){
		                    return ajax(Status.success,"数据错误，请联系管理员");
		                }else{
		                	pche.setAuditStatus(3);//不通过
		                	pche.setNodeId(null);
		                }
	                }else{
	                	flowchil= workflowservice.findnextbyid(pche.getNodeId());
	                	if (flowchil == null){
	                		pche.setAuditStatus(2);//通过
	                		pche.setRemark(null);
	                		pche.setNodeId(null);
	                	} else {
	                		pche.setRemark(evection.getField1());
	                		pche.setNodeId(evection.getNodeId()); 
	                		pche.setAuditStatus(1);//审核中
	                		pche.setNodeId(flowchil.getId());
	                	}
	                	/*pche.setDepEvaluate(evection.getOpinion());*/
	                }
	                this.update(pche);
	                break;
	            default:
	                return ajax(Status.success,"数据错误，请联系管理员"); 
            } 
            evection.setIsDelete(0);
            this.save(evection);
        }else{
            ///暂时没设计修改意见的地方。。 
            this.update(evection);
        }
        return ajax(Status.success,"保存成功");
    }
    
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else{
            try {
                this.deleteByCriteria(CkOvertimeSub.class, id);
                return ajax(Status.success,"删除成功");
            } catch (Exception e) {
                return ajax(Status.success,"数据错误，请联系管理员");
            }
        }
    }
    
 
/*/************************************************************************/
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public CkOvertimeSub getEvection() {
        return evection;
    }
    public void setEvection(CkOvertimeSub evection) {
        this.evection = evection;
    }
    public String getJsondate() {
        return jsondate;
    }
    public void setJsondate(String jsondate) {
        this.jsondate = jsondate;
    }
    public String getAdvid() {
        return advid;
    }
    public void setAdvid(String advid) {
        this.advid = advid;
    }
    public List<CkOvertimeSub> getEvectionlist() {
        return evectionlist;
    }
    public void setEvectionlist(List<CkOvertimeSub> evectionlist) {
        this.evectionlist = evectionlist;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getProcessId() {
        return processId;
    }
    public void setProcessId(String processId) {
        this.processId = processId;
    }
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	} 
}
