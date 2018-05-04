package com.lingnet.hcm.action.post;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.PositionGrade;
import com.lingnet.hcm.entity.PostPosition;
import com.lingnet.hcm.service.post.PostService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.qxgl.service.BranchService;
import com.lingnet.qxgl.service.QxcendepService;
/**
 * 职位action
 * @ClassName: BranchAction 
 * @Description: TODO 
 * @author 
 * @date 2016-8-3 下午1:50:42 
 *
 */
@Controller
public class PositionAction extends BaseAction{
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
    
    private PostPosition postPosition;
    private DeptPosition deptPosition;
    
    @Resource(name="postService")
    private PostService postService;
    
    private PositionGrade positionGrade;
    private String id;
    private String name;
    private String pid;//父id
    private String child;//子节点
    private Branch branch;//
    private String pname;//父名称
    private String formdata;
    private String cid;//分中心id
    private String did;//部门id
    private String flg;//
    
    /**
     * 转列表页面
     */
    public String list(){
        return "list";
    }
    /**
     * 添加页面
     */
    public String add(){
        deptPosition = new DeptPosition();
        deptPosition.setDeptId(cid);
        return "add";
    }

    /**
     * 列表页面
     * @Title: listData 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月31日 V 1.0
     */
    public String listData(){
        String json = postService.positionDeptList(id,pager);
        return ajax(Status.success, json);
    }
    /**
     * 部门岗位选择页
     * @return
     */
    public String show(){
        return "show";
    }
    
   
    
    /**
     * 岗位编辑
     */
    public String edit() {
        deptPosition = postService.get(DeptPosition.class, eq("id",id));
        if(deptPosition!=null){
            positionGrade = postService.get(PositionGrade.class, deptPosition.getPositionGrad());
            postPosition = postService.get(PostPosition.class, deptPosition.getPositionId());
        }
        
        return "add";
    }
    
    /**
     * 岗位查看
     */
    public String look() {
        deptPosition = postService.get(DeptPosition.class, eq("id",id));
        if(deptPosition!=null){
            positionGrade = postService.get(PositionGrade.class, deptPosition.getPositionGrad());
            postPosition = postService.get(PostPosition.class, deptPosition.getPositionId());
        }
        return "add";
    }
    
    
    /**
     * 岗位的增加和修改
     * @Title: postionSave 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月30日 V 1.0
     */
    public String postionSave(){
        try {
            String flg = postService.postionDeptSave(formdata);
            if (flg.equals("success")) {
                return ajax(Status.success, "success");
            } else {
                return ajax(Status.error, flg);
            }
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    
    }
    
    public String delete(){
        String json = postService.postionDeptDelete(id);
        return ajax(Status.success, json);
    
    }
    
    /**
     * 根据职务族查询部门岗位
     * @Title: positionList 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月7日 V 1.0
     */
    public String positionList(){
        String json = postService.depPositionList(id,pager);
        return ajax(Status.success, json);
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
    public PostPosition getPostPosition() {
        return postPosition;
    }
    public void setPostPosition(PostPosition postPosition) {
        this.postPosition = postPosition;
    }
    public DeptPosition getDeptPosition() {
        return deptPosition;
    }
    public void setDeptPosition(DeptPosition deptPosition) {
        this.deptPosition = deptPosition;
    }
    public PositionGrade getPositionGrade() {
        return positionGrade;
    }
    public void setPositionGrade(PositionGrade positionGrade) {
        this.positionGrade = positionGrade;
    }
    public String getFlg() {
        return flg;
    }
    public void setFlg(String flg) {
        this.flg = flg;
    }
  
}
