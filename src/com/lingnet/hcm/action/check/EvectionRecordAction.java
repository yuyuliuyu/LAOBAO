package com.lingnet.hcm.action.check; 

import java.util.Date;
import java.util.List; 
import java.util.Map;

import javax.annotation.Resource; 

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction; 
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.check.CkEvectionRecord;
import com.lingnet.hcm.service.check.EvectionRecordService;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.qxgl.service.WorkFlowChildService;
import com.lingnet.qxgl.service.WorkFlowService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
/**
 * 
 * @ClassName: EvectionRecordAction 
 * @Description: 出差管理控制层 
 * @author wangqiang
 * @date 2017年5月3日 上午8:22:44 
 *
 */
public class EvectionRecordAction extends BaseAction{
    
	private static final long serialVersionUID = -7905485284347723475L;
	
	private String id;
    private CkEvectionRecord evection;
    private String jsondate;
    private String advid;
    private String state;//页面选择（0审核）
    private List<CkEvectionRecord> evectionlist;

	@Resource(name = "adminService")
    private AdminService adminService;
    
    @Resource(name = "workflowservice")
    private WorkFlowService workflowservice;
    
    @Resource(name = "evectionRecordService")
    private EvectionRecordService evectionRecordService;
    
    @Resource(name = "workFlowChildService")
    private WorkFlowChildService workFlowChildService;
    
    /**
     * 跳转到出差申请列表页
     */
    public String list(){
        return "list";
    }
    
    /**
     * 获取出差申请信息集合
     * @Title: jsonlist 
     * @return 
     * @author wangqiang
     * @since 2017年5月3日 V 1.0
     */
    public String jsonlist(){
    	Map<String, Object> result = evectionRecordService.getDataByCond(pager, state);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
    }
    
    /**
     * 添加/编辑员工出差申请信息
     */
    public String add(){
        if (id == null || "".equals(id.trim())){
            evection = new CkEvectionRecord();  
            evection.setEmpId(LingUtil.userinfo().getId());//qxuser的ID
            evection.setJobNumber(LingUtil.userinfo().getUsername());
            evection.setApplyName(LingUtil.userinfo().getName());
        }else{
            evection=this.getBeanById(CkEvectionRecord.class, id);
        }
        return "add";
    }
    
    /**
     * 查看出差申请信息
     * @Title: view 
     * @return 
     * @author wangqiang
     * @since 2017年5月3日 V 1.0
     */
    public String view(){
    	evection = evectionRecordService.get(id);
    	return "view";
    }
    
    /**
     * 保存/修改出差申请流程
     */
    public String saveOrUpdate(){
        if (jsondate==null||"".equals(jsondate.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        evection = JsonUtil.toObject(jsondate, CkEvectionRecord.class);
        if (evection == null){
            return ajax(Status.success,"数据错误，请联系管理员");
        } else if (evection.getId() == null || "".equals(evection.getId().trim())){
        	evection.setApplyDate(new Date());
        	evection.setAuditStatus(0);//待审核状态
        	evection.setIsDelete(0);
            this.save(evection);
            return ajax(Status.success,"保存成功");
        } else {
        	CkEvectionRecord record = evectionRecordService.get(evection.getId());
        	record.setStartDate(evection.getStartDate());
        	record.setEndDate(evection.getEndDate());
        	record.setEvectionDay(evection.getEvectionDay());
        	record.setEvectionType(evection.getEvectionType());
        	if (!record.getProcessId().equals(evection.getProcessId())){
        		record.setNodeId(null);//更换流程后，初始节点
        	}
        	record.setProcessId(evection.getProcessId());
        	record.setRemark(evection.getRemark());
        	record.setAuditStatus(0);//恢复待审核状态
            this.update(record);
            return ajax(Status.success,"修改成功");
        }
    }
    
    /**
     * 查询申请的审核状态
     * @Title: getAuditStatus 
     * @return 
     * @author wangqiang
     * @since 2017年5月3日 V 1.0
     */
    public String getAuditStatus(){
    	CkEvectionRecord record = evectionRecordService.get(id);
    	String auditStatus = "";
    	if (record != null){
    		auditStatus = record.getAuditStatus()+"";
    	}
    	String json = JsonUtil.Encode(auditStatus);
        return ajax(Status.success, json);
    }
    
    /**
     * 删除申请信息
     * @Title: remove 
     * @return 
     * @author wangqiang
     * @since 2017年5月3日 V 1.0
     */
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else{
            try {
                this.deleteByCriteria(CkEvectionRecord.class, id);
                return ajax(Status.success,"删除成功");
            } catch (Exception e) {
                return ajax(Status.success,"数据错误，请联系管理员");
            }
        }
    }
    /**
     * 将审批提报上级
     * @Title: giveapp 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月15日 V 1.0
     */
    public String nextapp(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        evection=this.getBeanById(CkEvectionRecord.class, id);
        if(evection==null){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        if(advid==null||"".equals(advid)){
            evection.setNodeId(this.getBeanByCriteria(WorkFlowChild.class, Restrictions.eq("pid", evection.getProcessId()),Restrictions.eq("sort", 1)).getId());
        }else{
            evection.setNodeId(advid);  //审批流程节点
        }
        evection.setField1(state);  //当前审批人
        this.update(evection);
        return ajax(Status.success,"提报成功");
    }
    
    /**
     * 出差查看页面
     * @Title: looklist 
     * @return 
     * String 
     * @author zrl
     * @since 2017年4月15日 V 1.0
     */
    public String looklist(){
        return "looklist";
    }
    
    /**
     * 获取数据
     * @Title: getListData 
     * @return 
     * String 
     * @author zrl
     * @since 2017年4月15日 V 1.0
     */
    public String getListData() {
        return ajax(Status.success, JsonUtil.Encode(evectionRecordService.getListData(pager,searchData)));
    }
    /*---------------------------------------------*/
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public CkEvectionRecord getEvection() {
        return evection;
    }
    public void setEvection(CkEvectionRecord evection) {
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
    public List<CkEvectionRecord> getEvectionlist() {
        return evectionlist;
    }
    public void setEvectionlist(List<CkEvectionRecord> evectionlist) {
        this.evectionlist = evectionlist;
    } 
    public String getState() {
        return state;
    } 
    public void setState(String state) {
        this.state = state;
    } 
}
