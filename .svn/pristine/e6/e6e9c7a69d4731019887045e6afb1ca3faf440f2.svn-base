package com.lingnet.hcm.action.branch;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.basic.PerformanceAppraisal;
import com.lingnet.hcm.service.branch.PerformanceAppraisalService;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;

public class PerformanceAppraisalAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private String id;
    private String jsondata;
    private String state;
    private PerformanceAppraisal performanceAppraisal;

    @Resource(name="performanceAppraisalService")
    private PerformanceAppraisalService performanceAppraisalService;

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String list(){
        return "list";
    }

    /**
     * @Title: 列表展现页 
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String show(){
        return "show";
    }

    /**
     * @Title: 列表增加页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String add(){
        depId = LingUtil.userinfo().getDepId();
        QxDepartment qd = getBeanByCriteria(QxDepartment.class, Restrictions.eq("id", depId));
        if (qd != null) jsondata = qd.getName();

        return "add";
    }

    /**
     * @Title: 列表编辑页
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月6日 V 1.0
     */
    public String edit() {
        performanceAppraisal = getBeanByCriteria(PerformanceAppraisal.class, Restrictions.eq("id", id));
        if (performanceAppraisal != null) {
            depId = performanceAppraisal.getPerforDeptId();
            jsondata = performanceAppraisal.getPerforDeptName();
        }
        return "add";
    }

    /**
     * @Title: 列表展示数据
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String jsonlist(){
        return ajax(Status.success, JsonUtil.Encode(performanceAppraisalService.jsonlist(searchData, pager)));
    }

    /**
     * @Title: 获取当前登录人以及领导部门下的人的考核信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String getCurAndDeptDatas() {
        String userId = LingUtil.userName();
        return ajax(Status.success, JsonUtil.Encode(performanceAppraisalService.getCurAndDeptDatas(userId, searchData, pager)));
    }

    /**
     * @Title: 保存或者更新
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月13日 V 1.0
     */
    public String saveOrUpdate(){
        return ajax(Status.success, performanceAppraisalService.saveOrUpdate(formdata));
    }

    /**
     * @Title: 删除方法
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String remove(){
        return ajax(Status.success, performanceAppraisalService.remove(ids));
    }

    /**
     * @Title: 考核兑现
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String checkSure(){
        return ajax(Status.success, performanceAppraisalService.checkSure(ids));
    }

    /**
     * @Title: 考核不兑现
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月13日 V 1.0
     */
    public String checkCancel(){
        return ajax(Status.success, performanceAppraisalService.checkCancel(ids));
    }

    /**
     * @Title: 确认按钮
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String rebiut(){
        return ajax(Status.success, performanceAppraisalService.rebiut(ids));
    }

    /**
     * @Title: 获取当前公司所有人信息
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年7月12日 V 1.0
     */
    public String getCurComStaffDatas() {
        return ajax(Status.success, JsonUtil.Encode(performanceAppraisalService.getCurComStaffDatas(searchData, pager)));
    }

    /**
     * @Title: 绩效考核一览导出 
     * void 
     * @author zhanghj
     * @since 2017年8月13日 V 1.0
     */
    public void export() {
        performanceAppraisalService.export(searchData);
    }

    /**
     * @Title: 个人绩效考核一览导出 
     * void 
     * @author zhanghj
     * @since 2017年8月13日 V 1.0
     */
    public void exportForPer() {
        String userId = LingUtil.userName();
        performanceAppraisalService.exportForPer(userId, searchData);
    }

    /*--------------------------------------------------------------------------------------------------------------*/
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getJsondata() {
        return jsondata;
    }
    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public PerformanceAppraisal getPerformanceAppraisal() {
        return performanceAppraisal;
    }

    public void setPerformanceAppraisal(PerformanceAppraisal performanceAppraisal) {
        this.performanceAppraisal = performanceAppraisal;
    }

}
