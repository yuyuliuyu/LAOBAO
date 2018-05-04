package com.lingnet.hcm.action.check;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.check.ChangeApply;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
/**
 * 
 * @ClassName: ChangeApplyAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年7月16日 下午5:19:51 
 *
 */
@SuppressWarnings("serial")
public class ChangeApplyAction extends BaseAction {
    private String id;
    private String jsondata;
    private String changeno;
    private String state;
    private ChangeApply changeapp;
    
    /**
     * 打开列表页面
     */
    public String list(){
        return "list";
    }
    /**
     * 获得列表数据
     * @Title: jsonlist 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年7月16日 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String jsonlist(){
        StringBuilder sql = new StringBuilder();
        sql.append("  SELECT ID,MONTH,DAY,RESON,APPMAN,APPDATE,USERID,UDATE,USERNAME,APPLYNAME,STATE,CHACKNO,JOB_NUM,CREATEDATE,MODIFYDATE                                 ");
        sql.append("  FROM                                     ");
        sql.append("    (SELECT CCA.*                          ");
        sql.append("    FROM CK_CHANGE_APPLY CCA               ");
        sql.append("    LEFT JOIN QX_USERS QU                  ");
        sql.append("    ON CCA.USERID   = QU.USERNAME          ");
        sql.append("    WHERE QU.DEPID IN                      ");
        sql.append("      (SELECT QUS.BRANCH_DEP               ");
        sql.append("      FROM QX_USER_SHOWAUTH QUS            ");
        sql.append("      WHERE QUS.USERID = '"+LingUtil.userinfo().getId()+"'                ");
        sql.append("      )                                    ");
        sql.append("    UNION                                  ");
        sql.append("    SELECT CCA.*                           ");
        sql.append("    FROM CK_CHANGE_APPLY CCA               ");
        sql.append("    LEFT JOIN QX_USERS QU                  ");
        sql.append("    ON CCA.USERID = QU.USERNAME            ");
        sql.append("    WHERE QU.ID = '"+LingUtil.userinfo().getId()+"'                       ");
        sql.append("    ) QDATA                                ");
 		if (searchData != null){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);

			if (dataMap.get("username") != null && !"".equals(dataMap.get("username"))){
				sql.append(" WHERE USERNAME like '%"+dataMap.get("username")+"%'");
			}
 		} 
        try {
             if(changeno==null||"".equals(changeno.trim())){
                 sql.append("    and QDATA.ID IS NOT NULL   ");

                 sql.append("    ORDER BY QDATA.CHACKNO DESC   ");
                 pager = findPagerBySql(pager, sql.toString());
             }else{
                 sql.append("    ORDER BY QDATA.CHACKNO DESC   ");
                 pager = findPagerBySql(pager, sql.toString());
             }
             
         }catch (Exception e) {  
            HashMap result = new HashMap();
            result.put("data", "");
            result.put("total", pager.getTotalCount());
            String json = JsonUtil.Encode(result);
//            return ajax(Status.success, json); 
        }
       
         HashMap result = new HashMap();

         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         List<Map<String,Object>> resultlist=new ArrayList<Map<String,Object>>();
         List<?> list = pager.getResult();
         for (int i = 0; i < list.size(); i++) {
             Object[] obj=(Object[]) list.get(i);
             Map<String,Object> mapl=new HashMap<String, Object>(); 
             mapl.put("id",obj[0]);
             mapl.put("month",obj[1]);
             mapl.put("day", obj[2]);
             mapl.put("reson", obj[3]);
             mapl.put("username", obj[8]+"("+obj[6]+")");
             if(obj[7]!=null){
                 mapl.put("udate",sdf.format(obj[7]));
             }else{
                 mapl.put("udate", "");
             }
             mapl.put("applyname", obj[9]==null?"":obj[9]+"("+obj[4]+")");
             if(obj[10]!=null){// 0 申请中，1：审核通过  2：审核不通过
                 if(Integer.valueOf(obj[10].toString())==0){
                     mapl.put("state","申请中");
                 }
                 if(Integer.valueOf(obj[10].toString())==1){
                     mapl.put("state","审核通过");
                 }
                 if(Integer.valueOf(obj[10].toString())==2){
                     mapl.put("state","审核不通过");
                 }
             }else{
                 mapl.put("state","申请中"); 
             }
             if(obj[5]!=null){
                 mapl.put("appdate",sdf.format(obj[5]));
             }else{
                 mapl.put("appdate", "");
             } 
             resultlist.add(mapl);
         }
         result.put("data", resultlist);
         result.put("total", pager.getTotalCount());
         String json = JsonUtil.toJson(result);
         return ajax(Status.success, json); 
    }
    /**
     * 新增页面
     */
    public String add(){
        if(id==null||"".equals(id.trim())){
            changeapp=new ChangeApply();
        }else{ 
            changeapp=this.getBeanById(ChangeApply.class, id);
            if(changeapp.getState()>0){
                return "error";
            }
        }
        return "add";
    }
    /**
     * 保存
     */
    public String saveOrUpdate(){ 
        changeapp=JsonUtil.toObject(jsondata, ChangeApply.class);
        String jobnumber=LingUtil.userName();
        ChangeApply changeold=this.getBeanByCriteria(ChangeApply.class,
                Restrictions.eq("month", changeapp.getMonth()),
                Restrictions.eq("day", changeapp.getDay()),
                Restrictions.eq("userid",jobnumber));
        if(changeold!=null){
            return ajax(Status.success,"您今天的考勤已经申请修改过了哦"); 
        }
        changeapp.setState(0);
        if(changeapp.getId()==null||"".equals(changeapp.getId().trim())){ 
            String username=LingUtil.userName();
            changeapp.setUserid(username);
            changeapp.setUdate(new Date());
            BasicInformation basic=this.getBeanByCriteria(BasicInformation.class, 
                    Restrictions.or(Restrictions.eq("jobNumber", username), Restrictions.eq("practiceNum", username)));
            if(basic!=null){
                changeapp.setUsername(basic.getName());
                changeapp.setJobnumner(username);
                changeapp.setChackno(basic.getCheckNo());;
            }
            try { 
                this.save(changeapp);
            } catch (Exception e) {
                return ajax(Status.success,"操作失败"); 
            }
        }else{
            try {
                ChangeApply apply = getBeanByCriteria(ChangeApply.class, Restrictions.eq("id", changeapp.getId()));
                if (null ==apply) {
                    return ajax(Status.error,"该申请不存在"); 
                }
                apply.copyFrom(changeapp);
                this.update(apply);
            } catch (Exception e) {
                return ajax(Status.success,"操作失败"); 
            }
        }
        return ajax(Status.success,"success"); 
    }
    /**
     * 审核
     * @Title: apply 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年7月16日 V 1.0
     */
    public String apply(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据传输错误，请重新操作");
        } 
        String retstr="";
        changeapp=this.getBeanById(ChangeApply.class, id);
        if (null != changeapp) {
            if (changeapp.getState() == 1) {
                return ajax(Status.success,"该申请已经审核");
            }
        }
        QxUser userinfo=LingUtil.userinfo();
        changeapp.setAppman(userinfo.getUsername());
        changeapp.setAppdate(new Date());
        changeapp.setApplyname(userinfo.getName());
        try {
            changeapp.setState(Integer.parseInt(state));
            if("1".equals(state)){
                retstr="审核成功";
            }else{
                retstr="驳回成功";
            }
        } catch (Exception e) {
            return ajax(Status.success,"审核未成功");
        }
        this.update(changeapp);
        return ajax(Status.success,retstr);
    }

    /**
     * @Title: 驳回
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年8月30日 V 1.0
     */
    public String unapply(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据传输错误，请重新操作");
        } 
        String retstr="";
        changeapp=this.getBeanById(ChangeApply.class, id);
        if (null != changeapp) {
            if (changeapp.getState() == 2) {
                return ajax(Status.success,"该申请已经驳回");
            }
        }
        QxUser userinfo=LingUtil.userinfo();
        changeapp.setAppman(userinfo.getUsername());
        changeapp.setAppdate(new Date());
        changeapp.setApplyname(userinfo.getName());
        try {
            changeapp.setState(Integer.parseInt(state));
            if("1".equals(state)){
                retstr="审核成功";
            }else{
                retstr="驳回成功";
            }
        } catch (Exception e) {
            return ajax(Status.success,"审核未成功");
        }
        this.update(changeapp);
        return ajax(Status.success,retstr);
    }
    /**
     * 删除数据
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年7月16日 V 1.0
     */
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据传输错误，请重新操作");
        }
        try {
            this.deleteByCriteria(ChangeApply.class, Restrictions.eq("id", id));
        } catch (Exception e) { 
            return ajax(Status.success,"删除失败");
        }
        return ajax(Status.success,"删除成功");
    }
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
    public String getChangeno() {
        return changeno;
    }
    public void setChangeno(String changeno) {
        this.changeno = changeno;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public ChangeApply getChangeapp() {
        return changeapp;
    }
    public void setChangeapp(ChangeApply changeapp) {
        this.changeapp = changeapp;
    }
    
    
}
