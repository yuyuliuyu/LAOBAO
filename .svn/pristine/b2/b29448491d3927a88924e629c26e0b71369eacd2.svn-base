package com.lingnet.hcm.action.pact;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxCenDep;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.qxgl.service.BranchService;
import com.lingnet.qxgl.service.QxcendepService;
/**
 * 试岗期管理action
 * @ClassName: BranchAction 
 * @Description: TODO 
 * @author zrl
 * @date 2016-8-3 下午1:50:42 
 *
 */
@Controller
public class PactAction extends BaseAction{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Resource(name="branchService")
    private BranchService branchService;
    @Resource(name="backendDepService")
    private BackendDepService backendDepService;
    @Resource(name="qxcendepService")
    private QxcendepService qxcendepService;
    
    private String id;
    private String name;
    private String pid;//父id
    private String child;//子节点
    private Branch branch;//
    private String pname;//父名称
    private String formdata;
    private String cid;//分中心id
    private String did;//部门id
    /**
     * 分中心树
     * @Title: tree 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-4 V 1.0
     */
    public String tree(){
        return "tree";
    }
    /**
     * 获取分中心树(部门)
     * @Title: treeList 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-3 V 1.0
     */
    public String treeList(){
        String json = "";
        json = branchService.treeList();
       return ajax(Status.success, json); 
    }
    //集体合同
    public String list(){
        return "list";
    }
    
    public String add(){
        return "add";
    }
  
    public String changeadd(){
        return "changeadd";
    }
    
    public String end(){
        return "endadd";
    }
    
    public String relieve(){
        return "relieve";
    }
    
    public String look(){
        return "look";
    }
    //集个人合同
    public String personlist(){
        return "personlist";
    }
    
    public String personadd(){
        return "personadd";
    }
  
    public String pchangeadd(){
        return "pchangeadd";
    }
    
    public String pend(){
        return "pendadd";
    }
    
    public String prelieve(){
        return "prelieve";
    }
    
    public String plook(){
        return "plook";
    }
    
    
  
    public String disposeadd(){
        return "disposeadd";
    }
  
    public String disputeadd(){
        return "disputeadd";
    }
  
    public String disputelist(){
        return "disputelist";
    }

    //总页面
    public String all(){
        return "all";
    }
   
   
//////////////////////////////////////////////////////////////////////////
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getChild() {
        return child;
    }
    public void setChild(String child) {
        this.child = child;
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getFormdata() {
        return formdata;
    }
    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }
  
}
