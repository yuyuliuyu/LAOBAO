package com.lingnet.hcm.action.branch;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.ComChange;
import com.lingnet.hcm.entity.OrgHisMiddle;
import com.lingnet.hcm.service.branch.ComChangeService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;

/**
 * 
 * @ClassName: ComChangeAction
 * @Description: 公司申请变更Action
 * @author duanjj
 * @date 2017年3月27日 上午9:43:29
 *
 */
@Controller
public class ComChangeAction extends BaseAction {

    private static final long serialVersionUID = -6219734870317853683L;

    @Resource(name = "comChangeService")
    private ComChangeService comChangeService;
    private ComChange comChange;
    private OrgHisMiddle orgHisMiddle;
    private String changeType;
    private String cid;
    private String img;
    private String formdata;
    private String imgpath;// 图片地址
    private Branch branch;
    private String flg;
    private String appCompName;
    private String effectiveName;//
    
    private String parentName;

    /**
     * 跳转到相关添加页面
     */
    public String add() {
        orgHisMiddle = new OrgHisMiddle();
        orgHisMiddle.setChangeType(Integer.parseInt(changeType));
        orgHisMiddle.setType(0);
        comChange = new ComChange();
        // 变更类型 设立0 调整1 转移2 撤销3 合并4
        if ("0".equals(changeType)) {
            return "add";
        } else if ("1".equals(changeType)) {
            Branch branch = comChangeService.get(Branch.class, cid);
            orgHisMiddle.setChangeId(cid);
            orgHisMiddle.setChangeName(branch.getFzx());
            // comChange.setFzx(branch.getFzx());
            return "update";
        } else if ("2".equals(changeType)) {

            Branch branch = comChangeService.get(Branch.class, cid);
            orgHisMiddle.setChangeId(cid);
            orgHisMiddle.setChangeName(branch.getFzx());
            comChange.setFzx(branch.getFzx());
            return "change";
        } else if ("3".equals(changeType)) {
            Branch branch = comChangeService.get(Branch.class, cid);
            orgHisMiddle.setChangeId(cid);
            orgHisMiddle.setChangeName(branch.getFzx());
            comChange.setFzx(branch.getFzx());
            return "cancel";
        } else {
            return "add";
        }

    }

    /**
     * 跳转到相关编辑页面
     */
    public String edit() {
        orgHisMiddle = comChangeService.get(OrgHisMiddle.class, id);

        if (orgHisMiddle != null) {
            if(orgHisMiddle.getChangeType()==2){
                Branch b = comChangeService.get(Branch.class,
                        orgHisMiddle.getEffectiveId());
                if(b!=null){
                    effectiveName = branch.getFzx();
                }
            }
            comChange = comChangeService.get(ComChange.class,
                    eq("middleId", orgHisMiddle.getId()));
            branch = comChangeService.get(Branch.class,
                    orgHisMiddle.getApplyComp());
            if(branch!=null){
                appCompName = branch.getFzx();
            }else{
                QxDepartment dept = comChangeService.get(QxDepartment.class, orgHisMiddle.getApplyComp());
                if(dept!=null){
                    appCompName = dept.getName();
                }
            }
            if(comChange!=null){
                Branch b = comChangeService.get(Branch.class,
                        comChange.getPid());
                if (b != null) {
                    parentName = b.getFzx();
                }
            }
        }
        // 变更类型 设立0 调整1 转移2 撤销3 合并4
        if ("0".equals(changeType)) {
            return "add";
        } else if ("1".equals(changeType)) {
            return "update";
        } else if ("2".equals(changeType)) {
            return "change";
        } else if ("3".equals(changeType)) {
            return "cancel";
        } else {
            return "add";
        }
    }

    /**
     * 跳转到相关查看页面
     */
    public String look() {
        orgHisMiddle = comChangeService.get(OrgHisMiddle.class, id);

        if (orgHisMiddle != null) {
            comChange = comChangeService.get(ComChange.class,
                    eq("middleId", orgHisMiddle.getId()));
            branch = comChangeService.get(Branch.class,
                    orgHisMiddle.getApplyComp());
            if(branch!=null){
                appCompName = branch.getFzx();
            }else{
                QxDepartment dept = comChangeService.get(QxDepartment.class, orgHisMiddle.getApplyComp());
                if(dept!=null){
                    appCompName = dept.getName();
                }
            }
            if(comChange!=null){
                Branch b = comChangeService.get(Branch.class,
                        comChange.getPid());
                if (b != null) {
                    parentName = b.getFzx();
                }
            }
        }
        // 变更类型 设立0 调整1 转移2 撤销3 合并4
        if ("0".equals(changeType)) {
            return "add";
        } else if ("1".equals(changeType)) {
            return "update";
        } else if ("2".equals(changeType)) {
            return "change";
        } else if ("3".equals(changeType)) {
            return "cancel";
        } else {
            return "add";
        }
    }

    /**
     * 跳转list页面
     */
    public String list() {
        return "list";
    }

    /**
     * @Title: 列表页面数据展示
     * @return String
     * @author duanjj
     * @since 2017年4月18日 V 1.0
     */
    public String listdata() {
        String json = comChangeService.listdata(id, pager);
        return ajax(Status.success, json);
    }

    /**
     * 保存、修改方法
     */
    public String saveOrUpdate() {
        try {
            String flg = comChangeService.saveOrUpdata(formdata, img);
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
            String flg = comChangeService.deleteByIds(id);
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
     * @since 2017年4月20日 V 1.0
     */
    public String changeSubmit(){
        try {
            String flg = comChangeService.changeSubmit(id);
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
     * @since 2017年4月24日 V 1.0
     */
    public String auditList(){
        return "audit_list";
    }
    
    public String auditData(){
        String json = comChangeService.auditData(id, pager);
        return ajax(Status.success, json);
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
        orgHisMiddle = comChangeService.get(OrgHisMiddle.class, id);
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
            String flg = comChangeService.audit(formdata);
            if (flg.equals("success")) {
                return ajax(Status.success, "success");
            } else {
                return ajax(Status.error, flg);
            }
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }
    public ComChange getComChange() {
        return comChange;
    }

    public void setComChange(ComChange comChange) {
        this.comChange = comChange;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public OrgHisMiddle getOrgHisMiddle() {
        return orgHisMiddle;
    }

    public void setOrgHisMiddle(OrgHisMiddle orgHisMiddle) {
        this.orgHisMiddle = orgHisMiddle;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFormdata() {
        return formdata;
    }

    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getAppCompName() {
        return appCompName;
    }

    public void setAppCompName(String appCompName) {
        this.appCompName = appCompName;
    }

    public String getEffectiveName() {
        return effectiveName;
    }

    public void setEffectiveName(String effectiveName) {
        this.effectiveName = effectiveName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

}
