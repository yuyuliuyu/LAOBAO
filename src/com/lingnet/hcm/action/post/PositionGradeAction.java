package com.lingnet.hcm.action.post;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.PositionGrade;
import com.lingnet.hcm.service.post.PositionGradeService;
import com.lingnet.qxgl.entity.Branch;
/**
 * 
 * @ClassName: PositionGradeAction 
 * @Description: 职等Action
 * @author duanjj
 * @date 2017年4月14日 上午8:50:01 
 *
 */
@Controller
public class PositionGradeAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = -5741288545011674607L;
    private PositionGrade positionGrade;
    private String flg;//状态
    @Resource(name="positionGradeService")
    private PositionGradeService positionGradeService;
    private Branch branch;
    private String cid;
    
    /**
     * 跳转到list页面
     */
    public String list(){
        return LIST;
    }
    /**
     * 跳转到ADD页面
     */
    public String add(){
        positionGrade = new PositionGrade();
        positionGrade.setCompanyId(cid);
        branch = this.getBeanById(Branch.class, cid);
        return ADD;
    }
    /**
     * 跳转到EDIT页面
     */
    public String edit(){
        positionGrade = positionGradeService.get(PositionGrade.class, id);
        branch = this.getBeanById(Branch.class, positionGrade.getCompanyId());
        if(positionGrade==null){
            return ajax(Status.error,"数据已经删除，无法修改");
        } 
        return ADD;
    }
    /**
     * 
     * @Title: 列表页面数据 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月14日 V 1.0
     */
    public String listdata(){
        String json = positionGradeService.positionList(id,pager);
        return ajax(Status.success, json);
    }
    
    /**
     * @Title: 删除数据
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月14日 V 1.0
     */
    public String delete(){
        try {
            String json = positionGradeService.deleteGrades(id);
            return ajax(Status.success, json);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error, e.getMessage());
        }
       
    }
    /**
     * @Title: 职等保存方法 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月17日 V 1.0
     */
    public String save(){
        try {
            String flg = positionGradeService.saveOrUpdate(formdata);
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
     * @Title: 数据选择页面 
     * @return 
     * String 
     * @author duanjj
     * @since 2017年4月17日 V 1.0
     */
    public String show(){
        return "show";
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
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }

}
