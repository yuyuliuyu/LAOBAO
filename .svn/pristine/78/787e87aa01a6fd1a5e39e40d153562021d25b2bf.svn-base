package com.lingnet.hcm.action.branch;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptChange;
import com.lingnet.hcm.entity.OrgHisMiddle;
import com.lingnet.hcm.service.branch.DeptChangeService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;

public class DeptChangeAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = -921269000884542583L;
    private OrgHisMiddle orgHisMiddle;
    private String changeType;
    private String cid;
    private String img;
    private String formdata;
    private String imgpath;// 图片地址
    private Branch branch;
    private String flg;
    private String appCompName;
    private DeptChange deptChange;
    private String parentName;
    private String effectiveName;//

    @Resource(name = "deptChangeService")
    private DeptChangeService deptChangeService;

    /**
     * 跳转到相关添加页面
     */
    public String add() {
        orgHisMiddle = new OrgHisMiddle();
        orgHisMiddle.setChangeType(Integer.parseInt(changeType));
        orgHisMiddle.setType(0);
        deptChange = new DeptChange();
        // 变更类型 设立0 调整1 转移2 撤销3 合并4
        if ("0".equals(changeType)) {
            return "add";
        } else if ("1".equals(changeType)) {
            QxDepartment branch = deptChangeService
                    .get(QxDepartment.class, cid);
            orgHisMiddle.setChangeId(cid);
            orgHisMiddle.setChangeName(branch.getName());
            // deptChange.setFzx(branch.getFzx());
            return "update";
        } else if ("2".equals(changeType)) {

            QxDepartment branch = deptChangeService
                    .get(QxDepartment.class, cid);
            orgHisMiddle.setChangeId(cid);
            orgHisMiddle.setChangeName(branch.getName());
            deptChange.setName(branch.getName());
            return "change";
        } else if ("3".equals(changeType)) {
            QxDepartment branch = deptChangeService
                    .get(QxDepartment.class, cid);
            orgHisMiddle.setChangeId(cid);
            orgHisMiddle.setChangeName(branch.getName());
            deptChange.setName(branch.getName());
            return "cancel";
        } else if ("4".equals(changeType)) {
            QxDepartment branch = deptChangeService
                    .get(QxDepartment.class, cid);
            orgHisMiddle.setChangeId(cid);
            orgHisMiddle.setChangeName(branch.getName());
            deptChange.setName(branch.getName());
            return "mesh";
        } else {
            return "add";
        }

    }

    /**
     * 跳转到相关编辑页面
     */
    public String edit() {
        orgHisMiddle = deptChangeService.get(OrgHisMiddle.class, id);

        if (orgHisMiddle != null) {
            if (orgHisMiddle.getEffectiveId() != null) {
                Branch b = deptChangeService.get(Branch.class,
                        orgHisMiddle.getEffectiveId());
                if (b != null) {
                    effectiveName = b.getFzx();
                } else {
                    QxDepartment dept = deptChangeService.get(
                            QxDepartment.class, orgHisMiddle.getEffectiveId());
                    if (dept != null) {
                        effectiveName = dept.getName();
                    }
                }
            }
            deptChange = deptChangeService.get(DeptChange.class,
                    eq("middleId", orgHisMiddle.getId()));
            branch = deptChangeService.get(Branch.class,
                    orgHisMiddle.getApplyComp());
            if (branch != null) {
                appCompName = branch.getFzx();
            } else {
                QxDepartment dept = deptChangeService.get(QxDepartment.class,
                        orgHisMiddle.getApplyComp());
                if (dept != null) {
                    appCompName = dept.getName();
                }
            }
            if (deptChange != null) {
                Branch b = deptChangeService.get(Branch.class,
                        deptChange.getParent());
                if (b != null) {
                    parentName = b.getFzx();
                } else {
                    QxDepartment dept = deptChangeService.get(
                            QxDepartment.class, deptChange.getParent());
                    if (dept != null) {
                        parentName = dept.getName();
                    }
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
        orgHisMiddle = deptChangeService.get(OrgHisMiddle.class, id);

        if (orgHisMiddle != null) {
            if (orgHisMiddle.getEffectiveId() != null) {
                Branch b = deptChangeService.get(Branch.class,
                        orgHisMiddle.getEffectiveId());
                if (b != null) {
                    effectiveName = b.getFzx();
                } else {
                    QxDepartment dept = deptChangeService.get(
                            QxDepartment.class, orgHisMiddle.getEffectiveId());
                    if (dept != null) {
                        effectiveName = dept.getName();
                    }
                }
            }
            deptChange = deptChangeService.get(DeptChange.class,
                    eq("middleId", orgHisMiddle.getId()));
            branch = deptChangeService.get(Branch.class,
                    orgHisMiddle.getApplyComp());
            if (branch != null) {
                appCompName = branch.getFzx();
            } else {
                QxDepartment dept = deptChangeService.get(QxDepartment.class,
                        orgHisMiddle.getApplyComp());
                if (dept != null) {
                    appCompName = dept.getName();
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
     * 保存、修改方法
     */
    public String saveOrUpdate() {
        try {
            String flg = deptChangeService.saveOrUpdata(formdata, img);
            if (flg.equals("success")) {
                return ajax(Status.success, "success");
            } else {
                return ajax(Status.error, flg);
            }
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }

    public OrgHisMiddle getOrgHisMiddle() {
        return orgHisMiddle;
    }

    public void setOrgHisMiddle(OrgHisMiddle orgHisMiddle) {
        this.orgHisMiddle = orgHisMiddle;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public DeptChange getDeptChange() {
        return deptChange;
    }

    public void setDeptChange(DeptChange deptChange) {
        this.deptChange = deptChange;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getEffectiveName() {
        return effectiveName;
    }

    public void setEffectiveName(String effectiveName) {
        this.effectiveName = effectiveName;
    }

}
