package com.lingnet.qxgl.action.system; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.PostPosition;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.WorkFlowPrent;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.service.WorkFlowService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
@SuppressWarnings("serial")
public class WorkFlowAction extends BaseAction{
     private String pid;
     private String id;
     private String nodeid;
     private WorkFlowPrent workflowp;
     private WorkFlowChild workflowc;
     private String jsondate;
     private String type;
     private String companyId;

     @Resource(name = "workflowservice")
     private WorkFlowService workflowservice;
     /**
      * 
      * @Title: list 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
     public String list(){
         return "list";
     }
     @SuppressWarnings({ "unchecked", "rawtypes" })
    public String getworkflow(){
         String compid=LingUtil.userinfo().getCid(); 
    	 List<WorkFlowPrent> parentlist = this.getBeanListByCriteria(WorkFlowPrent.class, 
    	         Restrictions.eq("type",type),
    	         Restrictions.or(Restrictions.eq("compney",compid), Restrictions.isNull("compney")),
    	         Restrictions.eq("state", 1));
        Map result = new HashMap();
         List<Map<String, String>> list = new ArrayList<Map<String, String>>(); 
         if(parentlist!=null && parentlist.size()>0){
             for (int i = 0; i < parentlist.size(); i++) {
                 workflowp=parentlist.get(i);
                 Map<String, String> map = new HashMap<String, String>(); 
                 map.put("id",workflowp.getId()); 
                 map.put("text", workflowp.getName());
                 list.add(map);
            }
         }
         result.put("data", list);
         result.put("total", pager.getTotalCount());
         String json = JsonUtil.Encode(result);
         return ajax(Status.success,json);
     }
     /**
      * 
      * @Title: jsonlist 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String jsonlist(){
         try {
            pager=this.findPager(WorkFlowPrent.class, pager);
        } catch (Exception e) {
            e.printStackTrace();
        }
         Map result = new HashMap();
         List<Map<String, String>> list = new ArrayList<Map<String, String>>();
         List<WorkFlowPrent> parentlist=(List<WorkFlowPrent>) pager.getResult();
         if(parentlist!=null&&parentlist.size()>0){
             for (int i = 0; i < parentlist.size(); i++) { 
                 workflowp=parentlist.get(i);
                 Map<String, String> map = new HashMap<String, String>();
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 map.put("id",workflowp.getId());
                 map.put("name",workflowp.getName());
                 map.put("type", workflowp.getType());
                 map.put("createDate", sdf.format(workflowp.getCreateDate()));
                 map.put("usingState", workflowp.getState()+"");
                 map.put("processDes", workflowp.getMemo()); 
                 list.add(map);
            }
         }
         result.put("data", list);
         result.put("total", pager.getTotalCount());
         String json = JsonUtil.Encode(result);
         return ajax(Status.success,json);
     }
     /**
      * 获取工作流子节点信息
      * @Title: getDataByParentId 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月10日 V 1.0
      */
     @SuppressWarnings({ "rawtypes", "unchecked" })
     public String getDataByParentId(){
         if(id==null||"".equals(id.trim())){ 
                     return ajax(Status.success, "");
         }else{
             try {
                pager=this.findPagerByOrder(WorkFlowChild.class, pager, Order.asc("sort"), Restrictions.eq("pid",id));
            } catch (Exception e) {
                e.printStackTrace();
            }
         }
         Map result = new HashMap();
         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
         List<WorkFlowChild> childlist=(List<WorkFlowChild>) pager.getResult(); 
         for (int i = 0; i < childlist.size(); i++) { 
             workflowc=childlist.get(i);
             Map<String, Object> map = new HashMap<String, Object>();
             map.put("sort", workflowc.getSort());
             map.put("appman", workflowc.getAppman());
//             map.put("appdeptname", workflowc.getAppdeptname());
             map.put("isCheckEdit", workflowc.getIsCheckEdit());
             map.put("id",workflowc.getId());
             list.add(map);
        }
         result.put("data", list);
         result.put("total", pager.getTotalCount());
         String json = JsonUtil.Encode(result);
         return ajax(Status.success, json);
     }

     /**
      * 获取工作流子节点信息
      * @Title: getDataByParentId 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月10日 V 1.0
      */
     @SuppressWarnings({ "rawtypes", "unchecked" })
     public String getDataByPId(){
         pager.setPageSize(1000);
         /*根据流程id和流程节点id，获得该流程节点可进行审批的人*/
         if(pid==null||"".equals(pid.trim())){
             return ajax(Status.success, "数据错误");
         }else{
             try {
                //pager=this.findPager(WorkFlowChild.class, pager, Restrictions.eq("pid",pid));
                pager=this.findPagerByOrder(WorkFlowChild.class, pager, Order.asc("sort"), Restrictions.eq("pid",pid));
                
             } catch (Exception e) {
                e.printStackTrace();
            }
         }
         Map result = new HashMap();
         boolean st=false;
         if(pager.getResult()!=null&&pager.getResult().size()>0){
             if(nodeid==null||"".equals(nodeid)){  
                 for (int i = 0; i < pager.getResult().size(); i++) {
                     workflowc=new WorkFlowChild();
                     workflowc=(WorkFlowChild) pager.getResult().get(i);
                     if(workflowc.getSort()==1){
                         st=true;
                         break;
                     }
                }
                 if(!st){
                     workflowc=new WorkFlowChild();
                 } 
             }else{
                 workflowc=this.getBeanById(WorkFlowChild.class, nodeid);
             }
             
         }else{ 
             workflowc=this.getBeanById(WorkFlowChild.class, pid);  
             if(workflowc==null||workflowc.getId()==null){
                 result.put("data", "");
                 result.put("total", pager.getTotalCount());
                 String json = JsonUtil.Encode(result);
                 return ajax(Status.success, json);
             }else{
                 workflowc=this.getBeanByCriteria(WorkFlowChild.class,Restrictions.eq("pid", workflowc.getPid()), Restrictions.eq("sort", (workflowc.getSort()+1)));  
             }
         }
         List<DeptPosition> deptpositlist=this.getBeanListByCriteria(DeptPosition.class, Restrictions.eq("positionId",workflowc.getAppid())); 
         List<Map<String, String>> list = new ArrayList<Map<String, String>>(); 
         for (int i = 0; i < deptpositlist.size(); i++) { 
             DeptPosition deptpost=deptpositlist.get(i);
             Map<String, String> map = new HashMap<String, String>(); 
             QxDepartment qx = this.getBeanById(QxDepartment.class, deptpost.getDeptId());
             String bName = "";
             String bNameId = "";
             String deptname = "";
             if (qx != null) {
                 Branch branch = this.getBeanByCriteria(Branch.class, Restrictions.eq("id", qx.getBarchId()));
                 if (null != branch) {
                     bNameId = branch.getId();
                     bName = branch.getFzx();
                 }
                 deptname=qx.getName();
                 
             }
             map.put("text", "公司：" + bName +";   部门："+deptname+";       岗位：" +deptpost.getPositionName());  
             map.put("curNode", workflowc.getId());// 当前节点ID
             map.put("companyId", bNameId);// 公司ID
             map.put("id", deptpost.getId());
             list.add(map);
        }
         result.put("data", list);
         result.put("total", pager.getTotalCount());
         String json = JsonUtil.Encode(result);
         return ajax(Status.success, json);
         
     }

     /**
      * @Title: 获取某个审批流程的下一个审批岗位
      * @return 
      * String 
      * @author zhanghj
      * @since 2017年7月19日 V 1.0
      */
     public String getNextCheckDept() {
         Map<String, Object> nextMap = workflowservice.getNextCheckDept(pid, nodeid);
         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
         if (nextMap != null) {
             List<DeptPosition> deptpositlist=this.getBeanListByCriteria(DeptPosition.class, Restrictions.eq("positionId",nextMap.get("aproId"))); 
             for (int i = 0; i < deptpositlist.size(); i++) { 
                 DeptPosition deptpost=deptpositlist.get(i);
                 Map<String, Object> map = new HashMap<String, Object>(); 
                 QxDepartment qx = this.getBeanById(QxDepartment.class, deptpost.getDeptId());
                 String bName = "";
                 String bNameId = "";
                 String deptname = "";
                 if (qx != null) {
                     Branch branch = this.getBeanByCriteria(Branch.class, Restrictions.eq("id", qx.getBarchId()));
                     if (null != branch) {
                         bNameId = branch.getId();
                         bName = branch.getFzx();
                     }
                     deptname=qx.getName();
                     
                 }
                 map.put("text", "公司：" + bName +";   部门："+deptname+";       岗位：" +deptpost.getPositionName());  
                 map.put("curNode", nextMap.get("nextId"));// 下一节点ID
                 map.put("companyId", bNameId);// 公司ID
                 map.put("id", deptpost.getId());
                 list.add(map);
             }
         }
         return ajax(Status.success, JsonUtil.Encode(list));
     }

     @SuppressWarnings({ "unchecked", "rawtypes" })
    public String getUserByPostposit(){
         Map result = new HashMap();
         if(pid==null||"".equals(pid.trim())){ 
             result.put("data", "");
             result.put("total", pager.getTotalCount());
             String json = JsonUtil.Encode(result);
             return ajax(Status.success, json);
         }
         List<BasicInformation> baseinfolist=this.getBeanListByCriteria(BasicInformation.class, Restrictions.eq("filmId", companyId),
                 Restrictions.eq("specificPostId", pid));

         List<Map<String, String>> list = new ArrayList<Map<String, String>>(); 
         for (int i = 0; i < baseinfolist.size(); i++) { 
             BasicInformation userinfo=baseinfolist.get(i);
             Map<String, String> map = new HashMap<String, String>();  
             map.put("text", "工号："+userinfo.getJobNumber()+";       姓名：" +userinfo.getName());  
             map.put("id", userinfo.getJobNumber());
             list.add(map);
        }
         result.put("data", list);
         result.put("total", pager.getTotalCount());
         String json = JsonUtil.Encode(result);
         return ajax(Status.success, json);
     }
     /**
      * 
      * @Title: add_prent 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
     public String add_prent(){
         return "add";
     }

     /**
      * @Title: 修改流程定义
      * @return 
      * String 
      * @author zhanghj
      * @since 2017年5月9日 V 1.0
      */
     public String edit_prent(){
         workflowp = this.getBeanByCriteria(WorkFlowPrent.class, Restrictions.eq("id", id));
         if(workflowp.getCompney()==null||"".equals(workflowp.getCompney())){
             
         }else{
             jsondate=(this.getBeanById(Branch.class, workflowp.getCompney())).getFzx();
         }
         return "add";
     }
     /**
      * 
      * @Title: add_child 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
     public String add_child(){
         workflowc=new WorkFlowChild();
         if(id==null||"".equals(id.trim())){ 
             workflowc.setPid(pid);
            Integer maxnum= workflowservice.findmaxbypid(pid);
            if(maxnum==null){
                workflowc.setSort(1);
            }else{
                workflowc.setSort(maxnum+1);
            }
         }else{
             workflowc=this.getBeanById(WorkFlowChild.class, id);
         }
         return "subadd";
     }
     /**
      * 
      * @Title: saveorupdate_p 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
     public String saveorupdate_p(){
         if(jsondate!=null||"".equals(jsondate.trim())){
             workflowp=JsonUtil.toObject(jsondate, WorkFlowPrent.class);
             if (StringUtils.isBlank(workflowp.getId())) {
                 this.save(workflowp);
             }else{
                 this.update(workflowp);
             }
         }
         return ajax(Status.success,"success");
     }
     /**
      * 
      * @Title: saveorupdate_c 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
     public String saveorupdate_c(){
         if(jsondate!=null||"".equals(jsondate.trim())){
             workflowc=JsonUtil.toObject(jsondate, WorkFlowChild.class);
             if(workflowc==null||workflowc.getPid()==null||"".equals(workflowc.getPid().trim())){ 
                 return ajax(Status.success,"未找到上级流程节点。不能添加");
             }
             /*查用户表，放入用户userid */
//             QxUser userinfo=new QxUser();
             if(workflowc.getAppid()==null||"".equals(workflowc.getAppid().trim())){
                 return ajax(Status.success,"未找到上级流程节点。不能添加");
             }else{ 
                 PostPosition postposit=new PostPosition();
                 try {
                     postposit=this.getBeanById(PostPosition.class, workflowc.getAppid()); 
                } catch (Exception e) { 
                    return ajax(Status.success,"未找到上级流程节点。不能添加");
                } 
                 if(postposit!=null){
                     workflowc.setAppman(postposit.getPositionName());
                     workflowc.setAppdeptid(null); 
                     workflowc.setAppdeptname(postposit.getPositionName()); 
                 }
             }
             if(workflowc.getId()==null||("".equals(workflowc.getId().trim()))){
                 this.save(workflowc);
             }else{
                 this.update(workflowc);
             }
         }
         return ajax(Status.success,"success");
     }
     /**展示选择上级提报用户的页面
      * @Title: showuser 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月11日 V 1.0
      */
     public String showuser(){ 
         return "user";
     }
     /**
      * 删除一个主表，并循环删除相关子表信息
      * @Title: remove_p 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
     public String remove_p(){
         if(id==null||"".equals(id.trim())){
             return ajax(Status.success,"操作失败，请重新操作");
         }else{
             List<WorkFlowChild> flowclist=this.getBeanListByCriteria(WorkFlowChild.class,
                     Restrictions.eq("pid", id));
             for (int i = 0; i < flowclist.size(); i++) { 
                 this.deleteByCriteria(WorkFlowChild.class, Restrictions.eq("id", flowclist.get(i).getId()));
            }
             this.deleteByCriteria(WorkFlowPrent.class, Restrictions.eq("id", id));
         }
         return ajax(Status.success,"success");
     }
     /**
      * 删除一个子表
      * @Title: remove_c 
      * @return 
      * String 
      * @author 马晓鹏
      * @since 2017年4月15日 V 1.0
      */
     public String remove_c(){
         if(id==null||"".equals(id.trim())){
             return ajax(Status.success,"操作失败，请重新操作");
         }else{
             this.deleteByCriteria(WorkFlowChild.class, Restrictions.eq("id", id));
         }
         return ajax(Status.success,"success");
     }
      
     /*-*-*-*--------------------------------------------------------------------------------------------------------*/
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public WorkFlowPrent getWorkflowp() {
        return workflowp;
    }
    public void setWorkflowp(WorkFlowPrent workflowp) {
        this.workflowp = workflowp;
    }
    public WorkFlowChild getWorkflowc() {
        return workflowc;
    }
    public void setWorkflowc(WorkFlowChild workflowc) {
        this.workflowc = workflowc;
    }
    public String getJsondate() {
        return jsondate;
    }
    public void setJsondate(String jsondate) {
        this.jsondate = jsondate;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getNodeid() {
        return nodeid;
    }
    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
