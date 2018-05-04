package com.lingnet.hcm.action.contract;

import javax.annotation.Resource;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.LaborOutsourceApp;
import com.lingnet.hcm.entity.Outcontract;
import com.lingnet.hcm.service.contract.OutcontractService;
import com.lingnet.qxgl.entity.Branch;

public class OutcontractAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = 7137913353708867194L;
    @Resource(name="outcontractService")
    private OutcontractService outcontractService;
    private String formdata;
    private String flg;
    private Outcontract outcontract;
    private String contractorNameJia;
    private String contractorNameYi;
    private String applyName;
    
    /**
     * 跳转到相关添加页面
     */
    public String add() {
        outcontract = new Outcontract();
        return ADD;
    }
    
    /**
     * 跳转到相关编辑页面
     */
    public String edit() {
        outcontract = outcontractService.get(Outcontract.class, id);
        if(outcontract==null){
            return ajax(Status.error,"数据已经删除！");
        }
        Branch b1 = outcontractService.get(Branch.class,outcontract.getContractorJia());
        if(b1!=null){
            contractorNameJia = b1.getFzx();
        }
        Branch b2 = outcontractService.get(Branch.class,outcontract.getContractorYi());
        if(b2!=null){
            contractorNameYi = b2.getFzx();
        }
        LaborOutsourceApp app = outcontractService.get(LaborOutsourceApp.class,outcontract.getApplyId());
        if(app!=null){
            setApplyName(app.getProjectName());
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
     * @Title: 列表页面数据展示
     * @return String
     * @author duanjj
     * @since 2017年4月26日 V 1.0
     */
    public String listdata() {
        String json = outcontractService.listdata(pager);
        return ajax(Status.success, json);
    }
    /**
     * 保存、修改方法
     */
    public String saveOrUpdate() {
        try {
            String flg = outcontractService.saveOrUpdata(formdata);
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
            String flg = outcontractService.deleteByIds(id);
            if (flg.equals("success")) {
                return ajax(Status.success, "success");
            } else {
                return ajax(Status.error, flg);
            }
        } catch (Exception e) {
            return ajax(Status.error, e.getMessage());
        }
    }
    
    public String getFormdata() {
        return formdata;
    }
    public void setFormdata(String formdata) {
        this.formdata = formdata;
    }
    public String getFlg() {
        return flg;
    }
    public void setFlg(String flg) {
        this.flg = flg;
    }
    public Outcontract getOutcontract() {
        return outcontract;
    }
    public void setOutcontract(Outcontract outcontract) {
        this.outcontract = outcontract;
    }

    public String getContractorNameJia() {
        return contractorNameJia;
    }

    public void setContractorNameJia(String contractorNameJia) {
        this.contractorNameJia = contractorNameJia;
    }

    public String getContractorNameYi() {
        return contractorNameYi;
    }

    public void setContractorNameYi(String contractorNameYi) {
        this.contractorNameYi = contractorNameYi;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
    
    
}
