package com.lingnet.hcm.action.post;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.PositionGrade;
import com.lingnet.hcm.entity.PostDuty;
import com.lingnet.hcm.entity.PostPosition;
import com.lingnet.hcm.service.post.PostService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.qxgl.service.BranchService;
import com.lingnet.qxgl.service.QxcendepService;

/**
 * 标准岗位Action
 * 
 * @ClassName: PostAction
 * @Description: 标准岗位Action
 * @author duanjj
 * @date 2017年3月28日 下午3:08:33
 *
 */
@Controller
public class PostAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Resource(name = "branchService")
    private BranchService branchService;
    @Resource(name = "backendDepService")
    private BackendDepService backendDepService;
    @Resource(name = "qxcendepService")
    private QxcendepService qxcendepService;

    private PostDuty postDuty;
    @Resource(name = "postService")
    private PostService postService;
    
    private PostPosition postPosition;
    private DeptPosition deptPosition;
    private PositionGrade positionGrade;
    private String name;
    private String pid;// 父id
    private String child;// 子节点
    private Branch branch;//
    private String pname;// 父名称
    private String formdata;
    private String cid;// 分中心id
    private String did;// 部门id
    private String compName;// 分公司名称
    private String flg;//状态 判断是否可以编辑 true可以 false不可以

    /**
     * 职务族列表页面
     */
    public String list() {
        return "list";
    }

    /**
     * 职务族添加页面
     */
    public String dutylistadd() {
        PostDuty postDutyprt = postService.get(PostDuty.class, id); 
        postDuty=new PostDuty();
        if (postDutyprt != null) {
            postDuty.setPid(postDutyprt.getId());   
            name=(postDutyprt.getPostName());
            PostDuty pDuty = postService.get(PostDuty.class,
                    postDuty.getPid());
            if (pDuty != null) {
                pname = pDuty.getPostName();
            }
        } 
        return "dutylistadd";
    }

    /**
     * 职务族修改页面
     * 
     * @Title: dutylistedit
     * @return String
     * @author duanjj
     * @since 2017年3月28日 V 1.0
     */
    public String dutylistedit() {
        postDuty = postService.get(PostDuty.class, id);
        // 获取父职务和公司信息
        if (postDuty != null) {
            if (!"-1".equals(postDuty.getPid())) {
                PostDuty pDuty = postService.get(PostDuty.class,
                        postDuty.getPid());
                if (pDuty != null) {
                    pname = pDuty.getPostName();
                }
            }
            Branch branch = postService.get(Branch.class, postDuty.getCompId());
            if (branch != null) {
                compName = branch.getFzx();
            }
        }

        return "dutylistadd";
    }

    /**
     * @Title: 职务族树选择
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月3日 V 1.0
     */
    public String dutytree() {
        return "dutytree";
    }

    /**
     * 职务族修改保存方法
     * 
     * @Title: dutySave
     * @return String
     * @author duanjj
     * @since 2017年3月28日 V 1.0
     */
    public String dutySave() {
        try {
            String flg = postService.dutySave(formdata);
            if (flg.equals("success")) {
                return ajax(Status.success, "success");
            } else {
                return ajax(Status.error, flg);
            }
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    /**
     * 职务族树
     * 
     * @Title: treeList
     * @return String
     * @author duanjj
     * @since 2017年3月29日 V 1.0
     */
    public String treeList() {
        String json = postService.treeList();
        return ajax(Status.success, json);
    }
    
    /**
     * 职务族删除
     * @Title: deleteDuty 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月29日 V 1.0
     */
    public String deleteDuty(){
        String json = postService.deleteDuty(id);
        return ajax(Status.success, json);
    }
    /**
     * 岗位列表页面--传入职务族id,查询所有相关的数据
     * @Title: positionList 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月29日 V 1.0
     */
    public String positionList(){
        String json = postService.positionList(id,pager);
        return ajax(Status.success, json);
    }
    
    
    /**
     * @Title: 岗位数据选择页面
     * @return 
     * String 
     * @author duanjj
     * @since 2017年3月29日 V 1.0
     */
    public String show(){
        return "show";
    
    }
    
    
    /**
     * 岗位添加
     */
    public String add() {
        postPosition = new PostPosition();
        postPosition.setDutyId(cid); 
        return "add";
    }
    
    /**
     * 岗位添加
     */
    public String editposition() {
        postPosition = postService.get(PostPosition.class, id);
        if(postPosition!=null){
            if(postPosition.getPositionGrad()!=null){
                positionGrade = postService.get(PositionGrade.class, postPosition.getPositionGrad());
            }
        }
        return "add";
    }
    
    /**
     * 岗位添加
     */
    public String lookposition() {
        postPosition = postService.get(PostPosition.class, id);
        if(postPosition!=null){
            if(postPosition.getPositionGrad()!=null){
                positionGrade = postService.get(PositionGrade.class, postPosition.getPositionGrad());
            }
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
            String flg = postService.postionSave(formdata);
            if (flg.equals("success")) {
                return ajax(Status.success, "success");
            } else {
                return ajax(Status.error, flg);
            }
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    
    }
    //removePosition
    public String postionDelete(){
        String json = postService.postionDelete(id);
        return ajax(Status.success, json);
    
    }
    
    /**
     * 根据职务族id，查询该职务族的人员
     * @Title: personList 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月11日 V 1.0
     */
    public String personList(){
        String json = postService.personList(id,pager);
        return ajax(Status.success, json);
    }

    // ////////////////////////////////////////////////////////////////////////
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

    public PostDuty getPostDuty() {
        return postDuty;
    }

    public void setPostDuty(PostDuty postDuty) {
        this.postDuty = postDuty;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
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

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public PositionGrade getPositionGrade() {
        return positionGrade;
    }

    public void setPositionGrade(PositionGrade positionGrade) {
        this.positionGrade = positionGrade;
    }

}
