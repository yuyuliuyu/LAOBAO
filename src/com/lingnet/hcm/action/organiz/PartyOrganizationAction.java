package com.lingnet.hcm.action.organiz;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;  
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxCenDep;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.qxgl.service.BranchService;
import com.lingnet.qxgl.service.QxcendepService;
import com.lingnet.util.LingUtil;

/**
 * 党组织管理
 * @ClassName: StaffTurnoveAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年3月7日 下午3:36:47
 */
public class PartyOrganizationAction extends BaseAction{
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
    private String jm;//简码
    private String srm; //缩写码
    private String pid;//父id
    private String child;//子节点
    private Branch branch;//
    private String pname;//父名称
    private String formdata;
    private String cid;//分中心id
    private String did;//部门id
    private String imgpath;//图片地址
    private QxDepartment department;
    private QxCenDep cd;
    /** 
     * 分中心树
     * @Title: tree 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String tree(){
        return "tree";
    }
    /**
     * 获取分中心树
     * @Title: treeList 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String treeList(){
        String json = "";
        json = branchService.treeList();
       return ajax(Status.success, json); 
    }
    
    public String list(){
        return "list";
    }
    
    public String departadd(){
        return "info";
    }
    
    public String departadd1(){
        return "departadd1";
    }
    public String departadd11(){
        return "departadd11";
    }
    
    public String departadd2(){
        return "departadd2";
    }
    
    public String add(){
        return "add";
    }
    /**
     * 编辑
     * @Title: treeList 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String edit(){
        branch = branchService.get(id);
        String p_id = branch.getPid();
        if(!p_id.equals("-1")){
            Branch pb = branchService.get(branch.getPid());
            pname = pb.getFzx();
        }else{
          pname = "ROOT";  
        }
        
        return "add";
    }
    /** 
     * 获取分中心数据
     * @Title: getTreedata 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String getTreedata(){
        String json = "";
        json = branchService.getTreedata(pager);
        return ajax(Status.success, json);
    }
 
    /**
     * 更新
     * @Title: updata 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    /*public String saveOrUpdate(){
        String flg;
        try {
            flg = branchService.saveOrUpdata(formdata,child);
            if(flg.equals("success")){
                return ajax(Status.success, "success");
            }else{
                return ajax(Status.error,flg);
            }
        } catch (Exception e) {
            return ajax(Status.error,e.getMessage());
        }
        
    }*/
    /**
     * 删除
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String remove(){
        String json = "";
        try {
            json = branchService.remove(id);
        } catch (Exception e) {
           return ajax(Status.error, e.getMessage());
        }
        return ajax(Status.success, json);
        
    }
    /**
     * 部门分中心展现
     * @Title: DepList 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String depList(){
        return "dep_list";
    }
    /**
     * 部门分中心增加
     * @Title: DepAdd 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String depAdd(){
        department = new QxDepartment();
        cd = new QxCenDep();
        imgpath="";
        return "dep_add";
    }
    /**
     * 部门分中心编辑
     * @Title: DepEdit 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String depEdit(){
        department = backendDepService.get(QxDepartment.class, Restrictions.eq("id", did));
        cd = qxcendepService.get(QxCenDep.class, Restrictions.eq("id", id));
        if(department!=null){
            String pid = department.getParent();
            if(!pid.equals("-1")){
                QxDepartment d = backendDepService.get(QxDepartment.class,pid );
                pname = d.getName();
            }else{
                pname="ROOT";
            }
           
        }
        id = cd==null?"":cd.getId();
        imgpath = cd==null?"":cd.getImgpath();
        if(StringUtils.isBlank(imgpath)){
            imgpath = department==null?"":department.getImgpath();
        }
        return "dep_add";
    }
    /**
     * 部门分中心删除
     * @Title: DepRemove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String depRemove(){
        String json = "";
        try {
            json = branchService.depRemove(id,cid);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error, e.getMessage());
        }
        
        return ajax(Status.success, json);
    }
    /**
     * 获取分中心的部门
     * @Title: depData 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String depData(){
        String json ="";
        try {
            json = branchService.depData(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return ajax(Status.success, json);
    }
    /**
     * 保存分中心部门表
     * @Title: cenDepSaveOrUpdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String cenDepSaveOrUpdate(){
        String flg="";
        QxUser userinfo=LingUtil.userinfo();
        try {
            flg = branchService.cenDepSaveOrUpdate(formdata,userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error,e.getMessage());
        }
        return ajax(Status.success,flg);
    }
    /**
     * 
     * @Title: setDeafult 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月7日 V 1.0
     */
    public String setDefault(){
        String msg="";
        try {
            msg=branchService.setDefault(id);
        } catch (Exception e) {
            e.printStackTrace();
            msg=e.getMessage();
        }
        return ajax(Status.success,msg);
    }
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
    public String getJm() {
        return jm;
    }
    public void setJm(String jm) {
        this.jm = jm;
    }
    public String getSrm() {
        return srm;
    }
    public void setSrm(String srm) {
        this.srm = srm;
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
    public QxDepartment getDepartment() {
        return department;
    }
    public void setDepartment(QxDepartment department) {
        this.department = department;
    }
    public String getImgpath() {
        return imgpath;
    }
    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }
    public QxCenDep getCd() {
        return cd;
    }
    public void setCd(QxCenDep cd) {
        this.cd = cd;
    }
}
