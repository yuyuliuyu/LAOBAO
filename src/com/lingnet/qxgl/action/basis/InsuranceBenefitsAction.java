package com.lingnet.qxgl.action.basis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.basic.InsuranceBenefits;
import com.lingnet.qxgl.entity.JcDictionary;
import com.lingnet.util.JsonUtil;

/**
 * 保险福利项目ACTION
 * @ClassName: InsuranceBenefitsAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年4月19日 下午2:23:52 
 *
 */
public class InsuranceBenefitsAction extends BaseAction { 
    private static final long serialVersionUID = 1L;
    private String id;
    private String jsondata;
    private InsuranceBenefits insurbenefit;
    private String state;
    private String insuranceName;// 福利名称
    public String list(){
        return "list";
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String jsonlist(){ 
        try {
            pager=this.findPager(InsuranceBenefits.class, pager,Restrictions.isNotNull("id"));
        } catch (Exception e) { 
            e.printStackTrace();
        } 
        Map result = new HashMap(); 
        List<Map<String, String>> datalist=new ArrayList<Map<String,String>>();
        for (int i = 0; i < pager.getResult().size(); i++) {
            insurbenefit=(InsuranceBenefits) pager.getResult().get(i);
            Map<String, String> data = new HashMap<String, String>(); 
            data.put("id", insurbenefit.getId());
            data.put("name", insurbenefit.getName());
            data.put("insuranceModel", insurbenefit.getInsuranceModel());
            if(insurbenefit.getType()!=null){
                data.put("type", this.getBeanById(JcDictionary.class, insurbenefit.getType()).getDicname());
            }else{
                data.put("type","");
                }
            if(insurbenefit.getIspay()==null||insurbenefit.getIspay()==0){ 
                data.put("ispay", "否");
            }else{
                data.put("ispay", "是");
            }
            if(insurbenefit.getIsvalid()==null||insurbenefit.getIsvalid()==0){ 
                data.put("isvalid", "无效");
            }else{
                data.put("isvalid", "有效");
            } 
            datalist.add(data);
        }
        
        result.put("data", datalist); 
        result.put("total", pager.getTotalCount()); 
        String json=JsonUtil.Encode(result);
        return ajax(Status.success,json); 
    }

    /**
     * @Title: 获取全部福利项目 
     * @return 
     * Stirng 
     * @author zhanghj
     * @since 2017年4月20日 V 1.0
     */
    public String getAllItemsListData() {
        Conjunction and = Restrictions.conjunction();
        if (!StringUtils.isBlank(id)) {
            and.add(Restrictions.eq("id", id));
        }
        if (!StringUtils.isBlank(insuranceName)) {
            and.add(Restrictions.like("name", insuranceName, MatchMode.ANYWHERE));
        }
        List<InsuranceBenefits> list = getBeanListByCriteria(InsuranceBenefits.class, and);
        return ajax(Status.success, JsonUtil.Encode(list)); 
    }
    public String add(){
        if(id==null||"".equals(id.trim())){
            insurbenefit=new InsuranceBenefits();   
        }else{
            insurbenefit=this.getBeanById(InsuranceBenefits.class, id);
        }
        return "add";
    }
    public String saveOrUpdate(){
        if(jsondata==null||"".equals(jsondata.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        insurbenefit=JsonUtil.toObject(jsondata, InsuranceBenefits.class);
        if(insurbenefit==null){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else if(insurbenefit.getId()==null||"".equals(insurbenefit.getId().trim())){
            this.save(insurbenefit);
        }else{
            this.update(insurbenefit);
        }
        return ajax(Status.success,"保存/修改成功");
    }
    public String changeinfo(){
        return ajax(Status.success,"");
    } 
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else{
            try {
                this.deleteByCriteria(InsuranceBenefits.class, Restrictions.eq("id", id));
                return ajax(Status.success,"删除成功");
            } catch (Exception e) {
                e.printStackTrace();
                return ajax(Status.success,"数据错误，请联系管理员");
            }
        }
    }
    /*-----------------------------------------------------------------------------------------*/
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
    public InsuranceBenefits getInsurbenefit() {
        return insurbenefit;
    }
    public void setInsurbenefit(InsuranceBenefits insurbenefit) {
        this.insurbenefit = insurbenefit;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getInsuranceName() {
        return insuranceName;
    }
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }
}
