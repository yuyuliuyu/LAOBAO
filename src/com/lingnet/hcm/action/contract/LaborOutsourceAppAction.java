package com.lingnet.hcm.action.contract;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.LaborOutsourceApp;
import com.lingnet.hcm.entity.check.CkOvertimeSub;
import com.lingnet.hcm.service.contract.LaborOutsourceAppService;
import com.lingnet.qxgl.entity.Branch;

public class LaborOutsourceAppAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = -5377260726737279471L;
    @Resource(name="laborOutsourceAppService")
    private LaborOutsourceAppService laborOutsourceAppService;
    
    private LaborOutsourceApp laborOutsourceApp;
    private String formdata;
    private String cid;
    private Branch branch;
    private String applyComName;
    private String payComName;
    
    
    /**
     * 跳转到相关添加页面
     */
    public String add() {
        laborOutsourceApp = new LaborOutsourceApp();
        return ADD;
    }
    /**
     * 跳转到相关编辑页面
     */
    public String edit() {
        laborOutsourceApp = laborOutsourceAppService.get(LaborOutsourceApp.class, id);
        if(laborOutsourceApp==null){
            return ajax(Status.error,"数据已经删除！");
        }
        branch = laborOutsourceAppService.get(Branch.class, laborOutsourceApp.getApplyCom());
        if(branch!=null){
            applyComName = branch.getFzx();
        }
        branch = laborOutsourceAppService.get(Branch.class, laborOutsourceApp.getPayCom());
        if(branch!=null){
            payComName = branch.getFzx();
        }
        List<CkOvertimeSub> sublist=new ArrayList<CkOvertimeSub>();
        sublist=this.getBeanListByCriteria(CkOvertimeSub.class,
                Restrictions.eq("auditStatus", 1),
                Restrictions.eq("nodeId", laborOutsourceApp.getId()));
        formdata="";
        String looking="";
        if(sublist==null){
            return ADD;
        }
        for (int i = 0; i < sublist.size(); i++) {
            CkOvertimeSub sub=sublist.get(i);
            if(sub.getAuditStatus()==0){
                looking="驳回本计划";
            }else{
                looking="同意本计划";
            }
            formdata=formdata+sub.getAuditName()+"       于"+sub.getAuditDate()+looking+" <br>" +"         意见："+sub.getOpinion()+" <br>";
        }
        return ADD;
    }
    
    /**
     * 跳转到相关查看页面
     */
    public String look() {
        laborOutsourceApp = laborOutsourceAppService.get(LaborOutsourceApp.class, id);
        branch = laborOutsourceAppService.get(Branch.class, laborOutsourceApp.getApplyCom());
        if(branch!=null){
            applyComName = branch.getFzx();
        }
        branch = laborOutsourceAppService.get(Branch.class, laborOutsourceApp.getPayCom());
        if(branch!=null){
            payComName = branch.getFzx();
        }
        return ADD;
    }
    
    /**
     * 跳转list页面
     */
    public String list() {
        return "list";
    }
    
    /**
     * 跳转tree页面
     */
    public String tree() {
        return "tree";
    }
    
    /**
     * @Title: 列表页面数据展示
     * @return String
     * @author duanjj
     * @since 2017年4月25日 V 1.0
     */
    public String listdata() {
        String json = laborOutsourceAppService.listdata(id, pager);
        return ajax(Status.success, json);
    }
    
    /**
     * 保存、修改方法
     */
    public String saveOrUpdate() {
        try {
            String flg = laborOutsourceAppService.saveOrUpdata(formdata);
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
     * 删除方法
     */
    public String delete() {
        try {
            String flg = laborOutsourceAppService.deleteByIds(id);
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
     * 
     * @Title: 提交信息 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月25日 V 1.0
     */
    public String changeSubmit(){
        try {
            String flg = laborOutsourceAppService.changeSubmit(id);
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
     * 审核列表页面
     * @Title: auditList 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月25日 V 1.0
     */
    public String auditList(){
        return "audit_list";
    }
    
    /**
     * 审核页面
     * @Title: audit 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月24日 V 1.0
     */
    public String audit(){
        laborOutsourceApp = laborOutsourceAppService.get(LaborOutsourceApp.class, id);
        return "audit";
    }
    /**
     * 审核方法
     * @Title: auditSubmit 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月24日 V 1.0
     */
    public String auditSubmit(){
        try {
            String flg = laborOutsourceAppService.audit(formdata);
            if (flg.equals("success")) {
                return ajax(Status.success, "success");
            } else {
                return ajax(Status.error, flg);
            }
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    public LaborOutsourceApp getLaborOutsourceApp() {
        return laborOutsourceApp;
    }

    public void setLaborOutsourceApp(LaborOutsourceApp laborOutsourceApp) {
        this.laborOutsourceApp = laborOutsourceApp;
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
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public String getApplyComName() {
        return applyComName;
    }
    public void setApplyComName(String applyComName) {
        this.applyComName = applyComName;
    }
    public String getPayComName() {
        return payComName;
    }
    public void setPayComName(String payComName) {
        this.payComName = payComName;
    }

}
