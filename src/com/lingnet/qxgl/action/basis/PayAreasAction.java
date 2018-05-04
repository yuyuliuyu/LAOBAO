package com.lingnet.qxgl.action.basis;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.basic.AreasInfo;
import com.lingnet.hcm.entity.basic.PayAreas;
import com.lingnet.hcm.entity.basic.SocialWage;
import com.lingnet.util.JsonUtil;
public class PayAreasAction extends BaseAction{ 
    private static final long serialVersionUID = 1L;

    private String id;
    private String pid; 
    private String jsondata;
    private PayAreas payareas;
    private SocialWage wageinfo;
    private String state;
    private AreasInfo areasinfo;
    private String areaName;// 地区名称
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String pjsonlist(){
        List<AreasInfo> arealist=new ArrayList<AreasInfo>();
        if(pid==null||"".equals(pid.trim())){
            arealist=this.getBeanListByCriteria(AreasInfo.class, Restrictions.isNotNull("pid"));
        }else{
        try { 
            arealist=this.getBeanListByCriteria(AreasInfo.class, Restrictions.eq("pid",pid));
       } catch (Exception e) {
           e.printStackTrace();
       }
        }
        Map result = new HashMap();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>(); 
        if(arealist!=null&&arealist.size()>0){
            for (int i = 0; i < arealist.size(); i++) {
                areasinfo=arealist.get(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("id",areasinfo.getId()); 
                map.put("text", areasinfo.getName());
                list.add(map);
           }
        }
        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json);
    }
    public String list(){
        return "list";
    }
    public String jsonlist_wage(){
        List<SocialWage> wagelist=new ArrayList<SocialWage>();
        try {
            if(state==null||"".equals(state)){
                state="";
            }else if("1".equals(state)){
                state="社平工资";
            }else if("2".equals(state)){
                state="职工平均工资";
            }else if("3".equals(state)){
                state="最低工资";
            }
            wagelist=this.getBeanListByCriteria(SocialWage.class,Restrictions.eq("type",state));
        } catch (Exception e) { 
            e.printStackTrace();
        } 
        List<Map<String, String>> datalist=new ArrayList<Map<String,String>>();
        for (int i = 0; i < wagelist.size(); i++) {
            wageinfo=wagelist.get(i);
            Map<String, String> data = new HashMap<String, String>(); 
            data.put("id", wageinfo.getId()); 
            data.put("pid", wageinfo.getPid()); 
            data.put("type", wageinfo.getType()); 
            data.put("year", wageinfo.getYear()); 
            data.put("wagemoney", wageinfo.getWagemoney()+""); 
            if(wageinfo.getEnddate()==null){
                data.put("enddate", wageinfo.getEnddate()+""); 
            }else{
                data.put("enddate", new SimpleDateFormat("yyyy-MM-dd").format(wageinfo.getEnddate())); 
            }
            if(wageinfo.getStartdate()==null){
                data.put("startdate", wageinfo.getStartdate()+""); 
            }else{
                data.put("startdate", new SimpleDateFormat("yyyy-MM-dd").format(wageinfo.getStartdate())); 
            }  
            datalist.add(data);
        } 
        String json=JsonUtil.Encode(datalist);
        return ajax(Status.success,json);
    }
    public String jsonlist(){
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT                        ");
        sql.append("  JPA.ID, JA.NAME,JA_CITY.NAME city,JPA.AREA_STATE  ");
        sql.append("  FROM JC_PAY_AREAS JPA         ");
        sql.append("  LEFT JOIN JC_AREAS JA         ");
        sql.append("  ON JPA.AREA_PROVINCE = JA.ID  ");
        sql.append("  LEFT JOIN JC_AREAS JA_CITY    ");
        sql.append("  ON JPA.AREA_CITY = JA_CITY.ID ");

        if (!StringUtils.isBlank(areaName)) {
            sql.append("  WHERE JA_CITY.NAME LIKE '%"+areaName.trim()+"%'   ");
        }
        sql.append("  ORDER BY JPA.CREATEDATE ASC   ");

        pager = findPagerBySql(pager, sql.toString());
        List<?> list = pager.getResult();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", obj[0]);
            map.put("province", obj[1]);
            map.put("city", obj[2]);
            map.put("state", obj[3]);
            dataList.add(map);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());

        return ajax(Status.success, JsonUtil.Encode(map)); 
    }
    public String add(){
        if(id==null||"".equals(id.trim())){
            payareas=new PayAreas();   
            payareas.setState(1);
        }else{
            payareas=this.getBeanById(PayAreas.class, id);
        }
        return "add";
    }
    public String addwage(){
        if(id==null||"".equals(id.trim())){
            wageinfo=new SocialWage();
            wageinfo.setPid(pid);
        }else{
            wageinfo=this.getBeanById(SocialWage.class, id);
        }
        return "wage";
    }
    public String saveOrUpdate_wage(){ 
        if(jsondata==null||"".equals(jsondata.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        wageinfo=JsonUtil.toObject(jsondata, SocialWage.class);
        if(wageinfo==null){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else if(wageinfo.getId()==null||"".equals(wageinfo.getId().trim())){
            this.save(wageinfo);
        }else{
            this.update(wageinfo);
        }
        return ajax(Status.success,"保存/修改成功");
    }
    public String saveOrUpdate(){
        if(jsondata==null||"".equals(jsondata.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }
        payareas=JsonUtil.toObject(jsondata, PayAreas.class);
        if(payareas==null){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else if(payareas.getId()==null||"".equals(payareas.getId().trim())){
            payareas.setState(1);
            this.save(payareas);
        }else{
            this.update(payareas);
        }
        return ajax(Status.success,"保存/修改成功");
    }
    public String changeinfo(){
        return ajax(Status.success,"");
    } 
    public String remove_wage(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else{
            try {
                this.deleteByCriteria(SocialWage.class, Restrictions.eq("id", id));
                return ajax(Status.success,"删除成功");
            } catch (Exception e) {
                e.printStackTrace();
                return ajax(Status.success,"数据错误，请联系管理员");
            }
        }
    }
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，请联系管理员");
        }else{
            try {
                this.deleteByCriteria(PayAreas.class, Restrictions.eq("id", id));
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
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public PayAreas getPayareas() {
        return payareas;
    }
    public void setPayareas(PayAreas payareas) {
        this.payareas = payareas;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public SocialWage getWageinfo() {
        return wageinfo;
    }
    public void setWageinfo(SocialWage wageinfo) {
        this.wageinfo = wageinfo;
    }
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
